'''
요구사항
    - TL : 1s
    - . = 빈공간, # = 울타리, o=양, v=늑대
    - 3<=r,c<=250
    - 상하좌우 울타리없이 연결되어있으면 같은 영역
    - 탈출칸은 어떤 영역에도 속하지 않음(울타리가 아니다)
    - 한 영역에서,
        - 양의 수 > 늑대 수 = 양이 이기고 늑대를 우리에서 쫓아냄
        - 양의 수 <= 늑대 수 = 늑대가 이기고 그 영역의 모든 양을 먹음
    - 맨 처음에는 모든 양과 늑대가 마당 안 영역에 존재
    - 아침이 도달했을 때, 살아남은 양과 늑대의 수는?
    
접근
    - 구현(시뮬레이션)

1. 구현
    - bfs로 마당 모두를 탐색(visited로 중복 관리)
    - 한 영역을 탐색할때 양의 수와 늑대의 수를 파악
        - 탐색이 끝났을 때, 양의 수와 늑대의 수를 파악해서 정답을 갱신
    - 탈출 칸을 딱히 예외처리 할 필요는 없어보임
'''
from collections import deque
from sys import stdin
input = stdin.readline

def input_data():
    r,c = map(int,input().split())
    mapp = [list(input().strip()) for _ in range(r)]
    return r, c, mapp

def bfs(r, c, mapp):
    global sheep, wolf
    visited = [[False]*(c) for _ in range(r)]
    for c_row in range(r):
        for c_col in range(c):
            # 방문했다면 continue
            if visited[c_row][c_col]:
                continue
            # 방문처리
            visited[c_row][c_col] = True
            dq = deque()
            dq.append((c_row,c_col))
            # 양의 수와 늑대의 수
            c_sheep = 0
            c_wolf = 0
            while dq:
                cr,cc = dq.popleft()
                for i in range(4):
                    nr = cr +dr[i]
                    nc = cc +dc[i]
                    # 조건 확인
                    if not(0<nr<r and 0<nc<c) or mapp[nr][nc]=="#" or visited[nr][nc]:
                        continue
                    visited[nr][nc] = True
                    animal = mapp[nr][nc]
                    # 양인 경우
                    if animal == "o":
                        c_sheep +=1
                    elif animal == "v":
                        c_wolf +=1
                    # dq에 추가
                    dq.append((nr,nc))
            # 탐색이 끝나면 정답을 업데이트
            if c_sheep > c_wolf:
                sheep += c_sheep
            else:
                wolf += c_wolf

def solution(r, c, mapp):
    global sheep, wolf, dr, dc
    sheep,wolf = 0, 0
    dr = [-1,1,0,0]
    dc = [0,0,-1,1]
    bfs(r,c,mapp)
    return sheep, wolf

print(*solution(*input_data()))