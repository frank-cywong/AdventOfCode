import java.io.*;
import java.util.Scanner;
public class Five{
  static byte[][] overlapUpToTwo = new byte[1000][1000];
  public static void condint(int x, int y){
    //System.out.println(x+", "+y); for testing
    if(overlapUpToTwo[x][y] < 2){
      overlapUpToTwo[x][y]++;
    }
  }
  public static void parseInstruction(String s){
    String[] temp, temp2;
    temp = s.split(" -> ");
    temp2 = temp[0].split(",");
    int x1,x2,y1,y2;
    x1 = Integer.parseInt(temp2[0]);
    y1 = Integer.parseInt(temp2[1]);
    temp2 = temp[1].split(",");
    x2 = Integer.parseInt(temp2[0]);
    y2 = Integer.parseInt(temp2[1]);
    int t;
    if(x1 != x2 && y1 != y2){
      //return; for part 1
      if(x2 < x1){ // normalise in terms of increasing x
        t = x2;
        x2 = x1;
        x1 = t;
        t = y2;
        y2 = y1;
        y1 = t;
      }
      for(int x = x1; x <= x2; x++){
        condint(x,(y2 > y1 ? y1 - x1 + x : y1 - x + x1));
      }
      return;
    }
    if(x2 < x1){
      t = x2;
      x2 = x1;
      x1 = t;
    }
    if(y2 < y1){
      t = y2;
      y2 = y1;
      y1 = t;
    }
    if(x1 == x2){
      for(int y = y1; y <= y2; y++){
        condint(x1,y);
      }
      return;
    }
    if(y1 == y2){
      for(int x = x1; x <= x2; x++){
        condint(x,y1);
      }
      return;
    }
  }
  public static void main(String[] args) throws FileNotFoundException{
    File f = new File(args[0]);
    Scanner in = new Scanner(f);
    String temp;
    while(in.hasNextLine()){
      temp = in.nextLine();
      parseInstruction(temp);
    }
    int count = 0;
    for(int i = 0; i < 1000; i++){
      for(int j = 0; j < 1000; j++){
        if(overlapUpToTwo[i][j] >= 2){
          count++;
        }
      }
    }
    System.out.println(count);
  }
}