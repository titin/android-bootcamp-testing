package co.creativev.bootcamp.got;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class CurrencyConverterTest {

    @Test
    public void convert1IDRto200INR() throws IOException{
        CurrencyConverter currencyConverter = new CurrencyConverter();
        int amountCurrency = currencyConverter.currencyConverter1(1,"INR");
        assertEquals(200,amountCurrency);
    }

    @Test
    public void convert2IDRto400INR() throws IOException{
        CurrencyConverter currencyConverter = new CurrencyConverter();
        int amountCurrency = currencyConverter.currencyConverter2(2, "INR");
        assertEquals(400,amountCurrency);
    }

    @Test
    public void convertIDRtoINR() throws IOException{
        CurrencyConverter currencyConverter = new CurrencyConverter();
        int amountCurrency = currencyConverter.currencyConverter(2, "INR");
        assertEquals(400,amountCurrency);
    }

    @Test
    public void convertINRtoIDR() throws IOException{
        CurrencyConverter currencyConverter = new CurrencyConverter();
        int amountCurrency = currencyConverter.currencyConverterINRtoIDR(1, "IDR");
        assertEquals(400,amountCurrency);
    }


}