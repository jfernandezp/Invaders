package invaders;
 
import guardados.Cargar;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import consoleUI.UtilsUI;
 
public class Invaders {
     
    public static void main(String[] args) {
    	
    	String fichero = UtilsUI.getConsoleFilename("Introduce el nombre del fichero:", "json");
    	Cargar carga = new Cargar(fichero);    	
    	
    	Estado estado = new Estado(carga.getNivel(),carga.getVidas(),carga.getMaxVidas(),carga.getPuntos(),carga.getVelocidad(),carga.getDisparoCercano(),carga.getDisparoEstructura(),carga.getDisparoAzar());
    	
        JFrame frame = new JFrame("Invaders");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        int ventanatx = 700;
        int ventanaty = 500;
        InvaderPanel invadersPanel = new InvaderPanel(ventanatx, ventanaty,estado);
        frame.add(invadersPanel, BorderLayout.CENTER);
 
        frame.setSize(ventanatx, ventanaty);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setTitle("Java Invaders");
    }   
}