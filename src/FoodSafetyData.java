import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class FoodSafetyData {
    private static FoodDefinition[] foods = new FoodDefinition[1_000];
    private static StringBuilder build;

    private static final String COLON = ": ";
    private static final String NEW_LINE = "\n";
    private static final String TAG_SPECIFY = "</row>";
    private static final String NUTRIENT = "<NUTR_CONT";
    private static final String FOOD_NAME = "<DESC_KOR>";
    private static final String REGION = "<SAMPLING_REGION_NAME>";
    private static final String BRAND = "<MAKER_NAME>";
    private static final String TOTAL_QUANTITY = "<SERVING_SIZE>";

    private static final String[] CATEGORY =
            {"총 내용량", "열량", "탄수화물", "단백질", "지방", "당류", "나트륨", "콜레스테롤", "포화지방산", "트랜스지방"};

    private static class FoodDefinition {
        String name; String region; String brand;
        String total;
        String kcal; String car; String pro; String fat;
        String sug; String nat; String cho; String satFat; String traFat;

        public FoodDefinition(String name, String region, String brand,
                              String total,
                              String kcal, String car, String pro, String fat,
                              String sug, String nat, String cho, String satFat, String traFat) {
            this.name = name; this.region = region; this.brand = brand;
            this.total = total;
            this.kcal = kcal; this.car = car; this.pro = pro; this.fat = fat;
            this.sug = sug; this.nat = nat; this.cho = cho; this.satFat = satFat; this.traFat = traFat;
        }
    }

    public static void main(String[] args) {
        read();
        BufferedWriter bufWriter = null;

        try{
            bufWriter = Files.newBufferedWriter(Paths.get("/Users/exponential-e/Desktop/foodsafety_contest/output.txt"),Charset.forName("UTF-8"));
            StringBuilder make = new StringBuilder();

            for(FoodDefinition fd: foods) {
                make.append(fd.name).append(fd.region).append(fd.brand);
                make.append(fd.total);
                make.append(fd.kcal).append(fd.car).append(fd.pro).append(fd.fat);
                make.append(fd.sug).append(fd.nat).append(fd.cho).append(fd.satFat).append(fd.traFat);

                make.append(NEW_LINE);
            }
            bufWriter.write(make.toString());
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

    private static void read(){
        BufferedReader br = null;

        try{
            br = Files.newBufferedReader(Paths.get("/Users/exponential-e/Desktop/foodsafety_contest/FoodSafetyData1.txt"), Charset.forName("UTF-8"));

            for(int i = 0; i < foods.length; i++){
                String line = "";
                foods[i] = new FoodDefinition("", "", ""
                        , "", "", "", "", ""
                , "", "", "", "", "");

                while((line = br.readLine()) != null) {
                    if (line.contains(TAG_SPECIFY)) break;
                    System.out.println(line);
                    makeDatas(line, i);
                }
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
    }

    private static void makeDatas(String input, int idx) {
        build = new StringBuilder();

        if(input.contains(NUTRIENT))  {
            int index = input.charAt(10) - '0';
            extract(CATEGORY[index], 12, input.toCharArray());

            switch (index) {
                case 1:
                    foods[idx].kcal = build.toString();
                    break;
                case 2:
                    foods[idx].car = build.toString();
                    break;
                case 3:
                    foods[idx].pro = build.toString();
                    break;
                case 4:
                    foods[idx].fat = build.toString();
                    break;
                case 5:
                    foods[idx].sug = build.toString();
                    break;
                case 6:
                    foods[idx].nat = build.toString();
                    break;
                case 7:
                    foods[idx].cho = build.toString();
                    break;
                case 8:
                    foods[idx].satFat = build.toString();
                    break;
                case 9:
                    foods[idx].traFat = build.toString();
                    break;
            }
        }
        else if(input.contains(TOTAL_QUANTITY)) {
            extract(CATEGORY[0], TOTAL_QUANTITY.length(), input.toCharArray());
            foods[idx].total = build.toString();
        }
        else if(input.contains(FOOD_NAME)) {
            extract("음식 이름", FOOD_NAME.length(), input.toCharArray());
            foods[idx].name = build.toString();
        }
        else if(input.contains(REGION)) {
            extract("지역 이름", REGION.length(), input.toCharArray());
            foods[idx].region = build.toString();
        }
        else if(input.contains(BRAND)) {
            extract("상호 명", BRAND.length(), input.toCharArray());
            foods[idx].brand = build.toString();
        }
    }

    private static void extract(String header, int start, char[] chars) {
        build.append(header).append(COLON);

        StringBuilder valueBuilder = new StringBuilder();
        for(int x = start; x < chars.length; x++) {
            if(chars[x] == '<' || chars[x] == '/' || chars[x] == '>') break;
            valueBuilder.append(chars[x]);
        }

        build.append(valueBuilder.toString()).append(NEW_LINE);
    }
}
