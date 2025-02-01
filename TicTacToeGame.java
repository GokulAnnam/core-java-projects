import java.util.Scanner;

class TicTacToe
{
    static char board[][];

    public TicTacToe() 
    {
        board = new char[3][3];
        initBoard();   
    }

    public void initBoard()
    {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                board[i][j] = ' '; 
            }
        }
    }

    public static void displayBoard()
    {
        System.out.println("-------------"); 
        for(int i=0;i<3;i++)
        {
            System.out.print("| ");    
            for(int j=0;j<3;j++)
            {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    public static void placeMark(int row,int col,char mark)
    {
        if(row >= 0 && row <= 2 && col >= 0 && col <= 2)
        {
            board[row][col] = mark;
        }
        else
        {
            System.out.println("invalid position");
        }
    }

    public static boolean checkColWin()
    {
        for(int j=0;j<3;j++)
        {
            if(board[0][j] != ' ' && board[0][j] == board[1][j] && 
               board[1][j] == board[2][j])
            {
                return true;
            }
        }
        return false;
    }

    public static boolean checkRowWin()
    {
        for(int j=0;j<3;j++)
        {
            if(board[j][0] != ' ' && board[j][0] == board[j][1] && 
               board[j][1] == board[j][2])
            {
                return true;
            }
        }
        return false;
    }

    public static boolean checkDiagonalWin()
    {
        if((board[1][1] != ' ') && 
           ((board[0][0] == board[1][1] && board[2][2] == board[1][1]) ||
           (board[0][2] == board[1][1] && board[2][0] == board[1][1])))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean checkAllWins()
    {
        if(checkColWin() || checkRowWin() || checkDiagonalWin())
        {
            return true;
        }
        else
        {
            return false;
        }
    }   

}

class HumanPlayer
{
    String name;
    char mark;

    Scanner s = new Scanner(System.in);

    public HumanPlayer(String name, char mark)
    {
        this.name = name;
        this.mark = mark;
    }

    public boolean isValidMove(int row, int col)
    {
        if(row >= 0 && row <=2 && col >= 0 && col <=2)
        {
            if(TicTacToe.board[row][col] == ' ')
            {
                return true;
            }
        }
        return false;
    }

    public void makeMove()
    {
        int n;
        int row = 0;
        int col = 0;
        Scanner s = new Scanner(System.in);
        do
        {
            System.out.println("box number:");
            n = s.nextInt();
            switch(n)
            {
                case 1:
                   row = 0;
                   col = 0;
                   break;
                case 2:
                   row = 0;
                   col = 1;
                   break;
                case 3:
                   row = 0;
                   col = 2;
                   break;
                case 4:
                   row = 1;
                   col = 0;
                   break;
                case 5:
                   row = 1;
                   col = 1;
                   break;
                case 6:
                   row = 1;
                   col = 2;
                   break;
                case 7:
                   row = 2;
                   col = 0;
                   break;
                case 8:
                   row = 2;
                   col = 1;
                   break;
                case 9:
                   row = 2;
                   col = 2;
                   break;
                default:
                   System.out.println("enter valid postion");

            }
            if(!isValidMove(row,col))
            {
                System.out.println("invalid position");
            }
        }while(!isValidMove(row,col));

        TicTacToe.placeMark(row,col,mark);


    }

}



public class TicTacToeGame{
    public static void main(String[] args){
        TicTacToe obj = new TicTacToe();
        
        System.out.println("these represent the box numbers:");
        System.out.println("-------------");
        System.out.println("| 1 | 2 | 3 |");
        System.out.println("-------------");
        System.out.println("| 4 | 5 | 6 |");
        System.out.println("-------------");
        System.out.println("| 7 | 8 | 9 |");
        System.out.println("-------------");

        String s1,s2;
        char c1,c2;
        Scanner s = new Scanner(System.in);
        System.out.println("enter player 1 name:");
        s1 = s.next();
        System.out.println("enter player 2 name:");
        s2 = s.next();
        System.out.println("enter player 1 mark:");
        c1 = s.next().charAt(0);
        System.out.println("enter player 2 mark:");
        c2 = s.next().charAt(0);

        HumanPlayer p1 = new HumanPlayer(s1,c1);
        HumanPlayer p2 = new HumanPlayer(s2,c2);

        HumanPlayer cp; //current player
        cp = p1;

        while(true)
        {
            System.out.println(cp.name + " turn ");
            cp.makeMove();
            TicTacToe.displayBoard();
            
            if(TicTacToe.checkAllWins())
            {
                System.out.println( cp.name + " won");
                break;
            }
            else
            {
                if(cp == p1)
                {
                    cp = p2;
                }
                else
                {
                    cp = p1;
                }
            }
        }
        

    }
}