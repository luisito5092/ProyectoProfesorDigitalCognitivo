package logicaDeNegocios.dao;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexionMySql.ConexionSingleton;
import logicaDeNegocios.dto.DtoEvaluacionAplicada;
import logicaDeNegocios.dto.DtoPregunta;


public class DaoEvaluacionAplicada {

	Statement state;

	public void crearEvaluacionAplicada(String idEstudiante, String cursoCodigo, String nombreEvaluacion){
		try {
			state= ConexionSingleton.conectar().createStatement(); 
			String sql="INSERT INTO evaluacionAplicada VALUES(0,'" +idEstudiante +"',"
					+ "'" + cursoCodigo + "','" + nombreEvaluacion +"','Sin realizar');";
			state.executeUpdate(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	public ArrayList<DtoEvaluacionAplicada> listarEstadoEvaluaciones(String cursoCodigo,String evaluacion){
		ArrayList<DtoEvaluacionAplicada> listaEvaluaciones=new ArrayList<DtoEvaluacionAplicada>();
		try {
			state= ConexionSingleton.conectar().createStatement();
			
			String sql="Select estudiante_idEstudiante,estado, nombre,primerApellido,segundoApellido"
					+ " FROM estudiante JOIN evaluacionAplicada ON idEstudiante=estudiante_idEstudiante and "
					+ " evaluacion_nombre='"+evaluacion+"' and evaluacion_curso_codigo='"+cursoCodigo+"';";
			
			ResultSet rs1=state.executeQuery(sql);
			
			while(rs1.next()){
				DtoEvaluacionAplicada info=new DtoEvaluacionAplicada();
				info.setIdEstudiante(rs1.getString(1));
				info.setEstado(rs1.getString(2));
				info.setNombreEstudiante(rs1.getString(3)+" "+rs1.getString(4)+" "+rs1.getString(5));
				listaEvaluaciones.add(info);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return listaEvaluaciones;
	}
	 
	
}
