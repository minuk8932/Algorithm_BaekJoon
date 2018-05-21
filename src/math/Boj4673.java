package math;
/**
 * 
 * 	@author minchoba
 *	백준 4673번: 셀프 넘버
 *
 *	@see https://www.acmicpc.net/problem/4673/
 *
 */
public class Boj4673 {
	private static final int INF = 10_001;
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		boolean[] selfNum = new boolean[INF];
		
		for(int i = 1; i < INF; i++){
			int sum = getNums(i);		// 생성자에의해 도출된 값 중

			if(sum < INF){					// 범위 내의 값이면	
				selfNum[sum] = true;		// 그 값에 해당하는 배열 인덱스를 true로 초기화
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i < INF; i++){
			if(!selfNum[i]){									// 배열 내부 값이 거짓인 것만 골라서 버퍼에 담고
				sb.append(i).append(NEW_LINE);
			}
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
	
	/**
	 * 
	 * 	@param value: 생성자
	 * 	@return: 생성자에 의해 만들어진 수
	 */
	public static int getNums(int value) {
        int result = value;
        
        while(value > 0) {				// 생성자를 통한 값 도출
            result += value % 10;		// 생성자 + 생성자의 자리별 숫자 합
            value /= 10;					// 생성자의 자릿수를 하나씩 줄여나감
        }
        
        return result;		// 결과 생성 값 반환
    }
}
