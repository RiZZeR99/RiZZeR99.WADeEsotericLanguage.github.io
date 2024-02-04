package ela.module_1.elafinder.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import java.net.URI;
import java.util.Objects;

/**
 * EsotericLanguageCompiler
 */
@Validated


public class EsotericLanguageCompiler {
    @JsonProperty("name")
    private String name = null;

    @JsonProperty("description")
    private String description = null;

    @JsonProperty("external_link")
    private URI externalLink;

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

    @Schema(description = "")

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
                Objects.equals(this.description, esotericLanguageCompiler.description) &&
                Objects.equals(this.externalLink, esotericLanguageCompiler.externalLink);
    }

    public URI getExternalLink() {
        return externalLink;
    }

    public void setExternalLink(URI externalLink) {
        this.externalLink = externalLink;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

    @Override
    public String toString() {

        return "class EsotericLanguageCompiler {\n" +
                "    name: " + toIndentedString(name) + "\n" +
                "    description: " + toIndentedString(description) + "\n" +
                "    external link: " + toIndentedString(externalLink) + "\n" +
                "}";
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
