/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda;

import tienda.servicios.TiendaService;

/**
 *
 * @author milip
 */
public class NewMain {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        TiendaService ts = new TiendaService();
        System.out.println("LISTA DE PRODUCTOS ACTUAL");
        ts.imprimirListaProducto();
        ts.menuConsultas();
        
    }
    
}
