import java.util.ArrayList;
public class EighteenNode{
  public boolean isnumber;
  public int value;
  public EighteenNode l = null;
  public EighteenNode r = null;
  public int layer;
  public EighteenNode(EighteenNode l, EighteenNode r, int layer){
    isnumber = false;
    this.l = l;
    this.r = r;
    this.layer = layer;
  }
  public EighteenNode(int n, int layer){
    isnumber = true;
    value = n;
    this.layer = layer;
  }
  public ArrayList<EighteenNode> returniter(){
    ArrayList<EighteenNode> output = new ArrayList<EighteenNode>();
    output.add(this);
    if(isnumber){
      return output;
    } else {
      output.addAll(l.returniter()); // sort of depth-first search
      output.addAll(r.returniter());
    }
    return output;
  }
  public boolean checkSplit(){
    if(!isnumber){
      return false;
    }
    if(value < 10){
      return false;
    }
    isnumber = false;
    l = new EighteenNode(value / 2, layer + 1);
    r = new EighteenNode(value - (value / 2), layer + 1);
    value = 0;
    return true;
  }
  public void explode(EighteenNode lnum, EighteenNode rnum){ // checking done by Eighteen.java, if layer >= 4
    if(lnum != null){
      if(!lnum.isnumber){
        System.out.println("non number lnum passed to explode, likely error");
      }
      lnum.value += l.value;
    }
    if(rnum != null){
      if(!rnum.isnumber){
        System.out.println("non number lnum passed to explode, likely error");
      }
      rnum.value += r.value;
    }
    this.value = 0;
    this.isnumber = true;
    this.l = null;
    this.r = null;
  }
  public long getMagnitude(){
    if(isnumber){
      return value;
    }
    return(3 * l.getMagnitude() + 2 * r.getMagnitude());
  }
  public void updateLayer(){
    if(isnumber){
      return;
    }
    l.layer = this.layer + 1;
    r.layer = this.layer + 1;
    l.updateLayer();
    r.updateLayer();
  }
  public String toString(){
    if(isnumber){
      return ("" + value);
    }
    return("[" + l.toString() + "," + r.toString() + "]");
  }
  public static EighteenNode addAsRoot(EighteenNode a, EighteenNode b){
    a.layer = 1;
    b.layer = 1;
    a.updateLayer();
    b.updateLayer();
    return(new EighteenNode(a,b,0));
  }
  public static EighteenNode parseString(String s){
    try{
      int val = Integer.parseInt(s);
      return(new EighteenNode(val, 0));
    } catch (NumberFormatException e){}
    EighteenNode root = new EighteenNode(null,null,0);
    s = s.substring(1,s.length()-1);
    int bracketcount = 0;
    for(int i = 0; i < s.length(); i++){
      if(s.charAt(i) == '['){
        bracketcount++;
      }
      if(s.charAt(i) == ']'){
        bracketcount--;
      }
      if(s.charAt(i) == ',' && bracketcount == 0){
        root.l = parseString(s.substring(0,i));
        root.r = parseString(s.substring(i+1));
        break;
      }
    }
    root.updateLayer();
    return root;
  }
}
