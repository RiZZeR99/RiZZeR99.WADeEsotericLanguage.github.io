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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class GetByCriteriaApiController implements GetByCriteriaApi {

    private static final Logger log = LoggerFactory.getLogger(GetByCriteriaApiController.class);

    private ObjectMapper objectMapper;

//    private  HttpServletRequest request;

//    @org.springframework.beans.factory.annotation.Autowired
//    public GetByCriteriaApiController(ObjectMapper objectMapper, HttpServletRequest request) {
//        this.objectMapper = objectMapper;
//        this.request = request;
//    }

    @Override
    public ResponseEntity<List<EsotericLanguage>> getByCriteria(@Parameter(in = ParameterIn.DEFAULT, description = "Entity with criteria completed by the user", required = true, schema = @Schema()) @RequestBody Criteria body
    ) {
        System.out.println("Received request in getByCriteria with " + body);
        OntologyReader reader = new OntologyReader();
        return ResponseEntity.of(Optional.ofNullable(reader.getEsotericLanguagesByCriteria(body)));
//        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
