
/**
 * Clase que representa a un jugador en el juego.
 * Esta clase contiene información sobre el nombre y la ficha del jugador.
 *
 * @author JJ
 * @version 1.0
 */


public class Jugador {
    private String nombre;
    private char ficha;

    /**
     * Constructor de la clase Jugador con nombre y ficha.
     *
     * @param nombre El nombre del jugador.
     * @param ficha La ficha que representa al jugador en el juego.
     */

    public Jugador(String nombre,char ficha){
        this.nombre = nombre;
        this.ficha = ficha;
    }
    /**
     * Constructor de la clase Jugador con solo nombre.
     * Este constructor se utiliza cuando no se especifica la ficha.
     *
     * @param nombre El nombre del jugador.
     */

    public Jugador(String nombre){
        this.nombre = nombre;
    }
    /**
     * Obtiene el nombre del jugador.
     *
     * @return El nombre del jugador.
     */

    public String getNombre(){
        return this.nombre;
    }
    /**
     * Obtiene la ficha del jugador.
     *
     * @return La ficha del jugador.
     */
    public char getFicha(){
        return this.ficha;
    }
    /**
     * Establece el nombre del jugador.
     *
     * @param nombre El nuevo nombre del jugador.
     */
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    /**
     * Establece la ficha del jugador.
     *
     * @param ficha La nueva ficha del jugador.
     */
    public void setFicha(char ficha){
        this.ficha = ficha;
    }

}
