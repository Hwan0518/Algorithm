/*
Condition
    - TL :
      ML :
    - 1<=N,M<=50만
    - 1<=name<=20, 알파벳 소문자만

Question
    - 듣 + 보 에서 겹치는 사람의 명단을 사전순 출력

Access
    - Hash
        : hash에 듣+보 각각 cnt 확인후 1 이상인것 출력 -> N+M+(N,M중첩)


input&output hint
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
StringBuffer sb = new StringBuffer();
String input = br.readLine();
int val = Integer.parseInt(input);
sout(sb);
br.close();
*/


import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        int price = Integer.parseInt(br.readLine());
        int[] changes = {500, 100, 50, 10, 5, 1};

        int remainder = 1000 - price;
        int result = 0;
        for (int coin : changes) {
            if (remainder == 0) {
                break;
            }
            int quotient = remainder / coin;
            int possible = quotient*coin;
            result += quotient;
            int newRemainder = (quotient != 0) ? remainder % possible : remainder;
            remainder = newRemainder;
        }
        sb.append(result);
        System.out.println(sb);
    }
}


