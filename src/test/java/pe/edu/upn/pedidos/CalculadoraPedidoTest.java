package pe.edu.upn.pedidos;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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

    @Test
void debeCalcularSubtotalConsiderandoCantidad() {
    Producto producto = new Producto(
            "Producto",
            new BigDecimal("27.00"),
            3
    );

    CalculadoraPedido calculadora = new CalculadoraPedido();

    BigDecimal resultado = calculadora.calcularSubtotal(
            List.of(producto)
    );

    assertEquals(new BigDecimal("81.00"), resultado);
}

@Test
void debeRetornarCeroParaListaVacia() {
    CalculadoraPedido calculadora = new CalculadoraPedido();

    BigDecimal resultado = calculadora.calcularSubtotal(List.of());

    assertEquals(new BigDecimal("0.00"), resultado);
}


@ParameterizedTest
@CsvSource({
        "100.00, 10.00, 90.00",
        "100.00, 0.00, 100.00",
        "100.00, 100.00, 0.00"
})
void debeAplicarDescuento(
        String subtotal,
        String porcentaje,
        String esperado
) {
    CalculadoraPedido calculadora = new CalculadoraPedido();

    BigDecimal resultado = calculadora.aplicarDescuento(
            new BigDecimal(subtotal),
            new BigDecimal(porcentaje)
    );

    assertEquals(new BigDecimal(esperado), resultado);
}


@ParameterizedTest
@CsvSource({
        "100.00, 18.00",
        "90.00, 16.20"
})
void debeCalcularImpuesto(
        String baseImponible,
        String esperado
) {
    CalculadoraPedido calculadora = new CalculadoraPedido();

    BigDecimal resultado = calculadora.calcularImpuesto(
            new BigDecimal(baseImponible)
    );

    assertEquals(new BigDecimal(esperado), resultado);
}

}