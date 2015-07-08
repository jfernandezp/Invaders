package invaders.estado;

public class EstadoDefecto {
	
	protected int puntos, nivel, vidas, maxVidas, velocidad, disparoCercano, disparoEstructura, disparoAzar;
	protected EstadoDefecto estadoAnterior;;

	public EstadoDefecto(){
		estadoAnterior = this;
		nivel = 1;
		vidas = 3;
		maxVidas = 6;
		velocidad = 10;
		disparoCercano = 10;
		disparoEstructura = 20;
		disparoAzar = 30;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getVidas() {
		return vidas;
	}

	public void setVidas(int vidas) {
		this.vidas = vidas;
	}

	public int getMaxVidas() {
		return maxVidas;
	}

	public void setMaxVidas(int maxVidas) {
		this.maxVidas = maxVidas;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public int getDisparoCercano() {
		return disparoCercano;
	}

	public void setDisparoCercano(int disparoCercano) {
		this.disparoCercano = disparoCercano;
	}

	public int getDisparoEstructura() {
		return disparoEstructura;
	}

	public void setDisparoEstructura(int disparoEstructura) {
		this.disparoEstructura = disparoEstructura;
	}

	public int getDisparoAzar() {
		return disparoAzar;
	}

	public void setDisparoAzar(int disparoAzar) {
		this.disparoAzar = disparoAzar;
	}

}
