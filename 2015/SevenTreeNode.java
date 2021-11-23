import java.util.*;
public class SevenTreeNode{
  private String l_node, r_node;
  private int mode;
  private char value;
  private boolean valueset;
  private static Map<String, SevenTreeNode> nodemap;
  public SevenTreeNode(String l_node, String r_node, int mode, Map<String, SevenTreeNode> m){
    this.l_node = l_node;
    this.r_node = r_node;
    this.mode = mode;
    this.valueset = false;
    nodemap = m;
  }
  public SevenTreeNode(char value, Map<String, SevenTreeNode> m){
    this.value = value;
    this.valueset = true;
    nodemap = m;
  }
  public char getValue(){
    if(valueset){
      return value;
    }
    switch (mode){
      case 1: // AND
        char p1, p2;
        try{
          p1 = (char)Integer.parseInt(l_node);
        } catch (NumberFormatException e){
          p1 = (char)(nodemap.get(l_node).getValue());
        }
        try{
          p2 = (char)Integer.parseInt(r_node);
        } catch (NumberFormatException e){
          p2 = (char)(nodemap.get(r_node).getValue());
        }
        value = (char)(p1 & p2);
        break;
      case 2: // OR
        value = (char)(nodemap.get(l_node).getValue() | nodemap.get(r_node).getValue());
        break;
      case 3: // LSHIFT
        value = (char)(nodemap.get(l_node).getValue() << Integer.parseInt(r_node));
        break;
      case 4: // RSHIFT
        value = (char)(nodemap.get(l_node).getValue() >>> Integer.parseInt(r_node)); // unsigned right shift
        break;
      case 5: // NOT
        value = (char)(~nodemap.get(l_node).getValue());
        break;
      case 6: // nothing
        value = (nodemap.get(l_node).getValue());
        break;
    }
    valueset = true;
    return value;
  }
  public String toString(){
    return("Node with l = "+l_node+", r = "+r_node+", mode = "+mode+", value = "+getValue());
  }
}
