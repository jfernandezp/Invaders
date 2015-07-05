package invaders.graficos;

import java.awt.Color;

public class Cuadro {
	protected int posx, posy, width, height;
	protected Color color, colorText, colorLine;
	protected String texto;
	protected boolean activo = false;
	protected Boton[] botones;
	
	public Cuadro(){
		this.color = new Color(31,31,31);
		this.colorLine = Color.RED;
		this.colorText = Color.WHITE;
	}

	public Cuadro(int posx, int posy, int tx, int ty, String texto){
		this();
		this.posx = posx;
		this.posy = posy;
		this.width = tx;
		this.height = ty;
		this.texto = texto;
		this.setActivo(false);
	}

	public int getWidth() {
		return width;
	}

	public int getPosy() {
		return posy;
	}

	public int getPosx() {
		return posx;
	}

	public int getHeight() {
		return height;
	}
	
	public Color getColor(){
		return color;
	}
	
	public Color getColorLine(){
		return colorLine;
	}
	
	public Color getColorText(){
		return colorText;
	}
	
	public String getTexto(){
		return texto;
	}
	
	public void setActivo(boolean b){
		activo = b;
	}

	public boolean isActivo() {
		return activo;
	}

	public void switchActivo() {
		if(isActivo()){
			setActivo(false);
		} else {
			setActivo (true);
		}
	}	
}
