package string_manipulate;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj1159 {
	private static final String NONE = "PREDAJA";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		String[] member = new String[N];
		
		for(int i = 0; i < N; i++){
			member[i] = br.readLine();
		}
		br.close();
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < N; i++){
			int cnt = 0;
			
			for(int j = 0; j < N; j++){
				if(member[i].charAt(0) == member[j].charAt(0)){
					cnt++;
				}
			}
			
			if(cnt >= 5 && sb.indexOf(String.valueOf(member[i].charAt(0))) == -1){
				sb.append(member[i].charAt(0));
			}
		}
		
		int leng = sb.length();
		
		char[] words = new char[leng];
		
		for(int i = 0; i < leng; i++){
			words[i] = sb.charAt(i);
		}
		Arrays.sort(words);
		sb.delete(0, leng);
		
		for(int i = 0; i < leng; i++){
			sb.append(words[i]);
		}
		
		System.out.println(sb.length() == 0 ? NONE : sb.toString());
	}
}
