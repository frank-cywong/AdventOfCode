public class One{
  public static void main(String[] args){
    String temp = args[0];
    int output = 0;
    boolean outputed = false;
    for(int i = 0; i < temp.length(); i++){
      if(temp.charAt(i) == '('){
        output++;
      }
      if(temp.charAt(i) == ')'){
        output--;
      }
      if(output < 0 && outputed == false){
        outputed = true;
        System.out.println(i+1);
      }
    }
    System.out.println(output);
  }
}
