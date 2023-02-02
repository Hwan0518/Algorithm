'''
Condition
    - TL : 2s(4000만)
      ML : 128(32*10만)
    - 1<=N<=20
    - 무방향 그래프
    - 0<=cost<=2500

Question
    - 최소 도로 개수에서, cost들의 합
    - 불가능한 경우에는 -1 출력

Access
    try_1)
        - 플로이드 워셜을 역으로..?
        - 최소 도로 개수를 구해야 한다
            >>> 각 row(=도시number)에서 자연수 중에 가장 작은 숫자를 먼저 연결
            >>> 이후에 이어지지 못한 도시가 있다면, 서로 연결
            >>> 임의의 두 도시에서 연결하는 cost가 주어진 cost와 다른게 존재한다면 -1 출력,
                아니라면 cost의 합을 출력

    try_2)
        - clone coding : https://2hs-rti.tistory.com/entry/%EB%B0%B1%EC%A4%80-1507%EB%B2%88-%EA%B6%81%EA%B8%88%ED%95%9C-%EB%AF%BC%ED%98%B8-%ED%8C%8C%EC%9D%B4%EC%8D%AC
            >>> 플로이드워셜의 기본 원리인
                arr[i][j] = min(arr[i][j], arr[i][k] + arr[k][j]) 를 잘 이해하면 된다
            >>> i,j,k가 모두 다른 경우를 살펴봤을 때(자기 자신을 거치는게 아닌 경우),
                    1. arr[i][j] == arr[i][k]+arr[k][j]가 존재한다면, 다른 도시를 거쳐왔다는 뜻 >> 도로 개수에 포함x
                    2. arr[i][j] > arr[i][k]+arr[k][j]가 존재한다면, 구현할 수 없음 >> -1을 출력
'''
from sys import stdin
input = stdin.readline

#define function
def make_graph():
    # 강호가 구한 최소 이동 시간
    graph=[list(map(int,input().split())) for _ in range(n)]
    # 도로의 개수가 최소가 되는 도로를 True로 표시
    min_cost = [[True for _ in range(n)] for _ in range(n)]
    return graph,min_cost

def Reverse_Floyd():
    graph,min_cost = make_graph()
    #플로이드 워셜 역으로 진행
    result = 0
    for i in range(n):
        for j in range(n):
            for k in range(n):
                if i==j or i==k or j==k:
                    continue
                # 다른 도시를 거쳐서 간 경우
                if graph[i][j] == graph[i][k]+graph[k][j]:
                    min_cost[i][j] = False
                # 불가능한 경우
                if graph[i][j] > graph[i][k]+graph[k][j]:
                    return -1
    for i in range(n):
        for j in range(i,n): # 시작을 i에서 해야지 중복되지 않음
            if i==j:
                continue
            if min_cost[i][j]:
                result += graph[i][j]   
    return result

#main
n=int(input())
print(Reverse_Floyd())

