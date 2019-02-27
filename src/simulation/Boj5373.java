package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 5373번: 큐빙
 *
 *	@see https://www.acmicpc.net/problem/5373/
 *
 */
public class Boj5373 {
	private static char[][][] cube = new char[6][6][3];
	private static final String NEW_LINE = "\n";
	private static final char CCW = '-';
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			init();
			int N = Integer.parseInt(br.readLine());
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			String[] cmd = new String[N];
			
			for(int i = 0; i < N; i++) {
				cmd[i] = st.nextToken();
			}
			
			process(N, cmd);
			sb.append(printUp());
		}
		
		System.out.print(sb);
	}
	
	private static void init() {
		char[] color = {'w','y','r','o','g','b'};
		
		for(int i=0; i<6; i++){
	        for(int j=0; j<3; j++){
	            for(int k=0; k<3; k++){
	                cube[i][j][k] = color[i];
	            }
	        }
	    }
	}
	
	private static void process(int n, String[] arr) {
		for(int i = 0; i < n; i++) {
			char face = arr[i].charAt(0);
			char dir = arr[i].charAt(1);
			
			switch(face) {
			case 'U':
				turn(0, dir);
				turnUp(dir);
				break;
				
			case 'D':
				turn(1, dir);
				turnDown(dir);
				break;
				
			case 'F':
				turn(2, dir);
				turnFront(dir);
				break;
				
			case 'B':
				turn(3, dir);
				turnBack(dir);
				break;
				
			case 'L':
				turn(4, dir);
				turnLeft(dir);
				break;
				
			case 'R':
				turn(5, dir);
				turnRight(dir);
				break;
			}
		}
	}
	
	private static void turnUp(char direction){
		char[] tmp = new char[3];
		
		if(direction == CCW) {
			for(int i = 0; i < 3; i++) tmp[i] = cube[2][0][i];
			for(int i = 0; i < 3; i++) cube[2][0][i] = cube[4][0][i];
			for(int i = 0; i < 3; i++) cube[4][0][i] = cube[3][2][2-i];
			for(int i = 0; i < 3; i++) cube[3][2][2-i] = cube[5][0][i];
			for(int i = 0; i < 3; i++) cube[5][0][i] = tmp[i];
		}
		else {
			for(int i = 0; i < 3; i++) tmp[i] = cube[2][0][i];
			for(int i = 0; i < 3; i++) cube[2][0][i] = cube[5][0][i];
			for(int i = 0; i < 3; i++) cube[5][0][i] = cube[3][2][2-i];
			for(int i = 0; i < 3; i++) cube[3][2][2-i] = cube[4][0][i];
			for(int i = 0; i < 3; i++) cube[4][0][i] = tmp[i];
		}
	}

	private static void turnDown(char direction){
		char[] tmp = new char[3];
		
		if(direction == CCW) {
			for(int i = 0; i < 3; i++) tmp[i] = cube[2][2][i];
			for(int i = 0; i < 3; i++) cube[2][2][i] = cube[5][2][i];
			for(int i = 0; i < 3; i++) cube[5][2][i] = cube[3][0][2-i];
			for(int i = 0; i < 3; i++) cube[3][0][2-i] = cube[4][2][i];
			for(int i = 0; i < 3; i++) cube[4][2][i] = tmp[i];
		}
		else {
			for(int i = 0; i < 3; i++) tmp[i] = cube[2][2][i];
			for(int i = 0; i < 3; i++) cube[2][2][i] = cube[4][2][i];
			for(int i = 0; i < 3; i++) cube[4][2][i] = cube[3][0][2-i];
			for(int i = 0; i < 3; i++) cube[3][0][2-i] = cube[5][2][i];
			for(int i = 0; i < 3; i++) cube[5][2][i] = tmp[i];
		}
	}

	private static void turnFront(char direction){
		char[] tmp = new char[3];
		
		if(direction == CCW) {
			for(int i = 0; i < 3; i++) tmp[i] = cube[0][2][i];
			for(int i = 0; i < 3; i++) cube[0][2][i] = cube[5][i][0];
			for(int i = 0; i < 3; i++) cube[5][i][0] = cube[1][0][2-i];
			for(int i = 0; i < 3; i++) cube[1][0][2-i] = cube[4][2-i][2];
			for(int i = 0; i < 3; i++) cube[4][2-i][2] = tmp[i];
		}
		else {
			for(int i = 0; i < 3; i++) tmp[i] = cube[0][2][i];
			for(int i = 0; i < 3; i++) cube[0][2][i] = cube[4][2-i][2];
			for(int i = 0; i < 3; i++) cube[4][2-i][2] = cube[1][0][2-i];
			for(int i = 0; i < 3; i++) cube[1][0][2-i] = cube[5][i][0];
			for(int i = 0; i < 3; i++) cube[5][i][0] = tmp[i];
		}
	}

	private static void turnBack(char direction){
		char[] tmp = new char[3];
		
		if(direction == CCW) {
			for(int i = 0; i < 3; i++) tmp[i] = cube[0][0][i];
			for(int i = 0; i < 3; i++) cube[0][0][i] = cube[4][2-i][0];
			for(int i = 0; i < 3; i++) cube[4][2-i][0] = cube[1][2][2-i];
			for(int i = 0; i < 3; i++) cube[1][2][2-i] = cube[5][i][2];
			for(int i = 0; i < 3; i++) cube[5][i][2] = tmp[i];
		}
		else {
			for(int i = 0; i < 3; i++) tmp[i] = cube[0][0][i];
			for(int i = 0; i < 3; i++) cube[0][0][i] = cube[5][i][2];
			for(int i = 0; i < 3; i++) cube[5][i][2] = cube[1][2][2-i];
			for(int i = 0; i < 3; i++) cube[1][2][2-i] = cube[4][2-i][0];
			for(int i = 0; i < 3; i++) cube[4][2-i][0] = tmp[i];
		}
	}

	private static void turnLeft(char direction){
		char[] tmp = new char[3];
		
		if(direction == CCW) {
			for(int i = 0; i < 3; i++) tmp[i] = cube[0][i][0];
			for(int i = 0; i < 3; i++) cube[0][i][0] = cube[2][i][0];
			for(int i = 0; i < 3; i++) cube[2][i][0] = cube[1][i][0];
			for(int i = 0; i < 3; i++) cube[1][i][0] = cube[3][i][0];
			for(int i = 0; i < 3; i++) cube[3][i][0] = tmp[i];
		}
		else {
			for(int i = 0; i < 3; i++) tmp[i] = cube[0][i][0];
			for(int i = 0; i < 3; i++) cube[0][i][0] = cube[3][i][0];
			for(int i = 0; i < 3; i++) cube[3][i][0] = cube[1][i][0];
			for(int i = 0; i < 3; i++) cube[1][i][0] = cube[2][i][0];
			for(int i = 0; i < 3; i++) cube[2][i][0] = tmp[i];
		}
	}

	private static void turnRight(char direction){
		char[] tmp = new char[3];
		
		if(direction == CCW) {
			for(int i = 0; i < 3; i++) tmp[i] = cube[0][i][2];
			for(int i = 0; i < 3; i++) cube[0][i][2] = cube[3][i][2];
			for(int i = 0; i < 3; i++) cube[3][i][2] = cube[1][i][2];
			for(int i = 0; i < 3; i++) cube[1][i][2] = cube[2][i][2];
			for(int i = 0; i < 3; i++) cube[2][i][2] = tmp[i];
		}
		else {
			for(int i = 0; i < 3; i++) tmp[i] = cube[0][i][2];
		    for(int i = 0; i < 3; i++) cube[0][i][2] = cube[2][i][2];
		    for(int i = 0; i < 3; i++) cube[2][i][2] = cube[1][i][2];
		    for(int i = 0; i < 3; i++) cube[1][i][2] = cube[3][i][2];
		    for(int i = 0; i < 3; i++) cube[3][i][2] = tmp[i];
		}
	}
	
	private static void turn(int idx, char direction) {
		char[][] tmp = new char[3][3];
		int row = 0, col = 0;
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				tmp[i][j] = cube[idx][i][j];
			}
		}
		
		if(direction == CCW) {
			for(int i = 0; i < 3; i++){
				for(int j = 2; j >= 0; j--){				//		| 	   ^
	            	cube[idx][j][i] = tmp[row][col++];		//		|	   |
	            }											//		|______|
				
				row++;
	            col = 0;
	        }
		}
		
		else {			
			for(int i = 2; i >= 0; i--){
				for(int j = 0; j < 3; j++){					//		^		|
					cube[idx][j][i] = tmp[row][col++];		//		|		|
	            }											//		|_______|
				
				row++;
	            col = 0;
	        }
		}
	}
	
	private static StringBuilder printUp() {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				sb.append(cube[0][i][j]);
			}
			sb.append(NEW_LINE);
		}
		
		return sb;
	}
}
