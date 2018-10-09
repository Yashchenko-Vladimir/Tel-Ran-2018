package hdskhk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class hdskhk {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		list.add(345345);
		list.add(12312);
		list.stream().map(x -> x%10).forEach(System.out::println);
		list.stream().flatMap(x -> Arrays.asList(x.toString().toCharArray()).stream()).forEach(System.out::println);
		
	int	ar[] = {1,2,3,4,5,}; 
	Hashtable set = new Hashtable();
	Arrays.asList(ar).stream().collect(Collectors.toSet()).forEach(System.out:: println);
//	System.out.println(set.toString());
	for(int i = 0; i < 5; i++) {
		set.put( ar[i], i);
	}
	
	System.out.println(set.toString());
	System.out.println(set.toString());
	
	

	}

}
