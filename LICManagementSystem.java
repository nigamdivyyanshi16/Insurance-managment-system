import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.Scanner;

class InsuranceManagementFileSys{
      Scanner sc= new Scanner(System.in);
      File CheckFileExistence() throws IOException{
            //Check whether the file exist at the provided path or not. If not create that file.
            String cvsFile = "BuyerInfo.csv";
            File f = new File(cvsFile);
            String header= "Name , Age , Job , Type , premium amount, Password\n";
            if(!f.isFile()){
                  FileWriter fw = new FileWriter(cvsFile);
                  fw.write(header);
                  fw.close();
                  
            }
            return f;
            
      }
      
      void PolicyHolderInfo() throws IOException{
            File f1=CheckFileExistence(); //Checking whether file is existing or not.
            //This file can be a csv file.
            // Take name,age,profession , Insurance type,amoout of premium
            Scanner sc= new Scanner(System.in);
            System.out.println("user Info:");
            System.out.print("Name:\t");
            String Name= sc.nextLine();
            System.out.print("\nAge");
            int Age= sc.nextInt();
            sc.nextLine(); //used for consuming the remaining newline character.
            System.out.print("\nPremium Type: ");
            String Ptype= sc.nextLine();
            System.out.print("\nJob");
            String job= sc.nextLine();
            System.out.print("\nPremium Amount");
            int Pamount= sc.nextInt();
            sc.nextLine(); //used for consuming the remaining new line character in the input buffer.
            System.out.println("Enter password");
            String password= sc.nextLine();

            FileWriter fw= new FileWriter(f1,true);
            String strAge= Integer.toString(Age);
            String strPamount= Integer.toString(Pamount);
            fw.write(Name+ ", \t"+strAge+", \t"+job+", \t"+Ptype+", \t"+strPamount+", \t"+password+"\n");
            fw.flush();
            fw.close();
            sc.close();

      }
      void DisplaybuyerInfo(String Name) throws IOException{
            File f= CheckFileExistence();
            FileReader fr= new FileReader(f);
            // BufferedReader br= new BufferedReader(fr);
            String line;
            // Here, we are using try- with resource statement(refer to oracle doc). 
            try(BufferedReader br= new BufferedReader(fr)){
                  String head= br.readLine(); //reading the header.

                  // now reading each row:
                  while((line= br.readLine())!=null){
                        String[] data= line.split(",");
                        if(data[0].equalsIgnoreCase(Name)){
                              for(String value: data){
                                    System.out.print(value);
                              }

                        }
                        System.out.println();
                  }
            }
            catch(Exception e){
                  System.out.println(e);
            }
      }
      Boolean AuthenticateUser(String password) throws IOException{
            boolean IsvalidUser=false;
            String fileName= "BuyerInfo.csv";

            // Return true if it get authenticated otherwise false.
            // reading csv file using try with resource statement:
            try(BufferedReader br= new BufferedReader(new FileReader(fileName))){
                  String header= br.readLine();
                  String line;
                  while((line=br.readLine())!=null){
                        String[] data= line.split(",");
                        System.out.println("Passwrod is :"+data[5]);
                        if(data[5].trim().equals(password.trim())){
                              System.out.println("Valid user");
                              IsvalidUser=true;
                              break;
                        }
                        
                  }
            }
            catch(Exception e){
                  System.out.println("e");
            }
            return IsvalidUser;
      }

}

class InsuranceFunctionality extends InsuranceManagementFileSys{
      //Extending above class bcoz we need to use PolicyHolderInfo.
      Scanner sc= new Scanner(System.in);
      InsuranceManagementFileSys obj= new InsuranceManagementFileSys();
      // InsuranceFunctionality obj= new InsuranceFunctionality();
      String fileName = "BuyerInfo.csv";
      

      
      
      void CancellationPremium() throws IOException{
            // before Cancellation, authenticate the user.
            // AuthenticateUser();  it returns a boolean value.

            // Remove that holder from the file.
            System.out.println("Enter your password");
            String password = sc.nextLine();
            if(obj.AuthenticateUser(password)){
                  try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
                        String head= br.readLine();
                        String line;
                        while((line=br.readLine())!=null){
                              String[] data= line.split(",");
                              if(data[5].equals(password)){
                                    StringBuilder sb= new StringBuilder(line);
                                    StringBuilder builder= sb.delete(0,sb.length()-1);
                                    System.out.println("user deleted Successfully!");
                              }
                        }
                  }
            }
            else{
                  System.out.println("not a valid user!!");
            }



      }
      void DepositPremium(String Name, String Premium) throws IOException{
            // Again authenticate
            //update info in the file.
            System.out.println("Enter your password");
            String password= sc.nextLine();
            System.out.println("Your pswrd is:"+password);
            System.out.println("Your pswrd is:"+obj.AuthenticateUser(password));
            if(obj.AuthenticateUser(password)){
                  // logic for deposit premium
                  System.out.println("inside deposit fun");
                  try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                        String head= br.readLine();
                        String line;
                        while((line=br.readLine())!=null){
                              System.out.println("inside deposit try fun");
                              String[] data= line.split(",");
                              System.out.println("name comprea is "+data[0].trim().equalsIgnoreCase(Name.trim()));
                              if(data[0].trim().equalsIgnoreCase(Name.trim())){
                                    double intVal= Integer.parseInt(data[4].trim()); //declaring it as duoble due to type casting error.
                                    intVal= intVal+Integer.parseInt(Premium.trim())+Integer.parseInt(Premium.trim())*0.13f;
                                    System.out.println("After deposit premium:"+intVal);
                                    String StrVal= Integer.toString((int)intVal);
                                    FileWriter fw= new FileWriter("BuyerInfo.csv",true);
                                    fw.write("\t"+line.replace(data[4], StrVal));
                                    fw.close();
                                    break;
                              }
                        }
                  }
                  catch (NumberFormatException e) {
                        System.out.println(e);
                  }

            }
            else{
                  System.out.println("not a valid user");
            }
      }
}


public class LICManagementSystem {
      public static void main(String[] args) throws IOException{
            
            Scanner sc= new Scanner(System.in);
            // System.out.println("Reading data from csv file");
            // String Fname="BuyerInfo.csv";
            
            boolean flag= true;
            String name;
            // InsuranceManagementFileSys obj= new InsuranceManagementFileSys();
            InsuranceFunctionality obj= new InsuranceFunctionality();
            
            
            while(flag){
                  System.out.println("Enter 1. Enter user Details\n2. Display user info\n3. For Depositing premium\n4. For Cancellation\n5. Exit");
                  int choice= sc.nextInt();
                  switch (choice) {
                        case 1:
                              obj.PolicyHolderInfo();
                              break;
                        case 2:
                              sc.nextLine();
                              System.out.println("Enter the name of buyer");
                              name= sc.nextLine();
                              obj.DisplaybuyerInfo(name);
                              break;
                        case 3:
                              sc.nextLine();//consuming if any new line character in input buffer. It Makes the name string as null.
                              System.out.println("Enter name and Premium amount");
                              name= sc.nextLine();
                              System.out.println(name);
                              String premium= sc.next();

                              obj.DepositPremium(name,premium);
                              break;
                        case 4:
                              obj.CancellationPremium();
                              break;
                        case 5:
                              flag= false;
                              break;
                        default:
                              System.out.println("Wrong choice");
                              break;
                  }

            }
            sc.close();

      }
}
