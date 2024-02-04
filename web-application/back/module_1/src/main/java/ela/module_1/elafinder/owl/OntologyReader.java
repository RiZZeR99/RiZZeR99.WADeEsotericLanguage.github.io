package ela.module_1.elafinder.owl;

import ela.module_1.elafinder.models.*;
import ela.module_1.elafinder.utils.ResourceFileReader;
import org.apache.jena.arq.querybuilder.SelectBuilder;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.FileManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class OntologyReader {
    private static final String ontologyPath = "ela-ontology.owl";
    private final Properties prefixes;
    private final String rdfPrefx;
    private final String owlPrefix;
    private final String rdfsPrefix;
    private final String xsdPrefix;
    private final String elaPrefix;

    public OntologyReader() {
        prefixes = new Properties();
        try {
            prefixes.load(ResourceFileReader.getFileInputStreamReader("static/prefixes.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        rdfPrefx = (String) prefixes.get("rdf");
        rdfsPrefix = (String) prefixes.get("rdfs");
        owlPrefix = (String) prefixes.get("owl");
        xsdPrefix = (String) prefixes.get("xsd");
        elaPrefix = (String) prefixes.get("ela");
    }

    public List<EsotericLanguage> getEsotericLanguagesByCriteria(Criteria criteria) {
        List<EsotericLanguage> languages = new ArrayList<>();
        // Load an RDF model from a file (replace "data.ttl" with your RDF file)
        Model model = FileManager.getInternal().loadModelInternal("ela-ontology.owl");

        SelectBuilder selectBuilder = buildSelectForLanguage(criteria);

        // Execute the parameterized query
        try (QueryExecution qexec = QueryExecutionFactory.create(selectBuilder.build(), model)) {
            ResultSet results = qexec.execSelect();

            // Process the results
            while (results.hasNext()) {
                QuerySolution solution = results.nextSolution();
//                Resource name = solution.getResource("language");
                RDFNode language = solution.get("language");
                RDFNode languageName = solution.get("language_name");
                if (languageName.isLiteral()) {
                    System.out.println(languageName);
                }
                languages.add((buildEsotericLanguage(solution)));
            }
        }
        return languages;
    }

    private EsotericLanguage buildEsotericLanguage(QuerySolution solution) {
        EsotericLanguage language = new EsotericLanguage();
        String languageName = solution.get("language_name").toString();
        String languageDescription = solution.get("language_description").toString();
        EsotericLanguageCompiler compiler = getCompiler(languageName);
        return language;
    }

    private SelectBuilder buildSelectForLanguage(Criteria criteria) {
        SelectBuilder selectBuilder = new SelectBuilder()
                .addPrefix("ela", "http://www.semanticweb.org/ela-ontology#")
                .addPrefix("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#")
                .addPrefix("owl", "http://www.w3.org/2002/07/owl#")
                .addPrefix("rdfs", "http://www.w3.org/2000/01/rdf-schema#")
                .addPrefix("xsd", "http://www.w3.org/2001/XMLSchema#")
                .addVar("?language")
                .addVar("?author")
                .addVar("?interpreter")
                .addVar("?compiler")
                .addVar("?date")
                .addVar("?language_description")
                .addVar("?language_name")
                .addVar("?language_difficulty")
                .addWhere("?language", "a", "ela:Language")
                .addOptional("?language", "ela:isInterpretedBy", "?interpreter")
                .addOptional("?language", "ela:isCompiledWith", "?compiler")
                .addOptional("?language", "ela:LanguageName", "?language_name")
                .addOptional("?language", "ela:LanguageDescription", "?language_description");

        if (criteria.getComplexity() != null && criteria.getComplexity().isRequired()) {
            selectBuilder.addWhere("?language", "ela:hasDifficulty", "?language_difficulty")
                    .addWhere("?language_difficulty", "ela:DifficultyAlias", "?diff_alias")
                    .addFilter(String.format("(str(?diff_alias) = \"%s\")", criteria.getComplexity().getDifficulty()));
        } else {
            selectBuilder.addOptional("?language", "ela:hasDifficulty", "?language_difficulty");
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

    private EsotericLanguageCompiler getCompiler(String name) {
        EsotericLanguageCompiler compiler = new EsotericLanguageCompiler();

        return compiler;
    }


    private List<ProgramExample> getProgramExampleForLanguage(Resource language) {
        List<ProgramExample> programs = new ArrayList<>();
// Load an RDF model from a file (replace "data.owl" with your RDF file)
        Model model = FileManager.getInternal().loadModelInternal("ela-ontology.owl");

        // Read the parameterized SPARQL query from the file
        String queryPath = "programs.sparql";
        String queryString = readQueryFromFile(queryPath);

        // Create a ParameterizedSparqlString
        ParameterizedSparqlString parameterizedQuery = new ParameterizedSparqlString(queryString);

        // Set the parameter value
        parameterizedQuery.setLiteral("language_placeholder", "ela:" + language.getLocalName());

        // Execute the parameterized query
        try (QueryExecution qexec = QueryExecutionFactory.create(parameterizedQuery.asQuery(), model)) {
            ResultSet results = qexec.execSelect();

            // Process the results
            while (results.hasNext()) {
                QuerySolution solution = results.nextSolution();
                Resource code = solution.getResource("source_code");
                Resource description = solution.getResource("description");
                RDFNode object = solution.get("object");

                ProgramExample program = new ProgramExample();
                program.setCode(code.toString());
                program.setDescription(description.toString());
                programs.add(program);

                System.out.println("Code: " + code);
                if (object.isLiteral()) {
                    Literal literal = object.asLiteral();
                    System.out.println("Object (Literal): " + literal.getLexicalForm());
                } else if (object.isResource()) {
                    Resource resource = object.asResource();
                    System.out.println("Object (Resource): " + resource.getURI());
                }
                System.out.println("--------------------");
            }
        }
        return programs;
    }

    private void test(Criteria criteria) {

        List<EsotericLanguage> languages = new ArrayList<>();
        // Load an RDF model from a file (replace "data.ttl" with your RDF file)
        Model model = FileManager.getInternal().loadModelInternal("ela-ontology.owl");

        // Read the parameterized SPARQL query from the file
        String queryPath = "templates/queries/languages.sparql";
        String queryString = readQueryFromFile(queryPath);

        // Create a ParameterizedSparqlString
        ParameterizedSparqlString parameterizedQuery = new ParameterizedSparqlString(queryString);
        SelectBuilder selectBuilder = buildSelectForLanguage(criteria);
        //todo set here parameter values
        //we can replace them as parameterized variables
        //we take from the criteria the required values and replace them
        //we need to update this structure

        // Execute the parameterized query
        try (QueryExecution qexec = QueryExecutionFactory.create(parameterizedQuery.asQuery(), model)) {
            ResultSet results = qexec.execSelect();

            // Process the results
            while (results.hasNext()) {
                QuerySolution soln = results.nextSolution();
                Resource name = soln.getResource("language_name");
            }
        }
    }

    private AuthorDetails getAuthorForLanguage(EsotericLanguage language) {
        AuthorDetails author = new AuthorDetails();
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
