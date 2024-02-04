package ela.module_1.elafinder.api;

import ela.module_1.elafinder.models.Criteria;
import ela.module_1.elafinder.models.EsotericLanguageSummary;
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

    @Override
    public ResponseEntity<List<EsotericLanguageSummary>> getByCriteria(@Parameter(in = ParameterIn.DEFAULT, description = "Entity with criteria completed by the user", required = true, schema = @Schema()) @RequestBody Criteria body
    ) {
        log.info("Received request in getByCriteria with " + body);
        OntologyReader reader = new OntologyReader();
        return ResponseEntity.of(Optional.ofNullable(reader.getEsotericLanguageSummariesByCriteria(body)));
    }
}
