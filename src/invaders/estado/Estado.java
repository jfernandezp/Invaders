package invaders.estado;

public class Estado extends EstadoDefecto{
	
	int puntos;
	int limitey;
		
	private boolean finJuego = false;
	private boolean pausa = false;
	
	private int entorno = 0; //0 = normal, 1 = test
	
	
	public Estado(){
	}
	
	public Estado(int velocidad, int disparoCercano, int disparoEstructura, int disparoAzar, int limitey){
		puntos = 0;
		nivel = 1;
		vidas = 3;
		maxVidas = 6;
		this.velocidad = velocidad;
		this.disparoCercano = disparoCercano;
		this.disparoEstructura = disparoEstructura;
		this.disparoAzar = disparoAzar;
		this.limitey = limitey;
	}
	
	Estado(int limitey){
		puntos = 0;
		nivel = 1;
		vidas = 3;
		maxVidas = 6;
		velocidad = 10;
		disparoCercano = 10;
		disparoEstructura = 20;
		disparoAzar = 30;
		this.limitey = limitey;
	}

	public Estado(int nivel, int vidas, int maxVidas, int puntos, int velocidad,
			int disparoCercano, int disparoEstructura, int disparoAzar) {
		this.puntos = puntos;
		this.nivel = nivel;
		this.vidas = vidas;
		this.maxVidas = maxVidas;
		this.velocidad = velocidad;
		this.disparoCercano = disparoCercano;
		this.disparoEstructura = disparoEstructura;
		this.disparoAzar = disparoAzar;
	}
	
	public void setLimiteY(int limitey){
		this.limitey = limitey;
	}

	public void ganaPuntos(int data){
		float ponderadoNivel = 1 + ((float) nivel-1)/10;
		if (data > 0){
			puntos += 10 * ponderadoNivel;
		} else {
			puntos -= 5  * ponderadoNivel;
		}
	}
	
	public void aumentarNivel(){
		nivel++;
		if (nivel <= 10){
			aumentarDificultad();
		}
	}
	
	private void aumentarDificultad(){
		float ponderadoNivelA = 1 + ((float) nivel-1)/10;
		float ponderadoNivelR = 1 - ((float) nivel-1)/10;
		velocidad = (int) (velocidad * ponderadoNivelA);
		disparoCercano = (int) (disparoCercano * ponderadoNivelR) ; 
		disparoEstructura = (int) (disparoEstructura * ponderadoNivelR) ;
		disparoAzar = (int) (disparoAzar * ponderadoNivelR) ;	
	}
	
	public void ganaVida(int data){
		int nVidas = vidas + data;
		if (nVidas <= maxVidas){
			vidas = nVidas;
		} else {
			vidas = maxVidas;
		}
	}
	
	public void pierdeVida(int data){
		vidas -= data;
		if (vidas < 1){
			finJuego = true;
		}
	}
	
	public void setFinJuego(boolean fin){
		finJuego = fin;
	}
	
	public boolean getFinJuego(){
		return finJuego;
	}
	
	public void setPausa(boolean data){
		pausa = data;
	}
	
	public boolean getPausa(){
		return pausa;
	}
	
	public int getEntorno(){
		return entorno;
	}
}