import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestClientTestAppl {

	public static void main(String[] args) {
		RestTemplate rest = new RestTemplate();
		String url = "http://jsonplaceholder.typicode.com/posts";
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Post> requestEntity = new HttpEntity<Post>(new Post(1, 1000, "fff", "sgjdf"));
//		ResponseEntity<List<Post>> response = rest.exchange(url,  HttpMethod.GET, null, new ParameterizedTypeReference<List<Post>>() {});
		ResponseEntity<Post> response = rest.exchange(url,  HttpMethod.POST, requestEntity, new ParameterizedTypeReference<Post>() {});
		System.out.println(response.getBody());
		
		
		
	}

	private static Post getMaxBodyPost(List<Post> body) {
		
		return body.stream().collect(Collectors.maxBy((x,y) -> Integer.compare(x.getBody().length(), y.getBody().length()))).orElse(null);
	}

}
