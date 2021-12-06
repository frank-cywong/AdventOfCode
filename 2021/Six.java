import java.io.*;
import java.util.*;
public class Six{
  /* part 1
  static ArrayList<Integer> d = new ArrayList<Integer>();
  public static void incday(){
    for(int i = 0; i < d.size(); i++){
      if(d.get(i) == 0){
        d.set(i, 6);
        d.add(9); // 9 to make it 8 after one -1
        continue;
      }
      d.set(i, d.get(i) - 1);
    }
  }
  */
  static long[] d = new long[9];
  public static void incday(){
    long zero = 0;
    for(int i = 0; i < d.length; i++){
      if(i == 0){
        zero = d[i];
      }
      if(i == 8){
        d[i] = zero;
        continue;
      }
      d[i] = d[i+1];
      if(i == 6){
        d[i] += zero;
      }
    }
  }
  public static void main(String[] args) throws FileNotFoundException{
    File f = new File(args[0]);
    Scanner in = new Scanner(f);
    String s = in.nextLine();
    String[] temp = s.split(",");
    for(int i = 0; i < temp.length; i++){
      d[Integer.parseInt(temp[i])]++;
    }
    int turns = Integer.parseInt(args[1]);
    for(int i = 0; i < turns; i++){
      incday();
    }
    long count = 0;
    for(int i = 0; i < d.length; i++){
      count += d[i];
    }
    System.out.println(count);
  }
}