import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1680 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int W = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			
			int[] spot = new int[100001];
			int loop = N;
			
			while(loop-- > 0) {
				st = new StringTokenizer(br.readLine());
				spot[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
			}
			
			sb.append(collect(W, spot)).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static long collect(int limit, int[] arr) {
		long result = 0;
		long spare = 0;
		int waste = 0;
		
		for(int i = 0; i < arr.length;) {
			if(arr[i] == 0) {
				i++;
				continue;
			}
			
			waste += arr[i];
			
			if(waste > limit) {
				result += (i * 2);
				waste = 0;
				spare = 0;
			}
			else if(waste == limit) {
				result += (i * 2);
				waste = 0;
				spare = 0;
				i++;
			}
			else {
				spare = i;
				i++;
			}
		}

		return result + (spare * 2);
	}
}
