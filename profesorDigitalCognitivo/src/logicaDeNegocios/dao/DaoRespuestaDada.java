package logicaDeNegocios.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexionMySql.ConexionSingleton;
import logicaDeNegocios.dto.DtoEvaluacionAplicada;
import logicaDeNegocios.dto.DtoRespuestaDada;

public class DaoRespuestaDada {

	Statement state;

	public void crearRespuestaDada(DtoRespuestaDada dtoRespuestaD){
		try {
			state= ConexionSingleton.conectar().createStatement(); 
			String sql="INSERT INTO respuestaDada VALUES();";
			state.executeUpdate(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	public ArrayList<DtoRespuestaDada> listarRespuestasDadas(String cursoCodigo,String evaluacion, String idEstudiante){
		ArrayList<DtoRespuestaDada> listaRespuestas=new ArrayList<DtoRespuestaDada>();
		try {
			state= ConexionSingleton.conectar().createStatement();
			
			String sql="Select preguntaEvaluacion_preguntaSubtema_descripcion,preguntaEvaluacion_preguntaSubtema_pregunta,"
					+ "respuesta,isCorrecto FROM respuestaDada WHERE evaluacionAplicada_estudiante_idEstudiante='"+idEstudiante+"' "
					+ "AND evaluacionAplicada_evaluacion_curso_codigo='"+cursoCodigo+"' AND "
					+ "evaluacionAplicada_evaluacion_nombre='"+evaluacion+"';";
		
			ResultSet rs1=state.executeQuery(sql);
			while(rs1.next()){
				DtoRespuestaDada respuestas=new DtoRespuestaDada();
				respuestas.setTipoPregunta(rs1.getString(1));
				respuestas.setPregunta(rs1.getString(2));;
				respuestas.setRespuesta(rs1.getString(3));
				if(rs1.getBoolean(4)==true){
					respuestas.setCorrecto("Correcta");
				}else{
					respuestas.setCorrecto("Incorrecta");
				}
				listaRespuestas.add(respuestas);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return listaRespuestas;
	}
}
