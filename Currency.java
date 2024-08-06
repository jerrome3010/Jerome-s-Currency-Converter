import java.util.Scanner;

public class Currency {
    private String code;
    private CurrencyConversion conversion;
    private static Currency usd;
    private static Currency aud;

    public Currency(String code, CurrencyConversion conversion) {
        this.code = code;
        this.conversion = conversion;
    }

    public double convertToUSD(double amount) {
        return conversion.convertToUSD(amount);
    }

    public double convertFromUSD(double amountInUSD) {
        return conversion.convertFromUSD(amountInUSD);
    }

    public double convertToAUD(double amount) {
        return conversion.convertToAUD(amount);
    }

    public double convertFromAUD(double amountInAUD) {
        return conversion.convertFromAUD(amountInAUD);
    }

    public Currency() {
        menu();
    }

    private void menu() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Welcome to Jerome's Currency Converter!");
            System.out.println("Enter A to use regular exchange rates.");
            System.out.println("Enter B to input your own exchange rates.");
            System.out.println("Enter C to view regular exchange rates.");
            System.out.println("Enter D to view USD to AUD example exchanges.");
            System.out.println("Enter E to view AUD to USD example exchanges.");
            System.out.println("Enter X to exit.");

            char action = readChar(scanner);

            while (action != 'X' && action != 'x') {
                switch (action) {
                    case 'A':
                    case 'a':
                        regularExchange(scanner);
                        break;
                    case 'B':
                    case 'b':
                        customExchange(scanner);
                        break;
                    case 'C':
                    case 'c':
                        exchangeRates();
                        break;
                    case 'D':
                    case 'd':
                        USDtoAUD();
                        break;
                    case 'E':
                    case 'e':
                        AUDtoUSD();
                        break;
                    default:
                        System.out.println("Invalid input, please try again.");
                        break;
                }
                action = readChar(scanner);
            }
        }
    }

    private char readChar(Scanner scanner) {
        System.out.println("Please enter your choice (A, B, C, D, E, X): ");
        return scanner.next().charAt(0);
    }

    private void regularExchange(Scanner scanner) {
        Currency[] currencies = new Currency[2];
        currencies[0] = new Currency("USD", new CurrencyConversion("USD", 1.0, 1.54));
        currencies[1] = new Currency("AUD", new CurrencyConversion("AUD", 0.65, 1.0));

        System.out.print("Enter Currency Code Please (USD, AUD): ");
        String currencyCode = scanner.next();

        Currency fromCurrency = null;
        if (currencyCode.equalsIgnoreCase("AUD")) {
            fromCurrency = currencies[1];
        } else if (currencyCode.equalsIgnoreCase("USD")) {
            fromCurrency = currencies[0];
        } else {
            System.out.println("Invalid currency code!");
            return;
        }

        System.out.print("Enter Selected Currency Code Please (USD, AUD): ");
        String targetCurrencyCode = scanner.next();

        Currency toCurrency = null;
        if (targetCurrencyCode.equalsIgnoreCase("USD")) {
            toCurrency = currencies[0];
        } else if (targetCurrencyCode.equalsIgnoreCase("AUD")) {
            toCurrency = currencies[1];
        } else {
            System.out.println("Invalid currency code!");
            return;
        }

        System.out.print("Enter Amount Please: $");
        double amount = scanner.nextDouble();

        double convertedAmount = toCurrency.convertFromUSD(fromCurrency.convertToUSD(amount));
        System.out.printf("$%.2f %s is equal to $%.2f %s\n", amount, currencyCode, convertedAmount, targetCurrencyCode);
        System.out.println("Thank you for exchanging at Jerome's Bank. Have a great day!");
    }

    private void customExchange(Scanner scanner) {
        System.out.print("Enter AUD exchange rate to USD: ");
        double audRate = scanner.nextDouble();
        aud = new Currency("AUD", new CurrencyConversion("AUD", audRate, 1 / audRate));

        System.out.print("Enter USD exchange rate to AUD: ");
        double usdRate = scanner.nextDouble();
        usd = new Currency("USD", new CurrencyConversion("USD", usdRate, 1 / usdRate));

        System.out.print("Enter Amount Please: $");
        double amount = scanner.nextDouble();

        System.out.print("Enter Currency Code (USD, AUD): ");
        String currencyCode = scanner.next();

        Currency fromCurrency = null;
        if (currencyCode.equalsIgnoreCase("AUD")) {
            fromCurrency = aud;
        } else if (currencyCode.equalsIgnoreCase("USD")) {
            fromCurrency = usd;
        } else {
            System.out.println("Invalid currency code!");
            return;
        }

        System.out.print("Enter Selected Currency Code Please (USD, AUD): ");
        String targetCurrencyCode = scanner.next();

        Currency toCurrency = null;
        if (targetCurrencyCode.equalsIgnoreCase("USD")) {
            toCurrency = usd;
        } else if (targetCurrencyCode.equalsIgnoreCase("AUD")) {
            toCurrency = aud;
        } else {
            System.out.println("Invalid currency code!");
            return;
        }

        double convertedAmount = toCurrency.convertFromUSD(fromCurrency.convertToUSD(amount));
        System.out.printf("$%.2f %s is equal to $%.2f %s\n", amount, currencyCode, convertedAmount, targetCurrencyCode);
        System.out.println("Thank you for exchanging at Jerome's Bank. Have a great day!");
    }

    private void exchangeRates() {
        System.out.println("Current exchange rates:");
        System.out.println("1 AUD = 0.65 USD");
        System.out.println("1 USD = 1.54 AUD");
    }

    private static void USDtoAUD() {
        Currency usd = new Currency("USD", new CurrencyConversion("USD", 1.0, 1.54));
        Currency aud = new Currency("AUD", new CurrencyConversion("AUD", 0.65, 1.0));

        System.out.println("USD to AUD Conversions:");
        for (int i = 1; i <= 50; i++) {
            double amount = i;
            double convertedAmount = aud.convertFromUSD(usd.convertToUSD(amount));
            System.out.printf("$%.2f USD = $%.2f AUD\n", amount, convertedAmount);
        }
    }

    private static void AUDtoUSD() {
        Currency usd = new Currency("USD", new CurrencyConversion("USD", 1.0, 1.54));
        Currency aud = new Currency("AUD", new CurrencyConversion("AUD", 0.65, 1.0));

        System.out.println("AUD to USD Conversions:");
        for (int i = 1; i <= 50; i++) {
            double amount = i;
            double convertedAmount = usd.convertFromAUD(aud.convertToAUD(amount));
            System.out.printf("$%.2f AUD = $%.2f USD\n", amount, convertedAmount);
        }
    }

    public static void main(String[] args) {
        new Currency();
    }
}
