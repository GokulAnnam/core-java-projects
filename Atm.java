import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

class User{
    private String name;
    private int id;
    private double balance;
    private int pin;
    private ArrayList<String> transHis = new ArrayList<>();
    public static HashMap<String,User> h = new HashMap<>();

    public User(String name , int id , double balance , int pin)
    {
        this.name = name;
        this.id = id;
        this.balance = balance;
        this.pin = pin;
        // this.transHis = new ArrayList<>();
        h.put(name,this);

    }

    public int getUserId()
    {
        return id;
    }

    public int getPin()
    {
        return pin;
    }

    public String getUserName()
    {
        return name;
    }

    public double getBalance()
    {
        return balance;
    }

    public void setBalance(double newBalance)
    {
        this.balance = newBalance;
    }

    public void addTransaction (String t)
    {
        transHis.add(t);
    }

    public ArrayList<String> getTransHis()
    {
        return transHis;
    }

    public static boolean authenticate(String userName, int pin)
    {
        User u = h.get(userName);
        if(u!= null && u.getPin()== pin)
            return true;
        else
            return false;
    }

    public static User getUser(String name)
    {
        return h.get(name);
    }
}

class Bank{
    Scanner s = new Scanner(System.in);
    private String name;
    private int pin;
    private User currentUser;
    public void start()
    {
        boolean flag = true;
        while(flag)
        {
            System.out.println("enter user name:(enter END to Quit)");
            name = s.next();
            if(name.equals("END"))
            {
                System.out.println("ok aithe bye");
                flag = false;
                break;
            }
            System.out.println("enter pin:");
            pin = s.nextInt();
            //s.nextLine();           
            if(User.authenticate(name,pin))
            {
                currentUser = User.getUser(name);
                menu();
            }
            else
            {
                System.out.println("invalid username/pin");
            }
        }
      
    }

    public void menu()
    {
        //boolean flag = true;
        while(true)
        {
            System.out.println("enter 1 -> transaction history ");
            System.out.println("enter 2 -> withdraw ");
            System.out.println("enter 3 -> deposit ");
            System.out.println("enter 4 -> transfer");
            System.out.println("enter 5 -> quit ");
   
           int choice = s.nextInt();
           //s.nextLine();
   
           switch(choice){
               case 1:
                  showTransactionHistory();
                  break;
               case 2:
                  withdraw();
                  break;
               case 3:
                  deposit();
                  break;
               case 4:
                   transfer();
                   break;
               case 5:
                    System.out.println("thanks ra babu, po inka!!");
                    return;
               default:
                    System.out.println("invalid choice");
                    break;
   
           }        
        }
        
    }

    public void showTransactionHistory() {
        System.out.println("transaction history:");
        for(String t: currentUser.getTransHis())
        {
            System.out.println(t);
        }
    }

    public void withdraw() {
        System.out.println("enter amount to withdraw");
        double amt = s.nextDouble();
        if(amt<=currentUser.getBalance())
        {
            currentUser.setBalance(currentUser.getBalance() - amt);
            System.out.println("withdraw of Rs."+ amt + "is successful, current balance= "+currentUser.getBalance());
            currentUser.addTransaction("withdraw: Rs."+amt);
        }
        else
        {
            System.out.println("balance not sufficient");
        }
    }

    public void deposit() {
        System.out.println("enter amount to deposit:");
        double amt = s.nextDouble();
        currentUser.setBalance(currentUser.getBalance() + amt);
        currentUser.addTransaction("deposit amount: Rs."+amt);
        System.out.println("deposit successful, new balance: Rs."+currentUser.getBalance());
    }

    public void transfer() {
        System.out.println("enter receiver's username:");
        String r = s.next();
        System.out.println("enter amount to transfer:");
        double amt = s.nextDouble();
        if(amt<currentUser.getBalance())
        {
            User receiver = User.getUser(r);
            if(receiver != null)
            {
                currentUser.setBalance(currentUser.getBalance() - amt);
                receiver.setBalance(receiver.getBalance() + amt);
                currentUser.addTransaction("amt Rs." + amt + "transferred to "+ receiver.getUserName());
                receiver.addTransaction("amt Rs." + amt + "received from "+ currentUser.getUserName());
                System.out.println("transfer successful. new balance is Rs."+ currentUser.getBalance());

            }
            else
            {
                System.out.println("invalid receiver username");
            }

        }
    }
    
}

public class Atm{
    public static void main(String[] args){
        User u1 = new User("goks",12,10000,1234);
        User u2 = new User("vat",17,5000,5678);
        User u3 = new User("loh",19,20000,1912);
        Bank b = new Bank();
        b.start();
    }
}