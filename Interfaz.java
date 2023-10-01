// Se importan las librerias necesarias
import java.util.Scanner;

/**
 * Clase que gestiona la interacción con el usuario para el juego.
 * Contiene métodos para iniciar el juego, obtener entrada del usuario y mostrar el tablero.
 * Utiliza la clase Juego para gestionar la lógica del juego.
 *
 * @author JJ
 * @version 1.0
 */
public class Interfaz {

    private static Scanner entrada = new Scanner(System.in);
    private static Juego partida;
    private static int minWin = 4; //valor minimo para ganar por defecto

    /**
     * Constructor de la clase Interfaz.
     */
    public Interfaz(){
        // Constructor vacío
    }
    /**
     * Inicia el juego, solicita la configuración y gestiona juego.
     */
    public void iniciarJuego(){
        // Inicia el juego
        iniciarTablero();
        boolean terminarPartida = false;//variable para terminar el juego
        while(true){
            //Ciclo de juego
            for(int i = 0;i<partida.getJugadores().length;i++){
                //Ciclo de jugadores
                mostrarTablero(partida.getTablero());
                //Ciclo de jugadas
                partida.setTablero(nuevoTablero(elegirJugada(partida.getTablero(),i),i,partida.getTablero()));
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
        //Cierre el scanner
        entrada.close();
    }
    /**
     * Inicia el tablero del juego.
    * Solicita la configuración del juego al usuario.
    * Crea una instancia de la clase Juego con la configuración del usuario.
    */
    private static void iniciarTablero(){
        int cantidadJugadores;
        int tamañoMinTableroValido;
        int tamañoFilas;
        int tamañoColumnas;
        Jugador[] jugadores;
        //Se valida tipo de juego personalizado o clasico
        boolean personalizado = obtenervalor("Digite modo 1.Personalizado 2.Clasico: ", new int[]{1, 2}) == 1;
        if (personalizado){
            //Se solicita configuracion de juego personalizado
            cantidadJugadores = obtenervalor("Digite cantidad de jugadores (debe ser mayor o igual a 1 ): ",1);
            jugadores = obtenerJugadores(cantidadJugadores);
            minWin = obtenervalor(("Digite valor para ganar (recomendado 4): "), 1);
            tamañoMinTableroValido = minWin;
            tamañoFilas = obtenervalor("Digite tamaño de fila (debe ser mayor o igual a "+ tamañoMinTableroValido +"): ",tamañoMinTableroValido);
            tamañoColumnas = obtenervalor("Digite tamaño de columna (debe ser mayor o igual a "+tamañoMinTableroValido+"): ",tamañoMinTableroValido);
 
        }else{
            //Se establece configuracion de juego clasico
            cantidadJugadores = 2;
            minWin = 4;
            tamañoMinTableroValido = minWin;
            tamañoFilas = 6;
            tamañoColumnas = 7;
            jugadores = obtenerJugadores(cantidadJugadores);
        }
        //Se crea tablero
        char [][] tablero = crearTablero(tamañoFilas,tamañoColumnas);
        //Se crea instancia de juego
        partida = new Juego(tablero,jugadores);
    }
    /**
     * Crea un tablero vacío con el tamaño especificado.
     * @param filas Filas del tablero.
     * @param columnas columnas del tablero.
     * @return El tablero con caracteres en blanco.
     */
    private static char [][] crearTablero(int filas, int columnas){
        char [][] tablero = new char [filas][columnas];
        for(int i = 0; i < tablero.length;i++){
            for(int j = 0;j < tablero[i].length;j++){
                tablero [i][j] = ' ';
            }
        }
        return tablero;
    }
    /**
     * Obtiene un valor entero válido y mayor o igual a un minimo.
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
            nombreValido = validarNombre(jugadores,nombreJugador);
            if(!nombreValido) System.out.println("Nombre ya existe");
            }
            jugadores[i] = new Jugador(nombreJugador);
            while(fichaValida==false){
            fichaJugador = obtenerChar("Digite ficha de jugador (caracter <> | ) para el jugador "+ jugadores[i].getNombre()+" :");
            fichaValida = validarFicha(jugadores,fichaJugador);
            if(!fichaValida) System.out.println("Ficha ya existe");
            }
            jugadores[i].setFicha(fichaJugador);
        }
        return jugadores;
    }
    /**
     * Obtiene un string válido.
     * @param consulta Consulta para el usuario.
     * @return El string ingresado por el usuario.
     */
    private static String obtenerString(String consulta){
        String palabra;
        while(true){
            System.out.print(consulta);
                palabra = entrada.next();
                break;
        }
        return palabra;
    }
    /**
     * Obtiene un char válido.
     * @param consulta Consulta para el usuario.
     * @return El char ingresado por el usuario.
     */
    private static char obtenerChar(String consulta){
        String palabra;
        while(true){
            System.out.print(consulta);
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
     * Valida nombre de jugador que no exista ya en la partida.
     * @param jugadores Lista de jugadores de la partida.
     * @param nombreJugador Nombre del jugador a validar.
     * @return  True si el nombre es válido, false en caso contrario.
     */
    private static boolean validarNombre(Jugador[] jugadores,String nombreJugador){
        for(int i = 0; i<jugadores.length;i++){
            if(jugadores[i]!=null&&jugadores[i].getNombre()==nombreJugador) return false;
        }
        return true;
    }
    /**
     * Valida ficha de jugador que no exista ya en la partida.
     * @param jugadores Lista de jugadores de la partida.
     * @param fichaJugador Ficha del jugador a validar.
     * @return True si la ficha es válida, false en caso contrario.
     */
    private static boolean validarFicha(Jugador[] jugadores,char fichaJugador){
        for(int i = 0; i<jugadores.length;i++){
            if(jugadores[i]!=null&&jugadores[i].getFicha()==fichaJugador) return false;
        }
        return true;
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
     * Solicita al usuario la jugada que desea realizar.
     * @param tableroActual Tablero actual del juego.
     * @param indiceJugadorActual Indice del jugador actual en la lista de jugadores.
     * @return La columna elegida por el usuario.
     */
    private static int elegirJugada(char[][] tableroActual,int indiceJugadorActual) {
        int[] columnasPosibles = obtenerColumnasValidas(tableroActual);
        int columnaElegida = obtenervalor(partida.getJugadores()[indiceJugadorActual].getNombre() +" tu ficha es: "+ partida.getJugadores()[indiceJugadorActual].getFicha() + " elige una columna valida: ", columnasPosibles);
        return columnaElegida;
    }
    /**
     * Obtiene un valor entero válido de una lista de valores posibles
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
                    if(valor==valoresPosibles[i]){
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
     * Obtiene las columnas válidas para realizar una jugada.
     * @param tableroActual Tablero actual del juego.
     * @return La lista de columnas válidas para realizar una jugada.
     */
    private static int [] obtenerColumnasValidas(char[][] tableroActual){
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
    private static char[][] nuevoTablero(int columnasElegida,int indiceJugadorActual,char [][] tableroActual){


        for(int i = tableroActual.length-1;i>=0;i--){
            if(tableroActual[i][columnasElegida-1]==' '){
                tableroActual[i][columnasElegida-1] = partida.getJugadores()[indiceJugadorActual].getFicha();
                break;
            }
        }

        return tableroActual;
    }
    /**
     * Verifica si el juego sigue.
     * @param tableroActual Tablero actual del juego.
     * @param indiceJugadorActual Indice del jugador actual en la lista de jugadores.
     * @return True si el juego sigue, false en caso contrario.
     */
    private static boolean partidaSigue(char[][] tableroActual,int indiceJugadorActual){
        if(ganaJugador(tableroActual,indiceJugadorActual)){
            System.out.println("Felicidades "+ partida.getJugadores()[indiceJugadorActual].getNombre()+" has ganado");
            return false;
        }else if(tableroLleno(tableroActual)){
            System.out.println("Tablero lleno, nadie gana");
            return false;
        }
    return true;
    }
    /**
     * Verifica si el jugador actual gana.
     * @param tableroActual Tablero actual del juego.
     * @param indiceJugadorActual Indice del jugador actual en la lista de jugadores.
     * @return True si el jugador actual gana, false en caso contrario.
     */
    private static boolean ganaJugador(char[][] tableroActual, int indiceJugadorActual) {
        char fichaActual = partida.getJugadores()[indiceJugadorActual].getFicha();
    
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
    /**
     * Verifica si el tablero está lleno.
     * @param tableroActual Tablero actual del juego.
     * @return True si el tablero está lleno, false en caso contrario.
     */
    private static boolean tableroLleno(char[][] tableroActual){
        int contador = 0;
        for(int i = 0;i<tableroActual[0].length;i++){
            if(tableroActual[0][i]==' ') contador++;
        }
        if(contador==0) return true;
        return false;
    }
    /**
     * Imprime la ayuda del juego.
     * @param args Argumentos de la línea de comandos.
     */
    public void imprimirAyuda(String[] args){
        System.out.println("Bienvenido al juego de 4 en raya!");
        System.out.println("Cómo jugar:");
        System.out.println("- Selecciona la opción 'Clásico' para jugar con las reglas estándar.");
        System.out.println("- Selecciona la opción 'Personalizado' para personalizar el juego con tus propias reglas.");
        System.out.println("- En el modo 'Personalizado', podrás especificar el tamaño del tablero, la ficha de cada jugador y el valor para ganar.");
    }

}
