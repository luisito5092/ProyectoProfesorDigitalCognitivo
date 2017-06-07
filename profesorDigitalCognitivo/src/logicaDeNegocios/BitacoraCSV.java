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
import serviciosCognitivos.Encriptar;
import serviciosCognitivos.EncriptarDatos;

public class BitacoraCSV extends Bitacora {
	
	//************************************** CONSTRUCTOR *****************************************
	public BitacoraCSV(String pRuta){
		super( pRuta);
	}
	
	//************************************** OTROS METODOS *****************************************
	public void realizarRegistro(String email,String descripcion,String codigo) {
		boolean alreadyExists = new File(ruta).exists();
		DateFormat dateFormatFecha = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat dateFormatHora = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		
		try {
			
			CsvWriter csvOutput = new CsvWriter(new FileWriter(ruta, true), ';');
			
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
			csvOutput.write(EncriptarDatos.codificar(email));
			csvOutput.write(EncriptarDatos.codificar(dateFormatFecha.format(date)));
			csvOutput.write(EncriptarDatos.codificar(dateFormatHora.format(date)));
			csvOutput.write(EncriptarDatos.codificar(descripcion));
			csvOutput.write(EncriptarDatos.codificar(codigo));
			csvOutput.endRecord();
			
			csvOutput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
/*
	public ArrayList<DtoBitacora> leerRegistro(String fechaInicio,String fechaFinal){
		ArrayList<DtoBitacora> listaBitacoras=new ArrayList<DtoBitacora>();
		SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");

		try{
			Date inicio = fecha.parse(fechaInicio);
			Date finall = fecha.parse(fechaFinal);
			try{
				CsvReader bitacora = new CsvReader(getRuta());	
				bitacora.readHeaders();
				while (bitacora.readRecord()){
					Date actual = fecha.parse(EncriptarDatos.decodificar(bitacora.get("Fecha")));
					if(actual.after(inicio) && actual.before(finall)){
						DtoBitacora dto=new DtoBitacora();
						dto.setCorreoProfesor(bitacora.get("CorreoProfesor"));
						dto.setFecha(bitacora.get("Fecha"));
						dto.setHora(bitacora.get("Hora"));
						dto.setDescripcion(bitacora.get("Descripcion"));
						dto.setCodigoCurso(bitacora.get("CodigoCurso"));
						listaBitacoras.add(dto);
					}
				}
				bitacora.close();
			}
			catch(Exception e){
				
			}
		}catch(Exception e){
			
		}
		
		return listaBitacoras;
	}	*/
	
	public ArrayList<DtoBitacora> leerRegistro(String fechaInicio,String fechaFinal){
		ArrayList<DtoBitacora> listaBitacoras=new ArrayList<DtoBitacora>();
		SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
		try{
			Date inicio = fecha.parse(fechaInicio);
			Date finall = fecha.parse(fechaFinal);
			try{
				CsvReader bitacora = new CsvReader(getRuta());	
				bitacora.readHeaders();
				while (bitacora.readRecord()){
					Date actual = fecha.parse(EncriptarDatos.decodificar(bitacora.get("Fecha")));
					if(actual.after(inicio) && actual.before(finall)){
						DtoBitacora dto=new DtoBitacora();
						dto.setCorreoProfesor(EncriptarDatos.decodificar(bitacora.get("CorreoProfesor")));
						dto.setFecha(EncriptarDatos.decodificar(bitacora.get("Fecha")));
						dto.setHora(EncriptarDatos.decodificar(bitacora.get("Hora")));
						dto.setDescripcion(EncriptarDatos.decodificar(bitacora.get("Descripcion")));
						dto.setCodigoCurso(EncriptarDatos.decodificar(bitacora.get("CodigoCurso")));
						listaBitacoras.add(dto);
					}
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
