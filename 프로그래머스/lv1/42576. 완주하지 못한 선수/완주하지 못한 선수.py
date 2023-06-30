def solution(participant, completion):
    dic = {}
    for people in participant:
        if people in dic:
            dic[people] +=1
        else:
            dic[people] = 1
    
    for people in completion:
        if people in dic:
            dic[people] -=1
        else:
            return people  
    
    for people in dic.keys():
        if dic[people]:
            return people
    