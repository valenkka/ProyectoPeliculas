package mx.com.gm.peliculas.datos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.com.gm.peliculas.domain.Pelicula;
import mx.com.gm.peliculas.excepciones.AccesoDatosEx;
import mx.com.gm.peliculas.excepciones.EscrituraDatosEx;
import mx.com.gm.peliculas.excepciones.LecturaDatosEx;

public class AccesoDatosImpl implements IAccesoDatos {

    @Override
    public boolean existe(String nombreArchivo) throws AccesoDatosEx {
        File archivo = new File(nombreArchivo); //creamos la variable de tipo file
        return archivo.exists(); // aca preguntamos si existe y retorna verdadero o falso
    }

    @Override
    public List<Pelicula> listar(String nombreArchivo) throws LecturaDatosEx {
        var archivo = new File(nombreArchivo);
        List<Pelicula> Peliculas = new ArrayList<>();
        Pelicula pelicula;
        try {
            var entrada = new BufferedReader(new FileReader(archivo));//leemos lines del archivo
            var lectura = entrada.readLine();//con esto estamos leyendo la primer linea
            while (lectura != null) {//cuando lea una linea vacia va a tirar null
                pelicula = new Pelicula(lectura);
                Peliculas.add(pelicula);
                lectura = entrada.readLine();//repetiimos asi lee la linea de abajoa
            }
            System.out.println("SE HA GENERADO EL CATALOGO DE PELICULAS:" );
            entrada.close(); //cerramos nuestro bufferedReader
            return Peliculas;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
            throw new LecturaDatosEx("Excepcion al listar pelicula" + ex.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
            throw new LecturaDatosEx("Excepcion al listar pelicula" + ex.getMessage());
        }

    }

    @Override
    public void escribir(Pelicula pelicula, String nombreArchivo, boolean anexar) throws EscrituraDatosEx {
        File archivo = new File(nombreArchivo);//cargamos el nombre del achivo a escribir
        try {
            PrintWriter salida = new PrintWriter(new FileWriter(archivo, true)); //aca en este caso lo abrimos por que ya esta creado
            salida.println(pelicula.getNombre());//aca le cargamos el contenido al archivo
            salida.close();//lo cerramos asi se guardan los datos
            System.out.println("Se ha escrito la pelicula");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);//escribimos la exepcion
            throw new EscrituraDatosEx("Excepcion al escribir la pelicula" + ex.getMessage());
            
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new EscrituraDatosEx("Excepcion al escribir la pelicula" + ex.getMessage());
        }
    }

    @Override
    public String buscar(String nombreArchivo, String buscar) throws LecturaDatosEx {
        var archivo = new File(nombreArchivo);
        try {
            var entrada = new BufferedReader(new FileReader(archivo));//leemos lines del archivo
            var lectura = entrada.readLine();//con esto estamos leyendo la primer linea
            int indice = 1;
            while(lectura != null){//cuando lea una linea vacia va a tirar null
                
                //repetiimos asi lee la linea de abajoa
                if(buscar != null && lectura.equals(buscar)){
                    return " Pelicula : " + buscar + " Encontrada en el Indice: " + indice;
                }
                lectura = entrada.readLine();//repetiimos asi lee la linea de abajoa
                indice++;
            }
            entrada.close(); //cerramos nuestro bufferedReader
            return "no se ha encontrado la pelicula";
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);   
            throw new LecturaDatosEx("Excepcion al leer los datos" + ex.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
            throw new LecturaDatosEx("Excepcion al leer los datos" + ex.getMessage());
        }
    }

    @Override
    public void crear(String nombreArchivo) throws AccesoDatosEx {
        File archivo = new File(nombreArchivo);
        try {
            PrintWriter salida = new PrintWriter(archivo);
            salida.close();
            System.out.println("Se ha creado el archivo del catalogo");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            throw new AccesoDatosEx("Excepcion al crear el archivo" + ex.getMessage());
        }
        
    }

    @Override
    public void borrar(String nombreArchivo) throws AccesoDatosEx {
        File archivo = new File(nombreArchivo); //creamos la variable de tipo file
        if(archivo.exists())
        archivo.delete();
        System.out.println("Se ha borrado el archivo " + nombreArchivo);
    }

}
