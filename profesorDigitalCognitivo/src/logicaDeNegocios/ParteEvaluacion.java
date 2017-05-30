package logicaDeNegocios;
import java.util.ArrayList;

import logicaDeNegocios.dao.DaoEvaluacion;
import logicaDeNegocios.dao.DaoPregunta;

public class ParteEvaluacion {
	private String lala;
	private String tipoParte;
	private int puntajeAsignado;
	private ArrayList<Pregunta> preguntas;
	private double puntajePorPregunta;
	
	
	//************************************** CONSTRUCTOR *****************************************
	public ParteEvaluacion(String pTipo, int pPuntajeAsignado){
		setTipoParte(pTipo);
		setPuntajeAsignado(pPuntajeAsignado);
		
		
	}
	//************************************** GETTERS & SETTERS *****************************************
	public String getTipoParte() {
		return tipoParte;
	}
	public void setTipoParte(String tipoParte) {
		this.tipoParte = tipoParte;
	}
	public int getPuntajeAsignado() {
		return puntajeAsignado;
	}
	public void setPuntajeAsignado(int puntajeAsignado) {
		this.puntajeAsignado = puntajeAsignado;
	}
	public ArrayList<Pregunta> getPreguntas() {
		return preguntas;
	}
	public void setPreguntas(ArrayList<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}
	public double getPuntajePorPregunta() {
		return puntajePorPregunta;
	}
	public void setPuntajePorPregunta() {
		this.puntajePorPregunta = getPuntajeAsignado()/ getPreguntas().size();
	}
	
	//************************************** OTROS METODOS *****************************************
	public void agregarPreguntas(Pregunta pPregunta){
		preguntas.add(pPregunta);
	}
	
	
	
}
