
/**
 * Clase que representa un juego.
 * Contiene la información sobre el tablero y los jugadores involucrados en el juego.
 *
 * @author JJ
 * @version 1.0
 */
public class Juego {

    private char[][] tablero;
    private Jugador[] jugadores;
    private int minWin;

    /**
     * Constructor de la clase Juego con tablero y jugadores.
     *
     * @param tablero El tablero del juego.
     * @param jugadores Los jugadores del juego.
     */

    public Juego(char [][] tablero,Jugador[] jugadores,int minWin){
        this.tablero = tablero;
        this.jugadores = jugadores;
        this.minWin = minWin;
    }
    /**
     * Obtiene el tablero actual del juego.
     *
     * @return El tablero actual del juego.
     */
    public char[][] getTablero(){
        return this.tablero;
    }
    /**
     * Obtiene el minimo para ganar
     *
     * @return El mínimo para ganar del juego
     */
    public int getMinWin(){
        return this.minWin;
    }
    /**
     * Obtiene los jugadores del juego.
     *
     * @return Los jugadores del juego.
     */
    public Jugador[] getJugadores(){
        return this.jugadores;
    }
    /**
     * Establece el tablero del juego.
     *
     * @param indiceJugadorActual indice del jugador actual.
     */
    public void setTablero(int indiceJugadorActual, int columnaElegida){
        this.tablero = actualizarTablero(indiceJugadorActual,columnaElegida);
    }
    /**
     * Establece el mínimo para ganar el juego.
     *
     * @param minWin mínimo para ganar el juego.
     */
    public void setMinWin(int minWin){
        this.minWin = minWin;
    }
    /**
     * Crea un tablero vacio con el tamano especificado.
     * @param filas Filas del tablero.
     * @param columnas columnas del tablero.
     * @return El tablero con caracteres en blanco.
     */
    public static char [][] crearTablero(int filas, int columnas){
        char [][] tablero = new char [filas][columnas];
        for(int i = 0; i < tablero.length;i++){
            for(int j = 0;j < tablero[i].length;j++){
                tablero [i][j] = ' ';
            }
        }
        return tablero;
    }
    /**
     * Valida nombre de jugador que no exista ya en la partida.
     * @param jugadores Lista de jugadores de la partida.
     * @param nombreJugador Nombre del jugador a validar.
     * @return  True si el nombre es valido, false en caso contrario.
     */
    public static boolean validarNombre(Jugador[] jugadores,String nombreJugador){
        for(int i = 0; i<jugadores.length;i++){
            if(jugadores[i]!=null&&jugadores[i].getNombre().equals(nombreJugador)) return false;
        }
        return true;
    }
    /**
     * Valida ficha de jugador que no exista ya en la partida.
     * @param jugadores Lista de jugadores de la partida.
     * @param fichaJugador Ficha del jugador a validar.
     * @return True si la ficha es valida, false en caso contrario.
     */
    public static boolean validarFicha(Jugador[] jugadores,char fichaJugador){
        for(int i = 0; i<jugadores.length;i++){
            if(jugadores[i]!=null&&jugadores[i].getFicha()==fichaJugador) return false;
        }
        return true;
    }
    /**
     * Obtiene las columnas validas para realizar una jugada.
     * @param tableroActual Tablero actual del juego.
     * @return La lista de columnas validas para realizar una jugada.
     */
    public static int [] obtenerColumnasValidas(char[][] tableroActual){
        int[] columnasPosibles = new int[tableroActual[0].length];
        int n = 0;
        for (int i = 0; i < tableroActual[0].length; i++) {
            int contador = 0;
            for (int j = 0; j < tableroActual.length; j++) {
                if (tableroActual[j][i] == ' ') {
                    contador++;
                }
            }
            if (contador > 0) {
                columnasPosibles[n] = i + 1;
                n++;
            }
        }
        int[] columnasValidas = new int[n + 1];
        int s = 0;
        for (int i = 0; i < columnasPosibles.length; i++) {
            if (columnasPosibles[i] != 0) {
                columnasValidas[s] = columnasPosibles[i];
                s++;
            }
        }
        return columnasValidas;
    }
    /**
     * Crea un nuevo tablero con la jugada realizada por el jugador actual.
     * @param columnasElegida Columna elegida por el jugador actual.
     * @param indiceJugadorActual Indice del jugador actual en la lista de jugadores.
     * @param tableroActual  Tablero actual del juego.
     * @return El tablero actualizado con la jugada realizada por el jugador actual.
     */
    private char[][] nuevoTablero(int columnasElegida,int indiceJugadorActual,char [][] tableroActual){


        for(int i = tableroActual.length-1;i>=0;i--){
            if(tableroActual[i][columnasElegida-1]==' '){
                tableroActual[i][columnasElegida-1] = this.jugadores[indiceJugadorActual].getFicha();
                break;
            }
        }

        return tableroActual;
    }
    private char[][] actualizarTablero(int indiceJugadorActual,int columnaElegida){
        return nuevoTablero(columnaElegida,indiceJugadorActual,this.tablero);
    }
    /**
     * Verifica si el jugador actual gana.
     * @param tableroActual Tablero actual del juego.
     * @param indiceJugadorActual Indice del jugador actual en la lista de jugadores.
     * @return True si el jugador actual gana, false en caso contrario.
     */
    /**
     * Verifica si el jugador actual gana.
     * @param tableroActual Tablero actual del juego.
     * @param indiceJugadorActual Indice del jugador actual en la lista de jugadores.
     * @return True si el jugador actual gana, false en caso contrario.
     */
    public static boolean ganaJugador(char[][] tableroActual, int indiceJugadorActual,char fichaActual,int minWin) {
        // revisar horizontal
        for (int i = 0; i < tableroActual.length; i++) {
            int contador = 0;
            for (int j = 0; j < tableroActual[i].length; j++) {
                if (fichaActual == tableroActual[i][j]) {
                    contador++;
                    if (contador == minWin) {
                        System.out.println("gana horizontal en fila "+(i+1)+" y columna "+(j+1));
                        return true;
                    }
                } else {
                    contador = 0;
                }
            }
        }
    
        // revisar vertical abajo
        for (int j = 0; j < tableroActual[0].length; j++) {
            int contador = 0;
            for (int i = 0; i < tableroActual.length; i++) {
                if (fichaActual == tableroActual[i][j]) {
                    contador++;
                    if (contador == minWin) {
                        System.out.println("gana vertica en fila "+(i+1)+" y columna "+(j+1));
                        return true;
                    }
                } else {
                    contador = 0;
                }
            }
        }
    
        // revisar abajo derecha
        for (int i = 0; i < tableroActual.length; i++) {
            int contador = 0;
            for (int j = 0; j < tableroActual[i].length; j++) {
                contador = 0;
                for(int n = 0; i+n < tableroActual.length && j+n < tableroActual[i].length; n++) {
                    if(tableroActual[i+n][j+n] == fichaActual) {        
                        contador++;
                        if(contador == minWin){
                            System.out.println("gana diagonal abajo derecha en fila "+(i+1)+" y columna "+(j+1));
                            return true;
                        }
                    } else {
                        contador = 0;
                        break;
                    }
                }
            }
        }
    
        // revisar arriba derecha
        for (int i = 0; i < tableroActual.length; i++) {
            int contador = 0;
            for (int j = 0; j < tableroActual[i].length; j++) {
                contador = 0;
                for(int n = 0; i-n >= 0 && j+n < tableroActual[i].length; n++) {
                    if(tableroActual[i-n][j+n] == fichaActual) {
                        contador++;
                        if(contador == minWin){
                            System.out.println("gana diagonal arriba derecha en fila "+(i+1)+" y columna "+(j+1));
                            return true;
                        }
                    } else {
                        contador = 0;
                        break;
                    }
                }
            }
        }
    
        return false;
    }
    public static boolean tableroLleno(char[][] tableroActual){
        int contador = 0;
        for(int i = 0;i<tableroActual[0].length;i++){
            if(tableroActual[0][i]==' ') contador++;
        }
        if(contador==0) return true;
        return false;
    }

}
