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
	String jugador;
	private int nivel;
	private int vidas;
	private int maxVidas;
	private int velocidad;
	private int puntos;
	private int disparoCercano;
	private int disparoEstructura;
	private int disparoAzar;
	
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
	public void iniciarCarga(String fichero){
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
	public int getVidas() {
		return vidas;
	}
	public void setVidas(int vidas) {
		this.vidas = vidas;
	}
	public int getDisparoAzar() {
		return disparoAzar;
	}
	public void setDisparoAzar(int disparoAzar) {
		this.disparoAzar = disparoAzar;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public int getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	public int getDisparoEstructura() {
		return disparoEstructura;
	}
	public void setDisparoEstructura(int disparoEstructura) {
		this.disparoEstructura = disparoEstructura;
	}
	public int getMaxVidas() {
		return maxVidas;
	}
	public void setMaxVidas(int maxVidas) {
		this.maxVidas = maxVidas;
	}
	public int getDisparoCercano() {
		return disparoCercano;
	}
	public void setDisparoCercano(int disparoCercano) {
		this.disparoCercano = disparoCercano;
	}
	public int getPuntos() {
		return puntos;
	}
}