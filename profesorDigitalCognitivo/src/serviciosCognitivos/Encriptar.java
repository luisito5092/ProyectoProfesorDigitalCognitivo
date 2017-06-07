package serviciosCognitivos;
import java.util.ArrayList;
import java.util.Hashtable;

public class Encriptar {
	
	private static final String ORIGINAL= "¡·…ÈÕÌ”Û⁄˙—Ò‹¸";
	private static final String REPLACEMENT= "AaEeIiOoUuNnUu";
	
	
public static ArrayList<Integer> encriptaNumero (String textoTodo, String clave) {
			
//		Array de clave
		char[] caracteresClave = clave.toCharArray();
	
		String texto=stripAccents(textoTodo.toLowerCase());
		
//		Texto a encriptar en array
		char[] caracteresTexto = texto.toCharArray();
		
//		Tbla de equivalencias alfabeto
        Hashtable<Character, Integer> valorLetras = new Hashtable<Character, Integer>();
        valorLetras.put(' ', 0);
        valorLetras.put('a', 1);
        valorLetras.put('b', 2);
        valorLetras.put('c', 3);
        valorLetras.put('d', 4);
        valorLetras.put('e', 5);
        valorLetras.put('f', 6);
        valorLetras.put('g', 7);
        valorLetras.put('h', 8);
        valorLetras.put('i', 9);
        valorLetras.put('j', 10);
        valorLetras.put('k', 11);
        valorLetras.put('l', 12);
        valorLetras.put('m', 13);
        valorLetras.put('n', 14);
        valorLetras.put('o', 15);
        valorLetras.put('p', 16);
        valorLetras.put('q', 17);
        valorLetras.put('r', 18);
        valorLetras.put('s', 19);
        valorLetras.put('t', 20);
        valorLetras.put('u', 21);
        valorLetras.put('v', 22);
        valorLetras.put('?', 23);
        valorLetras.put('x', 24);
        valorLetras.put('y', 25);
        valorLetras.put('z', 26);
        valorLetras.put(',',27);
        valorLetras.put('-',28);
        valorLetras.put('_',29);
        valorLetras.put('ø',30);
        valorLetras.put('w',31);
        
        
        ArrayList<Integer> arrayNumEncr = new ArrayList<Integer>();
        
        int contadorTexto =0;
        int contadorClave=0;
        int auxSuma=0;
        
        while(contadorTexto< caracteresTexto.length){        	
        	
        if(contadorClave == caracteresClave.length){
        		contadorClave=0;}
        
        if(valorLetras.get(caracteresTexto[contadorTexto]) == 0){
        	contadorClave=0;
        	auxSuma=0;
        }
        
        else{
            auxSuma = valorLetras.get(caracteresTexto[contadorTexto])+valorLetras.get(caracteresClave[contadorClave]);
     }
        
        auxSuma=validarSumaEn(auxSuma);
        arrayNumEncr.add(auxSuma);
        
        contadorTexto++;
        contadorClave++;
        if(auxSuma==0){
        	contadorClave=0;
        }
        auxSuma=0;
        }

        return arrayNumEncr;

	}


public static ArrayList<Integer> desencriptaNumero (String textoTodo, String clave) {
	
//	Array de clave
	char[] caracteresClave = clave.toCharArray();
	

	String texto=stripAccents(textoTodo.toLowerCase());
	
//	Texto a encriptar en array
	char[] caracteresTexto = texto.toCharArray();
//	Tbla de equivalencias alfabeto
    Hashtable<Character, Integer> valorLetras = new Hashtable<Character, Integer>();
    valorLetras.put(' ', 0);
    valorLetras.put('a', 1);
    valorLetras.put('b', 2);
    valorLetras.put('c', 3);
    valorLetras.put('d', 4);
    valorLetras.put('e', 5);
    valorLetras.put('f', 6);
    valorLetras.put('g', 7);
    valorLetras.put('h', 8);
    valorLetras.put('i', 9);
    valorLetras.put('j', 10);
    valorLetras.put('k', 11);
    valorLetras.put('l', 12);
    valorLetras.put('m', 13);
    valorLetras.put('n', 14);
    valorLetras.put('o', 15);
    valorLetras.put('p', 16);
    valorLetras.put('q', 17);
    valorLetras.put('r', 18);
    valorLetras.put('s', 19);
    valorLetras.put('t', 20);
    valorLetras.put('u', 21);
    valorLetras.put('v', 22);
    valorLetras.put('?', 23);
    valorLetras.put('x', 24);
    valorLetras.put('y', 25);
    valorLetras.put('z', 26);
    valorLetras.put(',',27);
    valorLetras.put('-',28);
    valorLetras.put('_',29);
    valorLetras.put('ø',30);
    valorLetras.put('w',31);
    
    
    ArrayList<Integer> arrayNumEncr = new ArrayList<Integer>();
    
    int contadorTexto =0;
    int contadorClave=0;
    int auxSuma=0;
    
    while(contadorTexto< caracteresTexto.length){        	
    	
    if(contadorClave == caracteresClave.length){
    		contadorClave=0;}

    if(valorLetras.get(caracteresTexto[contadorTexto]) == 0){
    	contadorClave=0;
    	auxSuma=0;
    }
    else{
        auxSuma = valorLetras.get(caracteresTexto[contadorTexto])-valorLetras.get(caracteresClave[contadorClave]);
 } 
    auxSuma=validarSumaDes(auxSuma);

    arrayNumEncr.add(auxSuma);
    
    contadorTexto++;
    contadorClave++;
    if(auxSuma==0){
    	contadorClave=0;
    }
    auxSuma=0;
    }

    return arrayNumEncr;

}


public static String deNumeroATexto(ArrayList<Integer> encrNum){
	
	String textoEncriptado ="";
	
//	Tbla de equivalencias alfabeto
    Hashtable<Integer, Character> valorNumeros = new Hashtable<Integer, Character>();
    valorNumeros.put(0,' ');
    valorNumeros.put(1,'a');
    valorNumeros.put(2,'b');
    valorNumeros.put(3,'c');
    valorNumeros.put(4,'d');
    valorNumeros.put(5,'e');
    valorNumeros.put(6,'f');
    valorNumeros.put(7,'g');
    valorNumeros.put(8,'h');
    valorNumeros.put(9,'i');
    valorNumeros.put(10,'j');
    valorNumeros.put(11,'k');
    valorNumeros.put(12,'l');
    valorNumeros.put(13,'m');
    valorNumeros.put(14,'n');
    valorNumeros.put(15,'o');
    valorNumeros.put(16,'p');
    valorNumeros.put(17,'q');
    valorNumeros.put(18,'r');
    valorNumeros.put(19,'s');
    valorNumeros.put(20,'t');
    valorNumeros.put(21,'u');
    valorNumeros.put(22,'v');
    valorNumeros.put(23,'?');
    valorNumeros.put(24,'x');
    valorNumeros.put(25,'y');
    valorNumeros.put(26,'z');
    valorNumeros.put(27,',');
    valorNumeros.put(28,'-');
    valorNumeros.put(29,'_');
    valorNumeros.put(30,'ø');
    valorNumeros.put(31,'w');
    

    
    int contador =0;
    while(contador<encrNum.size()){
    	textoEncriptado+=valorNumeros.get(encrNum.get(contador)).toString();
    	contador++;
    }
    
	return textoEncriptado;
}

public static int validarSumaEn(int suma){
	int aux=0;
	if(suma>31){
		aux=suma-31;
	}
	else{
		aux=suma;
	}
	return aux;
}

public static int validarSumaDes(int suma){
	int aux=0;
	if(suma<0){
		aux=suma+31;
	}
	else{
		aux=suma;
	}
	return aux;
}

public static String stripAccents(String str) {
	if (str == null) {
		return null;}
	char[] array = str.toCharArray();
	for (int index = 0; index < array.length; index++) {
		int pos = ORIGINAL.indexOf(array[index]);
		if (pos > -1) {
			array[index] = REPLACEMENT.charAt(pos);}
		}
	return new String(array);
	}

public static void main (String [ ] args) {
	
	System.out.println("Texto: "+"øcomes Queso por la tarde?");
	System.out.println("EncriptaciÛn");
	ArrayList<Integer> listaEn = encriptaNumero("øcomes Queso por la tarde toda la manana y por toda la semana lalalala?", "turo");
	String encript = deNumeroATexto(listaEn);
	System.out.println(encript);
	
	
	System.out.println("DesencriptaciÛn");
	ArrayList<Integer> listaDes = desencriptaNumero(encript, "turo");
	String desencript = deNumeroATexto(listaDes);
	System.out.println(desencript);
}

}
