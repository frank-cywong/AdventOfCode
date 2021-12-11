import java.io.*;
import java.util.*;
public class Eleven{
  static int[][] data = new int[10][10];
  static boolean[][] flashed = new boolean[10][10];
  static int flashcount = 0;
  public static void flash(int x, int y){
    flashed[x][y] = true;
    for(int dx = -1; dx <= 1; dx++){
      for(int dy = -1; dy <= 1; dy++){
        if((x + dx >= 0) && (x + dx < 10) && (y + dy >= 0) && (y + dy < 10)){
          if(!flashed[x+dx][y+dy]){
            data[x+dx][y+dy]++;
            checkflash(x+dx,y+dy);
          }
        }
      }
    }
  }
  public static void checkflash(int x, int y){
    if((data[x][y] > 9) && !flashed[x][y]){
      flash(x,y);
    }
  }
  public static void step(){
    for(int i = 0; i < 10; i++){
      for(int j = 0; j < 10; j++){
        data[i][j]++;
        checkflash(i,j);
      }
    }
    //System.out.println(Arrays.deepToString(data));
    for(int i = 0; i < 10; i++){
      for(int j = 0; j < 10; j++){
        if(flashed[i][j]){
          data[i][j] = 0;
          flashcount++;
          flashed[i][j] = false;
        }
      }
    }
  }
  public static void main(String[] args) throws FileNotFoundException{
    File f = new File(args[0]);
    Scanner in = new Scanner(f);
    String temp;
    for(int i = 0; i < 10; i++){
      temp = in.nextLine();
      for(int j = 0; j < 10; j++){
        data[j][i] = Integer.parseInt(""+temp.charAt(j)); //some slightly bad decisions here means that things are stored by x-major
      }
    }
    int count = Integer.parseInt(args[1]);
    for(int i = 0; i < count; i++){
      step();
    }
    System.out.println(flashcount);
  }
}