'''
Approach
    - 한번에 최대 2명
    - 최대한 적게 사용하여 모든 사람을 구출
    - 1<=n<=5만, 40<=w<=240, 40<=limit<=240
    
>>> 그리디
    : w가 크거나 작은 순서대로 집어넣어본다
      -> fail
>>> limit을 만족하는 사람을 태운다
    : p를 꺼내고, (limit-p)-다른사람, 이 0에 가장 가까운 사람을 태운다

'''
from collections import deque
def solution(people, limit):
    answer = 0
    people.sort(reverse=True)
    dq = deque(people)
    while dq:
        fst = dq.popleft()
        if not dq:
            answer +=1
            print(fst)
            break
        scd = dq.pop()
        
        if fst + scd <= limit:
            print(fst,scd)
            answer +=1
        else:
            print(fst)
            answer +=1
            dq.append(scd)
        
    return answer