package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * EsotericLanguageCompiler
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-02-03T18:26:33.452665149Z[GMT]")


public class EsotericLanguageCompiler   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("release_year")
  private Integer releaseYear = null;

  public EsotericLanguageCompiler name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   **/
  @Schema(description = "")
  
    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public EsotericLanguageCompiler releaseYear(Integer releaseYear) {
    this.releaseYear = releaseYear;
    return this;
  }

  /**
   * Get releaseYear
   * @return releaseYear
   **/
  @Schema(description = "")
  
    public Integer getReleaseYear() {
    return releaseYear;
  }

  public void setReleaseYear(Integer releaseYear) {
    this.releaseYear = releaseYear;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EsotericLanguageCompiler esotericLanguageCompiler = (EsotericLanguageCompiler) o;
    return Objects.equals(this.name, esotericLanguageCompiler.name) &&
        Objects.equals(this.releaseYear, esotericLanguageCompiler.releaseYear);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, releaseYear);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EsotericLanguageCompiler {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    releaseYear: ").append(toIndentedString(releaseYear)).append("\n");
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
