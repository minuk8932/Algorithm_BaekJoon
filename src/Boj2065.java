import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2065 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	private static final String L = "left";
	private static final String R = "right";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		int M = Integer.parseInt(st.nextToken());				// 최대 탑승 가능 인원 
		int t = Integer.parseInt(st.nextToken());					// 정박장을 이동하는데 걸리는 시간
		int N = Integer.parseInt(st.nextToken());					
		
		int[] guest = new int[N];											// 손님마다 정박장 도착에 걸리는 시간
		int timer = 0;
		String[] site = new String[N];									// 손님이 도착하는 위치 : 배를타고 반대로 이동시켜줘야함
		String boatSite = L;												// 현재 보트의 위치
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine(), SPACE);
			guest[i] = Integer.parseInt(st.nextToken());
			site[i] = st.nextToken();
		}
		
		// 우선순위 큐로 먼저 도착하는 놈부터 푸쉬
		for(int i = 0; i < N; i++){
			
		}
		
//		System.out.println(sb.toString());
	}
}
