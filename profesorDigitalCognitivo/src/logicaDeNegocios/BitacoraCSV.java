package logicaDeNegocios;

import logicaDeNegocios.dto.DtoBitacora;

public class BitacoraCSV extends Bitacora {
	

	//************************************** CONSTRUCTOR *****************************************
		public BitacoraCSV(DtoBitacora pDto){
			super(pDto);
			setTipo("CSV");
		}
}
