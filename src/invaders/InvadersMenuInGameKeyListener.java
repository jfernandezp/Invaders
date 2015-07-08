package invaders;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InvadersMenuInGameKeyListener implements KeyListener {
	InvaderPanel controlador;
	public InvadersMenuInGameKeyListener(InvaderPanel invaderPanel) {
		this.controlador = invaderPanel;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if ((e.getKeyCode() == 80) || (e.getKeyCode() == 19)){
			controlador.pulsoPausa();
		} else if (e.getKeyCode() == 71){ // Guardar
			controlador.pulsoGuardar();
		} else if (e.getKeyCode() == 77 ) { //Menú
			controlador.pulsoMenu();
		} else if (e.getKeyCode() == 27) { // Escape
			controlador.pulsoEscape();
		} else if (e.getKeyCode() == 10) { //Enter
			controlador.pulsoEnter();
		}
		/*else if (e.getKeyCode() == 20 ) { //Tabulador
//			controlador.menuOpcionDerecha();
		}*/
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Apéndice de método generado automáticamente
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Apéndice de método generado automáticamente
		
	}

}
