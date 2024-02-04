package ela.module_1.elafinder.owl;

import ela.module_1.elafinder.utils.ResourceFileReader;

import java.io.IOException;
import java.util.Properties;

public abstract class AbstractSelectBuilderSparql implements SelectSparqlBuilder {
    protected final Properties prefixes;
    protected final String RDF_PREDIX;
    protected final String OWL_PREFIX;
    protected final String RDFS_PREFIX;
    protected final String XSD_PREFIX;
    protected final String ELA_PREFIX;

    public AbstractSelectBuilderSparql() {
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
}
