package logicaDeNegocios.dto;

import java.sql.Date;
import java.util.ArrayList;

import logicaDeNegocios.Estudiante;
import logicaDeNegocios.Evaluacion;
import logicaDeNegocios.Profesor;
import logicaDeNegocios.Tema;

public class DtoCurso {
	private String codigo;
	private String descripcion;
	private Profesor profesor;
	private ArrayList<Tema> temas;
	private ArrayList<Estudiante> estudiantes;
	private ArrayList<Evaluacion> evaluaciones;
	private String idProfesor;
	private String nombreProfesor;
		
	//************************************** GETTERS & SETTERS *****************************************
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String pCodigo) {
		this.codigo = pCodigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String pDescripcion) {
		this.descripcion = pDescripcion;
	}
	public ArrayList<Tema> getTemas() {
		return temas;
	}
	public void setTemas(ArrayList<Tema> pTemas) {
		this.temas = pTemas;
	}
	public ArrayList<Estudiante> getEstudiantes() {
		return estudiantes;
	}
	public void setEstudiantes(ArrayList<Estudiante> pEstudiantes) {
		this.estudiantes = pEstudiantes;
	}
	public ArrayList<Evaluacion> getEvaluaciones() {
		return evaluaciones;
	}
	public void setEvaluaciones(ArrayList<Evaluacion> pEvaluaciones) {
		this.evaluaciones = pEvaluaciones;
	}
	public String getIdProfesor() {
		return idProfesor;
	}
	public void setIdProfesor(String idProfesor) {
		this.idProfesor = idProfesor;
	}
	public String getNombreProfesor() {
		return nombreProfesor;
	}
	public void setNombreProfesor(String nombreProfesor) {
		this.nombreProfesor = nombreProfesor;
	}
	public Profesor getProfesor() {
		return profesor;
	}
	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
	

}
