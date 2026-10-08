package pe.edu.upn.pedidos;

import java.math.BigDecimal;

public record Producto(
        String nombre,
        BigDecimal precio,
        int cantidad
) {
    public Producto {
        if (precio.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "El precio no puede ser negativo"
            );
        }

        if (cantidad <= 0) {
            throw new IllegalArgumentException(
                    "La cantidad debe ser mayor que cero"
            );
        }
    }
}