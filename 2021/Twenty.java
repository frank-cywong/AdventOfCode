import java.util.*;
import java.io.*;
public class Twenty{
  public static int computeEnhancementIndex(boolean[][] image, int starti, int startj){
    int output = 0;
    for(int i = (starti - 1); i <= (starti + 1); i++){
      for(int j = (startj - 1); j <= (startj + 1); j++){
        output << 1;
        if(i >= 0 && j >= 0
      }
    }
  }
  public static boolean[][] enhance(boolean[][] image, boolean[] algorithm){
    boolean[][] output = new boolean[image.length + 2][image[0].length + 2];
    for(int i = 0; i < image.length; i++){
      for(int j = 0; j < image[i].length; j++){
        output[i][j] = algorithm[computeEnhancementIndex(image, i, j)];
      }
    }
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
      if(curline == ""){
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
    enhanceCount = Integer.parseInt(args[1]);
    for(int i = 0; i < enhanceCount; i++){
      image=enhance(image, algorithm);
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
