package com.nttdata.terpel.msconsultalistavehiculos.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize
public class DatosVehiculo extends ListarVehiculoCliente{

    @JsonProperty("marca")
    String marca;

    @JsonProperty("linea")
    String linea;

    @JsonProperty("anio")
    String anio;

    @JsonProperty("tipoMotor")
    String tipoMotor;
}
