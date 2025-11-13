import java.util.*;

class Solution {
    
    static Map<Character, Integer> score = new HashMap<>();
    
    public String solution(String[] survey, int[] choices) {
        
        // survey
        doSurvey(survey, choices);
        
        // find userTypes
        String answer = findUserTypes();
        
        // result
        return answer;
    }
    
    
    String findUserTypes() {
        
        String[] seq = { "RT", "CF", "JM", "AN" };
        
        String answer = "";
        for (String types : seq) {
            
            char t1 = types.charAt(0);
            char t2 = types.charAt(1);
            
            if (!score.containsKey(t2) 
                || (score.containsKey(t1) && score.get(t1) >= score.get(t2))) answer += t1;
            else answer += t2;
        }
        
        return answer;
    }
    
    
    void doSurvey(String[] survey, int[] choices) {
        
        for (int i=0; i<survey.length; i++) {
            
            int s = 0;
            int choice = choices[i];
            
            if (choice < 4) {
                
                char type = survey[i].charAt(0);
                s = 4-choice;
                
                score.merge(type, s, (o, n) -> o+n);
            }
            
            else if (choice > 4) {
                
                char type = survey[i].charAt(1);
                s = choice-4;
                
                score.merge(type, s, (o, n) -> o+n);
            }
        }
        
    }
    
    
}