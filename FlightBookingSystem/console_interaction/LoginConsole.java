package console_interaction;
import java.util.*;
import database_connectivity.LoginJDBC;
import model.*;

public class LoginConsole {
    static void login(){
        Scanner scn = new Scanner(System.in);

        System.out.println();
        System.out.println("Login:- \n");
        
        System.out.print("Enter the username: ");
        String username = scn.nextLine();
        System.out.print("Enter the password: ");
        String password = scn.nextLine();
        System.out.print("Enter the type of user(1. Customer,  2. Agent): ");
        int userType = scn.nextInt();

        if(userType == 1){
            Customer customer = (Customer)LoginJDBC.loginUser(username, password, userType);  
            if(customer != null) 
                CustomerHomeConsole.runCustomerConsole(customer);
        }
        else{
            Agent agent = (Agent)LoginJDBC.loginUser(username, password, userType);  
            if(agent != null) 
                AgentHomeConsole.runAgentConsole(agent);
        }
    }
}
