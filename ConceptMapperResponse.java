package com.lseg.search.conceptmapper.api;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value()
@Builder
public class ConceptMapperResponse {
  List<MappedConcept> mappings;
  String hostname;
}
