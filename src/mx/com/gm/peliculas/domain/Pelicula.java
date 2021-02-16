package mx.com.gm.peliculas.domain;


public class Pelicula {
   
    public Pelicula(){
        
    }
    public Pelicula(String nombre){
        this.nombre = nombre;
    }
    
    private String nombre;
   
    
    public String getNombre(){
        return this.nombre;
        
    }
    
    public void SetNombre(String nombre){
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Pelicula{" + "nombre=" + nombre + '}';
    }
    
    
}
