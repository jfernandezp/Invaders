package invaders;
 
import guardados.Cargar;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import consoleUI.UtilsUI;
 
public class Invaders {
	int ventanatx;
	int ventanaty;
	private JFrame frame;
	private JPanel panel;
	
	public Invaders(){
		iniciarTamano();
	}
	
	public Invaders(JFrame frame, JPanel panel, Estado estado){
		this();
		
		frame.setSize(ventanatx,ventanaty);	
		iniciarPanelMenu(frame,panel);
		
	}
	
	public void iniciarTamano(){
		ventanatx = 700;
        ventanaty = 500;
	}
	
	private void iniciarPanelMenu(JFrame frame, JPanel panel){
		this.frame = frame;
		this.panel = panel;
		iniciarPanelMenu();
	}
	
	private void iniciarPanelMenu(){
		this.panel = new MenuInvaders(ventanatx,ventanaty,frame);
		frame.add(this.panel, BorderLayout.CENTER);
        frame.paintAll(frame.getGraphics());
        ((MenuInvaders) panel).bucle();
	}
	
	private void createFrame(String titulo){
		frame = new JFrame(titulo);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        frame.setSize(ventanatx, ventanaty);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setTitle(titulo);
	}
	
	public void iniciarInvaderPanel(Estado estado){
        InvaderPanel invadersPanel = new InvaderPanel(ventanatx, ventanaty,estado);
        frame.add(invadersPanel, BorderLayout.CENTER);        
	}
	
	private void iniciarGUI(Estado estado, String titulo){
		createFrame(titulo);
    	iniciarInvaderPanel(estado);
	}
     
	public void iniciarConsola(String titulo){
    	String fichero = UtilsUI.getConsoleFilename("Introduce el nombre del fichero:", "json");
    	Cargar carga = new Cargar(fichero); 
    	Estado estado = new Estado(carga.getNivel(),carga.getVidas(),carga.getMaxVidas(),carga.getPuntos(),
    			carga.getVelocidad(),carga.getDisparoCercano(),carga.getDisparoEstructura(),carga.getDisparoAzar());
    	iniciarGUI(estado,titulo);
	}
	
	public void iniciarVentana(String titulo){
		Estado estado = new Estado();
		createFrame(titulo);
//		iniciarGUI(estado,titulo);
		iniciarPanelMenu();
	}
	
    public static void main(String[] args) {
    	String titulo = "Invaders";
    	Invaders invaders = new Invaders();
//    	invaders.iniciarConsola(titulo);
    	invaders.iniciarVentana(titulo);
    }   
}
