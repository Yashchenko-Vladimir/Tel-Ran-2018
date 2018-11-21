package telran.table;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import telran.annotations.Id;
import telran.annotations.Index;
import telran.annotations.Table;

public class TableClassInfo {

	public static void displayInfo(Object obj) {
		Field[] fields = obj.getClass().getDeclaredFields();
		
		printNameClass(obj);

		printFieldWithAnnotatitionIndex(fields);

		printFieldAnnotId(obj, fields);

	}

	private static void printFieldAnnotId(Object obj, Field[] fields) {
		Field res = null;
		for (Field field : fields) {
			if (field.isAnnotationPresent(Id.class)) {
				if(res != null)
					throw new RuntimeException("id duplication");
				res = field;
			}
		}
		if(res == null) {
			throw new RuntimeException("no id");
		}
		res.setAccessible(true);		
		try {
			System.out.println("primary key value: " + res.get(obj));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}

	private static void printFieldWithAnnotatitionIndex(Field[] fields) {
		List<Field> uniqueIndex = new ArrayList<>();
		List<Field> unUniqueIndex = new ArrayList<>();
		for (Field field : fields) {
			if (field.isAnnotationPresent(Index.class)) {
				if (field.getAnnotation(Index.class).unique())
					uniqueIndex.add(field);
				else
					unUniqueIndex.add(field);
			}
		}
		uniqueIndex.forEach(field -> System.out.println("Unique index : " + field.getName()));
		unUniqueIndex.forEach(field -> System.out.println("Ununique index : " + field.getName()));
		
	}

	private static void printNameClass(Object obj) {
		System.out.print("Table name: ");
				if (obj.getClass().isAnnotationPresent(Table.class)) {
			System.out.println(obj.getClass().getAnnotation(Table.class).name());
		} else {
			System.out.println(obj.getClass().getSimpleName());
		}
	}
}
