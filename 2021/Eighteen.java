import java.io.*;
import java.util.*;
public class Eighteen{
  static EighteenNode root = null; // placeholder
  public static boolean process(EighteenNode node){
    //System.out.println("processing " + node);
    boolean toreturn = false;
    while(processExplode(node)){toreturn = true;}
    if(processSplit(node)){return true;}
    return toreturn;
  }
  public static boolean processExplode(EighteenNode node){
    ArrayList<EighteenNode> iter = root.returniter();
    EighteenNode leftint = null;
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
        //System.out.println("exploding " + curnode + " with lnode " + leftint + ", rightnode " + possiblerightnode);
        curnode.explode(leftint, possiblerightnode);
        return true; // processed, so loop again
      }
    }
    return false;
  }
  public static boolean processSplit(EighteenNode node){
    ArrayList<EighteenNode> iter = root.returniter();
    for(int i = 0; i < iter.size(); i++){
      EighteenNode curnode = iter.get(i);
      if(curnode.checkSplit()){
        //System.out.println(curnode + " has been split");
        return true;
      }
    }
    return false;
  }
  public static void main(String[] args) throws FileNotFoundException{
    File f = new File(args[0]);
    Scanner in = new Scanner(f);
    String line;
    ArrayList<String> lines = new ArrayList<String>();
    while(in.hasNextLine()){
      line = in.nextLine();
      /* part 1
      if(root == null){
        root = EighteenNode.parseString(line);
        continue;
      }
      root = EighteenNode.addAsRoot(root, EighteenNode.parseString(line));
      while(process(root)){}
      //System.out.println("Line addition result: " + root + "\n\n");
      */
      lines.add(line);
    }
    long curmaxmagnitude = Long.MIN_VALUE;
    for(int i = 0; i < lines.size(); i++){
      for(int j = 0; j < lines.size(); j++){
        if(i == j){
          continue;
        }
        root = EighteenNode.addAsRoot(EighteenNode.parseString(lines.get(i)), EighteenNode.parseString(lines.get(j)));
        while(process(root)){}
        if(root.getMagnitude() > curmaxmagnitude){
          curmaxmagnitude = root.getMagnitude();
          System.out.println("New largest magnitude: " + curmaxmagnitude + " set by " + lines.get(i) + " + " + lines.get(j));
        }
      }
    }
    System.out.println(curmaxmagnitude);
    //System.out.println(root.getMagnitude());
    //System.out.println(root);
  }
}
