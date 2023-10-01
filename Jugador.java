

public class Jugador {
    private String nombre;
    private char ficha;

    public Jugador(String nombre,char ficha){
        this.nombre = nombre;
        this.ficha = ficha;
    }
    public Jugador(String nombre){
        this.nombre = nombre;
    }

    public String getNombre(){
        return this.nombre;
    }
    
    public char getFicha(){
        return this.ficha;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setFicha(char ficha){
        this.ficha = ficha;
    }

}
