package console_interaction;
import java.util.*;

import database_connectivity.RegisterJDBC;
import utils.*;

public class RegisterConsole{
    static void register(){
        Scanner scn = new Scanner(System.in);
        
        System.out.println("");
        System.out.println("Registration:- \n");
        System.out.println("Which of the following user are you?");
        System.out.println("1. Customer");
        System.out.println("2. Agent");

        System.out.print("\nEnter your choice: ");
        int option = scn.nextInt();
        scn.nextLine();
        
        if(option == 1){
            System.out.println();
            System.out.print("Enter the username: ");
            String username = scn.nextLine();
            System.out.print("Enter the password: ");
            String password = scn.nextLine();
            System.out.print("Enter your email address: ");
            String emailAddress = scn.nextLine();
            System.out.print("Enter your first name: ");
            String firstName = scn.nextLine();
            System.out.print("Enter your last name: ");
            String lastName = scn.nextLine();
            System.out.print("Enter your address details: ");
            String address = scn.nextLine();
            System.out.print("Enter your mobile number: ");
            String mobileNo = scn.nextLine();

            Customer customer = new Customer(0, username, password, emailAddress, firstName, lastName, address, mobileNo);
            RegisterJDBC.addCustomer(customer);
        }        
        else{
            System.out.println();
            System.out.print("Enter the username: ");
            String username = scn.nextLine();
            System.out.print("Enter the password: ");
            String password = scn.nextLine();
            System.out.print("Enter your email address: ");
            String emailAddress = scn.nextLine();
            System.out.print("Enter your role(1. Head,  2. Manager,  3. Supervisor): ");
            int roleNumber = scn.nextInt();
    
            Agent agent = new Agent(0, username, password, emailAddress, roleNumber == 1 ? AgentRole.MANAGER : AgentRole.SUPERVISOR);
            RegisterJDBC.addAgent(agent);
        }
    }
}
