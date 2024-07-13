'''
Condition
    - TL: 1s
    - 

Goals
    - 적록색약 x 구역 개수와, 적록색약 o 구역 개수를 출력

Approach
    - bfs, 섬찾기 문제
    - bfs를 두 번 돌려보자
    - 시작은 아무데서나 해도 되니까 0,0에서 출발, visited로 제어하면 됨
'''
from sys import stdin
from collections import deque
input = stdin.readline

n = int(input())
mapp = [list(input().strip()) for _ in range(n)]
dr = [-1,1,0,0]
dc = [0,0,-1,1]

def select_section(rg_blind:bool, section:str):
    if rg_blind:
        if section == "R" or section == "G":
            cur_section = {"R", "G"}
        else:
            cur_section = {"B"}
    else:
        cur_section = {section}
    return cur_section

def bfs(visited:list[list], rg_blind:bool):
    cnt = 0
    for r in range(n):
        for c in range(n):
            if visited[r][c]:
                continue
            cnt +=1
            visited[r][c] = True
            section = select_section(rg_blind, mapp[r][c])            
            dq = deque()
            dq.append((r,c))
            while dq:
                cr,cc = dq.popleft()
                for i in range(4):
                    nr,nc = cr+dr[i], cc+dc[i]
                    if not (0<=nr<n and 0<=nc<n) or visited[nr][nc] or mapp[nr][nc] not in section:
                        continue
                    visited[nr][nc] = True
                    dq.append((nr,nc))
    return cnt

def main():
    not_rg_blind = bfs([[False]*n for _ in range(n)], False)
    rg_blind = bfs([[False]*n for _ in range(n)], True)
    return not_rg_blind, rg_blind

print(*main())