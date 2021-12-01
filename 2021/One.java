import java.util.*;
import java.io.*;
public class One{
  public static void main(String[] args) throws FileNotFoundException{
    File f = new File(args[0]);
    Scanner in = new Scanner(f);
    int threebefore = -1;
    int twobefore = -1;
    int onebefore = -1;
    int cur = -1;
    int output = 0;
    while(in.hasNextInt()){
      threebefore = twobefore;
      twobefore = onebefore;
      onebefore = cur;
      cur = in.nextInt();
      if(cur > threebefore && threebefore >= 0){
        output++;
      }
    }
    System.out.println(output);
  }
}