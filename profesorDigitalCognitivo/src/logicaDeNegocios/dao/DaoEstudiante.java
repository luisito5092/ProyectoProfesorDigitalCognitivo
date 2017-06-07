package logicaDeNegocios.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexionMySql.ConexionSingleton;
import logicaDeNegocios.Estudiante;
import logicaDeNegocios.dto.DtoEstudiante;


public class DaoEstudiante {
	Statement state;

	public void crearEstudiante(Estudiante estudiante){
		
		try {
			state= ConexionSingleton.conectar().createStatement();
			String sql="INSERT INTO estudiante VALUES('" + estudiante.getIdEstudiante() + "','" + estudiante.getNumeroCarne()  + "','" +
					estudiante.getNombre()  + "','" + estudiante.getPrimerApellido()  + "','" + estudiante.getSegundoApellido()  + "','" + 
					estudiante.getCorreo() + "','"+estudiante.getFechaNacimiento()+"','"+estudiante.getTelefono()+"');";
			state.executeUpdate(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void eliminarEstudiante(String pIdEstudiante){
			
			try {
				state= ConexionSingleton.conectar().createStatement();
				String sql="DELETE FROM estudiante WHERE idEstudiante=" + pIdEstudiante+";";
				state.executeUpdate(sql);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	public void actualizarEstudiante(DtoEstudiante dtoEstudiante){
		try {
			state= ConexionSingleton.conectar().createStatement();
			String sql="UPDATE estudiante SET carneEstudiante=" + dtoEstudiante.getNumeroCarne()
			+", fechaNacimiento=" + dtoEstudiante.getFechaNacimiento() + ", primerApellido=" + dtoEstudiante.getPrimerApellido()+
			", segundoApellido=" + dtoEstudiante.getSegundoApellido() + ", nombre=" + dtoEstudiante.getNombre() +
			", correoElectronico=" + dtoEstudiante.getCorreo() + "fechaNacimiento=" + dtoEstudiante.getFechaNacimiento()+
			"WHERE idEstudiante=" + dtoEstudiante.getIdEstudiante() + ";";
			state.executeUpdate(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void agregarEstudianteCurso(String idEstudiante,String codigoCurso){
		try {
			state= ConexionSingleton.conectar().createStatement();
			String sql="INSERT INTO Estudiante_has_curso VALUES('"+idEstudiante+"','"+codigoCurso+"');";
			state.executeUpdate(sql);

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public DtoEstudiante consultarEstudiante(int pIdEstudiante){
		DtoEstudiante estudiante=new DtoEstudiante();
		try {
			state= ConexionSingleton.conectar().createStatement();
			String sql="SELECT * FROM estudiante WHERE idEstudiante=" + pIdEstudiante +";";
			ResultSet rs1=state.executeQuery(sql);
			estudiante.setIdEstudiante(rs1.getString(1));
			estudiante.setNumeroCarne(rs1.getString(2));
			estudiante.setNombre(rs1.getString(3));
			estudiante.setPrimerApellido(rs1.getString(4));
			estudiante.setSegundoApellido(rs1.getString(5));
			estudiante.setCorreo(rs1.getString(6));
			estudiante.setFechaNacimiento(rs1.getString(7));
	
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return estudiante;
	}

	public ArrayList<DtoEstudiante> crearListado(){
		ArrayList<DtoEstudiante> listaEstudiantes= new ArrayList<DtoEstudiante>();
		try {
			state= ConexionSingleton.conectar().createStatement();
			String sql="SELECT * FROM estudiante;";
			ResultSet rs1=state.executeQuery(sql);
			while(rs1.next()){
				DtoEstudiante estudiante=new DtoEstudiante();
				estudiante.setIdEstudiante(rs1.getString(1));
				estudiante.setNumeroCarne(rs1.getString(2));
				estudiante.setNombre(rs1.getString(3));
				estudiante.setPrimerApellido(rs1.getString(4));
				estudiante.setSegundoApellido(rs1.getString(5));
				estudiante.setCorreo(rs1.getString(6));
				estudiante.setFechaNacimiento(rs1.getString(7));
				listaEstudiantes.add(estudiante);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return listaEstudiantes;
	}
	
	public ArrayList<DtoEstudiante> crearListadoCurso(String curso){
		ArrayList<DtoEstudiante> listaEstudiantes= new ArrayList<DtoEstudiante>();
		try {
			state= ConexionSingleton.conectar().createStatement();
			String sql="SELECT * FROM estudiante JOIN Estudiante_has_curso ON curso_codigo='"+curso+"' and idEstudiante = Estudiante_idEstudiante;";
			ResultSet rs1=state.executeQuery(sql);
			while(rs1.next()){
				DtoEstudiante estudiante=new DtoEstudiante();
				estudiante.setIdEstudiante(rs1.getString(1));
				estudiante.setNumeroCarne(rs1.getString(2));
				estudiante.setNombre(rs1.getString(3));
				estudiante.setPrimerApellido(rs1.getString(4));
				estudiante.setSegundoApellido(rs1.getString(5));
				estudiante.setCorreo(rs1.getString(6));
				estudiante.setFechaNacimiento(rs1.getString(7));
				listaEstudiantes.add(estudiante);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return listaEstudiantes;
	}
	
}
