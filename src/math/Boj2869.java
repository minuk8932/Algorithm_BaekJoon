package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2869 {
	public static final String NEW_LINE = "\n";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());
//		int sum = 0, chk = 0;
		
		double days = (double)(V-A)/(A-B);
		int day = (int) Math.ceil(days);
		
		System.out.println(day+1);
//		
//LOOP:		while(true){
//			sum += A;
//			chk++;
//			if(sum >= V){
//				sb.append(chk).append(NEW_LINE);
//				break LOOP;
//			}
//			sum -= B;
//		}
//		
//		System.out.println(sb.toString());
	}

}
