'''
Condition
    - TL : 1s(2000만)
      ML :
    - 1<= N,M <= 8
    - cctv는 최대 8개까지 가능하다

Question
    - cctv
        1번 : 한쪽 방향
        2번 : 양쪽으로 두 방향
        3번 : 직각으로 두 방향
        4번 : 세 방향
        5번 : 네 방향
    - 벽 = 6
    - 빈 칸 = 0
    >>> cctv 방향을 정해서, 사각 지대의 최소 크기를 구하여라

Access
    - 예상 분류 : 
      실제 분류 : 구현

    try_1)
        - 백트래킹
            - 최대 O(4**8)
            - 사각지대 표시 어떻게 할건데?
                >>> 이중리스트 vs 집합 : 집합이 빼고 넣고 계산하기가 편함
            
            - local 변수
                canWatch(Set) : 감시할 수 있는 구역을 나타내는 집합
                cur(int) : 현재 작동시킬 cctv의 idx
            - global 변수
                n,m    
                cctv(List) : cctv의 위치와 종류를 나타냄
                mapp(List) : 전체적인 map 상황을 나타냄
                blindSpot(list) : 사각지대 크기가 담기는 리스트
            
            1. back(mapp,cctv,canWatch,cur,visited)로 백트래킹
            2. cctv를 완전탐색
                > 각 cctv마다 for문을 사용해서 네 방향으로 돌림
                > mapp에서 경계 or 벽을 만나기 전까지를 모두 canWatch에 넣음
                > 이후 cur+1을 backtracking
            3. cur==sizeofCCTV라면, 사각지대 크기를 blindSpot에 추가
                사각지대크기 = n*m - cctv개수 - canWatch크기 - 벽의개수
        >>> fail. temp==canWatch인 경우, temp를 비워줘야한다
            다른 cctv지만 같은곳을 감시하는 경우, d_update과정에서 모두 사라져버리기 때문
        
    try_2)
        >>> line80 추가, fail
        
    try_3)
        >>> temp와 canWatch를 list로 바꿔준다
'''
from sys import stdin
input = stdin.readline



#define function
def watching(r,c,i):
    temp = set()
    while (0<=r<n and 0<=c<m) and mapp[r][c] != 6 :
            if (r,c) not in cctv:
                temp.add((r,c))
            r += dr[i]
            c += dc[i]
    return temp


def back(canWatch:set,cur):
    global result
    # end condition
    if cur == len(cctv):
        sizeOfBlind = n*m - len(cctv) - len(canWatch) - Wall
        blindSpot.append(sizeOfBlind)
        return
    
    (r,c) = cctv[cur]
    t = mapp[r][c]
    for i in range(4):
        fstR,fstC = r+dr[i], c+dc[i]
        temp = watching(fstR,fstC,i)
        # temp와 canWatch의 교집합이 있는 경우, 빼주어야한다
        temp = temp - canWatch
        
        # 종류에 따라서 i방향으로 경계 or 6을 만날때까지 감시
        match t:
            # t==1이라면 바로 다음으로 백트래킹
            case 1:            
                canWatch.update(temp)
                back(canWatch,cur+1)
                canWatch.difference_update(temp)

            # t==2라면 반대 방향으로 한번 더 탐색하고 백트래킹 
            case 2:
                i_2 = (i+1)%2 if i<2 else (5-i)%5
                scdR,scdC = r+dr[i_2], c+dc[i_2]
                temp.update(watching(scdR,scdC,i_2))
                temp = temp-canWatch
                canWatch.update(temp)
                back(canWatch,cur+1)
                canWatch.difference_update(temp)

            # t==3이라면 수직 방향으로 한번 더 탐색하고 백트래킹
            case 3:
                i_3 = (i+2)%4 if i<2 else 3%i
                trdR,trdC = r+dr[i_3], c+dc[i_3]
                temp.update(watching(trdR,trdC,i_3))
                temp = temp-canWatch
                canWatch.update(temp)
                back(canWatch,cur+1)
                canWatch.difference_update(temp)
            
            # t==4라면, t==2,3일때를 합침. 즉 방향 두번을 더 탐색
            case 4:
                i_2 = (i+1)%2 if i<2 else (5-i)%5
                i_3 = (i+2)%4 if i<2 else 3%i
                temp.update(watching(r+dr[i_2],c+dc[i_2],i_2))
                temp.update(watching(r+dr[i_3],c+dc[i_3],i_3))
                temp = temp-canWatch
                canWatch.update(temp)
                back(canWatch,cur+1)
                canWatch.difference_update(temp)
            
            # t==5라면, 모든 방향을 탐색
            case 5:
                for i_5 in range(4):
                    if i_5 != i:
                        temp.update(watching(r+dr[i_5],c+dc[i_5],i_5))
                temp = temp-canWatch
                canWatch.update(temp)
                back(canWatch,cur+1)
                canWatch.difference_update(temp)
            
                
def findCCTV():
    cctv, mapp, Wall = [],[], 0
    for r in range(n):
        data = list(map(int,input().split()))
        mapp.append(data)
        for c in range(m):
            type = data[c]
            if type>0 and type<6:
                cctv.append((r,c))
            elif type ==6:
                Wall +=1
            
    return cctv, mapp, Wall


#main
n,m = map(int,input().split())
cctv, mapp, Wall = findCCTV()
blindSpot = []
canWatch = set()
dr = [-1,1,0,0]
dc = [0,0,-1,1]
result=0
if cctv:
    back(canWatch,0)
    print(min(blindSpot))
else:
    print(n*m-Wall)
    

