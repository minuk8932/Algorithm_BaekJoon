import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1722 {	
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int cmd = Integer.parseInt(st.nextToken());
		
		if(cmd == 1) {
			int number = Integer.parseInt(st.nextToken());
			
			
		}
		else {
			int[] seq = new int[N];
			
			for(int i = 1; i < N + 1; i++) {
				seq[i] = Integer.parseInt(st.nextToken());
			}
			
			
		}
		
		System.out.println();
	}
}
