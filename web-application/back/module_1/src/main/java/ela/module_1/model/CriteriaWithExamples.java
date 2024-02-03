package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CriteriaWithExamples
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-02-03T18:26:33.452665149Z[GMT]")


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
  public boolean equals(java.lang.Object o) {
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
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
