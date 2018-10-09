import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Hi {

	public static void main(String[] args) {
		 Person p1 = new Person("Kosa", 21);
	        Person p2 = new Person("Saosa", 21);
	        Person p3 = new Person("Tiuosa", 22);
	        Person p4 = new Person("Komani", 42);
	        Person p5 = new Person("Kannin", 25);
	        Person p6 = new Person("Kannin", 35);
	        Person p7 = new Person("Tiuosa", 52);
	        ArrayList<Person> list = new ArrayList<>();
	        list.add(p1);
	        list.add(p2);
	        list.add(p3);
	        list.add(p4);
	        list.add(p5);
	        list.add(p6);
	        list.add(p7);

	        // groupingBy
	        Map<Object, List<Person>> list2 = new HashMap<Object, List<Person>>();
	        list2 = list.stream().collect(Collectors.groupingBy(p -> Math.ceil(p.getAge()/10.0), Collectors.toList()));
	        System.out.println("grouping by age -> " + list2.toString());
//	        Collectors.groupingBy(e->Math.ceil(e.age/10.0),Collectors.summingInt(e->e.salary))

	}

}


