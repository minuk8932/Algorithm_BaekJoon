package search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * 
 * 	@author minchoba
 *	백준 5002번: 도어맨
 *
 *	@see https://www.acmicpc.net/problem/5002/
 *
 */
public class Boj5002 {
	private static int limit;
	private static final char MALE = 'M';
	private static final char FEMALE = 'W';
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int X = Integer.parseInt(br.readLine());
		
		LinkedList<Character> line = new LinkedList<>();
		for(char c: br.readLine().toCharArray()) {
			line.add(c);
		}
		
		System.out.println(search(X, -X, line));	// 입장 인원
	}
	
	private static int search(int upperBound, int lowerBound, LinkedList<Character> line) {
		int count = 0;
		int loop = line.size();
		limit = 0;
		
		while(loop-- > 0) {
			if(preGuest(line.get(0), MALE, upperBound, 1)) {				// 범위내에 첫번째 남자 손님을 들이는 경우
				limit++;
				count++;
				
				line.removeFirst();
			}
			else if(postGuest(line, MALE, upperBound, 1, line.size())) {	// 범위내에 다음 남자 손님을 들이는 경우
				limit++;
				count++;
				
				line.remove(1);
			}
			else if(preGuest(line.get(0), FEMALE, lowerBound, -1)) {		// 범위내에 첫번째 여자 손님을 들이는 경우
				limit--;
				count++;
				
				line.removeFirst();
			}
			else if(postGuest(line, FEMALE, lowerBound, -1, line.size())) {	// 범위내에 다음 여자 손님을 들이는 경우
				limit--;
				count++;
				
				line.remove(1);
			}
			else {				// 입장 종료
				break;
			}
		}
		
		return count;
	}
	
	private static boolean preGuest(char pre, char gender, int bound, int diff) {
		return (pre == gender && (limit * diff) < (bound * diff)) ? true : false;
	}
	
	private static boolean postGuest(LinkedList<Character> person, char gender, int bound, int diff, int size) {
		if(size < 2) return false;
;		return preGuest(person.get(1), gender, bound, diff) ? true : false;
	}
}
