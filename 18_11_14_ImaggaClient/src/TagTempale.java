import java.util.Map;

public class TagTempale {
	public double confidence;
	public Map<String, String> tag;
	
	public TagTempale() {
		
	}

	public double getConfidence() {
		return confidence;
	}

	public Map<String, String> getTag() {
		return tag;
	}

	@Override
	public String toString() {
		return "TagTempale [confidence=" + confidence + ", tag=" + tag + "]";
	}
	
}
