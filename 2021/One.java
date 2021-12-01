import java.util.*;
import java.io.*;
public class One{
  public static void main(String[] args) throws FileNotFoundException{
    File f = new File(args[0]);
    Scanner in = new Scanner(f);
    int last;
    int cur = -1;
    int output = 0;
    while(in.hasNextInt()){
      last = cur;
      cur = in.nextInt();
      if(cur > last && last >= 0){
        output++;
      }
    }
    System.out.println(output);
  }
}