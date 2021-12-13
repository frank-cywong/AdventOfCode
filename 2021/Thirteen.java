import java.io.*;
import java.util.*;
public class Thirteen{
  static boolean[][] data = new boolean[1500][1500]; // stored in [y][x]
  static int ymax = 0;
  static int xmax = 0;
  public static void doFold(String instruction){
    String temp = instruction.substring(11);
    //System.out.println(temp);
    char dir = temp.charAt(0);
    int coord = Integer.parseInt(temp.substring(2));
    if(dir == 'y'){
      int newxmax = xmax;
      int newymax = coord;
      boolean[][] newdata = new boolean[newymax][newxmax];
      for(int i = 0; i < newymax; i++){
        for(int j = 0; j < newxmax; j++){
          newdata[i][j] = data[i][j] || data[2*coord-i][j];
        }
      }
      data = newdata;
      xmax = newxmax;
      ymax = newymax;
    }
    if(dir == 'x'){
      int newxmax = coord;
      int newymax = ymax;
      boolean[][] newdata = new boolean[newymax][newxmax];
      for(int i = 0; i < newymax; i++){
        for(int j = 0; j < newxmax; j++){
          newdata[i][j] = data[i][j] || data[i][2*coord-j];
        }
      }
      data = newdata;
      xmax = newxmax;
      ymax = newymax;
    }
  }
  public static void main(String[] args) throws FileNotFoundException{
    File f = new File(args[0]);
    Scanner in = new Scanner(f);
    int runcount = Integer.parseInt(args[1]);
    String temp;
    int i = 0;
    int x;
    int y;
    String[] splitted;
    boolean donereading = false;
    while(in.hasNextLine()){
      temp = in.nextLine();
      if(temp.equals("")){
        donereading = true;
        continue;
      }
      if(donereading){
        if(i >= runcount){
          break;
        }
        doFold(temp);
        i++;
      }
      else{
        splitted = temp.split(",");
        x = Integer.parseInt(splitted[0]);
        y = Integer.parseInt(splitted[1]);
        if(y > ymax){
          ymax = y;
        }
        if(x > xmax){
          xmax = x;
        }
        data[y][x] = true;
      }
    }
    int count = 0;
    for(i = 0; i < data.length; i++){
      for(int j = 0; j < data[i].length; j++){
        if(data[i][j]){
          count++;
        }
      }
    }
    System.out.println(count);
  }
}