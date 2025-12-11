import java.util.*;

public class converter {

    // Nested Map: fromCurrency -> (toCurrency -> rate)
    static Map<String, Map<String, Double>> exchangeRates = new HashMap<>();

    static {
        // INR conversions
        Map<String, Double> inrMap = new HashMap<>();
        inrMap.put("AUD", 0.018341325);
        inrMap.put("BDT", 1.4398609);
        inrMap.put("BCH", 0.000032921681177);
        inrMap.put("CAD", 0.016365702);
        inrMap.put("USD", 0.011842903);
        inrMap.put("JPY", 1.6946939);
        inrMap.put("CHF", 0.0097582546);
        inrMap.put("CNH", 0.085451307);
        inrMap.put("HKD", 0.091787983);
        inrMap.put("NZD", 0.019809906);
        exchangeRates.put("INR", inrMap);

        // Add more currency maps similarly...
        Map<String, Double> audMap = new HashMap<>();
        audMap.put("INR", 54.462768);
        audMap.put("BDT", 78.438004);
        audMap.put("BCH", 0.001790426358416);
        audMap.put("CAD", 0.89);
        audMap.put("USD", 0.64488434);
        audMap.put("JPY", 92.28821);
        audMap.put("CHF", 0.53102296);
        audMap.put("CNH", 4.6511974);
        audMap.put("HKD", 4.996069);
        audMap.put("NZD", 1.0789633);
        exchangeRates.put("AUD", audMap);

        // Repeat similar blocks for BDT, BCH, CAD, USD, etc.
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double amount = 0.0;
        while (true) {
            System.out.print("Enter Amount: ");
            try {
                amount = Double.parseDouble(sc.nextLine());
                if (amount < 0) {
                    System.out.println("Amount must be non-negative.");
                } else break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }

        Set<String> currencies = exchangeRates.keySet();
        System.out.println("\nAvailable currencies: ");
        for (String cur : currencies) {
            System.out.print(cur + " ");
        }
        System.out.println();

        System.out.print("Select FROM currency: ");
        String from = sc.nextLine().trim().toUpperCase();

        if (!exchangeRates.containsKey(from)) {
            System.out.println("Currency not supported: " + from);
            sc.close();
            return;
        }

        System.out.print("Select TO currency: ");
        String to = sc.nextLine().trim().toUpperCase();

        if (!exchangeRates.get(from).containsKey(to)) {
            System.out.println("Conversion not supported from " + from + " to " + to);
            sc.close();
            return;
        }

        double rate = exchangeRates.get(from).get(to);
        double converted = amount * rate;

        System.out.printf("%.2f %s = %.5f %s\n", amount, from, converted, to);
        sc.close();
    }
}
