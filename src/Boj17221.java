import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj17221 {
	
	private static class Hunt{
		Status player = new Status(0, 0);
		Status mushMom = new Status(0, 0);
		
		public Hunt(Status player, Status mushMom) {
			this.player = player;
			this.mushMom = mushMom;
		}
	}
	
	private static class Status{
		int hp;
		int atk;
		
		public Status(int hp, int atk) {
			this.hp = hp;
			this.atk = atk;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());
		
		System.out.println(hunting(A, B, X, Y));
	}
	
	private static int hunting(int hp, int atk, int mHp, int mAtk) {
		int[] visit = new int[mHp + 1];
		Arrays.fill(visit, Integer.MAX_VALUE);
		
		Queue<Hunt> q = new LinkedList<>();
		q.offer(new Hunt(new Status(hp, atk), new Status(mHp, mAtk)));
		visit[mHp] = 1;
		
		while(!q.isEmpty()) {
			Hunt current = q.poll();
			Hunt[] tmp = new Hunt[3];
			
			System.out.println(current.player.hp + " " + current.player.atk + " " + current.mushMom.hp + " " + current.mushMom.atk);
			
			if(current.player.hp > current.mushMom.atk) {
				if(current.player.hp > current.mushMom.atk * 3) {
					tmp[2] = new Hunt(new Status(current.player.hp - current.mushMom.atk * 3, current.player.atk / 10 * 2 + current.player.atk)
							, new Status(current.mushMom.hp, current.mushMom.atk));
				}
				
				tmp[1] = new Hunt(new Status(current.player.hp - current.mushMom.atk, current.player.atk)
						, new Status(current.mushMom.hp - current.player.atk, current.mushMom.atk));
			}
			
			tmp[0] = new Hunt(new Status(current.player.hp / 10 + current.player.hp, current.player.atk)
					, new Status(current.mushMom.hp - current.mushMom.atk, current.mushMom.atk));
							
			for(Hunt next: tmp) {
				if(next == null) continue;
				
				if(next.mushMom.hp < 0) next.mushMom.hp = 0;
				if(visit[next.mushMom.hp] > visit[current.mushMom.hp] + 1) {
					visit[next.mushMom.hp] = visit[current.mushMom.hp] + 1;
					
					if(next.mushMom.hp == 0) continue;
					q.offer(next);
				}
			}
		}

		return visit[0] - 1;
	}
}
