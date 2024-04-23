package com.lseg.search.conceptmapper.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lseg.search.conceptmapper.api.normalizedvalues.UserInput;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.List;


@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE) // For Jackson deserialization
public class ConceptMappingRequest {
  @JsonProperty("user_inputs")
  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  @NotNull @Valid
  List<UserInput> userInputs;
}
