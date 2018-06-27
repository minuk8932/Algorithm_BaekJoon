import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj11387 {
	private static final String NEW_LINE = "\n";
	private static final String UP = "+";
	private static final String DOWN = "-";
	private static final String NOT = "0";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		Inven crii = new Inven(Double.parseDouble(st.nextToken()), 
									Double.parseDouble(st.nextToken()),
									Double.parseDouble(st.nextToken()),
									Double.parseDouble(st.nextToken()),
									Double.parseDouble(st.nextToken()));
		
		double crtLimitCC = (crii.criticalPercent = crii.criticalPercent >= 100 ? 1.0 : crii.criticalPercent);
		double criiCurrent = crii.strikePower * (1.0 + crii.strong / 100) * 
				((1.0 - crtLimitCC) + crtLimitCC * crii.criticalDemage) * 
				(1.0 + crii.attackSpeed / 100);
		
		st = new StringTokenizer(br.readLine());
		Inven pabu = new Inven(Double.parseDouble(st.nextToken()), 
								Double.parseDouble(st.nextToken()),
								Double.parseDouble(st.nextToken()),
								Double.parseDouble(st.nextToken()),
								Double.parseDouble(st.nextToken()));
		
		double crtLimitPC = (pabu.criticalPercent = pabu.criticalPercent >= 100 ? 1.0 : pabu.criticalPercent);
		double pabuCurrent = pabu.strikePower * (1.0 + pabu.strong / 100) * 
				((1.0 - crtLimitPC) + crtLimitPC * pabu.criticalDemage) * 
				(1.0 + pabu.attackSpeed / 100);
		
		st = new StringTokenizer(br.readLine());
		double spC = Double.parseDouble(st.nextToken());
		double sC = Double.parseDouble(st.nextToken());
		double cpC = Double.parseDouble(st.nextToken()) / 100;
		double cdC = Double.parseDouble(st.nextToken()) / 100;
		double asC = Double.parseDouble(st.nextToken()) / 100;	
		
		st = new StringTokenizer(br.readLine());
		double spP = Double.parseDouble(st.nextToken());
		double sP = Double.parseDouble(st.nextToken());
		double cpP = Double.parseDouble(st.nextToken()) / 100;
		double cdP = Double.parseDouble(st.nextToken()) / 100;
		double asP = Double.parseDouble(st.nextToken()) / 100;	
		
		crii.strikePower += (spP - spC);
		crii.strong += (sP - sC);
		crii.criticalPercent += (cpP - cpC);
		crii.criticalDemage += (cdP - cdC);
		crii.attackSpeed += (asP - asC);
		
		double crtLimitCE = (crii.criticalPercent = crii.criticalPercent >= 100 ? 1.0 : crii.criticalPercent / 100);
		double criiEquiped = crii.strikePower * (1.0 + crii.strong / 100) * 
				((1.0 - crtLimitCE) + crtLimitCE * crii.criticalDemage) * 
				(1.0 + crii.attackSpeed / 100);
		
		pabu.strikePower += (spC - spP);
		pabu.strong += (sC - sP);
		pabu.criticalPercent += (cpC - cpP);
		pabu.criticalDemage += (cdC - cdP);
		pabu.attackSpeed += (asC - asP);
		
		double crtLimitPE = (pabu.criticalPercent = pabu.criticalPercent >= 100 ? 1.0 : pabu.criticalPercent / 100); 
		double pabuEquiped = pabu.strikePower * (1.0 + pabu.strong / 100) * 
				((1.0 - crtLimitPE) + crtLimitPE * pabu.criticalDemage) * 
				(1.0 + pabu.attackSpeed / 100);
		
		sb.append(criiCurrent == criiEquiped ? NOT : (criiCurrent > criiEquiped ? DOWN : UP)).append(NEW_LINE);
		sb.append(pabuCurrent == pabuEquiped ? NOT : (pabuCurrent > pabuEquiped ? DOWN : UP)).append(NEW_LINE);
		
		System.out.println(sb.toString());
	}
	
	private static class Inven{
		double strikePower;
		double strong;
		double criticalPercent;
		double criticalDemage;
		double attackSpeed;
		
		public Inven(double strikePower, double strong, double criticalPercent, double criticalDemage, double attackSpeed) {
			this.strikePower = strikePower;
			this.strong = strong;
			this.criticalPercent = criticalPercent;
			this.criticalDemage = criticalDemage / 100;
			this.attackSpeed = attackSpeed / 100;
		}
	}
}
