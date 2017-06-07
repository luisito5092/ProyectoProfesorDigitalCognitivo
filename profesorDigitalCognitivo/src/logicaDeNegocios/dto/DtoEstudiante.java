package logicaDeNegocios.dto;

import java.io.InputStream;
import java.sql.Date;

public class DtoEstudiante{
	private String idEstudiante;
	private String numeroCarne;
	private String fechaNacimiento;
	private String primerApellido;
	private String segundoApellido;
	private String nombre;
	private String correo;
	private String telefono;
	
	//************************************** GETTERS & SETTERS *****************************************
	public String getIdEstudiante() {
		return idEstudiante;
	}
	public void setIdEstudiante(String pIdEstudiante) {
		this.idEstudiante = pIdEstudiante;
	}
	public String getNumeroCarne() {
		return numeroCarne;
	}
	public void setNumeroCarne(String pNumeroCarne) {
		this.numeroCarne = pNumeroCarne;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String pFechaNacimiento) {
		this.fechaNacimiento = pFechaNacimiento;
	}
	public String getPrimerApellido() {
		return primerApellido;
	}
	public void setPrimerApellido(String pPrimerApellido) {
		this.primerApellido = pPrimerApellido;
	}
	public String getSegundoApellido() {
		return segundoApellido;
	}
	public void setSegundoApellido(String pPegundoApellido) {
		this.segundoApellido = pPegundoApellido;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String pNombre) {
		this.nombre = pNombre;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String pTelefono) {
		this.telefono = pTelefono;
	}
	
}
