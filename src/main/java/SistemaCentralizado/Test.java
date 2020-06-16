package SistemaCentralizado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lizeth
 */
public class Test {
    static Scanner scanner = new Scanner(System.in);
    static int select = -1; 
    public static void main(String[] args) {
        // TODO code application logic here
        Test t  = new Test();
        int select = 1;
        while(select != 0){
            int id = 0; 
            String nombre = "";
            double precio = 0;
            System.out.println("Elige opción:");
            System.out.println("1.- Crear ");
            System.out.println("2.- Actualizar ");
            System.out.println("3.- Eliminar");
            System.out.println("0.- Salir");
            System.out.println("Ingrese el número de la opción:");
            select = Integer.parseInt(scanner.nextLine()); 
            switch(select){
				case 1: 
                                        
                                        System.out.println("Ingrese el id:");
                                        id = Integer.parseInt(scanner.nextLine()); 
                                        System.out.print("Introduzca su nombre: \n");
                                        nombre = scanner.nextLine ();
                                        System.out.print("Introduzca su precio: \n");
                                        precio = Double.parseDouble(scanner.nextLine());
                                        t.insertar(id,nombre,precio);
                                        System.out.print("Registro Exitosa \n");
					break;
				case 2: 
					System.out.println("Ingrese el id:");
                                        id = Integer.parseInt(scanner.nextLine()); 
                                        System.out.print("Introduzca su nombre: \n");
                                        nombre = scanner.nextLine ();
                                        System.out.print("Introduzca su precio: \n");
                                        precio = Double.parseDouble(scanner.nextLine());
                                        t.actualizar(id, nombre, precio);
                                        System.out.print("Actulización Exitosa \n");
					break;
				case 3: 
                                        System.out.println("Ingrese el id:");
                                        id = Integer.parseInt(scanner.nextLine()); 
					t.eliminar(id);
                                        System.out.print("Eliminación Exitosa \n");
					break;
				case 0: 
					System.out.println("Usted a salido del sistema");
					break;
				default:
					System.out.println("Número no reconocido");
                                        break;
				}
        }
        
        System.out.println("\n");
        
        //t.insertar();
        //t.actualizar(2, "PERA", 33.15);
        //t.eliminar(3);
    }
    
    public Connection getConexion() 
    {
        Connection conexion = null;
        
        String servidor = "localhost";
        
        String puerto = "5432";
        
        String baseDatos = "supermercado";
        
        String url = "jdbc:postgresql://" + servidor + ":" + puerto + "/" + baseDatos;
        
        String usuario = "postgres";
        
        String clave = "123456";
        
        try {
            conexion = DriverManager.getConnection(url, usuario, clave);
            
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conexion;
    }
    
    public void insertar(int id, String nombre, double precio) {

        Connection conexion = getConexion();
        
        String sql = "INSERT INTO producto VALUES ('"+id+"','"+nombre+"','"+precio+"')";
        //String sql = "insert into producto values (" + codigo + ",'" + nombre + "','" + precio + "')";
        try (Statement st = conexion.createStatement()) {
            
            //EL executeUpdate ES LA EJECUCIÓN DE LA SENTENCIA
            st.executeUpdate(sql);
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }

    }
    
    
     public void actualizar(int codigo, String nombre, double precio) {
         
        Connection conexion = getConexion();
        String sql = "UPDATE producto SET " + "nombre='" + nombre + "'" + ",precio=" + precio + "WHERE codigo=" + codigo;
        try (Statement st = conexion.createStatement()) {
            
            st.executeUpdate(sql);
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }

    public void eliminar(int codigo) {
        Connection conexion = getConexion();    
        String sql = "DELETE FROM producto WHERE codigo = " + codigo;
        
        try (Statement st = conexion.createStatement()) 
        {
            st.executeUpdate(sql);
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }
}
