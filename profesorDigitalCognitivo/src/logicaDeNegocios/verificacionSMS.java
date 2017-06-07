package logicaDeNegocios;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.*;
 
public class verificacionSMS {
	public void enviarSms(String numero, String codigo) {
		try {
			// Construct data
			String usuario = "username=" + "jose.andresbg@gmail.com";
			String hash = "&hash=" + "0074a3b905cac06f8443e8695229d5cbb1744b4e24b55824d744d1bed544a7b7";
			String mensaje = "&message=" + codigo;
			String emisor = "&sender=" + "Profesor C.";
			String telefono = "&numbers=" + "+506"+numero;
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
			String data = usuario + hash + telefono + mensaje + emisor;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();
			
		} catch (Exception e) {
		}
	}
	public String generarPIN(){
	        int randomPIN = (int)(Math.random()*9000)+1000;
	        String PINString = String.valueOf(randomPIN);
	        return PINString;
	}
	}