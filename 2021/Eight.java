import java.util.*;
import java.io.*;
public class Eight{
  //static int[] count = new int[10]; for part 1
  static int count = 0;
  public static int toBits(String s){
    int output = 0;
    for(int i = 0; i < s.length(); i++){
      output |= (1 << (s.charAt(i) - 'a'));
    }
    return output;
  }
  public static String bitmapToString(int x){
    String s = "";
    for(int i = 0; i < 7; i++){
      if((x & (1 << i)) != 0){
        s = (s + (char)('a' + i));
      }
    }
    return s;
  }
  public static void processLine(String s){
    String[] temp = s.split(" \\| "); // \| is needed to escape the | (logical or) in the regex, \\ is needed to escape the \
    String outputstring = temp[1];
    String[] output = outputstring.split(" ");
    String keysstring = temp[0];
    String[] keys = keysstring.split(" ");
    String[] namedkeys = new String[10];
    ArrayList<Integer> fivers = new ArrayList<Integer>();
    ArrayList<Integer> sixers = new ArrayList<Integer>();
    String[] decodelist = new String[7];
    /* index:
     0
    6 1
     2
    5 3
     4
    */
    for(int i = 0; i < keys.length; i++){
      if(keys[i].length() == 2){
        namedkeys[1] = keys[i];
        continue;
      }
      if(keys[i].length() == 3){
        namedkeys[7] = keys[i];
        continue;
      }
      if(keys[i].length() == 4){
        namedkeys[4] = keys[i];
        continue;
      }
      if(keys[i].length() == 5){ // char 2,3, or 5
        fivers.add(toBits(keys[i]));
        continue;
      }
      if(keys[i].length() == 6){ // char 0,6,or 9
        sixers.add(toBits(keys[i]));
        continue;
      }
      if(keys[i].length() == 7){
        namedkeys[8] = keys[i];
        continue;
      }
    }
    decodelist[0] = bitmapToString(toBits(namedkeys[7]) & (~toBits(namedkeys[1]))); // difference between 4 and 1 -> top bit
    int tempunit = toBits(namedkeys[4]) | toBits(decodelist[0]);
    // the six segment one with only 1 bit difference from tempunit is 9
    for(int i = 0; i < sixers.size(); i++){
      if(bitmapToString(sixers.get(i) ^ tempunit).length() == 1){
        namedkeys[9] = bitmapToString(sixers.get(i));
        sixers.remove(i);
      }
    }
    decodelist[4] = bitmapToString(toBits(namedkeys[9]) ^ tempunit);
    decodelist[5] = bitmapToString(~toBits(namedkeys[9]));
    /*
    System.out.println(namedkeys[9]);
    System.out.println(sixers.toString());
    System.out.println(Arrays.toString(decodelist));
    */
    // the four segment with top, bottom, left and right
    int tempunit2 = toBits(namedkeys[1]) | toBits(decodelist[0]) | toBits(decodelist[4]);
    // the five segment one with only 1 bit difference from tempunit2 is 3
    for(int i = 0; i < fivers.size(); i++){
      if(bitmapToString(fivers.get(i) ^ tempunit2).length() == 1){
        namedkeys[3] = bitmapToString(fivers.get(i));
        fivers.remove(i);
      }
    }
    decodelist[2] = bitmapToString(toBits(namedkeys[3]) ^ tempunit2);
    decodelist[6] = bitmapToString(~(toBits(namedkeys[3]) | toBits(decodelist[5])));
    //System.out.println(Arrays.toString(decodelist));
    // the five segment one with only 1 bit difference from 0,2,4,5 is 2
    int tempunit3 = toBits(decodelist[0]) | toBits(decodelist[2]) | toBits(decodelist[4]) | toBits(decodelist[5]);
    for(int i = 0; i < fivers.size(); i++){
      if(bitmapToString(fivers.get(i) ^ tempunit3).length() == 1){
        namedkeys[2] = bitmapToString(fivers.get(i));
        fivers.remove(i);
      }
    }
    decodelist[1] = bitmapToString(toBits(namedkeys[2]) ^ tempunit3);
    decodelist[3] = bitmapToString(toBits(namedkeys[1]) ^ toBits(decodelist[1]));
    namedkeys[0] = bitmapToString(toBits(namedkeys[8]) ^ toBits(decodelist[2]));
    namedkeys[5] = bitmapToString(fivers.get(0));
    namedkeys[6] = bitmapToString(toBits(namedkeys[8]) ^ toBits(decodelist[1]));
    //System.out.println(Arrays.toString(decodelist));
    //System.out.println(Arrays.toString(namedkeys));
    int tempnum = 0;
    for(int i = 0; i < output.length; i++){
      for(int j = 0; j < namedkeys.length; j++){
        if(toBits(namedkeys[j]) == toBits(output[i])){
          tempnum *= 10;
          tempnum += j;
        }
      }
    }
    count += tempnum;
  }
  public static void main(String[] args) throws FileNotFoundException{
    File f = new File(args[0]);
    Scanner in = new Scanner(f);
    String temp;
    while(in.hasNextLine()){
      temp = in.nextLine();
      processLine(temp);
    }
    // System.out.println(count[1]+count[4]+count[7]+count[8]); for part 1
    System.out.println(count);
  }
}