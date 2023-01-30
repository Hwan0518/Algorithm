'''
Condition
    - TL : 2s(4000만)
      ML : 512mb(128*10만)
    - 1<=N<=8, 1<=S<=300, 1<=W<=300
    - 충돌 >>> 각 계란에서는
                내구도 -= 상대계란 무게
                내구도0이면 >> 계란이 깨짐
    - 진행과정
        1. 가장 왼쪽의 계란을 든다
        2. if 손에 든 계란이 깨진상태 >> 넘어감
           elif 나머지 계란이 다 깨진상태 >> 넘어감
           else >> 깨지지 않은 다른 계란중 하나와 충돌
                   이후에 손에 든 계란을 원래자리로 놓고 넘어감
        3. 다음 계란을 들고 2번을 진행
           만약 맨 오른쪽 계란을 들었다면 과정 종료
    

Question
    - 깰 수 있는 계란의 최대 갯수는?

Access
    try_1)
        - dp 혹은 백트래킹으로 접근하면 될 것 같음
            >>> 소문제로 분리할 수 없으므로 백트래킹으로 접근해봄
        - 백트래킹 time complexity : 중복이 가능한 경우 O(N^N)
                                    중복이 불가능한 경우 O(N!)
            >>> 중복이 가능하므로 O(N^N) = O(8^8) = 1700만으로 통과!
        - 깨진 달걀을 0, 깨지지 않은 달걀을 1로 한다
'''

from sys import stdin
input = stdin.readline

#define function
def back(depth):
    #end condition
    if n==1:
        broken_list.append(0)
    if depth == n:
        broken=0
        for i in range(n):
            if eggs[i][0] <=0:
                broken +=1
        broken_list.append(broken)
        return
    #start searching
    c_idx = depth
    for n_idx in range(n):
        if c_idx == n_idx:
            continue
        cs,cw = eggs[c_idx]
        ns,nw = eggs[n_idx]
        # 현재 달걀이 깨진 상태
        if cs<=0:
            back(depth+1)
            return
        # 나머지 달걀이 모두 깨진 상태
        flag = True
        for i in range(n):
            if i != c_idx and eggs[i][0]>0: #하나라도 안깨진게 존재한다면
                flag = False
                break
        if flag:
            broken_list.append(n-1) #자기말고 모두 깨짐
            return
        # 충돌 진행
        if ns<=0:
            continue
        eggs[c_idx][0] -= nw
        eggs[n_idx][0] -= cw
        back(depth+1)
        eggs[c_idx][0] += nw
        eggs[n_idx][0] += cw
    return

#main
n=int(input())
eggs = [list(map(int,input().split())) for _ in range(n)]
broken_list = []
back(0)
print(max(broken_list))
