import java.io.*;
import java.util.Scanner;
public class Eight{
  public static void main(String[] args) throws FileNotFoundException{
    int output = 0;
    String temp;
    File f = new File(args[0]);
    Scanner in = new Scanner(f);
    while(in.hasNextLine()){
      temp = in.nextLine();
      for(int i = 0; i < temp.length(); i++){
        if(temp.charAt(i) == '"'){
          output++; // for both part 1 and 2
        }
        if(temp.charAt(i) == '\\'){
          output++; //for part 1 and 2
          //i++; // for part 1 only
          /*
          if(temp.charAt(i) == 'x'){ // for part 1 only
            i += 2;
            output += 2;
          }
          */
        }
      }
      output += 2; // for part 2
    }
    System.out.println(output);
  }
}
