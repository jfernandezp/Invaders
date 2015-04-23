package invaders;

import java.awt.Graphics;

public class Atacante {
	private InvaderPanel ip;
	public Atacante(InvaderPanel ip){
		
	}
	public Atacante( InvaderPanel invaderPanel, boolean dibujar, Graphics g, int posx, int posy, int x, int y) {
		if (dibujar) {
			this.ip = invaderPanel;
			if (posy > ip.limite){
				//game over
				ip.ataquey = ip.ataqueiy;
				posy =ip. ataqueiy;
			}
			g.fillRect(posx,posy,x,y);
		}
	}	
}
