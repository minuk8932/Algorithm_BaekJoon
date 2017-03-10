package realization;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj10984 {
	public static final String SPACE = " ";
	public static final String NEW_LINE = "\n";
	public static final Double TEN = 10.0;
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T > 0){
			int sumC = 0;
			double GPA = 0, sumG = 0, tmp = 0;
			
			int N = Integer.parseInt(br.readLine());
			for(int i = 0; i < N; i++){
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int C = Integer.parseInt(st.nextToken());
				double G = Double.parseDouble(st.nextToken());
				sumC += C;
				sumG += G;
				tmp += C * G;
			}
			
			GPA = Math.round((tmp / sumC) * TEN) / TEN;
			
			sb.append(sumC).append(SPACE).append(GPA).append(NEW_LINE);
			
			T--;
		}
		System.out.println(sb.toString());
	}

}
