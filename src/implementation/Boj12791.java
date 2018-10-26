package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 12791번: starman
 *
 *	@see https://www.acmicpc.net/problem/12791/
 *
 */
public class Boj12791 {
	private static final String NEW_LINE = "\n";
	private static final String EMPTY = "";
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] RDBMS = new String[3000];
		Arrays.fill(RDBMS, EMPTY);
		
		int Q = Integer.parseInt(br.readLine());
		
		RDBMS[1967] = "DavidBowie";
		RDBMS[1969] = "SpaceOddity";
		RDBMS[1970] = "TheManWhoSoldTheWorld";
		RDBMS[1971] = "HunkyDory";
		RDBMS[1972] = "TheRiseAndFallOfZiggyStardustAndTheSpidersFromMars";
		RDBMS[1973] = "AladdinSane";
		RDBMS[1973] += " PinUps";
		RDBMS[1974] = "DiamondDogs";
		RDBMS[1975] = "YoungAmericans";
		RDBMS[1976] = "StationToStation";
		RDBMS[1977] = "Low";
		RDBMS[1977] += " Heroes";
		RDBMS[1979] = "Lodger";
		RDBMS[1980] = "ScaryMonstersAndSuperCreeps";
		RDBMS[1983] = "LetsDance";
		RDBMS[1984] = "Tonight";
		RDBMS[1987] = "NeverLetMeDown";
		RDBMS[1993] = "BlackTieWhiteNoise";
		RDBMS[1995] = "1.Outside";
		RDBMS[1997] = "Earthling";
		RDBMS[1999] = "Hours";
		RDBMS[2002] = "Heathen";
		RDBMS[2003] = "Reality";
		RDBMS[2013] = "TheNextDay";
		RDBMS[2016] = "BlackStar";
		
		StringBuilder res = new StringBuilder();
		while(Q-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			StringBuilder sb = new StringBuilder();
			int from = Integer.parseInt(st.nextToken());			// 기간 입력
			int to = Integer.parseInt(st.nextToken());
			
			int counts = 0;
			
			for(int i = from; i < to + 1; i++) {
				if(RDBMS[i].equals(EMPTY)) continue;
				
				if(RDBMS[i].contains(SPACE)) {			// 같은 년도에 나온 앨범이 존재 할 경우 공백이 있으므로 체크
					st = new StringTokenizer(RDBMS[i]);
					
					while(st.hasMoreTokens()) {			// 해당 년도의 앨범 이름을 예제에 나와있는 순서대로 잘라온 다음, 버퍼에 저장
						counts++;
						sb.append(i).append(SPACE).append(st.nextToken()).append(NEW_LINE);
					}
				}
				else {			// 해당 년도에 하나의 앨범만 존재하는 경우
					counts++;
					sb.append(i).append(SPACE).append(RDBMS[i]).append(NEW_LINE);
				}
			}
			
			// 앨범의 갯수를 다시 다른 버퍼에 저장하고, 년도와 앨범이름이 적힌 버퍼의 값을 해당 버퍼에 저장
			res.append(counts).append(NEW_LINE).append(sb.toString());
		}
		
		System.out.println(res);		// 결과 값 한번에 출력
	}
}
