public class Default{
    public static void main(String[] args) {
        String[] category = {"계기변화", "시간순묘사", "최근경험", "과거비교", "육하원칙", "특별경험", "장소묘사", "활동묘사", "인물묘사", "사물묘사"};
        String[] subject = {"은행", "도서관", "재활용", "쇼핑", "명절", "공원", "수영", "영화", "음악장르", "해변", "산"};

        System.out.println(category[(int) (Math.random() * 10) % 8] + " " + subject[(int) (Math.random() * 10) % 9]);
    }
}