import java.io.*;
import java.util.*;
public class Sixteen{
  //static int versionsum = 0;
  static String remainingparse = null;
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
  public static ArrayList<Integer> parse(String packet, int maxparse) throws Exception{ // returns packet value(s)
    if(maxparse == 0){
      remainingparse = packet;
      //System.out.println("remaining parse being set to "+packet);
      return null;
    }
    if(packet == null){
      System.out.println("packet is null somehow");
    }
    if(packet == null || packet.length() == 0 || isAllZero(packet)){
      return null;
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
      ArrayList<Integer> output = new ArrayList<Integer>();
      output.add(val);
      ArrayList<Integer> tempv = parse(packet.substring(rs), maxparse - 1); // parse some only
      if(tempv != null){
        output.addAll(tempv);
      }
      return output;
    }
    int oplen = charToVal(packet.charAt(6));
    //System.out.println(packet);
    int opcount = (oplen == 1 ? bitstringToVal(packet.substring(7,18)) : bitstringToVal(packet.substring(7,22))); // either subpacket count or bit count
    String newpacket = (oplen == 1 ? packet.substring(18) : packet.substring(22));
    String toparselater = "";
    int newmaxparse = -1;
    if(oplen == 0){
      toparselater = newpacket.substring(opcount);
      newpacket = newpacket.substring(0,opcount);
    }
    if(oplen == 1){
      newmaxparse = opcount;
    }
    //System.out.println("values getting " + newpacket);
    //System.out.println("with parse " + newmaxparse);
    ArrayList<Integer> values = parse(newpacket, newmaxparse); // parse only some
    if(oplen == 1){
      toparselater = remainingparse;
    }
    int output = 0; // set this to whatever the identity is for the operator function
    switch(type){
      case 0:
        System.out.println(values);
        for(int i = 0; i < values.size(); i++){
          output += values.get(i);
        }
        /*
        System.out.println(oplen);
        System.out.println(opcount);
        System.out.println(toparselater);
        System.out.println(newpacket);
        System.out.println(values.toString());
        */
        break;
      case 1:
        output = 1;
        for(int i = 0; i < values.size(); i++){
          output *= values.get(i);
        }
        /*
        System.out.println("oplen "+oplen);
        System.out.println("opcount "+opcount);
        System.out.println("toparselater "+toparselater);
        System.out.println("newpacket "+newpacket);
        System.out.println(values.toString());
        */
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
          /*
          System.out.println(oplen);
          System.out.println(opcount);
          System.out.println(toparselater);
          System.out.println(newpacket);
          */
          throw new Exception("Expected 2 input values for values, instead got: " + values.toString());
        }
        output = (values.get(0) == values.get(1) ? 1 : 0);
        break;
    }
    ArrayList<Integer> arroutput = new ArrayList<Integer>();
    arroutput.add(output);
    /* this is wrong, oplen not opcount
    if(opcount == 1){
      if(remainingparse == null){
        toparselater = "";
      }
      toparselater = remainingparse;
    }
    */
    if(toparselater == null){
      toparselater = "";
    }
    ArrayList<Integer> tempv = parse(toparselater,-1);
    if(tempv != null){
      arroutput.addAll(tempv);
    }
    System.out.println("returning " + arroutput.toString());
    return arroutput;
  }
  
  public static void main(String[] args) throws FileNotFoundException, Exception{
    File f = new File(args[0]);
    Scanner in = new Scanner(f);
    String hex = in.nextLine();
    //System.out.println(hex);
    String bin = strHexToBin(hex);
    //System.out.println(bin);
    ArrayList<Integer> result = parse(bin,-1);
    System.out.println(result.get(0));
    //System.out.println(versionsum);
  }
}