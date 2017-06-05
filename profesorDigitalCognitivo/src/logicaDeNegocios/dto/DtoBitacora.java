package logicaDeNegocios.dto;

import logicaDeNegocios.Curso;

public class DtoBitacora {
	private String correoProfesor;
	private String codigoCurso;
	private String descripcion;
	private String fecha;
	private Curso curso;
	private String tipo;
	
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
	public String getCodigoCurso() {
		return codigoCurso;
	}
	public void setCodigoCurso(String codigoCurso) {
		this.codigoCurso = codigoCurso;
	}
	
	
}
