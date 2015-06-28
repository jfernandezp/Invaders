package invaders;
 
import guardados.Cargar;
import invaders.estado.Estado;
import invaders.menu.MenuControlador;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import consoleUI.UtilsUI;
 
public class Invaders {
	int ventanatx;
	int ventanaty;
	private JFrame frame;
	private JPanel panel;
	private Estado estado;
	
	//Paso 2b
	public Invaders(){
		iniciarTamano();
	}
	
	//Paso 2b
	public Invaders(String titulo){
		this();
		this.estado = new Estado();
		createFrame(titulo);
		iniciarPanelMenu();
        iniciarInvaderPanel(estado);
	}
	
	public Invaders(JFrame frame, JPanel panel, Estado estado){
		this();
		this.estado = estado;
		iniciarTamano();
		frame.setSize(ventanatx,ventanaty);	
		iniciarPanelMenu(frame,panel);
        iniciarInvaderPanel(this.estado);
	}
	
	//Paso 2a
	public void iniciarTamano(){
		ventanatx = 700;
        ventanaty = 500;
	}
	
	//paso 2a2
	private void createFrame(String titulo){
		frame = new JFrame(titulo);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        frame.setSize(ventanatx, ventanaty);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setTitle(titulo);
        frame.setBackground(Color.BLACK);
	}
	
	//Paso 3
	
	private void iniciarPanelMenu(){
		MenuControlador menuControlador = new MenuControlador(ventanatx,ventanaty,frame);
		panel = menuControlador.getPanel();
        if ( menuControlador.getEstado() != null ){
        	estado = menuControlador.getEstado();
        }
	}
	
	private void iniciarPanelMenu(JFrame frame, JPanel panel){
		this.frame = frame;
		this.panel = panel;
		iniciarPanelMenu();
	}
	
	public void iniciarInvaderPanel(Estado estado){
		//Borro el panel anterior
		frame.getContentPane().remove(panel);
		panel = null;
		//Añado el nuevo panel
        InvaderPanel invadersPanel = new InvaderPanel(ventanatx, ventanaty,estado); 
        frame.add(invadersPanel,BorderLayout.CENTER);
        invadersPanel.requestFocus();        
        frame.paintAll(frame.getGraphics());
		invadersPanel.bucle();
	}
	
	
	//Cosola	
	private void iniciarGUI(Estado estado, String titulo){
		createFrame(titulo);
    	iniciarInvaderPanel(estado);
	}
     //Consola
	public void iniciarConsola(String titulo){
    	String fichero = UtilsUI.getConsoleFilename("Introduce el nombre del fichero:", "json");
    	Cargar carga = new Cargar(fichero); 
    	Estado estado = new Estado(carga.getNivel(),carga.getVidas(),carga.getMaxVidas(),carga.getPuntos(),
    			carga.getVelocidad(),carga.getDisparoCercano(),carga.getDisparoEstructura(),carga.getDisparoAzar());
    	iniciarGUI(estado,titulo);
	}
	
	//Paso 1
    public static void main(String[] args) {
    	String titulo = "Invaders";
    	new Invaders(titulo);
//    	Invaders invaders = new Invaders();
//    	invaders.iniciarConsola(titulo);
    }   
}
