'''
Condition
    - TL : 1s(2000만)
      ML : 128(32*100만)
    - 5<=n,m<=100

Question
    - 치즈가 모두 녹아 없어지는데 걸리는 정확한 시간은?

Access
    - 예상 분류 :
      실제 분류 : 구현

    try_1)
        - 필요 기능 : 녹아야 하는 치즈 확인기능, 치즈 녹이는 기능
            1. 공통
                beMelt : 녹아야 하는 치즈 위치를 deque로 저장
                visit : 2번에서 bfs할때 방문기록, 이차원 배열로 구성
                cheese : 전체 치즈 위치, set으로 구현 >> while cheese로 계속진행
                
            2. 치즈 확인 기능
                : 현재 치즈위치를 cheese에서 pop해서 확인함
                  이후 현재 치즈에서 상하좌우 확인, 0이 두개 이상 있다면 beMelt에 추가
            
            3. 치즈 녹이는 기능
                : 2번이 끝난 이후, 시간을 +1
                  beMelt에서 pop한 위치를, mapp에서 0으로 바꿈
        
        >>> fail, 치즈로 둘러쌓인 0은 count하지 않아야함
    
    try_2)
        - 치즈 확인 기능에서, 치즈로 둘러쌓인 0은 cntZero를 +1하지 않아야함
            : 가장자리에는 치즈가 올 수 없으므로, 가장자리에서 bfs를 진행했을 때 연결된 0들만 카운트를 해야함
              >>> 현재 카운트 할 수 있는 0만 구하는 기능을 만들어야함
        
        Air : 카운트 할 수 있는 0의 위치, set으로 구현
        findAir : 모든 모서리 > (r=0,c),(r=n,c),(r,c=0),(r,c=m) 에서 bfs를 진행해서 연결된 부분을 구함
                             Air에 들어있지 않다면 add하는 형식으로 구현하면 됨
                  

'''
from typing import List, Deque, Set
from collections import deque
from sys import stdin
input = stdin.readline




#define function
def readData():
    r,c = map(int,input().split())
    mapp = []
    cheese = set()
    # 치즈 위치 추가
    for cr in range(r):
        mapp.append(list(map(int,input().split())))
        for cc in range(c):
            if mapp[cr][cc]:
                cheese.add((cr,cc))
    # r,c,cheese,mapp을 반환
    return r,c,cheese,mapp


def meltingCheese(mapp:List, cheese:Set, beMelt:Deque)->int:
    # beMelt의 치즈를 모두 녹임
    while beMelt:
        mr,mc = beMelt.popleft()
        # 녹은 치즈의 위치를 0으로 갱신
        mapp[mr][mc] = 0
        # cheese에서 (mr,mc)를 제거
        cheese.remove((mr,mc))
    return 1


def check(r:int, c:int, mapp:List, cheese:Set, beMelt:Deque, Air:Set)->None:
    # 현재 치즈 위치에서 인접해있는 0의 개수 확인
    for (cr,cc) in cheese:
        cntZero = 0
        for i in range(4):
            nr,nc = cr+dr[i], cc+dc[i]
            if not(0<=nr<r and 0<=nc<c) or mapp[nr][nc] or (nr,nc) not in Air:
                continue
            cntZero +=1
        # 인접해있는 0이 2개 이상이라면 녹아야 하는 치즈이다
        if cntZero >=2:
            beMelt.append((cr,cc))
    return


def findAir(r:int, c:int, Air:Set, mapp:List)->None:
    visited = set()
    # (r=0 or n,c)일때 연결된 공기
    for cr in [0,r-1]:
        dq=deque()
        for cc in range(c):
            if (cr,cc) in visited:
                continue
            # (cr,cc)부터 탐색 시작
            dq.append((cr,cc))
            Air.add((cr,cc))
            visited.add((cr,cc))
            while dq:
                Cr,Cc = dq.popleft()
                for i in range(4):
                    nr,nc = Cr+dr[i], Cc+dc[i]
                    if not(0<=nr<r and 0<=nc<c) or (nr,nc) in visited or mapp[nr][nc]:
                        continue
                    Air.add((nr,nc))
                    visited.add((nr,nc))
                    dq.append((nr,nc))
    
    # (r, c= 0 or m)일때 연결된 공기
    for cc in [0,c-1]:
        dq=deque()
        for cr in range(r):
            if (cr,cc) in visited:
                continue
            # (cr,cc)부터 탐색 시작
            dq.append((cr,cc))
            Air.add((cr,cc))
            visited.add((cr,cc))
            while dq:
                Cr,Cc = dq.popleft()
                for i in range(4):
                    nr,nc = Cr+dr[i], Cc+dc[i]
                    if not(0<=nr<r and 0<=nc<c) or (nr,nc) in visited or mapp[nr][nc]:
                        continue
                    Air.add((nr,nc))
                    visited.add((nr,nc))
                    dq.append((nr,nc))
    
    
def solution(r:int, c:int, cheese:Set, mapp:List) -> int:
    time = 0
    beMelt = deque()
    Air = set()
    # cheese가 모두 녹을때까지 진행
    while cheese:
        # 현재 카운트 할 수 있는 0를 찾음(공기를 찾음)
        findAir(r, c, Air, mapp)
        # 녹아야 하는 치즈들을 파악함
        check(r, c, mapp, cheese, beMelt, Air)
        # 치즈를 녹임
        time += meltingCheese(mapp, cheese, beMelt)
    # cheese가 모두 녹으면 시간을 return
    return time



#main
dr = [-1,1,0,0]
dc = [0,0,-1,1]
print(solution(*readData()))

