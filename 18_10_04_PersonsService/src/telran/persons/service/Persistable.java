package telran.persons.service;

import java.io.Serializable;

public interface Persistable extends Serializable{

	void save(String fileName);
	
}
