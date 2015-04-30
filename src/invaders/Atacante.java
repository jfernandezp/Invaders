package invaders;
 
import java.awt.Graphics;
 
public class Atacante {
	
	public int posy;
	public int posx;
	public int x;
	public boolean dibujar = true;
	
    private InvaderPanel ip;
    public Atacante(InvaderPanel ip){
         
    }
    public Atacante( InvaderPanel invaderPanel, boolean dibujari, Graphics g, int posxi, int posyi, int xi, int y) {
        posy = posyi;
        posx = posxi;
        x = xi;
        dibujar = dibujari;
        
    	if (dibujar) {
            this.ip = invaderPanel;
            if (posy > ip.limite){
                //game over
                ip.ataquey = ip.ataqueiy;
                posy =ip.ataqueiy;
            }
            g.fillRect(posx,posy,x,y);
        }
    }
    
    public int getposY(){
    	int t = posy;
    	return t;
    }
    
    public int getPosX(){
    	int t = posx;
    	return t;
    }
    
    public int getx(){
    	int t= x;
    	return t;
    }
    public  void noDibujar(){
    	dibujar = false;
    }
    public boolean getDibujar(){
    	return dibujar;
    }
}