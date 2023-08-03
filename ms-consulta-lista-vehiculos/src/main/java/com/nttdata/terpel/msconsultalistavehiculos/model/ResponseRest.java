package com.nttdata.terpel.msconsultalistavehiculos.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

@JsonAutoDetect
@JsonSerialize
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseRest {

    @JsonProperty(value = "code")
	@JsonPropertyDescription(value = "Código de respuesta que se presentó en el servicio ")
	private String code;

	@JsonProperty(value = "message")
	@JsonPropertyDescription(value = "Descripción del mensaje de exito/error")
	private String message;

	@JsonProperty(value = "data")
	private String data;
	
    @JsonProperty("meta")
    private MetaInformacion meta;
  
}

