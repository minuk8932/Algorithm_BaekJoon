import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj5525 {
	private static final char[] IO = {'I', 'O'};
	private static char[] PN;
	private static int leng;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		makePn(N);
		char[] S = br.readLine().toCharArray();
		
		System.out.println(getCount(S));
	}
	
	private static void makePn(int n) {
		PN = new char[n * 2 + 1];
		
		for(int i = 0; i < PN.length - 1; i++) {
			PN[i] = IO[i % 2];
		}
		
		PN[PN.length - 1] = IO[0];
	}
	
	private static int getCount(char[] arr) {
		boolean flag = false;
		int count = 0, tIdx = 0;
		
		while(tIdx < arr.length - 1) {
			if(arr[tIdx] == IO[0]) {
				int tmp = 0;
				
				for(int target = 0; target < PN.length; target++) {
					if(arr[tIdx] != PN[target]) {
						flag = false;
						break;
					}
					
					tmp++;
					tIdx++;
				}
				
				if(tmp == IO.length) {
					count++;
					flag = true;
				}
				
				if(!flag) tIdx++;
			}
		}
		
		return count;
	}
	
	private static int isIOI(char[] arr, int idx) {
		for(int target = 0; target < PN.length; target++) {
			if(arr[idx] != PN[target]) break;
			
			leng++;
			idx++;
		}
		
		return 0;
	}
}
