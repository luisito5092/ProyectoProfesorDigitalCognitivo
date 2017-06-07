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
	import serviciosCognitivos.Encriptar;
	
	public class BitacoraTXT extends Bitacora {
		
		//************************************** CONSTRUCTOR *****************************************
		public BitacoraTXT(String pRuta){
			super(pRuta);
		}
		
		//************************************** OTROS METODOS *****************************************
		public void realizarRegistro(String email,String descripcion,String codigo){
			boolean existeArchivo = getArchivo().exists();
			DateFormat dateFormatFecha = new SimpleDateFormat("yyyy-MM-dd");
			DateFormat dateFormatHora = new SimpleDateFormat("HH:mm:ss");
			Date date = new Date();
			Encriptar encriptar=new Encriptar();
			try{
				FileWriter modificar = new FileWriter(getRuta(), true);
				BufferedWriter bufferModificar = new BufferedWriter(modificar);
				if(!existeArchivo){
					bufferModificar.write("usuario" + "-" + "fecha" + "-" + "hora"+ "-" + "descripcion" + "-" + "codigoCurso");
					bufferModificar.newLine();
				}
				bufferModificar.write(encriptar.codificar(email) + "-" + encriptar.codificar(dateFormatFecha.format(date).toString()) + "-" + encriptar.codificar(dateFormatHora.format(date)) + "-" + encriptar.codificar(descripcion) + "-" + encriptar.codificar(codigo));
				//bufferModificar.write(email + "-" + dateFormatFecha.format(date).toString() + "-" + dateFormatHora.format(date) + "-" + descripcion + "-" + codigo);
				bufferModificar.newLine();
				bufferModificar.close();
				modificar.close();
			}
			catch(Exception e){
			}
		}
		
		public ArrayList<DtoBitacora> leerRegistro(String fechaInicio,String fechaFinal){
			ArrayList<DtoBitacora> listaBitacoras=new ArrayList<DtoBitacora>();
			SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
			
			
			try{
				Date inicio = fecha.parse(fechaInicio);
				Date finall = fecha.parse(fechaFinal);
				
				try {
		            BufferedReader b = new BufferedReader(new FileReader(getArchivo()));
		            String readLine = "";
		            
		            boolean bandera = false;
		            while ((readLine = b.readLine()) != null) {
		            	if(!bandera){
		            		bandera = true;
		            	}
		            	Date actual = fecha.parse(readLine.split("\\s*-\\s*")[1]);
		            	if(actual.after(inicio) && actual.before(finall)){
		            	//else{
		            		DtoBitacora dto=new DtoBitacora();	
							dto.setCorreoProfesor(readLine.split("\\s*-\\s*")[0]);
		    				dto.setFecha(readLine.split("\\s*-\\s*")[1]);
		    				dto.setHora(readLine.split("\\s*-\\s*")[2]);
		    				dto.setDescripcion(readLine.split("\\s*-\\s*")[3]);
		    				dto.setCodigoCurso(readLine.split("\\s*-\\s*")[4]);
		    				listaBitacoras.add(dto);
		            	}
		            }
		        } catch (Exception e) {

		        }
			}catch (Exception e) {

	        }
			
			return listaBitacoras;
		}

}
