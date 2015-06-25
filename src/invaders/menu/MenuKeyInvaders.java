package invaders.menu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MenuKeyInvaders implements KeyListener {

	private MenuInvaders controller;

	public MenuKeyInvaders(MenuInvaders menuInvaders) {
		controller = menuInvaders;
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
