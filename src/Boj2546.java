import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2546 {
	private static final int INF = 100_001;
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		while(T-- > 0) {
			String waste = br.readLine();
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			double N = Double.parseDouble(st.nextToken());
			double M = Double.parseDouble(st.nextToken());
			
			double avgC = 0, avgE = 0;
			double[] cLang = new double[INF];
			double[] econo = new double[INF];
			
			boolean[] isTrueC = new boolean[INF];
			boolean[] isTrueE = new boolean[INF];
			
			st = new StringTokenizer(br.readLine());
			
			for(int i = 0; i < N; i++) {
				double idx = Double.parseDouble(st.nextToken());
				cLang[(int) idx]++; 
				avgC += (double) idx;
			}
			avgC /= N;

			st = new StringTokenizer(br.readLine());
			
			for(int i = 0; i < M; i++) {
				double idx = Double.parseDouble(st.nextToken());
				econo[(int) idx]++; 
				avgE += (double) idx;
			}
			
			double tmp = avgE / M;
			
			for(int i = 1; i < INF; i++) {
				if(cLang[i] != 0) {
					if(i < avgC) {
						isTrueC[i] = true;
					}
				}
			}
			
			for(int i = 1; i < INF; i++) {
				if(econo[i] != 0) {
					isTrueE[i] = true;
				}
			}

			for(int i = 1; i < INF; i++) {
				if(isTrueC[i]) {
					double tmpAvg = (avgE + i) / (M + 1);
					
					if(tmp >= tmpAvg) {
						isTrueC[i] = false;
					}
				}
			}
			
			int res = 0;
			
			for(int i = 1; i < INF; i++) {
				if(isTrueC[i] && isTrueE[i]) {
					if(econo[i] > cLang[i]) {
						res += cLang[i];
					}
					else {
						res += econo[i];
					}
					System.out.println(i);
				}
			}
			
			sb.append(res).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
}
