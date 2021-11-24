import java.util.ArrayList;
public class NineGraphNode{
  public String name;
  public ArrayList<StringIntPair> paths;
  public NineGraphNode(String name){
    this.name = name;
    paths = new ArrayList<StringIntPair>();
  }
  public void addPath(String destination, int distance){
    paths.add(new StringIntPair(destination, distance));
  }
}
