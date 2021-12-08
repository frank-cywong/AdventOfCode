import java.util.*;
import java.io.*;
public class Eight{
  static int[] count = new int[10];
  public static void processLine(String s){
    String[] temp = s.split(" \\| "); // \| is needed to escape the | (logical or) in the regex, \\ is needed to escape the \
    String outputstring = temp[1];
    String[] output = outputstring.split(" ");
    String keysstring = temp[0];
    String[] keys = keysstring.split(" ");
    for(int i = 0; i < output.length; i++){
      if(output[i].length() == 2){
        count[1]++;
        continue;
      }
      if(output[i].length() == 3){
        count[7]++;
        continue;
      }
      if(output[i].length() == 4){
        count[4]++;
        continue;
      }
      if(output[i].length() == 7){
        count[8]++;
        continue;
      }
    }
  }
  public static void main(String[] args) throws FileNotFoundException{
    File f = new File(args[0]);
    Scanner in = new Scanner(f);
    String temp;
    while(in.hasNextLine()){
      temp = in.nextLine();
      processLine(temp);
    }
    System.out.println(count[1]+count[4]+count[7]+count[8]);
  }
}