'''
- TL : 2s
- 1<= m,n <= 250
- 글자는 1, 아니면 0
- 1이 상,하,좌,우,대각선 으로 연결되어있으면 한 개의 글자
총 글자 개수를 구하여라

알고리즘 분류 : BFS
시간복잡도 : O(N^2)

<접근>
- 시간 복잡도 O(N^2)까지도 가능
- 그래프 탐색으로, 섬 구하기 문제로 보면 됨
- BFS로 구현하면 될 것 같음

1. BFS
    - 입력 함수 구현
        - 그래프 생성
        - 1의 위치 리스트 생성
    - bfs 실행 함수 구현
        - 방문 처리함수(visited)생성
        - 방문한 1의 위치를 제외하고 모두 탐색
        - 탐색을 한번 시작할 때마다 섬 갯수 cnt +1
        - 모든 1의 위치 탐색을 끝내고 cnt를 return
    - 메인 함수 구현
        - 섬 개수를 return
'''
from sys import stdin
from collections import deque
input = stdin.readline

def input_data():
    graph = []
    stt_list = []
    r,c = map(int,input().split())
    for cr in range(r):
        graph.append(list(map(int,input().split())))
        for cc in range(c):
            if graph[-1][cc] == 1:
                stt_list.append((cr,cc))
    return r, c, graph, stt_list

def bfs(r, c, graph, stt_list):
    visited = [[False]*c for _ in range(r)]
    cnt = 0
    # 모든 1 위치를 탐색
    for stt in stt_list:
        # 이미 방문한 1이라면 continue
        stt_r, stt_c = stt[0], stt[1]
        if visited[stt_r][stt_c]:
            continue
        # 방문한 적 없다면 탐색 시작
        cnt +=1
        visited[stt_r][stt_c] = True
        dq = deque()
        dq.append((stt_r,stt_c))
        while dq:
            cr, cc = dq.popleft()
            # 상,하,좌,우,대각 모두 탐색
            for i in range(8):
                nr = cr+dr[i]
                nc = cc+dc[i]
                # 조건확인
                if not(0<=nr<r and 0<=nc<c) or visited[nr][nc] or graph[nr][nc] == 0:
                    continue
                # 조건을 만족하면 방문처리하고 queue에 추가
                visited[nr][nc] = True
                dq.append((nr,nc))
    # 모든 방문이 끝나면 cnt를 return
    return cnt

def solution(r, c, graph, stt_list):
    global dr,dc
    # 상,하,좌,우,오른대각,왼대각
    dr = [-1,1,0,0,-1,1,-1,1]
    dc = [0,0,-1,1,1,1,-1,-1]
    # 탐색
    cnt = bfs(r, c, graph, stt_list)
    return cnt
            
print(solution(*input_data()))