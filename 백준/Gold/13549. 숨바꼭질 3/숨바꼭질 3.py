'''
Condition
    - TL: 2s
    - n,k <=10만
    - 이동: 1초후, x+1 or x-1 이동(시간증가o)
    - 순간이동: 0초후, 2x 이동 (시간증가x)

Goals
    - n과 k가 만날 수 있는 최소 시간은?

Approach
    - bfs 도전
        - n*k하면 tle지만, 이동 방법이 2*x가 있으므로 도전해보자
'''
from collections import deque
n,k = map(int,input().split())

def move1(cur:int, t:int):
    return cur+1, t+1

def move2(cur:int, t:int):
    return cur-1, t+1

def move3(cur:int, t:int):
    return 2*cur, t

min_t = int(1e9)
visited = [-1]*(2**17+1)
visited[n] = 0
dq = deque()
dq.append((n,0))
while dq:
    cur, t = dq.popleft()
    if cur == k:
        min_t = min(min_t, t)
    for move in [move1, move2, move3]:
        new_x, new_t = move(cur, t)
        if not(new_x >= 0 and new_x <=100000) or new_t > min_t:
            continue
        if visited[new_x] != -1 and new_t >= visited[new_x]:
            continue
        visited[new_x] = new_t
        dq.append((new_x, new_t))
print(min_t)