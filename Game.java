
import java.util.concurrent.ThreadLocalRandom;
import java.util.Scanner;

public class Game {
    
    Player winner= new Player("None",0);

    public Game() {
    }

    public void launch(int row,int col,Player p1,Player p2) throws InterruptedException{

        // PLAYER VS PLAYER


        int randomNum = ThreadLocalRandom.current().nextInt(1, 3);
        Player firstPlayer;

        // Choosing the player who is going to play first randomly 
        if(randomNum==2){
            firstPlayer=p1;
            p1=p2;
            p2=firstPlayer;
        }

        p1.giveturn(1);
        p2.giveturn(2);
        
        System.out.println();
        Thread.sleep(1000);
        System.out.println(p1.name+" is chosen randomly to be the first player.");
        System.out.println();
        Thread.sleep(1000);

        P4 game= new P4(row, col, p1, p2);
        Scanner scan = new Scanner(System.in);
            int c;

            System.out.println(game);
            System.out.println();

                // if the game is not full and there is no winner, the game keeps playing
                while(game.verify()==0 && !full(game)){
                    System.out.println( p1.name+", which column do you want to play? " );
                    c = scan.nextInt();

                    // if the player entered an invalid column we ask again
                    while(!game.tryPlay(c,p1)){
                        System.out.println();
                        System.out.print("Try again: ");
                        c = scan.nextInt();
                    }
                    System.out.println();
                    System.out.println();
                    System.out.println(game);
                    Thread.sleep(1000);

                    // Now is the second player's turn (if the game still on)
                    if (game.verify()==0 && !full(game)){
                        System.out.println();
                        System.out.println( p2.name+", which column do you want to play? " );
                        c = scan.nextInt();
                        while(!game.tryPlay(c,p2)){
                            System.out.println();
                            System.out.print("Try again: ");
                            c = scan.nextInt();
                        }
                        System.out.println();
                        System.out.println();
                        System.out.println(game);
                        System.out.println();
                        Thread.sleep(1000);
                    }
                }
        
            
        

            // if we arrive here then the game is either full and/or got a winner. We check, print and end this game.

            if(game.verify()==1){
                p1.wins++;
                System.out.println("!!!!!!!!!!!!!!      "+p1.name+" has won.      !!!!!!!!!!!!!!");
                winner=p1;
                return;
            }
            if(game.verify()==2){
                p2.wins++;
                System.out.println("!!!!!!!!!!!!!!      "+p2.name+" has won.      !!!!!!!!!!!!!!");
                winner=p2;
                return;
            } 
            if (full(game)){
                System.out.println("!!!!!!!!!!!!!!      Tie!      !!!!!!!!!!!!!!");
                System.out.println();
                return;
            }
    
    }

    public void launch1P(int row, int col,Player p) throws InterruptedException{
        /*
        Single player, which means versus the CPU
        Note: The CPU is not an AI, its juste a random plays
        */
        int randomNum;
        Player CPU=new Player("CPU", 0);

        p.giveturn(1);
        CPU.giveturn(2);
        
        System.out.println();
        Thread.sleep(1000);
        System.out.println("You start.");
        System.out.println();
        Thread.sleep(1000);

        P4 game= new P4(row, col, p, CPU);
        try (Scanner scan = new Scanner(System.in)) {
            int c;

            System.out.println(game);
            System.out.println();


                // if the game is not full and there is no winner, the game keeps playing
                while(game.verify()==0 && !full(game)){
                    System.out.printf( p.name+", which column do you want to play? " );
                    c = scan.nextInt();

                    // if the player entered an invalid column we ask again
                    while(!game.tryPlay(c,p)){
                        System.out.println();
                        System.out.print("Try again: ");
                        c = scan.nextInt();
                    }
                    System.out.println();
                    System.out.println();
                    System.out.println(game);
                    Thread.sleep(1000);

                    // CPU turn, its going to be randomly random
                    if (game.verify()==0 && !full(game)){
                        System.out.println();
                        System.out.println( CPU.name+", which column do you want to play? " );
                        System.out.println();
                        Thread.sleep(1000);
                        System.out.println("CPU is thinking...");
                        Thread.sleep(3000);
                        randomNum = ThreadLocalRandom.current().nextInt(1, col+1);
                        System.out.println("CPU has played column "+randomNum);
                        game.tryPlay(randomNum, CPU);
                        System.out.println();
                        System.out.println();
                        System.out.println(game);
                        System.out.println();
                        Thread.sleep(1000);
                    }
                }
        }

        // We verify if there is a winner, else its a TIE.

        if(game.verify()==1){
                p.wins++;
                System.out.println("!!!!!!!!!!!!!!      "+p.name+" has won.      !!!!!!!!!!!!!!");
                winner=p;
                return;
            }
            if(game.verify()==2){
                CPU.wins++;
                System.out.println("!!!!!!!!!!!!!!      "+CPU.name+" has won.      !!!!!!!!!!!!!!");
                return;
            } 
            if (full(game)){
                System.out.println("!!!!!!!!!!!!!!      Tie!      !!!!!!!!!!!!!!");
                System.out.println();
                return;
            }

    }


    public Boolean full(P4 game){
        /*
        Check if the first row of the table is full, in this case all the cells are full
        */
        for(int j=0;j<game.table[0].length;j++){
            if (game.table[0][j]==0){
                return false;
            }
        }
        return true;
    }

    public void lookForNewGame() throws InterruptedException{
        /*
        Just some prints to look for a game
        */

        System.out.println();
        System.out.println();
        System.out.print(".");
        Thread.sleep(500);
        System.out.print(".");
        Thread.sleep(500);
        System.out.print(".");
        Thread.sleep(500);
        System.out.print("LOOKING FOR THE NEXT GAME!");
        Thread.sleep(1000);

    }
    public void matchEnd() throws InterruptedException{
        /*
        Just some prints to announce the end of all matchs
        */
        System.out.println();
        System.out.println();
        System.out.println("No more games found.");
        System.out.println();
        System.out.println("THANK YOU FOR YOUR PARTICIPATION!");
        System.out.println();    
    }

}
