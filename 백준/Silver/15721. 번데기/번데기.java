/*
Condition
    - TL :
      ML :

Question
    - param0 = String, 특정 시점
      parmm1 = Integer, 승객 인원수
    - input = 년도4자리+월2자리, 승객수
    - solution()의 리턴값은 String
    - 생산시작과 생산종료를 param0와 비교하여, 생산중인 시점의 모델을 출력
    - param1의 승객 인원수와 비교하여 탑승 가능한 모델만 출력
    - 단종된 모델은 *를 붙여서 출력
    - 최종 출력값은 모델 이름순으로 정렬해야함

Access
    -

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
import java.util.stream.Collectors;

import static java.lang.Math.max;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        // a,t,word를 입력받음
        int a = Integer.parseInt(br.readLine());
        int t = Integer.parseInt(br.readLine());
        String word = br.readLine();
        // 전체 순회하며 a_cnt와 t_cnt, repeat을 증가시켜줌
        int a_cnt = 0;
        int t_cnt = 0;
        int repeat = 2;
        while (t_cnt<t){
            ArrayList<String> say = new ArrayList<>(Arrays.asList("0","1","0","1"));
            for (int i = 0; i < repeat; i++) {
                say.add("0");
            }
            for (int i = 0; i < repeat; i++) {
                say.add("1");
            }

            for (String s : say) {
                // n번째 사람
                if (s.equals(word)) {
                    t_cnt++;
                }
                if (t_cnt == t) {
                    break;
                }
                // 다음 사람
                a_cnt++;
                if (a_cnt == a) {
                    a_cnt = 0;
                }
            }
            repeat++;
        }
        sb.append(a_cnt);
        System.out.println(sb);
    }

}

