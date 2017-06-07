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
		DateFormat dateFormatFecha = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat dateFormatHora = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
			
		try {
			
			CsvWriter csvOutput = new CsvWriter(new FileWriter(ruta, true), ',');
			
			// if the file didn't already exist then we need to write out the header line
			if (!alreadyExists)
			{
				csvOutput.write("CorreoProfesor");
				csvOutput.write("Fecha");
				csvOutput.write("Hora");
				csvOutput.write("Descripcion");
				csvOutput.write("CodigoCurso");
				csvOutput.endRecord();
			}
			// else assume that the file already has the correct header line
			
			// write out a few records
			csvOutput.write(email);
			csvOutput.write(dateFormatFecha.format(date).toString());
			csvOutput.write(dateFormatHora.format(date).toString());
			csvOutput.write(descripcion);
			csvOutput.write(codigo);
			csvOutput.endRecord();
			
			csvOutput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public ArrayList<DtoBitacora> leerRegistro(String fechaInicio,String fechaFinal){
		ArrayList<DtoBitacora> listaBitacoras=new ArrayList<DtoBitacora>();
		SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
		try{
			Date inicio = fecha.parse(fechaInicio);
			Date finall = fecha.parse(fechaFinal);
			try{
				CsvReader bitacora = new CsvReader(getRuta());	
				bitacora.readHeaders();
				
				while (bitacora.readRecord()){
					//Date actual = fecha.parse(bitacora.get("Fecha"));
					//if(actual.after(inicio) && actual.before(finall)){
						DtoBitacora dto=new DtoBitacora();
						dto.setCorreoProfesor(bitacora.get("CorreoProfesor"));
						dto.setFecha(bitacora.get("Fecha"));
						dto.setHora(bitacora.get("Hora"));
						dto.setDescripcion(bitacora.get("Descripcion"));
						dto.setCodigoCurso(bitacora.get("CodigoCurso"));
						listaBitacoras.add(dto);
					//}
				}
				bitacora.close();
			}
			catch(Exception e){
				
			}
		}catch(Exception e){
			
		}
		
		return listaBitacoras;
	}	
		
}
