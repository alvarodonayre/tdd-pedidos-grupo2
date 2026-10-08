package pe.edu.upn.pedidos;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculadoraPedidoTest {

    @Test
    void debeCalcularSubtotalDeDosProductos() {
        Producto producto1 = new Producto(
                "Producto 1",
                new BigDecimal("10.00"),
                1
        );

        Producto producto2 = new Producto(
                "Producto 2",
                new BigDecimal("20.00"),
                1
        );

        CalculadoraPedido calculadora = new CalculadoraPedido();

        BigDecimal resultado = calculadora.calcularSubtotal(
                List.of(producto1, producto2)
        );

        assertEquals(new BigDecimal("30.00"), resultado);
    }
}