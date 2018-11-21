package ImaggaColorsAppl;

public class Color {
	String closest_palette_color;
	String closest_palette_color_parent;
	Double percent;
	
	public Color() {
		
	}

	public String getClosest_palette_color() {
		return closest_palette_color;
	}

	public String getClosest_palette_color_parent() {
		return closest_palette_color_parent;
	}

	public Double getPercent() {
		return percent;
	}

	@Override
	public String toString() {
		return "Color [closest_palette_color=" + closest_palette_color + ", closest_palette_color_parent="
				+ closest_palette_color_parent + ", percent=" + percent + "]";
	}
	
	
}
