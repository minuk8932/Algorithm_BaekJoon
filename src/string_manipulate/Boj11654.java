package string_manipulate;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj11654 {
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char ascii = (char) br.read();
		
		System.out.println(new Integer(ascii));
	}
}
