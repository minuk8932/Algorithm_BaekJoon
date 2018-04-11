package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2755번 : 이번학기 평점은 몇점?
 *
 *	@see https://www.acmicpc.net/problem/2755/
 *
 */
public class Boj2755 {
	private static final String SPACE = " ";
	private static final String ZERO = "0";
	
	// 학점 상수 배열 정수형 * 100 
	private static final int[] GRADE = {4300, 4000, 3700, 3300, 3000, 2700, 2300, 2000, 1700, 1300, 1000, 700, 0};
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int sum = 0, gradeSum = 0;
		
		while(N-- > 0){
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			String sub = st.nextToken();										// 과목명
			int subGrade = Integer.parseInt(st.nextToken());		// 과목의 학점
			String tmp = st.nextToken();										// 과목 등급
			
			int point = 0;
			
			switch(tmp){					// 등급 별 점수 산출
			case "A+":
				point = GRADE[0];
				break;
				
			case "A0":
				point = GRADE[1];
				break;
				
			case "A-":
				point = GRADE[2];
				break;
				
			case "B+":
				point = GRADE[3];
				break;
				
			case "B0":
				point = GRADE[4];
				break;
				
			case "B-":
				point = GRADE[5];
				break;
				
			case "C+":
				point = GRADE[6];
				break;
				
			case "C0":
				point = GRADE[7];
				break;
				
			case "C-":
				point = GRADE[8];
				break;
				
			case "D+":
				point = GRADE[9];
				break;
				
			case "D0":
				point = GRADE[10];
				break;
				
			case "D-":
				point = GRADE[11];
				break;
				
			case "F":
				point = GRADE[12];
				break;
			}
			
			gradeSum += subGrade;			// 총 학점 구하기
			sum += (point * subGrade);	// 총점 구하기
		}
		
		int res = sum / gradeSum;										// 총 평점
		double resPoint = Math.round((double) res / 10);	// 총 평점 반올림 후
		resPoint = resPoint / 100;										// 반올림 값에서 100으로 나누어 실제 평점을 뽑아냄
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(resPoint);
		
		System.out.println(sb.length() == 3 ? sb.toString() + ZERO : sb.toString());		// 만약 총 평점이 x.0인 경우 뒤에 0을 하나 더 붙여주고 출력
	}
}
