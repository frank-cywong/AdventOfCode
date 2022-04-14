import java.util.*;
import java.io.*;
public class Twenty{
  public static void debugPrint(boolean[][] image){
    for(int i = 0; i < image.length; i++){
      for(int j = 0; j < image[0].length; j++){
        System.out.print(image[i][j] ? "#" : ".");
      }
      System.out.print("\n");
    }
  }
  public static int computeEnhancementIndex(boolean[][] image, int starti, int startj){
    int output = 0;
    for(int i = (starti - 1); i <= (starti + 1); i++){
      for(int j = (startj - 1); j <= (startj + 1); j++){
        output <<= 1;
        if(i >= 0 && j >= 0 && i < (image.length) && j < (image[i].length)){
          if(image[i][j]){
            output |= 1;
          }
        }
      }
    }
    return output;
  }
  public static boolean[][] enhance(boolean[][] image, boolean[] algorithm){
    boolean[][] output = new boolean[image.length + 2][image[0].length + 2];
    for(int i = -1; i <= image.length; i++){
      for(int j = -1; j <= image[0].length; j++){
        output[i+1][j+1] = algorithm[computeEnhancementIndex(image, i, j)];
      }
    }
    return output;
  }
  public static void main(String[] args) throws FileNotFoundException{
    File f = new File(args[0]);
    Scanner in = new Scanner(f);
    String rawalgorithm = in.nextLine();
    boolean[] algorithm = new boolean[rawalgorithm.length()];
    for(int i = 0; i < rawalgorithm.length(); i++){
      algorithm[i] = (rawalgorithm.charAt(i) == '#');
    }
    ArrayList<String> lines = new ArrayList<String>();
    while(in.hasNextLine()){
      String curline = in.nextLine();
      if(curline.equals("")){
        continue;
      }
      lines.add(curline);
    }
    boolean[][] image = new boolean[lines.size()][lines.get(0).length()];
    for(int i = 0; i < lines.size(); i++){
      String curline = lines.get(i);
      for(int j = 0; j < curline.length(); j++){
        image[i][j] = (curline.charAt(j) == '#');
      }
    }
    int enhanceCount = Integer.parseInt(args[1]);
    debugPrint(image);
    for(int i = 0; i < enhanceCount; i++){
      image=enhance(image, algorithm);
      debugPrint(image);
    }
    int output = 0;
    for(int i = 0; i < image.length; i++){
      for(int j = 0; j < image[i].length; j++){
        if(image[i][j]){
          output++;
        }
      }
    }
    System.out.println(output);
  }
}
