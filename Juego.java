
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


    /**
     * Constructor de la clase Juego con tablero y jugadores.
     *
     * @param tablero El tablero del juego.
     * @param jugadores Los jugadores del juego.
     */

    public Juego(char [][] tablero,Jugador[] jugadores){
        this.tablero = tablero;
        this.jugadores = jugadores;
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
     * @param tablero El nuevo tablero del juego.
     */
    public void setTablero(char[][] tablero){
        this.tablero = tablero;
    }

}
