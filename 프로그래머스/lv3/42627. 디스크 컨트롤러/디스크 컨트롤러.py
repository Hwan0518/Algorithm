'''
Approach
    - 최소 평균 작업시간을 구하여라
    - 각 작업시간 = 해당작업 종료시간 - 해당작업 요청시간

>>> 매번 종료시간을 바꿔주면서 힙정렬을 해주면 될까?
    : X
    
>>> 기다리는 시간이 짧아야지 최소 averrage가 나온다!
    : 한 작업의 종료시점과 가장 가까운 요청시점을 가진 작업을 선택하면 된다
    
>>> 그냥 제일 cost부터 해결하면 됨
'''
from heapq import *
def solution(jobs):
    # 초기화
    answer = 0
    n = len(jobs)
    end = 0
    heapify(jobs)
    
    while jobs:
        # 작업 수행중이 아니라면 가장 빠른 순서부터 해결
        cur_stt, cur_cost = heappop(jobs)
        if cur_stt >= end:
            answer += cur_cost        
            end = cur_stt + cur_cost
        # 작업 수행중이라면, 요청이 들어온 작업중 가장 cost가 작은 작업을 실행
        else:
            candidate = [(cur_cost, cur_stt)]
            while jobs:
                n_stt, n_cost = heappop(jobs)
                if n_stt >= end:
                    heappush(jobs,[n_stt,n_cost])
                    break
                heappush(candidate,(n_cost,n_stt))
            # 가장 cost가 작은 요청을 제외하고 모두 다시 집어넣음
            target_cost, target_stt = heappop(candidate)
            answer += (end - target_stt) + target_cost
            for n_cost, n_stt in candidate:
                heappush(jobs,[n_stt,n_cost])
            end += target_cost
    
    return answer//n

# print(solution([[1,4],[7,9],[1000,3]]))
# print(solution([[0,3],[4,4],[5,3],[4,1]]))
print(solution([[0,1],[0,2],[2,1]]))