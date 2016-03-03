package co.creativev.bootcamp.got;


public class CurrencyConverter {
    public int currencyConverter(int amount, String s) {
        if (s.equals("INR")){
            return amount*200;
        } else {
            return amount/200;
        }
    }

}
