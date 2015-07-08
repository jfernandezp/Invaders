package invaders.menu;

import invaders.graficos.Boton;

import java.awt.Graphics;
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
		botones = new Boton[numBotones];
		String[] auxBtnText = {"Nuevo juego", "Cargar partida", "Créditos", "Salir"};
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
			case 3:
				controlador.salir();
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
