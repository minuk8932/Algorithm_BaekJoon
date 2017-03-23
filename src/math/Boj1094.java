package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj1094 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int X = Integer.parseInt(br.readLine());
		int cnt = 0;
		double log = Math.log(X) / Math.log(2);
		
		if (log * log == X || X == 1) {
			cnt = 1;
			sb.append(cnt);
		}
		
		else {
RESULT:		for (;;) {
				int res = 1, Max = 0;
				LOOP: while (true) {
					res *= 2;						
					
					if (res <= X) {
						Max = Math.max(Max, res);
					} 
					
					else {
						break LOOP;
					}
				}
				cnt++;
				X -= Max;
				if(X == 1){
					cnt++;
					break RESULT;
				}
				else if(X==0){
					break RESULT;
				}
			}
			sb.append(cnt);
		}
		System.out.println(sb.toString());
	}

}
