import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class FileInOutWriter {
	private static final String COMMA = ",";
	private static final String HYPER = "-";
	
	public static void main(String[] args) throws Exception{        
        BufferedWriter bufWriter = null;
        try{
            bufWriter = Files.newBufferedWriter(Paths.get("/Users/exponential-e/Desktop/MOCK_DATA_edit.csv"),Charset.forName("UTF-8"));
            List<List<String>> allData = read();
            
            for(List<String> newLine : allData){
                List<String> list = newLine;
                
                StringBuilder build = new StringBuilder();
                
                for(String data : list){
                    build.append(data).append(COMMA);
                }
                
                bufWriter.write(build.toString().substring(0, build.length() - 1));
                bufWriter.newLine();
            }
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally{
            try{
                if(bufWriter != null){
                    bufWriter.close();
                }
            } 
            catch(IOException e){
                e.printStackTrace();
            }
        }
	}
	
	private static List<List<String>> read(){
		List<List<String>> ret = new ArrayList<>();
        BufferedReader br = null;
        
        try{
            br = Files.newBufferedReader(Paths.get("/Users/exponential-e/Desktop/MOCK_DATA.csv"));
            Charset.forName("UTF-8");
            String line = "";
            
            while((line = br.readLine()) != null){
                List<String> tmpList = new ArrayList<String>();
                String array[] = line.split(COMMA);
                
                if(array[0].equals("id")) {
                	tmpList = Arrays.asList(array);
                    ret.add(tmpList);
                    
                    continue;
                }
                
                String item = array[3];
                StringTokenizer st = new StringTokenizer(item);
                String dates = st.nextToken();
                String hours = st.nextToken();
                
                st = new StringTokenizer(dates, HYPER);
                String year = st.nextToken();
                st.nextToken();
                st.nextToken();
                
                int mon = (int) (Math.random() * 12) + 1;
                int day = (int) (Math.random() * 30) + 1;
                
                array[3] = year + HYPER + (mon < 10 ? "0" + mon: mon) + HYPER + (day < 10 ? "0" + day: day) + " " + hours;
                tmpList = Arrays.asList(array);
                
                ret.add(tmpList);
            }
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally{
            try{
                if(br != null){
                    br.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        
        return ret;
	}
}
