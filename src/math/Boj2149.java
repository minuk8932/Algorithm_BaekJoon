package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * 	@author exponential-e
 *	백준 2149번: 암호 해독
 *
 *	@see https://www.acmicpc.net/problem/2149/
 *
 */
public class Boj2149 {
	private static Pair[] key;
	
	private static class Pair implements Comparable<Pair>{
		int idx;
		char word;
		
		public Pair(int idx, char word) {
			this.idx = idx;
			this.word = word;
		}

		@Override
		public int compareTo(Pair p) {
			if(this.word < p.word) {
				return -1;
			}
			else if(this.word > p.word) {
				return 1;
			}
			else {
				if(this.idx < p.idx) return -1;
				else if(this.idx > p.idx) return 1;
				else return 0;
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = {br.readLine(), br.readLine()};

		getPlainText(input);
	}
	
	private static int[] init(String input) {
		key = new Pair[input.length()];
		
		for(int i = 0; i < key.length; i++) {	// 순서 유지
			key[i] = new Pair(i, input.charAt(i));
		}
		
		Arrays.sort(key);
		
		int[] seq = new int[key.length];
		for(int i = 0; i < seq.length; i++) {
			seq[key[i].idx] = i;				// i번째 key의 원래 위치번 배열에 현재 위치 저장
		}
		
		return seq;
	}
	
	private static void getPlainText(String[] in) {
		int[] index = init(in[0]);
		
		StringBuilder sb = new StringBuilder();
		int N = in[0].length(), M = in[1].length() / N;		// N: key 길이
		
		char[][] plain = new char[M][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				plain[j][i] = in[1].charAt(i * M + j);		// 평문을 위에서 아래로 입력
			}
		}
		
		for(int i = 0; i < M; i++) {						// 원 순서대로, key의 위치 정보를 기반으로 저장
			for(int j = 0; j < N; j++) {
				sb.append(plain[i][index[j]]);
			}
		}

		System.out.println(sb.toString());
	}
}
