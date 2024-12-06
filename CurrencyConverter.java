import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CurrencyConverter {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("=== Welcome to the Currency Converter Program ===\n");
        String[] supportedCurrencies = {"USD", "EUR", "INR", "GBP", "AUD", "CAD", "JPY", "CNY"};
        System.out.println("Supported Currencies: ");
        for (String currency : supportedCurrencies) {
            System.out.print(currency + " ");
        }
        System.out.println("\n");

        System.out.print("Enter the base currency (e.g., USD): ");
        String baseCurrency = input.nextLine().trim().toUpperCase();

        System.out.print("Enter the target currency (e.g., INR): ");
        String targetCurrency = input.nextLine().trim().toUpperCase();

        System.out.print("Enter the amount to convert: ");
        double amountToConvert = input.nextDouble();

        try {
            String apiKey = "YOUR_API_KEY";
            String apiUrl = "https://api.exchangerate-api.com/v4/latest/" + baseCurrency;
            URL requestUrl = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
            connection.setRequestMethod("GET");

            int status = connection.getResponseCode();
            if (status == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder responseBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    responseBuilder.append(line);
                }
                reader.close();

                String response = responseBuilder.toString();
                String searchKey = "\"" + targetCurrency + "\":";
                int startIndex = response.indexOf(searchKey) + searchKey.length();
                int endIndex = response.indexOf(",", startIndex);
                if (endIndex == -1) {
                    endIndex = response.indexOf("}", startIndex);
                }
                double exchangeRate = Double.parseDouble(response.substring(startIndex, endIndex).trim());

                double convertedAmount = amountToConvert * exchangeRate;

                System.out.printf("\nConverted Amount: %.2f %s\n", convertedAmount, targetCurrency);
            } else {
                System.out.println("Error: Unable to fetch exchange rates. Please check the currency codes.");
            }
        } catch (Exception ex) {
            System.out.println("An unexpected error occurred: " + ex.getMessage());
        } finally {
            input.close();
        }
    }
}