import java.io.*;
import java.util.*;
public class Fourteen{
  static HashMap<String, String> rules = new HashMap<String, String>();
  static String polymer;
  public static void doStep(){
    String newpolymer = "";
    String temp;
    for(int i = 0; i < polymer.length() - 1; i++){
      temp = polymer.substring(i,i+2);
      newpolymer += polymer.charAt(i);
      if(rules.containsKey(temp)){
        newpolymer += rules.get(temp);
      }
    }
    newpolymer += polymer.charAt(polymer.length() - 1);
    polymer = newpolymer;
  }
  public static int countFreq(){
    HashMap<Character, Integer> freq = new HashMap<Character, Integer>();
    char temp;
    for(int i = 0; i < polymer.length(); i++){
      temp = polymer.charAt(i);
      freq.put(temp,freq.getOrDefault(temp,0)+1);
    }
    Integer[] values = freq.values().toArray(new Integer[0]);
    Arrays.sort(values);
    return(values[values.length-1] - values[0]);
  }
  public static void main(String[] args) throws FileNotFoundException{
    File f = new File(args[0]);
    Scanner in = new Scanner(f);
    String temp;
    String[] rule;
    polymer = in.nextLine();
    while(in.hasNextLine()){
      temp = in.nextLine();
      if(temp.equals("")){
        continue;
      }
      rule = temp.split(" -> ");
      rules.put(rule[0], rule[1]);
    }
    int count = Integer.parseInt(args[1]);
    for(int i = 0; i < count; i++){
      doStep();
    }
    System.out.println(countFreq());
  }
}