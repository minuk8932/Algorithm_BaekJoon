package realization;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11723번 : 집합
 *
 *	@see https://www.acmicpc.net/problem/11723/
 *
 */
public class Boj11723 {
	private static final String ADD = "add";
	private static final String REMOVE = "remove";
	private static final String CHECK = "check";
	private static final String TOGGLE = "toggle";
	private static final String ALL = "all";
	private static final String EMPTY = "empty";
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	private static boolean[] set = null;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int M = Integer.parseInt(br.readLine());		
		set = new boolean[21];							// 집합 배열 선언
		
		StringBuilder sb = new StringBuilder();
		
		while(M-- > 0){
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			String command = st.nextToken();				// 명령어를 입력받고
			
			if(command.equals(ALL)){							// 명령어가 all인 경우 해당 메소드 실행 후 다음 입력으로 넘어감
				all();
				continue;
			}
			
			if(command.equals(EMPTY)){						// 명령어가 empty인 경우 해당 메소드 실행 후 다음 입력으로 넘어감
				empty();
				continue;
			}
			
			int idx = Integer.parseInt(st.nextToken());	// 그 외의 경우 숫자를 받고
			
			switch(command){										// 각 명령어 별 함수 실행 및 조건에 따른 값을 버퍼에 담아줌
			case ADD :
				add(idx);
				break;
				
			case REMOVE :
				remove(idx);
				break;
				
			case CHECK :
				sb.append(check(idx)).append(NEW_LINE);
				break;
				
			case TOGGLE : 
				toggle(idx);
				break;
			}
		}
		
		System.out.println(sb.toString());						// 결과값 한번에 출력
	}
	private static int check(int idx){		// 해당 숫자가 집합에 존재하는 경우 1, 아니면 0 출력
		if(set[idx]){
			return 1;
		}
		else{
			return 0;
		}
	}
	
	private static void add(int idx){		// 해당 숫자가 집합에 존재하지 않는 경우 그 숫자를 추가함
		if(!set[idx]){
			set[idx] = true;
		}
	}
	
	private static void remove(int idx){	// 해당 숫자가 집합에 존재하는 경우 그 숫자를 제거함
		if(set[idx]){
			set[idx] = false;
		}
	}
	
	private static void toggle(int idx){		// 해당 숫자가 집합에 존재하면 제거하고, 존재하지 않으면 추가함
		if(set[idx]){
			set[idx] = false;
		}
		else{
			set[idx] = true;
		}
	}
	
	private static void all(){						// 집합 내의 포함 할 수 있는 모든 숫자를 존재함으로 바꿔줌
		Arrays.fill(set, true);
	}
	
	private static void empty(){				// 집합 내부를 비워줌
		Arrays.fill(set, false);
	}
}
