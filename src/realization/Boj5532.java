package realization;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj5532 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int sumM, sumK;
		
		int L = Integer.parseInt(br.readLine());
		int A = Integer.parseInt(br.readLine());
		int B = Integer.parseInt(br.readLine());
		int C = Integer.parseInt(br.readLine());
		int D = Integer.parseInt(br.readLine());
		
		
		if(A%C > 0){
			sumM = A/C + 1;
		} else {
			sumM = A/C;
		}
		
		if(B%D > 0){
			sumK = B/D + 1;
		} else {
			sumK = B/D;
		}
		
		if(sumM > sumK){
			System.out.println(L-sumM);
		} else {
			System.out.println(L-sumK);
		}
	}

}
