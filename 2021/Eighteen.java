import java.io.*;
import java.util.*;
public class Eighteen{
  static EighteenNode root = null; // placeholder
  public static boolean process(EighteenNode node){
    ArrayList<EighteenNode> iter = root.returniter();
    EighteenNode leftint = null;
    System.out.println("processing " + node);
    for(int i = 0; i < iter.size(); i++){
      EighteenNode curnode = iter.get(i);
      if(curnode.isnumber){
        leftint = curnode;
      }
      if((!curnode.isnumber) && (curnode.layer >= 4)){ // explode it
        EighteenNode possiblerightnode = null;
        for(int j = (i + 3); j < iter.size(); j++){ // i + 1 would be curnode.l , i + 2 would be curnode.r, neither of which we want
          possiblerightnode = iter.get(j);
          if(possiblerightnode.isnumber){
            break;
          }
        }
        System.out.println("exploding " + curnode + " with lnode " + leftint + ", rightnode " + possiblerightnode);
        curnode.explode(leftint, possiblerightnode);
        return true; // processed, so loop again
      }
      if(curnode.checkSplit()){
        System.out.println(curnode + " has been split");
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
