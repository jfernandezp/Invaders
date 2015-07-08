package invaders.menu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MenuKeyListener implements KeyListener {

	private PanelMenu controller;

	public MenuKeyListener(PanelMenu menuPanel) {
		controller = menuPanel;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		controller.keyPress(e.getKeyCode());				
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
