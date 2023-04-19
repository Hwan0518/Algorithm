'''
Condition
    - TL : 1s (약 2000만)
      ML : 512mb
    - m = 질량, d = 방향, s = 속력
    - 각 모서리는 연결되어있다

Question
    - k번 이동 후 남아있는 파이어볼의 합을 출력

Access
    - 예상 분류 : 구현
      실제 분류 : 구현

    try_1)
        - 필요한 기능 : 이동, 폭발(병합 & 분리 & 방향설정 & 소멸)
            1. 공통
            : mapp - 파이어볼의 질량,속력,방향,개수(m,s,d,isSame) 정보를 담고있음
              fireball - 파이어볼이 존재하는 위치(r,c)를 담고있음, set구현
            
            2. move
            : 파이어볼의 위치를 전체탐색. 이때는 pop을 사용하여 다 비워준다
              각 위치에서 d방향으로 s만큼 이동
              이때 각 모서리는 연결되어있음 
                    r 설정
                        d=0,1,7 : (cr-s)%r
                        d=3,4,5 : (cr+s)%r
                        d=2,6   : cr
                    c 설정 
                        d=1,2,3 : (cc+s)%c
                        d=5,6,7 : (cc-s)%c
                        d=0,4   : cc
                - merge 
                    : 이동한 칸으로 m,d,s를 병합
                      mapp을 갱신한다
                      파이어볼의 위치를 갱신
                
            3. explosion
                : (질량,속력,방향 설정)
                  파이어볼의 위치를 전체탐색. pop을 사용하여 다 비워준다
                  질량은 m/5, 속력은 m/cnt, 
                  방향은 dir%2:라면 1,3,5,7 그렇지 않다면 0,2,4,6
                  mapp을 갱신한다
            
            4. extinction
                : 모든 파이어볼의 위치를 전체탐색. pop을 사용하여 다 비워준다
                  mapp에서 질량을 확인하여 0이라면 소멸시킨다
                  그렇지 않다면 다시 파이어볼의 위치에 append해준다
                 
                 
    try_2)
        - 소멸 기능은 explosion안에서 한번에 일어난다
        - explosion은 두 개 이상의 파이어볼이 있을 때만 일어난다

'''
from typing import List,Tuple,Set,Deque
from collections import deque
from copy import deepcopy
from sys import stdin
input = stdin.readline




#define function
def readInput():    
    n,M,k = map(int,input().split())
    mapp = [[deque() for _ in range(n)] for _ in range(n)] # m,s,d,even,odd : even과 odd가 포함되었는지를 나타냄
    fireball = set()
    for _ in range(M):
        r,c,m,s,d = map(int,input().split())
        # d가 짝수라면 even=1,odd=0이다. 홀수라면 반대로 적용
        even = 1- d%2
        odd = d%2
        # fireball 위치 저장
        fireball.add((r-1,c-1))
        # mapp에 data 업데이트
        data = [m,s,d,even,odd]
        mapp[r-1][c-1].append(data)
    return n,k,mapp,fireball


def explosion(n:int, locate:Tuple, mapp:List[List[Deque[int]]], fireball:Set[Tuple])->None:
    cr,cc = locate
    # 병합 : (cr,cc)에 존재하는 fireball을 모두 병합
    cData = [0,0,0,0,0]
    cntFireball = len(mapp[cr][cc])
        # 현재 위치의 모든 fireball 정보를 더함
    for _ in range(cntFireball):
        fData = mapp[cr][cc].popleft()
        for i in range(5):
            cData[i] += fData[i]
        
    # 분리 : 병합한 fireball을 네 방향으로 분리
        # 새로운 질량, 속력, 방향 설정
    m,s,d,even,odd = cData
    nm = m//5
    # 질량이 0이라면 소멸
    if nm == 0:
        fireball.remove((cr,cc))
        return
    ns = s//(even+odd)
    nDir = [1,3,5,7] if even and odd else [0,2,4,6]
        # fireball이 4개로 분리
    for i in range(4):
        nd = nDir[i]
        data = [nm,ns,nd,1-nd%2,nd%2]
        mapp[cr][cc].append(data)
    return


def move(n:int, mapp:List[List[Deque[int]]], fireball:Set[Tuple])->None:
    # 파이어볼 전체 탐색
    size = len(fireball)
    temp = set()
    beMove = []
    for _ in range(size):
        cr,cc = fireball.pop()
        cntFireball = len(mapp[cr][cc])
        for _ in range(cntFireball): # >> 문제발생. 한번 더 움직여버림.. fireball처럼 해결해야함
            # 현재 위치의 데이터 확인
            data = mapp[cr][cc].popleft()
            m,s,d,even,odd = data
            
            # 이동 위치 설정
                #1. r방향 설정
            nr,nc = cr,cc
            if d in (0,1,7):
                nr = (cr-s)%n
            elif d in (3,4,5):
                nr = (cr+s)%n
                #2. c방향 설정
            if d in (1,2,3):
                nc = (cc+s)%n
            elif d in (5,6,7):
                nc = (cc-s)%n

            temp.add((nr,nc))
            beMove.append((nr,nc,data))

    # fireball 이동
    fireball.update(temp)
    for movedData in beMove:
        nr,nc,data = movedData
        mapp[nr][nc].append(data)
    return


def solution(n:int,k:int,mapp:List[List[Deque[int]]],fireball:Set[Tuple])->int:
    # k번 이동 시작
    for _ in range(k):
        move(n,mapp,fireball)
        # fireball이 두개 이상이 모여있다면 병합/분리 진행
        for (cr,cc) in deepcopy(fireball):
            if len(mapp[cr][cc])>=2:
                explosion(n,(cr,cc),mapp,fireball)
    
    # 파이어볼 질량 합 출력
    result = 0
    for r,c in fireball:
        size = len(mapp[r][c])
        for i in range(size):
            result += mapp[r][c][i][0]
    return result





#main
Dir = {0:(-1,0), 1:(-1,1), 2:(0,1), 3:(1,1), 4:(1,0), 5:(1,-1), 6:(0,-1), 7:(-1,-1)}
print(solution(*readInput()))
