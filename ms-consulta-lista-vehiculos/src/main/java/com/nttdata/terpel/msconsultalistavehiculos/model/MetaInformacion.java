package com.nttdata.terpel.msconsultalistavehiculos.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;


@JsonAutoDetect
@JsonSerialize
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MetaInformacion {

    private static final long serialVersionUID = 6872117570224011584L;
    
    @JsonProperty("requestTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private String requestTime;
    
    @JsonProperty(value = "apiVersion")
    private String apiVersion;

}

