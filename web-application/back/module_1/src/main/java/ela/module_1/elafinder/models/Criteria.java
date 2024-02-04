package ela.module_1.elafinder.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * Object used to complete the criteria used in searching for an esoteric language
 */
@Schema(description = "Object used to complete the criteria used in searching for an esoteric language")
@Validated

public class Criteria {
    @JsonProperty("complexity")
    private CriteriaComplexity complexity = null;

    @JsonProperty("release_year")
    private CriteriaReleaseYear releaseYear = null;

    @JsonProperty("author_details")
    private CriteriaAuthorDetails authorDetails = null;

    @JsonProperty("with_program_examples")
    private CriteriaWithProgramExamples withExamples = null;

    public Criteria complexity(CriteriaComplexity complexity) {
        this.complexity = complexity;
        return this;
    }

    /**
     * Get complexity
     *
     * @return complexity
     **/
    @Schema(description = "")

    public CriteriaComplexity getComplexity() {
        return complexity;
    }

    public void setComplexity(CriteriaComplexity complexity) {
        this.complexity = complexity;
    }

    public Criteria releaseYear(CriteriaReleaseYear releaseYear) {
        this.releaseYear = releaseYear;
        return this;
    }

    /**
     * Get releaseYear
     *
     * @return releaseYear
     **/
    @Schema(description = "")

    public CriteriaReleaseYear getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(CriteriaReleaseYear releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Criteria authorDetails(CriteriaAuthorDetails authorDetails) {
        this.authorDetails = authorDetails;
        return this;
    }

    /**
     * Get authorDetails
     *
     * @return authorDetails
     **/
    @Schema(description = "")

    public CriteriaAuthorDetails getAuthorDetails() {
        return authorDetails;
    }

    public void setAuthorDetails(CriteriaAuthorDetails authorDetails) {
        this.authorDetails = authorDetails;
    }

    public Criteria withExamples(CriteriaWithProgramExamples withExamples) {
        this.withExamples = withExamples;
        return this;
    }

    /**
     * Get withExamples
     *
     * @return withExamples
     **/
    @Schema(description = "")

    public CriteriaWithProgramExamples getWithExamples() {
        return withExamples;
    }

    public void setWithExamples(CriteriaWithProgramExamples withExamples) {
        this.withExamples = withExamples;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Criteria criteria = (Criteria) o;
        return Objects.equals(this.complexity, criteria.complexity) &&
                Objects.equals(this.releaseYear, criteria.releaseYear) &&
                Objects.equals(this.authorDetails, criteria.authorDetails) &&
                Objects.equals(this.withExamples, criteria.withExamples);
    }

    @Override
    public int hashCode() {
        return Objects.hash(complexity, releaseYear, authorDetails, withExamples);
    }

    @Override
    public String toString() {

      String sb = "class Criteria {\n" +
              "    complexity: " + toIndentedString(complexity) + "\n" +
              "    releaseYear: " + toIndentedString(releaseYear) + "\n" +
              "    authorDetails: " + toIndentedString(authorDetails) + "\n" +
              "    withExamples: " + toIndentedString(withExamples) + "\n" +
              "}";
        return sb;
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
