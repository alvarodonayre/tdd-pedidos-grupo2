package pe.edu.upn.pedidos;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class CalculadoraPedido {

    private static final BigDecimal CIEN = new BigDecimal("100");
    private static final BigDecimal IGV = new BigDecimal("0.18");
    private static final int ESCALA = 2;
    private static final RoundingMode REDONDEO = RoundingMode.HALF_UP;

    public BigDecimal calcularSubtotal(List<Producto> productos) {
        BigDecimal subtotal = BigDecimal.ZERO;

        for (Producto producto : productos) {
            subtotal = subtotal.add(
                    producto.precio().multiply(
                            BigDecimal.valueOf(producto.cantidad())
                    )
            );
        }

        return redondear(subtotal);
    }

    public BigDecimal aplicarDescuento(
            BigDecimal subtotal,
            BigDecimal porcentaje
    ) {
        validarPorcentaje(porcentaje);

        BigDecimal descuento = subtotal
                .multiply(porcentaje)
                .divide(CIEN, ESCALA, REDONDEO);

        return redondear(subtotal.subtract(descuento));
    }

    public BigDecimal calcularImpuesto(BigDecimal baseImponible) {
        return redondear(baseImponible.multiply(IGV));
    }

    public BigDecimal calcularTotal(
            List<Producto> productos,
            BigDecimal porcentaje
    ) {
        BigDecimal subtotal = calcularSubtotal(productos);
        BigDecimal baseImponible = aplicarDescuento(subtotal, porcentaje);
        BigDecimal impuesto = calcularImpuesto(baseImponible);

        return redondear(baseImponible.add(impuesto));
    }

    public BigDecimal calcularTotal(List<Producto> productos) {
        return calcularTotal(productos, BigDecimal.ZERO);
    }

    private void validarPorcentaje(BigDecimal porcentaje) {
        if (porcentaje.compareTo(BigDecimal.ZERO) < 0
                || porcentaje.compareTo(CIEN) > 0) {
            throw new IllegalArgumentException(
                    "El porcentaje de descuento debe estar entre 0 y 100"
            );
        }
    }

    private BigDecimal redondear(BigDecimal valor) {
        return valor.setScale(ESCALA, REDONDEO);
    }
}