'''
Approach
    - 진행방식
        1. 큐에서 프로세스 하나를 꺼냄
        2. 큐에, 꺼낸것보다 우선순위가 높은 프로세스가 있다면 다시 큐에 넣음
        3. 그렇지 않다면 꺼낸 프로세스를 실행하고 종료시킴
        
>>> deque 사용
    : 큐를 한번 돌려서 maxVal과 그 갯수를 파악해둔 후, location에 위치한 프로세스가 종료될때까지 실행한다
    
'''
from collections import deque
def solution(priorities, location):
    answer = 0
    location +=1
    dq = deque(priorities)
    
    while dq:
        val = dq[0]
        location -=1
        # val == location일때
        if location == 0:
            # 우선순위가 최대일때
            if val >= max(dq):
                print(val)
                answer +=1
                return answer
            # 우선순위가 최대가 아닐때
            else:
                dq.rotate(-1)
                location += len(dq)
                continue
            
        # val != location일때
        if val >= max(dq):
            answer +=1
            dq.popleft()
            print(val)
        else:
            dq.rotate(-1)
