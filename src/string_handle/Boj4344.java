package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 4344번: 평균은 넘겠지
 *
 *	@see https://www.acmicpc.net/problem/4344/
 *
 */
public class Boj4344 {
	private static final String PERCENT = "%";
	private static final String NEW_LINE = "\n";
	private static final String DOT = ".";
	private static final String ZERO = "0";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int C = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(C-- > 0){
			StringTokenizer st = new StringTokenizer(br.readLine());
			double N = Double.parseDouble(st.nextToken());				// 학생 수
			double[] score = new double[(int) N];						// 점수 저장 배열
			double scoreSum = 0;											// 점수의 합
			
			for(int i = 0; i < N; i++){
				score[i] = Double.parseDouble(st.nextToken());
				scoreSum += score[i];
			}
			
			double avg = scoreSum / N;			// 점수의 평균
			double cnt = 0.0;					// 평균을 넘는 인원
			
			for(int i = 0; i < N; i++){
				if(score[i] > avg){
					cnt += 1000.0;				// 소수점 3째 반올림이므로 1000씩 더한 후
				}
			}
			
			double per = cnt * 1000.0 / N;											// 1000배 하여 넘는 인원의 1000배 값을 구함
			String avgStr = String.valueOf(Math.round(per / 10.0));	// 소수점 1번째 자리 반올림을 한 값을 문자열로 받아옴
			
			
			if(per == 0){		// 평균을 넘은 인원이 없는 경우
				sb.append(ZERO).append(DOT).append("000").append(PERCENT).append(NEW_LINE);
			}
			else{				
				int leng = avgStr.length();
				
				if(leng == 5){									// 평균을 넘는 인원이 10% 이상인 경우
					for(int i = 0; i < leng + 1; i++){
						if(i == 2){
							sb.append(DOT);					
						}
						else if(i < 2){
							sb.append(avgStr.charAt(i));
						}
						else{
							sb.append(avgStr.charAt(i - 1));
						}
					}
				}
				else if(leng == 4){							// 평균을 넘는 인원이 1~10% 사이의 경우
					for(int i = 0; i < leng + 1; i++){
						if(i == 1){
							sb.append(DOT);					
						}
						else if(i < 1){
							sb.append(avgStr.charAt(i));
						}
						else{
							sb.append(avgStr.charAt(i - 1));
						}
					}
				}
				else if(leng <= 3){							// 평균을 넘는 인원이 0~1% 사이의 경우
					for(int i = 0; i < leng + 1; i++){
						if(i == 0){
							sb.append(ZERO).append(DOT);					
						}
						else{
							sb.append(avgStr.charAt(i - 1));
						}
					}
				}
				
				sb.append(PERCENT).append(NEW_LINE);		// 위의 각 조건에 따라 값들을 버퍼에 담은 후 뒤에 %를 붙이고
			}
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}
