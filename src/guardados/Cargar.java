package guardados;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Cargar {
	static String jugador;
	private static int nivel;
	private static int vidas;
	private static int maxVidas;
	private static int velocidad;
	private static int puntos;
	private static int disparoCercano;
	private static int disparoEstructura;
	private static int disparoAzar;
	
	public Cargar(String fichero) {
		jugador = "Invaders";
		setNivel(1);
		setVidas(3);
		setMaxVidas(6);
		setVelocidad(10);
		setDisparoCercano(10);
		setDisparoEstructura(20);
		setDisparoAzar(30);
				
		iniciarCarga(fichero);
	}
	public static void iniciarCarga(String fichero){
		JSONParser parser = new JSONParser();
		try {
			 
			Object obj = parser.parse(new FileReader("niveles/"+fichero));
	 
			JSONObject jsonObject = (JSONObject) obj;
	 
			
			jugador = (String) jsonObject.get("jugador");
			nivel =  Integer.parseInt((String) jsonObject.get("nivel"));
			vidas = Integer.parseInt((String)  jsonObject.get("vidas"));
			maxVidas = Integer.parseInt((String)  jsonObject.get("maxVidas"));
			puntos = Integer.parseInt((String)  jsonObject.get("puntos"));
			velocidad = Integer.parseInt((String)  jsonObject.get("velocidad"));
			disparoCercano = Integer.parseInt((String)  jsonObject.get("disparoCercano"));
			disparoEstructura = Integer.parseInt((String)  jsonObject.get("disparoEstructura"));
			disparoAzar = Integer.parseInt((String)  jsonObject.get("disparoAzar"));	

		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado. Cargando datos por defecto");
		} catch (IOException e) {
			System.out.println("Error de entrada o salida. Cargando datos por defecto");
		} catch (ParseException e) {
			System.out.println("Error al interpretar el fichero. Cargando datos por defecto");
		}
	 
     }
	public static int getVidas() {
		return vidas;
	}
	public static void setVidas(int vidas) {
		Cargar.vidas = vidas;
	}
	public static int getDisparoAzar() {
		return disparoAzar;
	}
	public static void setDisparoAzar(int disparoAzar) {
		Cargar.disparoAzar = disparoAzar;
	}
	public static int getNivel() {
		return nivel;
	}
	public static void setNivel(int nivel) {
		Cargar.nivel = nivel;
	}
	public static int getVelocidad() {
		return velocidad;
	}
	public static void setVelocidad(int velocidad) {
		Cargar.velocidad = velocidad;
	}
	public static int getDisparoEstructura() {
		return disparoEstructura;
	}
	public static void setDisparoEstructura(int disparoEstructura) {
		Cargar.disparoEstructura = disparoEstructura;
	}
	public static int getMaxVidas() {
		return maxVidas;
	}
	public static void setMaxVidas(int maxVidas) {
		Cargar.maxVidas = maxVidas;
	}
	public static int getDisparoCercano() {
		return disparoCercano;
	}
	public static void setDisparoCercano(int disparoCercano) {
		Cargar.disparoCercano = disparoCercano;
	}
	public int getPuntos() {
		return puntos;
	}
}