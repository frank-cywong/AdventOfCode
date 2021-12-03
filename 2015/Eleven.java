import java.util.*;
import java.io.*;
public class Eleven{
  public static boolean isInvalidChar(char c){
    return(c == 'i' || c == 'o' || c == 'l');
  }
  public static boolean isIncSubString(String s){
    return((s.charAt(1)-1 == s.charAt(0)) && (s.charAt(2)-1 == s.charAt(1)));
  }
  public static boolean isValid(String p){
    Set<String> twolist = new HashSet<String>();
    String twolast = null;
    String twocur = null;
    boolean hasPair = false;
    boolean hasIncreasing = false;
    for(int i = 0; i < p.length(); i++){
      if(isInvalidChar(p.charAt(i))){
        return false;
      }
      if((!hasPair) && i < p.length() - 1){
        if(twolast != null){
          twolist.add(twolast);
        }
        twolast = twocur;
        twocur = p.substring(i, i+2);
        if(twolist.contains(twocur)){
          hasPair = true;
        }
      }
      if((!hasIncreasing) && i < p.length() - 2){
        if(isIncSubString(p.substring(i, i+3))){
          hasIncreasing = true;
        }
      }
    }
    return hasPair && hasIncreasing;
  }
  public static void incStr(String s){
    char curchar;
    for(int i = s.length() - 1; i >= 0; i--){
      curchar = s.charAt(i);
      if(curchar != 'z'){
        s = s.substring(0,i) + (char)(curchar + 1) + s.substring(i+1);
        break;
      }
      s = s.substring(0,i) + "a" + s.substring(i+1);
    }
  }
  public static void main(String[] args) throws FileNotFoundException{
    File f = new File(args[0]);
    Scanner in = new Scanner(f);
    String temp = in.nextLine();
    while(!isValid(temp)){
      incStr(temp);
    }
    System.out.println(temp);
  }
}
