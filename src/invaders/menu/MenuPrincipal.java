package invaders.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class MenuPrincipal extends PanelMenu{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MenuPrincipal(int ventanatx, int ventanaty, JFrame frame, MenuControlador menuControlador){
		super(ventanatx,ventanaty,frame,menuControlador);
		numBotones = 4;
		menus = new int[numBotones][4];
		String[] auxBtnText = {"Nuevo juego", "Personalizar partida", "Créditos", "Salir"};
		inicializarBotones(auxBtnText);		
	}
	
	public void paintComponent(Graphics g){
        super.paintComponent(g);
	}

	protected void pulsaEnter(int keyCode) {
		int i = this.buscarBotonActivo();
		switch(i){
			case 0:
				controlador.setSalirMenu(true);
				break;
			case 1: 
				personalizarPartida();
				break;
			case 2:
				controlador.showCredits();
				break;
		}		
	}

	public void personalizarPartida() {
		JFileChooser elegir = new JFileChooser();
		elegir.showOpenDialog(this);
		File fichero = elegir.getSelectedFile();
		controlador.personalizarPartida(fichero);
	}
}
