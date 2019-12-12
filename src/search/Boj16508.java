package search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 16508번: 전공책
 *
 *	@see https://www.acmicpc.net/problem/16508/
 *
 */
public class Boj16508 {
	private static class Book {
		public int C;
		public String W;
		public int[] letter;

		public Book(int C, String W) {
			this.C = C;
			this.W = W;

			this.letter = new int[26];
			for (int i = 0; i < W.length(); i++)
				this.letter[W.charAt(i) - 'A']++;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] words = new int[26];
		String T = br.readLine();
		
		for (int i = 0; i < T.length(); i++) {
			words[T.charAt(i) - 'A']++;
		}

		int N = Integer.parseInt(br.readLine());
		Book[] book = new Book[N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			book[i] = new Book(Integer.parseInt(st.nextToken()), st.nextToken());
		}

		System.out.print(bookSum(N, words, book));
	}
	
	private static int bookSum(int n, int[] input, Book[] arr) {
		int res = Integer.MAX_VALUE;
		int len = 1 << n;
		
		for (int i = 1; i < len; i++) {
			int[] count = new int[26];
			int cost = 0;
			int idx = -1;

			for (int c = 1; c <= i; c *= 2) {
				idx++;
				if ((i & c) != c) continue;					// check by bit mask

				cost += arr[idx].C;
				for (int j = 0; j < 26; j++) {
					count[j] += arr[idx].letter[j];
				}
			}

			boolean flag = true;
			for (int j = 0; j < 26; j++) {
				if (count[j] < input[j]) {
					flag = false;
					break;
				}
			}

			if (flag) res = Math.min(res, cost);
		}
		
		return res == Integer.MAX_VALUE ? -1 : res;
	}
}
