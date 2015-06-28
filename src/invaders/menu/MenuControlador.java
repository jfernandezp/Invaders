package invaders.menu;

import java.awt.BorderLayout;
import java.io.File;

import guardados.Cargar;
import invaders.estado.Estado;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuControlador {

	private JPanel panel;
	private int velocidadBucle;
	private boolean salirMenu;
	private Estado estado;
	private JFrame frame;
	private int ventanatx, ventanaty;

	public MenuControlador(int ventanatx, int ventanaty, JFrame frame) {
		this.frame = frame;
		this.ventanatx = ventanatx;
		this.ventanaty = ventanaty;
		velocidadBucle = 100;
        mostrarMenu();
        bucle();
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

	public void showCredits() {
		removePanel();
		
		JPanel tmppanel = new PanelCreditos(ventanatx, ventanaty, frame, this);
		panel = tmppanel;
    	frame.add(this.panel, BorderLayout.CENTER);	
    	frame.paintAll(frame.getGraphics());
		
	}

	public void mostrarMenu() {
		removePanel();
		
		JPanel tmppanel = new MenuPrincipal(ventanatx,ventanaty,frame,this);
		panel = tmppanel;
    	frame.add(this.panel, BorderLayout.CENTER);
    	frame.paintAll(frame.getGraphics());
	}

	private void removePanel(){
		try { MenuKeyListener getMyKeyListener = ((PanelMenu) panel).getMyKeyListener();
		frame.removeKeyListener(getMyKeyListener);
		} catch (NullPointerException e){
			
		}
		panel = null;
		
	}
}
