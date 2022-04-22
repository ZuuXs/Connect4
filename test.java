
public class test {
    
    public static void main (String[] args){
        Player p1= new Player("zxs", 1);
        Player p2= new Player("salim",2);
        P4 game=new P4(4, 4, p1, p2);
        game.tryPlay(1, p1);
        game.tryPlay(1, p1);
        game.tryPlay(1, p1);
        game.tryPlay(1, p1);
        System.out.println(game);
        System.out.println(game.vertical());
    }
}

