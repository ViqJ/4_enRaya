

import java.util.Scanner;

public class Interfaz {
    private static Scanner entrada = new Scanner(System.in);
    private static Juego partida;
    private static int minWin = 4;

    public Interfaz(){

    }

    public void iniciarJuego(){
        iniciarTablero();
        boolean terminarPartida = false;
        while(true){
            for(int i = 0;i<partida.getJugadores().length;i++){
                mostrarTablero(partida.getTablero());
                partida.setTablero(nuevoTablero(elegirJugada(partida.getTablero(),i),i,partida.getTablero()));
                if(!partidaSigue(partida.getTablero(),i)){
                    mostrarTablero(partida.getTablero());
                    terminarPartida = true;   
                    break;
                }
            }
            if(terminarPartida) break;
            
        }
        entrada.close();
    }

    private static void iniciarTablero(){
            int cantidadJugadores;
            int tamañoMinTableroValido;
            int tamañoFilas;
            int tamañoColumnas;
            Jugador[] jugadores;
        boolean personalizado = obtenervalor("Digite modo 1.Personalizado 2.Clasico: ", new int[]{1, 2}) == 1;
        if (personalizado){
            cantidadJugadores = obtenervalor("Digite cantidad de jugadores (debe ser mayor o igual a 1 ): ",1);
            jugadores = obtenerJugadores(cantidadJugadores);
            minWin = obtenervalor(("Digite valor para ganar (recomendado 4): "), 1);
            tamañoMinTableroValido = minWin;
            tamañoFilas = obtenervalor("Digite tamaño de fila (debe ser mayor o igual a "+ tamañoMinTableroValido +"): ",tamañoMinTableroValido);
            tamañoColumnas = obtenervalor("Digite tamaño de columna (debe ser mayor o igual a "+tamañoMinTableroValido+"): ",tamañoMinTableroValido);
 
        }else{
            cantidadJugadores = 2;
            minWin = 4;
            tamañoMinTableroValido = minWin;
            tamañoFilas = 6;
            tamañoColumnas = 6;
            jugadores = obtenerJugadores(cantidadJugadores);
        }
       char [][] tablero = crearTablero(tamañoFilas,tamañoColumnas);
        partida = new Juego(tablero,jugadores);
    }

    private static char [][] crearTablero(int filas, int columnas){
        char [][] tablero = new char [filas][columnas];
        for(int i = 0; i < tablero.length;i++){
            for(int j = 0;j < tablero[i].length;j++){
                tablero [i][j] = ' ';
            }
        }
        return tablero;
    }

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

    private static String obtenerString(String consulta){
        String palabra;
        while(true){
            System.out.print(consulta);
                palabra = entrada.next();
                break;
        }
        return palabra;
    }

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

    private static boolean validarNombre(Jugador[] jugadores,String nombreJugador){
        for(int i = 0; i<jugadores.length;i++){
            if(jugadores[i]!=null&&jugadores[i].getNombre()==nombreJugador) return false;
        }
        return true;
    }

    private static boolean validarFicha(Jugador[] jugadores,char fichaJugador){
        for(int i = 0; i<jugadores.length;i++){
            if(jugadores[i]!=null&&jugadores[i].getFicha()==fichaJugador) return false;
        }
        return true;
    }

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
    
    private static int elegirJugada(char[][] tableroActual,int indiceJugadorActual) {
        int[] columnasPosibles = obtenerColumnasValidas(tableroActual);
        int columnaElegida = obtenervalor(partida.getJugadores()[indiceJugadorActual].getNombre() +" tu ficha es: "+ partida.getJugadores()[indiceJugadorActual].getFicha() + " elige una columna valida: ", columnasPosibles);
        return columnaElegida;
    }

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
 
    private static char[][] nuevoTablero(int columnasElegida,int indiceJugadorActual,char [][] tableroActual){


        for(int i = tableroActual.length-1;i>=0;i--){
            if(tableroActual[i][columnasElegida-1]==' '){
                tableroActual[i][columnasElegida-1] = partida.getJugadores()[indiceJugadorActual].getFicha();
                break;
            }
        }

        return tableroActual;
    }

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

    private static boolean ganaJugador(char[][] tableroActual, int indiceJugadorActual) {
        char fichaActual = partida.getJugadores()[indiceJugadorActual].getFicha();
    
        // revisar horizontal
        for (int i = 0; i < tableroActual.length; i++) {
            int contador = 0;
            for (int j = 0; j < tableroActual[i].length; j++) {
                if (fichaActual == tableroActual[i][j]) {
                    contador++;
                    if (contador == minWin) {
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
                        return true;
                    }
                } else {
                    contador = 0;
                }
            }
        }
    
        // revisar abajo izquierda
        for (int i = 0; i < tableroActual.length; i++) {
            int contador = 0;
            for (int j = 0; j < tableroActual[i].length; j++) {
                for(int n = 0; i+n < tableroActual.length && j+n < tableroActual[i].length; n++) {
                    if(tableroActual[i+n][j+n] == fichaActual) {        
                        contador++;
                        if(contador == minWin) return true;
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
                for(int n = 0; i-n >= 0 && j+n < tableroActual[i].length; n++) {
                    if(tableroActual[i-n][j+n] == fichaActual) {
                        contador++;
                        if(contador == minWin) return true;
                    } else {
                        contador = 0;
                        break;
                    }
                }
            }
        }
    
        return false;
    }

    private static boolean tableroLleno(char[][] tableroActual){
        int contador = 0;
        for(int i = 0;i<tableroActual[0].length;i++){
            if(tableroActual[0][i]==' ') contador++;
        }
        if(contador==0) return true;
        return false;
    }

    public void imprimirAyuda(String[] args){
        System.out.println("Bienvenido al juego de 4 en raya!");
        System.out.println("Cómo jugar:");
        System.out.println("- Selecciona la opción 'Clásico' para jugar con las reglas estándar.");
        System.out.println("- Selecciona la opción 'Personalizado' para personalizar el juego con tus propias reglas.");
        System.out.println("- En el modo 'Personalizado', podrás especificar el tamaño del tablero, la ficha de cada jugador y el valor para ganar.");
    }
}
