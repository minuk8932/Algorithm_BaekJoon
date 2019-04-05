package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15449번: Art
 *
 *	@see https://www.acmicpc.net/problem/15449/
 *
 */
public class Boj15449 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] line = new int[5];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 5; i++) {
			line[i] = Integer.parseInt(st.nextToken());
 		}
		
		System.out.println(getTriangles(line));
	}
	
	private static int getTriangles(int[] arr) {
		Arrays.sort(arr);
		int count = 0;
		
		for(int l1 = 0; l1 < arr.length; l1++) {
			for(int l2 = l1 + 1; l2 < arr.length; l2++) {
				for(int l3 = l2 + 1; l3 < arr.length; l3++) {
					if(arr[l1] + arr[l2] > arr[l3]) count++;		// 삼각형 정의
				}
			}
		}
		
		return count;
	}
}
