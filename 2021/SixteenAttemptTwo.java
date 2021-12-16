import java.io.*;
import java.util.*;
public class SixteenAttemptTwo{
  //static int versionsum = 0;
  static String globalpacket;
  public static int charToVal(char c){
    return (c - '0');
  }
  public static int bitstringToVal(String s){
    int v = 0;
    for(int i = 0; i < s.length(); i++){
      v <<= 1;
      if(charToVal(s.charAt(i)) == 1){
        v |= 1;
      }
    }
    return v;
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
  public static ArrayList<Integer> parseFixedLength(int bits) throws Exception{
    int initialbitcount = globalpacket.length();
    ArrayList<Integer> output = new ArrayList<Integer>();
    while(globalpacket.length() > (initialbitcount - bits)){
      output.add(parseOneValue());
    }
    return output;
  }
  public static ArrayList<Integer> parseByCount(int count) throws Exception{
    ArrayList<Integer> output = new ArrayList<Integer>();
    for(int i = 0; i < count; i++){
      output.add(parseOneValue());
    }
    return output;
  }
  public static int parseOneValue() throws Exception{ // returns packet value(s)
    String packet = globalpacket;
    if(packet.length() == 0 || isAllZero(packet)){
      globalpacket = "";
      return 0;
    }
    int parsed = 0;
    int version = bitstringToVal(packet.substring(0,3));
    //versionsum += version;
    int type = bitstringToVal(packet.substring(3,6));
    if(type == 4) {
      int control = 1;
      int rs = 6;
      int val = 0;
      while(control != 0){
        control = charToVal(packet.charAt(rs));
        val <<= 4;
        val += bitstringToVal(packet.substring(rs+1,rs+5));
        rs += 5;
      }
      globalpacket = packet.substring(rs);
      return val;
    }
    int oplen = charToVal(packet.charAt(6));
    //System.out.println(packet);
    int opcount = (oplen == 1 ? bitstringToVal(packet.substring(7,18)) : bitstringToVal(packet.substring(7,22))); // either subpacket count or bit count
    String newpacket = (oplen == 1 ? packet.substring(18) : packet.substring(22));
    ArrayList<Integer> values = null;
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
    //ArrayList<Integer> values = parse(newpacket, newmaxparse); // just parse everything else for now
    int output = 0; // set this to whatever the identity is for the operator function
    switch(type){
      case 0:
        for(int i = 0; i < values.size(); i++){
          output += values.get(i);
        }
        break;
      case 1:
        output = 1;
        for(int i = 0; i < values.size(); i++){
          output *= values.get(i);
        }
        break;
      case 2:
        output = Integer.MAX_VALUE;
        for(int i = 0; i < values.size(); i++){
          output = Math.min(output, values.get(i));
        }
        break;
      case 3:
        output = Integer.MIN_VALUE;
        for(int i = 0; i < values.size(); i++){
          output = Math.max(output, values.get(i));
        }
        break;
      case 5:
        if(values.size() != 2){
          throw new Exception("Expected 2 input values for values, instead got: " + values.toString());
        }
        output = (values.get(0) > values.get(1) ? 1 : 0);
        break;
      case 6:
        if(values.size() != 2){
          throw new Exception("Expected 2 input values for values, instead got: " + values.toString());
        }
        output = (values.get(0) < values.get(1) ? 1 : 0);
        break;
      case 7:
        if(values.size() != 2){
          throw new Exception("Expected 2 input values for values, instead got: " + values.toString());
        }
        output = (values.get(0) == values.get(1) ? 1 : 0);
        break;
    }
    return output;
  }

  public static void main(String[] args) throws FileNotFoundException, Exception{
    File f = new File(args[0]);
    Scanner in = new Scanner(f);
    String hex = in.nextLine();
    //System.out.println(hex);
    String bin = strHexToBin(hex);
    //System.out.println(bin);
    globalpacket = bin;
    ArrayList<Integer> result = parseFixedLength(bin.length());
    System.out.println(result.get(0));
    //System.out.println(versionsum);
  }
}
