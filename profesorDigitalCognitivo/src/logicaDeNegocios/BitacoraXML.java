package logicaDeNegocios;



import logicaDeNegocios.dto.DtoBitacora;

public class BitacoraXML extends Bitacora {

	
	//************************************** CONSTRUCTOR *****************************************
			public BitacoraXML(DtoBitacora pDto){
				super(pDto);
				setTipo("CSV");
			}
		
}
