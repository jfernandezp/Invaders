package guardados;

import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
 


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import consoleUI.UtilsUI;

public class Guardar {
//	 @SuppressWarnings("unchecked")
	    public static void main(String[] args) throws IOException {
	    	
	    	String jugador = UtilsUI.getConsole("Jugador:");
	    	int nivel = UtilsUI.getConsoleInt("Nivel:");
	    	int vidas = UtilsUI.getConsoleInt("Vidas:");
	    	int maxVidas = UtilsUI.getConsoleInt("Máximo de vidas:");
	    	int puntos = UtilsUI.getConsoleInt("Número de puntos:");
	    	int velocidad = UtilsUI.getConsoleInt("Velocidad:");
	    	int disparoCercano = UtilsUI.getConsoleInt("Disparo Cercano:");
	    	int disparoEstructura = UtilsUI.getConsoleInt("Disparo Estructura:");
	    	int disparoAzar = UtilsUI.getConsoleInt("Disparo Azar:");
	    	String nombreArchivo = UtilsUI.getConsoleFilename("Fichero de guardado:", "json");
	    	
	        JSONObject obj = new JSONObject();
//	        obj.put("jugador", jugador);
//	        obj.put("nivel", nivel);
//	        obj.put("vidas", vidas);
//	        obj.put("maxVidas", maxVidas);
//	        obj.put("velocidad", velocidad);
//	        obj.put("disparoCercano", disparoCercano);
//	        obj.put("disparoEstructura", disparoEstructura);
//	        obj.put("disparoAzar", disparoAzar);
	    	
	        obj.put("jugador", "jugador");
	        obj.put("nivel", Integer.toString(nivel));
	        obj.put("vidas", Integer.toString(vidas));
	        obj.put("maxVidas", Integer.toString(maxVidas));
	        obj.put("puntos", Integer.toString(puntos));
	        obj.put("velocidad", Integer.toString(velocidad));
	        obj.put("disparoCercano", Integer.toString(disparoCercano));
	        obj.put("disparoEstructura", Integer.toString(disparoEstructura));
	        obj.put("disparoAzar", Integer.toString(disparoAzar));
	        	 
	        FileWriter file = new FileWriter("niveles/"+nombreArchivo);
	        try {
	            file.write(obj.toJSONString());	
	            System.out.println("Nivel creado");
	        } catch (IOException e) {
	            e.printStackTrace();
	 
	        } finally {
	            file.flush();
	            file.close();
	        }
	    }
}
