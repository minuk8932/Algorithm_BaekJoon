package divide_and_conquer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 16719번: ZOAC
 *
 * @see https://www.acmicpc.net/problem/16719/
 *
 */
public class Boj16719 {
	private static final String NEW_LINE = "\n";
	private static StringBuilder sb = new StringBuilder();
	private static boolean[] visit;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] input = br.readLine().toCharArray();
		visit = new boolean[input.length];

		recursion(input, 0, input.length - 1);
		System.out.println(sb.toString());
	}
	
	private static void recursion(char[] arr, int start, int end) {
		int min = 501, target = -1;

		for (int i = start; i < end + 1; i++) {				// find minimum in range
			if (visit[i] || min <= arr[i]) continue;
			min = arr[i];
			target = i;
		}

		if (min == 501) return;
		visit[target] = true;

		for (int i = 0; i < arr.length; i++) {
			if (!visit[i]) continue;
			sb.append(arr[i]);
		}
		sb.append(NEW_LINE);

		recursion(arr, target + 1, end);				// right has priority
		recursion(arr, start, target - 1);
	}
}
