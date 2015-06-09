package launcher;

import java.awt.BorderLayout;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import invaders.Estado;
import invaders.InvaderPanel;

//Haciendose...
/**
 * Invaders v1.1
 * @author jfernandezpe
 * @version 1.1 08/06/2015
 *
 */
public class InvadersLauncher {

	private void loadImage(){
	}
	
	public static void main(String[] args) {
		String titulo = "Invaders";
		Estado estado = new Estado();
		
		JFrame frame = new JFrame(titulo);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int ventanatx = 700;
        int ventanaty = 500;
        JPanel panel = new LauncherPanel();
        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(ventanatx, ventanaty);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setTitle(titulo);
        try {
			Thread.sleep(3000);
			frame.getContentPane().remove(panel);
			panel = null;
	        panel = new InvaderPanel(ventanatx, ventanaty,estado);
			frame.add(panel, BorderLayout.CENTER);
	        frame.paintAll(frame.getGraphics());
		} catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
        
		
	}

}
