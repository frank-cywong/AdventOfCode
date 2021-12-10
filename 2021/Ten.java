import java.io.*;
import java.util.*;
public class Ten{
  public static char getClosing(char c){
    switch(c){
      case '(':
        return ')';
      case '<':
        return '>';
      case '[':
        return ']';
      case '{':
        return '}';
    }
    System.out.println("Invalid character passed to getClosing");
    return c;
  }
  public static int getReturnValue(char c){
    switch(c){
      case ')':
        return 3;
      case '>':
        return 25137;
      case ']':
        return 57;
      case '}':
        return 1197;
    }
    System.out.println("Invalid character passed to getReturnValue");
    return 0;
  }
  public static boolean isOpening(char c){
    return(c == '(' || c == '<' || c == '[' || c == '{');
  }
  public static int getSecondReturnValue(char c){
    switch(c){
      case '(':
        return 1;
      case '<':
        return 4;
      case '[':
        return 2;
      case '{':
        return 3;
    }
    System.out.println("Invalid character passed to getClosing");
    return 0;
  }
  public static long autoComplete(ArrayList<Character> chars){
    long output = 0;
    long exp = 1;
    for(int i = 0; i < chars.size(); i++){
      output += getSecondReturnValue(chars.get(i)) * exp;
      exp *= 5;
    }
    return output;
  }
  public static long checkCorrupt(String s){
    ArrayList<Character> opening_brackets = new ArrayList<Character>();
    char temp, toCompare;
    for(int i = 0; i < s.length(); i++){
      temp = s.charAt(i);
      if(isOpening(temp)){
        opening_brackets.add(temp);
      } else {
        if(getClosing(opening_brackets.get(opening_brackets.size()-1)) == temp){
          opening_brackets.remove(opening_brackets.size()-1);
        } else {
          // corrupted string
          // return getReturnValue(temp); for part 1
          return 0;
        }
      }
    }
    return autoComplete(opening_brackets);
  }
  public static void main(String[] args) throws FileNotFoundException{
    File f = new File(args[0]);
    Scanner in = new Scanner(f);
    String temp;
    long count = 0;
    ArrayList<Long> counts = new ArrayList<Long>();
    while(in.hasNextLine()){
      temp = in.nextLine();
      count = checkCorrupt(temp);
      if(count != 0){
        counts.add(count);
      }
    }
    Collections.sort(counts);
    //System.out.println(counts.size());
    System.out.println(counts.get(counts.size()/2));
  }
}
