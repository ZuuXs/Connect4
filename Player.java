public class Player {
    String name,colour;
    int id,turn,wins;
    public Player(String name,int id) {
        this.name=name;
        this.id=id;
        this.wins=0;

        if(id%2==1){
            this.colour="Red";
            this.turn=1;
        }else{
            this.colour="Yellow";
            this.turn=2;
        }
    }
    
    public String toString() {
        return "Player [colour=" + colour + ", id=" + id + ", name=" + name + ", turn=" + turn + "]";
    }

    public void won(){
        this.wins++;
    }
    
}
