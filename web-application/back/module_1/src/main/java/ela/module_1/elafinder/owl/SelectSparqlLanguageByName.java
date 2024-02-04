package ela.module_1.elafinder.owl;

import ela.module_1.elafinder.models.Criteria;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.jena.arq.querybuilder.SelectBuilder;

public class SelectSparqlLanguageByName extends AbstractSelectBuilderSparql {
    @Override
    public SelectBuilder buildSelectForLanguages(Criteria criteria) {
        throw new NotImplementedException();
    }

    public SelectBuilder buildSelectForLanguageByName(String name) {
        SelectBuilder selectBuilder = new SelectBuilder()
                .setDistinct(true)
                .addPrefix("ela", ELA_PREFIX)
                .addPrefix("rdf", RDF_PREDIX)
                .addPrefix("rdfs", RDFS_PREFIX)
                .addPrefix("owl", OWL_PREFIX)
                .addPrefix("xsd", XSD_PREFIX)
                .addVar("?language")
                .addVar("?date")
                .addVar("?language_description")
                .addVar("?language_name")
                .addVar("?diff_alias")
                .addVar("?external_link")
                .addWhere("?language", "a", "ela:Language")
                .addWhere("?language", "ela:hasDifficulty", "?language_difficulty")
                .addWhere("?language_difficulty", "ela:DifficultyAlias", "?diff_alias")
                .addFilter("(?language = <" + ELA_PREFIX + name + ">)")
                .addOptional("?language", "ela:LanguageName", "?language_name")
                .addOptional("?language", "ela:LanguageDescription", "?language_description")
                .addOptional("?language", "ela:ExternalLink", "?external_link")
                .addOptional("?language", "ela:DateOfCreation", "?date");

        return selectBuilder;
    }
}
