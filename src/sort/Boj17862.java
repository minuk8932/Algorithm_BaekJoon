package sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 17862번: 나의 학점은?
 *
 *	@see https://www.acmicpc.net/problem/17862/
 *
 */
public class Boj17862 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] score = new int[50];
		for(int i = 0; i < score.length; i++) {
			score[i] = Integer.parseInt(st.nextToken());
		}
		
		int target = Integer.parseInt(br.readLine());
		System.out.println(result(score, target));
	}
	
	private static String result(int[] arr, int t) {
		int count = 1;
		Arrays.sort(arr);
		
		for(int i = arr.length - 1; i >= 0; i--) {
			if(arr[i] == t) break;
			count++;
		}
		
		if(count >= 1 && count <= 5) return "A+";
		else if(count >= 6 && count <= 15) return "A0";
		else if(count >= 16 && count <= 30) return "B+";
		else if(count >= 31 && count <= 35) return "B0";
		else if(count >= 36 && count <= 45) return "C+";
		else if(count >= 46 && count <= 48) return "C0";
		else return "F";
	}
}
