package ela.module_1.elafinder.owl;

import ela.module_1.elafinder.models.*;
import ela.module_1.elafinder.utils.ResourceFileReader;
import org.apache.jena.arq.querybuilder.SelectBuilder;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.FileManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class OntologyReader {
    private static final Logger log = LoggerFactory.getLogger(OntologyReader.class);
    private static final String ELA_ONTOLOGY_OWL = "ontology/ela-ontology.owl";

    public OntologyReader() {
    }

    public List<EsotericLanguageSummary> getEsotericLanguageSummariesByCriteria(Criteria criteria) {
        List<EsotericLanguageSummary> languages = new ArrayList<>();
// Load an RDF model from a file (replace "data.ttl" with your RDF file)
        Model model = FileManager.getInternal().loadModelInternal(ELA_ONTOLOGY_OWL);
        SelectBuilder selectBuilder = new SelectSparqlLanguageSummaryBuilder().buildSelectForLanguages(criteria);

        // Execute the parameterized query
        log.info(selectBuilder.toString());
        try (QueryExecution queryExecution = QueryExecutionFactory.create(selectBuilder.build(), model)) {
            ResultSet results = queryExecution.execSelect();

            // Process the results
            while (results.hasNext()) {
                QuerySolution solution = results.nextSolution();
                languages.add((buildEsotericLanguageSummary(solution)));
            }
        }

        return languages;
    }

    private EsotericLanguageSummary buildEsotericLanguageSummary(QuerySolution solution) {
        EsotericLanguageSummary summary = new EsotericLanguageSummary();

        Literal name = solution.getLiteral("language_name");
        String resourceLink = solution.getResource("language").getURI();

        summary.setName(name.getLexicalForm());
        summary.setResourceUri(resourceLink);

        return summary;
    }

    public EsotericLanguage getEsotericLanguageByName(String name) {
        // Load an RDF model from a file (replace "data.ttl" with your RDF file)
        Model model = FileManager.getInternal().loadModelInternal(ELA_ONTOLOGY_OWL);
        SelectBuilder selectBuilder = new SelectSparqlLanguageByName().buildSelectForLanguageByName(name);

        // Execute the parameterized query
        log.info(selectBuilder.toString());
        try (QueryExecution queryExecution = QueryExecutionFactory.create(selectBuilder.build(), model)) {
            ResultSet results = queryExecution.execSelect();

            // Process the results
            if (results.hasNext()) {
                QuerySolution solution = results.nextSolution();
                return buildEsotericLanguage(model, solution);
            }
        }
        return null;
    }

    private EsotericLanguage buildEsotericLanguage(Model model, QuerySolution solution) {
        EsotericLanguage esotericLanguage = new EsotericLanguage();

        Literal name = solution.getLiteral("language_name");
        Literal description = solution.getLiteral("language_description");
        Literal externalLink = solution.getLiteral("external_link");
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

        esotericLanguage.setExternalLink(URI.create(externalLink.getString()));

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
        try (QueryExecution queryExecution = QueryExecutionFactory.create(parameterizedQuery.asQuery(), model)) {
            ResultSet results = queryExecution.execSelect();

            // Process the results
            while (results.hasNext()) {
                QuerySolution solution = results.nextSolution();
                Literal name = solution.getLiteral("compiler_name");
                Literal description = solution.getLiteral("compiler_description");

                EsotericLanguageCompiler compiler = new EsotericLanguageCompiler();
                compiler.setName(name.getLexicalForm());
                compiler.setDescription(description.getLexicalForm());
                if (solution.getLiteral("link") != null) {
                    compiler.setExternalLink(URI.create(solution.getLiteral("link").getString()));
                }
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
                if (solution.getLiteral("link") != null) {
                    interpreter.setExternalLink(URI.create(solution.getLiteral("link").getString()));
                }
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

    @Deprecated
    public List<EsotericLanguage> getEsotericLanguagesByCriteria(Criteria criteria) {
        List<EsotericLanguage> languages = new ArrayList<>();
        // Load an RDF model from a file (replace "data.ttl" with your RDF file)
        Model model = FileManager.getInternal().loadModelInternal(ELA_ONTOLOGY_OWL);
        SelectBuilder selectBuilder = new SelectSparqlLanguageBuilder().buildSelectForLanguages(criteria);

        // Execute the parameterized query
        log.info(selectBuilder.toString());
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
}
