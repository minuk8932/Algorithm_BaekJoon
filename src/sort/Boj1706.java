package sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 1706번: 크로스워드
 *
 *	@see https://www.acmicpc.net/problem/1706/
 *
 */
public class Boj1706 {
	private static ArrayList<String> list = new ArrayList<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		char[][] word = new char[R][C];
		for(int i = 0; i < R; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < C; j++) {
				word[i][j] = line.charAt(j);
			}
		}
		
		System.out.println(sorting(R, C, word));
	}
	
	private static String sorting(int r, int c, char[][] w) {
		search(r, c, w, true);			// row
		search(r, c, w, false);			// col
		
		Collections.sort(list);
		return list.get(0);
	}
	
	private static void search(int r, int c, char[][] w, boolean flag) {
		if(!flag) {
			int tmp = r;
			r = c;
			c = tmp;
		}
		
		for(int i = 0; i < r; i++) {
			StringBuilder sb = new StringBuilder();
					
			for(int j = 0; j < c; j++) {
				char word = flag ? w[i][j]: w[j][i];
				
				if(word == '#') {
					if(sb.length() > 1) list.add(sb.toString());
					sb = new StringBuilder();
							
					continue;
				}
				
				sb.append(word);
			}
			
			if(sb.length() > 1) list.add(sb.toString());
		}
	}
}
