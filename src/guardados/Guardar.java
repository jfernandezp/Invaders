package guardados;

import invaders.estado.EstadoDefecto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;

import Valide.Valide;
import Valide.ValideException;

public class Guardar {
	public Guardar(File fichero, EstadoDefecto estadoAnterior) {
		try {
			String ruta = Valide.valideFilename(fichero.toString(),"json");
			fichero = new File(ruta);
			escribirJSON(fichero,estadoAnterior);
		} catch (ValideException e2) {
			// TODO Bloque catch generado automáticamente
			e2.printStackTrace();
		}
    }

	@SuppressWarnings("unchecked")
	private void escribirJSON(File fichero, EstadoDefecto estadoAnterior) {
	JSONObject obj = new JSONObject();
		
	    obj.put("jugador", "jugador");
	    obj.put("nivel", Integer.toString(estadoAnterior.getNivel()));
	    obj.put("vidas", Integer.toString(estadoAnterior.getVidas()));
	    obj.put("maxVidas", Integer.toString(estadoAnterior.getMaxVidas()));
	    obj.put("puntos", Integer.toString(estadoAnterior.getPuntos()));
	    obj.put("velocidad", Integer.toString(estadoAnterior.getVelocidad()));
	    obj.put("disparoCercano", Integer.toString(estadoAnterior.getDisparoCercano()));
	    obj.put("disparoEstructura", Integer.toString(estadoAnterior.getDisparoEstructura()));
	    obj.put("disparoAzar", Integer.toString(estadoAnterior.getDisparoAzar()));
	    	 
	    FileWriter file;
		try {
			file = new FileWriter(fichero);
		    try {
		        file.write(obj.toJSONString());	
		        System.out.println("Nivel creado");
	        } catch (IOException e) {
	 
	        } finally {
	            file.flush();
	            file.close();
	        }		    
		} catch (IOException e1) {
		}
	}
}