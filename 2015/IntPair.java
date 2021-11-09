public class IntPair{
	public int a,b;
	public IntPair(int a, int b){
		this.a = a;
		this.b = b;
	}
	public boolean equals(Object o){
		if(o == null || getClass() != o.getClass()){
			return false;
		}
		IntPair p = (IntPair) o;
		return(a == p.a && b == p.b);
	}
	public int hashCode(){
		return (a + b);
	}
	public String toString(){
		return("("+a+", "+b+")");
	}
}