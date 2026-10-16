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
//	    String jsonPayload = "{ \"name\": \"Updated Name\", \"description\": \"Updated Description\" }";


            // Define the API endpoint URL
            // String apiUrl = "https://api.example.com/resource/123"; // Replace with your API endpoint
	    
	    // Use the GoLang application for testing locally
           String apiUrl = "http://localhost:8080/resource/123"; // Replace with your API endpoint

            // Use Java Text Blocks (Java 15+) to handle multi-line JSON and quotes without manual escaping
            String jsonPayload = """
                {
                  "schemaId": "builtin:maintenance-windows",
                  "scope": "environment",
                  "value": {
                    "name": "Weekly Synthetic Monitor Maintenance (Fri 19:00 - Mon 01:00)",
                    "enabled": true,
                    "description": "Recurring maintenance for 6 synthetic monitors; Friday 19:00 to Monday 01:00, America/Chicago time.",
                    "schedule": {
                      "trigger": {
                        "time": "19:00",
                        "rule": "<SCHEDULING_RULE_REFERENCE_FROM_STEP_1>",
                        "earliestStart": "2026-10-16"
                      },
                      "timezone": "America/Chicago",
                      "duration": 3240
                    },
                    "filter": "monitor.id IN (\\\"SYNTHETIC_MONITOR-REAL-ID-1\\\", \\\"SYNTHETIC_MONITOR-REAL-ID-2\\\")",
                    "autoDelete": false,
                    "objectScopes": {
                      "syntheticMonitors": {
                        "disableSyntheticMonitors": true,
                        "disableSyntheticMonitorFilter": "monitor.id IN (\\\"SYNTHETIC_MONITOR-REAL-ID-1\\\", \\\"SYNTHETIC_MONITOR-REAL-ID-2\\\")"
                      }
                    }
                  }
                }
                """;


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
