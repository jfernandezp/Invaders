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
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
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
    int ciclosDisparos = 0;
    int limite = (desplazamientoyFila * 12)  + ataqueiy;
    private Atacante listaAtacantes[] = new Atacante[60];
   // private int listaColumnas[] = new int[12];
    Graphics g2;
       
    int xDD = 5;
    int yDD = 9;
    int posxDD;
    int posyDD;
      
    int ventanatx;
    int ventanaty;
    int margenVentana = 5;
     
      
    int velocidad = 10; //20;
    boolean pausa = true; //false;
    boolean finJuego = false;
    
    int tmpAtaqueCercano = 15;
    int tmpAtaqueEstructura = 30;
         
    ArrayList<Disparo> disparos = new ArrayList<Disparo>();
    ArrayList<Disparo> disparosAtacantes = new ArrayList<Disparo>();
    ArrayList<Estructura> listaEstructuras = new ArrayList<Estructura>();
      
    public InvaderPanel(final int ventanatx, int ventanaty, int veloc, int ataqCercano, int ataqEstructura){
    	
    	velocidad = veloc;
    	tmpAtaqueCercano = ataqCercano;
    	tmpAtaqueEstructura = ataqEstructura;
    	
        defensatx=40;
        defensaty=40;
        defensax = 350 - (defensatx/2);
        defensay = 450 - defensaty;
        ataquey = 5;
        ataqueix = 115;
        ataqueLimite = ataqueix * 2 - margenVentana;
        this.ventanatx = ventanatx;
        this.ventanaty = ventanaty;
           
        posxDD = defensax + (defensatx / 2) - (xDD / 2);
        //posyDD = defensay - yDD *12 - 20;
        posyDD = defensay - yDD -4;
           
        setBackground(Color.BLACK);
        /*
        for (int i = 0; i < 12; i++){
            listaColumnas[i] = 0;
        }*/
          
       // ataqueix = ataqueix -2 ;
   
        /******************************************/
        KeyListener listener = new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
//              System.out.println("+"+e.getKeyCode()+"+");
            }
            @Override
            public void keyPressed(KeyEvent e) {
                //System.out.println("+"+e.getKeyCode()+"+");
                int nPosicion;
                if (e.getKeyCode() == 37){
                    nPosicion = defensax - velocidadDefensa;
                    if (nPosicion < 0) {
                        defensax = 0;
                    } else {
                        defensax = nPosicion;
                    }
                } else if ( e.getKeyCode() == 39) {
                    nPosicion = defensax + velocidadDefensa;
                    int limite = ventanatx - margenVentana - (defensatx );
                    if (nPosicion > limite) {
                        defensax = limite;
                    } else {
                        defensax = nPosicion;
                    }
                } else if ((e.getKeyCode() == 80) || (e.getKeyCode() == 19) ){
                    if (pausa) {
                        pausa = false;
                    } else {
                        pausa = true;
                    }
                }
                //repaint();
            }
   
            @Override
            public void keyReleased(KeyEvent e) {
                //System.out.println("_"+e.getKeyCode()+"_");
                if (e.getKeyCode() == 32){
                    int posx = defensax + defensatx/2 - xDD / 2;
                    disparos.add(new Disparo(true, posx, posyDD, xDD, yDD));
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
        if ((!pausa) && (!finJuego)){
            intentarBajarFila(1); 
              
            desplazamientoVertical();
              
            comprobarColisionesDisparosAtaque();
              
            comprobarColisionesDisparosDefensa();
           
            Ataques();
        }
                  
        //System.out.println("_________________________________________________");
          
        repaint();  
    }
  
  
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        if (listaEstructuras.isEmpty()){
	        boolean [][] estructura = new boolean[3][3];
	        estructura[0][0] = true;
	        estructura[0][1] = false;
	        estructura[0][2] = true;
	        estructura[1][0] = true;
	        estructura[1][1] = false;
	        estructura[1][2] = true;
	        estructura[2][0] = true;
	        estructura[2][1] = true;
	        estructura[2][2] = true;
	         
	        int bloque = ventanatx / 5; 
	        /*int te = 75;
	        int tem = te / 2;
	        int bloque1 = bloque - tem;
	        int bloque2 = bloque*2 -tem;
	        int bloque3 = bloque*3 -tem;
	        int bloque4 = bloque*4 -tem;
	        int ey = 400;
	        int ty = 45;*/
	        int tex = 25; //tamaño estructura en x;
	        int tey = 15; //tamaño estructura en y;
	        int eposx; //Posicion en x de la estructura
	        int eposyi = 335; //Posicion en y de la estructura  (inicial)
	        int eposy; //Posicion en y de la estructura (en la estructura) 
	 
	         
	        for (int i = 0; i < 4; i++){
	            //tem = tex * estructura.length;
	            int bloqueEstructura = (bloque * (i+1)) -  (tex * estructura[0].length / 2);
	            eposy = eposyi;
	            for (int j = 0; j < estructura.length; j++){
	                eposx = bloqueEstructura;
	                for (int k = 0; k < estructura[j].length; k++){
	                    if (estructura[j][k]){
	                        listaEstructuras.add(new Estructura(g, eposx, eposy, tex, tey, j, k));
	                    }
	                    eposx = eposx+25;
	                }
	                eposy = eposy - 15;
	            }
	        }
	        
	        /*
	        for (int i = 0; i < 4; i++){
	            //tem = tex * estructura.length;
	            int bloqueEstructura = (bloque * (i+1)) -  (tex * estructura[0].length / 2);
	            eposy = eposyi;
	            for (int j = 0; j < estructura.length; j++){
	                eposx = bloqueEstructura;
	                for (int k = 0; k < estructura[j].length; k++){
	                    if (estructura[j][k]){
	                        listaEstructuras.add(new Estructura(g, eposx, eposy, tex, tey, j, k));
	                    }
	                    eposx = eposx+25;
	                }
	                eposy = eposy + 15;
	            }
	        }*/
	       //. System.out.println(bloque2);
	        /*
	        Estructura estructura1 = new Estructura(g, bloque1, ey, te, ty);
	        Estructura estructura2 = new Estructura(g, bloque2, ey, te, ty);
	        Estructura estructura3 = new Estructura(g, bloque3, ey, te, ty);
	        Estructura estructura4 = new Estructura(g, bloque4, ey, te, ty);*/
        } else {
        	Iterator<Estructura> iEstructura = listaEstructuras.iterator();
        	while (iEstructura.hasNext()){
        		Estructura estructura = iEstructura.next();
        		estructura.pintar(g);
        	}
        }
           
        g.setColor(Color.WHITE);
               
        int filas = 5;
        int columnas = 12;
           
        int posx;
        int posy = ataquey - desplazamientoyFila;
        //int fil = filas;
        int col;
        int cuenta = 0;
        /*while (fil > 0){
            col = columnas;
            while (col > 0){
                col--;
            }
            fil--;
        }*/
        /*
        for (int i = 0; i < 12; i++){
            listaColumnas[i] = 0;
        }*/
          
           
        //fil = filas;
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
                  
               // listaColumnas[col] += dibujar ? 1 : 0;
            }
            filas--;
        }       
           
        g.fillRect(defensax,defensay,defensatx,defensaty);
           
        Iterator<Disparo> iDisparos = disparos.iterator();
        while(iDisparos.hasNext()){
            Disparo t = iDisparos.next();
            t.dibujar(g);       
        }
          
        Iterator<Disparo> iDisparosAtacantes = disparosAtacantes.iterator();
        while(iDisparosAtacantes.hasNext()){
            Disparo t = iDisparosAtacantes.next();
            t.dibujar(g);
        }
         
        //en desarrollo...
         
        
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
        } else if (ataqueix <= margenVentana){
            velocidadAtaque = velocidadAtaque * -1;
            ciclos++;
        }
            ataqueix += velocidadAtaque;
    }
      
    private void comprobarColisionesDisparosAtaque(){
        //es el mismo codigo con escasas variaciones de this.comprobarColisionesDisparosDefensa();
//      System.out.println(disparosAtacantes.size());
        Iterator<Disparo> iDisparos = disparosAtacantes.iterator();
        while(iDisparos.hasNext()){
            boolean hayColision;
            boolean hayColisionEstructura;
            boolean eliminarElemento = false;
            Disparo disparo = iDisparos.next();
            if (disparo.getposY() >= ventanaty ) {
                iDisparos.remove();
            } else {
                disparo.mover();
                
                
                if (disparo.getposY() >= listaEstructuras.get(0).getMinPosY()){
                	Iterator<Estructura> iEstructuras = listaEstructuras.iterator();
                	while (iEstructuras.hasNext()){
                		Estructura estructura = iEstructuras.next();
                		if (estructura.getDibujar() && (disparo.getposY() >= estructura.getposY() )){
                			hayColisionEstructura = disparo.compruebaColisionX(estructura.getposX(), estructura.getX(), estructura.getDibujar());
                			if (hayColisionEstructura){
                				estructura.tocada();
                				eliminarElemento = true;
                			}
                		}
                	}
                }
                if (!eliminarElemento){
	                if ((disparo.getposY() >= defensay) && ((disparo.getposY() <= defensay + defensaty)) ) {
	                    hayColision = disparo.compruebaColisionX(defensax,defensatx,true);
	                    if (hayColision){
	                        finJuego = true;
	                    }
	                }
                }
                
                if (eliminarElemento){
                	iDisparos.remove();
                }
            }
        }
    }
      
    private void comprobarColisionesDisparosDefensa(){
        Iterator<Disparo> iDisparos = disparos.iterator();
          
        while(iDisparos.hasNext()){
            boolean hayColision;
            boolean hayColisionEstructura;
            boolean eliminarElemento = false;
            Disparo disparo = iDisparos.next();
            if (disparo.getposY() <= 0 ) {
                iDisparos.remove();
            } else {
                disparo.mover();
                
                listaEstructuras.get(0);
				if (disparo.getposY() <= Estructura.getMaxPosY()){
	                Iterator<Estructura> iEstructuras = listaEstructuras.iterator();
	                while (iEstructuras.hasNext() && !eliminarElemento){
	                	
	                	Estructura estructura = iEstructuras.next();
	                	
	                	if (disparo.getposY() <= estructura.getPosYbaja() && estructura.getDibujar()){
		                	hayColisionEstructura = disparo.compruebaColisionX(estructura.getposX(), estructura.getX(), estructura.getDibujar());
		                	if (hayColisionEstructura){
		                		estructura.tocada();
		                		eliminarElemento = true;
		                	}
	                	}
	                }
                }
                
                if (!eliminarElemento){
	                for (int i = 0; i < listaAtacantes.length; i = i + 12){
	                    if (listaAtacantes[i].getPosY() >= disparo.getposY()){
	                        //lógica de detección colisiones en x
	                        int l = i + 12;
	                        while (i < l){
	                            hayColision = disparo.compruebaColisionX(listaAtacantes[i].getPosX(), listaAtacantes[i].getx(), listaAtacantes[i].getDibujar());
	                            if (hayColision) {
	                                listaAtacantes[i].noDibujar();
	                               // iDisparos.remove();
	                                eliminarElemento = true;
	                                i++;
	                            } else {
	                                i++;
	                            }
	                        }
	                    }
	                } 
                }
            }
            if (eliminarElemento){
                iDisparos.remove();
            }
        }
    }
  
    private void Ataques(){
          
        int cicloMasCercano = tmpAtaqueCercano;
        int cicloEstructura = tmpAtaqueEstructura;
        int cicloAleatorio = cicloEstructura;
        int cicloFin = cicloAleatorio + cicloEstructura;
      
        //Sacamos la lista de candidatos de disparos activas
          
        ArrayList<Atacante> candidatosDisparos = new ArrayList<Atacante>();
          
        int k = 0;
        for(int i = 11; i >= 0; i--){
            for (int f = 4; f >= 0; f--){
                k = f * 12 + i;
                try{
                    if(listaAtacantes[k].getDibujar()){
                        candidatosDisparos.add( listaAtacantes[k]);
                        break;
                    }
                } catch(NullPointerException e) {
                    //
                }
            }
        }
          
        //Disparo desde el invaders más cercano
        if (ciclosDisparos == cicloMasCercano){
            try {
                candidatosDisparos = disparoMasCercano(candidatosDisparos);
            } catch (IndexOutOfBoundsException e){
                //
            }
        }
  
        //Disparo sobre una estructura al azar.
        if (ciclosDisparos == cicloEstructura){
            candidatosDisparos = disparoEstructura(candidatosDisparos);
        }
          
        //Disparo sobre un lugar al azar.
        if (ciclosDisparos == cicloAleatorio){
            candidatosDisparos = disparoAzar(candidatosDisparos);
        }
          
        if (ciclosDisparos == cicloFin){
            ciclosDisparos = 0;
        } else {
            ciclosDisparos++;
        }
    }
      
    private ArrayList<Atacante> disparoMasCercano(
            ArrayList<Atacante> candidatosDisparos) {
        //Disparo sobre el más cercano al jugador
        int posxRelativaMin = -1;
        int puntero = 0;
        for (int i = 0; i < candidatosDisparos.size(); i++){
            candidatosDisparos.get(i).posxRelativa(defensax);
            //System.out.println(candidatosDisparos.get(i).getPosxRelativa());
            if ((posxRelativaMin > candidatosDisparos.get(i).getPosxRelativa()) ||( posxRelativaMin == -1) ){
                posxRelativaMin = candidatosDisparos.get(i).getPosxRelativa();
                puntero = i;
                //System.out.println("es mas cerca el "+puntero+" con valor "+posxRelativaMin);
            } 
        }
        //creo el disparo
        //System.out.println(disparosAtacantes.size());
        disparosAtacantes.add(addDisparoAtacante(candidatosDisparos.get(puntero)));
        candidatosDisparos.remove(puntero);
        //System.out.println(disparosAtacantes.size());
        return candidatosDisparos;
    }
  
    private ArrayList<Atacante> disparoEstructura(
            ArrayList<Atacante> candidatosDisparos) {
    	/*
    	Random r = new Random();
    	try {
    		int f = r.nextInt(candidatosDisparos.size())
    	} catch (Exception e){
    		//
    	}*/
        return candidatosDisparos;
    }
  
    private ArrayList<Atacante> disparoAzar(
        ArrayList<Atacante> candidatosDisparos) {
        Random r = new Random();
        try {
            int f = r.nextInt(candidatosDisparos.size());
 
            disparosAtacantes.add(addDisparoAtacante(candidatosDisparos.get(f)));
            candidatosDisparos.remove(f);
             
        } catch (IllegalArgumentException e){
            //
        }
        return candidatosDisparos;
    }
  
      
  
    private Disparo addDisparoAtacante(Atacante atacante) {
        int xDisparo = 15;
        int yDisparo = 15;
        Disparo disparo = new Disparo(false, (atacante.getPosX() + (atacante.getx() / 2) - (xDisparo /2)), atacante.getPosY(), xDisparo, yDisparo);
  
        return disparo;
    }
}