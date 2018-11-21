package telran.sportsmen;

public abstract class AbstractSpotcman implements ISportsman {
	protected String name;
	protected String description;
	
	
	public AbstractSpotcman(String name, String describtion) {
		super();
		this.name = name;
		this.description = describtion;
	}
	
}
