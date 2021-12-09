import java.io.*;
import java.util.*;
public class Nine{
  static ArrayList<byte[]> heightdata = new ArrayList<byte[]>();
  static short basinindex = 1;
  static short[][] basinassigner = null; // placeholder
  static short[] basinsizes = null; // placeholder
  public static void testMin(int i, int j){ // highly unoptimised but probably good enough
    byte val = heightdata.get(i)[j];
    if(i > 0 && heightdata.get(i-1)[j] <= val){
      return;
    }
    if(j > 0 && heightdata.get(i)[j-1] <= val){
      return;
    }
    if(j < (heightdata.get(i).length - 1) && heightdata.get(i)[j+1] <= val){
      return;
    }
    if(i < (heightdata.size() - 1) && heightdata.get(i+1)[j] <= val){
      return;
    }
    basinassigner[i][j] = basinindex;
    basinindex++;
    propagate(i,j);
    return;
  }
  public static void propagate(int i, int j){
    short index = basinassigner[i][j];
    int topropagate = 0;
    if(i > 0 && basinassigner[i-1][j] == 0){
      basinassigner[i-1][j] = index;
      topropagate |= (1 << 0);
    }
    if(j > 0 && basinassigner[i][j-1] == 0){
      basinassigner[i][j-1] = index;
      topropagate |= (1 << 1);
    }
    if(j < (basinassigner[i].length - 1) && basinassigner[i][j+1] == 0){
      basinassigner[i][j+1] = index;
      topropagate |= (1 << 2);
    }
    if(i < (basinassigner.length - 1) && basinassigner[i+1][j] == 0){
      basinassigner[i+1][j] = index;
      topropagate |= (1 << 3);
    }
    if((topropagate & 1 << 0) != 0){
      propagate(i-1,j);
    }
    if((topropagate & 1 << 1) != 0){
      propagate(i,j-1);
    }
    if((topropagate & 1 << 2) != 0){
      propagate(i,j+1);
    }
    if((topropagate & 1 << 3) != 0){
      propagate(i+1,j);
    }
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
    basinassigner = new short[heightdata.size()][heightdata.get(0).length];
    for(int i = 0; i < basinassigner.length; i++){
      for(int j = 0; j < basinassigner[i].length; j++){
        if(heightdata.get(i)[j] == 9){
          basinassigner[i][j] = -1;
        }
      }
    };
    for(int i = 0; i < heightdata.size(); i++){
      for(int j = 0; j < heightdata.get(i).length; j++){
        testMin(i,j);
      }
    }
    basinsizes = new short[basinindex];
    for(int i = 0; i < basinassigner.length; i++){
      //System.out.println(Arrays.toString(basinassigner[i]));
      for(int j = 0; j < basinassigner[i].length; j++){
        if(basinassigner[i][j] > 0){
          basinsizes[basinassigner[i][j]]++;
        }
      }
    }
    Arrays.sort(basinsizes);
    //System.out.println(Arrays.toString(basinsizes));
    System.out.println(basinsizes[basinsizes.length-1]*basinsizes[basinsizes.length-2]*basinsizes[basinsizes.length-3]);
  }
}
