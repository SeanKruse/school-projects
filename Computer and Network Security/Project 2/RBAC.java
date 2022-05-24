import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RBAC {
	public static ArrayList<Role> listOfRoles = new ArrayList<>();
	public static ArrayList<String> resources = new ArrayList<>();
	public static ArrayList<String> ssd = new ArrayList<>();
	public static String[][] roleObjectMatrix = null;
	public static boolean roleValidity = true;
	public static boolean resourceValidity = true;
	public static boolean ssdValidity = true;
	
	public ArrayList<Role> readRoleHierarchy() throws FileNotFoundException {
		int count = 0;
		ArrayList<String> roleNames = new ArrayList<>();
  		Scanner hierarchyReader = new Scanner(new File("roleHierarchy.txt"));
  		while (hierarchyReader.hasNextLine()) {
  			count++;
			String s = hierarchyReader.nextLine();
    		String[] breakdown = s.split("	");
    		Role temp1 = null;
    		Role temp2 = null;
    		if (!roleNames.contains(breakdown[0])) {
				temp1 = new Role(breakdown[0]);
			}
    		else {
				for (Role listOfRole: listOfRoles) {
					if (listOfRole.name.equals(breakdown[0]))
						temp1 = listOfRole;
				}
			}
    		if (temp1.descendant != null) {
    			roleValidity = false;
				System.out.println("Invalid line found at line: " + count + ", enter any key to read the file again.");
				Scanner kb = new Scanner(System.in);
				String thisVariableIsNotEverGoingToBeUsed = kb.nextLine();
				break;
			}
    		if (!roleNames.contains(breakdown[1])) {
				temp2 = new Role(breakdown[1]);
			}
    		else {
				for (Role listOfRole: listOfRoles) {
					if (listOfRole.name.equals(breakdown[1]))
						temp2 = listOfRole;
				}
			}
			if (!roleNames.contains(temp1.name)) {
				roleNames.add(temp1.name);
				listOfRoles.add(temp1);
			}
			if (!roleNames.contains(temp2.name)) {
				roleNames.add(temp2.name);
				listOfRoles.add(temp2);
			}
			temp1.setDescendant(temp2);
		}
		if (roleValidity) return listOfRoles;
		else return null;
  	}
  	
  	public ArrayList<String> readResources() throws FileNotFoundException {
        Scanner input = new Scanner(new File("resourceObjects.txt"));
        while (input.hasNext()) {
        resources.add(input.next());
        }

        for(int i = 0; i < resources.size(); i++){
          for(int j = i+1; j <resources.size(); j++){
            if(resources.get(j).equals(resources.get(i))) {
            	resourceValidity = false;
            	System.out.println("duplicate object is found: " + resources.get(j) + ", enter any key to read it again");
            	Scanner kb = new Scanner(System.in);  
            	String pause = kb.nextLine();
            }
          }
        }
        if (resourceValidity) return resources;
        else return null;
	}
	
	public ArrayList<String> checkSSD() throws FileNotFoundException { 
		
		ArrayList<String> mutual = new ArrayList<>();
      	Scanner scan = new Scanner(new File("roleSetsSSD.txt"));
      	while (scan.hasNextLine()) {
        	mutual.add(scan.nextLine());
      	}
        for(int i = 0; i < mutual.size(); i++){
          String[] num = mutual.get(i).split("\\s+");
          int count = Integer.parseInt(num[0]);
          if (count < 2) {
          	ssdValidity = false;
            System.out.println("invalid line is found in roleSetsSSD.txt: line: " + (i + 1) + ", enter any key to read it again");
            Scanner kb = new Scanner(System.in);  
            String thisVariableIsNotEverGoingToBeUsed = kb.nextLine();
          }
        }
      if (ssdValidity) return mutual;
      else return null;
	}// end checkSSD
	
	public String[][] createRoleObjectMatrix() {

    	roleObjectMatrix = new String[listOfRoles.size() + 1][resources.size() + 1];
    	roleObjectMatrix[0][0] = "";
		
    	for (int i = 0; i < listOfRoles.size(); i++) {
    		roleObjectMatrix[i + 1][0] = listOfRoles.get(i).name;
		}
    	
    	for (int j = 0; j < resources.size(); j++) {
    		roleObjectMatrix[0][j + 1] = resources.get(j);
		}
    	return roleObjectMatrix;
    } // end createRoleObjectMatrix
	
	public static void main(String[] args) throws FileNotFoundException {
		RBAC self = new RBAC();
		listOfRoles = self.readRoleHierarchy();
		resources = self.readResources();
		ssd = self.checkSSD();
		roleObjectMatrix = self.createRoleObjectMatrix();
		
		if (!resourceValidity) {
			if (resources == null)
				resources = new ArrayList<>();
			resourceValidity = true;
			resources = self.readResources();
		}
		
		if (!resourceValidity) {
			System.out.println("File is still invalid. Please re-run the program to try again.");
			System.exit(-1);
		}
		
		if (!ssdValidity) {
			if (ssd == null)
				ssd = new ArrayList<>();
			ssdValidity = true;
			ssd = self.checkSSD();
		}
		
		if (!ssdValidity) {
			System.out.println("File is still invalid. Please re-run the program to try again.");
			System.exit(-1);
		}
		
		if (!roleValidity) {
			if (listOfRoles == null)
				listOfRoles = new ArrayList<>();
			roleValidity = true;
			listOfRoles = self.readRoleHierarchy();
		}
		
		if (!roleValidity) {
			System.out.println("File is still invalid. Please re-run the program to try again.");
			System.exit(-1);
		}
		
		if (listOfRoles != null) {
			Role currentRole;
			Role parentRole;
		
			for (int i = 0; i < listOfRoles.size(); i++) {
				currentRole = listOfRoles.get(i);
				parentRole = currentRole.descendant;
				while (parentRole != null) {
					parentRole.ascendants.add(currentRole);
					parentRole = parentRole.descendant;
				}
			}
		
			for (int i = 0; i < listOfRoles.size(); i++) {
				currentRole = listOfRoles.get(i);
				ArrayList<String> rootAscendants = new ArrayList<>();
				if (currentRole.descendant == null) {
				
					for (int j = 0; j < currentRole.ascendants.size(); j++) {
						Role nextRole = currentRole.ascendants.get(j);
						if (nextRole.descendant.equals(currentRole))
							rootAscendants.add(nextRole.name);
					}
					System.out.print(currentRole.name + "--->");
					for (int m = 0; m < rootAscendants.size() - 1; m++) 
						System.out.print(rootAscendants.get(m) + ", ");
					System.out.print(rootAscendants.get(rootAscendants.size() - 1));
					System.out.println();
				}
			}
		
			for (int j = 0; j < listOfRoles.size(); j++) {
				currentRole = listOfRoles.get(j);
				if (currentRole.descendant != null) {
					ArrayList<String> immediateAscendants = new ArrayList<>();
					System.out.print(currentRole.name + "--->");
					if (!currentRole.ascendants.isEmpty()) {
						for (int k = 0; k < currentRole.ascendants.size(); k++) {
							Role nextRole = currentRole.ascendants.get(k);
							if (nextRole.descendant.equals(currentRole))
								immediateAscendants.add(nextRole.name);
						}
						for (int m = 0; m < immediateAscendants.size() - 1; m++) 
							System.out.print(immediateAscendants.get(m) + ", ");
						System.out.print(immediateAscendants.get(immediateAscendants.size() - 1));
					}
					System.out.println();
				}
			}
		
			for (int i = 0; i < listOfRoles.size(); i++) {
			currentRole = listOfRoles.get(i);	
			System.out.println("Role: " + currentRole.name);
				if (currentRole.ascendants.isEmpty())
					System.out.println(currentRole.name + " has no ascendants.");
				else {
					System.out.print(currentRole.name + "'s ascendants: ");
					for (int j = 0; j < currentRole.ascendants.size() - 1; j++)
						System.out.print(currentRole.ascendants.get(j).name + ", ");
					System.out.println(currentRole.ascendants.get(currentRole.ascendants.size() - 1).name);
				}
			}
		}
		System.out.println("Resource objects: " + resources);
		System.out.println("-----Role-Object Matrix-----");
    	for (int i = 0; i < listOfRoles.size(); i++) {
      		for(int j = 0; j < resources.size(); j++) {
        		System.out.print(roleObjectMatrix[i][j] + "	");
        		if (j == resources.size() - 1) System.out.println();
      		}
    	}
    	System.out.println("SSD constraints: " + ssd);
	}
}
