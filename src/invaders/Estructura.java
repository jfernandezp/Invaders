package invaders;
 
import java.awt.Color;
import java.awt.Graphics;
 
public class Estructura {
        private int posx;
        private int posy;
        private int tamx;
        private int tamy;
        private byte estado;
        private Graphics g;
        private static int maxPosY = 0;
        private static int minPosY = -1;
        
        public Estructura(Graphics g, int posx, int posy, int tamx, int tamy, int dibujoy, int dibujox){
            this.posx = posx;
            this.posy = posy;
            this.tamx = tamx;
            this.tamy = tamy;
            this.g = g;
            this.estado = 4;
            pintar(this.g);
            
            if ((posy + tamy) > maxPosY){
            	maxPosY = posy+tamy;
            };
            if ((minPosY < 0 )|| (posy < minPosY)){
            	minPosY = posy;
            }
        }
        
        public void pintar(Graphics g){
        	this.g = g;
        	nuevoEstadocolor(estado);
        	this.g.fillRect(this.posx,this.posy,this.tamx,this.tamy);
        }
         
        public void nuevoEstadocolor(int estado){
            int numero = 63 * estado;
            Color color = new Color(numero, numero, numero);
            g.setColor(color);
        }
        
        public static int getMaxPosY(){
        	return maxPosY;
        }

		public int getX() {
			return tamx;
		}

		public boolean getDibujar() {
			if (estado == 0){
				return false;
			} else {
				return true;
			}
		}

		public int getposX() {
			return posx;
		}

		public int getposY(){
			return posy;
		}
		
		public int getPosYbaja(){
			int posybaja = posy + tamy;
			return posybaja;
		}
		
		public void tocada() {
			estado = (byte) (estado - 1);
			if (estado < 0) {
				estado = 0;
			}
		}

		public int getMinPosY() {
			return minPosY;
		}
}