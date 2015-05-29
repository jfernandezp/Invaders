package invaders;
  
import java.awt.Color;
import java.awt.Graphics;
  
public class Disparo {
    int posx;
    int posy;
    int x;
    int y;
    boolean tipo; 
    int movimiento;
     
    Disparo(boolean tipo, int posxO, int posyO, int xO, int yO){
        this.tipo = tipo;
        posx = posxO;
        posy = posyO;
        x = xO;
        y = yO;
        if (tipo){
            movimiento = -10;
        } else {
            movimiento = 10;
        }
    }
      
    public void dibujar(Graphics g){
        g.setColor(Color.RED);
          
        g.fillRect(posx, posy, x, y);
    }
      
    public void mover(){
         posy += movimiento;
    }
     
    public int getposY() {
        return posy;
    }
     
    public boolean compruebaColisionX (int iposx, int ix, boolean comprobar){
            /*
            A1_________A2
            ___B1___B2___
             
            ___A1___A2____
            B1__________B2
             
            A1___A2
               B1____B2
                
                 A1_____A2
            B1______B2
             
            a1 > b2 no
            b2 < a1 no
            a1 > b2 no
            b1 > a2 no
             
            ((inicioDisparo > finalDestino) &&
                (finalDestino < inicioDisparo)) ||
                ((inicioDisparo > finalDestino) &&
                (inicioDestino > finalDisparo))
             
            si b1 > a1 y b1 < a2
            si a1 > b1 y a1 < b2
             
            ---
             
            si a2 > b1 y a2 < b2
            si b2 > a1 y b2 < a2
             
            a1__________a2
            _______b1_________b2
             
             
            si b2 
                     */
        if (comprobar){
            int b1 = iposx;
            int b2 = iposx + ix;
            int a1 = posx;
            int a2 = posx + x;
 
            if ( 
                    ((b1 >= a1) && (b1 <= a2)) ||
                    ((a1 >= b1) && (a1 <= b2)) ||
                    ((a2 >= b1) && (a2 <= b2)) ||
                    ((b2 >= a1) && (b2 <= a2))
            ){
                // HAY INTERECCION
                return true;
            } else {
                //no la hay
                return false;
                 
            }
        } else {
            return false;
        }
    }
}