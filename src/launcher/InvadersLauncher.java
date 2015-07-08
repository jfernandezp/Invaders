package launcher;

import invaders.Invaders;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

//Haciendose...
/**
 * Invaders v0.3
 * @author jfernandezpe
 * @version 0.3
 * @since 2015/06/17
 *
 */
public class InvadersLauncher {
	
	JFrame frame;
	JPanel panel;

	InvadersLauncher(long tiempoInicial){

        int ventanatx = 700;
        int ventanaty = 500;
		
		String titulo = "Invaders";
		
		generateGUI(titulo,ventanatx,ventanaty);
		
		long tiempoFinal = System.currentTimeMillis();
		
		long tiempo = tiempoFinal - tiempoInicial;
		
        try {
			Thread.sleep((long) (1800 + tiempo));
			((LauncherPanel) panel).nextSplash();
			panel.repaint();
			
			Thread.sleep((long) (1800 + tiempo));
			
			frame.getContentPane().remove(panel);
			panel = null;
			new Invaders(frame,panel);
			
		} catch (Exception e) {
//			e.printStackTrace();
		}	
	}
	
	
	private void generateGUI(String titulo, int ventanatx, int ventanaty){
		frame = new JFrame(titulo);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new LauncherPanel();
        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(ventanatx, ventanaty);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setTitle(titulo);
       }
	
	public static void main(String[] args) {
		long tiempoInicial = System.currentTimeMillis();
		new InvadersLauncher(tiempoInicial);			
	}

}
