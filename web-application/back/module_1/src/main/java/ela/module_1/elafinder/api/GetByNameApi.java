package ela.module_1.elafinder.api;

import ela.module_1.elafinder.models.EsotericLanguage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface GetByNameApi {
    @Operation(summary = "Get a esoteric languages based on the given local name", description = "Based on the local name, the application will return the language resource", tags = {"ela"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = EsotericLanguage.class)))),

            @ApiResponse(responseCode = "404", description = "No esoteric language found based on the criteria"),

            @ApiResponse(responseCode = "500", description = "Server error")})
    @RequestMapping(value = "/getByName/{name}",
            method = RequestMethod.GET)
    ResponseEntity<EsotericLanguage> getByCriteria(@Parameter(in = ParameterIn.DEFAULT, description = "Name of the language", required = true, schema = @Schema()) @PathVariable String name
    );

}
