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
          output++;
        }
        if(temp.charAt(i) == '\\'){
          if(temp.charAt(i+1) == 'x'){
            i += 3;
            output += 3;
          } else {
            i += 1;
            output += 1;
          }
        }
      }
    }
    System.out.println(output);
  }
}
