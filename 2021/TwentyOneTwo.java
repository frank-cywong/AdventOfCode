import java.util.*;
public class TwentyOneTwo{
  static long[][][] universeCounter = new long[22][22][2]; // dp as follows: long[a][b][c]: # of universes where player c is moving now, where player 0 has a points and player 1 has b points, if pts >= 21, set to 21
  static Deque<Integer> processNext = new ArrayDeque<Integer>(); // list of a, b, c, as encoded by: (a << 6) | (b << 1) | c to process
  static int loca;
  static int locb;
  public static long simulate(){
  }
  public static void main(String[] args){
    loca = Integer.parseInt(args[0]);
    locb = Integer.parseInt(args[1]);
    universeCounter[0][0][0] = 1;
    processNext.addLast(0); // 0, 0, 0 for a, b, c
    System.out.println(simulate());
  }
}
