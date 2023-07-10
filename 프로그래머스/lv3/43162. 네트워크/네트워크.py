'''
Approach
    - n,edge가 주어질때 네트워크 개수를 return
    - 1<=n<=200

>>> 간단하게 섬의 개수를 구하는 문제라 보면 됨

'''
from collections import deque
def solution(n, computers):
    answer = 0
    visited = [False]*200
    # bfs 시작
    for i in range(n):
        if visited[i]:
            continue
        answer +=1
        dq = deque()
        dq.append(i)
        while dq:
            c = dq.popleft()
            if visited[c]:
                continue
            visited[c] = True
            for i in range(n):
                if i == c:
                    continue
                if computers[c][i]:
                    dq.append(i)
    return answer