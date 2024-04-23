package com.lseg.search.conceptmapper.api;

import com.lseg.search.conceptmapper.api.normalizedvalues.UserInput;
import lombok.Builder;
import lombok.Value;

@Value()
@Builder
public class MappedConcept {
  NormalizedValue normalizedValue;
  UserInput userInput;
}
