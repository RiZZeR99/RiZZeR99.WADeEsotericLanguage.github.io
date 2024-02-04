package ela.module_1.elafinder.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CriteriaAuthorDetails
 */
@Validated


public class CriteriaAuthorDetails {
    @JsonProperty("author_details")
    private AuthorDetails data = null;

    @JsonProperty("required")
    private Boolean required = null;

    public CriteriaAuthorDetails data(AuthorDetails data) {
        this.data = data;
        return this;
    }

    /**
     * Get data
     *
     * @return data
     **/
    @Schema(description = "")

    public AuthorDetails getData() {
        return data;
    }

    public void setData(AuthorDetails data) {
        this.data = data;
    }

    public CriteriaAuthorDetails required(Boolean required) {
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
        CriteriaAuthorDetails criteriaAuthorDetails = (CriteriaAuthorDetails) o;
        return Objects.equals(this.data, criteriaAuthorDetails.data) &&
                Objects.equals(this.required, criteriaAuthorDetails.required);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, required);
    }

    @Override
    public String toString() {

        String sb = "class CriteriaAuthorDetails {\n" +
                "    data: " + toIndentedString(data) + "\n" +
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

    public boolean validAuthorName() {
        return data.getName() != null && !data.getName().isBlank();
    }
}
