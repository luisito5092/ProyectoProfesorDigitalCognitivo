package serviciosCognitivos;



import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import javax.net.ssl.SSLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ServicioReconocimientoFacial {
	
//// Ignorar	
private final static int CONNECT_TIME_OUT = 30000;
private final static int READ_OUT_TIME = 50000;

private static String boundaryString = getBoundary();

protected static byte[] post(String url, HashMap<String, String> map, HashMap<String, byte[]> fileMap) throws Exception {
        HttpURLConnection conne;
        URL url1 = new URL(url);
        conne = (HttpURLConnection) url1.openConnection();
        conne.setDoOutput(true);
        conne.setUseCaches(false);
        conne.setRequestMethod("POST");
        conne.setConnectTimeout(CONNECT_TIME_OUT);
        conne.setReadTimeout(READ_OUT_TIME);
        conne.setRequestProperty("accept", "*/*");
        conne.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundaryString);
        conne.setRequestProperty("connection", "Keep-Alive");
        conne.setRequestProperty("user-agent", "Mozilla/4.0 (compatible;MSIE 6.0;Windows NT 5.1;SV1)");
        DataOutputStream obos = new DataOutputStream(conne.getOutputStream());
        Iterator iter = map.entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry<String, String> entry = (Map.Entry) iter.next();
            String key = entry.getKey();
            String value = entry.getValue();
            obos.writeBytes("--" + boundaryString + "\r\n");
            obos.writeBytes("Content-Disposition: form-data; name=\"" + key
                    + "\"\r\n");
            obos.writeBytes("\r\n");
            obos.writeBytes(value + "\r\n");
        }
        if(fileMap != null && fileMap.size() > 0){
            Iterator fileIter = fileMap.entrySet().iterator();
            while(fileIter.hasNext()){
                Map.Entry<String, byte[]> fileEntry = (Map.Entry<String, byte[]>) fileIter.next();
                obos.writeBytes("--" + boundaryString + "\r\n");
                obos.writeBytes("Content-Disposition: form-data; name=\"" + fileEntry.getKey()
                        + "\"; filename=\"" + encode(" ") + "\"\r\n");
                obos.writeBytes("\r\n");
                obos.write(fileEntry.getValue());
                obos.writeBytes("\r\n");
            }
        }
        obos.writeBytes("--" + boundaryString + "--" + "\r\n");
        obos.writeBytes("\r\n");
        obos.flush();
        obos.close();
        InputStream ins = null;
        int code = conne.getResponseCode();
        try{
            if(code == 200){
                ins = conne.getInputStream();
            }else{
                ins = conne.getErrorStream();
            }
        }catch (SSLException e){
            e.printStackTrace();
            return new byte[0];
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buff = new byte[4096];
        int len;
        while((len = ins.read(buff)) != -1){
            baos.write(buff, 0, len);
        }
        byte[] bytes = baos.toByteArray();
        ins.close();
        return bytes;
    }

    private static String getBoundary() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < 32; ++i) {
            sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-".charAt(random.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_".length())));
        }
        return sb.toString();
    }

    private static String encode(String value) throws Exception{
        return URLEncoder.encode(value, "UTF-8");
    }
    
    public static byte[] getBytesFromFile(File f) {
        if (f == null) {
            return null;
        }
        try {
            FileInputStream stream = new FileInputStream(f);
            ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = stream.read(b)) != -1)
                out.write(b, 0, n);
            stream.close();
            out.close();
            return out.toByteArray();
        } catch (IOException e) {
        }
        return null;
	}	
	
////Ignorar lo de arriba
    
    
	public double compararImagen() throws JSONException { //No se ocupa
		String str = "";
        File file = new File("C:\\Users\\HP\\workspace\\PruebaVisualRecog\\britney\\b1.jpg");
		byte[] buff = getBytesFromFile(file);
        File file2 = new File("C:\\Users\\HP\\workspace\\PruebaVisualRecog\\aguilera\\c2.jpg");
        byte[] buff2 = getBytesFromFile(file2);
		String url = "https://api-us.faceplusplus.com/facepp/v3/compare";
        HashMap<String, String> map = new HashMap<>();
        HashMap<String, byte[]> byteMap = new HashMap<>();
        map.put("api_key", "shF3UeJjLcJNA942teve_lrDrfmns6VJ");
        map.put("api_secret", "uHX37EkOZijogZ7qLgaJM1wMcXQCipp_");
        byteMap.put("image_file1", buff);
        byteMap.put("image_file2", buff2);
        try{
            byte[] bacd = post(url, map, byteMap);
            str = new String(bacd);
            //System.out.println(str);
        }catch (Exception e) {
        	e.printStackTrace();
		}
        JSONObject res = new JSONObject(str);
        System.out.println(res.getDouble("confidence"));
        return res.getDouble("confidence");
	}
	
	
	
	public void agregarCaraASet(String nombreSet_outerid, String pface_token) {	// No tocar
		String url = "https://api-us.faceplusplus.com/facepp/v3/faceset/addface";
        HashMap<String, String> map = new HashMap<>();
        HashMap<String, byte[]> byteMap = new HashMap<>();
        map.put("api_key", "shF3UeJjLcJNA942teve_lrDrfmns6VJ");
        map.put("api_secret", "uHX37EkOZijogZ7qLgaJM1wMcXQCipp_");
        map.put("outer_id", nombreSet_outerid);
        map.put("face_tokens", pface_token);

        try{
            byte[] bacd = post(url, map, byteMap);
            String str = new String(bacd);
            System.out.println("Cara agregada");
        }catch (Exception e) {
        	e.printStackTrace();
		}
	}
	
	
	public void crearSetImagenes(String nombreSet) { //Si el set no está creado
		String url = "https://api-us.faceplusplus.com/facepp/v3/faceset/create";
        HashMap<String, String> map = new HashMap<>();
        HashMap<String, byte[]> byteMap = new HashMap<>();
        map.put("api_key", "shF3UeJjLcJNA942teve_lrDrfmns6VJ");
        map.put("api_secret", "uHX37EkOZijogZ7qLgaJM1wMcXQCipp_");
        map.put("display_name", nombreSet);
        map.put("outer_id", nombreSet);
        try{
            byte[] bacd = post(url, map, byteMap);
            String str = new String(bacd);
            System.out.println("Set agregado");
        }catch (Exception e) {
        	e.printStackTrace();
		}
	}

	
	public void getDetalleSet(String nombreSet) { //Si el set no está creado
		String url = "https://api-us.faceplusplus.com/facepp/v3/faceset/getdetail";
        HashMap<String, String> map = new HashMap<>();
        HashMap<String, byte[]> byteMap = new HashMap<>();
        map.put("api_key", "shF3UeJjLcJNA942teve_lrDrfmns6VJ");
        map.put("api_secret", "uHX37EkOZijogZ7qLgaJM1wMcXQCipp_");
        map.put("outer_id", nombreSet);
        try{
            byte[] bacd = post(url, map, byteMap);
            String str = new String(bacd);
            System.out.println(str);
        }catch (Exception e) {
        	e.printStackTrace();
		}
	}
	
	
	public void borrarSetImagenes(String nombreSet) { //Si el set no está creado
		String url = "https://api-us.faceplusplus.com/facepp/v3/faceset/delete";
        HashMap<String, String> map = new HashMap<>();
        HashMap<String, byte[]> byteMap = new HashMap<>();
        map.put("api_key", "shF3UeJjLcJNA942teve_lrDrfmns6VJ");
        map.put("api_secret", "uHX37EkOZijogZ7qLgaJM1wMcXQCipp_");
        map.put("outer_id", nombreSet);
        map.put("check_empty", "0");
        try{
            byte[] bacd = post(url, map, byteMap);
            String str = new String(bacd);
            System.out.println("Set borrado");
        }catch (Exception e) {
        	e.printStackTrace();
		}
     
	}
	
	
	public void setIdImagenEstudiante(String pface_token, String pnuevo_id) {
		String url = "https://api-us.faceplusplus.com/facepp/v3/face/setuserid";
        HashMap<String, String> map = new HashMap<>();
        HashMap<String, byte[]> byteMap = new HashMap<>();
        map.put("api_key", "shF3UeJjLcJNA942teve_lrDrfmns6VJ");
        map.put("api_secret", "uHX37EkOZijogZ7qLgaJM1wMcXQCipp_");
        map.put("face_token", pface_token);
        map.put("user_id", pnuevo_id);
        try{
            byte[] bacd = post(url, map, byteMap);
            System.out.println("Id Imagen actualizado");
            //            String str = new String(bacd);
            //System.out.println(str);
        }catch (Exception e) {
        	e.printStackTrace();
		}

	}
	
	public void agregarImagenEstudiante_Servicio(String direccion,String nuevo_id, String nombreSet) throws JSONException, InterruptedException {
		String str = "";
		String face_token = "";
		String url = "https://api-us.faceplusplus.com/facepp/v3/detect";
        File file = new File(direccion);
		byte[] buff = getBytesFromFile(file);
		HashMap<String, String> map = new HashMap<>();
        HashMap<String, byte[]> byteMap = new HashMap<>();
        map.put("api_key", "shF3UeJjLcJNA942teve_lrDrfmns6VJ");
        map.put("api_secret", "uHX37EkOZijogZ7qLgaJM1wMcXQCipp_");
        byteMap.put("image_file", buff);
        try{
            byte[] bacd = post(url, map, byteMap);
            str = new String(bacd);
            //System.out.println(str);
        }catch (Exception e) {
        	e.printStackTrace();
		}
        JSONObject res = new JSONObject(str);
        System.out.println(res);
        String faces = res.getString("faces");
        JSONArray jArray1 = new JSONArray(faces);
        JSONObject object3 = jArray1.getJSONObject(0);
        face_token = object3.getString("face_token");
        System.out.println(face_token);
        Thread.sleep(3000);
        setIdImagenEstudiante(face_token, nuevo_id);
        Thread.sleep(3000);
        agregarCaraASet(nombreSet, face_token);
        Thread.sleep(3000);
        System.out.println("FIN. Cara agregada al Set");
	}
	
	
	
	
	public String buscarEstudiante(String imagenB64) throws JSONException {
		String str = "";
		String url = "https://api-us.faceplusplus.com/facepp/v3/search";
		HashMap<String, String> map = new HashMap<>();
        HashMap<String, byte[]> byteMap = new HashMap<>();
        map.put("api_key", "shF3UeJjLcJNA942teve_lrDrfmns6VJ");
        map.put("api_secret", "uHX37EkOZijogZ7qLgaJM1wMcXQCipp_");
        map.put("image_base64", imagenB64);
        map.put("outer_id", "Personas del Sistema");
        try{
            byte[] bacd = post(url, map, byteMap);
            str = new String(bacd);
            JSONObject response = new JSONObject(str);
            String results = response.getString("results");
            JSONArray jArray1 = new JSONArray(results);
            JSONObject resultado = jArray1.getJSONObject(0);
            double confianza = resultado.getDouble("confidence");
            String id = resultado.getString("user_id");
            if (confianza >= 60) {
                System.out.println(id+" , "+confianza);
            }
            else {
                System.out.println("La confianza obtenida en el Reconocimiento Cognitivo de la aplicación determina que no está habilitado para realizar la prueba");
            	return null;
            }
            return id;
        }catch (Exception e) {
        	e.printStackTrace();
        	return null;
		}
	}
}

  