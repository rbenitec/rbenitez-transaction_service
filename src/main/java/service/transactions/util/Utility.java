package service.transactions.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Utility {
    private static final int LONGITUD_CUENTA = 12;
    private static final String NUMERO_BANCO = "9551";
    private static final String SUCURSAL = "212";

    // Constructor privado para evitar instanciación
    private Utility() {
        throw new UnsupportedOperationException("Esta es una clase utilitaria y no debe ser instanciada.");
    }

    /**
     * Genera un número de cuenta bancaria de 12 dígitos.
     *
     * @return Número de cuenta como String.
     */
    public static String generatedAccountNumber() {
        return generatedNumberRandom(LONGITUD_CUENTA);
    }

    /**
     * Genera un CCI (Código de Cuenta Interbancario) de 18 dígitos a partir del número de cuenta.
     *
     * @param numeroCuenta Número de cuenta de 12 dígitos.
     * @return CCI de 18 dígitos como String.
     */
    public static String generatedCCI(String numeroCuenta) {
        if (numeroCuenta == null || numeroCuenta.length() != LONGITUD_CUENTA || !numeroCuenta.matches("\\d+")) {
            throw new IllegalArgumentException("El número de cuenta debe tener exactamente 12 dígitos numéricos.");
        }
        return NUMERO_BANCO.concat(numeroCuenta).concat(SUCURSAL);
    }

    /**
     * Método auxiliar para generar un número aleatorio con una longitud específica.
     *
     * @param longitud Cantidad de dígitos del número a generar.
     * @return Número aleatorio como String.
     */
    private static String generatedNumberRandom(int longitud) {
        Random random = new Random();
        StringBuilder numero = new StringBuilder();
        for (int i = 0; i < longitud; i++) {
            numero.append(random.nextInt(10));
        }
        return numero.toString();
    }

    /**
     * Método para obtener la fecha y hora actual en formato "dd-MM-yyyy HH:mm:ss".
     *
     * @return Fecha y hora como String.
     */
    public static String getDateTimeNow() {
        LocalDateTime hour = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return hour.format(formatter);
    }
}
