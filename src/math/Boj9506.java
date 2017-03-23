package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Boj9506 {
	public static final String SPACE = " ";
	public static final String NEW_LINE = "\n";
	public static final String PLUS = "+";
	public static final String EQUAL = "=";
	public static final int STOP = -1;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		LOOP: while (true) {
			int N = Integer.parseInt(br.readLine());
			ArrayList<Integer> nums = new ArrayList<>();
			int cnt = 1, sum = 0;

			if (N == STOP) {
				break LOOP;
			}

			for (int i = 1; i <= N / 2; i++) {
				if (N % i == 0) {
					nums.add(i);
					sum += i;
					cnt++;
				}
			}
			
			int arrSize = nums.size();
			
			if(N == sum){
				sb.append(N+" = ");
				for(int i = 0; i < arrSize; i++){
					if(i == (arrSize - 1)){
						sb.append(nums.get(i)).append(NEW_LINE);
					}
					else{
						sb.append(nums.get(i)+" + ");
					}
				}
			}
			else{
				sb.append(N+" is NOT perfect.").append(NEW_LINE);
			}
			
		}
		System.out.println(sb.toString());
	}

}
