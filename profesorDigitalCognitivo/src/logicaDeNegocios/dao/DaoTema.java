package logicaDeNegocios.dao;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import conexionMySql.ConexionSingleton;
import logicaDeNegocios.dto.DtoCurso;
import logicaDeNegocios.dto.DtoTema;

public class DaoTema {
	Statement state;

	public void agregarTemaNuevo(String descripcionTema){
		try {
			String sql="INSERT INTO tema VALUES('"+ descripcionTema.toString()+"');";
			state.executeUpdate(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
		
	public void agregarTemaCurso(String cursoTema,String descripcionTema){
			try {
				state= ConexionSingleton.conectar().createStatement();
				String sql="INSERT INTO curso_has_tema VALUES('"+ cursoTema+ "','"+descripcionTema+"');";
				state.executeUpdate(sql);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	
	public void eliminarTema(String pDescripcion, String pCurso){
		try {
			state= ConexionSingleton.conectar().createStatement();
			String sql="DELETE FROM curso_has_tema WHERE tema_descripcion='" + pDescripcion+ "' and "
					+ "curso_codigo ='"+pCurso+"' ;";
			state.executeUpdate(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void actualizarTema(DtoTema dtoTema){
		try {
			state= ConexionSingleton.conectar().createStatement();
			String sql="UPDATE tema SET descripcion='" + dtoTema.getDescripcion()+ "';";
			state.executeUpdate(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public DtoTema consultarTema(String pDescripcion){
		DtoTema tema=new DtoTema();
		try {
			state= ConexionSingleton.conectar().createStatement();
			String sql="SELECT * FROM tema WHERE descripcion=" + pDescripcion +";";
			ResultSet rs1=state.executeQuery(sql);
			tema.setDescripcion(rs1.getString(1));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return tema;
	}
	
	public ArrayList<DtoTema> listarTemas(String Curso){
		ArrayList<DtoTema> listaTemas= new ArrayList<DtoTema>();
		try {
			state= ConexionSingleton.conectar().createStatement();
			String sql="SELECT * FROM tema JOIN curso_has_tema ON curso_codigo='"+Curso+"' and tema_descripcion = descripcion;";
			ResultSet rs1=state.executeQuery(sql);
			while(rs1.next()){
				DtoTema tema=new DtoTema();
				tema.setDescripcion(rs1.getString(1));	
				listaTemas.add(tema);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return listaTemas;
	}
	
	public ArrayList<DtoTema> listarTodosTemas(){
		ArrayList<DtoTema> listaTemas= new ArrayList<DtoTema>();
		try {
			state= ConexionSingleton.conectar().createStatement();
			String sql="SELECT * FROM tema GROUP BY descripcion;";
			ResultSet rs1=state.executeQuery(sql);
			while(rs1.next()){
				DtoTema tema=new DtoTema();
				tema.setDescripcion(rs1.getString(1));	
				listaTemas.add(tema);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return listaTemas;
	}

}
