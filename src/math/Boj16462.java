package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj16462 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] score = new int[N];
		for(int i = 0; i < N; i++) {
			String num = "";
			for(char w: br.readLine().toCharArray()) {
				if(w == '0' || w == '6') w = '9';
				num += w;
			}
			
			int modify = Integer.parseInt(num);
			if(modify > 100) modify = 100;
			
			score[i] = modify * 10;
		}
		
		System.out.println(getAvg(score));
	}
	
	private static int getAvg(int[] arr) {
		int sum = 0;
		for(int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		
		sum /= arr.length;
		int round = sum % 10;
		
		sum = round > 4 ? sum + 10 : sum;
		
		return sum / 10;
	}
}
