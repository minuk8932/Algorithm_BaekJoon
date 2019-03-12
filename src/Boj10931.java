import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Boj10931 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(hashing(br.readLine()));
	}
	
	private static String hashing(String str) throws NoSuchAlgorithmException {
		StringBuilder sb = new StringBuilder();
		byte[] crypto = sha384(str);
		
		for(byte b: crypto) {
			sb.append(String.format("%02x", b));
		}
		
		return sb.toString();
	}
	
	private static byte[] sha384(String msg) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("SHA-384");
		md.update(msg.getBytes());
		
		return md.digest();
	}
}
