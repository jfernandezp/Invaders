package invaders;

import invaders.estado.Estado;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InvaderKeyListener implements KeyListener {
	

	private InvaderPanel controlador;
	private Estado estado;

	public InvaderKeyListener(InvaderPanel invaderPanel, Estado estado){
		this.controlador = invaderPanel;
		this.estado = estado;		
	}
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 37 && !estado.getPausa() && !estado.getFinJuego() ){
        	controlador.moverIzquierda();
        } else if ( e.getKeyCode() == 39 && !estado.getPausa() && !estado.getFinJuego()){
        	controlador.moverDerecha();
        } else if ((e.getKeyCode() == 80) || (e.getKeyCode() == 19) ){
        	controlador.pulsoPausa();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 32){
           controlador.lanzoDisparo();
        } 
    }
}