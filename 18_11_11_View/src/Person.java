
public class Person {
int id;
String name;
int birthYear;
public Person(int id, String name, int birthYear) {
	super();
	this.id = id;
	this.name = name;
	this.birthYear = birthYear;
}
@Override
public String toString() {
	return "Person [id=" + id + ", name=" + name + ", birthYear=" + birthYear + "]";
}

}
