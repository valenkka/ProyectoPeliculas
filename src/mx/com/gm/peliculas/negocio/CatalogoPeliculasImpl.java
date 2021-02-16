package mx.com.gm.peliculas.negocio;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.com.gm.peliculas.datos.AccesoDatosImpl;
import mx.com.gm.peliculas.datos.IAccesoDatos;
import mx.com.gm.peliculas.domain.Pelicula;
import mx.com.gm.peliculas.excepciones.AccesoDatosEx;
import mx.com.gm.peliculas.excepciones.EscrituraDatosEx;
import mx.com.gm.peliculas.excepciones.LecturaDatosEx;

public class CatalogoPeliculasImpl implements CatalogoPeliculas {

    private final IAccesoDatos datos;
    
    public CatalogoPeliculasImpl(){
        this.datos = new AccesoDatosImpl();
    }
    
    
    @Override
    public void agregarPelicula(String nombrePelicula, String nombreArchivo) {
        Pelicula pelicula = new Pelicula(nombrePelicula);
        try {
            this.datos.escribir(pelicula, nombreArchivo, true);
        } catch (EscrituraDatosEx ex) {
            ex.getMessage();
        }
    }

    @Override
    public void listarPeliculas(String nombreArchivo) {
        try {
            List<Pelicula> peliculas = this.datos.listar(nombreArchivo);
            for(Pelicula elemento: peliculas){
            System.out.println(elemento.getNombre());
        }
        System.out.println("-----------------------------");
        } catch (LecturaDatosEx ex) {
            ex.getMessage();
        }

    }

    @Override
    public void buscarPelicula(String nombreArchivo, String buscar) {
        try {
            if(this.datos.existe(nombreArchivo)){
                try {
                    System.out.println(this.datos.buscar(nombreArchivo, buscar));
                } catch (LecturaDatosEx ex) {
                    ex.getMessage();
                }
            }else{
                System.out.println("El Catalogo "+ nombreArchivo + " no existe");
            }
            
        } catch (AccesoDatosEx ex) {
            ex.getMessage();
        }
    }



@Override
        public void iniciarArchivo(String nombreArchivo) {
        
        try {
            if(this.datos.existe(nombreArchivo)){
                this.datos.borrar(nombreArchivo);
                this.datos.crear(nombreArchivo);
            }else{
                this.datos.crear(nombreArchivo);
            }
            
            
            /*    try {
            this.datos.crear(nombreArchivo);
            } catch (AccesoDatosEx ex) {
            ex.getMessage();
            }
        */      } catch (AccesoDatosEx ex) {
            ex.getMessage();
        }
    }

}
