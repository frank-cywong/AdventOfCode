import java.util.*;
public class NineGraphNode{
  public String name;
  public HashMap<String,Integer> paths;
  public NineGraphNode(String name){
    this.name = name;
    paths = new HashMap<String,Integer>();
  }
  public void addPath(String destination, int distance){
    paths.put(destination, distance);
  }
  public int getDistance(String dest){
    return(paths.get(dest));
  }
}
