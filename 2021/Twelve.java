import java.util.*;
import java.io.*;
public class Twelve{
  static HashMap<String, Integer> visited = new HashMap<String, Integer>();
  static HashMap<String, Set<String>> nodes = new HashMap<String, Set<String>>();
  //static ArrayList<String> path = new ArrayList<String>();
  static int pathcount = 0;
  static boolean smallplus = false;
  public static boolean isBig(String node){
    return(Character.isUpperCase(node.charAt(0)));
  }
  public static void dfs(String node){
    if(node.equals("end")){
      pathcount++;
      //System.out.println(path.toString());
      return;
    }
    //System.out.println(node);
    //path.add(node);
    //System.out.println(path.toString());
    Set<String> adj = nodes.get(node);
    if(visited.containsKey(node)){
      visited.put(node, visited.get(node) + 1);
    } else {
      visited.put(node,1);
    }
    Iterator<String> it = adj.iterator();
    String tempnode;
    while(it.hasNext()){
      tempnode = it.next();
      if(tempnode.equals("start")){
        continue;
      }
      if(isBig(tempnode) || !visited.containsKey(tempnode) || visited.get(tempnode) == 0){
        dfs(tempnode);
      } else if (!smallplus && visited.get(tempnode) == 1){
        smallplus = true;
        dfs(tempnode);
        smallplus = false;
      }
    }
    visited.put(node, visited.get(node) - 1);
    //path.remove(path.size()-1);
  }
  public static void main(String[] args) throws FileNotFoundException{
    File f = new File(args[0]);
    Scanner in = new Scanner(f);
    String temp;
    String[] inst;
    while(in.hasNextLine()){
      temp = in.nextLine();
      inst = temp.split("-");
      if(nodes.containsKey(inst[0])){
        nodes.get(inst[0]).add(inst[1]);
      } else {
        nodes.put(inst[0], new HashSet<String>());
        nodes.get(inst[0]).add(inst[1]);
      }
      if(nodes.containsKey(inst[1])){
        nodes.get(inst[1]).add(inst[0]);
      } else {
        nodes.put(inst[1], new HashSet<String>());
        nodes.get(inst[1]).add(inst[0]);
      }
    }
    dfs("start");
    System.out.println(pathcount);
  }
}