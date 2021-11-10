import java.security.MessageDigest;
import java.security.*;
public class Four{
	public static void main(String[] args) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("MD5");
		String key = args[0];
		int i = 1;
		byte[] hash;
		while(true){
			md.update((key+i).getBytes());
			hash = md.digest();
			if(hash[0] == 0 && hash[1] == 0 && ((hash[2] & (byte)0xF0) == 0)){
				System.out.println(i);
				return;
			}
			i++;
			if(i % 10000 == 0){
				System.out.println("On i = "+i);
			}
		}
	}
}