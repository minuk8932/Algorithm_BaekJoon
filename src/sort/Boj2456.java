package sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2456번: 나는 학급 회장이다
 *
 *	@see https://www.acmicpc.net/problem/2456/
 *
 */
public class Boj2456 {
	private static boolean[] include = new boolean[3];
	private static ArrayList<Integer> idx;
	
	private static final String SPACE = " ";
	
	private static class Election{
		int total;
		int[] score = new int[3];
		
		public Election(int total, int[] score) {
			this.total = total;
			
			for(int i = 0; i < score.length; i++) {
				this.score[i] = score[i];
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Election[] candidate = new Election[3];
		for(int i = 0; i < 3; i++) {
			candidate[i] = new Election(0, new int[3]);
		}
		
		while(N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 3; i++) {
				int val = Integer.parseInt(st.nextToken());
				candidate[i].score[val - 1] += 1;			// 받은 점수의 갯수
				candidate[i].total += val;					// 총점
			}
		}
		
		System.out.println(getResult(candidate));
	}
	
	private static String getResult(Election[] e) {
		StringBuilder sb = new StringBuilder();
		int max = 0;
		for(int i = 0; i < 3; i++) {
			if(e[i].total > max) max = e[i].total;
		}
		
		idx = new ArrayList<>();
		for(int i = 0; i < 3; i++) {
			if(max == e[i].total) {
				idx.add(i);
				include[i] = true;
			}
		}
		
		// 총점으로만 회장이 당선 되는가?
		if(idx.size() == 1) return sb.append(idx.get(0) + 1).append(SPACE).append(max).toString();
		
		if(isBest(e, 2)) return sb.append(idx.get(0) + 1).append(SPACE).append(max).toString();		// 총점이 같을 때 3점을 많이 받은 후보
		if(isBest(e, 1)) return sb.append(idx.get(0) + 1).append(SPACE).append(max).toString();		// 총점이 같고 3점의 수도 같을 때 2점을 많이 받은 후보
		return sb.append(0).append(SPACE).append(max).toString();									// 회장 선출 불가
	}
	
	private static boolean isBest(Election[] e, int p) {
		int[] value = {e[0].score[p], e[1].score[p], e[2].score[p]};
		
		int max = 0;
		for(int i = 0; i < 3; i++) {
			if(value[i] > max) max = value[i];
		}
		
		idx = new ArrayList<>();
		for(int i = 0; i < 3; i++) {
			if(max == value[i]) idx.add(i);
		}
		
		if(idx.size() == 1 && include[idx.get(0)]) return true;		// 유일하게 선출 가능하고 그 중 최댓값을 가진 후보인 경우
		return false;
	}
}
