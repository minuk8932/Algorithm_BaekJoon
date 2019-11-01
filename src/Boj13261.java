import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj13261 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int L = Integer.parseInt(st.nextToken());
		int G = Integer.parseInt(st.nextToken());
		
		int[] C = new int[L];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < L; i++) {
			C[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(C);
		System.out.println(getHazard(L, G, C));
	}
	
	private static long getHazard(int l, int g, int[] c) {
		int sets = 1;
		int comp = c[0];
		
		for(int i = 1; i < l; i++) {
			if(comp == c[i]) continue;
			sets++;
			comp = c[i];
		}
		
		long result = 0;		
		
		return result;
	}
}
