package logicaDeNegocios.factory;

import logicaDeNegocios.Profesor;
import logicaDeNegocios.dto.DtoProfesor;

public class FabricaProfesor {
	
	public Profesor fabricarProfesor(DtoProfesor dtoProfesor){
		return new Profesor(dtoProfesor.getNombre(), dtoProfesor.getCorreoElectronico(), dtoProfesor.getContrasenia(),
				dtoProfesor.getTelefono());
		
	}

}
