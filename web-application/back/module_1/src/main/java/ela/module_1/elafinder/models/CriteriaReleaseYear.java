package ela.module_1.elafinder.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CriteriaReleaseYear
 */
@Validated


public class CriteriaReleaseYear {
    @JsonProperty("year")
    private Integer year = null;

    @JsonProperty("required")
    private Boolean required = null;

    public CriteriaReleaseYear year(Integer year) {
        this.year = year;
        return this;
    }

    /**
     * Get year
     *
     * @return year
     **/
    @Schema(example = "1976", description = "")

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public CriteriaReleaseYear required(Boolean required) {
        this.required = required;
        return this;
    }

    /**
     * Get required
     *
     * @return required
     **/
    @Schema(example = "true", description = "")

    public Boolean isRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CriteriaReleaseYear criteriaReleaseYear = (CriteriaReleaseYear) o;
        return Objects.equals(this.year, criteriaReleaseYear.year) &&
                Objects.equals(this.required, criteriaReleaseYear.required);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, required);
    }

    @Override
    public String toString() {

        String sb = "class CriteriaReleaseYear {\n" +
                "    year: " + toIndentedString(year) + "\n" +
                "    required: " + toIndentedString(required) + "\n" +
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
