'''
Condition
    - TL : 1s(2000만)
      ML : 256(64*100만)
    - 1<=n<=200, 1<=k<=1000(바이러스 종류)
    - 0<=s<-1만, 1<=x,y<=n

Question
    - s초 이후, (x,y)에 존재하는 바이러스의 종류를 출력하여라
      (x,y)에 바이러스가 없다면 0을 출력한다

Access
    - 예상 분류 : 구현
      실제 분류 : 구현

    try_1)
        - 필요 기능 : 바이러스 증식
        
            - 바이러스 증식 : 번호가 낮은 바이러스부터 시작해서 모든 바이러스 위치를 탐색,
                           각 위치에서 상하좌우가 경계를 넘어가지 않고 비어있다면 증식
                           
                        

'''
from collections import deque
from sys import stdin
input = stdin.readline




#define function
def multiply():
    cntVirus = 0
    for type in range(1,k+1):
        cntVirus += len(virus[type])
        
        # 증식 시작
        for (cr,cc) in virus[type][:]:
            for i in range(4):
                nr,nc = cr+dr[i], cc+dc[i]
                # 경계 및 바이러스 유무 확인
                if not(1<=nr<=n and 1<=nc<=n) or mapp[nr][nc]:
                    continue
                mapp[nr][nc] = type
                virus[type].append((nr,nc))
                cntVirus +=1
    
    # virus의 총 개수를 리턴 >> 더이상 증식할 칸이 남아있나 확인하기 위한 장치
    return cntVirus



def solution():
    # 바이러스 종류 입력
    for type in range(1,k+1):
        virus[type] = []
        
    # 바이러스 위치 입력
    for r in range(1,n+1):
        data = [0] + list(map(int,input().split()))
        mapp.append(data)
        for c in range(1,n+1):
            type = mapp[r][c]
            if type:
                virus[type].append((r,c))
    
    # s,x,y 입력
    s,x,y = map(int,input().split())
    
    # s초동안 바이러스 증식
    for _ in range(s):
        cntVirus = multiply()
        if cntVirus == n*n:
            break
    
    return mapp[x][y]
        
        
                


#main
n,k = map(int,input().split())
mapp = [[0 for _ in range(n+1)]]
virus = {}
dr = [-1,1,0,0]
dc = [0,0,-1,1]
print(solution())