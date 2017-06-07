package serviciosCognitivos;

import java.util.ArrayList;

public class EncriptarDatos {
	
	
	private static String[] splitPalabras(String mensaje){
		String[] lista;
		lista= mensaje.split("\\s");
		return lista;
	}
	public static String codificar(String mensaje){
		String[] lista=splitPalabras(mensaje);
		String retorno="";
		char[] cadena;
		for(int i=0;i<lista.length;i++){
			cadena=lista[i].toCharArray();
			int indice=cadena.length-1;
			for(int j=0;j<cadena.length;j++){
				retorno+=cadena[indice];
				
				indice--;
			}
			retorno+=" ";
		}
		return retorno;
	}
	
	public static String decodificar(String mensaje){
		return codificar(mensaje);
	}
}
