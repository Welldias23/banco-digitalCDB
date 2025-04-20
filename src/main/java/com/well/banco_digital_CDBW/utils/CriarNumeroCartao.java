package com.well.banco_digital_CDBW.utils;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class CriarNumeroCartao {

    private final String MASTERCARD_PREFIX = "510510";
    private final String VISA_PREFIX = "424242";

    public String criarNumeroCartao(String bandeira) {
        StringBuilder numeroParcial = new StringBuilder();

        if (bandeira.equalsIgnoreCase("mastercard")) {
            numeroParcial.append(MASTERCARD_PREFIX);
        } else if (bandeira.equalsIgnoreCase("visa")) {
            numeroParcial.append(VISA_PREFIX);
        } else {
            throw new IllegalArgumentException("Bandeira inválida. Use 'mastercard' ou 'visa'.");
        }

        Random random = new Random();

        while (numeroParcial.length() < 15) {
            numeroParcial.append(random.nextInt(10));
        }
        
        int digitoVerificador = calcularDigitoVerificador(numeroParcial.toString());
        numeroParcial.append(digitoVerificador);

        return formatarNumeroCartao(numeroParcial.toString());
    }

    private int calcularDigitoVerificador(String numero) {
        int soma = 0;
        boolean alternar = true;

        for (int i = numero.length() - 1; i >= 0; i--) {
            int n = Character.getNumericValue(numero.charAt(i));
            if (alternar) {
                n *= 2;
                if (n > 9) n -= 9;
            }
            soma += n;
            alternar = !alternar;
        }

        int mod10 = soma % 10;
        return (mod10 == 0) ? 0 : 10 - mod10;
    }

    private String formatarNumeroCartao(String numero) {
        return numero.replaceAll("....(?!$)", "$0 ");
    }
}
