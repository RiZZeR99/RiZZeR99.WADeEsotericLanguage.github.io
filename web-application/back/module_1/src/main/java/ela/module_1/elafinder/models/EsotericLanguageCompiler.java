package ela.module_1.elafinder.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * EsotericLanguageCompiler
 */
@Validated


public class EsotericLanguageCompiler {
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
     *
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
     *
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
    public boolean equals(Object o) {
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

        String sb = "class EsotericLanguageCompiler {\n" +
                "    name: " + toIndentedString(name) + "\n" +
                "    releaseYear: " + toIndentedString(releaseYear) + "\n" +
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
