import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        // 자산 입력
        int cash = Integer.parseInt(br.readLine());
        int j_cash = cash;
        int s_cash= cash;
        int j_stock = 0;
        int s_stock = 0;
        int up = 0;
        int down = 0;

        // 주식 가격 입력
        int[] sharePrice = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        // 14일까지 주식 계산
        for (int i = 0; i < 14; i++) {
            int today_price = sharePrice[i];

            // <준현>
            // 준현이 현금이 오늘 주가보다 크다면 전량매수
            if (j_cash >= today_price) {
                int j_possible = j_cash / today_price;
                j_cash -= j_possible * today_price;
                j_stock += j_possible;
            }

            // <성민>
            if (i == 0) {
                continue;
            }
            int yesterday_price = sharePrice[i - 1];
            // 어제보다 가격이 높아졌다면 up을 +1, down을 0으로
            if (today_price > yesterday_price) {
                up ++;
                down = 0;
            }
            // 어제보다 가격이 낮아졌다면 up을 0, down을 +1
            else if (today_price < yesterday_price) {
                up = 0;
                down ++;
            }
            // 가격이 같다면 둘다 0으로
            else {
                up = 0;
                down = 0;
            }
            // 현금이 오늘 주가이상이고, 3일연속 하락이라면 전량 매수
            if (s_cash >= today_price && down >= 3) {
                int s_possible = s_cash / today_price;
                s_cash -= s_possible * today_price;
                s_stock += s_possible;
            }
            // 주식을 가지고있을때, 3일연속 주가상승이면 전량매도
            if (s_stock > 0 && up >= 3) {
                s_cash += s_stock * today_price;
                s_stock = 0;
            }
        }
        // 자산 계산
        int j_asset = j_cash + j_stock * sharePrice[sharePrice.length-1];
        int s_asset = s_cash + s_stock * sharePrice[sharePrice.length-1];
        // 준현이가 더 크다면 bnp, 작다면 timing, 같다면 samesame
        if (j_asset > s_asset) {
            sb.append("BNP");
        } else if (j_asset < s_asset) {
            sb.append("TIMING");
        } else {
            sb.append("SAMESAME");
        }

        System.out.println(sb);
    }

}

