import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj3462 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int D = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		while(D-- > 0){
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			int chk = 0;
			int[] num = new int[2];
			Arrays.fill(num, 1);
			
			if(n == m || m == 1){
				chk = 1;
			}
			else if(m == 0 || n == 0){
				chk = 0;
			}
			else if(m == n -1){
				chk = n * m / 2;
			}
			else if(m == 2){
				chk = 1;
			}
			else {
				for(int i = 1; i < m + 1; i++){
					int tmp = num[1];
					num[1] = num[1] * i + num[0];
					num[0] = tmp;
				}
			}
			
			System.out.println(num[1]);
			
			chk = (chk % 2 == 0 ? 0 : 1);
			
			sb.append(chk).append(NEW_LINE);
		}
		br.close();
		
		System.out.println(sb.toString());
	}
}
