import java.io.*;
public class Five{
	public static boolean isVowel(char c){
		return(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
	}
	public static boolean isProhibited(String s){
		return(s.equals("ab") || s.equals("cd") || s.equals("pq") || s.equals("xy"));
	}
	public static boolean isNice(String s){
		int vowelcount = 0;
		boolean charrepeated = false;
		char lastchar = 0;
		for(int i = 0; i < s.length(); i++){
			if(isVowel(s.charAt(i))){
				vowelcount++;
			}
			if(lastchar == s.charAt(i)){
				charrepeated = true;
			}
			lastchar = s.charAt(i);
			if(i < (s.length() - 1)){
				if(isProhibited(s.substring(i,i+2))){
					return false;
				}
			}
		}
		if(vowelcount >= 3 && charrepeated){
			return true;
		}
		return false;
	}
	public static void main(String[] args) throws IOException{
		FileInputStream f = new FileInputStream(args[0]);
		String temp = "";
		char curchar;
		curchar = 0;
		int count = 0;
		while(curchar != (char)-1){
			curchar = (char)f.read();
			if(curchar != '\n'){
				temp += curchar;
			} else {
				if(isNice(temp)){
					count++;
				}
				temp = "";
			}
		}
		System.out.println(count);
	}
}