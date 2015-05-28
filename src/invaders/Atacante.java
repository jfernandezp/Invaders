package invaders;
 
import java.awt.Graphics;
 
public class Atacante  {
	
	private int posy;
	private int posx;
	private int tx;
	private int ty;
	private boolean dibujar = true;
	public int posxRelativa = 3; 
	private static int numAtacantes = 0;
	
    private InvaderPanel ip;
    
    public Atacante( InvaderPanel invaderPanel, boolean dibujari, Graphics g, int posx, int posy, int tx, int ty) {
        this.posy = posy;
        this.posx = posx;
        this.tx = tx;
        this.ty = ty;
        this.dibujar = dibujari;
        pintar(invaderPanel, g); 
        
        siDibujar();
    }
    
    public void pintar(InvaderPanel invaderPanel, Graphics g){
    	if (dibujar) {
            this.ip = invaderPanel;
            if (posy > ip.limite){
                //game over
                ip.ataquey = ip.ataqueiy;
                posy =ip.ataqueiy;
            }
            g.fillRect(posx,posy,tx,ty);
        }
    }
    
    public void setPosx(int data){
    	posx = data;
    }
    
    public void setPosy(int data){
    	posy = data;
    }
    public int getPosY(){
    	return posy;
    }
    
    public int getPosX(){
    	return posx;
    }
    
    public int gettx(){
    	return tx;
    }
    
    public void siDibujar(){
    	dibujar = true;
    	numAtacantes++;
    }
    
    public  void noDibujar(){
    	dibujar = false;
    	numAtacantes--;
    }
    public boolean getDibujar(){
    	return dibujar;
    }    
    public void posxRelativa(int posicion){
    	posxRelativa = posx - posicion; 
    	if (posxRelativa < 0){
    		posxRelativa = posxRelativa * -1;
    	}
    }
    public Integer getPosxRelativa(){
    	return posxRelativa;
    }

    public static int getNumAtacantes(){
    	return numAtacantes;
    }    
}