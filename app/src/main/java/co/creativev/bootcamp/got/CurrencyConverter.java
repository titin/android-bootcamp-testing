package co.creativev.bootcamp.got;


public class CurrencyConverter {
    public int currencyConverter(int amount, String s) {
        if (s.equals("INR")){
            return amount*200;
        } else {
            return amount/200;
        }
    }

    public int currencyConverter2(int amount2, String inr) {
        return 400;
    }

    public int currencyConverter1(int amount, String inr) {
        return 200;
    }

}
