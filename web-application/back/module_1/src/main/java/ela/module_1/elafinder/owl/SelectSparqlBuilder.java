package ela.module_1.elafinder.owl;

import ela.module_1.elafinder.models.Criteria;
import org.apache.jena.arq.querybuilder.SelectBuilder;

public interface SelectSparqlBuilder {

    SelectBuilder buildSelectForLanguages(Criteria criteria);
}
