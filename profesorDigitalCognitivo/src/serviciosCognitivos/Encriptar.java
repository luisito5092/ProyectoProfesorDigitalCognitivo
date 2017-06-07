package serviciosCognitivos;
import java.util.ArrayList;
import java.util.Hashtable;

public class Encriptar {
	
	private static final String ORIGINAL= "ÂÁáÉéÍíÓóÚúÑñÜü";
	private static final String REPLACEMENT= "aAaEeIiOoUuNnUu";
	
	
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
        valorLetras.put('%',27);
        valorLetras.put('-',28);
        valorLetras.put('_',29);
        valorLetras.put('¿',30);
        valorLetras.put('@',31);
        valorLetras.put('0',32);
        valorLetras.put('1',33);
        valorLetras.put('2',34);
        valorLetras.put('3',35);
        valorLetras.put('4',36);
        valorLetras.put('5',37);
        valorLetras.put('6',38);
        valorLetras.put('7',39);
        valorLetras.put('8',40);
        valorLetras.put('9',41);
        valorLetras.put('w',42);
        valorLetras.put(':',43);
        valorLetras.put('.',44);
        valorLetras.put('*',45);
        
        
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
    valorLetras.put('%',27);
    valorLetras.put('-',28);
    valorLetras.put('_',29);
    valorLetras.put('¿',30);
    valorLetras.put('@',31);
    valorLetras.put('0',32);
    valorLetras.put('1',33);
    valorLetras.put('2',34);
    valorLetras.put('3',35);
    valorLetras.put('4',36);
    valorLetras.put('5',37);
    valorLetras.put('6',38);
    valorLetras.put('7',39);
    valorLetras.put('8',40);
    valorLetras.put('9',41);
    valorLetras.put('w',42);
    valorLetras.put(':',43);
    valorLetras.put('.',44);
    valorLetras.put('*',45);
    
    
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
    valorNumeros.put(27,'%');
    valorNumeros.put(28,'-');
    valorNumeros.put(29,'_');
    valorNumeros.put(30,'¿');
    valorNumeros.put(30,'¿');
    valorNumeros.put(31,'@');
    valorNumeros.put(32,'0');
    valorNumeros.put(33,'1');
    valorNumeros.put(34,'2');
    valorNumeros.put(35,'3');
    valorNumeros.put(36,'4');
    valorNumeros.put(37,'5');
    valorNumeros.put(38,'6');
    valorNumeros.put(39,'7');
    valorNumeros.put(40,'8');
    valorNumeros.put(41,'9');
    valorNumeros.put(42,'w');
    valorNumeros.put(43,':');
    valorNumeros.put(44,'.');
    valorNumeros.put(45,'*');
    
    

    
    int contador =0;
    while(contador<encrNum.size()){
    	textoEncriptado+=valorNumeros.get(encrNum.get(contador)).toString();
    	contador++;
    }
    
	return textoEncriptado;
}

public static int validarSumaEn(int suma){
	int aux=0;
	if(suma>45){
		aux=suma-45;
	}
	else{
		aux=suma;
	}
	return aux;
}

public static int validarSumaDes(int suma){
	int aux=0;
	if(suma<0){
		aux=suma+45;
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

public  static String codificar(String texto){
	ArrayList<Integer> listaEn = encriptaNumero(texto, "tango");
	String encript = deNumeroATexto(listaEn);
	return encript;
}

public static String decodificar(String texto){
	ArrayList<Integer> listaDes = desencriptaNumero(texto, "tango");
	String desencript = deNumeroATexto(listaDes);
	return desencript;
}
public static void main (String [ ] args) {
	
	//System.out.println("Texto: "+"¿comes Queso por la tarde?");
	//System.out.println("Encriptación");
	//ArrayList<Integer> listaEn = encriptaNumero("¿comes Queso por la tarde toda la manana y por toda la semana lalalala?", "turo");
	//String encript = deNumeroATexto(listaEn);
	//String prueba=codificar("Se ha eliminado el curso:");
	//System.out.println(codificar(prueba));
	
	
//	System.out.println("Desencriptación");
	//ArrayList<Integer> listaDes = desencriptaNumero(encript, "turo");
	//String desencript = deNumeroATexto(listaDes);
	System.out.println(decodificar("i1l9gr6h"));
	//System.out.println(decodificar("7f -b ut?n_ue_ ym ?v0zÂ¿r 1jsys_uo um 4s_mt7p0 0v?zy3ts8ei8*n-ujzfr3n"));
	//System.out.println(decodificar("m6f"));
	//System.out.println(decodificar("7f -b ym?tx2brv ym ?v0zÂ¿r m6f"));
}
}
