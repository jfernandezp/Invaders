package invaders.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class PanelMenu extends JPanel{
	/**
	 * 
	 */
	int buttonPosx, buttonPosy, buttonSpace;
	int buttonWidth, buttonHeight;
	int numBotones;
	int[][] menus;
	protected String[] btnText;
	protected boolean[] btnEstados;
	protected MenuControlador controlador;
	private JFrame frame;
	private MenuKeyListener lisenner;

	PanelMenu(int ventanatx, int ventanaty, JFrame frame, MenuControlador menuControlador){
		this.frame = frame;
		setBackground(frame.getBackground());
		buttonSpace = 20;
		buttonWidth = 240;
		buttonHeight = 40;
		buttonPosx = ventanatx / 2 - buttonWidth / 2;
		buttonPosy = buttonSpace;
		this.controlador = menuControlador;	
		lisenner = new MenuKeyListener(this);		
		frame.addKeyListener(lisenner);
		frame.setFocusable(true);
	}
	
	public void paintComponent(Graphics g){
        super.paintComponent(g);
		
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
	
	protected void inicializarBotones(String[] auxBtnText) {
		btnEstados = new boolean[numBotones];
		crearEstado(numBotones);
		btnText = auxBtnText;
		createButtons(numBotones);	
	}
	
	protected void crearEstado(int num) {
		num--;
		if (num < 1){
			btnEstados[num] = true;
		} else {
			btnEstados[num] = false;
			crearEstado(num);
		}
	}

	protected void createButtons(int num){
		num--;
		if (num < 1) {
			int posy = buttonPosy + buttonSpace / 2;
			int[] aux = {buttonPosx,posy,buttonWidth,buttonHeight};
			menus[num] = aux;
		} else {
			int posy = calcPosy(num);
			int[] aux = {buttonPosx,posy,buttonWidth,buttonHeight};
			menus[num] = aux;			
			createButtons(num);
		}
	}
	
	protected int calcPosy(int num){
		return buttonHeight + buttonHeight * num + buttonSpace * (num) - buttonSpace;	
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

	protected int buscarBotonActivo() {
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
	
	protected abstract void pulsaEnter(int keyCode);
	
	public MenuKeyListener getMyKeyListener(){
		return lisenner;
	}
}
