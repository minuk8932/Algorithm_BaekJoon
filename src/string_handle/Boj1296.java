package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * 	@author minchoba
 *	백준 1296번: 데이트
 *
 *	@see https://www.acmicpc.net/problem/1296/
 *
 */
public class Boj1296 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] origin = br.readLine().toCharArray();
		LoveCounts lcOrigin = new LoveCounts(0, 0, 0, 0);
		
		for(int i = 0; i < origin.length; i++) {		// 오민식의 love 갯수
			if(origin[i] == 'L') lcOrigin.l++;
			else if(origin[i] == 'O') lcOrigin.o++;
			else if(origin[i] == 'V') lcOrigin.v++;
			else if(origin[i] == 'E') lcOrigin.e++;
		}
		
		int N = Integer.parseInt(br.readLine());
		char[][] comp = new char[N][20];
		LoveCounts[] lcComp = new LoveCounts[N];
		
		int[] sumValue = new int[N];
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			int leng = line.length();
			comp[i] = line.toCharArray();
			
			lcComp[i] = new LoveCounts(0, 0, 0, 0);
			
			for(int j = 0; j < leng; j++) {						// 각 여자 별 love 갯수
				if(comp[i][j] == 'L') lcComp[i].l++;
				else if(comp[i][j] == 'O') lcComp[i].o++;
				else if(comp[i][j] == 'V') lcComp[i].v++;
				else if(comp[i][j] == 'E') lcComp[i].e++;
			}
			
			int L = lcOrigin.l + lcComp[i].l;		// 각 여자마다 오민식과의 러브 합
			int O = lcOrigin.o + lcComp[i].o;
			int V = lcOrigin.v + lcComp[i].v;
			int E = lcOrigin.e + lcComp[i].e;
			
			sumValue[i] = process(L, O, V, E);		// 러브 총점 계산 프로세스
		}
		
		int max = 0;
		for(int i = 0; i < N; i++) {					// 최댓값 도출
			if(max < sumValue[i]) max = sumValue[i];
		}
		
		ArrayList<String> res = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			if(max == sumValue[i]) {
				String tmp = "";
				
				for(int j = 0; j < comp[i].length; j++) {	// 최대값과 같은 점수를 갖는 이름을 모두 구해
					tmp += comp[i][j];
				}
				
				res.add(tmp);		// 리스트에 저장
			}
		}
		
		Collections.sort(res);
		System.out.println(res.get(0));		// 정렬 후 가장 앞선 이름을 출력
	}
	
	/**
	 * 사랑 카운트 이너 클래스
	 * @author minchoba
	 *
	 */
	private static class LoveCounts{
		int l;
		int o;
		int v;
		int e;
		
		public LoveCounts(int l, int o, int v, int e) {
			this.l = l;
			this.o = o;
			this.v = v;
			this.e = e;
		}
	}
	
	/**
	 * 사랑 점수 계산 프로세스
	 * 
	 */
	private static int process(int L, int O, int V, int E) {
		return (((L + O)*(L + V)*(L + E)*(O + V)*(O + E)*(V + E)) % 100);
	}
}
