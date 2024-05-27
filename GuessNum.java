import java.util.Scanner;
import java.util.Random;

public class GuessNum{
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        Random r = new Random();
        boolean flag = false;
        int genNum = r.nextInt(100-1) + 1;
        System.out.println("you have a total of 3 chances to guess num");
        for(int i=0;i<3;i++)
        {
            System.out.println("chance "+ (i+1));
            System.out.println("give your number:");
            int num = s.nextInt();
            if(num == genNum)
            {
                System.out.println("you guessed correctly");
                flag = true;
                break;
            }
            else if(num > genNum)
            {
                System.out.println("the number you guessed is greater than generated");
            }
            else if(num<genNum)
            {
                System.out.println("the number you guessed is lesser than generated");
            }
        }
        if(flag == false)
        {
            System.out.println("your chances are over, you lost and generated number is:" + genNum);
        }
        
    }

}