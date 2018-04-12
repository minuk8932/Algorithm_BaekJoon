package implementation;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15233번 : 최종점수
 *
 *	@see https://www.acmicpc.net/problem/15233/
 *
 */
public class Boj15233 {
	private static final String SPACE = " ";
	private static final String WINNER_A = "A";
	private static final String WINNER_B = "B";
	private static final String DRAW = "TIE";
	
	private static BufferedReader br = null;
	private static StringTokenizer st = null;
	
	public static void main(String[] args) throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine(), SPACE);
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int G = Integer.parseInt(st.nextToken());
		
		String[] teamA = new String[A];
		String[] teamB = new String[B];
		String[] goals = new String[G];
		
		input(teamA);									// 팀별 선수와 골넣은 선수의 항목을 입력받는 메소드
		input(teamB);
		input(goals);
		
		int pointA = getPoints(teamA, goals);	// 누가 골을 넣었는지 확인하고 해당 팀의 값을 반환받아 각 변수에 저장
		int pointB = getPoints(teamB, goals);
		
		System.out.println(pointA == pointB ? DRAW : (pointA > pointB ? WINNER_A : WINNER_B));	// 점수가 같다면 TIE를 아니면 조건에 따라 A, B를 출력
	}
	
	/**
	 * 
	 * @param array
	 * @throws IOException
	 *	값 입력 메소드
	 *
	 */
	public static void input(String[] array) throws IOException{
		st = new StringTokenizer(br.readLine(), SPACE);
		
		for(int i = 0; i < array.length; i++){
			array[i] = st.nextToken();
		}
	}
	
	/**
	 * 
	 * @param team
	 * @param goals
	 * @return 팀별 점수 반환
	 * 골 넣은 사람을 비교해 값을 반환해줌
	 * 
	 */
	public static int getPoints(String[] team, String[] goals){
		int points = 0;
		
		for(int i = 0; i < goals.length; i++){
			for(int j = 0; j < team.length; j++){
				if(team[j].equals(goals[i])){
					points++;
					
					break;
				}
			}
		}
		
		return points;
	}
}
