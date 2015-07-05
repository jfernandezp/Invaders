package invaders.graficos;

import java.awt.Color;

public class Boton extends Cuadro{	
	public Boton(int posx, int posy, int tx, int ty, String texto){
		super(posx,posy,tx,ty,texto);
	}	
	@Override
	public void setActivo(boolean b){
		activo = b;
		if (b){
			this.color = Color.WHITE;
			this.colorLine = Color.RED;
			this.colorText = Color.BLACK;
		}
	}
}