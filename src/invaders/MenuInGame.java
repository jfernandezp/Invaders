package invaders;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class MenuInGame {
	Boton[] elementos;
	MenuInGame(int ventanatx){
	    	//Pausa
	    	//Salir al menú
	    	//Guardar
	    	//reiniciar 
	    	
	    	int posx, posy;
	    	int tx, ty;
	    	int numeroBotones = 3;
	    	tx = 130;
	    	ty = 30;
	    	elementos = new Boton[numeroBotones];
	    	String[] textos = {"Pausa/Iniciar (P)", "Guardar (G)", "Ir al menú (M)"};
	    	for (int i = 0; i < elementos.length; i++){
	    		posx = ventanatx / 4 * (i + 1) - tx / 2;
				posy = 10;
				elementos[i] = new Boton(posx,posy,tx,ty,textos[i]);
	    	}
	    	elementos[0].setActivo(true);
	}
	
	private class Boton{
		private int posx, posy, width, height;
		private String texto;
		private Color color, colorLine, colorText;
		
		Boton(int posx, int posy, int tx, int ty, String texto){
			this.posx = posx;
			this.posy = posy;
			this.width = tx;
			this.height = ty;
			this.texto = texto;
			this.setActivo(false);
		}
		
		public void setActivo(boolean b){
			if (b){
				this.color = Color.WHITE;
				this.colorLine = Color.RED;
				this.colorText = Color.BLACK;
			} else {
				this.color = new Color(31,31,31);
				this.colorLine = Color.RED;
				this.colorText = Color.WHITE;
			}
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
		
	}

	public void pintarMenu(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
    	int fontSize = 15;
		Font f = new  Font ("SansSerif", Font.BOLD, fontSize); 
        g2.setFont(f);
		for (int i = 0; i < elementos.length; i++){
			g.setColor(elementos[i].getColor());
			g.fillRect(elementos[i].getPosx(),elementos[i].getPosy(),elementos[i].getWidth(),elementos[i].getHeight());
			g.setColor(elementos[i].getColorLine());
			g.drawRect(elementos[i].getPosx(),elementos[i].getPosy(),elementos[i].getWidth(),elementos[i].getHeight());
			g.setColor(elementos[i].getColorText());
	        g2.drawString(elementos[i].getTexto(), elementos[i].getPosx() + 10, elementos[i].getPosy() + 20);
		}
		
	}
}
