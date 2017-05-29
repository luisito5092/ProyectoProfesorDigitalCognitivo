package logicaDeNegocios.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexionMySql.ConexionSingleton;
import logicaDeNegocios.dto.DtoParteEvaluacion;
import logicaDeNegocios.dto.DtoPregunta;


public class DaoParteEvaluacion {
	Statement state;

	public void crearParteEvaluacion(String tipo, String puntos, String curso, String nombreEvaluacion ){
		try {
			state= ConexionSingleton.conectar().createStatement(); 
			String sql="INSERT INTO parteEvaluacion VALUES('"+ tipo + "'," +
					puntos +",'" + curso + "','" + nombreEvaluacion +"');";
			state.executeUpdate(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public ArrayList<DtoPregunta> listarPreguntasParaEvaluacion( String curso, String nombreEvaluacion){
		ArrayList<DtoPregunta> listaPreguntas= new ArrayList<DtoPregunta>();
		try {
			state= ConexionSingleton.conectar().createStatement();
			 		    
			String sql= "SELECT  subtema_tema_descripcion,subtema_descripcion, descripcion,pregunta"+ 
					" from preguntaSubtema  join parteEvaluacion join curso_has_tema join preguntaEvaluacion"+
					" on evaluacion_curso_codigo = curso_codigo and subtema_tema_descripcion = tema_descripcion"+ 
					" and curso_codigo='"+curso+"' and evaluacion_nombre = '"+nombreEvaluacion+"' "+ 
					" and tipo = descripcion" +
					
					" and pregunta not in (select preguntaSubtema_pregunta from preguntaEvaluacion"+
												" where parteEvaluacion_evaluacion_nombre = '"+nombreEvaluacion+"'"+
												" and parteEvaluacion_evaluacion_curso_codigo = '"+curso+"')"+
					" group by subtema_tema_descripcion,subtema_descripcion,descripcion,pregunta"+
					" order by subtema_tema_descripcion,subtema_descripcion,descripcion,pregunta;";

			ResultSet rs1=state.executeQuery(sql);
			while(rs1.next()){ 
				DtoPregunta pregunta=new DtoPregunta();
				
				pregunta.setTema(rs1.getString(1));
				pregunta.setSubtema(rs1.getString(2));
				pregunta.setDescripcionPregunta(rs1.getString(3));
				pregunta.setPregunta(rs1.getString(4));
					
				listaPreguntas.add(pregunta);
			}
			
		} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		}
		return listaPreguntas;
	}

}
