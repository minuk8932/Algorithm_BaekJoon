package string_manipulate;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj5598 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String line = br.readLine();
		char[] str = line.toCharArray();
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < str.length; i++){
			if(str[i] > 'C'){
				str[i] = (char) (str[i] - 3);
			}
			else{
				str[i] = (char)(str[i] + 23);
			}
			sb.append(str[i]);
		}
		System.out.println(sb.toString());
	}
}
