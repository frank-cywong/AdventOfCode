import java.io.*;
import java.util.*;
public class Nine{
  static Map<String,NineGraphNode> nodes = new HashMap<String,NineGraphNode>();
  /*
  public static int iteratenodes(Set<String> nodesleft, int curminpath, int pathuntilnow, String prevnode){
    String[] nodesleftlist = (String[])nodesleft.toArray();
    Set<String> temp;
    int temp2;
    for(int i = 0; i < nodesleftlist.length; i++){
      temp.addAll(nodesleft);
      temp.remove(nodes.get(nodesleftlist[i]));
      if(prevnode != null){
        pathuntilnow += nodes.get(prevnode).paths.get(nodesleftlist[i]);
      }
      temp2 = iteratenodes(temp,curminpath,pathuntilnow,nodesleftlist[i]);
      if(temp2 < curminpath){
        curminpath = temp2;
      }
    }
    return curminpath;
  }
  */
  // Does this properly in O(2^n*n^2)
  static int[][] visitedAndLast;
  static boolean[][] setted;
  static String[] nodenames;
  static int nodecount;
  public static int getVisitedAndLast(int visited, int last){
    if(setted[visited][last]){
      return visitedAndLast[visited][last];
    }
    int tempminval = Integer.MAX_VALUE;
    int lastvisitedset = visited & (~ 1 << last);
    int temp;
    String nodenamestring;
    String curnodenamestring = nodenames[last];
    for(int i = 0; i < nodecount; i++){
      if(((1 << last) & lastvisitedset) == 0){
        continue;
      }
      nodenamestring = nodenames[i];
      temp = getVisitedAndLast(lastvisitedset,i) + nodes.get(nodenamestring).getDistance(curnodenamestring);
      if(temp < tempminval){
        tempminval = temp;
      }
    }
    return tempminval;
  }
  public static int bestPathFromN(int startnodeindex){
    visitedAndLast = new int[1 << nodecount][nodecount];
    setted = new boolean[1 << nodecount][nodecount];
    visitedAndLast[1 << startnodeindex][startnodeindex] = 0;
    setted[1 << startnodeindex][startnodeindex] = true;
    int tempmin = Integer.MAX_VALUE;
    int temp;
    for(int i = 0; i < nodecount; i++){
      if(i == startnodeindex){
        continue;
      }
      temp = getVisitedAndLast(1 << nodecount - 1,i);
      if (temp < tempmin){
        tempmin = temp;
      }
    }
    return tempmin;
  }
  public static void main(String[] args) throws FileNotFoundException{
    File f = new File(args[0]);
    Scanner in = new Scanner(f);
    String temp;
    String[] temp2;
    String[] temp3;
    NineGraphNode tempnode;
    while (in.hasNextLine()){
      temp = in.nextLine();
      temp2 = temp.split(" to ");
      temp3 = new String[3];
      temp3[0] = temp2[0];
      temp2 = temp2[1].split(" = ");
      temp3[1] = temp2[0];
      temp3[2] = temp2[1];
      tempnode = nodes.get(temp3[0]);
      if(tempnode == null){
        tempnode = new NineGraphNode(temp3[0]);
        nodes.put(temp3[0],tempnode);
      }
      tempnode.addPath(temp3[1],Integer.parseInt(temp3[2]));
      tempnode = nodes.get(temp3[1]);
      if(tempnode == null){
        tempnode = new NineGraphNode(temp3[1]);
        nodes.put(temp3[1],tempnode);
      }
      tempnode.addPath(temp3[0],Integer.parseInt(temp3[2]));
    }
    //System.out.println(iteratenodes(nodes.keySet(),Integer.MAX_VALUE,0,null));
    nodenames = nodes.keySet().toArray(new String[0]);
    nodecount = nodenames.length;
    int tempmin2 = Integer.MAX_VALUE;
    int curtestval;
    for(int i = 0; i < nodecount; i++){
      curtestval = bestPathFromN(i);
      if(curtestval < tempmin2){
        tempmin2 = curtestval;
      }
    }
    System.out.println(tempmin2);
  }
}
