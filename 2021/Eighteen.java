import java.io.*;
import java.util.*;
public class Eighteen{
  static EighteenNode root = null; // placeholder
  public static boolean process(EighteenNode node){
    ArrayList<EighteenNode> iter = root.returniter();
    EighteenNode leftint = null;
    for(int i = 0; i < iter.size(); i++){
      EighteenNode curnode = iter.get(i);
      if(curnode.isnumber){
        leftint = curnode;
      }
      if((!curnode.isnumber) && (curnode.layer >= 4)){ // explode it
        EighteenNode possiblerightnode = null;
        for(int j = (i + 1); j < iter.size(); j++){
          possiblerightnode = iter.get(j);
          if(possiblerightnode.isnumber){
            break;
          }
        }
        curnode.explode(leftint, possiblerightnode);
        return true; // processed, so loop again
      }
      if(curnode.checkSplit()){
        return true;
      }
    }
    return false;
  }
  public static void main(String[] args) throws FileNotFoundException{
    File f = new File(args[0]);
    Scanner in = new Scanner(f);
    String line;
    while(in.hasNextLine()){
      line = in.nextLine();
      if(root == null){
        root = EighteenNode.parseString(line);
        continue;
      }
      root = EighteenNode.addAsRoot(root, EighteenNode.parseString(line));
      while(process(root)){}
    }
    System.out.println(root.getMagnitude());
    System.out.println(root);
  }
}
