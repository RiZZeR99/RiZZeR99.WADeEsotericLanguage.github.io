package ela.module_1.elafinder.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * EsotericLanguage
 */
@Validated


public class EsotericLanguage {
    @JsonProperty("name")
    private String name = null;

    @JsonProperty("compiler")
    private EsotericLanguageCompiler compiler = null;

    @JsonProperty("description")
    private String description = null;

    @JsonProperty("program_examples")
    private List<ProgramExample> examplesOfPrograms = null;

    @JsonProperty("author_details")
    private AuthorDetails authorDetails = null;

    public EsotericLanguage name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get name
     *
     * @return name
     **/
    @Schema(example = "Brainfuck", description = "")

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EsotericLanguage compiler(EsotericLanguageCompiler compiler) {
        this.compiler = compiler;
        return this;
    }

    /**
     * Get compiler
     *
     * @return compiler
     **/
    @Schema(description = "")

    public EsotericLanguageCompiler getCompiler() {
        return compiler;
    }

    public void setCompiler(EsotericLanguageCompiler compiler) {
        this.compiler = compiler;
    }

    public EsotericLanguage description(String description) {
        this.description = description;
        return this;
    }

    /**
     * Get description
     *
     * @return description
     **/
    @Schema(description = "")

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EsotericLanguage examplesOfPrograms(List<ProgramExample> examplesOfPrograms) {
        this.examplesOfPrograms = examplesOfPrograms;
        return this;
    }

    public EsotericLanguage addExamplesOfProgramsItem(ProgramExample examplesOfProgramsItem) {
        if (this.examplesOfPrograms == null) {
            this.examplesOfPrograms = new ArrayList<ProgramExample>();
        }
        this.examplesOfPrograms.add(examplesOfProgramsItem);
        return this;
    }

    /**
     * Get examplesOfPrograms
     *
     * @return examplesOfPrograms
     **/
    @Schema(example = "[\"Hello world : ( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡°(ᕦ( ͡°ヮ ͡°)ᕥ( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°) ( ͡°(ᕦ( ͡°ヮ ͡°)ᕥ( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)ᕦ( ͡°ヮ ͡°)ᕥ( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)ᕦ( ͡°ヮ ͡°)ᕥ( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)ᕦ( ͡°ヮ ͡°)ᕥ ( ͡° ͜ʖ ͡°)(∩ ͡° ͜ʖ ͡°)⊃━☆ﾟ.*(∩ ͡° ͜ʖ ͡°)⊃━☆ﾟ.*(∩ ͡° ͜ʖ ͡°)⊃━☆ﾟ.*(∩ ͡° ͜ʖ ͡°)⊃━☆ﾟ.*(> ͜ʖ<)) ͡°)ᕦ( ͡°ヮ ͡°)ᕥ( ͡° ͜ʖ ͡°)ᕦ( ͡°ヮ ͡°)ᕥ( ͡° ͜ʖ ͡°) ᕦ( ͡°ヮ ͡°)ᕥ(> ͜ʖ<)ᕦ( ͡°ヮ ͡°)ᕥᕦ( ͡°ヮ ͡°)ᕥ( ͡° ͜ʖ ͡°)( ͡°((∩ ͡° ͜ʖ ͡°)⊃━☆ﾟ.*) ͡°)(∩ ͡° ͜ʖ ͡°)⊃━☆ﾟ.*(> ͜ʖ<)) ͡°)ᕦ( ͡°ヮ ͡°)ᕥᕦ( ͡°ヮ ͡°)ᕥ(♥ ͜ʖ♥) ᕦ( ͡°ヮ ͡°)ᕥ(> ͜ʖ<)(> ͜ʖ<)(> ͜ʖ<)(♥ ͜ʖ♥)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)(♥ ͜ʖ♥)(♥ ͜ʖ♥)( ͡° ͜ʖ ͡°) ( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)(♥ ͜ʖ♥)ᕦ( ͡°ヮ ͡°)ᕥᕦ( ͡°ヮ ͡°)ᕥ(♥ ͜ʖ♥)(∩ ͡° ͜ʖ ͡°)⊃━☆ﾟ.*(> ͜ʖ<)(♥ ͜ʖ♥)(∩ ͡° ͜ʖ ͡°)⊃━☆ﾟ.*(♥ ͜ʖ♥)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°) (♥ ͜ʖ♥)(> ͜ʖ<)(> ͜ʖ<)(> ͜ʖ<)(> ͜ʖ<)(> ͜ʖ<)(> ͜ʖ<)(♥ ͜ʖ♥)(> ͜ʖ<)(> ͜ʖ<)(> ͜ʖ<)(> ͜ʖ<)(> ͜ʖ<)(> ͜ʖ<)(> ͜ʖ<)(> ͜ʖ<)(♥ ͜ʖ♥)ᕦ( ͡°ヮ ͡°)ᕥᕦ( ͡°ヮ ͡°)ᕥ ( ͡° ͜ʖ ͡°)(♥ ͜ʖ♥)ᕦ( ͡°ヮ ͡°)ᕥ( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)(♥ ͜ʖ♥)ಠ_ಠ\"]", description = "")

    public List<ProgramExample> getExamplesOfPrograms() {
        return examplesOfPrograms;
    }

    public void setExamplesOfPrograms(List<ProgramExample> examplesOfPrograms) {
        this.examplesOfPrograms = examplesOfPrograms;
    }

    public EsotericLanguage authorDetails(AuthorDetails authorDetails) {
        this.authorDetails = authorDetails;
        return this;
    }

    /**
     * Get authorDetails
     *
     * @return authorDetails
     **/
    @Schema(description = "")

    public AuthorDetails getAuthorDetails() {
        return authorDetails;
    }

    public void setAuthorDetails(AuthorDetails authorDetails) {
        this.authorDetails = authorDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EsotericLanguage esotericLanguage = (EsotericLanguage) o;
        return Objects.equals(this.name, esotericLanguage.name) &&
                Objects.equals(this.compiler, esotericLanguage.compiler) &&
                Objects.equals(this.description, esotericLanguage.description) &&
                Objects.equals(this.examplesOfPrograms, esotericLanguage.examplesOfPrograms) &&
                Objects.equals(this.authorDetails, esotericLanguage.authorDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, compiler, description, examplesOfPrograms, authorDetails);
    }

    @Override
    public String toString() {
        return "class EsotericLanguage {\n" +
                "    name: " + toIndentedString(name) + "\n" +
                "    compiler: " + toIndentedString(compiler) + "\n" +
                "    description: " + toIndentedString(description) + "\n" +
                "    examplesOfPrograms: " + toIndentedString(examplesOfPrograms) + "\n" +
                "    authorDetails: " + toIndentedString(authorDetails) + "\n" +
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
