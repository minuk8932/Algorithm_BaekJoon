package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 9557번: Arabic and English
 */
public class Boj9557 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	private static final char ARAB_LANGUAGE = '#';
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			
			String[] arab = new String[N];
			int idx = 0;
			boolean isVisited = false;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				arab[i] = st.nextToken();
				
				if(arab[i].charAt(0) != ARAB_LANGUAGE) {	// 영단어가 나오는 인덱스를 구함
					idx = i;
					isVisited = true;
				}
			}
			
			if(idx == 0 && !isVisited) {				// 영단어가 아예 나오지 않은 경우	
				for(int i = 0; i < N; i++) {
					sb.append(arab[i]).append(SPACE);
				}
			}
			else {										// 영단어가 나온 경우
				for(int i = idx + 1; i < N; i++) {		// 영단어 다음부터 끝까지 버퍼에 담고
					sb.append(arab[i]).append(SPACE);
				}
				
				sb.append(arab[idx]).append(SPACE);		// 영단어만 담은 후
				
				for(int i = 0; i < idx; i++) {			// 처음부터 영단어 전까지 단어를 버퍼에 담아줌
					sb.append(arab[i]).append(SPACE);
				}
			}
			
			sb.append(NEW_LINE);			// 개행 삽입
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}
