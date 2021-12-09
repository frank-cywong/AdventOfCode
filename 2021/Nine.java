import java.io.*;
import java.util.*;
public class Nine{
  static ArrayList<byte[]> heightdata = new ArrayList<byte[]>();
  public static int testMin(int i, int j){ // highly unoptimised but probably good enough
    byte val = heightdata.get(i)[j];
    if(i > 0 && heightdata.get(i-1)[j] <= val){
      return 0;
    }
    if(j > 0 && heightdata.get(i)[j-1] <= val){
      return 0;
    }
    if(j < (heightdata.get(i).length - 1) && heightdata.get(i)[j+1] <= val){
      return 0;
    }
    if(i < (heightdata.size() - 1) && heightdata.get(i+1)[j] <= val){
      return 0;
    }
    return (val + 1);
  }
  public static void main(String[] args) throws FileNotFoundException{
    File f = new File(args[0]);
    Scanner in = new Scanner(f);
    String temp;
    byte[] row;
    int risk = 0;
    while(in.hasNextLine()){
      temp = in.nextLine();
      row = new byte[temp.length()];
      for(int i = 0; i < temp.length(); i++){
        row[i] = (byte)(temp.charAt(i) - '0');
      }
      heightdata.add(row);
    }
    for(int i = 0; i < heightdata.size(); i++){
      for(int j = 0; j < heightdata.get(i).length; j++){
        risk += testMin(i,j);
      }
    }
    System.out.println(risk);
  }
}