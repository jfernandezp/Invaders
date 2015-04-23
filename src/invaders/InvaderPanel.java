package invaders;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
	
	public int ataqueLimite;
	public int velocidadAtaque = 1;
	public int velocidadDefensa = 8;
	private int desplazamientoyFila = 30;
	int ciclos = 0;
	int limite = (desplazamientoyFila * 12)  + ataqueiy;
	private Atacante listaAtacantes[] = new Atacante[60];
	
	
	public InvaderPanel(){
		defensatx=40;
		defensaty=40;
		defensax = 350 - (defensatx/2);
		defensay = 450 - defensaty;
		ataquey = 5;
		ataqueix = 115;
		ataqueLimite = ataqueix * 2 -5;
		
		
        setBackground(Color.BLACK);

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
				}
			}
		};
		addKeyListener(listener);
        
        
        /**************************************/       
        
        //listen to key presses
        setFocusable(true);
       // addKeyListener(this);

        //call step() 60 fps
        Timer timer = new Timer(1000/1000, this);
        timer.start();
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (ciclos>= 1){
			ataquey += desplazamientoyFila;
			ciclos=0;
			/*int limite = ataqueiy + desplazamientoyFila * 9;
			if (ataquey >= limite){
				//game over
				ataquey = ataqueiy;
			}*/
		}
			
		// TODO Apéndice de método generado automáticamente
		if (ataqueix >= ataqueLimite){
			velocidadAtaque = velocidadAtaque * -1;
			ciclos++;
		} else if (ataqueix <= 5){
			velocidadAtaque = velocidadAtaque * -1;
			ciclos++;
		}
			ataqueix += velocidadAtaque;
		repaint();
		
	}
		
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		Graphics2D g2=(Graphics2D) g; 
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
				listaAtacantes[cuenta] = new Atacante(this, dibujar, g, posx,posy,30,20);
				posx+=40;
				col--;
				cuenta++;
			}
			filas--;
		}		
		
		g.fillRect(defensax,defensay,defensatx,defensaty);
		g.setColor(Color.RED);
		int xO = 10;
		int yO = 10 * 3;
		int posxO = defensax + (defensatx / 2) - (xO / 2);
		int posxY = defensay - yO *12 - 20;
		g.fillOval(posxO, posxY, xO, yO * 12);
		
	 }
	/*
	public void atacante(boolean dibujar, Graphics g, int posx, int posy,int x, int y){
		if (dibujar) {
			Atacante atacanted = new Atacante(this, dibujar,g, posx,posy,x,y);
		}
	}*/

}



