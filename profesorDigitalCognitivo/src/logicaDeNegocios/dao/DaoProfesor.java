package logicaDeNegocios.dao;

import java.sql.ResultSet;


import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexionMySql.ConexionSingleton;
import logicaDeNegocios.Profesor;
import logicaDeNegocios.dto.DtoProfesor;

public class DaoProfesor {

	Statement state;

	public void crearProfesor(Profesor daoProfesor){
		try {
			state= ConexionSingleton.conectar().createStatement();
			String sql="INSERT INTO profesor VALUES('" + daoProfesor.getCorreo() + "','" + daoProfesor.getContrasenia() +
					"','"+daoProfesor.getNombre()+"' ,'" + daoProfesor.getTelefono() +"');";
			state.executeUpdate(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ConexionSingleton.desconectar();
	}
	
	public void eliminarProfesor(String pCorreo){
		try {
			state= ConexionSingleton.conectar().createStatement();
			String sql="DELETE FROM profesor WHERE codigo=" + pCorreo+ ";";
			state.executeUpdate(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void actualizarProfesor(DtoProfesor dtoProfesor){
		try {
			state= ConexionSingleton.conectar().createStatement();
			String sql="UPDATE profesor SET correoElectronico=" + dtoProfesor.getCorreoElectronico()
			+", contrasenia=" + dtoProfesor.getContrasenia() + ""
					+ "WHERE correoElectronico=" + dtoProfesor.getCorreoElectronico() + ";";
			state.executeUpdate(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public DtoProfesor consultarCurso(String pCorreo){
		DtoProfesor profesor=new DtoProfesor();
		try {
			state= ConexionSingleton.conectar().createStatement();
			String sql="SELECT * FROM profesor WHERE correoElectronico=" + pCorreo +";";
			ResultSet rs1=state.executeQuery(sql);
			profesor.setCorreoElectronico(rs1.getString(1));
			profesor.setContrasenia(rs1.getString(2));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return profesor;
	}
	
	public ArrayList<DtoProfesor> listarProfesores(){
		ArrayList<DtoProfesor> listaProfesores= new ArrayList<DtoProfesor>();
		try {
			state= ConexionSingleton.conectar().createStatement();
			String sql="SELECT * FROM profesor;";
			ResultSet rs1=state.executeQuery(sql);
			while(rs1.next()){
				DtoProfesor profesor=new DtoProfesor();
				profesor.setCorreoElectronico(rs1.getString(1));
				profesor.setContrasenia(rs1.getString(2));	
				profesor.setNombre(rs1.getString(3));
				listaProfesores.add(profesor);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return listaProfesores;
	}
	
	public String getTelefono(String usuario) throws SQLException{
	  	state= ConexionSingleton.conectar().createStatement();
        String sql = "SELECT * FROM profesor WHERE correoElectronico='"+usuario+"';";
        ResultSet rs1=state.executeQuery(sql);
        String telefono = "";
		while(rs1.next()){	
			telefono = (rs1.getString(4));
		}
        return telefono;
	}
	
	  public boolean validarUsuario(DtoProfesor profesor) throws SQLException{
		  	state= ConexionSingleton.conectar().createStatement();
	        String sql = "SELECT * FROM profesor WHERE correoElectronico='"+profesor.getCorreoElectronico()+"' AND contrasenia='"+
	        		profesor.getContrasenia()+"';";
	        ResultSet rs1=state.executeQuery(sql);
	        return rs1.next();
	  }
}
