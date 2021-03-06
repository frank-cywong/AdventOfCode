import java.io.*;
import java.util.*;
public class SixteenAttemptTwo{
  //static int versionsum = 0;
  static String globalpacket;
  public static int charToVal(char c){
    return (c - '0');
  }
  public static long bitstringToVal(String s){
    /*
    long v = 0;
    for(int i = 0; i < s.length(); i++){
      v <<= 1;
      if(charToVal(s.charAt(i)) == 1){
        v |= 1;
      }
    }
    if(v > (1 << 16)){
      System.out.println("potential overflow error " + v);
    }
    */
    return Integer.parseInt(s,2);
  }
  public static String strHexToBin(String hex){
    String output = "";
    char temp;
    String temps;
    for(int i = 0; i < hex.length(); i++){
      temp = hex.charAt(i);
      temps = Integer.toBinaryString((temp >= 'A' ? temp - 'A' + 10 : temp - '0'));
      while(temps.length() < 4){
        temps = "0" + temps;
      }
      output += temps;
    }
    return output;
  }
  public static boolean isAllZero(String s){
    for(int i = 0; i < s.length(); i++){
      if(s.charAt(i) != '0'){
        return false;
      }
    }
    return true;
  }
  public static ArrayList<Long> parseFixedLength(int bits) throws Exception{
    int initialbitcount = globalpacket.length();
    ArrayList<Long> output = new ArrayList<Long>();
    while(globalpacket.length() > (initialbitcount - bits)){
      output.add(parseOneValue());
      if(output.get(output.size()-1) < 0){
        System.out.println("warning potential less than 0 error");
      }
    }
    if(globalpacket.length() != (initialbitcount - bits)){
      System.out.println("potential error bit counts do not match up");
    }
    return output;
  }
  public static ArrayList<Long> parseByCount(int count) throws Exception{
    ArrayList<Long> output = new ArrayList<Long>();
    for(int i = 0; i < count; i++){
      output.add(parseOneValue());
      if(output.get(i) < 0){
        System.out.println("warning potential less than 0 error");
      }
    }
    return output;
  }
  public static long parseOneValue() throws Exception{ // returns packet value(s)
    String packet = globalpacket;
    if(packet.length() == 0 || isAllZero(packet)){
      globalpacket = "";
      System.out.println("potential error " + packet);
      return 0L;
    }
    int parsed = 0;
    int version = (int)bitstringToVal(packet.substring(0,3));
    //versionsum += version;
    int type = (int)bitstringToVal(packet.substring(3,6));
    if(type == 4) {
      int control = 1;
      int rs = 6;
      long val = 0;
      while(control != 0){
        control = charToVal(packet.charAt(rs));
        val <<= 4;
        val += bitstringToVal(packet.substring(rs+1,rs+5));
        rs += 5;
        if(rs > 50){
          System.out.println("potential overflow "+val);
        }
      }
      /*
      if(rs > 50){
        System.out.println("potential overflow "+val);
        System.out.println(packet.substring(0,rs));
      }
      */
      globalpacket = packet.substring(rs);
      System.out.println(packet.substring(0,rs) + " gave " + val);
      return val;
    }
    int oplen = charToVal(packet.charAt(6));
    //System.out.println(packet);
    int opcount = (int)(oplen == 1 ? bitstringToVal(packet.substring(7,18)) : bitstringToVal(packet.substring(7,22))); // either subpacket count or bit count
    String newpacket = (oplen == 1 ? packet.substring(18) : packet.substring(22));
    ArrayList<Long> values = null;
    if(oplen == 0){
      /*
      int parselimitbits = Integer.parseInt(opcount,2);
      toparselater = newpacket.substring(parselimitbits);
      newpacket = newpacket.substring(0,parselimitbits);
      */
      globalpacket = newpacket;
      values = parseFixedLength(opcount);
    }
    if(oplen == 1){
      globalpacket = newpacket;
      values = parseByCount(opcount);
    }
    if(oplen != 0 && oplen != 1){
      System.out.println("likely error, invalid oplen "+oplen);
    }
    if(oplen == 1 && opcount != values.size()){
      System.out.println("likely error, opcount "+opcount+" but values "+values);
    }
    //ArrayList<Integer> values = parse(newpacket, newmaxparse); // just parse everything else for now
    long output = 0; // set this to whatever the identity is for the operator function
    //System.out.println(type);
    if(values.get(0) == 131893){ //something seems wrong
      System.out.println(packet.length());
    }
    switch(type){
      case 0:
        for(int i = 0; i < values.size(); i++){
          output += values.get(i);
          if(output > (1L << 32)){
            System.out.println("warning potential overflow in 0:" + output);
            //System.out.println("sum of "+values);
          }
        }
        //System.out.println(type + " should be 0");
        break;
      case 1:
        output = 1;
        for(int i = 0; i < values.size(); i++){
          output *= values.get(i);
          if(output > (1L << 32)){
            System.out.println("warning potential overflow in 1:" + output);
          }
        }
        //System.out.println(type + " should be 1");
        break;
      case 2:
        output = Long.MAX_VALUE;
        for(int i = 0; i < values.size(); i++){
          output = Math.min(output, values.get(i));
          if(output > (1L << 32)){
            System.out.println("warning potential overflow in 2:" + output);
          }
        }
        break;
      case 3:
        output = Long.MIN_VALUE;
        for(int i = 0; i < values.size(); i++){
          output = Math.max(output, values.get(i));
          if(output > (1L << 32)){
            System.out.println("warning potential overflow in 3:" + output);
          }
        }
        break;
      case 5:
        if(values.size() != 2){
          throw new Exception("Expected 2 input values for values, instead got: " + values.toString());
        }
        output = ((values.get(0) > values.get(1)) ? 1 : 0);
        break;
      case 6:
        if(values.size() != 2){
          throw new Exception("Expected 2 input values for values, instead got: " + values.toString());
        }
        output = ((values.get(0) < values.get(1)) ? 1 : 0);
        break;
      case 7:
        if(values.size() != 2){
          throw new Exception("Expected 2 input values for values, instead got: " + values.toString());
        }
        System.out.println(" are these equal? " + values.get(0) + " == " + values.get(1)); // something seems off here as well
        /*
        So after a while of debugging, here's the issue:
        values is an ArrayList with type Long, the object Long
        in 5 and 6, nothing bad happens, as well, Long < Long isn't a thing
        But with Long == Long, it tries to compare its addresses
        However, this only happens for longer longs since shorter longs are interred https://stackoverflow.com/questions/19485818/why-are-2-long-variables-not-equal-with-operator-in-java
        this bug took me like 2 hours of my time to debug
        */
        output = ((values.get(0).equals(values.get(1))) ? 1 : 0);
        break;
    }
    System.out.println(values + ", type " + type + " = " + output);
    return output;
  }

  public static void main(String[] args) throws FileNotFoundException, Exception{
    File f = new File(args[0]);
    Scanner in = new Scanner(f);
    String hex = in.nextLine();
    System.out.println(hex);
    String bin = strHexToBin(hex);
    //System.out.println(bin);
    globalpacket = bin;
    System.out.println(parseOneValue());
    //ArrayList<Long> result = parseFixedLength(bin.length());
    //System.out.println(result.get(0));
    //System.out.println(versionsum);
  }
}
