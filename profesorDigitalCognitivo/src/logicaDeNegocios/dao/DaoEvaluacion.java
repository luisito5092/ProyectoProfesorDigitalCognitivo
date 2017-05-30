package logicaDeNegocios.dao;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexionMySql.ConexionSingleton;
import logicaDeNegocios.dto.DtoCurso;
import logicaDeNegocios.dto.DtoEvaluacion;
import serviciosCognitivos.EnviarMail;
import serviciosCognitivos.EnviarMail2;



public class DaoEvaluacion {
	
	Statement state;

	public void crearEvaluacion(String nombre, String puntaje, String porcentaje, String horaYFecha, String minutos, String tipo, String codigoCurso){
		
		try {
			
			state= ConexionSingleton.conectar().createStatement();
			String sql;
			sql="INSERT INTO evaluacion VALUES('" + nombre + "'," + puntaje + ",'" + 
					horaYFecha + "'," + minutos +",false," + porcentaje + ",'"+ codigoCurso +"','"+ tipo +"');";
			state.executeUpdate(sql);
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void eliminarEvaluacion(String pCodigoCurso, String pNombre){
		try {
			state= ConexionSingleton.conectar().createStatement();
			String sql="DELETE FROM curso WHERE curso_codigo=" + pCodigoCurso+ "AND nombre=" + pNombre+";";
			state.executeUpdate(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void actualizarEvaluacion(DtoEvaluacion dtoEvaluacion){
		try {
			state= ConexionSingleton.conectar().createStatement();
			String sql="UPDATE evaluacion SET curso_codigo=" + dtoEvaluacion.getCurso().getCodigo()+", nombre="
			+ dtoEvaluacion.getNombreEvaluacion() + ", minutosDisponibles="+ dtoEvaluacion.getMinutosDisponibles()+
			", horaFecha="+dtoEvaluacion.getHoraFecha()+", porcentajeCurso="+ dtoEvaluacion.getPorcentajeCurso()+
			", puntajeTotal="+dtoEvaluacion.getPuntajeTotal()+", isHabilitada="+dtoEvaluacion.isHabilitada()+
			"tipo="+dtoEvaluacion.getTipo()+" WHERE curso_codigo=" + dtoEvaluacion.getCurso().getCodigo()+", AND nombre=" +
			dtoEvaluacion.getNombreEvaluacion()+";";
			state.executeUpdate(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public DtoEvaluacion consultarEvaluacion(DtoEvaluacion dtoEvaluacion){
		DtoEvaluacion evaluacion=new DtoEvaluacion();
		try {
			state= ConexionSingleton.conectar().createStatement();
			String sql="SELECT * FROM curso WHERE codigo_curso=" + dtoEvaluacion.getCurso().getCodigo() 
					+" AND nombre="+dtoEvaluacion.getNombreEvaluacion()+";";
			ResultSet rs1=state.executeQuery(sql);
			evaluacion.setNombreEvaluacion(rs1.getString(1));
			evaluacion.setPuntajeTotal(rs1.getInt(2));
			evaluacion.setHoraFecha(rs1.getString(1));
			evaluacion.setMinutosDisponibles(rs1.getInt(4));
			evaluacion.setHabilitada(rs1.getBoolean(5));
			evaluacion.setPorcentajeCurso(rs1.getDouble(6));
			evaluacion.setTipo(rs1.getString(8));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return evaluacion;
	}
	
	public ArrayList<DtoEvaluacion> listarEvaluacionesCurso(String codigo){
		ArrayList<DtoEvaluacion> listaEvaluaciones= new ArrayList<DtoEvaluacion>();
		try {
			state= ConexionSingleton.conectar().createStatement();
			String sql="SELECT * FROM evaluacion where curso_codigo='"+codigo+"';";
			ResultSet rs1=state.executeQuery(sql);
			while(rs1.next()){
				DtoEvaluacion evaluacion=new DtoEvaluacion();
				evaluacion.setNombreEvaluacion(rs1.getString(1));
				evaluacion.setPuntajeTotal(rs1.getInt(2));
				evaluacion.setHoraFecha(rs1.getString(3));
				evaluacion.setMinutosDisponibles(rs1.getInt(4));
				evaluacion.setHabilitada(rs1.getBoolean(5));
				evaluacion.setPorcentajeCurso(rs1.getDouble(6));
				evaluacion.setTipo(rs1.getString(8));	
				listaEvaluaciones.add(evaluacion);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return listaEvaluaciones;
	}
	
	public ArrayList<DtoEvaluacion> listarEvaluacionesHabilitadasCurso(String codigo){
		ArrayList<DtoEvaluacion> listaEvaluaciones= new ArrayList<DtoEvaluacion>();
		try {
			state= ConexionSingleton.conectar().createStatement();
			String sql="SELECT * FROM evaluacion where curso_codigo='"+codigo+"' AND isHabilitada=1;";
			ResultSet rs1=state.executeQuery(sql);
			while(rs1.next()){
				DtoEvaluacion evaluacion=new DtoEvaluacion();
				evaluacion.setNombreEvaluacion(rs1.getString(1));
				evaluacion.setPuntajeTotal(rs1.getInt(2));
				evaluacion.setHoraFecha(rs1.getString(3));
				evaluacion.setMinutosDisponibles(rs1.getInt(4));
				evaluacion.setHabilitada(rs1.getBoolean(5));
				evaluacion.setPorcentajeCurso(rs1.getDouble(6));
				evaluacion.setTipo(rs1.getString(8));	
				listaEvaluaciones.add(evaluacion);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return listaEvaluaciones;
	}
	
	public ArrayList<DtoEvaluacion> listarEvaluacionesDeshabilitadasCurso(String codigo){
		ArrayList<DtoEvaluacion> listaEvaluaciones= new ArrayList<DtoEvaluacion>();
		try {
			state= ConexionSingleton.conectar().createStatement();
			String sql="SELECT * FROM evaluacion where curso_codigo='"+codigo+"' AND isHabilitada=0;";
			ResultSet rs1=state.executeQuery(sql);
			while(rs1.next()){
				DtoEvaluacion evaluacion=new DtoEvaluacion();
				evaluacion.setNombreEvaluacion(rs1.getString(1));
				evaluacion.setPuntajeTotal(rs1.getInt(2));
				evaluacion.setHoraFecha(rs1.getString(3));
				evaluacion.setMinutosDisponibles(rs1.getInt(4));
				evaluacion.setHabilitada(rs1.getBoolean(5));
				evaluacion.setPorcentajeCurso(rs1.getDouble(6));
				evaluacion.setTipo(rs1.getString(8));	
				listaEvaluaciones.add(evaluacion);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return listaEvaluaciones;
	}
	
	public void habilitarEvaluacion(String codigo, String nombreEvaluacion,String[] idEstudiante){
		DaoEvaluacionAplicada aplicada=new DaoEvaluacionAplicada();
		//EnviarMail2 email=new EnviarMail2();
		try {
			state= ConexionSingleton.conectar().createStatement();
			String sql="UPDATE evaluacion SET isHabilitada=1 WHERE curso_codigo='" + codigo + "' AND nombre='" +
					nombreEvaluacion+"';";
			state.executeUpdate(sql);
			
			for (int i=0;i<idEstudiante.length;i++){
				aplicada.crearEvaluacionAplicada(idEstudiante[i],codigo,nombreEvaluacion);
				//String sql2= "SELECT correoElectronico from estudiante where idEstudiante='"+idEstudiante[i]+"';";
				//ResultSet rs1=state.executeQuery(sql2);
				//email.enviarCorreo(rs1.getString(1));
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public ArrayList<DtoEvaluacion> consultarInfoEvaluacion(String nombre, String curso){
		ArrayList<DtoEvaluacion> listaEvaluaciones= new ArrayList<DtoEvaluacion>();
		try {
			state= ConexionSingleton.conectar().createStatement();
			String sql="SELECT * FROM evaluacion WHERE curso_codigo='" + curso +"' AND nombre='"+nombre+"';";
			ResultSet rs1=state.executeQuery(sql);
			while(rs1.next()){
				DtoEvaluacion evaluacion=new DtoEvaluacion();
				evaluacion.setNombreEvaluacion(rs1.getString(1));
				evaluacion.setPuntajeTotal(rs1.getInt(2));
				evaluacion.setHoraFecha(rs1.getString(3));
				evaluacion.setMinutosDisponibles(rs1.getInt(4));
				evaluacion.setHabilitada(rs1.getBoolean(5));
				evaluacion.setPorcentajeCurso(rs1.getDouble(6));
				evaluacion.setTipo(rs1.getString(8));	
				listaEvaluaciones.add(evaluacion);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return listaEvaluaciones;
	}
	
}
