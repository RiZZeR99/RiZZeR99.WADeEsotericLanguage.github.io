package ela.module_1.elafinder.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

@Validated
public class EsotericLanguageSummary {
    @JsonProperty("name")
    private String name = null;

    @JsonProperty("resource_uri")
    private String resourceUri = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResourceUri() {
        return resourceUri;
    }

    public void setResourceUri(String resourceUri) {
        this.resourceUri = resourceUri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EsotericLanguageSummary that = (EsotericLanguageSummary) o;
        return Objects.equals(name, that.name) && Objects.equals(resourceUri, that.resourceUri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, resourceUri);
    }

    @Override
    public String toString() {
        return "EsotericLanguageSummary{" +
                "name='" + name + '\'' +
                ", resourceUri='" + resourceUri + '\'' +
                '}';
    }
}
