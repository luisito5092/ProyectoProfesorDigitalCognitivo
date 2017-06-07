package logicaDeNegocios.dao;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexionMySql.ConexionSingleton;
import logicaDeNegocios.dto.DtoCurso;
import logicaDeNegocios.dto.DtoPregunta;

public class DaoPregunta {

	Statement state;

	public void crearPregunta(String pregunta,String descripcion, String ayuda,
			String respuestaCorrecta,String descripcionSubtema,String descripcionTema ){
		try {
			state= ConexionSingleton.conectar().createStatement();
			String Sql="INSERT INTO preguntaSubtema VALUES('"+pregunta+"','"+descripcion+"','"+ayuda+"','"+respuestaCorrecta+"','"+
					descripcionSubtema+ "','"+descripcionTema+"');";
					
			state.executeUpdate(Sql);
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void eliminarPregunta(DtoPregunta dtoPregunta){
		try {
			String sql;
			state= ConexionSingleton.conectar().createStatement();
			
			String tema= dtoPregunta.getTema();
			String subTema = dtoPregunta.getSubtema();
			String descripcion = dtoPregunta.getDescripcionPregunta();
			String pregunta = dtoPregunta.getPregunta();
			
			sql="DELETE FROM preguntaSubtema where pregunta='"+pregunta+"' and descripcion ='"+descripcion+"' "
					+ "and subtema_descripcion='"+subTema+"' and subtema_tema_descripcion='"+tema+"'  "
					+ "and pregunta not in (select preguntaSubtema_pregunta from preguntaEvaluacion  "
												+ "where subtema_descripcion='"+subTema+"' "
												+ "and subtema_tema_descripcion='"+tema+"' "
												+ "group by preguntaSubtema_pregunta)";
			state.executeUpdate(sql);
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void actualizarPregunta(DtoPregunta dtoPregunta, String preguntaOriginal){
		try {
			String sql;
			state= ConexionSingleton.conectar().createStatement();
			
			String tema= dtoPregunta.getTema();
			String subtema = dtoPregunta.getSubtema();
			String descripcion = dtoPregunta.getDescripcionPregunta();
			String pregunta = dtoPregunta.getPregunta();
			String ayuda = dtoPregunta.getDescripcionAyuda();
			String respuesta = dtoPregunta.getRespuestaCorrecta();						
			
			sql="UPDATE preguntaSubtema set pregunta='"+pregunta+"', descripcionAyuda='"+ayuda+"', respuestaCorrecta='"+respuesta+"'"
					+ "where pregunta='"+preguntaOriginal+"' and descripcion='"+descripcion+"' "
					+ "	and subtema_descripcion='"+subtema+"' and subtema_tema_descripcion='"+tema+"'"
					+ " and pregunta not in (select preguntaSubtema_pregunta from preguntaEvaluacion "
												+ " where preguntaSubtema_subtema_descripcion='"+subtema+"'"
												+ " and preguntaSubtema_subtema_tema_descripcion='"+tema+"'"
												+ " group by preguntaSubtema_pregunta);";			
			state.executeUpdate(sql);
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
		
		public DtoPregunta consultarPregunta(String pPregunta, String pDescripcion){
			DtoPregunta pregunta=new DtoPregunta();
			String sql;
			try {
				state= ConexionSingleton.conectar().createStatement();
				if(pDescripcion.equals("Selecci�n �nica")){
					sql="SELECT * FROM seleccionUnica WHERE pregunta="+ pPregunta +" AND descripcion=" +
							 pDescripcion+";";
				;
				}else if(pDescripcion.equals("Complete")){
					sql="SELECT * FROM complete WHERE pregunta="+ pPregunta +" AND descripcion=" +
							 pDescripcion+";";
				}else{
					sql="SELECT * FROM desarrollo WHERE pregunta="+ pPregunta +" AND descripcion=" +
							 pDescripcion+";";
				}
				
				ResultSet rs1=state.executeQuery(sql);
				pregunta.setPregunta(rs1.getString(1));
				pregunta.setDescripcionPregunta(rs1.getString(2));
				pregunta.setDescripcionAyuda(rs1.getNString(3));
				pregunta.setRespuestaCorrecta(rs1.getString(4));
				pregunta.setSubtema(rs1.getString(5));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return pregunta;
		}
		
		public ArrayList<DtoPregunta> listarPreguntaSubtema(String SubtemaDescripcion,String TemaDescripcion){
			ArrayList<DtoPregunta> listarPreguntas= new ArrayList<DtoPregunta>();
			try {
				state= ConexionSingleton.conectar().createStatement();
				String sql="SELECT * FROM preguntaSubtema where 	subtema_descripcion='"+SubtemaDescripcion+"' AND "
						+ "subtema_tema_descripcion='"+TemaDescripcion+"';";
				
				ResultSet rs1=state.executeQuery(sql);
				
				while(rs1.next()){
					DtoPregunta pregunta= new DtoPregunta();
					pregunta.setPregunta(rs1.getString(1));
					pregunta.setDescripcionPregunta(rs1.getString(2));
					pregunta.setDescripcionAyuda(rs1.getNString(3));
					pregunta.setRespuestaCorrecta(rs1.getString(4));
					listarPreguntas.add(pregunta);
				}
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return listarPreguntas;
		}
	
		public String getRespuesta(String tema, String subtema, String descripcion, String pregunta){
			String respuesta = new String();
			try {
				state= ConexionSingleton.conectar().createStatement();
				String Sql="SELECT descripcionAyuda, respuestaCorrecta FROM preguntaSubtema"+
					" where pregunta='"+pregunta+"' and descripcion ='"+descripcion+"'"+
				 	" and subtema_descripcion='"+subtema+"' and subtema_tema_descripcion='"+tema+"';";
				
				ResultSet rs1 = state.executeQuery(Sql);
				
				while(rs1.next()){
					respuesta = rs1.getString(2);
					return respuesta;
				}
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			return respuesta;
		}
		
	public void AgregarRespuestasIncorrectas(ArrayList<String> respuestas,String pregunta,String descripcionSubtema,String descripcionTema){
		for(int i=0;i<respuestas.size();i++){
			try {
				state= ConexionSingleton.conectar().createStatement();
				String Sql="INSERT INTO respuestaIncorrecta VALUES('"+respuestas.get(i)+"','Selección Única','"+pregunta+"','"+descripcionSubtema+"','"+descripcionTema+"');";
				state.executeUpdate(Sql);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public void agregarPreguntaEvaluacion(String tema, String subtema,String descripcion, String pPregunta, String evaluacion, String curso){
		try {
			state= ConexionSingleton.conectar().createStatement();
			String Sql="insert into preguntaEvaluacion values('"+descripcion+"','"+pPregunta+"',"+
			"'"+subtema+"','"+tema+"', '"+evaluacion+"','"+curso+"');";
			state.executeUpdate(Sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	}
	
}
