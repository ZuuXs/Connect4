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

                if(playerCount%2==1){
                    System.out.println("The number of players is odd, so "+listOfPlayers[playerCount-1].name+" should choose a player to play with by entering his id. here is the list of players:");
                    System.out.println("ID       Player");
                    for (int i=0;i<playerCount-1;i++){
                        System.out.println(listOfPlayers[i].id+"        "+listOfPlayers[i].name);
                    }
                    System.out.print("You can also play with the pc by typing 0: ");
                    int opp=scanner.nextInt();

                    while(opp>playerCount || opp<0){
                        System.out.print("Invalid ID ! enter again: ");
                        opp=scanner.nextInt();
                    }

                    if(opp>=0 && opp<=playerCount){
                        
                        for(int i=0;i<playerCount-1;i+=2){
                            Thread.sleep(1000);
                            System.out.println();
                            System.out.println("                       GAME "+(i+2)/2+"                       ");
                            System.out.println();
                            System.out.println("The players of this match will be: "+listOfPlayers[i].name+" VS "+listOfPlayers[i+1].name);
                            Game game= new Game();
                            game.launch(row,col,listOfPlayers[i],listOfPlayers[i+1]);
                            game.lookForNewGame();
                        }

                        Thread.sleep(1000);
                        System.out.println();
                        System.out.println("                       GAME "+(playerCount+1)/2+"                       ");
                        System.out.println();

                        if(opp>0){

                            System.out.println("The players of this match will be: "+listOfPlayers[playerCount-1].name+" VS "+listOfPlayers[opp-1].name);
                            
                            Game lastgame=new Game();
                            lastgame.launch(row,col,listOfPlayers[playerCount-1],listOfPlayers[opp-1]);
                            lastgame.lookForNewGame();
                            lastgame.matchEnd();

                        }if(opp==0){
                            System.out.println("The players of this match will be: "+listOfPlayers[playerCount-1].name+" VS CPU.");
                            Game game=new Game();
                            game.launch1P(row,col,listOfPlayers[playerCount-1]);
                            game.lookForNewGame();
                            game.matchEnd();
                        }
                    }
                }else{
                    Game game= new Game();
                    for(int i=0;i<playerCount;i+=2){
                        Thread.sleep(1000);
                        System.out.println();
                        System.out.println("                       GAME "+(i+2)/2+"                       ");
                        System.out.println();
                        System.out.println("The players of this match will be: "+listOfPlayers[i].name+" VS "+listOfPlayers[i+1].name);
                        game= new Game();
                        game.launch(row,col,listOfPlayers[i],listOfPlayers[i+1]);
                        game.lookForNewGame();
                    }
                    game.matchEnd();
                }
            }else{
                System.out.print("Enter the name of the player: ");
                Player player = new Player (scanner.nextLine(),1);
                Game game=new Game();
                game.launch1P(row,col,player);
            }
        }  
    }
}

