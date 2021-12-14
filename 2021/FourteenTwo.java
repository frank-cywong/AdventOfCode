import java.io.*;
import java.util.*;
public class FourteenTwo{
  static HashMap<String, Long> freqcount = new HashMap<String, Long>();
  static HashMap<String, String[]> rules = new HashMap<String, String[]>();
  static Set<String> substrings;
  public static void doStep(){
    HashMap<String, Long> newfreqcount = new HashMap<String, Long>();
    
  }
  public static long countFreq(){
    Long[] values = freqcount.values().toArray(new Long[0]);
    Arrays.sort(values);
    return(values[values.length-1] - values[0]);
  }
  public static void main(String[] args) throws FileNotFoundException{
    File f = new File(args[0]);
    Scanner in = new Scanner(f);
    String temp;
    String[] rule;
    String[] inputpair;
    String polymer = in.nextLine();
    while(in.hasNextLine()){
      temp = in.nextLine();
      if(temp.equals("")){
        continue;
      }
      rule = temp.split(" -> ");
      inputpair = new String[2];
      inputpair[0] = rule[0].substring(0,1) + rule[1];
      inputpair[1] = rule[1].substring(1,2) + rule[1];
      rules.put(rule[0], inputpair);
    }
    substrings = rules.keySet();
    int count = Integer.parseInt(args[1]);
    String s;
    for(int i = 0; i < polymer.length() - 1; i++){
      s = polymer.substring(i, i+2);
      freqcount.put(s, freqcount.getOrDefault(s, 0L) + 1);
    }
    for(int i = 0; i < count; i++){
      doStep();
      //System.out.println("Doing step: "+i);
    }
    System.out.println(countFreq());
  }
}