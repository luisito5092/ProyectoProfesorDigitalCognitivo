package logicaDeNegocios;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import logicaDeNegocios.dto.DtoBitacora;

public class BitacoraCSV extends Bitacora {
	
	
	
	//************************************** CONSTRUCTOR *****************************************
	public BitacoraCSV(String pRuta){
		super( pRuta);
	}
	
	//************************************** OTROS METODOS *****************************************
	public void realizarRegistro(String email,String descripcion,String codigo) {
		boolean alreadyExists = new File(ruta).exists();
		DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");
		Date date = new Date();
			
		try {
			
			CsvWriter csvOutput = new CsvWriter(new FileWriter(ruta, true), ',');
			
			// if the file didn't already exist then we need to write out the header line
			if (!alreadyExists)
			{
				csvOutput.write("Fecha");
				csvOutput.write("CorreoProfesor");
				csvOutput.write("Descripcion");
				csvOutput.write("CodigoCurso");
				csvOutput.endRecord();
			}
			// else assume that the file already has the correct header line
			
			// write out a few records
			csvOutput.write(email);
			csvOutput.write(descripcion);
			csvOutput.write(dateFormat.format(date).toString());
			csvOutput.write(codigo);
			csvOutput.endRecord();
			
			csvOutput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public ArrayList<DtoBitacora> leerRegistro(){
		ArrayList<DtoBitacora> listaBitacoras=new ArrayList<DtoBitacora>();
		try{
			CsvReader bitacora = new CsvReader(getRuta());
			
			bitacora.readHeaders();
	
			while (bitacora.readRecord()){
				DtoBitacora dto=new DtoBitacora();	
				dto.setDescripcion(bitacora.get("Descripcion"));
				dto.setFecha(bitacora.get("fecha"));
				dto.setCorreoProfesor(bitacora.get("CorreoProfesor"));
				dto.setCodigoCurso(bitacora.get("CodigoCurso"));
				listaBitacoras.add(dto);
			}
			bitacora.close();
		}
		catch(Exception e){
			
		}
		return listaBitacoras;
	}	
		
}
