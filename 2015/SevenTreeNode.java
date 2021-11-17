public class SevenTreeNode{
  private SevenTreeNode l_node, r_node;
  private int mode;
  private char value;
  private boolean valueset;
  public SevenTreeNode(SevenTreeNode l_node, SevenTreeNode r_node, int mode){
    this.l_node = l_node;
    this.r_node = r_node;
    this.mode = mode;
    this.valueset = false;
  }
  public SevenTreeNode(char value){
    this.value = value;
    this.valueset = true;
  }
  public char getValue(){
    if(valueset){
      return value;
    }
    switch (mode){
      case 1: // AND
        value = (char)(l_node.getValue() & r_node.getValue());
        break;
      case 2: // OR
        value = (char)(l_node.getValue() | r_node.getValue());
        break;
      case 3: // LSHIFT
        value = (char)(l_node.getValue() << r_node.getValue());
        break;
      case 4: // RSHIFT
        value = (char)(l_node.getValue() >>> r_node.getValue()); // unsigned right shift
        break;
      case 5: // NOT
        value = (char)(~l_node.getValue());
        break;
    }
    valueset = true;
    return value;
  }
}
