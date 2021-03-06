import java.util.*;
public class Eleven{
  public static boolean isInvalidChar(char c){
    return(c == 'i' || c == 'o' || c == 'l');
  }
  public static boolean isIncSubString(String s){
    return((s.charAt(1)-1 == s.charAt(0)) && (s.charAt(2)-1 == s.charAt(1)));
  }
  public static boolean isPair(String s){
    return(s.charAt(0) == s.charAt(1));
  }
  public static boolean isValid(String p){
    int hasPairCount = 0;
    boolean hasIncreasing = false;
    boolean nocheck = false;
    for(int i = 0; i < p.length(); i++){
      if(isInvalidChar(p.charAt(i))){
        return false;
      }
      if((hasPairCount < 2) && i < p.length() - 1){
        if(nocheck){
          nocheck = false;
        } else if (isPair(p.substring(i,i+2))){
          hasPairCount++;
          nocheck = true;
        }
      }
      if((!hasIncreasing) && i < p.length() - 2){
        if(isIncSubString(p.substring(i, i+3))){
          hasIncreasing = true;
        }
      }
    }
    return (hasPairCount >= 2) && hasIncreasing;
  }
  public static String incStr(String s){
    char curchar;
    for(int i = s.length() - 1; i >= 0; i--){
      curchar = s.charAt(i);
      if(curchar != 'z'){
        s = s.substring(0,i) + (char)(curchar + 1) + s.substring(i+1);
        return s;
      }
      s = s.substring(0,i) + "a" + s.substring(i+1);
    }
    return s;
  }
  public static void main(String[] args){
    String temp = args[0];
    while(!isValid(temp)){
      temp = incStr(temp);
      //System.out.println(temp);
    }
    System.out.println(temp);
  }
}
