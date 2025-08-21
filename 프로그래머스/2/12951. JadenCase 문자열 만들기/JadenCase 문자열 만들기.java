import java.util.*;

/*
Approach
- " "로 split
- 이후 첫 단어가 알파벳인지만 확인 (character 활용)
*/

class Solution {
    
    public String solution(String s) {
        
        // set-up
        StringBuilder sb = new StringBuilder();
        
        // jaden-case
        boolean isFirst = true;
        for (int i=0; i<s.length(); i++) {
            
            char c = s.charAt(i);
            
            // character
            if (c >= 'A' && c <= 'z') {
                if (isFirst) {
                    sb.append(Character.toUpperCase(c));
                } else {
                    sb.append(Character.toLowerCase(c));
                }
                isFirst = false;
            } 
            
            // blank
            else if (c == ' ') {
                isFirst = true;
                sb.append(c);
            }
            
            // num
            else {
                isFirst = false;
                sb.append(c);
            }
            
        }
        
        // result
        return sb.toString();
        
    }
}