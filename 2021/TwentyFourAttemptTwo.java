import java.util.*;
import java.io.*;
public class TwentyFourAttemptTwo{
  static int[][] data = new int[14][3]; // divfactor, k, j
  static int[] digits = new int[14];
  public static void test(){
    digits = new int[] {9,9,9,9,9,9,9,9,9,9,9,9,9,9}; // has to be set like this due to -1 at the end otherwise
    boolean[] forceset = new boolean[14];
    boolean redo = false;
    while(true){
      redo = false;
      int z = 0;
      int zmod = 0;
      for(int i = 0; i < 14; i++){
        zmod = z % 26;
        int k = data[i][1];
        if(k < 0){
          if(zmod + k > 10){
            digits[i-1] -= (zmod + k - 9);
            System.out.println("Redoing with digits: " + Arrays.toString(digits) + " and index " + i);
            redo = true;
            forceset[i-1] = true;
            break;
          }
          if(zmod + k <= 0){
            for(int j = i - 1; j >= 0; j--){
              if(!forceset[j] && digits[j] > 1){
                digits[j]--;
                forceset[j] = true;
                redo = true;
                break;
              }
            }
            System.out.println("Redoing with digits: " + Arrays.toString(digits) + " and index " + i);
            break;
          }
          if(!forceset[i]){
            digits[i] = zmod + k;
            //System.out.println(zmod);
          }
        }
        z /= data[i][0];
        if(zmod + k - digits[i] != 0){
          z = digits[i] + data[i][2] + 26 * z;
        }
      }
      if(!redo){
        break;
      }
    }
    System.out.println(Arrays.toString(digits));
  }
  public static void main(String[] args) throws FileNotFoundException{
    File f = new File(args[0]);
    Scanner in = new Scanner(f);
    int rowcount = 0;
    String temp;
    int inputcount = 0;
    while(in.hasNextLine()){
      temp = in.nextLine();
      rowcount++;
      if(rowcount == 18){
        rowcount = 0;
        inputcount++;
      }
      if(rowcount == 5){
        data[inputcount][0] = Integer.parseInt(temp.split(" ")[2]);
      }
      if(rowcount == 6){
        data[inputcount][1] = Integer.parseInt(temp.split(" ")[2]);
      }
      if(rowcount == 16){
        data[inputcount][2] = Integer.parseInt(temp.split(" ")[2]);
      }
    }
    System.out.println(Arrays.deepToString(data));
    test();
  }
}