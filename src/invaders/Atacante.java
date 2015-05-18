package invaders;
 
import java.awt.Graphics;
 
public class Atacante  {
	
	private int posy;
	private int posx;
	private int x;
	private boolean dibujar = true;
	public int posxRelativa = 3; 
	
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
    
    public int getPosY(){
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
    public void posxRelativa(int posicion){
    	posxRelativa = posx - posicion; 
    	if (posxRelativa < 0){
    		posxRelativa = posxRelativa * -1;
    	}
    	//return posxRelativa;
    }
    public Integer getPosxRelativa(){
    	return posxRelativa;
    }
    /*
    @Override
    public int compareTo(Atacante o) {
       String a=new String(String.valueOf(this.getPosxRelativa()));
       String b=new String(String.valueOf(o.getPosxRelativa()));
       return a.compareTo(b);
   }*/
}