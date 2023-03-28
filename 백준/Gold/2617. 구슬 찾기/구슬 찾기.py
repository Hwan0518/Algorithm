'''
Condition
    - TL : 1s(2000만)
      ML : 128(32*100만)
    - 1<=n<=99, 1<=M<=n(n-1)/2

Question
    - m개의 무게 관계를 보고, 무게가 중간이 될 수 없는 구슬의 개수를 구하여라

Access
    - 예상 분류 : 위상정렬
      실제 분류 : 그래프, 깊이우선탐색, 플로이드-워셜

    try_1) 위상정렬
        - 진입차수밖에 없는 node와, 진출차수밖에 없는 node를 구한다
        
    try_2) 위상정렬 + 유니온파인드
        - 부모노드가 같은 노드중에 제일 무겁다면 result로 삼는다

    try_3) clone coding : https://campkim.tistory.com/17
        - dfs로 해결
          : 구슬을 전체 탐색 하여, 
            해당 구슬보다 크거나 작은 구슬이 (n+1)/2개를 넘어가면 중간값이 될 수 없다

'''
from collections import deque
from sys import stdin
input = stdin.readline




#define function
def dfs(arr, stt, visited):
    cnt = 0
    dq = deque()
    dq.append(stt)
    visited[stt] = True
    while dq:
        current = dq.popleft()
        #current에 연결된 구슬들을 확인(더 무겁거나 가벼운 구슬들)
        for i in arr[current]:
            if visited[i]:
                continue
            cnt +=1
            visited[i] = True
            dq.append(i)
    return cnt
        

def solution():
    #구슬들의 관계를 input
    for i in range(m):
        heavy,light = map(int,input().split())
        bigger[light].append(heavy)
        smaller[heavy].append(light)
    #dfs 시작
    answer = 0
    for i in range(1,n+1):
        visited = [False for _ in range(n+1)]
        # i보다 더 무겁거나 가벼운 구슬이 mid개 이상이면 answer+1
        if dfs(bigger,i,visited) >=mid:
            answer +=1
        if dfs(smaller,i,visited)>=mid:
            answer +=1
        
    return answer





#main
n,m = map(int,input().split())
bigger = [[] for _ in range(n+1)]   # 해당 idx보다 큰 구슬
smaller = [[] for _ in range(n+1)]  # 해당 idx보다 작은 구슬
mid = (n+1)//2                      # 구슬 개수의 중간값, bigger와 smaller가 이 값을 넘어가면 안된다

print(solution())