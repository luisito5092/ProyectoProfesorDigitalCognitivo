package logicaDeNegocios;

import java.util.ArrayList;

import logicaDeNegocios.dto.DtoBitacora;

public abstract class Bitacora {
	protected String correoProfesor;
	protected String descripcion;
	protected String fecha;
	protected Curso curso;
	protected String tipo;
	
	//************************************** CONSTRUCTOR *****************************************
		public Bitacora(){
		}
		
		//************************************** GETTERS & SETTERS *****************************************
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getCorreoProfesor() {
		return correoProfesor;
	}

	public void setCorreoProfesor(String correoProfesor) {
		this.correoProfesor = correoProfesor;
	}
	
	
	//************************************** OTROS METODOS *****************************************

	
	public abstract String actualizarBitacora();
}
