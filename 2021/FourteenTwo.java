import java.io.*;
import java.util.*;
public class FourteenTwo{
  static HashMap<String, Long> freqcount = new HashMap<String, Long>();
  static HashMap<String, String[]> rules = new HashMap<String, String[]>();
  static Set<String> substrings;
  static String polymer = null;
  public static void doStep(){
    HashMap<String, Long> newfreqcount = new HashMap<String, Long>();
    Iterator<String> it = substrings.iterator();
    String key;
    String[] newkeys;
    long freq;
    while(it.hasNext()){
      key = it.next();
      newkeys = rules.get(key);
      freq = freqcount.getOrDefault(key, 0L);
      newfreqcount.put(newkeys[0], newfreqcount.getOrDefault(newkeys[0], 0L) + freq);
      newfreqcount.put(newkeys[1], newfreqcount.getOrDefault(newkeys[1], 0L) + freq);
    }
    freqcount = newfreqcount;
  }
  public static long countFreq(){
    Iterator<String> it = substrings.iterator();
    String key;
    HashMap<String, Long> charfreqcount = new HashMap<String, Long>();
    String ckey;
    long freq;
    charfreqcount.put(polymer.substring(0,1),1L); // for double-counting compensation
    charfreqcount.put(polymer.substring(polymer.length()-1, polymer.length()), 1L);
    while(it.hasNext()){
      key = it.next();
      freq = freqcount.getOrDefault(key, 0L);
      for(int i = 0; i < key.length(); i++){
        ckey = key.substring(i, i+1);
        charfreqcount.put(ckey, freq + charfreqcount.getOrDefault(ckey, 0L));
      }
    }
    System.out.println(charfreqcount);
    Long[] values = charfreqcount.values().toArray(new Long[0]);
    Arrays.sort(values);
    return((values[values.length-1] - values[0]) / 2); // due to double-counting
  }
  public static void main(String[] args) throws FileNotFoundException{
    File f = new File(args[0]);
    Scanner in = new Scanner(f);
    String temp;
    String[] rule;
    String[] inputpair;
    polymer = in.nextLine();
    while(in.hasNextLine()){
      temp = in.nextLine();
      if(temp.equals("")){
        continue;
      }
      rule = temp.split(" -> ");
      inputpair = new String[2];
      inputpair[0] = rule[0].substring(0,1) + rule[1];
      inputpair[1] = rule[1] + rule[0].substring(1,2);
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
      //System.out.println(freqcount);
      //System.out.println("Doing step: "+i);
    }
    System.out.println(countFreq());
  }
}