public class CurrencyConversion {
    private String name;
    private double rateToUSD;
    private double rateToAUD;

    public CurrencyConversion(String name, double rateToUSD, double rateToAUD) {
        this.name = name;
        this.rateToUSD = rateToUSD;
        this.rateToAUD = rateToAUD;
    }

    public double convertToUSD(double amount) {
        return amount * rateToUSD;
    }

    public double convertFromUSD(double amount) {
        return amount / rateToUSD;
    }

    public double convertToAUD(double amount) {
        return amount * rateToAUD;
    }

    public double convertFromAUD(double amount) {
        return amount / rateToAUD;
    }
}
