
import java.util.concurrent.ThreadLocalRandom;
import java.util.Scanner;

public class Game {

    public Game() {
    }

    public void launch(int row,int col,Player p1,Player p2){
        int randomNum = ThreadLocalRandom.current().nextInt(1, 3);
        Player firstPlayer;
        Player secondPlayer;
        if(randomNum==1){
            firstPlayer=p1;
            secondPlayer=p2;
        }else{
            firstPlayer=p2;
            secondPlayer=p1;
        }
        
        P4 game= new P4(row, col, firstPlayer, secondPlayer);
        Scanner scan= new Scanner(System.in);

        while(!full(game)){
            System.out.printf( firstPlayer.name+", which column do you want to play? " );
            int c = scan.nextInt();
            while(!game.tryPlay(c,p1)){
                System.out.println();
                System.out.print("Try again: ");
            };
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

}
