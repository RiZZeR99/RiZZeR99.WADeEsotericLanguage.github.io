package ela.module_1.elafinder.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

@Validated
@Getter
@Setter
public class ProgramExample {
    @JsonProperty("code")
    private String code;

    @JsonProperty("description")
    private String description;

    @Override
    public String toString() {
        return "ProgramExample{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProgramExample that = (ProgramExample) o;
        return Objects.equals(code, that.code) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, description);
    }
}
