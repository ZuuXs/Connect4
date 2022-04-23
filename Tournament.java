import java.util.Scanner;

public class Tournament {
    Player[] listOfPlayers;
    Player[] Winners;
    int winnerIndex=0;
    int playerCount;
    int col;
    int row;

    public Tournament(int row,int col,Player[] listOfPlayers){
    this.listOfPlayers=listOfPlayers;
    this.playerCount=this.listOfPlayers.length;
    this.row=row;
    this.col=col;
    }
    
    public void start() throws InterruptedException{
        try (Scanner scanner = new Scanner( System.in )) {
            if(playerCount%2==1){
                Winners=new Player[(playerCount+1)/2];
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
                        if(game.winner.id!=0){
                            Winners[winnerIndex]=game.winner;
                            winnerIndex++;
                        }
                        game.lookForNewGame();
                    }

                    Thread.sleep(1000);
                    System.out.println();
                    System.out.println("                       GAME "+(playerCount+1)/2+"                       ");
                    System.out.println();

                    if(opp>0){

                        System.out.println("The players of this match will be: "+listOfPlayers[playerCount-1].name+" VS "+listOfPlayers[opp-1].name);
                        
                        Game game=new Game();
                        game.launch(row,col,listOfPlayers[playerCount-1],listOfPlayers[opp-1]);
                        if(game.winner.id!=0){
                            Winners[winnerIndex]=game.winner;
                            winnerIndex++;
                        }
                        game.lookForNewGame();
                        game.matchEnd();

                    }if(opp==0){
                        System.out.println("The players of this match will be: "+listOfPlayers[playerCount-1].name+" VS CPU.");
                        Game game=new Game();
                        game.launch1P(row,col,listOfPlayers[playerCount-1]);
                        if(game.winner.id!=0){
                            Winners[winnerIndex]=game.winner;
                            winnerIndex++;
                        }
                        game.lookForNewGame();
                        game.matchEnd();
                    }
                }
            }else{
                Winners=new Player[playerCount/2];
                Game game= new Game();
                for(int i=0;i<playerCount;i+=2){
                    Thread.sleep(1000);
                    System.out.println();
                    System.out.println("                       GAME "+(i+2)/2+"                       ");
                    System.out.println();
                    System.out.println("The players of this match will be: "+listOfPlayers[i].name+" VS "+listOfPlayers[i+1].name);
                    game= new Game();
                    game.launch(row,col,listOfPlayers[i],listOfPlayers[i+1]);
                    if(game.winner.id!=0){
                        Winners[winnerIndex]=game.winner;
                        winnerIndex++;
                    }
                    game.lookForNewGame();
                }
                game.matchEnd();
            }
        }
    }
    public void end(){
        if(winnerIndex==0){
            System.out.println("No winner...");
        }else{
            System.out.println("The Winners are: ");
            for(int i=0;i<winnerIndex;i++){
                System.out.println("- "+Winners[i].name);
            }
        }
    }

}
