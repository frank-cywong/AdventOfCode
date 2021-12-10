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
  public static int checkCorrupt(String s){
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
          return getReturnValue(temp);
        }
      }
    }
    return 0;
  }
  public static void main(String[] args) throws FileNotFoundException{
    File f = new File(args[0]);
    Scanner in = new Scanner(f);
    String temp;
    int count = 0;
    while(in.hasNextLine()){
      temp = in.nextLine();
      count += checkCorrupt(temp);
    }
    System.out.println(count);
  }
}
