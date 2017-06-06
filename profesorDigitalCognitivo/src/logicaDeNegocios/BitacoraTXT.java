package logicaDeNegocios;

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

	public class BitacoraTXT extends Bitacora {
		
		public BitacoraTXT(String pRuta){
			super(pRuta);
		}
		
		
		public void realizarRegistro(String email,String descripcion,String codigo){
			boolean existeArchivo = getArchivo().exists();
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
			}

		}
		
		public ArrayList<DtoBitacora> leerRegistro(){
			ArrayList<DtoBitacora> listaBitacoras=new ArrayList<DtoBitacora>();
			try {
	            BufferedReader b = new BufferedReader(new FileReader(getArchivo()));
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
