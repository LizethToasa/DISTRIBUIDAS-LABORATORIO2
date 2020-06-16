
import SistemaCentralizadoMySQL.entidades.Producto;
import SistemaCentralizadoMySQL.session.ProductoJpaController;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Test {
    public static void main(String[] args) {
        //TOMA LOS PARAMETROS ESTABLECIDOS DE LA CADENA DE CONEXION
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("practicabdd");
       //LA CLASE CONTROLADORA RECIBE DE PARAMETRO UNA CADENA DE CONEXION  
        ProductoJpaController pjc = new ProductoJpaController(emf);
       //CREAMOS UN NUEVO PRODUCTO
        Producto p = new Producto();
        
        p.setNombre("MANTEQUILLA");
        Double d1 = 10.35;
        BigDecimal bd1 = BigDecimal.valueOf(d1.doubleValue());
        p.setPrecio(bd1);
        
        try {
            pjc.create(p);
            //pjc.edit(p);
        } catch (Exception ex) {
            Logger.getLogger(Test.class.getName()).log(Level.ALL.SEVERE, null, ex);
        }
    }    
}
