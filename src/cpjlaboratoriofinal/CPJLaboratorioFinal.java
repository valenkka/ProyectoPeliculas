package cpjlaboratoriofinal;

import java.util.Scanner;
import mx.com.gm.peliculas.datos.AccesoDatosImpl;
import mx.com.gm.peliculas.domain.Pelicula;
import mx.com.gm.peliculas.excepciones.AccesoDatosEx;
import mx.com.gm.peliculas.negocio.CatalogoPeliculasImpl;

public class CPJLaboratorioFinal {

    public static void main(String[] args) throws AccesoDatosEx {
        Scanner in = new Scanner(System.in);
        int opcion = 0;
        CatalogoPeliculasImpl peliculas = new CatalogoPeliculasImpl();
        System.out.println("Ingrese nombre del catalogo");
        String nombreArchivo = in.nextLine();

        while (opcion <= 4 && opcion >= 1) {
            System.out.println("--------------------------------------------------------");
            System.out.println("INGRESE 1 : para iniciar el catalogo de cine con el nombre " + nombreArchivo);
            System.out.println("INGRESE 2 : para agregar una pelicular al catalogo de " + nombreArchivo);
            System.out.println("INGRESE 3 : para hacer un listado del catalogo de " + nombreArchivo);
            System.out.println("INGRESE 4 : para buscar una pelicula en el listado de " + nombreArchivo);
            System.out.println("INGRESE 5 : para salir del menu");
            System.out.println("--------------------------------------------------------");
            opcion = in.nextInt();

            switch (opcion) {
                case 1:
                    peliculas.iniciarArchivo(nombreArchivo);
                    break;
                case 2:
                    in.nextLine();
                    System.out.println("Ingrese el nombre de la pelicula a agregar: ");
                    String nombrePelicula = in.nextLine().toLowerCase();
                    peliculas.agregarPelicula(nombrePelicula, nombreArchivo);
                    break;
                case 3:
                    peliculas.listarPeliculas(nombreArchivo);
                    break;
                case 4:
                    in.nextLine();
                    System.out.println("Ingrese el nombre de la pelicula a buscar");
                    String buscar = in.nextLine().toLowerCase();
                    peliculas.buscarPelicula(nombreArchivo, buscar);
                    break;

            }

        }

    }
}
