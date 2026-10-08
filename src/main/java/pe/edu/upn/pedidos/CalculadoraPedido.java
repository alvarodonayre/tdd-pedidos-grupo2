package pe.edu.upn.pedidos;

import java.math.BigDecimal;
import java.util.List;

public class CalculadoraPedido {

    public BigDecimal calcularSubtotal(List<Producto> productos) {
        BigDecimal subtotal = BigDecimal.ZERO;

        for (Producto producto : productos) {
            subtotal = subtotal.add(
                    producto.precio().multiply(
                            BigDecimal.valueOf(producto.cantidad())
                    )
            );
        }

        return subtotal.setScale(2);
    }
}