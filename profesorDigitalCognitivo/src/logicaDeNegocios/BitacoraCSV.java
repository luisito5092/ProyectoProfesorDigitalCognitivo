package logicaDeNegocios;

import logicaDeNegocios.dto.DtoBitacora;

public class BitacoraCSV extends Bitacora {
	

	//************************************** CONSTRUCTOR *****************************************
		public BitacoraCSV(Curso pCurso){
			super();
			this.setCurso(pCurso);
			setTipo("CSV");
			setFecha(" ");
			setCorreoProfesor(pCurso.getProfesor().getCorreo());
			this.curso.vincularBitacora(this);
		}

		@Override
		public String actualizarBitacora() {
			String cambio="Se realizó un cambio en el curso "+curso+" a las "+fecha+"";
			return cambio;
		}
}
