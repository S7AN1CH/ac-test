package com.lseg.search.conceptmapper.api;

import com.lseg.search.conceptmapper.CombinedConceptMapper;
import com.lseg.search.conceptmapper.api.normalizedvalues.*;
import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/queryintentdeals/api/v1/")
@Slf4j
@RequiredArgsConstructor
public class ApiController {

  private final CombinedConceptMapper combinedConceptMapper;

  private final Tracer tracer;

  @Value("${app.hostname}")
  private final String hostname;

  @PostMapping("/map")
  @Operation(summary = "Tries to find the provided user input text to a reference entity.",
          responses = {
                  @ApiResponse(responseCode = "200",
                          description = "Success is reported even if the entity was not found.",
                          content = @Content(schema = @Schema(anyOf = {
                                  DateValue.class,
                                  DateRange.class,
                                  IntegerValue.class,
                                  TextValue.class,
                                  TextArrayValue.class
                          }))
                  ),
                  @ApiResponse(
                          responseCode = "400",
                          description = "Bad request. The provided input is invalid."
                  ),
                  @ApiResponse(
                          responseCode = "500",
                          description = "This is a genuine server error.")})
  public ConceptMapperResponse map(@NotNull @Valid @RequestBody ConceptMappingRequest conceptMappingRequest) {
    log.info("Mapping called: " + conceptMappingRequest);
    addTagsToTracerContext(conceptMappingRequest);
    List<MappedConcept> mappings = combinedConceptMapper.map(conceptMappingRequest.getUserInputs());
    return ConceptMapperResponse.builder()
            .mappings(mappings)
            .hostname(hostname)
            .build();
  }

  private void addTagsToTracerContext(ConceptMappingRequest conceptMappingRequest) {
    Span currentSpan = tracer.currentSpan();
    if (currentSpan != null) {
      currentSpan.tag("inputs.count", String.valueOf(conceptMappingRequest.getUserInputs().size()));
    }
  }
}
