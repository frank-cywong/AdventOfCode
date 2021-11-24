import java.io.*;
import java.util.*;
public class Nine{
  static Map<String,NineGraphNode> nodes = new HashMap<String,NineGraphNode>();
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
  public static void main(String[] args) throws FileNotFoundException{
    File f = new File(args[0]);
    Scanner in = new Scanner(f);
    String temp;
    String[] temp2;
    String[] temp3;
    NineGraphNode tempnode;
    while (Scanner.hasNextLine()){
      temp = Scanner.nextLine();
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
    System.out.println(iteratenodes(nodes.keySet(),Integer.MAX_VALUE,0,null));
  }
}
