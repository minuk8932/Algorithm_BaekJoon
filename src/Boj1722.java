import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1722 {	
	private static final String SPACE = " ";
	
	private static int[] s = null;
	private static int[] arr = null;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int command = Integer.parseInt(st.nextToken());
		int perm = 0;
		arr = new int[N];
		
		s = new int[N];
		for(int i = 0; i < N; i++) s[i] = (i + 1);
		
		if(command == 1) {
			perm = Integer.parseInt(st.nextToken());			
			int cnt = 1;
			
			while(cnt != perm) {
				cnt++;
				// per
			}
			
			for(int i = 0; i < N; i++) sb.append(s[i]).append(SPACE);
		}
		else {
			for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
			int cnt = 1;
			
			while(!isSame(N)) {
				cnt++;
				// per
			}
			
			sb.append(cnt);
		}
		
		System.out.println(sb.toString());
	}
	private static void init(int N, int[] a) {
		for(int i = 0; i < N; i++) {
			s[i] = a[i];
		}
	}
	
	private static boolean isSame(int N) {
		for(int i = 0; i < N; i++) {
			if(arr[i] != s[i]) return false;
		}
		
		return true;
	}
	
	private static void cntPermutation(int N) {		
		
	}
	
	private static void nextPermutation() {
		
	}
}
