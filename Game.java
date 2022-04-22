
import java.util.concurrent.ThreadLocalRandom;
import java.util.Scanner;

public class Game {

    public Game() {
    }

    public String launch(int row,int col,Player p1,Player p2){
        int randomNum = ThreadLocalRandom.current().nextInt(1, 3);
        Player firstPlayer;
        if(randomNum==2){
            firstPlayer=p1;
            p1=p2;
            p2=firstPlayer;
        }
        
        P4 game= new P4(row, col, p1, p2);
        Scanner scan= new Scanner(System.in);
        int c;

        while(!full(game)){
            while(game.verify()==0){
                System.out.printf( p1.name+", which column do you want to play? " );
                c = scan.nextInt();
                while(!game.tryPlay(c,p1)){
                    System.out.println();
                    System.out.print("Try again: ");
                }
                System.out.println(game);
                System.out.printf( p2.name+", which column do you want to play? " );
                c = scan.nextInt();
                while(!game.tryPlay(c,p2)){
                    System.out.println();
                    System.out.print("Try again: ");
                }
                System.out.println(game);
            }
            if(game.verify()==1){
                return (p1.name+" has won");
            }else{
                return (p2.name+" has won");
            } 
        }
        return ("Tie!");
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

}
