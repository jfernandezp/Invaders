package invaders.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuInvaders extends JPanel{
	int buttonPosx, buttonPosy, buttonSpace;
	int buttonWidth, buttonHeight;
	int velocidad;
	int[][] menus;
	private String[] btnText;
	private boolean[] btnEstados;
	private boolean salir;
	
	public MenuInvaders(int ventanatx, int ventanaty, JFrame frame){
		int numBotones;
		velocidad = 100;
		frame.setBackground(Color.BLACK);
		buttonSpace = 20;
		buttonWidth = 240;
		buttonHeight = 40;
		buttonPosx = ventanatx / 2 - buttonWidth / 2;
		buttonPosy = buttonSpace;
		numBotones = 4;
		menus = new int[numBotones][4];
		String[] auxBtnText = {"Nuevo juego", "Personalizar partida", "Créditos", "Salir"};
		btnEstados = new boolean[numBotones];
		crearEstado(numBotones);
		btnText = auxBtnText;
		createButtons(numBotones);
		
		MenuKeyInvaders lisenner = new MenuKeyInvaders(this);
		frame.requestFocus();
			frame.addKeyListener(lisenner);
		
	}
	
	public void bucle(){
		salir = false;
		while(!salir){
			try {
				Thread.sleep(velocidad);
				repaint();
			} catch (InterruptedException e) {
				// TODO Bloque catch generado automáticamente
				e.printStackTrace();
			}			
		}
	}
	
	private void crearEstado(int num) {
		num --;
		if (num < 1){
			btnEstados[num] = true;
		} else {
			btnEstados[num] = false;
			crearEstado(num);
		}
	}

	private void createButtons(int num){
		num--;
		if (num < 1) {
			int posy = buttonPosy + buttonSpace / 2;
			int[] aux = {buttonPosx,posy,buttonWidth,buttonHeight};
			menus[num] = aux;
		} else {
			int posy = buttonHeight + buttonHeight * num + buttonSpace * (num) - buttonSpace;	
			int[] aux = {buttonPosx,posy,buttonWidth,buttonHeight};
			menus[num] = aux;			
			createButtons(num);
		}
	}
	
	public void paintComponent(Graphics g){
//        super.paintComponent(g);
		
		g.setColor(Color.RED);
		Color colorFill = new Color(31,31,31);
		Color colorLine = Color.RED;
		Color colorText = Color.WHITE;
		Color colorFillFocus = Color.WHITE;
		Color colorTextFocus = colorFill;
		Graphics2D g2 = (Graphics2D) g;
    	int fontSize = 20;
		Font f = new  Font ("SansSerif", Font.BOLD, fontSize); 
        g2.setFont(f);

        for (int i = 0; i < menus.length; i++){
        	Color colorFondo;
        	Color colorLetra;
        	if (btnEstados[i]){
        		colorFondo = colorFillFocus;
        		colorLetra = colorTextFocus;
        	} else {
        		colorFondo = colorFill;
        		colorLetra = colorText;
        	}
			g.setColor(colorFondo);
			g.fillRect(menus[i][0], menus[i][1], menus[i][2], menus[i][3]);
			g.setColor(colorLine);
			g.drawRect(menus[i][0], menus[i][1], menus[i][2], menus[i][3]);

			g.setColor(colorLetra);
			int textWidth = menus[i][0] + 20;
			int textHeight = menus[i][1] + menus[i][3] / 2 + fontSize / 3;
	        g2.drawString(btnText[i], textWidth, textHeight);
		}
	}

	public void keyPress(int keyCode) {
		switch(keyCode){
			case 10:
				pulsaEnter(keyCode);
			break;
			case 32:
				pulsaEnter(keyCode);
			break;
			case 37:
				break;
			case 38:
				teclaArriba();
				break;
			case 39:
				break;
			case 40:
				teclaAbajo();
			break;
		}
	}

	private void pulsaEnter(int keyCode) {
		boolean encontradoActivo = false;
		for(int i = 0; i < btnEstados.length && !encontradoActivo; i++){
			if(btnEstados[i]){
				encontradoActivo = true;
				switch(i){
					case 0:
						salir = true;
						break;
					
				}
			}
		}
		
	}

	private int buscarBotonActivo() {
		int activo = 0;
		boolean salir = false;
		for (int i = 0; i < btnEstados.length && !salir; i++ ) {
			if (btnEstados[i]){
				activo = i;
				salir = true;
			}
		}
		return activo;
	}

	private void teclaAbajo() {
		int activo = buscarBotonActivo();
		btnEstados[activo] = false;
		activo++;
		if (activo >= btnEstados.length){
			activo = 0;
		}
		btnEstados[activo] = true;
	}

	private void teclaArriba() {
		int activo = buscarBotonActivo();
		btnEstados[activo] = false;
		if (activo <= 0){
			activo = btnEstados.length;
		}
		activo--;
		btnEstados[activo] = true;
	}
}
