import java.io.*;
import java.util.*;
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
	public static boolean isNiceTwo(String s){
		Set<String> repeated = new HashSet<String>();
		boolean overlap = false;
		boolean letterbetween = false;
		String lastpair = "";
		for(int i = 0; i < s.length(); i++){
			if(!overlap && (i < s.length() - 2)){
				if(repeated.contains(s.substring(i, i+2))){
					overlap = true;
				} else {
				repeated.add(lastpair);
				lastpair = s.substring(i,i+2);
				}
			}
			if(!letterbetween && (i < s.length() - 2) && (s.charAt(i) == s.charAt(i+2))){
				letterbetween = true;
			}
			if(overlap && letterbetween){
				return true;
			}
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
				if(isNiceTwo(temp)){
					count++;
				}
				temp = "";
			}
		}
		System.out.println(count);
	}
}