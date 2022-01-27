package common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class CSVFileModifider {
	public static void main(String[] args) {
        try{
        	File file = new File("/Users/exponential-e/Desktop/SWM_Proj/crawling/game.csv");
        	BufferedWriter bw = Files.newBufferedWriter(Paths.get("/Users/exponential-e/Desktop/SWM_Proj/crawling/temp.csv"),Charset.forName("UTF-8"));
        	BufferedReader br = new BufferedReader(new FileReader(file));
            
        	String input = "";
        	
        	while ((input = br.readLine()) != null) {
                String[] token = input.split(",");
                String[] dummy = new String[6];
                
                for(int i =0; i < 6; i++) {
                	if(i == 3 && !token[i].equals("time")) token[i] = "\"" + token[i] + "\"";
                	dummy[i] = token[i];
                }
                
                String result = "";
               
                for(int i=0; i < 6; i++) {
                	result += dummy[i] + (i == 5 ? "": ",");
                }
                System.out.println(result);
                bw.write(result + "\n");
            }
            br.close();
            bw.close();
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
