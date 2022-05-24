//Authors: Devin Schulist and Sean Kruse
//Date:3/27/2022
//Description of program:
//This program is used to retrieve data from a csv file and upload it to a postgresql database. It does this by
//using a regex split statement to break the csv line by line into components or fields which are then fed into
//a series of statements that add escaping characters that postgres would typically have issues with so that we
//can retain the original data while making insert statements syntactically correct. Once the components from the
//csv row have been made correct for postgre we then take the data and feed it into the correct prepared statement
// while storing the key for most of the statements into an arraylist. The arraylist will check for duplicate keys
//so that we do not store the same data twice. This data would be an exact match, and we don't want that. We want
//our data to reference once not match 188000 times. After the statement executes the data is loaded to postgres
//and the process repeats for every row inside postgres. Finally, it is important to note that a config.properties file
//is used to retrieve the server, user, password and database. This is done as a best practice to protect ourselves.
//The file is referenced and the values are loaded into variables used to create the string we use to properly connect
//to a pre-configured user with proper rights and settings.

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.File;
import java.sql.ResultSet;

public class test {
  public static void main(String[] args) throws Exception {
    try{
      File myFile = new File("src/main/java/MUP_IHP_RY21_P02_V10_DY19_PrvSvc_0.csv");
      Scanner myReader = new Scanner(myFile);
      myReader.nextLine(); //skips the column descriptor row

      ArrayList<String> rl = new ArrayList<>();
      ArrayList<String> procl = new ArrayList<String>();
      ArrayList<String> pl = new ArrayList<String>();
      ArrayList<String> fl = new ArrayList<String>();

      while (myReader.hasNextLine()) {
        if (myReader.hasNextLine()) {
          String data = myReader.nextLine();
          String[] arrData = data.split("(?!\\B\"[^\"]*),(?![^\"]*\"\\B)");

          Properties prop = new Properties();
          prop.load(new FileInputStream("src/main/java/config.properties"));

          //pull from config.properties and run connection
          String server = prop.getProperty("server");
          String database = prop.getProperty("database");
          String user = prop.getProperty("user");
          String password = prop.getProperty("password");

          Connection c = null; //to get to server
          Statement stmt = null; //to create quiries and upload to server
          c = DriverManager.getConnection("jdbc:postgresql://"+server+":5432/"+database, user, password);
          c.setAutoCommit(false); //recommended in documentation
          stmt = c.createStatement();

          //Need to clean out apostrophes from inserts while keeping original sentence
          //pull single quote keep chars
          if(arrData[1].contains("\'")){
            arrData[1] = arrData[1].replaceAll("\\b'\\b","''");
            arrData[1] = arrData[1].replaceAll("\\b' ","'' ");
            arrData[1] = arrData[1].replaceAll(" '"," ''");
          }
          if(arrData[2].contains("\'")){
            arrData[2] = arrData[2].replaceAll("\\b'\\b","''");
            arrData[2] = arrData[2].replaceAll("\\b' ","''");
            arrData[2] = arrData[2].replaceAll(" '"," ''");
          }
          if(arrData[3].contains("\'")){
            arrData[3] = arrData[3].replaceAll("\\b'\\b","''");
            arrData[3] = arrData[3].replaceAll("\\b' ","''");
            arrData[3] = arrData[3].replaceAll(" '"," ''");
          }
          if(arrData[8].contains("\'")){
            arrData[8] = arrData[8].replaceAll("\\b'\\b","''");
            arrData[8] = arrData[8].replaceAll("\\b' ","''");
            arrData[8] = arrData[8].replaceAll(" '"," ''");
          }
          if(arrData[10].contains("\'")){
            arrData[10] = arrData[10].replaceAll("\\b'\\b","''");
            arrData[10] = arrData[10].replaceAll("\\b' ","''");
            arrData[10] = arrData[10].replaceAll(" '"," ''");
          }



          if(!rl.contains(arrData[7])){
            String ruca = String.format("insert into ruca(rndrng_prvdr_ruca,rndrng_prvdr_ruca_desc) values(%s,\'%s\')", arrData[7], arrData[8]);
            rl.add(arrData[7]);
            stmt.executeUpdate(ruca);
          }


          if(!procl.contains(arrData[9])){
            String procedures = String.format("insert into procedures(drg_cd,drg_desc) values(%s,\'%s\')", arrData[9], arrData[10]);
            procl.add(arrData[9]);
            stmt.executeUpdate(procedures);
          }


          if (!pl.contains(arrData[0])){
            String provider = String.format("INSERT INTO Provider(rndrng_ccn,rndrng_prvdr_org_name,rndrng_prvdr_st,rndrng_prvdr_city,rndrng_prvdr_zip5,rndrng_prvdr_ruca) VALUES(%1$s,\'%2$s\',\'%3$s\',\'%4$s\',\'%5$s\',\'%6$s\')", arrData[0], arrData[1], arrData[2], arrData[3],arrData[6],arrData[7]);
            pl.add(arrData[0]);
            stmt.executeUpdate(provider);
          }


          if(!fl.contains(arrData[4])){
            String fedStand = String.format("insert into federalstandard(rndrng_prvdr_state_abrvtn,rndrng_prvdr_state_fips,rndrng_ccn) values(\'%s\',%s,%s)", arrData[4], arrData[5],arrData[0]);
            fl.add(arrData[4]);
            stmt.executeUpdate(fedStand);
          }

          String billing = String.format("insert into billing(rndrng_ccn,drg_cd,tot_dschrgs,avg_submtd_cvrd_chrg,avg_tot_pymt_amt,avg_mdcr_pymnt_amt) values(%s,%s,%s,%s,%s,%s)", arrData[0], arrData[9], arrData[11], arrData[12], arrData[13], arrData[14]);
          stmt.executeUpdate(billing);

          //System.out.println(provider);
          //System.out.println(fedStand);
          //System.out.println(ruca);
          //System.out.println(procedures);
          //System.out.println(billing);

          stmt.close();
          c.commit();
          c.close();
        }
      }
      myReader.close();
    }catch (
            FileNotFoundException e){
      System.out.println("error reading file.");
      e.printStackTrace();
    }
  }
}

