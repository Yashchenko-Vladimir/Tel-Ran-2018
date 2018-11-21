package ImaggaColorsAppl;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.SocketUtils;
import org.springframework.web.client.RestTemplate;

public class ImaggaClientColorsTestAppl {
	 
	static final String URL = "https://api.imagga.com/v2/colors?image_url=";
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
		ResponseEntity<ResultTemplate> response = restTemplate.exchange(URL+image_url, HttpMethod.GET, requestEnttity, ResultTemplate.class);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("|   Color     Parent         Percent |");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		response.getBody().result.colors.image_colors.stream().
		forEach(x-> System.out.printf("|  %-8s | %-12s | %-6.2f  |\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n", x.getClosest_palette_color(),
				x.getClosest_palette_color_parent(), x.getPercent()));
//		System.out.println(response.getBody().result.colors.image_colors);
		
		
		
	}

	

	

}
