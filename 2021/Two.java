import java.io.*;
import java.util.*;
public class Two{
  public static void main(String[] args) throws FileNotFoundException{
    File f = new File(args[0]);
    Scanner in = new Scanner(f);
    String[] temp;
    String instruction;
    int move;
    int dx = 0;
    int dy = 0;
    int aim = 0;
    while(in.hasNextLine()){
      temp = in.nextLine().split(" ");
      instruction = temp[0];
      move = Integer.parseInt(temp[1]);
      switch(instruction){
        case "up":
          aim -= move;
          break;
        case "forward":
          dx += move;
          dy += (move * aim);
          break;
        case "down":
          aim += move;
          break;
      }
    }
    System.out.println(dx*dy);
  }
}