package ela.module_1.elafinder.owl;

import ela.module_1.elafinder.models.Criteria;
import org.apache.jena.arq.querybuilder.SelectBuilder;

public class SelectSparqlLanguageSummaryBuilder extends AbstractSelectBuilderSparql {

    @Override
    public SelectBuilder buildSelectForLanguages(Criteria criteria) {

        SelectBuilder selectBuilder = new SelectBuilder()
                .setDistinct(true)
                .addPrefix("ela", ELA_PREFIX)
                .addPrefix("rdf", RDF_PREDIX)
                .addPrefix("rdfs", RDFS_PREFIX)
                .addPrefix("owl", OWL_PREFIX)
                .addPrefix("xsd", XSD_PREFIX)
                .addVar("?language")
                .addVar("?language_name")
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
            if (criteria.getAuthorDetails().validAuthorName()) {
                selectBuilder.addWhere("?language", "ela:isCreatedBy", "?author")
                        .addWhere("?author", "ela:PersonName", "?author_name")
                        .addFilter(String.format("(\"%s\" in str(?author_name))", criteria.getAuthorDetails().getData().getName()));
            }
        } else {
            selectBuilder.addOptional("?language", "ela:isCreatedBy", "?author");
        }

        if (criteria.getWithExamples() != null && criteria.getWithExamples().isRequired()) {
            selectBuilder.addWhere("?program", "ela:isExampleFor", "?language");
            if (!criteria.getWithExamples().isWithExamplesRequired()) {
                selectBuilder.addFilter("(STRLEN(?program) = 0)");
            }
        }

        return selectBuilder;
    }
}
