/**
 * Clase principal que inicia el juego.
 * Se encarga de crear una instancia de la interfaz del juego y verificar si se proporciona
 * un parámetro de ayuda desde la línea de comandos.
 *
 * @author JJ
 * @version 1.0
 */

public class Principal {
    /**
     * Método principal que inicia el juego.
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String [] args){
        // Inicia el juego
        Interfaz.iniciarJuego(args);
    }
    
}
