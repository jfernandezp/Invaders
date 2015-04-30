package invaders;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class Invaders {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Invaders");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     //   frame.setLayout(new BorderLayout());

        InvaderPanel pongPanel = new InvaderPanel();
        frame.add(pongPanel, BorderLayout.CENTER);

        frame.setSize(700, 500);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setTitle("Java Invaders");
	}	
}
