import java.lang.Math;

public class Project2 {

    public static void main (String[] args){
        int Player1=0;
        int whenprint=1;
        int[] divisorset=new int[10];
        int[] cmove=new int[]{1,101,201,301,401,501,601,701,801,901};
        for(int k=0;k<10;k++){
            divisorset[k]=k+1;
        }
        int[] countMove=new int[4];
        int[] wintime=new int[4];

        ArrayList<Integer> board=new ArrayList<>();
        int[] board1={5,10,8,10,7,5,9,10,6,7,10,6,5,8,9,5,10,5,9,6,8,7,10,6,8};
        for(int m=0;m< board1.length;m++){
            board.add(m,board1[m]);
        }
        DoublyLinkedList<Integer> game=new DoublyLinkedList<>();
        game.addMultiple(game,board);
        int countmove=0;
        for(int cc=0;cc<1000;cc++){
            countmove+=game.Playgame(game,game.getFirst(game),Player1,(int)(Math.random()*(6)+1),0);
        }
        System.out.println("One Player");
        System.out.println(" ");
        System.out.println("Player A Average move is "+(countmove/1000));
        System.out.println("Player A winning rate is 100.0%");
        System.out.println(" ");

        System.out.println("Four Players");
        System.out.println(" ");
        for(int mm=0;mm<1000;mm++){
            int[] result=game.Playgame4(game,game.getFirst(game),game.getFirst(game),game.getFirst(game),game.getFirst(game),0,0,0,0,(int)(Math.random()*(6)+1),(int)(Math.random()*(6)+1),(int)(Math.random()*(6)+1),(int)(Math.random()*(6)+1),0,0,0,0);
            countMove[0]+=result[1];
            countMove[1]+=result[2];
            countMove[2]+=result[3];
            countMove[3]+=result[4];
            if(result[0]==1){wintime[0]++;}
            if(result[0]==2){wintime[1]++;}
            if(result[0]==3){wintime[2]++;}
            if(result[0]==4){wintime[3]++;}
            if(mm==0){
                System.out.println("First result is ");
                System.out.println("PlayerA average move is "+countMove[0]);
                System.out.println("PlayerA winning rate is "+(float)(wintime[0]*100)+"%");
                System.out.println("PlayerB average move is "+countMove[1]);
                System.out.println("PlayerB winning rate is "+(float)(wintime[1]*100)+"%");
                System.out.println("PlayerC average move is "+countMove[2]);
                System.out.println("PlayerC winning rate is "+(float)(wintime[2]*100)+"%");
                System.out.println("PlayerD average move is "+countMove[3]);
                System.out.println("PlayerD winning rate is "+(float)(wintime[3]*100)+"%");
                System.out.println(" ");
            }
            if( mm==100 || mm==200 ||mm==300 || mm==400 || mm==500 || mm==600 ||mm==700 || mm==800 || mm==900){
                System.out.println(" ");
                System.out.println("The result for "+(mm+1)+" game is ");

                System.out.println("PlayerA average move is "+countMove[0]/cmove[whenprint]);
                System.out.println("PlayerA winning rate is "+(float)(wintime[0]/divisorset[whenprint])+"%");
                System.out.println("PlayerB average move is "+countMove[1]/cmove[whenprint]);
                System.out.println("PlayerB winning rate is "+(float)(wintime[1]/divisorset[whenprint])+"%");
                System.out.println("PlayerC average move is "+countMove[2]/cmove[whenprint]);
                System.out.println("PlayerC winning rate is "+(float)(wintime[2]/divisorset[whenprint])+"%");
                System.out.println("PlayerD average move is "+countMove[3]/cmove[whenprint]);
                System.out.println("PlayerD winning rate is "+(float)(wintime[3]/divisorset[whenprint])+"%");
                System.out.println(" ");
                whenprint++;
            }

        }

        System.out.println("Final Result is ");
        System.out.println(" ");
        System.out.println("PlayerA average move is "+countMove[0]/1000);
        System.out.println("PlayerA winning rate is "+(float)(wintime[0]/10)+"%");
        System.out.println("PlayerB average move is "+countMove[1]/1000);
        System.out.println("PlayerB winning rate is "+(float)(wintime[1]/10)+"%");
        System.out.println("PlayerC average move is "+countMove[2]/1000);
        System.out.println("PlayerC winning rate is "+(float)(wintime[2]/10)+"%");
        System.out.println("PlayerD average move is "+countMove[3]/1000);
        System.out.println("PlayerD winning rate is "+(float)(wintime[3]/10)+"%");
    }
}
