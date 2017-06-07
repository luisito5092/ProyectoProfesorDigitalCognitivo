package logicaDeNegocios;

import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;

public class Estudiante{
	private String idEstudiante;
	private String numeroCarne;
	private String fechaNacimiento;
	private String primerApellido;
	private String segundoApellido;
	private String nombre;
	private String correo;
	private String telefono;
	private ArrayList<EvaluacionAplicada> evaluacionesAplicadas;
	private ArrayList<Curso> cursos;
	
	//************************************** CONSTRUCTOR *****************************************
		public Estudiante(String pIdEstudiante, String pNumeroCarne, String pFechaNacimiento,
				String pPrimerApellido, String pSegundoApellido, String pNombre, String pCorreo, String pTelefono){
			setIdEstudiante(pIdEstudiante);
			setNumeroCarne(pNumeroCarne);
			setFechaNacimiento(pFechaNacimiento);
			setPrimerApellido(pPrimerApellido);
			setSegundoApellido(pSegundoApellido);
			setNombre(pNombre);
			setCorreo(pCorreo);
			setTelefono(pTelefono);
		}
		
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

	public ArrayList<EvaluacionAplicada> getEvaluacionesAplicadas() {
		return evaluacionesAplicadas;
	}

	public void setEvaluacionesAplicadas(ArrayList<EvaluacionAplicada> evaluacionesAplicadas) {
		this.evaluacionesAplicadas = evaluacionesAplicadas;
	}

	public ArrayList<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(ArrayList<Curso> cursos) {
		this.cursos = cursos;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String pTelefono) {
		this.telefono = pTelefono;
	}
	//************************************** OTROS METODOS *****************************************
	public void agregarEvaluacionAplicada(EvaluacionAplicada aplicada){
		evaluacionesAplicadas.add(aplicada);
	}
	
}
