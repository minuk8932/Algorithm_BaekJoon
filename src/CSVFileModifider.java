import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CSVFileModifider {
	static String[][] indat = new String[3124][6];

	public static void main(String[] args) {
        try{
        	File file = new File("/Users/exponential-e/Desktop/SWM_Proj/crawling/game_detail.csv");
        	BufferedWriter bw = Files.newBufferedWriter(Paths.get("/Users/exponential-e/Desktop/SWM_Proj/crawling/temp.csv"),Charset.forName("UTF-8"));
        	BufferedReader br = new BufferedReader(new FileReader(file));
            
        	String input = "";
        	int row = 0, col = 0;
        	
        	while ((input = br.readLine()) != null) {
                String[] token = input.split(",", -1);
                
                for(col=0; col < 6; col++) {
                	if(col == 3) token[col] = "\"" + token[col] + "\"";
                	
                	indat[row][col] = token[col];
                }
               
                for(col=0; col < 6; col++) {
                	bw.write(indat[row][col] + (col == 5 ? "": ","));
                	System.out.print(indat[row][col] + (col == 5 ? "": ","));
                }
                bw.write("\n");
                System.out.println("");
               
                row++;
            }
            br.close();
        }
        catch (FileNotFoundException e){
            System.out.println("파일 없음");
            e.printStackTrace();
        }
        catch (IOException e){
            System.out.println("I/O 에러");
            e.printStackTrace();
        }
	}
}
