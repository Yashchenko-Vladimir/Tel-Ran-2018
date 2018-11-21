import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.SocketUtils;
import org.springframework.web.client.RestTemplate;

public class ImaggaClientTestAppl {
	static final String URL = "https://api.imagga.com/v2/tags?image_url=";
	static final String AUTH_TOKEN =
			"Basic YWNjXzY0M2QzMzFhMjRhOTZjMDo2ODdjYmVkNWMzOWE3Y2I0N2I0ZTFmZDM2Y2QwNDQ1Yg==";

	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", AUTH_TOKEN);
//		String image_url = "http://chudo-povar.com/images/spagetti-boloneze.jpg";
	    String image_url = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/36/Hopetoun_falls.jpg/1200px-Hopetoun_falls.jpg";
		
	    HttpEntity<String> requestEnttity = new HttpEntity<String>(headers);
//		ResponseEntity<String> response1 = restTemplate.exchange(URL+image_url, HttpMethod.GET, requestEnttity, String.class);
//		System.out.println(response1.getBody());
		ResponseEntity<ResultTemplase> response = restTemplate.exchange(URL+image_url, HttpMethod.GET, requestEnttity, ResultTemplase.class);
//		System.out.println(response.getBody().getResult().getTags().size());
		printConfMore50(response.getBody().getResult().getTags());
		
		
	}

	private static void printConfMore50(List<TagTempale> tags) {
		tags.stream().filter(tag -> tag.getConfidence() > 10).sorted((x, y) -> Double.compare(x.confidence, y.confidence)).
		forEach(tag -> System.out.printf("Confidence %.2f => %s\n", tag.getConfidence(), tag.getTag().get("en")));
		//x -> System.out.println(x.getTag().get("en")
		
	}

	

}
