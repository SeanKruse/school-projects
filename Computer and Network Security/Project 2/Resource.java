import java.util.ArrayList;

public class Resource {
	
	ArrayList<String> permissions = new ArrayList<>();
	
	public void addPermission(String permission) {
		if (!permissions.contains(permission))
			permissions.add(permission);
	}
}
