package invaders;

public class Estado {
	
	int puntos;
	int nivel;
	int vidas;
	int maxVidas;
	int velocidad;
	
	private int disparoCercano;
	private int disparoEstructura;
	private int disparoAzar;
	
	private boolean finJuego = false;
	private boolean pausa = false;
	
	
	Estado(){
		puntos = 0;
		nivel = 1;
		vidas = 3;
		maxVidas = 6;
		velocidad = 10;
		disparoCercano = 10;
		disparoEstructura = 20;
		disparoAzar = 30;
	}
	
	Estado(int velocidad, int disparoCercano, int disparoEstructura, int disparoAzar){
		puntos = 0;
		nivel = 1;
		vidas = 3;
		maxVidas = 6;
		this.velocidad = velocidad;
		this.disparoCercano = disparoCercano;
		this.disparoEstructura = disparoEstructura;
		this.disparoAzar = disparoAzar;
	}

	public void ganaPuntos(int data){
		float ponderadoNivel = 1 + ((float) nivel-1)/10;
		System.out.println(ponderadoNivel);
		if (data > 0){
			puntos += 10 * ponderadoNivel;
		} else {
			puntos -= 2  * ponderadoNivel;
		}
	}
	
	public int getPuntos(){
		return puntos;
	}
	
	public int getNivel(){
		return nivel;
	}
	
	public void aumentarNivel(){
		nivel++;
		if (nivel <= 10){
			aumentarDificultad();
		}
	}
	
	private void aumentarDificultad(){
		float ponderadoNivelA = 1 + ((float) nivel-1)/5;
		float ponderadoNivelR = 1 - ((float) nivel-1)/5;
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
	
	public int getVidas(){
		return vidas;
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
	
	public void setVelocidad(int data){
		velocidad = data;
	}
	
	public int getVelocidad(){
		return velocidad;
	}
	
	public void setDisparoCercano(int data){
		disparoCercano = data;
	}
	
	public int getDisparoCercano(){
		return disparoCercano;
	}
	
	public void setDisparoAzar(int data){
		disparoAzar = data;
	}
	
	public int getDisparoAzar(){
		return disparoAzar;
	}
	
	public void setDisparoEstructura(int data){
		disparoEstructura = data;
	}
	
	public int getDisparoEstructura(){
		return disparoEstructura;
	}
}
