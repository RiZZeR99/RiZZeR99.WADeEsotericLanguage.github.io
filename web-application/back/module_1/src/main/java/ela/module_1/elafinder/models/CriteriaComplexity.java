package ela.module_1.elafinder.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CriteriaComplexity
 */
@Validated


public class CriteriaComplexity {
    @JsonProperty("difficulty")
    private DifficultyEnum difficulty = null;
    @JsonProperty("required")
    private Boolean required = null;

    public CriteriaComplexity difficulty(DifficultyEnum difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    /**
     * Get difficulty
     *
     * @return difficulty
     **/
    @Schema(example = "medium", description = "")

    public DifficultyEnum getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(DifficultyEnum difficulty) {
        this.difficulty = difficulty;
    }

    public CriteriaComplexity required(Boolean required) {
        this.required = required;
        return this;
    }

    /**
     * Get required
     *
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
        CriteriaComplexity criteriaComplexity = (CriteriaComplexity) o;
        return Objects.equals(this.difficulty, criteriaComplexity.difficulty) &&
                Objects.equals(this.required, criteriaComplexity.required);
    }

    @Override
    public int hashCode() {
        return Objects.hash(difficulty, required);
    }

    @Override
    public String toString() {

      String sb = "class CriteriaComplexity {\n" +
              "    difficulty: " + toIndentedString(difficulty) + "\n" +
              "    required: " + toIndentedString(required) + "\n" +
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

    /**
     * Gets or Sets difficulty
     */
    public enum DifficultyEnum {
        EASY("easy"),

        MEDIUM("moderate"),

        HARD("hard"),

        NO_CHANCE("no_chance");

        private final String value;

        DifficultyEnum(String value) {
            this.value = value;
        }

        @JsonCreator
        public static DifficultyEnum fromValue(String text) {
            for (DifficultyEnum b : DifficultyEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }

        @Override
        @JsonValue
        public String toString() {
            return String.valueOf(value);
        }
    }
}
