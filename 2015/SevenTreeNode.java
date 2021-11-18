public class SevenTreeNode{
  private SevenTreeNode l_node, r_node;
  private int mode;
  private char value;
  private boolean valueset;
  private Map nodemap;
  public SevenTreeNode(SevenTreeNode l_node, SevenTreeNode r_node, int mode, Map m){
    this.l_node = l_node;
    this.r_node = r_node;
    this.mode = mode;
    this.valueset = false;
    nodemap = m;
  }
  public SevenTreeNode(char value, Map m){
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
        value = (char)(nodemap.get(l_node).getValue() & nodemap.get(r_node).getValue());
        break;
      case 2: // OR
        value = (char)(nodemap.get(l_node).getValue() | nodemap.get(r_node).getValue());
        break;
      case 3: // LSHIFT
        value = (char)(nodemap.get(l_node).getValue() << nodemap.get(r_node).getValue());
        break;
      case 4: // RSHIFT
        value = (char)(nodemap.get(l_node).getValue() >>> nodemap.get(r_node).getValue()); // unsigned right shift
        break;
      case 5: // NOT
        value = (char)(~nodemap.get(l_node).getValue());
        break;
    }
    valueset = true;
    return value;
  }
}
