import java.io.*;
import java.util.*;
public class Ten{
  public static ArrayList<Integer> lookAndSay(ArrayList<Integer> data){
    ArrayList<Integer> output = new ArrayList<Integer>();
    int matchdigit = 0;
    int matchfreq = 0;
    for(int i = 0; i < data.size(); i++){
      if(matchdigit == 0){
        matchdigit = data.get(i);
        matchfreq = 1;
        continue;
      }
      if(matchdigit == data.get(i)){
        matchfreq++;
        continue;
      }
      output.add(matchfreq);
      output.add(matchdigit);
      matchdigit = data.get(i);
      matchfreq = 1;
    }
    output.add(matchfreq);
    output.add(matchdigit);
    return output;
  }
  public static void main(String[] args) throws FileNotFoundException{
    File f = new File(args[0]);
    Scanner in = new Scanner(f);
    String temp = "";
    while(in.hasNextLine()){
      temp = in.nextLine();
    }
    ArrayList<Integer> seq = new ArrayList<Integer>(temp.length());
    for(int i = 0; i < temp.length(); i++){
      seq.add(Integer.parseInt(temp.substring(i,i+1)));
    }
    int count = Integer.parseInt(args[1]);
    for(int i = 0; i < count; i++){
      seq = lookAndSay(seq);
    }
    System.out.println(seq.size());
  }
}
