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
	
	
	
	public void agregarCaraASet(String pouter_id, String pface_token) {	// No tocar
		String url = "https://api-us.faceplusplus.com/facepp/v3/faceset/addface";
        HashMap<String, String> map = new HashMap<>();
        HashMap<String, byte[]> byteMap = new HashMap<>();
        map.put("api_key", "shF3UeJjLcJNA942teve_lrDrfmns6VJ");
        map.put("api_secret", "uHX37EkOZijogZ7qLgaJM1wMcXQCipp_");
        map.put("outer_id", pouter_id);
        map.put("face_tokens", pface_token);

        try{
            byte[] bacd = post(url, map, byteMap);
            String str = new String(bacd);
            System.out.println(str);
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
        map.put("display_name", "Personas del Sistema");
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
        map.put("display_name", "Personas del Sistema");
        map.put("outer_id", nombreSet);
        map.put("check_empty", "0");
        try{
            byte[] bacd = post(url, map, byteMap);
            String str = new String(bacd);
            System.out.println(str);
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
//            String str = new String(bacd);
            //System.out.println(str);
        }catch (Exception e) {
        	e.printStackTrace();
		}
	}
	
	public void agregarImagenEstudiante_Servicio(String direccion,String nuevo_id) throws JSONException, InterruptedException {
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
        agregarCaraASet("Personas del Sistema", face_token);
        Thread.sleep(3000);
	}
	
	
	
	
	public String buscarEstudiante(String imagenB64, String n) throws JSONException {
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
            System.out.println(map);
        }catch (Exception e) {
        	e.printStackTrace();
		}
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
        	id = null;
            System.out.println("La confianza obtenida en el Reconocimiento Cognitivo de la aplicación determina que no está habilitado para realizar la prueba");
        }
        return id;
	}
	
	
	
	
	
	
	
	public String buscarEstudiante(String direccion) throws JSONException {
		String str = "";
		String url = "https://api-us.faceplusplus.com/facepp/v3/search";
        File file = new File(direccion);
		byte[] buff = getBytesFromFile(file);
		HashMap<String, String> map = new HashMap<>();
        HashMap<String, byte[]> byteMap = new HashMap<>();
        map.put("api_key", "shF3UeJjLcJNA942teve_lrDrfmns6VJ");
        map.put("api_secret", "uHX37EkOZijogZ7qLgaJM1wMcXQCipp_");
        byteMap.put("image_file", buff);
        map.put("outer_id", "Personas del Sistema");
        try{
            byte[] bacd = post(url, map, byteMap);
            str = new String(bacd);
        }catch (Exception e) {
        	e.printStackTrace();
		}
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
        	id = null;
            System.out.println("La confianza obtenida en el Reconocimiento Cognitivo de la aplicación determina que no está habilitado para realizar la prueba");
        }
        return id;
	}


	
//	public static void main(String[] args) throws Exception{
//	ServicioReconocimientoFacial test = new ServicioReconocimientoFacial();
//	//test.crearSetImagenes("Personas del Sistema");
////test.borrarSetImagenes("Personas del Sistema");
////test.agregarImagenEstudiante_Servicio("C:\\Users\\HP\\Downloads\\sergio.jpg", "Sergio Arroyo");
////	test.agregarImagenEstudiante_Servicio("C:\\Users\\HP\\Downloads\\jose andres.jpg", "Jose Estrada");
////	test.agregarImagenEstudiante_Servicio("C:\\Users\\HP\\Downloads\\luis.jpg", "Luis Bolaños");
////	test.agregarImagenEstudiante_Servicio("C:\\Users\\HP\\Downloads\\juan bautista.jpg", "Juan Bautista");
//	//<test.agregarImagenEstudiante_Servicio("C:\\Users\\HP\\Downloads\\luis chavarria.jpg", "Luis Chavarria");
////	test.agregarImagenEstudiante_Servicio("C:\\Users\\HP\\Downloads\\juan jose.jpg", "Juan Varela");
//
////	test.crearSetImagenes();
////	test.buscarEstudiante("C:\\Users\\HP\\Downloads\\test2.jpg");
////	String imagenB64 = "/9j/4AAQSkZJRgABAQEAYABgAAD/4RDqRXhpZgAATU0AKgAAAAgABAE7AAIAAAALAAAISodpAAQAAAABAAAIVpydAAEAAAAUAAAQzuocAAcAAAgMAAAAPgAAAAAc6gAAAAgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAEp1YW4gSm9zw6kAAAAFkAMAAgAAABQAABCkkAQAAgAAABQAABC4kpEAAgAAAAM1NAAAkpIAAgAAAAM1NAAA6hwABwAACAwAAAiYAAAAABzqAAAACAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAMjAxNzowNDoyNSAxODo1MTozNQAyMDE3OjA0OjI1IDE4OjUxOjM1AAAASgB1AGEAbgAgAEoAbwBzAOkAAAD/4QsdaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wLwA8P3hwYWNrZXQgYmVnaW49J++7vycgaWQ9J1c1TTBNcENlaGlIenJlU3pOVGN6a2M5ZCc/Pg0KPHg6eG1wbWV0YSB4bWxuczp4PSJhZG9iZTpuczptZXRhLyI+PHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj48cmRmOkRlc2NyaXB0aW9uIHJkZjphYm91dD0idXVpZDpmYWY1YmRkNS1iYTNkLTExZGEtYWQzMS1kMzNkNzUxODJmMWIiIHhtbG5zOmRjPSJodHRwOi8vcHVybC5vcmcvZGMvZWxlbWVudHMvMS4xLyIvPjxyZGY6RGVzY3JpcHRpb24gcmRmOmFib3V0PSJ1dWlkOmZhZjViZGQ1LWJhM2QtMTFkYS1hZDMxLWQzM2Q3NTE4MmYxYiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIj48eG1wOkNyZWF0ZURhdGU+MjAxNy0wNC0yNVQxODo1MTozNS41Mzg8L3htcDpDcmVhdGVEYXRlPjwvcmRmOkRlc2NyaXB0aW9uPjxyZGY6RGVzY3JpcHRpb24gcmRmOmFib3V0PSJ1dWlkOmZhZjViZGQ1LWJhM2QtMTFkYS1hZDMxLWQzM2Q3NTE4MmYxYiIgeG1sbnM6ZGM9Imh0dHA6Ly9wdXJsLm9yZy9kYy9lbGVtZW50cy8xLjEvIj48ZGM6Y3JlYXRvcj48cmRmOlNlcSB4bWxuczpyZGY9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkvMDIvMjItcmRmLXN5bnRheC1ucyMiPjxyZGY6bGk+SnVhbiBKb3PDqTwvcmRmOmxpPjwvcmRmOlNlcT4NCgkJCTwvZGM6Y3JlYXRvcj48L3JkZjpEZXNjcmlwdGlvbj48L3JkZjpSREY+PC94OnhtcG1ldGE+DQogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgIDw/eHBhY2tldCBlbmQ9J3cnPz7/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";
////	test.buscarEstudiante(imagenB64,"");
//	}


}

  