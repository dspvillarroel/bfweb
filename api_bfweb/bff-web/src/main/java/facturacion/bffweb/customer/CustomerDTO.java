package facturacion.bffweb.customer;

import java.sql.Date;

import lombok.Data;

@Data
public class CustomerDTO {
    
    private Long id;
    private String nombre;
    private String cedula;
    private Date fecha_nacimiento;
    private double monto_primera_compra;
    private Long tipo;

}
