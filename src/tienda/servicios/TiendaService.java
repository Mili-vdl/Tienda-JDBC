/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda.servicios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import tienda.entidades.Producto;
import tienda.persistencia.DAO;

/**
 *
 * @author milip
 */
public class TiendaService extends DAO {

    public void menuConsultas() throws Exception {
        Scanner scan = new Scanner(System.in);

        int opc;
        do {
            System.out.println("\nMENU CONSULTAS");
            System.out.println("1. Listar el nombre de todos los productos de la tabla producto");
            System.out.println("2. Listar los nombres y los precios de todos los productos");
            System.out.println("3. Listar aquellos productos que su precio esta entre 120 y 202");
            System.out.println("4. Listar los portátiles de la tabla producto");
            System.out.println("5. Listar el nombre y el precio del producto mas barato");
            System.out.println("6. Ingresar un producto a la base de datos");
            System.out.println("7. Ingresar un fabricante a la base de datos");
            System.out.println("8. Editar un producto con datos a eleccion");
            System.out.println("9. SALIR");

            opc = scan.nextInt();

            switch (opc) {
                case 1:
                    consultarBase("select nombre from producto;");
                    mostrarQuery(1);
                    break;
                case 2:
                    consultarBase("select nombre, precio from producto;");
                    mostrarQuery(2);
                    break;
                case 3:
                    consultarBase("select * from producto where precio between 120 and 202;");
                    mostrarQuery(4);
                    break;
                case 4:
                    consultarBase("select * from producto where nombre like'%portatil%';");
                    mostrarQuery(4);
                    break;
                case 5:
                    consultarBase("select nombre, precio from producto order by precio limit 1;");
                    mostrarQuery(2);
                    break;
                case 6:
                    ime("insert into producto values(12,'prueba', 123, 3)");
                    imprimirListaProducto();
                    break;
                case 7:
                    System.out.println("Inserte el codigo del fabricante");
                    String codigo = scan.next();
                    System.out.println("Inserte el nombre del fabricante");
                    String nombre = scan.next();
                    ime("insert into fabricante values('" + codigo + "','" + nombre + "');");
                    consultarBase("select * from fabricante;");
                    mostrarQuery(2);
                    break;
                case 8:
                    System.out.println("Ingrese el codigo del producto que desea editar");
                    String codigoP = scan.next();
                    System.out.println("Ingrese la columna que desea editar");
                    String colum = scan.next();
                    System.out.println("Ingrese el dato nuevo a guardar");
                    String datoN = scan.next();
                    ime("update producto set " + colum + "='" + datoN + "' where codigo = " + codigoP);
                    imprimirListaProducto();
                    break;
                case 9:
                    System.out.println("El programa ha finalizado.");
                    break;
                default:
                    System.out.println("La opcion indicada no es válida.");
                    break;
            }

        } while (opc != 9);
    }

    public Collection<Producto> crearObjetosProducto() throws Exception {
        // crear lista de productos
        Collection<Producto> productos = new ArrayList();

        // int codigo, String nombre, double precio, int codigoFabricante
        consultarBase("select * from producto;");
        while (resultado.next()) {
            Producto p = new Producto();
            p.setCodigo(resultado.getInt(1));
            p.setNombre(resultado.getString(2));
            p.setPrecio(resultado.getDouble(3));
            p.setCodigoFabricante(resultado.getInt(4));

            productos.add(p);
        }
        return productos;
    }

    public void imprimirListaProducto() throws Exception {
        Collection<Producto> productos = crearObjetosProducto();

        for (Producto aux : productos) {
            System.out.println(aux.toString());
        }
    }

}
