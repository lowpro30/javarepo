import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiGetExample{
	public static void main(String[] args) throws Exception {

		// 1. Create the client (send/request go through this)
		HttpClient client = HttpClient.newHttpClient();

		// 2. Create the request. Build the request
		HttpRequest request = HttpRequest.newBuilder()
			.uri(URI.create("https://www.cloud-resolve.com/"))
			.header("Accept", "application/json")
			.GET()
			.build();

		// 3. Send the request and get the response as a String
		HttpResponse<String> response = client.send(
				request,
				HttpResponse.BodyHandlers.ofString() //give us the body as a string
				);
		System.out.println("Status Code: " + response.statusCode());
		System.out.println("Headers: " + response.headers().map());
		System.out.println("Body:	" + response.body());

	}


}
/* Links:
 Class HttpRequest: https://download.java.net/java/early_access/loom/docs/api/java.net.http/java/net/http/HttpRequest.html

 Class HttpClient: https://download.java.net/java/early_access/loom/docs/api/java.net.http/java/net/http/HttpClient.html

 Class HttpResponse: https://download.java.net/java/early_access/loom/docs/api/java.net.http/java/net/http/HttpResponse.html

*/
