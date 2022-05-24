import java.util.ArrayList;

public class Role {
	ArrayList<Role> ascendants = new ArrayList<>();
	ArrayList<Resource> resources = new ArrayList<>();
	Role descendant = null;
	String name;
	
	public Role(String name) {
		this.name = name;
	}
	
	public void setDescendant(Role descendant) {
		this.descendant = descendant;
	}
	
	public void addResource(Resource resource) {
		if (!resources.contains(resource))
			resources.add(resource);
	}
	
}
