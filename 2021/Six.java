import java.io.*;
import java.util.*;
public class Six{
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
  public static void main(String[] args) throws FileNotFoundException{
    File f = new File(args[0]);
    Scanner in = new Scanner(f);
    String s = in.nextLine();
    String[] temp = s.split(",");
    for(int i = 0; i < temp.length; i++){
      d.add(Integer.parseInt(temp[i]));
    }
    int turns = Integer.parseInt(args[1]);
    for(int i = 0; i < turns; i++){
      incday();
    }
    System.out.println(d.size());
  }
}