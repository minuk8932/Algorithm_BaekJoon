package realization;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj5565 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int sum = Integer.parseInt(br.readLine());
		int[] price = new int[9];
		
		if(sum<=10000){
		
			for(int i=0; i<price.length;i++){
				price[i] = Integer.parseInt(br.readLine());
				if(i>0){
					price[i] += price[i-1];
				}
			}
		
			System.out.println(sum-price[8]);
		}
	}
}

