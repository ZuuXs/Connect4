
import java.util.concurrent.ThreadLocalRandom;
import java.util.Scanner;

public class Game {

    public Game() {
    }

    public void launch(int row,int col,Player p1,Player p2) throws InterruptedException{
        int randomNum = ThreadLocalRandom.current().nextInt(1, 3);
        Player firstPlayer;

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
        Scanner scan= new Scanner(System.in);
        int c;

        System.out.println(game);


            while(game.verify()==0 && !full(game)){
                System.out.printf( p1.name+", which column do you want to play? " );
                c = scan.nextInt();
                while(!game.tryPlay(c,p1)){
                    System.out.println();
                    System.out.print("Try again: ");
                    c = scan.nextInt();
                }
                System.out.println();
                System.out.println();
                System.out.println(game);
                Thread.sleep(1000);

                if (game.verify()==0 && !full(game)){
                    System.out.println();
                    System.out.printf( p2.name+", which column do you want to play? " );
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
            if(game.verify()==1){
                p1.wins++;
                System.out.println("!!!!!!!!!!!!!!      "+p1.name+" has won.      !!!!!!!!!!!!!!");
                return;
            }
            if(game.verify()==2){
                p2.wins++;
                System.out.println("!!!!!!!!!!!!!!      "+p2.name+" has won.      !!!!!!!!!!!!!!");
                return;
            } 
            if (full(game)){
                System.out.println("!!!!!!!!!!!!!!      Tie!      !!!!!!!!!!!!!!");
                System.out.println();
                return;
            }
    }

    public void launch1P(int row, int col,Player p){

    }

    public Boolean full(P4 game){
        for(int j=0;j<game.table[0].length;j++){
            if (game.table[0][j]==0){
                return false;
            }
        }
        return true;
    }

    public void lookForNewGame() throws InterruptedException{
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
        System.out.println();
        System.out.println();
        System.out.println("No more games found.");
        System.out.println();
        System.out.println("THANK YOU FOR YOUR PARTICIPATION!");
        System.out.println();    
    }

}
