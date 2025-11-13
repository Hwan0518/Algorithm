import java.util.*;

class Solution {
    
    public String solution(String new_id) {
        
        String answer = step1(new_id);
        System.out.println(answer);
        
        answer = step2(answer);
        System.out.println(answer);
        
        answer = step3(answer);
        System.out.println(answer);
        
        answer = step4(answer);
        System.out.println(answer);
        
        answer = step5(answer);
        System.out.println(answer);
        
        answer = step6(answer);
        System.out.println(answer);
        
        answer = step7(answer);
        
        return answer;
    }
    
    
    String step1(String s) {
        return s.toLowerCase();
    }
    
    
    String step2(String s) {
        
        StringBuilder sb = new StringBuilder();
        
        for (char c : s.toCharArray()) {
            if (('a'<=c && c<='z') || ('0'<=c && c<='9') || c=='-' || c=='_' || c=='.') sb.append(c);    
        }
        
        return sb.toString();
    }
    
    
    String step3(String s) {
        
        int i = 0;
        int last = s.length();
        
        while (i < last-1) {
            
            if (s.charAt(i) == '.' && s.charAt(i+1) == '.') {
                s = s.replaceFirst("\\.\\.", ".");
                last --;
                continue;
            }
            
            i ++;
        }
        
        return s;
    }
    
    
    String step4(String s) {
        
        boolean removeFirst = false;
        boolean removeLast = false;
        
        if (s.charAt(0) == '.') removeFirst =true;
        if (s.charAt(s.length()-1) == '.') removeLast = true;
        
        if (removeFirst && removeLast) s = s.length() > 1 ? s.substring(1, s.length()-1) : "";
        else if (removeFirst) s = s.substring(1);
        else if (removeLast) s = s.substring(0, s.length()-1);
        
        return s;
    }
    
    
    String step5(String s) {
        return s.length() == 0 ? "a" : s;
    }
    
    
    String step6(String s) {
        
        if (s.length() >= 16) {
            s = s.substring(0, 15);
            
            if (s.charAt(s.length()-1) == '.') s = s.substring(0, 14);
        }
        
        return s;
    }
    
    
    String step7(String s) {
        
        while (s.length() < 3) {
            s += s.charAt(s.length()-1);
        }
        
        return s;
    }
    
}