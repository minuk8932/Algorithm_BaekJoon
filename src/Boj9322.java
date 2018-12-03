import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj9322 {
	private static final char SPACE = ' ';
	private static final char NEW_LINE = '\n';
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int n = Integer.parseInt(br.readLine());
			String[] publicKey1  = new String[n];
			String[] publicKey2  = new String[n];
			String[] pw = new String[n];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				publicKey1[i] = st.nextToken();
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				publicKey2[i] = st.nextToken();
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				pw[i] = st.nextToken();
			}
			
			sb.append(decoding(publicKey1, publicKey2, pw));
		}
	}
	
	private static String decoding(String[] arr1, String[] arr2, String[] ans) {
		int[] idx = new int[arr1.length];
		Arrays.fill(idx, -1);
		
		for(int i = 0; i < arr1.length; i++) {
			if(idx[i] != -1) continue;
			
			for(int j = 0; j < arr2.length; j++) {
				if(arr1[i] == arr2[j]) {
					idx[i] = j;
					break;
				}
			}
		}
		
		return null;
	}
}
