'''

'''

def solution(answers):
    answer = []
    n = len(answers)
    # 1번, 2번, 3번의 선택
    fst = [i for i in range(1,6)] * (n//5 +1)
    scd = [2,1,2,3,2,4,2,5] * (n//8 +1)
    trd = [3,3,1,1,2,2,4,4,5,5] * (n//10 +1)
    
    # 정답과 비교
    cnt = [0,0,0]
    for i in range(n):
        ans = answers[i]
        if fst[i] == ans:
            cnt[0] +=1
        if scd[i] == ans:
            cnt[1] +=1
        if trd[i] == ans:
            cnt[2] +=1
    
    for i in range(3):
        if cnt[i] == max(cnt):
            answer.append(i+1)
    
    return answer