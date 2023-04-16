import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        ArrayList<Character> alphabet = new ArrayList<>();
        for (char c = 'a'; c <= 'z'; c++) {
            alphabet.add(c);
        }

        String word = br.readLine();
        int size = word.length();
        HashSet<Character> visited = new HashSet<>();
        for (int i = 0; i < 26; i++) {
            char alpha = alphabet.get(i);
            if (visited.contains(alpha)) {continue;}
            boolean flag = false;

            // alpha가 word안에 있는지 검사
            int idx = 0;
            for (int j = 0; j < size; j++) {
                char s = word.charAt(j);
                if (alpha == s) {
                    flag = true;
                    idx = j;
                    break;
                }
            }
            // flag = true라면 j값을 더하고 아니라면 -1값을 더함
            if (flag) {
                sb.append(idx + " ");
            } else {
                sb.append(-1 + " ");
            }
        }

        System.out.println(sb);
        br.close();
    }
}
