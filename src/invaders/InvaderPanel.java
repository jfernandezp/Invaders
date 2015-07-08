package invaders;
   
import guardados.Guardar;
import invaders.estado.Estado;
import invaders.objetos.Atacante;
import invaders.objetos.Disparo;
import invaders.objetos.Estructura;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JPanel;
   
   
public class InvaderPanel extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public Estado estado; 
    
    public int margenSuperior = 130;
    
    public int defensax;
    public int defensay;
    public int defensatx;
    public int defensaty;
    public int ataqueiy = 5 + margenSuperior;
    public int ataquey = ataqueiy;
    public int ataqueix = 115;
    public int ataquex = ataqueix;
      
    public int ataquetx = 30;
    public int ataquety = 20;
       
    public int ataqueLimitex;
    public int velocidadAtaque = 1; //0; //1;
    public int velocidadDefensa = 8;
    private int desplazamientoyFila = 30;
    int ciclos = 0;
    int ciclosDisparos = 0;
    int limiteiy = (desplazamientoyFila * 12)  + ataqueiy;
    public int limitey = limiteiy;
    private Atacante listaAtacantes[] = new Atacante[60];

    Graphics g2;
       
    int xDD = 5;
    int yDD = 9;
    int posxDD;
    int posyDD;
      
    int ventanatx;
    int ventanaty;
    int margenVentana = 5;
    
    ArrayList<Disparo> disparos = new ArrayList<Disparo>();
    ArrayList<Disparo> disparosAtacantes = new ArrayList<Disparo>();
    ArrayList<Estructura> listaEstructuras = new ArrayList<Estructura>();

	private MenuInGame menu;
    
    private static Random r = new Random();
    
	private boolean salir;
	
	private InvadersMenuInGameKeyListener listenerMenuIngame;
	private InvaderKeyListener listener;

	private boolean pierdeVida = false;

	private int tiempoAvisos = 1000;
      

    public InvaderPanel(final int ventanatx, int ventanaty, Estado estado){
    	
    	this.estado = estado;
    	this.estado.setLimiteY(limitey);
    	this.estado.setPausa(true);
    	salir = false;
    	    	
        defensatx=40;
        defensaty=20;
        defensax = 350 - (defensatx/2);
        defensay = margenSuperior + 440- defensaty;
        ataqueLimitex = ataquex * 2;
        this.ventanatx = ventanatx;
        this.ventanaty = ventanaty;
           
        posxDD = defensax + (defensatx / 2) - (xDD / 2);
        posyDD = defensay - yDD -4;
           
        setBackground(Color.BLACK);
        //Menu
        menu = new MenuInGame(this, ventanatx, ventanaty);

        menu.pulsoPausa(estado.getPausa());
        listenerMenuIngame = new InvadersMenuInGameKeyListener(this);
        addKeyListener(listenerMenuIngame);
   
        //Listener
        listener = new InvaderKeyListener(this,this.estado);    
        addKeyListener(listener);
        setFocusable(true);
        this.setVisible(true);
    }
    
    protected void moverIzquierda(){
    	int nPosicion = defensax - velocidadDefensa;
        if (nPosicion < 0) {
            defensax = 0;
        } else {
            defensax = nPosicion;
        }
    	
    }
    
    protected void moverDerecha(){
        int nPosicion = defensax + velocidadDefensa;
        int limite = ventanatx - margenVentana - (defensatx );
        if (nPosicion > limite) {
            defensax = limite;
        } else {
            defensax = nPosicion;
        }    	
    }
    
    protected void pulsoPausa(){
    	boolean pausa = true;
        if (estado.getPausa()) {
            pausa = false;
        } 
        estado.setPausa(pausa);
        menu.pulsoPausa(pausa);
    }
    
    protected void pulsoGuardar(){
    	if(!estado.getPausa()){
	    	pulsoPausa();
	    	menu.pulsoGuardar();
    	} else {
    		menu.pulsoGuardar();
    	}
    }
    
    protected void pulsoMenu(){
    	menu.pulsoMenu();
    }

	public void pulsoEscape() {
		menu.pulsoEscape();
	}

	public void pulsoEnter() {
		menu.pulsoEnter();		
	}
	
	
    protected void lanzoDisparo(){
    	 int posx = defensax + defensatx/2 - xDD / 2;
         disparos.add(new Disparo(true, posx, posyDD, xDD, yDD));
    }


	public void setEstado(int nivel, int vidas, int maxVidas, int velocidad,
			int disparoCercano, int disparoEstructura, int disparoAzar) {		
	}
      
    //Bucle del juego:
    public void bucle() {
    	while(!estado.getFinJuego() && !this.salir){
	        try {
				Thread.sleep(1000/estado.getVelocidad());
			} catch (InterruptedException e) {
				//
			}
	        
	        super.repaint();
	        	        
	        if (!estado.getPausa()){	  
	        	perdidaVida();
	        	
	        	comprobarEstado();
	        	
	            intentarBajarFila(1); 
	              
	            desplazamientoHorizontal();
	              
	            comprobarColisionesDisparosAtaque();
	              
	            comprobarColisionesDisparosDefensa();
	           
	            Ataques();
	        }
        }
    	menu.finJuego();
    	try {
			Thread.sleep(tiempoAvisos);
		} catch (InterruptedException e) {
		}
    }
    
    //Comprueba el estado del juego;
    public void comprobarEstado(){
    	if(Atacante.getNumAtacantes() < 1) {
    		for (int i = 0; i < listaAtacantes.length;i++){
    			listaAtacantes[i].siDibujar();
    			disparos.clear();
    			ataquey = ataqueiy;
    			ataquex = ataqueix;
    		}
			estado.aumentarNivel();
			estado.ganaVida(2);
			limitey = Estructura.getMinPosY();
    	}
    }
  
    public void paintComponent(Graphics g){
		super.paintComponent(g);
        
        g.setColor(Color.WHITE);
        
        Graphics2D g2=(Graphics2D) g;
        pintarLetras(g2);
        
        pintarEstructuras(g);
           
        pintarAtacantes(g);      
           
        pintarDefensa(g);
        
        pintarDisparosAtacantes(g);

        pintarDisparosDefensa(g);
        
        menu.pintarMenu(g);
     }
  
    private void pintarLetras(Graphics2D g2){
    	Font f = new  Font ("SansSerif", Font.BOLD, 12); 
        g2.setFont(f);
        g2.drawString("Tienes "+estado.getVidas()+" vidas, "+estado.getPuntos()+" puntos y estás en el "+estado.getNivel()+"º nivel",  30,  margenSuperior - 10);
    }
    
    private void pintarEstructuras(Graphics g) {
    	if (listaEstructuras.isEmpty()){ //Si no se ha pintado estructuras
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
	        int tex = 25; //tamaño estructura en x;
	        int tey = 15; //tamaño estructura en y;
	        int eposx; //Posicion en x de la estructura
	        int eposyi = margenSuperior + 305; //Posicion en y de la estructura  (inicial)
	        int eposy; //Posicion en y de la estructura (en la estructura) 
	 
	         
	        for (int i = 0; i < 4; i++){
	            int bloqueEstructura = (bloque * (i+1)) -  (tex * estructura[0].length / 2);
	            eposy = eposyi;
	            for (int y = 0; y < estructura.length; y++){
	                eposx = bloqueEstructura;
	                for (int x = 0; x < estructura[y].length; x++){
	                    if (estructura[y][x]){
	                        listaEstructuras.add(new Estructura(g, eposx, eposy, tex, tey, y, x));
	                    }
	                    eposx = eposx+25;
	                }
	                eposy = eposy - 15;
	            }
	        }
        } else { //si ya se han pintado
        	Iterator<Estructura> iEstructura = listaEstructuras.iterator();
        	while (iEstructura.hasNext()){
        		Estructura estructura = iEstructura.next();
        		estructura.pintar(g);
        	}
        }		
	}
    
    private void pintarAtacantes(Graphics g){
    	g.setColor(Color.WHITE);
        
        int filas = 5;
        int columnas = 12;
           
        int posx;
        int posy = ataquey - desplazamientoyFila;
        int col;
        int cuenta = 0;
        while (filas > 0)
        {   
            posx = ataquex;
            posy += desplazamientoyFila;
            col = columnas;
            boolean dibujar=true;
            while (col > 0){
            	
         
                 if (listaAtacantes[cuenta] != null){ //Si está creado
                     dibujar = listaAtacantes[cuenta].getDibujar();
                     listaAtacantes[cuenta].setPosx(posx);
                     listaAtacantes[cuenta].setPosy(posy);
                     listaAtacantes[cuenta].pintar(this, g);
                 } else { //Si no se ha creado
                	 listaAtacantes[cuenta] = new Atacante(this, dibujar, g, posx,posy,ataquetx,ataquety);
                 }
               
                posx+=40;
                  
                col--;
                cuenta++;
            }
            filas--;
        } 
    }
    
    private void pintarDefensa(Graphics g){
    	g.fillRect(defensax,defensay,defensatx,defensaty);
    }

    private void pintarDisparosAtacantes(Graphics g){
    	Iterator<Disparo> iDisparosAtacantes = disparosAtacantes.iterator();
        while(iDisparosAtacantes.hasNext()){
            Disparo t = iDisparosAtacantes.next();
            t.dibujar(g);
        }  
    }
   
    private void pintarDisparosDefensa(Graphics g){
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
        }
    }
      
    private void desplazamientoHorizontal(){
        if (ataquex >= ataqueLimitex){
            velocidadAtaque = velocidadAtaque * -1;
            ciclos++;
        } else if (ataquex <= margenVentana){
            velocidadAtaque = velocidadAtaque * -1;
            ciclos++;
        }
        ataquex += velocidadAtaque;
    }
      
    private void comprobarColisionesDisparosAtaque(){
    	boolean tocanDefensa = false;
        //es el mismo codigo con escasas variaciones de this.comprobarColisionesDisparosDefensa();
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
                
                //Si el disparo ha llegado a la posición de la estructura
                if (disparo.getposY() >= Estructura.getMinPosY()){
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
                //Miramos si colisiona con el defensor.
                if (!eliminarElemento){
	                if ((disparo.getposY() >= defensay) && ((disparo.getposY() <= defensay + defensaty)) ) {
	                    hayColision = disparo.compruebaColisionX(defensax,defensatx,true);
	                    if (hayColision){
	                    	pierdeVida(1);
	                        tocanDefensa = true;
	                        eliminarElemento = true;	                        
	                    }
	                }
                }
                
                if (eliminarElemento){
                	iDisparos.remove();
                }
            }
        }
        if (tocanDefensa){
        	disparosAtacantes.clear();
        	disparos.clear();
        }
    }
      
    private void comprobarColisionesDisparosDefensa(){
        Iterator<Disparo> iDisparos = disparos.iterator();
          
        while(iDisparos.hasNext()){
            boolean hayColision;
            boolean hayColisionEstructura;
            boolean eliminarElemento = false;
            Disparo disparo = iDisparos.next();
            
            //Si ha sobrepasado el límite superior.
            if (disparo.getposY() <= 0 ) {
                iDisparos.remove();
            } else {
            	//Se mueve
                disparo.mover();
                
                //Comprobamos si hay colisión con estructuras.
                listaEstructuras.get(0); //aseguramos que esté en 0.                
				if (disparo.getposY() <= Estructura.getMaxPosY()){
	                Iterator<Estructura> iEstructuras = listaEstructuras.iterator();
	                while (iEstructuras.hasNext() && !eliminarElemento){
	                	
	                	Estructura estructura = iEstructuras.next();
	                	
	                	if (disparo.getposY() <= estructura.getPosYbaja() && estructura.getDibujar()){
		                	hayColisionEstructura = disparo.compruebaColisionX(estructura.getposX(), estructura.getX(), estructura.getDibujar());
		                	if (hayColisionEstructura){
		                		estructura.tocada();
		                		estado.ganaPuntos(-1);
		                		eliminarElemento = true;
		                	}
	                	}
	                }
                }
                
				//Comprobamos si hay colisión con los atacantes.
                if (!eliminarElemento){
	                for (int i = 0; i < listaAtacantes.length; i = i + 12){
	                    if (listaAtacantes[i].getPosY() >= disparo.getposY()){
	                        //lógica de detección colisiones en x
	                        int l = i + 12;
	                        while (i < l){
	                            hayColision = disparo.compruebaColisionX(listaAtacantes[i].getPosX(), listaAtacantes[i].gettx(), listaAtacantes[i].getDibujar());
	                            if (hayColision) {
	                                listaAtacantes[i].noDibujar();
	                               	estado.ganaPuntos(1);
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
          
        int cicloMasCercano = estado.getDisparoCercano();
        int cicloEstructura = estado.getDisparoEstructura();
        int cicloAleatorio = estado.getDisparoAzar();
        int cicloFin = cicloAleatorio + cicloMasCercano; //momento fin de ciclo
      
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
          
        if (ciclosDisparos >= cicloFin){
            ciclosDisparos = 0;
        } else {
            ciclosDisparos++;
        }
    }
      
    private ArrayList<Atacante> disparoMasCercano(
            ArrayList<Atacante> candidatosDisparos) {
        //Disparo sobre el más cercano al jugador
        
    	int puntero = obtenerQuienDispara(candidatosDisparos,defensax);
        //creo el disparo
        disparosAtacantes.add(addDisparoAtacante(candidatosDisparos.get(puntero)));
        candidatosDisparos.remove(puntero);
        return candidatosDisparos;
    }
  
    private ArrayList<Atacante> disparoEstructura(
            ArrayList<Atacante> candidatosDisparos) {
    	//Obtener estructuras;
    	
    	ArrayList<Estructura> candidatosEstructuras = new ArrayList<Estructura>();
    	Iterator<Estructura> iEstructuras = listaEstructuras.iterator();
    	while (iEstructuras.hasNext()){
    		Estructura estructura = iEstructuras.next();
    		if (estructura.getDibujar()){
    			candidatosEstructuras.add(estructura);
    		}
    	}
    	
    	
    	try {
    		int f = r.nextInt(candidatosEstructuras.size());
    		candidatosEstructuras.get(f);
    	} catch (Exception e) {
    		if (estado.getEntorno() == 1 ) {
    			System.out.println("Estructura no encontrada");
    		}
    	}
    	
    	//disparo más cercana sobre la estructura
    	int puntoDisparo = defensax; 
    	int puntero = obtenerQuienDispara(candidatosDisparos,puntoDisparo);

        //creo el disparo
        disparosAtacantes.add(addDisparoAtacante(candidatosDisparos.get(puntero)));
        candidatosDisparos.remove(puntero);
        return candidatosDisparos;
    }
  
    private ArrayList<Atacante> disparoAzar(
        ArrayList<Atacante> candidatosDisparos) {
        
        try {
            int f = r.nextInt(candidatosDisparos.size());
 
            disparosAtacantes.add(addDisparoAtacante(candidatosDisparos.get(f)));
            candidatosDisparos.remove(f);
             
        } catch (Exception e){
            //
        }
        return candidatosDisparos;
    }
   
    //Obtener quien está más cerca al punto de disparo.
    private int obtenerQuienDispara(ArrayList<Atacante> candidatosDisparos, int puntoDisparo){
    	//Encuentra el más cercano a un punto.
    	int posxRelativaMin = -1;
        int puntero = 0;
        for (int i = 0; i < candidatosDisparos.size(); i++){
            candidatosDisparos.get(i).posxRelativa(puntoDisparo);
            if ((posxRelativaMin > candidatosDisparos.get(i).getPosxRelativa()) ||( posxRelativaMin == -1) ){
                posxRelativaMin = candidatosDisparos.get(i).getPosxRelativa();
                puntero = i;
            } 
        }
    	
    	return puntero;
    }
    
    //añade atacante.
    private Disparo addDisparoAtacante(Atacante atacante) {
        int xDisparo = 15;
        int yDisparo = 15;
        Disparo disparo = new Disparo(false, (atacante.getPosX() + (atacante.gettx() / 2) - (xDisparo /2)), atacante.getPosY(), xDisparo, yDisparo);
  
        return disparo;
    }
    
    public void pierdeVida(int vidas){
    	pierdeVida = true;
        estado.pierdeVida(vidas);
		menu.pierdeVida(true);
    }
    
    public void perdidaVida(){
    	if (pierdeVida){
    		pierdeVida = false;
    		try {
				Thread.sleep(tiempoAvisos );
			} catch (InterruptedException e) {
			}
    	} else {
    		menu.pierdeVida(false);
    	}
    }

	public void salir() {
		salir = true;
	}

	public void quitarLisener() {
		removeKeyListener(listener);
		removeKeyListener(listenerMenuIngame);
	}

	public void setPausa(boolean pausa) {
		estado.setPausa(pausa);		
	}

	public void guardarPartida(File fichero) {
		try{
			new Guardar(fichero,estado.getEstadoAnterior());
		} catch (Exception e){}
	}
}