package telran.sportsmen;

public class Runner extends AbstractSpotcman{

	public Runner(String name, String description) {
		super(name, description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void action() {
		System.out.println(name + " runner " + description);
		
	}

}
