import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1780 {
	private static final int O = 1;
	private static final int M = -1;
	private static final int Z = 0;	
	private static final int FULL_SIZE = 9;
	
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] paper = new int[N][N];
		int paperO = 0, paperM = 0, paperZ = 0;
		
		for(int i = 0; i < N; i++){
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			for(int j = 0; j < N; j ++){
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int loop = N / 3;
		int addJ = 0;
		
		while(loop-- > 0){			
			int addI = 0;
			
			for(int i = addI; i < (addI + 3); i++){
				int cntO = 0, cntM = 0, cntZ = 0;
				for(int j = addJ; j < (addJ + 3); j++){
					if(paper[i][j] == O){
						cntO++;
					}
					else if(paper[i][j] == M){
						cntM++;
					}
					else{
						cntZ++;
					}
				}
				addI += 3;
				
				if(cntO == FULL_SIZE ){
					paperO += 9;
				} 
				else if(cntM == FULL_SIZE){
					paperM += 9;
				}
				else if(cntZ == FULL_SIZE){
					paperZ += 9;
				}
				else{
					paperO += cntO;
					paperM += cntM;
					paperZ += cntZ;
				}
			}
			addJ += 3;
		}	
		StringBuilder sb = new StringBuilder();
		sb.append(paperM).append(NEW_LINE).append(paperZ).append(NEW_LINE).append(paperO);
		System.out.println(sb.toString());
	}
}
