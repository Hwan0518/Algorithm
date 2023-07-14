'''
Approach
    - 진행방식
        1. 큐에서 프로세스 하나를 꺼냄
        2. 큐에, 꺼낸것보다 우선순위가 높은 프로세스가 있다면 다시 큐에 넣음
        3. 그렇지 않다면 꺼낸 프로세스를 실행하고 종료시킴
        
>>> deque 사용
    : 큐를 한번 돌려서 maxVal과 그 갯수를 파악해둔 후, location에 위치한 프로세스가 종료될때까지 실행한다
    
>>> max를 사용하자
    : n이 작기 때문에 그냥 max를 사용해도 될 것 같다
    
'''
from collections import deque
def solution(priorities, location):
    answer = 0
    l = location
    dq = deque(priorities)
    
    while dq:
        val = dq[0]
        # 목표가 우선순위일때
        if l == 0 and val >= max(dq):
            return answer +1
        # 목표가 우선순위가 아닐때
        elif l == 0:
            dq.rotate(-1)
            l += len(dq)
        # 목표가 아니지만 우선순위일때
        elif val >= max(dq):
            dq.popleft()
            answer +=1
        # 목표도 아니고 우선순위도 아닐때
        else:
            dq.rotate(-1)
        l -=1
        
