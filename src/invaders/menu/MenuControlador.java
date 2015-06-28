package invaders.menu;

import java.io.File;

import guardados.Cargar;
import invaders.estado.Estado;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuControlador {

	private MenuInvaders panel;
	private int velocidadBucle;
	private boolean salirMenu;
	private Estado estado;

	public MenuControlador(int ventanatx, int ventanaty, JFrame frame) {
		panel = new MenuInvaders(ventanatx,ventanaty,frame,this);
		velocidadBucle = 100;
	}

	public JPanel getPanel() {
		return panel;
	}
	
	public void bucle(){
		salirMenu = false;
		while(!salirMenu){
			try {
				Thread.sleep(velocidadBucle);
				panel.repaint();
			} catch (InterruptedException e) {
				//
			}			
		}
	}

	public void setSalirMenu(boolean b) {
		salirMenu = b;		
	}

	public void personalizarPartida(File fichero) {
		Cargar carga = new Cargar(fichero);		
		estado = new Estado(carga.getNivel(),carga.getVidas(),carga.getMaxVidas(),carga.getPuntos(),carga.getVelocidad(),carga.getDisparoCercano(),carga.getDisparoEstructura(),carga.getDisparoAzar());	
	}
	
	public Estado getEstado(){
		return estado;
	}

}
