package telran.sportsmen;

public class FootBaller extends AbstractSpotcman{
	
public FootBaller(String name, String describtion) {
		super(name, describtion);
		// TODO Auto-generated constructor stub
	}

@Override
public void action() {
	System.out.println(name + " footballer " + description);
}
}
