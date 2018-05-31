package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15787번: 기차가 어둠을 헤치고 은하수를
 *
 *	@see https://www.acmicpc.net/problem/15787/
 *
 */
public class Boj15787 {
	private static final int INF = 100_001;
	private static final int MAX_SEATS = 22;
	
	private static boolean[][] train = null;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		train = new boolean[INF][MAX_SEATS];
		for(int i = 0; i < M; i++) {			
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());		// 명령어
			int trainNum = Integer.parseInt(st.nextToken());	// 기차 번호
			int seat = 0;										// 좌석 번호
			
			try {											// 명령어에 따른 좌석번호 입력 여부
				seat = Integer.parseInt(st.nextToken());
			}
			catch(Exception e) {
				seat = 0;
			}
			
			switch (command) {
			case 1:							// 1: 탑승
				getOn(trainNum ,seat);
				break;
				
			case 2:							// 2: 하차
				getOff(trainNum, seat);
				break;
				
			case 3:							// 3: 좌석마다 한자리 뒤로 밀어줌
				push(trainNum);
				break;
				
			case 4:							// 4: 좌석마다 한자리 앞으로 밀어줌
				pull(trainNum);
				break;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		String line = " ";
		
		for(int i = 1; i < N + 1; i++) {
			line = " ";
			for(int j = 1; j < MAX_SEATS - 1; j++) {
				line += train[i][j];				// line 변수에 앞에 공백을 넣고 기찻값을 한줄로 저장
			}
			
			if(!sb.toString().contains(line)) sb.append(line);	// line에 담은 값이 버퍼에 존재하지 않으면, 담아줌
		}
		
		st = new StringTokenizer(sb.toString());	// 버퍼의 값을 공백단위로 쪼갠 후
		System.out.println(st.countTokens());		// 토큰의 갯수를 출력
	}
	
	private static void getOn(int t, int s) {		// 탑승
		if(!train[t][s]) train[t][s] = true;
	}
	
	private static void getOff(int t, int s) {		// 하차
		if(train[t][s]) train[t][s] = false;
	}
	
	private static void push(int t) {				// 뒤로 한칸씩 이동
		for(int i = MAX_SEATS - 1; i > 0; i--) {
			train[t][i] = train[t][i - 1]; 
		}
		
		train[t][MAX_SEATS - 1] = false;
	}
	
	private static void pull(int t) {				// 앞으로 한칸씩 이동
		for(int i = 1; i < MAX_SEATS; i++) {
			train[t][i - 1] = train[t][i]; 
		}
		
		train[t][0] = false;
	}
}
