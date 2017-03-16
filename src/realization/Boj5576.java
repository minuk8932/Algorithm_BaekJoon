package realization;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj5576 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] W = new int[10];
		int[] K = new int[10];
		int sumW = 0, sumK = 0;
		
		for(int i = 0; i < W.length; i++){
			W[i] = Integer.parseInt(br.readLine());
		}
		for(int i = 0; i < K.length; i++){
			K[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(W);
		Arrays.sort(K);
		
		for(int i = W.length-1; i >= W.length-3; i--){
			sumW += W[i];
			sumK += K[i];
		}
		
		System.out.println(sumW+" "+sumK);
	}

}
