/*
Approach
- 그냥 스택으로 하자
*/

import java.util.*;


class Solution {

    boolean solution(String s) {
        
        // set-up
        ArrayDeque<String> stack = new ArrayDeque<>();
        ArrayDeque<Character> list = new ArrayDeque<>();
        for (int i=0; i<s.length(); i++) {
            list.add(s.charAt(i));
        }
        
        // stack
        while (!list.isEmpty()) {
            
            String ss = String.valueOf(list.removeFirst());
            
            if (stack.isEmpty()) {
                stack.add(ss);
                continue;
            }
            
            String last = stack.removeLast();
            
            if (last.equals("(") && ss.equals("(")) {
                stack.add(last);
                stack.add(ss);
            }
            
            if (last.equals(")")) {
                stack.add(last);
                break;
            }
        }
        
        // answer
        boolean answer = stack.isEmpty() ? true : false;
        return answer;
        
    }
    
}