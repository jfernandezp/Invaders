package invaders.menu;

import invaders.estado.Estado;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuControlador {

	private MenuInvaders panel;
	private int velocidadBucle;
	private boolean salirMenu;

	public MenuControlador(int ventanatx, int ventanaty, JFrame frame) {
		panel = new MenuInvaders(ventanatx,ventanaty,frame,this);
		velocidadBucle = 100;
	}

	public JPanel getPanel() {
		return panel;
	}

	public Estado getEstado() {
		return panel.getEstado();
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

}
