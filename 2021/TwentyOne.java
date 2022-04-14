public class TwentyOne{
  static int[] loc;
  static int[] score;
  static class Dice{
    int state = 0;
    int rollcount = 0;
    int roll(){
      state++;
      if(state == 101){
        state = 1;
      }
      rollcount++;
      return state;
    }
  }
  public static int simulate(int loca, int locb){
    loc = new int[2];
    loc[0] = loca;
    loc[1] = locb;
    score = new int[2];
    int cursim = 0;
    Dice d = new Dice();
    while(true){
      int rollval = 0;
      for(int i = 0; i < 3; i++){
        rollval += d.roll();
      }
      int newloc = (loc[cursim] + rollval) % 10;
      if(newloc == 0){
        newloc = 10;
      }
      loc[cursim] = newloc;
      score[cursim] += newloc;
      if(score[cursim] >= 1000){
        break;
      }
      cursim++;
      cursim %= 2;
    }
    // winning player: cursim
    // losing player: cursim++, cursim %= 2;
    cursim++;
    cursim %= 2;
    return(d.rollcount * score[cursim]);
  }
  public static void main(String[] args){
    int playerOneLoc = Integer.parseInt(args[0]);
    int playerTwoLoc = Integer.parseInt(args[1]);
    System.out.println(simulate(playerOneLoc, playerTwoLoc));
  }
}
