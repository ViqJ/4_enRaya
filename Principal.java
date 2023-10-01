

public class Principal {
    public static void main(String [] args){
        Interfaz partida = new Interfaz();
        for (String arg : args) {
            if (arg.equals("-h") || arg.equals("-help")) {
                partida.imprimirAyuda(args);
            }
        }
        partida.iniciarJuego();
    }
}
