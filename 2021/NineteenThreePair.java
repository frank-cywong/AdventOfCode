public class NineteenThreePair{
  public int x;
  public int y;
  public int z;
  public NineteenThreePair(int x, int y, int z){
    this.x = x;
    this.y = y;
    this.z = z;
  }
  public int hashCode(){
    return (17 * x + 7 * y + z);
  }
  public boolean equals(Object o){
    if(o == null){
      return false;
    }
    if(this.getClass() != o.getClass()){
      return false;
    }
    NineteenThreePair other = (NineteenThreePair) o;
    return((this.x == other.x) && (this.y == other.y) && (this.z == other.z));
  }
  public String toString(){
    return("("+this.x+", "+this.y+", "+this.z+")");
  }
}