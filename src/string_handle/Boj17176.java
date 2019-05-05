package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj17176 {
	private static final char SPACE = ' ';
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] seq = new int[53];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			seq[Integer.parseInt(st.nextToken())]++;
		}
		
		char[] words = br.readLine().toCharArray();
		
		System.out.println(decode(N, seq, words));
	}
	
	private static String decode(int n, int[] encrypt, char[] plain) {
		int[] result = new int[53];
		
		for(char c: plain) {
			if(c == SPACE) result[0]++;
			else if(c >= 'A' && c <= 'Z') result[(c - 'A') + 1]++;
			else result[(c - 'a') + 27]++;
		}
		
		for(int i = 0; i < result.length; i++) {
			if(result[i] != encrypt[i]) return "n";
		}
		
		return "y";
	}
}
