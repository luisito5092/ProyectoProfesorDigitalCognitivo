package logicaDeNegocios.dto;

public class DtoRespuestaDada {
	private String isCorrecto;
	private String pregunta;
	private String tipoPregunta;
	private String tema;
	private String subtema;
	private String respuesta;
	private String evaluacion;
	private String codigoCurso;
	private String parteEvaluacionNombre;
	private String parteEvaluacionCodigo;
	private String idEstudiante;
	
	
	public String isCorrecto() {
		return isCorrecto;
	}
	public void setCorrecto(String isCorrecto) {
		this.isCorrecto = isCorrecto;
	}
	public String getPregunta() {
		return pregunta;
	}
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	public String getTipoPregunta() {
		return tipoPregunta;
	}
	public void setTipoPregunta(String tipoPregunta) {
		this.tipoPregunta = tipoPregunta;
	}
	public String getTema() {
		return tema;
	}
	public void setTema(String tema) {
		this.tema = tema;
	}
	public String getSubtema() {
		return subtema;
	}
	public void setSubtema(String subtema) {
		this.subtema = subtema;
	}
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	public String getEvaluacion() {
		return evaluacion;
	}
	public void setEvaluacion(String evaluacion) {
		this.evaluacion = evaluacion;
	}
	public String getCodigoCurso() {
		return codigoCurso;
	}
	public void setCodigoCurso(String codigoCurso) {
		this.codigoCurso = codigoCurso;
	}
	public String getParteEvaluacionNombre() {
		return parteEvaluacionNombre;
	}
	public void setParteEvaluacionNombre(String parteEvaluacionNombre) {
		this.parteEvaluacionNombre = parteEvaluacionNombre;
	}
	public String getParteEvaluacionCodigo() {
		return parteEvaluacionCodigo;
	}
	public void setParteEvaluacionCodigo(String parteEvaluacionCodigo) {
		this.parteEvaluacionCodigo = parteEvaluacionCodigo;
	}
	public String getIdEstudiante() {
		return idEstudiante;
	}
	public void setIdEstudiante(String idEstudiante) {
		this.idEstudiante = idEstudiante;
	}
	
	
}
