public class StringIntPair{
  public String first;
  public int second;
  public StringIntPair(String s, int i){
    this.first = s;
    this.second = i;
  }
  public String toString(){
    return("("+first+", "+second+")");
  }
}
