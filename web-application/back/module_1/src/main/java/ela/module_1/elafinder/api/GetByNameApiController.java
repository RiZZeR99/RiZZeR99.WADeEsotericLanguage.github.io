package ela.module_1.elafinder.api;

import ela.module_1.elafinder.models.EsotericLanguage;
import ela.module_1.elafinder.owl.OntologyReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin
public class GetByNameApiController implements GetByNameApi {

    private static final Logger log = LoggerFactory.getLogger(GetByNameApiController.class);

    @Override
    public ResponseEntity<EsotericLanguage> getByCriteria(String name) {
        log.info("Requested name: " + name);
        OntologyReader reader = new OntologyReader();
        return ResponseEntity.of(Optional.ofNullable(reader.getEsotericLanguageByName(name)));
    }
}
