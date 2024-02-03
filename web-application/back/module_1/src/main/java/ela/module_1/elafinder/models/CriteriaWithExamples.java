package ela.module_1.elafinder.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CriteriaWithExamples
 */
@Validated


public class CriteriaWithExamples   {
  @JsonProperty("withExamplesRequired")
  private Boolean withExamplesRequired = null;

  @JsonProperty("required")
  private Boolean required = null;

  public CriteriaWithExamples withExamplesRequired(Boolean withExamplesRequired) {
    this.withExamplesRequired = withExamplesRequired;
    return this;
  }

  /**
   * Get withExamplesRequired
   * @return withExamplesRequired
   **/
  @Schema(description = "")
  
    public Boolean isWithExamplesRequired() {
    return withExamplesRequired;
  }

  public void setWithExamplesRequired(Boolean withExamplesRequired) {
    this.withExamplesRequired = withExamplesRequired;
  }

  public CriteriaWithExamples required(Boolean required) {
    this.required = required;
    return this;
  }

  /**
   * Get required
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
    CriteriaWithExamples criteriaWithExamples = (CriteriaWithExamples) o;
    return Objects.equals(this.withExamplesRequired, criteriaWithExamples.withExamplesRequired) &&
        Objects.equals(this.required, criteriaWithExamples.required);
  }

  @Override
  public int hashCode() {
    return Objects.hash(withExamplesRequired, required);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CriteriaWithExamples {\n");
    
    sb.append("    withExamplesRequired: ").append(toIndentedString(withExamplesRequired)).append("\n");
    sb.append("    required: ").append(toIndentedString(required)).append("\n");
    sb.append("}");
    return sb.toString();
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
