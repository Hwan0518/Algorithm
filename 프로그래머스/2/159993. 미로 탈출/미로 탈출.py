'''
- 그래프 문제
- start -> 레버 -> 출구 까지 걸리는 시간중 최단 시간을 찾아라
- 탈출할 수 없다면 -1을 return 하여라
- 가로 세로를 n,m이라 했을 때, 5<=n,m<=100
    >>> TL을 1s라 생각하면, n^3도 가능. 브루트포스라 봐도 될 것 같음

>>> dfs/bfs를 활용해보자
1. dfs
    끝까지 갔을 때 길이 막혀있다면 시간적으로 손해를 많이 볼 듯
2. bfs
    i) visited와 시간을 들고다니면 쉽게 해결될 것 같음
    ii) lever 상태관리 추가 
'''
from collections import deque

def bfs(n,m,maps,stt):
    dq = deque()
    dq.append(stt)
    # stt에서 bfs를 시작
    while dq:
        cr,cc,cur_time,visited,lever = dq.popleft()
        # 상하좌우 탐색
        for i in range(4):
            nr = cr + dr[i]
            nc = cc + dc[i]
            # 범위를 벗어나거나, X이거나, 방문처리된 곳이라면 -> continue
            if not(0<=nr<n and 0<=nc<m) or maps[nr][nc] =="X" or visited[nr][nc]:
                continue
            val = maps[nr][nc]
            # 새로운곳이 레버라면 -> 활성화 시키고 visited 초기화
            if val == "L":
                newVisited = [[False]*m for _ in range(n)]
                newVisited[nr][nc] = True
                dq.append([nr,nc,cur_time+1,newVisited,True])
            # 새로운곳이 도착점이고, 레버가 활성화된 상태라면 -> 최솟값 갱신
            elif maps[nr][nc] == "E" and lever:
                return cur_time+1
            # 모두 해당되지 않는다면 -> 방문처리 후 이동
            else:
                visited[nr][nc] = True
                dq.append([nr,nc,cur_time+1,visited,lever])
    return -1

def solution(maps):
    global dr,dc
    answer = 0
    # 전처리 -> n,m,stt,방문처리,레버,방향을 설정
    n = len(maps)
    m = len(maps[0])
    visited = [[False]*m for _ in range(n)]
    lever = False
    for i in range(n):
        for j in range(m):
            if maps[i][j] == "S":
                stt = [i,j,0,visited,lever]
    dr = [-1,1,0,0]
    dc = [0,0,-1,1]
    
    # bfs 시작
    answer = bfs(n,m,maps,stt)
    return answer