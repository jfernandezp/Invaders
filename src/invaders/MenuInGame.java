package invaders;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class MenuInGame {
	Boton[] elementos;
	private Dialogo dialogo;
	MenuInGame(int ventanatx, int ventanaty){
	    	//Pausa = 0
			//Guardar = 1
	    	//Salir al menú = 2
	    	
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
	    	int widthDialogo = 395;
	    	int heightDialogo = 100;
	    	String textoDialogo = "¿De verdad quieres salir del juego e ir al menú?";
	    	dialogo = new Dialogo(ventanatx / 2 - widthDialogo / 2, ventanaty / 2 - heightDialogo / 2,
	    			widthDialogo, heightDialogo, textoDialogo);
	}
	
	private class Boton{
		private int posx, posy, width, height;
		private String texto;
		private Color color, colorLine, colorText;
		private boolean activo = false;
		
		Boton(int posx, int posy, int tx, int ty, String texto){
			this.posx = posx;
			this.posy = posy;
			this.width = tx;
			this.height = ty;
			this.texto = texto;
			this.setActivo(false);
		}
		
		void setActivo(boolean b){
			activo = b;
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

		int getWidth() {
			return width;
		}

		int getPosy() {
			return posy;
		}

		int getPosx() {
			return posx;
		}

		int getHeight() {
			return height;
		}
		
		Color getColor(){
			return color;
		}
		
		Color getColorLine(){
			return colorLine;
		}
		
		Color getColorText(){
			return colorText;
		}
		
		String getTexto(){
			return texto;
		}

		boolean getActivo() {
			return activo;
		}		
	}
	
	private class Dialogo{
		private int posx, posy, width, height;
		private Color color, colorText, colorLine;
		private String texto;
		private boolean activo = false;
		
		Dialogo(){
			setActivo(false);
			this.color = new Color(31,31,31);
			this.colorLine = Color.RED;
			this.colorText = Color.WHITE;
		}
		Dialogo(int x, int y, int w, int h, String txt){
			this();
			posx = x;
			posy = y;
			width = w;
			height = h;
			texto = txt;		
		}
		
		int getWidth() {
			return width;
		}

		int getPosy() {
			return posy;
		}

		int getPosx() {
			return posx;
		}

		int getHeight() {
			return height;
		}
		
		Color getColor(){
			return color;
		}
		
		Color getColorLine(){
			return colorLine;
		}
		
		Color getColorText(){
			return colorText;
		}
		
		String getTexto(){
			return texto;
		}
		public boolean isActivo() {
			return activo;
		}
		public void setActivo(boolean activo) {
			this.activo = activo;
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
		if (dialogo.isActivo()){
			System.out.println("esta activo");
			g.setColor(dialogo.getColor());
			g.fillRect(dialogo.getPosx(), dialogo.getPosy(), dialogo.getWidth(), dialogo.getHeight());
			g.setColor(dialogo.getColorLine());
			g.drawRect(dialogo.getPosx(),dialogo.getPosy(),dialogo.getWidth(),dialogo.getHeight());
			g.setColor(dialogo.getColorText());
	        g2.drawString(dialogo.getTexto(), dialogo.getPosx() + 20, dialogo.getPosy() + 20);
		}
	}
	
	private int buscarActivo(){
		boolean encontrado = false;
		int indice = 0;
		for (int i = 0; i < elementos.length && !encontrado; i++){
			if (elementos[i].getActivo()){
				encontrado = true;
				indice = i;
			}
		}
		return indice;
	}

	public void pulsoPausa() {
//		elementos[buscarActivo()].setActivo(false);
//		elementos[0].setActivo(true);	
	}

	public void pulsoPausa(boolean pausa) {
		elementos[0].setActivo(pausa);
		
	}

	public void pulsoGuardar() {
//		elementos[buscarActivo()].setActivo(false);
//		elementos[1].setActivo(true);		
	}

	public void pulsoMenu() {
//		elementos[buscarActivo()].setActivo(false);
		elementos[2].setActivo(true);
		dialogo.setActivo(true);
		
	}

	
	//Sin uso
	public void OpcionDerecha() {
		int activo = buscarActivo();
		elementos[activo].setActivo(false);
		activo = elementoDerecha(activo);
		elementos[activo].setActivo(true);
	}	
	//Sin uso
	private int elementoDerecha(int activo) {
		activo++;
		if (activo > elementos.length){
			activo = 0;
		}
		return activo;
	}
}
