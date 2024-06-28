/*
conditions
    - TL: 1s
    - 3kg, 5kg 봉지가 있음

goals
    - N kg을 배달해야할 때, 최소 봉지 수
    - 불가능하면 -1 출력

approach
    - 5kg부터 채워넣고, 부족한 부분을 3kg으로 채워넣는다
    - 전부 3kg으로 다 채웠을때도 불가능하면 -1출력
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        boolean trigger = false;
        for (int cnt5 = n/5; cnt5>=0; cnt5-=1){
            int cnt3 = (n-5*cnt5)/3;
            if (5*cnt5 + 3*cnt3 == n){
                System.out.println(cnt5+cnt3);
                trigger = true;
                break;
            }
        }
        if (!trigger) System.out.println(-1);
    }
}