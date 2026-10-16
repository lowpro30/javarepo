import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

public class ApiPutExample {
    public static void main(String[] args) {
        try {
            // Create an HttpClient
            HttpClient client = HttpClient.newHttpClient();

            // Define the API endpoint URL
            String apiUrl = "https://api.example.com/resource/123"; // Replace with your API endpoint

            // Create the JSON payload for the PUT request
            String jsonPayload = "{ \"name\": \"Updated Name\", \"description\": \"Updated Description\" }";

            // Create an HttpRequest for the PUT method
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .header("Content-Type", "application/json")
                    .PUT(BodyPublishers.ofString(jsonPayload))
                    .build();

            // Send the request and get the response
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

            // Print the response status code and body
            System.out.println("Response Status Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
