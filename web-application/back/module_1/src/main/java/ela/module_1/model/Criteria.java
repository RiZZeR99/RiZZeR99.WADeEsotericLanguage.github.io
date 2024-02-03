package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.CriteriaAuthorDetails;
import io.swagger.model.CriteriaComplexity;
import io.swagger.model.CriteriaReleaseYear;
import io.swagger.model.CriteriaWithExamples;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Object used to complete the criteria used in searching for an esoteric language
 */
@Schema(description = "Object used to complete the criteria used in searching for an esoteric language")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-02-03T18:26:33.452665149Z[GMT]")


public class Criteria   {
  @JsonProperty("complexity")
  private CriteriaComplexity complexity = null;

  @JsonProperty("release_year")
  private CriteriaReleaseYear releaseYear = null;

  @JsonProperty("author_details")
  private CriteriaAuthorDetails authorDetails = null;

  @JsonProperty("withExamples")
  private CriteriaWithExamples withExamples = null;

  public Criteria complexity(CriteriaComplexity complexity) {
    this.complexity = complexity;
    return this;
  }

  /**
   * Get complexity
   * @return complexity
   **/
  @Schema(description = "")
  
    @Valid
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
   * @return releaseYear
   **/
  @Schema(description = "")
  
    @Valid
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
   * @return authorDetails
   **/
  @Schema(description = "")
  
    @Valid
    public CriteriaAuthorDetails getAuthorDetails() {
    return authorDetails;
  }

  public void setAuthorDetails(CriteriaAuthorDetails authorDetails) {
    this.authorDetails = authorDetails;
  }

  public Criteria withExamples(CriteriaWithExamples withExamples) {
    this.withExamples = withExamples;
    return this;
  }

  /**
   * Get withExamples
   * @return withExamples
   **/
  @Schema(description = "")
  
    @Valid
    public CriteriaWithExamples getWithExamples() {
    return withExamples;
  }

  public void setWithExamples(CriteriaWithExamples withExamples) {
    this.withExamples = withExamples;
  }


  @Override
  public boolean equals(java.lang.Object o) {
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
    StringBuilder sb = new StringBuilder();
    sb.append("class Criteria {\n");
    
    sb.append("    complexity: ").append(toIndentedString(complexity)).append("\n");
    sb.append("    releaseYear: ").append(toIndentedString(releaseYear)).append("\n");
    sb.append("    authorDetails: ").append(toIndentedString(authorDetails)).append("\n");
    sb.append("    withExamples: ").append(toIndentedString(withExamples)).append("\n");
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
