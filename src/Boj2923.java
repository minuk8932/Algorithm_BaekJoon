import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2923 {
	private static final String NEW_LINE = "\n";
	
	private static class Number {
		int A;
		int B;
		
		public Number(int A, int B) {
			this.A = A;
			this.B = B;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Number[] num = new Number[N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			num[i] = new Number(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		System.out.println(getList(N, num));
	}
	
	private static String getList(int n, Number[] arr) {
		StringBuilder sb = new StringBuilder();
		
		int max = arr[0].A + arr[0].B;
		sb.append(max).append(NEW_LINE);
		
		
		
		return sb.toString();
	}
}
