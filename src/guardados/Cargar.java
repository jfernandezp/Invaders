package guardados;

import invaders.estado.EstadoDefecto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
	private JSONObject jsonObject;
	
	Cargar(){
		valoresPorDefecto();
	}

	public Cargar(File fichero) {
		this();
		iniciarCarga(fichero);
	}
	
	
	public Cargar(String fichero) {
		this();
		iniciarCarga(fichero);
	}
	
	private void valoresPorDefecto() {
		EstadoDefecto estadoDefecto = new EstadoDefecto();
		jugador = "Invaders";
		setNivel(estadoDefecto.getNivel());
		setVidas(estadoDefecto.getVidas());
		setMaxVidas(estadoDefecto.getMaxVidas());
		setVelocidad(estadoDefecto.getVelocidad());
		setDisparoCercano(estadoDefecto.getDisparoCercano());
		setDisparoEstructura(estadoDefecto.getDisparoEstructura());
		setDisparoAzar(estadoDefecto.getDisparoAzar());
	}
	
	public void iniciarCarga(String fichero) {
		JSONParser parser = new JSONParser();
		
		try { 	 
			Object obj = parser.parse(new FileReader(fichero));
	 
			jsonObject = (JSONObject) obj;
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado. Cargando datos por defecto");
		} catch (IOException e) {
			System.out.println("Error de entrada o salida. Cargando datos por defecto");
		} catch (ParseException e) {
			System.out.println("Error al interpretar el fichero. Cargando datos por defecto");
		}
		
		obtenerDatosFichero();		
	}

	public void iniciarCarga(File fichero){
		JSONParser parser = new JSONParser();
		try {
			try {			 
				Object obj = parser.parse(new FileReader(fichero));
		 
				jsonObject = (JSONObject) obj;
			} catch (FileNotFoundException e) {
				System.out.println("Fichero no encontrado. Cargando datos por defecto");
			} catch (IOException e) {
				System.out.println("Error de entrada o salida. Cargando datos por defecto");
			} catch (ParseException e) {
				System.out.println("Error al interpretar el fichero. Cargando datos por defecto");
			}
			obtenerDatosFichero();	
		} catch (NullPointerException e){
			
		}
		
			 
    }
	
	private void obtenerDatosFichero(){
		try{
			jugador = (String) jsonObject.get("jugador");
			nivel =  Integer.parseInt((String) jsonObject.get("nivel"));
			vidas = Integer.parseInt((String)  jsonObject.get("vidas"));
			maxVidas = Integer.parseInt((String)  jsonObject.get("maxVidas"));
			puntos = Integer.parseInt((String)  jsonObject.get("puntos"));
			velocidad = Integer.parseInt((String)  jsonObject.get("velocidad"));
			disparoCercano = Integer.parseInt((String)  jsonObject.get("disparoCercano"));
			disparoEstructura = Integer.parseInt((String)  jsonObject.get("disparoEstructura"));
			disparoAzar = Integer.parseInt((String)  jsonObject.get("disparoAzar"));
		} catch (NullPointerException e){
			
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