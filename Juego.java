

public class Juego {

    private char[][] tablero;
    private Jugador[] jugadores;

    public Juego(char [][] tablero,Jugador[] jugadores){
        this.tablero = tablero;
        this.jugadores = jugadores;
    }
    public char[][] getTablero(){
        return this.tablero;
    }
    public Jugador[] getJugadores(){
        return this.jugadores;
    }
    public boolean validarJugadad(int columna){
        return (true);
    }
    public void setTablero(char[][] tablero){
        this.tablero = tablero;
    }

}
