'''
Condition
    - TL : 1s(약 2000만)
      ML : 128(32*100만)
    - 1<=N<=100
    
Question
    - 뽑힌 정수들의 개수를 출력하고, 뽑힌 정수들을 오름차순으로 출력
    
Access
    try_1)
        - 예상 분류 : dp
          문제 분류 : 그래프 이론, 그래프 탐색, DFS
        
        1. i = 1~n까지 탐색 시작
        2. bfs(i) 시작
            2-1. i를 firstSet에 넣음
            2-2. 두번째 라인에서 i번째 숫자를 secondSet에 넣음
            2-3. secondLine[i]에 방문한 적이 없다면 다시 bfs(secondLine[i])를 시작
            2-4. 방문했던 수라면 fstSet과 secSet을 비교하여 같은경우에만 answer에 대입함
'''


import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline




#define function
def bfs(stt,fstSet:list,secSet:list):
    global answer, visited
    if stt >n:
        return
    
    # stt를 첫번째 집합에 대입하고 방문처리
    fstSet.append(stt)
    visited.add(stt)
    
    # 두번째 줄의 stt번째 숫자를, 두번째 집합에 대입
    secNum = secLine[stt]
    secSet.append(secNum)
    
    # 두번째 줄의 stt번째 숫자에 방문한적이 없다면 bfs 진행
    if secNum not in visited:
        bfs(secNum,fstSet,secSet)
    
    # 방문했다면 fstSet과 secSet을 비교해서 answer에추가
    else:
        fstSet.sort()
        secSet.sort()
        if fstSet==secSet:
            answer.update(fstSet)
        return
            

def solution():
    global visited
    for stt in fstLine:
        fstSet=[]
        secSet=[]
        visited = set()
        bfs(stt,fstSet,secSet)
    return





#main
n=int(input())
fstLine = [i for i in range(1,n+1)]
secLine = [0] + [int(input()) for _ in range(n)]
answer = set()
solution()
answer = list(answer)
answer.sort()
print(len(answer))
print(*answer,sep='\n')