package logicaDeNegocios.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import conexionMySql.ConexionSingleton;
import logicaDeNegocios.dto.DtoBitacora;

public class DaoBitacora {

	Statement state;
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	Date date = new Date();
	
	public void registrarBitacora(DtoBitacora dto){
		try {
			state= ConexionSingleton.conectar().createStatement();
			String sql="INSERT INTO bitacora VALUES('" + dto.getDescripcion() + "','" + dateFormat.format(date).toString() + "','"+dto.getCorreoProfesor()+"','"+dto.getCodigoCurso()+"');";
			state.executeUpdate(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public ArrayList<DtoBitacora> consultarBitacoras(String fechaInicio,String fechaFinal,String email) throws ParseException{
		ArrayList<DtoBitacora> listaBitacoras=new ArrayList<DtoBitacora>();
		try {
			state= ConexionSingleton.conectar().createStatement();
			//SimpleDateFormat fechaHora = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");
			//Date inicio = fechaHora.parse(fechaInicio);
			//Date finall = fechaHora.parse(fechaFinal);
			String sql="Select * from bitacora where correoProfe='"+email+"';";
			ResultSet rs1=state.executeQuery(sql);
			while(rs1.next()){
				//Date actual = fechaHora.parse(rs1.getString(2));
				//if(actual.after(inicio) || actual.before(finall)){
					DtoBitacora bitacora=new DtoBitacora();	
					bitacora.setDescripcion(rs1.getString(1));
					bitacora.setFecha(rs1.getString(2));
					bitacora.setCorreoProfesor(rs1.getString(3));
					bitacora.setCodigoCurso(rs1.getString(4));
					listaBitacoras.add(bitacora);
				//}
				
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return listaBitacoras;
	}
	
	public void registrarBitacoraCSV(DtoBitacora dto){
		
	}
	
}

