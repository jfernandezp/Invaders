package invaders.menu;

import invaders.graficos.Boton;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;

public class PanelCreditos extends PanelMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PanelCreditos(int ventanatx, int ventanaty, JFrame frame, MenuControlador menuControlador){
		super(ventanatx,ventanaty,frame,menuControlador);
		numBotones = 1;
		botones = new Boton[numBotones];
		String[] auxBtnText = {"Atrás"};
		inicializarBotones(auxBtnText);		
	}
	
	public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        int i = botones.length;
        int posyln1 = calcPosy(i);
        int posyln2 = calcPosy(i + 1);
        int posx = buttonPosx - 40;
        
		Graphics2D g2 = (Graphics2D) g;

		g.setColor(Color.white);
		Font f = new  Font ("SansSerif", Font.BOLD, 14); 
        g2.setFont(f);
        g2.drawString("jfernandezpe", posx, posyln1 + 20);
        g2.drawString("Visita la web http://jfernandezpe.wordpress.com", posx, posyln2 + 20);        
	}

	@Override
	protected void pulsaEnter(int keyCode) {
		boolean encontradoActivo = false;
		for(int i = 0; i < btnEstados.length && !encontradoActivo; i++){
			if(btnEstados[i]){
				encontradoActivo = true;
				switch(i){
					case 0:
						controlador.mostrarMenu();
						break;
				}
			}
		}
	}
}
