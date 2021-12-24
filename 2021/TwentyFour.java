import java.util.*;
import java.io.*;
public class TwentyFour{
  public static int[] registers = new int[4];
  public static String inpstream = "";
  public static int getRegister(char c){
    return registers[c-'w'];
  }
  public static void setRegister(char c, int i){
    registers[c-'w'] = i;
  }
  public static void inp(char c){
    setRegister(c, Integer.parseInt(inpstream.substring(0,1)));
    inpstream = inpstream.substring(1);
  }
  public static void addImmediate(char c, int i){
    setRegister(c, getRegister(c) + i);
  }
  public static void addRegister(char c, char v){
    addImmediate(c, getRegister(v));
  }
  public static void mulImmediate(char c, int i){
    setRegister(c, getRegister(c) * i);
  }
  public static void mulRegister(char c, char v){
    mulImmediate(c, getRegister(v));
  }
  public static void divImmediate(char c, int i){
    if(i == 0){
      throw new ArithmeticException("div a b called with b = 0");
    }
    setRegister(c, getRegister(c) / i);
  }
  public static void divRegister(char c, char v){
    divImmediate(c, getRegister(v));
  }
  public static void modImmediate(char c, int i){
    int a = getRegister(c);
    if(a < 0 || i <= 0){
      throw new ArithmeticException("mod a b called with invalid values");
    }
    setRegister(c, a % i);
  }
  public static void modRegister(char c, char v){
    modImmediate(c, getRegister(v));
  }
  public static void eqlImmediate(char c, int i){
    setRegister(c, getRegister(c) == i ? 1 : 0);
  }
  public static void eqlRegister(char c, char v){
    eqlImmediate(c, getRegister(v));
  }
  public static boolean execInst(String inst){
    /*
    inp = 0
    add = 2,3
    mul = 4,5
    div = 6,7
    mod = 8,9
    eql = 10,11
    */
    int instrtype = 0;
    int addressingmode = 0;
    String[] temp = inst.split(" ");
    char a1 = 0;
    char a2 = 0;
    int v = 0;
    a1 = temp[1].charAt(0);
    //System.out.println(Arrays.toString(temp));
    try{
      if(temp.length > 2){
        v = Integer.parseInt(temp[2]);
      }
    } catch (NumberFormatException e){
      a2 = temp[2].charAt(0);
      addressingmode = 1;
    }
    switch(temp[0]){
      case "inp":
        break;
      case "add":
        instrtype = 2;
        break;
      case "mul":
        instrtype = 4;
        break;
      case "div":
        instrtype = 6;
        break;
      case "mod":
        instrtype = 8;
        break;
      case "eql":
        instrtype = 10;
        break;
    }
    try{
      switch(instrtype + addressingmode){
        case 0:
          inp(a1);
          break;
        case 2:
          addImmediate(a1,v);
          break;
        case 3:
          addRegister(a1,a2);
          break;
        case 4:
          mulImmediate(a1,v);
          break;
        case 5:
          mulRegister(a1,a2);
          break;
        case 6:
          divImmediate(a1,v);
          break;
        case 7:
          divRegister(a1,a2);
          break;
        case 8:
          modImmediate(a1,v);
          break;
        case 9:
          modRegister(a1,a2);
          break;
        case 10:
          eqlImmediate(a1,v);
          break;
        case 11:
          eqlRegister(a1,a2);
          break;
      }
    } catch(ArithmeticException e){
      return false;
    }
    return true;
  }
  public static boolean execAll(String[] instr){
    registers = new int[4];
    boolean returnval;
    for(int i = 0; i < instr.length; i++){
      returnval = execInst(instr[i]);
      if(!returnval){
        return false;
      }
    }
    return true;
  }
  public static boolean hasZero(long s){
    while(s != 0){
      if(s % 10 == 0){
        return true;
      }
      s /= 10;
    }
    return false;
  }
  public static long testCases(String[] instr){
    long curtest = 99999999999999L;
    while(true){
      if(!hasZero(curtest)){
        System.out.println("Testing " + curtest);
        inpstream = Long.toString(curtest);
        boolean success = execAll(instr);
        if(success){
          System.out.println(Arrays.toString(registers));
          if(registers[3] == 0){
            return curtest;
          }
        }
      }
      curtest--;
    }
  }
  public static void main(String[] args) throws FileNotFoundException{
    File f = new File(args[0]);
    Scanner in =  new Scanner(f);
    String temp;
    ArrayList<String> instr = new ArrayList<String>();
    while(in.hasNextLine()){
      temp = in.nextLine();
      instr.add(temp);
    }
    //inpstream = "7";
    //execAll(instr.toArray(new String[0]));
    //System.out.println(Arrays.toString(registers));
    System.out.println(testCases(instr.toArray(new String[0])));
  }
}