package launcher;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class LauncherPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] splash;
	private int cursor;
	/**
	 * Create the panel.
	 * @param frame 
	 */
	public LauncherPanel() {
		cursor = 0;
		String[] tmpsplash = {"img/splashjfernandezpe.png","img/splashinvaders.png"};
		splash = tmpsplash;		
	}

	 @Override
    public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Image image = new ImageIcon(splash[cursor]).getImage();
		g2d.drawImage(image, 0, 0, this);
	 }
	 
	 public void nextSplash(){
		 if (cursor < (splash.length - 1)){
			 cursor++;
		 } else {
			 cursor = 0;
		 }
	 }

}
