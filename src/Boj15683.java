import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj15683 {
	private static boolean[][] isObserved = null;
	private static int[][] map = null;
	
	private static CctvInfo[] cctv = null;
	private static Queue<CctvInfo> q = new LinkedList<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		isObserved = new boolean[N][M];
		
		int size = 0;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] > 0 && map[i][j] <= 6) {
					if(map[i][j] != 6) {
						q.offer(new CctvInfo(map[i][j], i , j));
						size++;
					}
		
					isObserved[i][j] = true;
				}
			}
		}
		
		cctv = new CctvInfo[size];
		
		for(int i = 0; i < size; i++) {
			CctvInfo info = q.poll();
			q.offer(info);
			cctv[i] = new CctvInfo(info.num, info.row, info.col);
		}
		
		search(N, M, size);
		
		int res = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(!isObserved[i][j]) res++;
			}
		}
		
		System.out.println(res);
	}
	
	private static class Cctvs{
		int up;
		int right;
		int down;
		int left;
		
		public Cctvs(int up, int right, int down, int left) {
			this.up = up;
			this.right = right;
			this.down = down;
			this.left = left;
		}
	}
	
	private static class CctvInfo{
		int num;
		int row;
		int col;
		
		public CctvInfo(int num, int row, int col) {
			this.num = num;
			this.row = row;
			this.col = col;
		}
	}
	
	private static void search(int n, int m, int leng) {		
		while(!q.isEmpty()) {
			int size = q.size();
			CctvInfo[] max = new CctvInfo[6];
			ArrayList<Cctvs> arr = new ArrayList<>();
			
			while(size-- > 0) {
				CctvInfo cc = q.poll();
				
				int upCnt = 0;
				int downCnt = 0;
				int rightCnt = 0;
				int leftCnt = 0;
				
				switch (map[cc.row][cc.col]) {
				case 1:
					upCnt = rowIncrease(n, cc.row, false);
					downCnt = rowDecrease(cc.row, false);
					rightCnt = colIncrease(m, cc.col, false);
					leftCnt = colDecrease(cc.col, false);
					
					arr.add(new Cctvs(upCnt, downCnt, rightCnt, leftCnt));
					
					max[map[cc.row][cc.col]] = new CctvInfo(getMax(upCnt, downCnt, rightCnt, leftCnt), cc.row, cc.col);
					break;
					
				case 2:
					upCnt = rowIncrease(n, cc.row, false);
					downCnt = rowDecrease(cc.row, false);
					rightCnt = colIncrease(m, cc.col, false);
					leftCnt = colDecrease(cc.col, false);
					
					int rowTotal = upCnt + downCnt;
					int colTotal = rightCnt + leftCnt;
					
					arr.add(new Cctvs(rowTotal, colTotal, -1, -1));
					
					max[map[cc.row][cc.col]] = new CctvInfo(Math.max(rowTotal, colTotal), cc.row, cc.col);
					break;
					
				case 3:
					upCnt = rowIncrease(n, cc.row, false);
					downCnt = rowDecrease(cc.row, false);
					rightCnt = colIncrease(m, cc.col, false);
					leftCnt = colDecrease(cc.col, false);
					
					int urCnt = upCnt + rightCnt;
					int rdCnt = downCnt + rightCnt;
					int dlCnt = downCnt + leftCnt;
					int luCnt = leftCnt + upCnt;
					
					arr.add(new Cctvs(urCnt, rdCnt, dlCnt, luCnt));
					
					max[map[cc.row][cc.col]] = new CctvInfo(getMax(urCnt, rdCnt, dlCnt, luCnt), cc.row, cc.col);				
					break;
					
				case 4:
					upCnt = rowIncrease(n, cc.row, false);
					downCnt = rowDecrease(cc.row, false);
					rightCnt = colIncrease(m, cc.col, false);
					leftCnt = colDecrease(cc.col, false);
					
					int urdCnt = upCnt + downCnt + rightCnt;
					int rdlCnt = downCnt + rightCnt + leftCnt;
					int dluCnt = rightCnt + leftCnt + upCnt;
					int lurCnt = leftCnt + upCnt + downCnt;
					
					arr.add(new Cctvs(urdCnt, rdlCnt, dluCnt, lurCnt));
					
					max[map[cc.row][cc.col]] = new CctvInfo(getMax(urdCnt, rdlCnt, dluCnt, lurCnt), cc.row, cc.col);				
					break;
					
				case 5:
					upCnt = rowIncrease(n, cc.row, false);
					downCnt = rowDecrease(cc.row, false);
					rightCnt = colIncrease(m, cc.col, false);
					leftCnt = colDecrease(cc.col, false);
					
					arr.add(new Cctvs(upCnt + downCnt + rightCnt + leftCnt, -1, -1, -1));
					
					max[map[cc.row][cc.col]] = new CctvInfo(upCnt + downCnt + rightCnt + leftCnt, cc.row, cc.col);
					break;
				}
			}
			
			int totalMax = 0;
			int ccNum = 0, ccRow = 0, ccCol = 0;
			
			for(int i = 1; i < max.length; i++) {
				if(totalMax < max[i].num) {
					totalMax = max[i].num;
					ccNum = i;
					ccRow = max[i].row;
					ccCol = max[i].col;
				}
			}
			
			
		}
	}
	
//	private static void search(int n, int m, int leng) {
//		boolean[] isVisited = new boolean[leng];
//		
//		for(int c = 0; c < leng; c++) {
//			if(isVisited[c]) continue;
//			isVisited[c] = true;
//			
//			int upCnt = 0;
//			int downCnt = 0;
//			int rightCnt = 0;
//			int leftCnt = 0;
//			int max = 0;
//			
//			switch (map[cctv[c].row][cctv[c].col]) {
//			case 1:
//				upCnt = rowIncrease(n, c, false);
//				downCnt = rowDecrease(c, false);
//				rightCnt = colIncrease(m, c, false);
//				leftCnt = colDecrease(c, false);
//				
//				max = getMax(upCnt, downCnt, rightCnt, leftCnt);
//				
//				if(upCnt == max) {
//					rowIncrease(n, c, true);
//				}
//				else if(downCnt == max) {
//					rowDecrease(c, true);
//				}
//				else if(rightCnt == max) {
//					colIncrease(m, c, true);
//				}
//				else {
//					colDecrease(c, true);
//				}
//				
//				break;
//				
//			case 2:
//				upCnt = rowIncrease(n, c, false);
//				downCnt = rowDecrease(c, false);
//				rightCnt = colIncrease(m, c, false);
//				leftCnt = colDecrease(c, false);
//				
//				int rowTotal = upCnt + downCnt;
//				int colTotal = rightCnt + leftCnt;
//				
//				if(rowTotal > colTotal) {
//					rowIncrease(n, c, true);
//					rowDecrease(c, true);
//				}
//				else {
//					colIncrease(m, c, true);
//					colDecrease(c, true);
//				}
//				
//				break;
//				
//			case 3:
//				upCnt = rowIncrease(n, c, false);
//				downCnt = rowDecrease(c, false);
//				rightCnt = colIncrease(m, c, false);
//				leftCnt = colDecrease(c, false);
//				
//				int urCnt = upCnt + rightCnt;
//				int rdCnt = downCnt + rightCnt;
//				int dlCnt = downCnt + leftCnt;
//				int luCnt = leftCnt + upCnt;
//				
//				max = getMax(urCnt, rdCnt, dlCnt, luCnt);
//				
//				if(max == urCnt) {
//					rowIncrease(n, c, true);
//					colIncrease(m, c, true);
//				}
//				else if(max == rdCnt) {
//					colIncrease(m, c, true);
//					rowDecrease(c, true);
//				}
//				else if(max == dlCnt) {
//					rowDecrease(c, true);
//					colDecrease(c, true);
//				}
//				else {
//					rowIncrease(n, c, true);
//					colDecrease(c, true);
//				}
//				
//				break;
//				
//			case 4:
//				upCnt = rowIncrease(n, c, false);
//				downCnt = rowDecrease(c, false);
//				rightCnt = colIncrease(m, c, false);
//				leftCnt = colDecrease(c, false);
//				
//				int udrCnt = upCnt + downCnt + rightCnt;
//				int drlCnt = downCnt + rightCnt + leftCnt;
//				int rluCnt = rightCnt + leftCnt + upCnt;
//				int ludCnt = leftCnt + upCnt + downCnt;
//				
//				max = getMax(udrCnt, drlCnt, rluCnt, ludCnt);
//				
//				if(max == udrCnt) {
//					rowIncrease(n, c, true);
//					rowDecrease(c, true);
//					colIncrease(m, c, true);
//				}
//				else if(max == drlCnt) {
//					rowDecrease(c, true);
//					colDecrease(c, true);
//					colIncrease(m, c, true);
//				}
//				else if(max == rluCnt) {
//					rowIncrease(n, c, true);
//					colDecrease(c, true);
//					colIncrease(m, c, true);
//				}
//				else {
//					rowIncrease(n, c, true);
//					rowDecrease(c, true);
//					colDecrease(c, true);
//				}
//				
//				break;
//				
//			case 5:
//				rowIncrease(n, c, true);
//				rowDecrease(c, true);
//				colIncrease(m, c, true);
//				colDecrease(c, true);
//				
//				break;
//			}
//		}
//	}
	
	private static int getMax(int a, int b, int c, int d) {
		return Math.max(a, Math.max(b, Math.max(c, d)));
	}
	
	private static int rowIncrease(int n, int idx, boolean chg) {
		int cnt = 0;
		
		for(int row = cctv[idx].row; row < n; row++) {
			if(map[row][cctv[idx].col] == 6) break;
			if(!isObserved[row][cctv[idx].col]) cnt++;
			
			if(chg) isObserved[row][cctv[idx].col] = true;
		}
		
		return cnt;
	}
	
	private static int rowDecrease(int idx, boolean chg) {
		int cnt = 0;
		
		for(int row = cctv[idx].row; row >= 0; row--) {
			if(map[row][cctv[idx].col] == 6) break;
			if(!isObserved[row][cctv[idx].col]) cnt++;
			
			if(chg) isObserved[row][cctv[idx].col] = true;
		}
		
		return cnt;
	}
	
	private static int colIncrease(int m, int idx, boolean chg) {
		int cnt = 0;
		
		for(int col = cctv[idx].col; col < m; col++) {
			if(map[cctv[idx].row][col] == 6) break;
			if(!isObserved[cctv[idx].row][col]) cnt++;
			
			if(chg) isObserved[cctv[idx].row][col] = true;
		}
		
		return cnt;
	}
	
	private static int colDecrease(int idx, boolean chg) {
		int cnt = 0;
		
		for(int col = cctv[idx].col; col >= 0; col--) {
			if(map[cctv[idx].row][col] == 6) break;
			if(!isObserved[cctv[idx].row][col]) cnt++;
			
			if(chg) isObserved[cctv[idx].row][col] = true;
		}
		
		return cnt;
	}
}
