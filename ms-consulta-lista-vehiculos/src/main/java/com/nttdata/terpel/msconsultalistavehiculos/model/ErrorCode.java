package com.nttdata.terpel.msconsultalistavehiculos.model;

public class ErrorCode {
	
	private ErrorCode() {}
	
	public static final String NULL_VOID_VALUE = "Atributo requerido.";
	public static final String NULL_OBJECT = "Objeto requerido.";
	public static final String WRONG_FORMAT = "Formato incorrecto.";
	public static final String MAX_SIZE = "El valor supera la cantidad de caracteres permitidos.";
	public static final String WRONG_OPTION = "La acción es incorrecta. Se debe ingresar I (Insert) para instertar, U (Update) para actualizar o D (Delete) para eliminar.";

}

