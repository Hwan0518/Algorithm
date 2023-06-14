'''
Condition
    - TL : 2s
      ML :
    - n<=200, m<=1000

Question
    - 

Access
    - 예상 분류 : 플로이드워셜
      실제 분류 : 그래프

    try_1)
        - 어떻게든 여행 계획에 맞도록 도시들이 연결되기만 하면 되므로
          플로이드 워셜로 연결되는지만 보면 될듯 싶음
        - 플로이드 워셜
            >>> O(N^3) == O(200^3) == 80만
            >>> 75% 틀림..
            
'''
from sys import stdin
input = stdin.readline


#Define function
def createGraph():
    graph = [[0]*(n+1) for _ in range(n+1)]
    for stt in range(1,n+1):
        for city,connection in enumerate(map(int,input().split())):
            if connection:
                graph[stt][city+1] = 1
                graph[city+1][stt] = 1    
    # 자기 자신으로도 갈 수 있도록 처리
    for self in range(1,n+1):
        graph[self][self] = 1
    return graph


def floydWarshall():
    for waypoint in range(1,n+1):
        for stt in range(1,n+1):
            for end in range(1,n+1):
                # stt와 end가 이미 연결되어있다면 continue
                if graph[stt][end]:
                    continue
                cost_1 = graph[stt][waypoint]
                cost_2 = graph[waypoint][end]
                # cost_1과 cost_2가 0보다 크다면 stt,end는 연결되어있음
                if cost_1 and cost_2:
                    graph[stt][end] = cost_1 + cost_2
    
    # 여행 계획대로 갈 수 있는지 확인
    for i in range(m-1):
        curCity = schedule[i]
        nextCity = schedule[i+1]
        # 연결 확인
        if graph[curCity][nextCity]:
            continue
        else:
            return "NO"
    return "YES"
    
                


#Main
n=int(input())
m=int(input())
graph = createGraph()
schedule = list(map(int,input().split()))
print(floydWarshall())