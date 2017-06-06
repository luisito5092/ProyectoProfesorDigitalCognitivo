package logicaDeNegocios;

import java.io.File;
import java.util.ArrayList;

import logicaDeNegocios.dto.DtoBitacora;

public abstract class Bitacora {
	protected String ruta;
	protected File archivo;
	
	//************************************** CONSTRUCTOR *****************************************
		public Bitacora(String pRuta){
			setRuta(pRuta);
			setArchivo();
		}
		
		//************************************** GETTERS & SETTERS *****************************************
		public String getRuta() {
			return ruta;
		}

		public void setRuta(String pDireccion) {
			this.ruta = pDireccion;
		}

		public File getArchivo() {
			return archivo;
		}

		public void setArchivo() {
			this.archivo = new File(getRuta());
		}
	
	
	//************************************** OTROS METODOS *****************************************
	public abstract void realizarRegistro(String email,String descripcion,String codigo);
	public abstract ArrayList<DtoBitacora> leerRegistro();
}
