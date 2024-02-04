package ela.module_1.elafinder.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import ela.module_1.elafinder.models.EsotericLanguage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetRandomApiController implements GetRandomApi {

    private static final Logger log = LoggerFactory.getLogger(GetRandomApiController.class);

    private ObjectMapper objectMapper;

//    private  HttpServletRequest request;

//    @org.springframework.beans.factory.annotation.Autowired
//    public GetRandomApiController(ObjectMapper objectMapper, HttpServletRequest request) {
//        this.objectMapper = objectMapper;
//        this.request = request;
//    }

    public ResponseEntity<EsotericLanguage> getRandom() {
//        String accept = request.getHeader("Accept");
//        if (accept != null && accept.contains("application/json")) {
//            try {
//                return new ResponseEntity<EsotericLanguage>(objectMapper.readValue("{\n  \"examplesOfPrograms\" : [ \"Hello world : ( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡°(ᕦ( ͡°ヮ ͡°)ᕥ( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°) ( ͡°(ᕦ( ͡°ヮ ͡°)ᕥ( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)ᕦ( ͡°ヮ ͡°)ᕥ( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)ᕦ( ͡°ヮ ͡°)ᕥ( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)ᕦ( ͡°ヮ ͡°)ᕥ ( ͡° ͜ʖ ͡°)(∩ ͡° ͜ʖ ͡°)⊃━☆ﾟ.*(∩ ͡° ͜ʖ ͡°)⊃━☆ﾟ.*(∩ ͡° ͜ʖ ͡°)⊃━☆ﾟ.*(∩ ͡° ͜ʖ ͡°)⊃━☆ﾟ.*(> ͜ʖ<)) ͡°)ᕦ( ͡°ヮ ͡°)ᕥ( ͡° ͜ʖ ͡°)ᕦ( ͡°ヮ ͡°)ᕥ( ͡° ͜ʖ ͡°) ᕦ( ͡°ヮ ͡°)ᕥ(> ͜ʖ<)ᕦ( ͡°ヮ ͡°)ᕥᕦ( ͡°ヮ ͡°)ᕥ( ͡° ͜ʖ ͡°)( ͡°((∩ ͡° ͜ʖ ͡°)⊃━☆ﾟ.*) ͡°)(∩ ͡° ͜ʖ ͡°)⊃━☆ﾟ.*(> ͜ʖ<)) ͡°)ᕦ( ͡°ヮ ͡°)ᕥᕦ( ͡°ヮ ͡°)ᕥ(♥ ͜ʖ♥) ᕦ( ͡°ヮ ͡°)ᕥ(> ͜ʖ<)(> ͜ʖ<)(> ͜ʖ<)(♥ ͜ʖ♥)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)(♥ ͜ʖ♥)(♥ ͜ʖ♥)( ͡° ͜ʖ ͡°) ( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)(♥ ͜ʖ♥)ᕦ( ͡°ヮ ͡°)ᕥᕦ( ͡°ヮ ͡°)ᕥ(♥ ͜ʖ♥)(∩ ͡° ͜ʖ ͡°)⊃━☆ﾟ.*(> ͜ʖ<)(♥ ͜ʖ♥)(∩ ͡° ͜ʖ ͡°)⊃━☆ﾟ.*(♥ ͜ʖ♥)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°) (♥ ͜ʖ♥)(> ͜ʖ<)(> ͜ʖ<)(> ͜ʖ<)(> ͜ʖ<)(> ͜ʖ<)(> ͜ʖ<)(♥ ͜ʖ♥)(> ͜ʖ<)(> ͜ʖ<)(> ͜ʖ<)(> ͜ʖ<)(> ͜ʖ<)(> ͜ʖ<)(> ͜ʖ<)(> ͜ʖ<)(♥ ͜ʖ♥)ᕦ( ͡°ヮ ͡°)ᕥᕦ( ͡°ヮ ͡°)ᕥ ( ͡° ͜ʖ ͡°)(♥ ͜ʖ♥)ᕦ( ͡°ヮ ͡°)ᕥ( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)(♥ ͜ʖ♥)ಠ_ಠ\" ],\n  \"author_details\" : {\n    \"nationality\" : \"de\",\n    \"name\" : \"Foo Daniels\",\n    \"deathDate\" : \"1987-04-21T00:00:00Z\",\n    \"birthDate\" : \"1945-11-23T00:00:00Z\"\n  },\n  \"name\" : \"Brainfuck\",\n  \"description\" : \"description\",\n  \"compiler\" : {\n    \"name\" : \"name\",\n    \"release_year\" : 0\n  },\n  \"notableApplications\" : [ \"ELUnix\", \"Vindovs\", \"MariDB\" ]\n}", EsotericLanguage.class), HttpStatus.NOT_IMPLEMENTED);
//            } catch (IOException e) {
//                log.error("Couldn't serialize response for content type application/json", e);
//                return new ResponseEntity<EsotericLanguage>(HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//        }

        return new ResponseEntity<EsotericLanguage>(HttpStatus.NOT_IMPLEMENTED);
    }

}
