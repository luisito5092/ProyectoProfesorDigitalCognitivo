package logicaDeNegocios.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.csvreader.CsvReader;

import logicaDeNegocios.dto.DtoBitacora;



public class TXT {
	private String ruta;
	private File TXT;
	
	public TXT(String pRuta){
		setRuta(pRuta);
		setArchivoTXT();
	}
	
	public String getRuta() {
		return ruta;
	}

	public void setRuta(String pRuta) {
		this.ruta = pRuta;
	}

	public File getArchivoTXT() {
		return TXT;
	}

	public void setArchivoTXT() {
		this.TXT = new File(getRuta());
	}
	
	
	public boolean registrarBitacoraTXT(String email,String descripcion,String codigo){
		boolean retorno = true;
		boolean existeArchivo = getArchivoTXT().exists();
		DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");
		Date date = new Date();
		
		try{
			FileWriter modificar = new FileWriter(getRuta(), true);
			BufferedWriter bufferModificar = new BufferedWriter(modificar);
			if(!existeArchivo){
				bufferModificar.write("fecha" + "-" + "usuario" + "-" + "accion" + "-" + "codigoCurso");
				bufferModificar.newLine();
			}
			bufferModificar.write(dateFormat.format(date).toString() + "-" + email + "-" + descripcion + "-" + codigo);
			bufferModificar.newLine();
			bufferModificar.close();
			modificar.close();
		}
		catch(Exception e){
			retorno = false;
		}
		return retorno;
	}
	
	public ArrayList<DtoBitacora> leerRegistroTXT(){
		ArrayList<DtoBitacora> listaBitacoras=new ArrayList<DtoBitacora>();
		try {
            BufferedReader b = new BufferedReader(new FileReader(getArchivoTXT()));
            String readLine = "";
            
            boolean bandera = false;
            while ((readLine = b.readLine()) != null) {
            	if(!bandera){
            		bandera = true;
            	}
            	else{
            		DtoBitacora dto=new DtoBitacora();	
    				dto.setFecha(readLine.split("\\s*-\\s*")[0]);
    				dto.setCorreoProfesor(readLine.split("\\s*-\\s*")[1]);
    				dto.setDescripcion(readLine.split("\\s*-\\s*")[2]);
    				dto.setCodigoCurso(readLine.split("\\s*-\\s*")[3]);
    				listaBitacoras.add(dto);
            	}
            }
        } catch (Exception e) {

        }
		return listaBitacoras;
	}
}
