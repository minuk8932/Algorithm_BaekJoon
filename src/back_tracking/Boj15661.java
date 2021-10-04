package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.BiPredicate;

/**
 * 
 * 	@author minchoba
 *	백준 15661번: 링크와 스타트
 *
 *	@see https://www.acmicpc.net/problem/15661/
 *
 */
public class Boj15661 {
	private static int min = Integer.MAX_VALUE;

	private static boolean[][] team;
	private static int[][] potential;

	private static int N;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		potential = new int[N][N];
		team = new boolean[2][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++) {
				potential[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		backTracking(0);
		System.out.println(min);
	}

	/**
	 *
	 * Back tracking
	 *
	 * line 51 ~ 57: Team building complete
	 * line 59 ~ 65: Current member distribution
	 *
	 * @param current
	 */
	private static void backTracking(int current) {
		if (current == N) {
			int t1 = getTeamPotential(team[0]);
			int t2 = getTeamPotential(team[1]);

			min = Math.min(min, Math.abs(t1 - t2));
			return;
		}

		team[0][current] = true;
		backTracking(current + 1);
		team[0][current] = false;

		team[1][current] = true;
		backTracking(current + 1);
		team[1][current] = false;
	}

	private static int getTeamPotential(boolean[] team) {
		int sum = 0;

		for (int i = 0; i < team.length; i++) {
			for (int j = i + 1; j < team.length; j++) {
				if(THEY_ARE_NOT_TEAM.test(team[i], team[j])) continue;

				sum += potential[i][j] + potential[j][i];
			}
		}

		return sum;
	}

	private static final BiPredicate<Boolean, Boolean> THEY_ARE_NOT_TEAM = (x, y) -> !x || !y;
}
