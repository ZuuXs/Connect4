public class P4 {
    int[][] table;
    int row,col;
    Player p1,p2;

    // Constructor
    public P4(int row,int col,Player p1, Player p2){
        this.row=row;
        this.col=col;
        this.table= new int[row][col];
        this.p1=p1;
        this.p2=p2;
    }

    // Display
    public String toString(){
        for (int i = 0;i < table.length; i++) {
            for (int j = 0;j < table[i].length-1;j++) {
               System.out.print("["+table[i][j] +"]"+",");  
            }
            System.out.print("["+table[i][table[i].length-1] +"]"); 
            System.out.println();
        }
        System.out.println();
        return("Player 1 is "+p1.name+" with "+p1.colour+" colour."+'\n'+"Player 2 is "+p2.name+" with "+p2.colour+" colour.");
    }

    // Play the turn
    public Boolean tryPlay(int column,Player player){

        // Check the presence of the column
        if(column<=0 || column>this.col){
            System.out.println("Column doesn't exist !");
            return false;
        }

        // See if the column is full or not
        else if(table[0][column-1]!=0){
            System.out.println("Full column, Try another one :)");
            return false;
        }

        // If the code arrives here then we can play
        else{
            for (int i=row-1;i>=0;i--){
                if (table[i][column-1]==0){
                    table[i][column-1]=player.turn;
                    break;
                }
            }
            return true;
        }
    }
    
    public int horizontal(){
        int accumelator=1;
        int winner=0;
        for(int i=0;i<row;i++){

            if(winner!=0){
                break;
            }

            for (int j=0;j<table[i].length-1;j++){
                // [optimisation] if columns are greater than 3 then it is possible to find a horizontal combination,
                // otherwise it is impossible;
                if(col>3){
                    // if the cell is not empty and contains the same colour as the next cell horizontally then we increment, 
                    // else we reset the accumelator. 
                    if(table[i][j]!=0 && table[i][j]==table[i][j+1]){
                        accumelator++;
                        // if the accumelator find 4 combination of one player then he is the winner. 
                        if (accumelator==4){
                            winner=table[i][j];
                            return winner;
                        }
                        // [optimisation] if the remaining cells are less than the remaining combinations to reach 4
                        // we jump to the next line because there is no chance to find a winner in this one. 
                        if(table[i].length-j<4-accumelator){
                            break;
                        }
                    }else{
                        accumelator=1;
                    }
                }
            }
        }
        return winner;
    }

    public int vertical(){
        int accumelator=1;
        int winner=0;

        for(int i=0;i<col;i++){
            for (int j=row-1;j>0;j--){
                
                // [optimisation] if the remaining rows to check are greater than the rest of the accumelator to reach 4
                // then it is possible to find a vertical combination, otherwise it is impossible;
                if(j>=4-accumelator){
 
                    // [optimisation] if cell is empty then all the rest above are, we skip this column, no chance of a combination...
                    if(table[j][i]==0){
                        break;
                    }

        
                    // if the cell isn t empty and contains the same colour as the next cell vertically then we increment, else we reset the accumelator.
                    if(table[j][i]!=0 && table[j][i]==table[j-1][i]){
                        accumelator++;
                    }else{
                        accumelator=1;
                    }

                    // if the accumelator find 4 combination of one player then he is the winner. 
                    if (accumelator==4){
                        winner=table[j][i];
                        return winner;
                    }
                }else{
                    break;
                }
            }
        }
        return winner;
    }

    public int diag(){
        int winner=0;
        int accumelator=1;

            

        // loop 1 i++ j++
        for(int i=0;i<row-3;i++){
            accumelator=1;
            int j=0;
            int iTemp=i;

            while(col-iTemp>4-accumelator && j<col-1){
                if (table[iTemp][j]!=0 && table[iTemp][j]==table[iTemp+1][j+1]){
                    accumelator++;
                }else{
                    accumelator=1;
                }
                if (accumelator==4){
                    winner=table[iTemp][j];
                    return winner;
                }
                iTemp++;
                j++;
            }
        }
                
        accumelator=1;
            

        // loop 2 j++ i++
        for(int i=1;i<col-3;i++){
            accumelator=1;
            int j=0;
            int iTemp=i;
            System.out.println("loop2");
            while(row-i-j>3-accumelator && j<col-1){
                
                if (table[j][iTemp]!=0 && table[j][iTemp]==table[j+1][iTemp+1]){
                    accumelator++;
                }else{
                    accumelator=1;
                }
                if (accumelator==4){
                    winner=table[j][iTemp];
                    return winner;
                }
                iTemp++;
                j++;
            }
        }
                
        accumelator=1;
    
        // loop 3 i-- j++
        for(int i=row-1;i>2;i--){
            accumelator=1;
            int j=0;
            int iTemp=i;

            while(iTemp>3-accumelator && j<col-1){
                
                if (table[iTemp][j]!=0 && table[iTemp][j]==table[iTemp-1][j+1]){
                    accumelator++;
                }else{
                    accumelator=1;
                }
                if (accumelator==4){
                    winner=table[iTemp][j];
                    return winner;
                }
                iTemp--;
                j++;
            }
        }

        accumelator=1;

        // loop 4 j++ i--
        for(int i=1;i<col-3;i++){
            accumelator=1;
            int j=row-1;
            int iTemp=i;

            while(row-iTemp>3-accumelator && j>0){
                
                if (table[j][iTemp]!=0 && table[j][iTemp]==table[j-1][iTemp+1]){
                    accumelator++;
                }else{
                    accumelator=1;
                }
                if (accumelator==4){
                    winner=table[j][iTemp];
                    return winner;
                }
                iTemp++;
                j--;
            }
        }
        return winner;
    }


    public int verify(){
        
        if (this.horizontal()!=0){
            return this.horizontal();
        } 
        if (this.vertical()!=0){
            return this.vertical();
        }
        
        if (this.diag()!=0){
            return this.diag();
        }
        return 0;
    }
}
