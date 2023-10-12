import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
public class CurrencyConverter {
    private static final String API_KEY = "9fe97e2436db806942f445ca";
    private static final String API_URL = "https://open.er-api.com/v6/latest/" + API_KEY + "/";
    public static void main(String[] args) {
        try {
            JSONObject exchangeRates = getExchangeRates();
            if (exchangeRates != null) {
                System.out.println("Welcome to Currency Converter!");
                System.out.println("Available base currencies: " + exchangeRates.keySet());
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Enter the base currency: ");
                String fromCurrency = reader.readLine().toUpperCase();
                System.out.print("Enter the target currency: ");
                String toCurrency = reader.readLine().toUpperCase();
                if (!exchangeRates.has(fromCurrency) || !exchangeRates.has(toCurrency)) {
                    System.out.println("Invalid currency selection.");
                    return;
                }
                System.out.print("Enter the amount to convert: ");
                double amount = Double.parseDouble(reader.readLine());
                double convertedAmount = currencyConverter(amount, fromCurrency, toCurrency, exchangeRates);
                System.out.println(amount + " " + fromCurrency + " is equal to " + convertedAmount + " " + toCurrency);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject getExchangeRates() throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(API_URL).openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();
            connection.disconnect();

            return new JSONObject(response.toString()).getJSONObject("rates");
        } else {
            System.out.println("Error fetching exchange rates. HTTP Error: " + responseCode);
            return null;
        }
    }

    public static double currencyConverter(double amount, String fromCurrency, String toCurrency, JSONObject exchangeRates) {
        double fromRate = exchangeRates.getDouble(fromCurrency);
        double toRate = exchangeRates.getDouble(toCurrency);

        if (!fromCurrency.equals("USD")) {
            amount = amount / fromRate;
        }

        return amount * toRate;
    }
}
