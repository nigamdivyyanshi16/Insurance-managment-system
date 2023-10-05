import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.Scanner;

class Insurance
{
Scanner sc=new Scanner(System.in);
public File checkInsuranceFile()throws IOException
{
String file2="InsuranceInfo.csv";
File f2=new File(file2);
String title="Entry Age/Policy Terms, 25years, 30 years, 35 years,40 years \n";
if(f2.exists())
{
return(f2);
}
else
{
FileWriter fw=new FileWriter(f2);
fw.write(title);
fw.write("30 yrs, Rs.11300, Rs.11900, Rs.12200, Rs.12700\n");
fw.write("35 yrs, Rs.14700, Rs.15800, Rs.16700, Rs.17400\n");
fw.write("40 yrs, Rs.21500, Rs.22900, Rs.24400, NA\n");
fw.write("45 yrs, Rs.32000, Rs.34500, NA, NA\n");
fw.flush();
fw.close();
fw.close();
return(f2);
}
}


public void showInsuranceDetails()throws IOException
{
File file=checkInsuranceFile();
FileReader fr= new FileReader(file);
         
            String line;
            
            BufferedReader br= new BufferedReader(fr);
                  String head= br.readLine(); //reading the header.

                  // now reading each row:
                  while((line= br.readLine())!=null){
                             String[] data= line.split(",");
                      
                              for(String value: data){
                                    System.out.print(value);
                              

                        }
                        System.out.println();
                  }
            
            
}
}

class UsersEnd extends Insurance
{
static Scanner sc=new Scanner(System.in);
String age,Name;
File checkFile()throws IOException
{
String file1="Users.csv";
File f1=new File(file1);
String title="Name , Age , Ph. number , Occupation , Premium Paid , Premium Unpaid \n";
if(f1.exists())
{
return(f1);
}
else
{
FileWriter fw=new FileWriter(f1);
fw.write(title);
fw.close();
return(f1);
}
}

public void enterInfo()throws IOException
{
File F=checkFile();
System.out.println("Enter your Information");
System.out.println("Name");
Name=sc.next();
System.out.println("Age");
age=sc.next();
System.out.println("Phone Number");
String ph=sc.next();
System.out.println("Occupation");
String occ=sc.next();
System.out.println("Premium");
String premium=sc.next();
FileWriter fw=new FileWriter(F,true);
fw.write(Name+","+age+","+ph+","+occ+","+premium);
fw.write("\n");
fw.flush();
fw.close();
}


public void DisplaybuyerInfo(String nm)throws IOException
{
            File f= checkFile();
            FileReader fr= new FileReader(f);
            String line; 
            try(BufferedReader br= new BufferedReader(fr))
                {
                  String head= br.readLine(); //reading the header.

                  // now reading each row:
                  while((line= br.readLine())!=null)
                        {
                        String[] data= line.split(",");
                        if(data[0].equalsIgnoreCase(nm))
                             {
                              for(String value: data){
                                    System.out.print(value+" ");
                              }
                           }
                        }
                        System.out.println();
                  }
            catch(Exception e)
             {
              System.out.println(e);
             }
 }
            
      
public static void main(String args[])throws IOException
{
Scanner ss=new Scanner(System.in);
UsersEnd ob=new UsersEnd();
while(true)
{
System.out.println("1.Display Premium Plans for 1Cr Insurance \n 2.Buy Plan \n 3.Veiw User Details\n 4. Exit");
int ch=ss.nextInt();
switch(ch)
{
case 1:ob.showInsuranceDetails();
break;
case 2:ob.enterInfo();
break;
case 3:System.out.println("enter user's name");
String nm=ss.next();
 ob.DisplaybuyerInfo(nm);
break;
case 4:System.out.println("Exiting....");
System.exit(0);
break;
default:System.out.println("Invalid Input");
}}
}
}