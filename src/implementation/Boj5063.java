package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj5063 {
	public static final int LIMIT = 1000000;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N;
		int r, e, c;
		int pro;
		

		N = Integer.parseInt(br.readLine());

		
		
		for (int i = 0; i < N; i++) {

			String[] cost = br.readLine().split(" ");
			
			r = Integer.parseInt(cost[0]);
			e = Integer.parseInt(cost[1]);
			c = Integer.parseInt(cost[2]);
			
			if (-LIMIT <= r && e <= LIMIT && c <= LIMIT && 0 <= c) {
				
				pro = r + c;
				
				if(e > pro){
					System.out.println("advertise");
				}else if(e < pro){
					System.out.println("do not advertise");
				}else{
					System.out.println("does not matter");
				}
				
			}
			
		}
		br.close();
	}

}
