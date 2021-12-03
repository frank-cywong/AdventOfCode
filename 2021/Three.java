import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Three{
  public static void main(String[] args) throws FileNotFoundException{
    File f = new File(args[0]);
    Scanner in = new Scanner(f);
    int[][] freqcount = new int[0][0];
    String temp = "";
    while(in.hasNextLine()){
      temp = in.nextLine();
      if(freqcount.length == 0){
        freqcount = new int[temp.length()][2];
      }
      for(int i = 0; i < temp.length(); i++){
        freqcount[i][temp.charAt(i)-'0']++;
      }
    }
    int max = 0;
    int min = 0;
    for(int i = 0; i < temp.length(); i++){
      if(freqcount[i][1] > freqcount[i][0]){
        max = (max << 1) + 1;
        min = (min << 1);
      } else {
        max = (max << 1);
        min = (min << 1) + 1;
      }
    }
    System.out.println(max*min);
  }
}
