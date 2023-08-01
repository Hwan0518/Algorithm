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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        // n을 입력받음
        Integer n = Integer.parseInt(br.readLine());

        // n번만큼 위치를 입력받고, 움직인 횟수를 확인
        HashMap<Integer, Integer> dct = new HashMap<>();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            int[] input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int cow = input[0];
            int posit = input[1];

            if (!dct.containsKey(cow)) {
                dct.put(cow, posit);
            } else if (!dct.get(cow).equals(posit)) {
                cnt ++;
                dct.put(cow, posit);
            }
        }
        sb.append(cnt);
        System.out.println(sb);
    }

}

