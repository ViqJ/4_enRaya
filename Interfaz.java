// Se importan las librerias necesarias
import java.util.Scanner;

/**
 * Clase que gestiona la interaccion con el usuario para el juego.
 * Contiene metodos para iniciar el juego, obtener entrada del usuario y mostrar el tablero.
 * Utiliza la clase Juego para gestionar la logica del juego.
 *
 * @author JJ
 * @version 1.0
 */
public class Interfaz {

    private static Scanner entrada = new Scanner(System.in);
    private static Juego partida;

    /**
     * Constructor de la clase Interfaz.
     */
    public Interfaz(){
        // Constructor vacio
    }
    /**
     * Inicia el juego, solicita la configuracion y gestiona juego.
     */
    public static void iniciarJuego(String [] args){
        // Verifica si se proporciona un parámetro de ayuda desde la línea de comandos
        for (String arg : args) {
            if (arg.equals("-h") || arg.equals("-help")) {
                imprimirAyuda(args);
            }
        }
        boolean reiniciar=false;
        do{
            // Inicia el juego
            iniciarTablero();
            boolean terminarPartida = false;//variable para terminar el juego
            while(true){
                //Ciclo de juego
                for(int i = 0;i<partida.getJugadores().length;i++){
                    //Ciclo de jugadores
                    mostrarTablero(partida.getTablero());
                    //Ciclo de jugadas
                    partida.setTablero(i,obtenervalor(partida.getJugadores()[i].getNombre() +" tu ficha es: "+ partida.getJugadores()[i].getFicha() + " elige una columna valida: ", Juego.obtenerColumnasValidas(partida.getTablero())));
                    //Verificar si el jugador actual gana
                    if(!partidaSigue(partida.getTablero(),i)){
                        mostrarTablero(partida.getTablero());
                        terminarPartida = true;   
                        break;
                    }
                }
                //Verificar si el juego termina
                if(terminarPartida) break;
            }
            reiniciar = 1 == obtenervalor("Desea volver a intentarlo? 1.Si 2.No: ",new int[]{1,2});
        }while(reiniciar);
        //Cierre el scanner
        entrada.close();
    }
    /**
     * Inicia el tablero del juego.
    * Solicita la configuracion del juego al usuario.
    * Crea una instancia de la clase Juego con la configuracion del usuario.
    */
    private static void iniciarTablero(){
        int cantidadJugadores;
        int tamanoMinTableroValido;
        int tamanoFilas;
        int tamanoColumnas;
        int minWin;
        Jugador[] jugadores;
        //Se valida tipo de juego personalizado o clasico
        boolean personalizado = obtenervalor("Digite modo 1.Personalizado 2.Clasico: ", new int[]{1, 2}) == 1;
        if (personalizado){
            //Se solicita configuracion de juego personalizado
            cantidadJugadores = obtenervalor("Digite cantidad de jugadores (debe ser mayor o igual a 1 ): ",1);
            jugadores = obtenerJugadores(cantidadJugadores);
            minWin = obtenervalor(("Digite valor para ganar (recomendado 4): "), 1);
            tamanoMinTableroValido = minWin;
            tamanoFilas = obtenervalor("Digite tamano de fila (debe ser mayor o igual a "+ tamanoMinTableroValido +"): ",tamanoMinTableroValido);
            tamanoColumnas = obtenervalor("Digite tamano de columna (debe ser mayor o igual a "+tamanoMinTableroValido+"): ",tamanoMinTableroValido);
 
        }else{
            //Se establece configuracion de juego clasico
            cantidadJugadores = 2;
            minWin = 4;
            tamanoMinTableroValido = minWin;
            tamanoFilas = 6;
            tamanoColumnas = 7;
            jugadores = obtenerJugadores(cantidadJugadores);
        }
        //Se crea tablero
        char [][] tablero = Juego.crearTablero(tamanoFilas,tamanoColumnas);
        //Se crea instancia de juego
        partida = new Juego(tablero,jugadores,minWin);
    }

    /**
     * Obtiene un valor entero valido y mayor o igual a un minimo.
     * @param consulta  Consulta para el usuario.
     * @param valormin  Valor minimo aceptado.
     * @return El valor entero ingresado por el usuario.
         */
    private static int obtenervalor(String consulta,int valormin){
        int valor = 0;
        while(true){
            System.out.print(consulta);
            if(entrada.hasNextInt()){
                valor = entrada.nextInt();
                if(valor>=valormin) break;
                System.out.println("Valor no valido");
            }else{
            System.out.println("Valor no valido");
            entrada.next();
            }
        }
        return valor;
    }
    /**
     * Crea los jugadores para la partida y devuelve la lista de los mismos.
     * @param cantidadJugadores Cantidad de jugadores para la partida.
     * @return  La lista de jugadores para la partida.
     */
    private static Jugador[] obtenerJugadores(int cantidadJugadores){
        Jugador[] jugadores = new Jugador[cantidadJugadores];

        for(int i = 0;i<cantidadJugadores;i++){
            boolean nombreValido = false;
            boolean fichaValida = false;
            String nombreJugador = "";
            char fichaJugador = ' ';
            while(nombreValido==false){
            nombreJugador = obtenerString("Digite nombre de jugador "+ (i+1) +" :");
            nombreValido = Juego.validarNombre(jugadores,nombreJugador);
            if(!nombreValido) System.out.println("Nombre ya existe");
            }
            jugadores[i] = new Jugador(nombreJugador);
            while(fichaValida==false){
            fichaJugador = obtenerChar("Digite ficha de jugador (caracter <> | ) para el jugador "+ jugadores[i].getNombre()+" :");
            fichaValida = Juego.validarFicha(jugadores,fichaJugador);
            if(!fichaValida) System.out.println("Ficha ya existe");
            }
            jugadores[i].setFicha(fichaJugador);
        }
        return jugadores;
    }
    /**
     * Obtiene un string valido.
     * @param consulta Consulta para el usuario.
     * @return El string ingresado por el usuario.
     */
    private static String obtenerString(String consulta){
        String palabra;
        while(true){
            System.out.print(consulta);
                if(entrada.hasNextInt()){
                    System.out.println("Valor no valido");
                    entrada.next();
                    continue;
                }
                palabra = entrada.next();
                break;
        }
        return palabra;
    }
    /**
     * Obtiene un char valido.
     * @param consulta Consulta para el usuario.
     * @return El char ingresado por el usuario.
     */
    private static char obtenerChar(String consulta){
        String palabra;
        while(true){
            System.out.print(consulta);
                if(entrada.hasNextInt()){
                    System.out.println("Valor no valido");
                    entrada.next();
                    continue;
                }
                palabra = entrada.next();
                if(palabra.length()==1&&palabra!="|"){
                break;
                }else{
                System.out.println("Valor no valido");
                }
        }
        return palabra.charAt(0);
    }


    /**
     * Imprime el tablero actual del juego.
     * @param tableroActual Tablero actual del juego.
     */
    private static void mostrarTablero(char [][] tableroActual){
        String separadorValores = "|";
        String separadorFilas =  "-";
        int largoEspacio = separadorValores.length()*2+1;//uno ya que char es de un solo valor
        System.out.println();//pasamos a siguiente linea al terminar la fila
        for(int i =0;i<tableroActual[0].length;i++){
            System.out.print(" "+(i+1)+"   ");
        }
        System.out.println();//pasamos a siguiente linea al terminar la fila
        for(int i =0;i<tableroActual[0].length;i++){
            System.out.print(" v   ");
        }
        System.out.println();//pasamos a siguiente linea al terminar la fila

        for(int i = 0; i < tableroActual.length;i++){
            for(int c = 0;c < tableroActual[i].length*largoEspacio+(tableroActual[i].length*2)-2;c++){ //imprimimos linea horizontal
                System.out.print(separadorFilas);
            }
            System.out.println();//pasamos a siguiente linea
            
            for(int j = 0;j < tableroActual[i].length;j++){
                System.out.print(separadorValores+tableroActual[i][j]+separadorValores+"  ");//imprimimos valor de fila
            }
            System.out.println();//pasamos a siguiente linea al terminar la fila

            for(int c = 0;c < tableroActual[i].length*largoEspacio+(tableroActual[i].length*2)-2;c++){ //imprimimos linea horizontal
                System.out.print(separadorFilas);
            }
            System.out.println();//pasamos a siguiente linea al terminar la fila
        }
    }

    /**
     * Obtiene un valor entero valido de una lista de valores posibles
     * @param consulta Consulta para el usuario.
     * @param valoresPosibles Lista de valores posibles.
     * @return El valor entero ingresado por el usuario.
     */
    private static int obtenervalor(String consulta,int [] valoresPosibles){
        int valor = 0;
        boolean valorValido = false;
        while(true){
            System.out.print(consulta);
            if(entrada.hasNextInt()){
                valor = entrada.nextInt();
                for(int i=0;i<valoresPosibles.length;i++){
                    if(valoresPosibles[i]!=0&&valor==valoresPosibles[i]){
                        valorValido = true;
                        break;
                    }
                }
                if(valorValido) break;
                System.out.println("Valor no valido");
            }else{
            System.out.println("Valor no valido");
            entrada.next();
            }
        }
        return valor;
    }


    /**
     * Verifica si el juego sigue.
     * @param tableroActual Tablero actual del juego.
     * @param indiceJugadorActual Indice del jugador actual en la lista de jugadores.
     * @return True si el juego sigue, false en caso contrario.
     */
    private static boolean partidaSigue(char[][] tableroActual,int indiceJugadorActual){
        if(Juego.ganaJugador(tableroActual,indiceJugadorActual,partida.getJugadores()[indiceJugadorActual].getFicha(),partida.getMinWin())){
            System.out.println("Felicidades "+ partida.getJugadores()[indiceJugadorActual].getNombre()+" has ganado");
            return false;
        }else if(Juego.tableroLleno(tableroActual)){
            System.out.println("Tablero lleno, nadie gana");
            return false;
        }
    return true;
    }

    /**
     * Verifica si el tablero esta lleno.
     * @param tableroActual Tablero actual del juego.
     * @return True si el tablero esta lleno, false en caso contrario.
     */

    /**
     * Imprime la ayuda del juego.
     * @param args Argumentos de la linea de comandos.
     */
    private static void imprimirAyuda(String[] args){
        System.out.println("Bienvenido al juego de 4 en raya!");
        System.out.println("Como jugar:");
        System.out.println("- Selecciona la opcion 'Clasico' para jugar con las reglas estandar.");
        System.out.println("- Selecciona la opcion 'Personalizado' para personalizar el juego con tus propias reglas.");
        System.out.println("- En el modo 'Personalizado', podras especificar el tamano del tablero, la ficha de cada jugador y el valor para ganar.");
    }

}
