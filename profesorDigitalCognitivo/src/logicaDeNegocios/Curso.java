package logicaDeNegocios;
//Hola guapos
import java.sql.Date;
import java.util.ArrayList;

import logicaDeNegocios.dao.DaoCurso;
import logicaDeNegocios.dao.DaoEvaluacion;
import logicaDeNegocios.dao.DaoProfesor;
import logicaDeNegocios.dto.DtoCurso;
import logicaDeNegocios.dto.DtoEvaluacion;
import logicaDeNegocios.dto.DtoTema;
import logicaDeNegocios.factory.FabricaEvaluacionFormativa;
import logicaDeNegocios.factory.FabricaEvaluacionSumativa;
import logicaDeNegocios.factory.FabricaTema;

public class Curso {
	private String codigo;
	private String descripcion;
	private Profesor profesor;
	private ArrayList<Tema> temas;
	private ArrayList<Estudiante> estudiantes;
	private ArrayList<Evaluacion> evaluaciones;
	private ArrayList<Bitacora> bitacoras;

	//************************************** CONSTRUCTOR *****************************************
	public Curso(String pCodigo, String pDescripcion){
		setCodigo(pCodigo);
		setDescripcion(pDescripcion);
		temas= new ArrayList<Tema>();
		estudiantes= new ArrayList<Estudiante>();
		evaluaciones = new ArrayList<Evaluacion>();
		
	}
	
	//************************************** GETTERS & SETTERS *****************************************
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String pCodigo) {
		this.codigo = pCodigo;
		actualizarBitacoras();
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String pDescripcion) {
		this.descripcion = pDescripcion;
		actualizarBitacoras();
	}
	public ArrayList<Tema> getTemas() {
		return temas;
	}
	public void setTemas(ArrayList<Tema> pTemas) {
		this.temas = pTemas;
		actualizarBitacoras();
	}
	public ArrayList<Estudiante> getEstudiantes() {
		return estudiantes;
	}
	public void setEstudiantes(ArrayList<Estudiante> pEstudiantes) {
		this.estudiantes = pEstudiantes;
		actualizarBitacoras();
	}
	public ArrayList<Evaluacion> getEvaluaciones() {
		return evaluaciones;
	}
	public void setEvaluaciones(ArrayList<Evaluacion> pEvaluaciones) {
		this.evaluaciones = pEvaluaciones;
		actualizarBitacoras();
	}
	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
		actualizarBitacoras();
	}

	public ArrayList<Bitacora> getBitacoras() {
		return bitacoras;
	}

	public void setBitacoras(ArrayList<Bitacora> bitacoras) {
		this.bitacoras = bitacoras;
		actualizarBitacoras();
	}

	//************************************** OTROS METODOS *****************************************
	public void eliminarCurso(String idCurso){
		DaoCurso borrar=new DaoCurso();
		borrar.eliminarCurso(idCurso);
	}
	
	public void almacenarCurso(Curso pCurso){
		DaoCurso alamacenar=new DaoCurso();
		alamacenar.crearCurso(pCurso);
	}
	
	public void asignarProfesor(String correoProfesor,String codigoCurso){
		DaoCurso registrarProfesor =new DaoCurso();
		registrarProfesor.registrarProfesor(correoProfesor, codigoCurso);
	}
	
	public void actualizarCurso(DtoCurso dtoCurso, String cursoOriginal){
		DaoCurso actualizar=new DaoCurso();
		actualizar.actualizarCurso(dtoCurso, cursoOriginal);
	}
	
	public void agregarEvaluacion(Evaluacion evaluacion){
		DaoEvaluacion evaluaciones= new DaoEvaluacion();
		
		String porcentaje = Double.toString(evaluacion.getPorcentajeCurso());
		String puntaje = Double.toString(evaluacion.getPuntajeTotal());
		String minutos = Integer.toString(evaluacion.getMinutosDisponibles());

		evaluaciones.crearEvaluacion(evaluacion.getNombreEvaluacion(), puntaje, porcentaje, evaluacion.getHoraFecha(), minutos,evaluacion.getTipo(), this.getCodigo());
	}
	
	public void agregarTema(Tema tema){
		temas.add(tema);
	}
	
	private void actualizarBitacoras(){
		DtoCurso dtoCurso=new DtoCurso();
		dtoCurso.setCodigo(this.codigo);
		dtoCurso.setDescripcion(this.descripcion);
		dtoCurso.setEstudiantes(this.estudiantes);
		dtoCurso.setProfesor(this.profesor);
		dtoCurso.setTemas(this.temas);
		dtoCurso.setEvaluaciones(this.evaluaciones);
	}
	
	public void vincularBitacora(Bitacora pBitacora){
		bitacoras.add(pBitacora);
	}
}
