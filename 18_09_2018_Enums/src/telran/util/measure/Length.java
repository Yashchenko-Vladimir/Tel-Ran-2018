package telran.util.measure;

public class Length {
	private float number;
	private LengthUnit unit;
	public Length(float number, LengthUnit unit) {
		this.number = number;
		this.unit = unit;
	}
	public float getNumber() {
		return number;
	}
	public void setNumber(float number) {
		this.number = number;
	}
	public LengthUnit getUnit() {
		return unit;
	}
	public void setUnit(LengthUnit unit) {
		this.unit = unit;
	}
	@Override
	public String toString(){
		return ""+(int)number+unit;
	}
	public Length plus(Length length) {
		Length len = length.convert(unit);
		float num = number + len.getNumber();
		return new Length(num, unit);
	}
	public Length minus(Length length) {
		Length len = length.convert(unit);
		float num = number - len.getNumber();
		return new Length(num, unit);
	}
	public Length convert(LengthUnit otherUnit) {
		float num = number * (unit.getValue() / otherUnit.getValue());
		return new Length(num, otherUnit);
				
	}
//	public Length convert(LengthUnit otherUnit) {
//        float tmp = this.getUnit().getValue() / otherUnit.getValue();
//        this.setNumber(this.getNumber()*tmp);
//        this.setUnit(otherUnit);
//    return this;
//}
	@Override
	public boolean equals(Object obj){
		Length length=(Length)obj;
		return Float.compare(number, length.number)==0 && unit==length.unit;
	}
}
