
public enum UnitWeight {
	GR(1.0f), KG(1000.0f), LBS(453.592f);
	private float value;
	
	private UnitWeight(float value) {
		this.value= value;
	}
	
	public float getValue() {
		return value;
	}
	
	public float convert(UnitWeight other) {
		return value/other.value;
	}

}
