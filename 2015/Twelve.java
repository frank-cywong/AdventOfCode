import java.io.*;
public class Twelve{
  public static void main(String[] args) throws IOException{
    FileInputStream in = new FileInputStream(args[0]);
    int count = 0;
    char temp = 0;
    while(temp != (char)-1){
      temp = (char)in.read();
      try{
        count += Integer.parseInt(""+temp);
      } catch(Exception e){
      }
    }
    System.out.println(count);
  }
}
