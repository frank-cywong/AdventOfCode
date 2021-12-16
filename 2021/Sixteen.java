import java.io.*;
import java.util.*;
public class Sixteen{
  static int versionsum = 0;
  public static int charToVal(char c){
    return (c - '0');
  }
  public static int bitstringToVal(String s){
    int v = 0;
    for(int i = 0; i < s.length(); i++){
      v <<= 1;
      if(charToVal(s.charAt(0)) == 1){
        v |= 1;
      }
    }
    return v;
  }
  public static String strHexToBin(String hex){
    String output = "";
    char temp;
    for(int i = 0; i < hex.length(); i++){
      temp = hex.charAt(i);
      temp = (char)(temp >= 'A' ? temp - 'A' + 10 : temp - '0');
      output += Integer.toBinaryString((int)temp);
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
  public static void parse(String packet){
    if(packet.length() == 0 || isAllZero(packet)){
      return;
    }
    int version = bitstringToVal(packet.substring(0,3));
    versionsum += version;
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
      parse(packet.substring(rs)); // just parse everything else
      return;
    }
    int oplen = charToVal(packet.charAt(6));
    int opcount = (oplen == 1 ? bitstringToVal(packet.substring(7,18)) : bitstringToVal(packet.substring(7,22))); // either subpacket count or bit count
    String newpacket = (oplen == 1 ? packet.substring(18) : packet.substring(22));
    parse(newpacket); // just parse everything else for now
    return;
  }
  public static void main(String[] args) throws FileNotFoundException{
    File f = new File(args[0]);
    Scanner in = new Scanner(f);
    String hex = in.nextLine();
    String bin = strHexToBin(hex);
    parse(bin);
    System.out.println(versionsum);
  }
}