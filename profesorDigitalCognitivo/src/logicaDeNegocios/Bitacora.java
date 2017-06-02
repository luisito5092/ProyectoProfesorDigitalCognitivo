package logicaDeNegocios;

import java.util.ArrayList;

import logicaDeNegocios.dto.DtoBitacora;

public class Bitacora {
	private String correoProfesor;
	private String descripcion;
	private String fecha;
	private Curso curso;
	private String tipo;
	
	//************************************** CONSTRUCTOR *****************************************
		public Bitacora(DtoBitacora dto){
			setCorreoProfesor(dto.getCorreoProfesor());
			setDescripcion(dto.getDescripcion());
			setFecha(dto.getFecha());
			setCurso(dto.getCurso());
		}
		
		//************************************** GETTERS & SETTERS *****************************************
	public String getCorreoProfesor() {
		return correoProfesor;
	}
	public void setCorreoProfesor(String correoProfesor) {
		this.correoProfesor = correoProfesor;
	}
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
	
	
	
	//************************************** OTROS METODOS *****************************************
	
	
}
