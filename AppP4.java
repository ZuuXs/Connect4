import java.util.Scanner;

public class AppP4 {
    public static void main (String[] args) throws InterruptedException{
        try (Scanner scanner = new Scanner( System.in )) {
            int playerCount=0;


            System.out.print( "Enter the number of rows you want (greater than 3): " );
            int row = scanner.nextInt();

            while(row<4){
                System.out.print( "Greater than 4 or the game becomes unplayable : " );
                row = scanner.nextInt();                    
            }

            System.out.print( "Now, the number of columns you want (greater than 3): " );
            int col = scanner.nextInt();

            while(col<4){
                System.out.print( "Greater than 3 or the game becomes unplayable : " );
                col = scanner.nextInt();                    
            }


            System.out.print( "How many people want to play? " );
            playerCount = scanner.nextInt();



            while(playerCount<1){
                System.out.print( "There must be at least 1 player! \nenter again: " );
                playerCount = scanner.nextInt();
            }
            


            scanner.nextLine();
            if(playerCount>1){

                Player[] listOfPlayers= new Player[playerCount];
                for(int i=0;i<playerCount;i++){
                    System.out.print("Enter the name of player "+(i+1)+": ");
                    listOfPlayers[i] = new Player (scanner.nextLine(),(i+1));
                }

                Tournament tournament=new Tournament(row,col,listOfPlayers);
                tournament.start();
                tournament.end();

            }else{
                System.out.print("Enter the name of the player: ");
                Player player = new Player (scanner.nextLine(),1);
                Game game=new Game();
                game.launch1P(row,col,player);
            }
        }  
    }
}

