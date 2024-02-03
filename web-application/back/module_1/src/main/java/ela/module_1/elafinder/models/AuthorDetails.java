package ela.module_1.elafinder.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import org.threeten.bp.OffsetDateTime;

import java.util.Objects;

/**
 * AuthorDetails
 */
@Validated

public class AuthorDetails {
    @JsonProperty("name")
    private String name = null;

    @JsonProperty("nationality")
    private String nationality = null;

    @JsonProperty("birthDate")
    private OffsetDateTime birthDate = null;

    @JsonProperty("deathDate")
    private OffsetDateTime deathDate = null;

    public AuthorDetails name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get name
     *
     * @return name
     **/
    @Schema(example = "Foo Daniels", description = "")

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AuthorDetails nationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    /**
     * Get nationality
     *
     * @return nationality
     **/
    @Schema(example = "de", description = "")

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public AuthorDetails birthDate(OffsetDateTime birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    /**
     * Get birthDate
     *
     * @return birthDate
     **/
    @Schema(example = "1945-11-23T00:00Z", description = "")

    public OffsetDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(OffsetDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public AuthorDetails deathDate(OffsetDateTime deathDate) {
        this.deathDate = deathDate;
        return this;
    }

    /**
     * Get deathDate
     *
     * @return deathDate
     **/
    @Schema(example = "1987-04-21T00:00Z", description = "")

    public OffsetDateTime getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(OffsetDateTime deathDate) {
        this.deathDate = deathDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AuthorDetails authorDetails = (AuthorDetails) o;
        return Objects.equals(this.name, authorDetails.name) &&
                Objects.equals(this.nationality, authorDetails.nationality) &&
                Objects.equals(this.birthDate, authorDetails.birthDate) &&
                Objects.equals(this.deathDate, authorDetails.deathDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, nationality, birthDate, deathDate);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AuthorDetails {\n");

        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    nationality: ").append(toIndentedString(nationality)).append("\n");
        sb.append("    birthDate: ").append(toIndentedString(birthDate)).append("\n");
        sb.append("    deathDate: ").append(toIndentedString(deathDate)).append("\n");
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
