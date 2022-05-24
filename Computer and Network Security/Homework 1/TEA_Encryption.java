import java.util.*;

public class TEA_Encryption {
    //declare global variables
    static int DeltaOne = 0x11111111;
    static int DeltaTwo = 0x22222222;
    static int[] k = {0x00000000, 0x00000000, 0x00000000, 0x00000000};
    static int[] R = {0x00000000, 0x00000000, 0x00000000};
    static int[] L = {0x00000000, 0x00000000, 0x00000000};

    public void encrypt(){
		//encryption method
		R[1] = L[0] + (((R[0] << 4)+(k[0])) ^ ((R[0] >>> 5)+(k[1])) ^ (R[0] + DeltaOne)); 
        L[1] = R[0];

        R[2] = L[1] + (((R[1] << 4)+(k[2])) ^ ((R[1] >>> 5)+(k[3])) ^ (R[1] + DeltaTwo)); 
        L[2] = R[1];
    }

    public void getk(){
        //Scanner Object
        Scanner scan = new Scanner (System.in);

        System.out.println("Please input K[0] in Hex String (without '0x')");
        k[0] = Integer.parseUnsignedInt(scan.nextLine(), 16);
        
        System.out.println("Please input K[1] in Hex String (without '0x')");
        k[1] = Integer.parseUnsignedInt(scan.nextLine(), 16);

        System.out.println("Please input K[2] in Hex String (without '0x')");
        k[2] = Integer.parseUnsignedInt(scan.nextLine(), 16);

        System.out.println("Please input K[3] in Hex String (without '0x')");
        k[3] = Integer.parseUnsignedInt(scan.nextLine(), 16);

        System.out.println("Please input L[0] in Hex String (without '0x')");
        L[0] = Integer.parseUnsignedInt(scan.nextLine(), 16);

        System.out.println("Please input R[0] in Hex String (without '0x')");
        R[0] = Integer.parseUnsignedInt(scan.nextLine(), 16);
    }
    
    public void printlr(){
        //print out the arrays
        for (int i=0; i < 3; i++){   
            System.out.println("L[" + i + "] = " + Integer.toHexString(L[i]));
        }
        for (int i=0; i < 3; i++){   
            System.out.println("R[" + i + "] = " + Integer.toHexString(R[i]));
        }
    }

    public static void main(String[] args){
        //instantiate objects
        TEA_Encryption t = new TEA_Encryption();
        t.getk();
        t.encrypt();
        t.printlr();
    }
}