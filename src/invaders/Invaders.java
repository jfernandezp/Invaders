package invaders;
 
import java.awt.BorderLayout;

import javax.swing.JFrame;

import consoleUI.UtilsUI;
 
public class Invaders {
     
    public static void main(String[] args) {
    	
    	int velocidad = UtilsUI.getConsoleInt("Velocidad del juego [Por defecto 10]:",true, 10);
    	int ataqueCercano = UtilsUI.getConsoleInt("Momento en el que atacan directamente [Por defecto: 10]:",true, 10);
    	int ataqueEstructura = UtilsUI.getConsoleInt("Momento del ataque a estructura [Por defecto 20] :",true, 20);
    	int ataqueNormal = UtilsUI.getConsoleInt("Momento del ataque a normal [Por defecto 30] :",true, 30);
    	
        JFrame frame = new JFrame("Invaders");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     //   frame.setLayout(new BorderLayout());
 
        int ventanatx = 700;
        int ventanaty = 500;
        InvaderPanel pongPanel = new InvaderPanel(ventanatx, ventanaty, velocidad, ataqueCercano, ataqueEstructura,ataqueNormal);
        frame.add(pongPanel, BorderLayout.CENTER);
 
        frame.setSize(ventanatx, ventanaty);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setTitle("Java Invaders");
    }   
}