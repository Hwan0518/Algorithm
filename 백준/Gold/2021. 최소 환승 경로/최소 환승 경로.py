'''
Condition
    - TL : 1s
      ML :
    - 1<=n<=10만, 1<=L<=10만

Question
    - 최소 환승횟수를 출력. 도착하지 못한다면 -1을 출력한다

Access
    - 예상 분류 : 플로이드워셜, 다익스트라, 벨만포드
      실제 분류 :

    try_1)
        - n이 10만이므로 플로이드워셜은 안됨
            >>> O(n^3)
        - n=10만, l=10만일때 다익스트라 사용 가능
            >>> O((N+L)logN)
        - 벨만-포드 불가능
            >>> O(V*E)
            
        다익스트라
        - 힙큐를 사용하지 않으면 O(N^2+E)이고,
          힙큐를 사용하면 O((N+E)*logN)이므로 힙큐를 사용해야함
          : O((100만+10만)*log10만))
        
        조건
        - 환승할때 cost +1을 해주면 됨
            : graph에 열차의 번호를 적어주고, 열차의 번호가 달라진다면 cost+1
        - 지하철은 거꾸로도 갈 수 있으므로 무방향 그래프
'''
from heapq import *
from sys import stdin
input = stdin.readline


#Define Function
def createGraph():
    graph = [[] for _ in range(n+1)]
    for train_number in range(l):
        data = list(map(int,input().split()))
        data.pop()
        # edge 저장
        for i in range(len(data)-1):
            graph[data[i]].append((data[i+1],train_number))
            graph[data[i+1]].append((data[i],train_number))
    return graph

def solution():
    distance = [1e9]*(n+1)
    q = []
    # stt가 포함된 모든 노선을 확인
    for stt_data in graph[stt]:
        cur_train = stt_data[1]
        heappush(q, (0,stt,cur_train))
        distance[stt] = 0
        while q:
            cur_dist, node, cur_train = heappop(q)
            if distance[node] < cur_dist:    
                continue
            for data in graph[node]:
                neighbor = data[0]
                n_train = data[1]
                cost = distance[node]
                # 환승했다면 cost +=1
                if cur_train != n_train:
                    cost += 1
                # cost와 현재 값을 비교
                if cost < distance[neighbor]:
                    distance[neighbor] = cost
                    heappush(q, (cost, neighbor, n_train))
    if distance[end] == 1e9:
        return -1
    else:
        return distance[end]            
    
            

#Main
n,l = map(int,input().split())
graph = createGraph()
stt, end = map(int,input().split())
print(solution())