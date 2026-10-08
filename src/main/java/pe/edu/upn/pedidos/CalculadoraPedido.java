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

    public BigDecimal aplicarDescuento(
            BigDecimal subtotal,
            BigDecimal porcentaje
    ) {
        BigDecimal descuento = subtotal
                .multiply(porcentaje)
                .divide(new BigDecimal("100"));

        return subtotal.subtract(descuento).setScale(2);
    }


    public BigDecimal calcularImpuesto(BigDecimal baseImponible) {
    BigDecimal porcentajeIgv = new BigDecimal("0.18");

    return baseImponible
            .multiply(porcentajeIgv)
            .setScale(2);
}
}