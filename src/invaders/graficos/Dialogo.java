package invaders.graficos;

public class Dialogo extends Cuadro{
	
	public Dialogo(int posx, int posy, int tx, int ty, String texto){
		super(posx,posy,tx,ty,texto);
		
		this.botones = new Boton[2];
		String[] text = {"Salir (Enter)", "Cancelar (Esc)"};
		
		int btnwidth = 120;
		int heightbtn = 30;
		
		for (int i = 0; i < botones.length; i++){
			int btnposx = (width / 3) * (i + 1) - btnwidth / 2 + posx;
			int btnposy = posy + 50;
			this.botones[i] = new Boton(btnposx, btnposy, btnwidth, heightbtn, text[i]);
		}
	}
	
	public Boton[] getBotones(){
		return botones;
	}
}