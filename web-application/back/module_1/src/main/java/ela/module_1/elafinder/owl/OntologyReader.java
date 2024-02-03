package ela.module_1.elafinder.owl;

import ela.module_1.elafinder.models.AuthorDetails;
import ela.module_1.elafinder.models.Criteria;
import ela.module_1.elafinder.models.EsotericLanguage;
import ela.module_1.elafinder.models.ProgramExample;
import ela.module_1.elafinder.utils.ResourceFileReader;
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
    private static String ontologyPath = "ela-ontology.owl";
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

        // Read the parameterized SPARQL query from the file
        String queryPath = "templates/queries/languages.sparql";
        String queryString = readQueryFromFile(queryPath);

        // Create a ParameterizedSparqlString
        ParameterizedSparqlString parameterizedQuery = new ParameterizedSparqlString(queryString);

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
        return languages;
    }

    private List<ProgramExample> getProgramExampleForLanguage(EsotericLanguage language) {
        List<ProgramExample> programs = new ArrayList<>();
// Load an RDF model from a file (replace "data.ttl" with your RDF file)
        Model model = FileManager.getInternal().loadModelInternal("ela-ontology.owl");

        // Read the parameterized SPARQL query from the file
        String queryPath = "programs.sparql";
        String queryString = readQueryFromFile(queryPath);

        // Create a ParameterizedSparqlString
        ParameterizedSparqlString parameterizedQuery = new ParameterizedSparqlString(queryString);

        // Set the parameter value
        parameterizedQuery.setLiteral("language_name_to_match", language.getName());

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
