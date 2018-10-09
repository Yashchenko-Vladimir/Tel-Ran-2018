package telran.util.measure;

public enum LengthUnit {
	MM(1f),CM(10f),IN(25.4f),FT(304.8f),M(1000f);
	private float value;
	private LengthUnit(float value){
		this.value=value;
	}
	public float getValue(){
		return value;
	}
	public float between(Length l1, Length l2){
		Length num1 = l1.convert(this);
		Length num2 = l2.convert(this);
		float res = num2.getNumber() - num1.getNumber();
		return res;
	}

}
