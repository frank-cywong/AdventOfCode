public class SevenTreeNode{
  private SevenTreeNode l_node, r_node;
  private short mode, value;
  private boolean valueset;
  public SevenTreeNode(SevenTreeNode l_node, SevenTreeNode r_node, short mode){
    this.l_node = l_node;
    this.r_node = r_node;
    this.mode = mode;
    this.valueset = false;
  }
  public SevenTreeNode(short value){
    this.value = value;
    this.valueset = true;
  }
  public short getValue(){
    if(valueset){
      return value;
    }
    switch (mode){
      case 1: // AND
        value = l_node.getValue() & r_node.getValue();
        break;
      case 2: // OR
        value = l_node.getValue() | r_node.getValue();
        break;
      case 3: // LSHIFT
        value = l_node.getValue() << r_node.getValue();
        break;
      case 4: // RSHIFT
        value = l_node.getValue() >>> r_node.getValue(); // unsigned right shift
        break;
      case 5: // NOT
        value = ~l_node.getValue();
        break;
      valueset = true;
      return value;
    }
  }
  public static int unsignedShortToSignedInt(short i){
    if(i >= 0){
      return (int)i;
    } else {
      return(65536 - (int)i);
    }
  }
}
