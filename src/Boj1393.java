import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1393 {
	private static final String SPACE = " ";
	
	private static class Point{
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Point station = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		
		st = new StringTokenizer(br.readLine());
		Point current = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		Point move = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		
		System.out.println(jump(station,  current, move));
	}
	
	private static StringBuilder jump(Point target, Point start, Point m) {
		StringBuilder sb = new StringBuilder();
		
		if(m.y == 0) {
			int x = target.x;
			int y = start.y;
			
			if(m.x == 0) {
				sb.append(start.x).append(SPACE).append(start.y);
			}
			else if(m.x > 0){
				if(target.x < start.x) sb.append(start.x).append(SPACE).append(start.y);
				else sb.append(x).append(SPACE).append(y);
			}
			else {
				if(target.x > start.x) sb.append(start.x).append(SPACE).append(start.y);
				else sb.append(x).append(SPACE).append(y);
			}
			
			return sb;
		}
		else if(m.x == 0) {
			int x = start.x;
			int y = target.y;
			
			if(m.y == 0) {
				sb.append(start.x).append(SPACE).append(start.y);
			}
			else if(m.y > 0){
				if(target.y < start.y) sb.append(start.x).append(SPACE).append(start.y);
				else sb.append(x).append(SPACE).append(y);
			}
			else {
				if(target.y > start.y) sb.append(start.x).append(SPACE).append(start.y);
				else sb.append(x).append(SPACE).append(y);
			}
			
			return sb;
		}
		else {			
			double tilt = (double) m.y / (double) m.x;
			double revTilt = -1.0 / tilt;
			
			double funcS = target.y - (revTilt * target.x);
			double funcT = start.y - (tilt * start.x);
	
			double x = (funcS - funcT) / (tilt - revTilt);
			double y = (revTilt * x) + funcS;
			
			return start.x >= x ? sb.append((start.x)).append(SPACE).append((start.y))
					: sb.append((int) (x)).append(SPACE).append((int) (y));
		}
	}
}
