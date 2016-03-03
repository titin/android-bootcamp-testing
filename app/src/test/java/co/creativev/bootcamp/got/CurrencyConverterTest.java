package co.creativev.bootcamp.got;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class CurrencyConverterTest {

    @Test
    public void convert1INRto200IDR() throws IOException{
        CurrencyConverter currencyConverter = new CurrencyConverter();
        double amountCurrency = currencyConverter.currencyConverter(1, "INR");
        assertEquals(200,amountCurrency,0.0);
    }

    @Test
    public void convert2INRto400IDR() throws IOException{
        CurrencyConverter currencyConverter = new CurrencyConverter();
        double amountCurrency = currencyConverter.currencyConverter(2, "INR");
        assertEquals(400,amountCurrency,0.0);
    }

    @Test
    public void convert200IDRto1INR() throws IOException{
        CurrencyConverter currencyConverter = new CurrencyConverter();
        double amountCurrency = currencyConverter.currencyConverter(200, "IDR");
        assertEquals(1,amountCurrency,0.0);
    }

    @Test
    public void convertIDRtoINR() throws Exception {
        CurrencyConverter currencyConverter = new CurrencyConverter();
        double amountCurrency = currencyConverter.currencyConverter(1, "IDR");
        assertEquals(0.05,amountCurrency,0.0);
    }
}