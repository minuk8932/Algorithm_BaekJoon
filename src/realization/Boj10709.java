package realization;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj10709 {
	public static final String NEW_LINE = "\n";
	public static final String SPACE = " ";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());

		char[] chg = new char[W];
		

		for (int i = 0; i < H; i++) {
			String C = br.readLine();
			
			int tmp = 0;
			if (C.contains("c")) {
				chg = C.toCharArray();
				for (int j = 0; j < W; j++) {
					
					if (C.indexOf('c') == C.lastIndexOf('c')) {
						if (C.indexOf('c') > j) {							
							sb.append(-1).append(SPACE);
							
						} else {
							sb.append(tmp).append(SPACE);
							tmp++;							
						}
						
					} else {
						if(C.indexOf('c') > j){
							sb.append(-1).append(SPACE);
							
						} else if(C.indexOf('c') <= j && C.lastIndexOf('c') > j){							
							if(chg[j] == 'c'){
								tmp = 0;
								sb.append(tmp).append(SPACE);
								tmp++;
							}
							else if(chg[j]=='.'){
								sb.append(tmp).append(SPACE);
								tmp++;
							}
						} else {
							sb.append(tmp).append(SPACE);
							tmp++;
						}
						
					}
				}
			} else {
				for(int j = 0; j < W; j++){
					sb.append(-1).append(SPACE);
				}
			}
			sb.append(NEW_LINE);
		}
		System.out.println(sb.toString());

	}

}
