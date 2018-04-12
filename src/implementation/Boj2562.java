package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj2562 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int max = 0, idx = 0;
		int[] nums = new int[9];
		for(int i = 0; i < 9; i ++){
			nums[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, nums[i]);
			if(max == nums[i]){
				idx = i+1;
			}
		}

		System.out.println(max +"\n"+ idx);
	}

}
