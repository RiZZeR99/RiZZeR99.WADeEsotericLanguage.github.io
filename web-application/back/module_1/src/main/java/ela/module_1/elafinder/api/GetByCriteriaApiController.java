package ela.module_1.elafinder.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import ela.module_1.elafinder.models.Criteria;
import ela.module_1.elafinder.models.EsotericLanguage;
import ela.module_1.elafinder.owl.OntologyReader;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
public class GetByCriteriaApiController implements GetByCriteriaApi {

    private static final Logger log = LoggerFactory.getLogger(GetByCriteriaApiController.class);

    private  ObjectMapper objectMapper;

//    private  HttpServletRequest request;

//    @org.springframework.beans.factory.annotation.Autowired
//    public GetByCriteriaApiController(ObjectMapper objectMapper, HttpServletRequest request) {
//        this.objectMapper = objectMapper;
//        this.request = request;
//    }

    @Override
    public ResponseEntity<List<EsotericLanguage>> getByCriteria(@Parameter(in = ParameterIn.DEFAULT, description = "Entity with criteria completed by the user", required = true, schema = @Schema()) @RequestBody Criteria body
    ) {
        OntologyReader reader = new OntologyReader();
        reader.getEsotericLanguagesByCriteria(null);
//        String accept = request.getHeader("Accept");
//        if (accept != null && accept.contains("application/json")) {
//            return exampleOfCriteria(request);
//        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

//    private ResponseEntity<List<EsotericLanguage>> exampleOfCriteria(HttpServletRequest requestBody) {
//        try {
//            return new ResponseEntity<List<EsotericLanguage>>(objectMapper.readValue("[ {\n  \"examplesOfPrograms\" : [ \"Hello world : ( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡°(ᕦ( ͡°ヮ ͡°)ᕥ( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°) ( ͡°(ᕦ( ͡°ヮ ͡°)ᕥ( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)ᕦ( ͡°ヮ ͡°)ᕥ( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)ᕦ( ͡°ヮ ͡°)ᕥ( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)ᕦ( ͡°ヮ ͡°)ᕥ ( ͡° ͜ʖ ͡°)(∩ ͡° ͜ʖ ͡°)⊃━☆ﾟ.*(∩ ͡° ͜ʖ ͡°)⊃━☆ﾟ.*(∩ ͡° ͜ʖ ͡°)⊃━☆ﾟ.*(∩ ͡° ͜ʖ ͡°)⊃━☆ﾟ.*(> ͜ʖ<)) ͡°)ᕦ( ͡°ヮ ͡°)ᕥ( ͡° ͜ʖ ͡°)ᕦ( ͡°ヮ ͡°)ᕥ( ͡° ͜ʖ ͡°) ᕦ( ͡°ヮ ͡°)ᕥ(> ͜ʖ<)ᕦ( ͡°ヮ ͡°)ᕥᕦ( ͡°ヮ ͡°)ᕥ( ͡° ͜ʖ ͡°)( ͡°((∩ ͡° ͜ʖ ͡°)⊃━☆ﾟ.*) ͡°)(∩ ͡° ͜ʖ ͡°)⊃━☆ﾟ.*(> ͜ʖ<)) ͡°)ᕦ( ͡°ヮ ͡°)ᕥᕦ( ͡°ヮ ͡°)ᕥ(♥ ͜ʖ♥) ᕦ( ͡°ヮ ͡°)ᕥ(> ͜ʖ<)(> ͜ʖ<)(> ͜ʖ<)(♥ ͜ʖ♥)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)(♥ ͜ʖ♥)(♥ ͜ʖ♥)( ͡° ͜ʖ ͡°) ( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)(♥ ͜ʖ♥)ᕦ( ͡°ヮ ͡°)ᕥᕦ( ͡°ヮ ͡°)ᕥ(♥ ͜ʖ♥)(∩ ͡° ͜ʖ ͡°)⊃━☆ﾟ.*(> ͜ʖ<)(♥ ͜ʖ♥)(∩ ͡° ͜ʖ ͡°)⊃━☆ﾟ.*(♥ ͜ʖ♥)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°) (♥ ͜ʖ♥)(> ͜ʖ<)(> ͜ʖ<)(> ͜ʖ<)(> ͜ʖ<)(> ͜ʖ<)(> ͜ʖ<)(♥ ͜ʖ♥)(> ͜ʖ<)(> ͜ʖ<)(> ͜ʖ<)(> ͜ʖ<)(> ͜ʖ<)(> ͜ʖ<)(> ͜ʖ<)(> ͜ʖ<)(♥ ͜ʖ♥)ᕦ( ͡°ヮ ͡°)ᕥᕦ( ͡°ヮ ͡°)ᕥ ( ͡° ͜ʖ ͡°)(♥ ͜ʖ♥)ᕦ( ͡°ヮ ͡°)ᕥ( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)(♥ ͜ʖ♥)ಠ_ಠ\" ],\n  \"author_details\" : {\n    \"nationality\" : \"de\",\n    \"name\" : \"Foo Daniels\",\n    \"deathDate\" : \"1987-04-21T00:00:00Z\",\n    \"birthDate\" : \"1945-11-23T00:00:00Z\"\n  },\n  \"name\" : \"Brainfuck\",\n  \"description\" : \"description\",\n  \"compiler\" : {\n    \"name\" : \"name\",\n    \"release_year\" : 0\n  },\n  \"notableApplications\" : [ \"ELUnix\", \"Vindovs\", \"MariDB\" ]\n}, {\n  \"examplesOfPrograms\" : [ \"Hello world : ( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡°(ᕦ( ͡°ヮ ͡°)ᕥ( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°) ( ͡°(ᕦ( ͡°ヮ ͡°)ᕥ( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)ᕦ( ͡°ヮ ͡°)ᕥ( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)ᕦ( ͡°ヮ ͡°)ᕥ( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)ᕦ( ͡°ヮ ͡°)ᕥ ( ͡° ͜ʖ ͡°)(∩ ͡° ͜ʖ ͡°)⊃━☆ﾟ.*(∩ ͡° ͜ʖ ͡°)⊃━☆ﾟ.*(∩ ͡° ͜ʖ ͡°)⊃━☆ﾟ.*(∩ ͡° ͜ʖ ͡°)⊃━☆ﾟ.*(> ͜ʖ<)) ͡°)ᕦ( ͡°ヮ ͡°)ᕥ( ͡° ͜ʖ ͡°)ᕦ( ͡°ヮ ͡°)ᕥ( ͡° ͜ʖ ͡°) ᕦ( ͡°ヮ ͡°)ᕥ(> ͜ʖ<)ᕦ( ͡°ヮ ͡°)ᕥᕦ( ͡°ヮ ͡°)ᕥ( ͡° ͜ʖ ͡°)( ͡°((∩ ͡° ͜ʖ ͡°)⊃━☆ﾟ.*) ͡°)(∩ ͡° ͜ʖ ͡°)⊃━☆ﾟ.*(> ͜ʖ<)) ͡°)ᕦ( ͡°ヮ ͡°)ᕥᕦ( ͡°ヮ ͡°)ᕥ(♥ ͜ʖ♥) ᕦ( ͡°ヮ ͡°)ᕥ(> ͜ʖ<)(> ͜ʖ<)(> ͜ʖ<)(♥ ͜ʖ♥)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)(♥ ͜ʖ♥)(♥ ͜ʖ♥)( ͡° ͜ʖ ͡°) ( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)(♥ ͜ʖ♥)ᕦ( ͡°ヮ ͡°)ᕥᕦ( ͡°ヮ ͡°)ᕥ(♥ ͜ʖ♥)(∩ ͡° ͜ʖ ͡°)⊃━☆ﾟ.*(> ͜ʖ<)(♥ ͜ʖ♥)(∩ ͡° ͜ʖ ͡°)⊃━☆ﾟ.*(♥ ͜ʖ♥)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°) (♥ ͜ʖ♥)(> ͜ʖ<)(> ͜ʖ<)(> ͜ʖ<)(> ͜ʖ<)(> ͜ʖ<)(> ͜ʖ<)(♥ ͜ʖ♥)(> ͜ʖ<)(> ͜ʖ<)(> ͜ʖ<)(> ͜ʖ<)(> ͜ʖ<)(> ͜ʖ<)(> ͜ʖ<)(> ͜ʖ<)(♥ ͜ʖ♥)ᕦ( ͡°ヮ ͡°)ᕥᕦ( ͡°ヮ ͡°)ᕥ ( ͡° ͜ʖ ͡°)(♥ ͜ʖ♥)ᕦ( ͡°ヮ ͡°)ᕥ( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)(♥ ͜ʖ♥)ಠ_ಠ\" ],\n  \"author_details\" : {\n    \"nationality\" : \"de\",\n    \"name\" : \"Foo Daniels\",\n    \"deathDate\" : \"1987-04-21T00:00:00Z\",\n    \"birthDate\" : \"1945-11-23T00:00:00Z\"\n  },\n  \"name\" : \"Brainfuck\",\n  \"description\" : \"description\",\n  \"compiler\" : {\n    \"name\" : \"name\",\n    \"release_year\" : 0\n  },\n  \"notableApplications\" : [ \"ELUnix\", \"Vindovs\", \"MariDB\" ]\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
//        } catch (IOException e) {
//            log.error("Couldn't serialize response for content type application/json", e);
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
