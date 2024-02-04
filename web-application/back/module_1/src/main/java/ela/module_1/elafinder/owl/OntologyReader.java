package ela.module_1.elafinder.owl;

import ela.module_1.elafinder.models.*;
import ela.module_1.elafinder.utils.ResourceFileReader;
import org.apache.jena.arq.querybuilder.SelectBuilder;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.FileManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class OntologyReader {
    private static final String ELA_ONTOLOGY_OWL = "ela-ontology.owl";
    private final Properties prefixes;
    private final String RDF_PREDIX;
    private final String OWL_PREFIX;
    private final String RDFS_PREFIX;
    private final String XSD_PREFIX;
    private final String ELA_PREFIX;

    public OntologyReader() {
        prefixes = new Properties();
        try {
            prefixes.load(ResourceFileReader.getFileInputStreamReader("static/prefixes.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        RDF_PREDIX = (String) prefixes.get("rdf");
        RDFS_PREFIX = (String) prefixes.get("rdfs");
        OWL_PREFIX = (String) prefixes.get("owl");
        XSD_PREFIX = (String) prefixes.get("xsd");
        ELA_PREFIX = (String) prefixes.get("ela");
    }

    public List<EsotericLanguage> getEsotericLanguagesByCriteria(Criteria criteria) {
        List<EsotericLanguage> languages = new ArrayList<>();
        // Load an RDF model from a file (replace "data.ttl" with your RDF file)
        Model model = FileManager.getInternal().loadModelInternal("ela-ontology.owl");

        SelectBuilder selectBuilder = buildSelectForLanguage(criteria);

        // Execute the parameterized query
        System.out.println(selectBuilder);
        try (QueryExecution queryExecution = QueryExecutionFactory.create(selectBuilder.build(), model)) {
            ResultSet results = queryExecution.execSelect();

            // Process the results
            while (results.hasNext()) {
                QuerySolution solution = results.nextSolution();
                languages.add((buildEsotericLanguage(model, solution)));
            }
        }
        return languages;
    }

    private SelectBuilder buildSelectForLanguage(Criteria criteria) {
        SelectBuilder selectBuilder = new SelectBuilder()
                .addPrefix("ela", ELA_PREFIX)
                .addPrefix("rdf", RDF_PREDIX)
                .addPrefix("rdfs", RDFS_PREFIX)
                .addPrefix("owl", OWL_PREFIX)
                .addPrefix("xsd", XSD_PREFIX)
                .addVar("?language")
                .addVar("?author")
                .addVar("?date")
                .addVar("?language_description")
                .addVar("?language_name")
                .addVar("?diff_alias")
                .addWhere("?language", "a", "ela:Language")
                .addWhere("?language", "ela:hasDifficulty", "?language_difficulty")
                .addWhere("?language_difficulty", "ela:DifficultyAlias", "?diff_alias")
                .addOptional("?language", "ela:LanguageName", "?language_name")
                .addOptional("?language", "ela:LanguageDescription", "?language_description");

        if (criteria.getComplexity() != null && criteria.getComplexity().isRequired()) {
            selectBuilder.addFilter(String.format("(str(?diff_alias) = \"%s\")", criteria.getComplexity().getDifficulty()));
        }

        if (criteria.getReleaseYear() != null && criteria.getReleaseYear().isRequired()) {
            selectBuilder.addWhere("?language", "ela:DateOfCreation", "?date")
                    .addFilter(String.format("(year(?date) = %s)", criteria.getReleaseYear().getYear()));
        } else {
            selectBuilder.addOptional("?language", "ela:DateOfCreation", "?date");
        }

        if (criteria.getAuthorDetails() != null && criteria.getAuthorDetails().isRequired()) {
            selectBuilder.addWhere("?language", "ela:isCreatedBy", "?author")
                    .addWhere("?author", "ela:PersonName", "?author_name")
                    .addFilter(String.format("(\"%s\" in str(?author_name))", criteria.getAuthorDetails().getData().getName()));
        } else {
            selectBuilder.addOptional("?language", "ela:isCreatedBy", "?author");
        }

        if (criteria.getWithExamples() != null && criteria.getWithExamples().isRequired()) {
            selectBuilder.addWhere("?program", "ela:isExampleFor", "?language");
        }

        return selectBuilder;
    }

    private EsotericLanguage buildEsotericLanguage(Model model, QuerySolution solution) {
        EsotericLanguage esotericLanguage = new EsotericLanguage();

        Literal name = solution.getLiteral("language_name");
        Literal description = solution.getLiteral("language_description");
        Resource language = solution.getResource("language");

        List<EsotericLanguageCompiler> compilers = getCompilers(model, language);

        List<EsotericLanguageInterpreter> interpreters = getInterpreters(model, language);

        List<ProgramExample> programs = getProgramExamplesForLanguage(model, language);

        AuthorDetails author = getAuthorForLanguage(model, language);

        CriteriaComplexity.DifficultyEnum difficulty = CriteriaComplexity.DifficultyEnum.fromValue(solution.getLiteral("diff_alias").getLexicalForm());

        esotericLanguage.setName(name.getLexicalForm());
        esotericLanguage.setDescription(description.getLexicalForm());

        esotericLanguage.setCompilers(compilers);
        esotericLanguage.setInterpreters(interpreters);

        esotericLanguage.setAuthorDetails(author);

        esotericLanguage.setExamplesOfPrograms(programs);

        esotericLanguage.setDifficulty(difficulty);

        return esotericLanguage;
    }

    private List<EsotericLanguageCompiler> getCompilers(Model model, Resource language) {
        List<EsotericLanguageCompiler> compilers = new ArrayList<>();

        // Read the parameterized SPARQL query from the file
        String queryPath = "templates/queries/compilers.sparql";
        String queryString = readQueryFromFile(queryPath);

        // Create a ParameterizedSparqlString
        ParameterizedSparqlString parameterizedQuery = new ParameterizedSparqlString(queryString);

        // Set the parameter value
        parameterizedQuery.setIri("language_placeholder", language.getURI());

        // Execute the parameterized query
        try (QueryExecution qexec = QueryExecutionFactory.create(parameterizedQuery.asQuery(), model)) {
            ResultSet results = qexec.execSelect();

            // Process the results
            while (results.hasNext()) {
                QuerySolution solution = results.nextSolution();
                Literal name = solution.getLiteral("compiler_name");
                Literal description = solution.getLiteral("compiler_description");

                EsotericLanguageCompiler compiler = new EsotericLanguageCompiler();
                compiler.setName(name.getLexicalForm());
                compiler.setDescription(description.getLexicalForm());
                compilers.add(compiler);
            }
        }

        return compilers;
    }

    private List<EsotericLanguageInterpreter> getInterpreters(Model model, Resource language) {
        List<EsotericLanguageInterpreter> interpreters = new ArrayList<>();

        // Read the parameterized SPARQL query from the file
        String queryPath = "templates/queries/interpreters.sparql";
        String queryString = readQueryFromFile(queryPath);

        // Create a ParameterizedSparqlString
        ParameterizedSparqlString parameterizedQuery = new ParameterizedSparqlString(queryString);

        // Set the parameter value
        parameterizedQuery.setIri("language_placeholder", language.getURI());

        // Execute the parameterized query
        try (QueryExecution queryExecution = QueryExecutionFactory.create(parameterizedQuery.asQuery(), model)) {
            ResultSet results = queryExecution.execSelect();

            // Process the results
            while (results.hasNext()) {
                QuerySolution solution = results.nextSolution();
                Literal name = solution.getLiteral("interpreter_name");
                Literal description = solution.getLiteral("interpreter_description");

                EsotericLanguageInterpreter interpreter = new EsotericLanguageInterpreter();
                interpreter.setName(name.getLexicalForm());
                interpreter.setDescription(description.getLexicalForm());
                interpreters.add(interpreter);
            }
        }

        return interpreters;
    }

    private List<ProgramExample> getProgramExamplesForLanguage(Model model, Resource language) {
        List<ProgramExample> programs = new ArrayList<>();
// Load an RDF model from a file (replace "data.owl" with your RDF file)

        // Read the parameterized SPARQL query from the file
        String queryPath = "templates/queries/programs.sparql";
        String queryString = readQueryFromFile(queryPath);

        // Create a ParameterizedSparqlString
        ParameterizedSparqlString parameterizedQuery = new ParameterizedSparqlString(queryString);

        // Set the parameter value
        parameterizedQuery.setIri("language_placeholder", language.getURI());

        // Execute the parameterized query
        try (QueryExecution queryExecution = QueryExecutionFactory.create(parameterizedQuery.asQuery(), model)) {
            ResultSet results = queryExecution.execSelect();

            // Process the results
            while (results.hasNext()) {
                QuerySolution solution = results.nextSolution();
                Literal code = solution.getLiteral("source_code");
                Literal description = solution.getLiteral("description");

                ProgramExample program = new ProgramExample();
                program.setCode(code.getLexicalForm());
                program.setDescription(description.getLexicalForm());
                programs.add(program);
            }
        }
        return programs;
    }

    private AuthorDetails getAuthorForLanguage(Model model, Resource language) {
        AuthorDetails author = new AuthorDetails();

        // Read the parameterized SPARQL query from the file
        String queryPath = "templates/queries/author.sparql";
        String queryString = readQueryFromFile(queryPath);

        // Create a ParameterizedSparqlString
        ParameterizedSparqlString parameterizedQuery = new ParameterizedSparqlString(queryString);

        // Set the parameter value
        parameterizedQuery.setIri("language_placeholder", language.getURI());

        // Execute the parameterized query
        try (QueryExecution queryExecution = QueryExecutionFactory.create(parameterizedQuery.asQuery(), model)) {
            ResultSet results = queryExecution.execSelect();

            // Process the results
            while (results.hasNext()) {
                QuerySolution solution = results.nextSolution();
                Literal name = solution.getLiteral("author_name");
                Literal description = solution.getLiteral("author_description");

                author.setName(name.getLexicalForm());
            }
        }

        return author;
    }

    private String readQueryFromFile(String filePath) {
        try (InputStream inputStream = ResourceFileReader.getFileInputStreamReader((filePath))) {
            if (inputStream == null) {
                throw new IllegalArgumentException("File not found: " + filePath);
            }
            return FileManager.get().readWholeFileAsUTF8(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
