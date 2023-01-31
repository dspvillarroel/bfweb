package facturacion.bffweb.product;

import lombok.Data;

@Data
public class QParams {
    private int page;
    private int size;
    private String sort;
}
