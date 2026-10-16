import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;


public class ApiPutExample {
    public static void main(String[] args) {
        try {
            // Create an HttpClient
            HttpClient client = HttpClient.newHttpClient();
//	    String jsonPayload = "{ \"name\": \"Updated Name\", \"description\": \"Updated Description\" }";


            // Define the API endpoint URL
            // String apiUrl = "https://api.example.com/resource/123"; // Replace with your API endpoint
	    
	    // Use the GoLang application for testing locally
           String apiUrl = "http://localhost:8080/resource/123"; // Replace with your API endpoint

            // Use Java Text Blocks (Java 15+) to handle multi-line JSON and quotes without manual escaping
	    String jsonPaylog = Files.readString(
			    Path.of("payload.json"),
			    StandardCharsets.UTF_8
			    );

            // Create an HttpRequest for the PUT method
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .header("Content-Type", "application/json")
		    .PUT(BodyPublishers.ofFile(Path.of("payload.json")))
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
