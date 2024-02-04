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
                Objects.equals(this.birthDate, authorDetails.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, nationality, birthDate);
    }

    @Override
    public String toString() {

        return "class AuthorDetails {\n" +
                "    name: " + toIndentedString(name) + "\n" +
                "    nationality: " + toIndentedString(nationality) + "\n" +
                "    birthDate: " + toIndentedString(birthDate) + "\n" +
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
