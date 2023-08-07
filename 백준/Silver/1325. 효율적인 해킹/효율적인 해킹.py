'''
Approach
    - n개의 컴퓨터
    - A가 B를 신뢰하면, B를 해킹시 A도 해킹가능
    - 1<=n<=1만, 1<=m<=10만
    - 방향 그래프
    
>>> BFS로 해결하자
    : 방향그래프이므로, B에서 A를 따라서 쭈욱 확인하면 된다
      visit을 매번 초기화 시켜주면 될듯

>>> visit을 매번 초기화 시켜주면 안되고, 자식노드에 한번이라도 들어갔다면 초기화 해야한다

>>> 메모이제이션을 같이 써야할 것 같은데..?
    : 한번 방문해서 끝까지 파악했다면 저장해놓고 계속 사용!
'''
from sys import stdin
from collections import deque
input = stdin.readline

def inputData():
    n,m = map(int,input().split())
    graph = [[] for _ in range(n+1)]
    for _ in range(m):
        a, b = map(int,input().split())
        graph[b].append(a)
    return n, graph

def solution(n, graph):
    hacking = {}
    maxCnt = 0
    for stt in range(1,n+1):
        visited = [False]*(n+1)
        visited[stt] = True
        cnt = 1
        dq = deque()
        dq.append(stt)
        # dq가 빌때까지 계속
        while dq:
            cur = dq.popleft()
            for neighbor in graph[cur]:
                if visited[neighbor]:
                    continue
                cnt +=1
                dq.append(neighbor)
                visited[neighbor] = True
        if cnt in hacking:
            hacking[cnt].append(stt)
        else:
            hacking[cnt] = [stt]
                
    maxCnt = max(hacking)
    answer = sorted(hacking[maxCnt])
    return answer

print(*solution(*inputData()), sep=' ')