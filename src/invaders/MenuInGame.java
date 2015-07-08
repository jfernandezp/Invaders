package invaders;

import invaders.graficos.Boton;
import invaders.graficos.Cuadro;
import invaders.graficos.Dialogo;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;

import javax.swing.JFileChooser;

public class MenuInGame {
	Cuadro[] elementos;

	private Cuadro dialogo;
	private InvaderPanel controlador;

	private boolean pierdeVida;

	private Cuadro btnPierdeVida;

	private boolean finJuego;

	private Cuadro btnFinJuego;
	MenuInGame(InvaderPanel controladorW, int ventanatx, int ventanaty){

	    	//Pausa = 0
			//Guardar = 1
	    	//Salir al menú = 2
	    	
	    	int posx, posy;
	    	int tx, ty;
	    	int numeroBotones = 3;
	    	tx = 130;
	    	ty = 30;
	    	this.controlador = controladorW;
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
	    	int widthVidaPerdida = 230;
	    	int heightVidaPerdida = 40;
	    	String textoVidaPerdida = "Un disparo te ha alcanzado";
	    	btnPierdeVida = new Boton(ventanatx / 2 - widthVidaPerdida / 2, ventanaty / 2 - heightVidaPerdida / 2,
	    			widthVidaPerdida, heightVidaPerdida, textoVidaPerdida);
	}

	public void pintarMenu(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
    	int fontSize = 15;
		Font f = new  Font ("SansSerif", Font.BOLD, fontSize); 
        g2.setFont(f);
		for (int i = 0; i < elementos.length; i++){
			pintarElemento(g,g2,elementos[i],10,20);
		}
		if (dialogo.isActivo()){
			pintarElemento(g,g2,dialogo,20,20);
	        Boton[] botones = ((Dialogo)dialogo).getBotones();
	        for (int i = 0; i < botones.length; i++){
	        	pintarElemento(g,g2,botones[i],10,20);
	        }
		}
		if(pierdeVida){
			pintarElemento(g,g2,btnPierdeVida, 20, 20);
		}
		if(finJuego){
			pintarElemento(g,g2,btnFinJuego, 20, 20);			
		}
	}
	
	private void pintarElemento(Graphics g, Graphics2D g2,
			Cuadro cuadrado, int i, int j) {
		g.setColor(cuadrado.getColor());
		g.fillRect(cuadrado.getPosx(), cuadrado.getPosy(), cuadrado.getWidth(), cuadrado.getHeight());
		g.setColor(cuadrado.getColorLine());
		g.drawRect(cuadrado.getPosx(),cuadrado.getPosy(),cuadrado.getWidth(),cuadrado.getHeight());
		g.setColor(cuadrado.getColorText());
        g2.drawString(cuadrado.getTexto(), cuadrado.getPosx() + i, cuadrado.getPosy() + j);		
	}
	
	private int buscarActivo(){
		boolean encontrado = false;
		int indice = 0;
		for (int i = 0; i < elementos.length && !encontrado; i++){
			if (elementos[i].isActivo()){
				encontrado = true;
				indice = i;
			}
		}
		return indice;
	}

	public void pulsoPausa(boolean pausa) {
		elementos[0].setActivo(pausa);
		
	}

	public void pulsoGuardar() {
		JFileChooser elegir = new JFileChooser();
		elegir.showSaveDialog(controlador);
		File fichero = elegir.getSelectedFile();
		controlador.guardarPartida(fichero);	
	}

	public void pulsoMenu() {
//		elementos[buscarActivo()].setActivo(false);
		elementos[2].switchActivo();
		dialogo.switchActivo();
		if(dialogo.isActivo()){
			controlador.setPausa(true);
		} else {
			controlador.setPausa(false);
		}
	}
	
	public void pulsoEnter(){
		if(dialogo.isActivo()){
			controlador.salir();
		}
	}
	
	public void pulsoEscape(){
		pulsoMenu();
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

	public void pierdeVida(boolean b) {
		pierdeVida = b;
	}

	public void finJuego() {
		finJuego = true;
		btnFinJuego = btnPierdeVida;
		btnFinJuego.setTexto("Fin del juego");
	}
}
