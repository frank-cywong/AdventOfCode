import java.io.*;
import java.util.*;
public class Four{
  static boolean[] won; // if board has won, for part 2
  static int lastwon;
  static long[] v; // stores in bitwise form for if a number was called
  static ArrayList<int[][]> boarddata = new ArrayList<int[][]>();
  static long[] wins = {0b11111L, 0b11111L << 5, 0b11111L << 10, 0b11111L << 15, 0b11111L << 20, 0b1000010000100001000010000L, 0b0100001000010000100001000L, 0b0010000100001000010000100L, 0b0001000010000100001000010L, 0b0000100001000010000100001L};
  public static ArrayList<Integer> hasWin(){
    ArrayList<Integer> o = new ArrayList<Integer>();
    for(int i = 0; i < v.length; i++){
      for(int j = 0; j < wins.length; j++){
        if((v[i] & wins[j]) == wins[j]){
          o.add(i);
        }
      }
    }
    return o;
  }
  public static int computeScore(int index, int last){
    int count = 0;
    long local = v[index];
    for(int i = 0; i < 25; i++){
      if((local & (1L << i)) == 0){
        count += boarddata.get(index)[i / 5][i % 5];
      }
    }
    return(count * last);
  }
  public static int[] genRowFromString(String s){
    int[] o = new int[5];
    String[] temp = s.split(" ");
    int j = 0;
    for(int i = 0; i < temp.length; i++){
      if(temp[i].equals("")){
        continue;
      }
      o[j] = Integer.parseInt(temp[i]);
      j++;
    }
    return o;
  }
  public static void main(String[] args) throws FileNotFoundException{
    File f = new File(args[0]);
    Scanner in = new Scanner(f);
    String numbers = null;
    String temp;
    int bc = 0;
    int rc = 0;
    int[][] tempboard = new int[5][5];
    while(in.hasNextLine()){
      temp = in.nextLine();
      if(numbers == null){
        numbers = temp;
        continue;
      }
      if(temp.equals("")){
        continue;
      }
      tempboard[rc] = genRowFromString(temp);
      rc++;
      if(rc == 5){
        rc = 0;
        boarddata.add(tempboard);
        bc++;
        tempboard = new int[5][5];
      }
    }
    String[] numstring = numbers.split(",");
    ArrayList<Integer> win;
    int cur = 0;
    v = new long[boarddata.size()];
    won = new boolean[boarddata.size()];
    for(int i = 0; i < numstring.length; i++){
      cur = Integer.parseInt(numstring[i]);
      for(int j = 0; j < v.length; j++){
        if(won[j]){
          continue;
        }
        for(int k = 0; k < 25; k++){
          if(boarddata.get(j)[k / 5][k % 5] == cur){
            v[j] |= (1L << k);
          }
        }
      }
      System.out.println(cur);
      win = hasWin();
      if(win.size() != 0){
        for(int j = 0; j < win.size(); j++){
          if(won[win.get(j)]){
            continue;
          }
          won[win.get(j)] = true;
          lastwon = computeScore(win.get(j),cur);
          System.out.println(win.get(j) + " has won");
        }
      }
    }
    System.out.println(lastwon);
  }
}