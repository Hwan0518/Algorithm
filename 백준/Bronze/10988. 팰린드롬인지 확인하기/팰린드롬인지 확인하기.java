/*
Condition
    - TL :
      ML :

Question
    -

Access
    - input의 앞, 뒤에서 한글자씩 꺼내서 같은지 비교한다

*/

import java.io.*;



public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        // 입력 문자열
        String input = br.readLine();
        // flag = true면 팰린드롬, false면 팰린드롬이 아님
        boolean flag = true;
        // forward = 앞에서, backward = 뒤에서
        int size = input.length();
        for (int i = 0; i < size; i++) {
            char forward = input.charAt(i);
            char backward = input.charAt(size-1-i);
            if (forward != backward) {
                flag = false;
                break;
            }
        }

        if (flag) {
            sb.append(1);
        } else {
            sb.append(0);
        }

        System.out.println(sb);
        br.close();
    }
}