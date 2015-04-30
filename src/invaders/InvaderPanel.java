package invaders;
 
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;
 
 
public class InvaderPanel extends JPanel implements ActionListener {
    public int defensax;
    public int defensay;
    public int defensatx;
    public int defensaty;
    public int ataqueiy = 5;
    public int ataquey = ataqueiy;
    public int ataqueix;
    
    public int ataquetx = 30;
    public int ataquety = 20;
     
    public int ataqueLimite;
    public int velocidadAtaque = 1; //0; //1;
    public int velocidadDefensa = 8;
    private int desplazamientoyFila = 30;
    int ciclos = 0;
    int limite = (desplazamientoyFila * 12)  + ataqueiy;
    private Atacante listaAtacantes[] = new Atacante[60];
    Graphics g2;
     
    int xDD = 15;
    int yDD = 15;
    int posxDD;
    int posyDD;
    
    int velocidad = 20;
    
 
    ArrayList<Disparo> disparos = new ArrayList<Disparo>();
	public InvaderPanel(){
        defensatx=40;
        defensaty=40;
        defensax = 350 - (defensatx/2);
        defensay = 450 - defensaty;
        ataquey = 5;
        ataqueix = 115;
        ataqueLimite = ataqueix * 2 -5;
         
        posxDD = defensax + (defensatx / 2) - (xDD / 2);
        //posyDD = defensay - yDD *12 - 20;
        posyDD = defensay - yDD -4;
         
        setBackground(Color.BLACK);
        
       // ataqueix = ataqueix -2 ;
 
        /******************************************/
        KeyListener listener = new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
            }
 
            @Override
            public void keyPressed(KeyEvent e) {
                int nPosicion;
                if (e.getKeyCode() == 37){
                    nPosicion = defensax - velocidadDefensa;
                    if (nPosicion < 0) {
                        defensax = 0;
                    } else {
                        defensax = nPosicion;
                    }
                } else if (e.getKeyCode() == 39) {
                    nPosicion = defensax + velocidadDefensa;
                    int limite = 695 - (defensatx );
                    if (nPosicion > limite) {
                        defensax = limite;
                    } else {
                        defensax = nPosicion;
                    }
                } 
                //repaint();
            }
 
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == 32){
                	int posx = defensax + defensatx/2 - xDD / 2;
                    disparos.add(new Disparo(posx, posyDD, xDD, yDD));
                }
            }
        };
        addKeyListener(listener);
         
         
        /**************************************/      
         
        //listen to key presses
        setFocusable(true);
       // addKeyListener(this);
 
        //call step() 60 fps
        Timer timer = new Timer(1000 / velocidad , this);
        timer.start();
    }
    
    //Bucle del juego:
    public void actionPerformed(ActionEvent e) {
        
    	intentarBajarFila(1); 
        
    	desplazamientoVertical();
    	
    	comprobarColisionesAtaques();
    	
        comprobarColisionesDisparos();
     
        Ataques();
        
        repaint();  
    }


	public void paintComponent(Graphics g){
        super.paintComponent(g);
         
        g.setColor(Color.WHITE);
             
        int filas = 5;
        int columnas = 12;
         
        int posx;
        int posy = ataquey - desplazamientoyFila;
        int fil = filas;
        int col;
        int cuenta = 0;
        while (fil > 0){
            col = columnas;
            while (col > 0){
                col--;
            }
            fil--;
        }
         
        fil = filas;
        while (filas > 0)
        {   
            posx = ataqueix;
            posy += desplazamientoyFila;
            col = columnas;
            boolean dibujar=true;
            while (col > 0){
            	 if (listaAtacantes[cuenta] != null)
            		 dibujar = listaAtacantes[cuenta].getDibujar();
                listaAtacantes[cuenta] = new Atacante(this, dibujar, g, posx,posy,ataquetx,ataquety);
                posx+=40;
                col--;
                cuenta++;
            }
            filas--;
        }       
         
        g.fillRect(defensax,defensay,defensatx,defensaty);
         
        Iterator<Disparo> iDisparos = disparos.iterator();
        while(iDisparos.hasNext()){
            Disparo t = iDisparos.next();
            t.dibujar(g);       
        }
     }


	private void intentarBajarFila(int nciclos) {
		if (ciclos>= nciclos){
	        ataquey += desplazamientoyFila;
	        ciclos=0;
	        /*int limite = ataqueiy + desplazamientoyFila * 9;
	        if (ataquey >= limite){
	            //game over
	            ataquey = ataqueiy;
	        }*/
	    }
	}
	
	private void desplazamientoVertical(){
	    if (ataqueix >= ataqueLimite){
	        velocidadAtaque = velocidadAtaque * -1;
	        ciclos++;
	    } else if (ataqueix <= 5){
	        velocidadAtaque = velocidadAtaque * -1;
	        ciclos++;
	    }
	        ataqueix += velocidadAtaque;
	}
	
	private void comprobarColisionesAtaques(){
		
	}
	
	private void comprobarColisionesDisparos(){
		Iterator<Disparo> iDisparos = disparos.iterator();
	    
	    while(iDisparos.hasNext()){
	    	boolean b;
	    	boolean gb = false;
	        Disparo t = iDisparos.next();
	        if (t.getposY() <= 0 ) {
	        	iDisparos.remove();
	        } else {
	            t.mover();
	            for (int i = 0; i < listaAtacantes.length; i = i + 12){
	            	if (listaAtacantes[i].getposY() >= t.getposY()){
	            		//lógica de detección colisiones en x
	            		int l = i + 12;
	            		while (i < l){
	            			b = t.compruebaColisionX(listaAtacantes[i].getPosX(), listaAtacantes[i].getx(), listaAtacantes[i].getDibujar());
	            			if (b) {
	            				listaAtacantes[i].noDibujar();
	            	           // iDisparos.remove();
	            	            gb = true;
	            				i++;
	            			} else {
	            				i++;
	            			}
	            		}
	            	}
	            }	            
	        }
	        if (gb){
	        	iDisparos.remove();
	        }
	    }
	}

	private void Ataques(){
		Random r = new Random();
		int aleatorio = r.nextInt(6) + 1;
		
	}
}