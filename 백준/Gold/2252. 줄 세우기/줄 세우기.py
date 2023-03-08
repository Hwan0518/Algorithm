'''
Condition
    - TL : 2s (4천만)
      ML : 128 (32*100만)
    - 1<=N<=3.2만, 1<=M<=10만
      (N=학생 수, M=비교 횟수)
    
    
    
Question
    - 학생들의 키 순서대로 정렬한 결과를 출력하여라
      답이 여러개라면 아무거나 출력하여라
    
Access
    try_1)
        - 예상 분류 : 정렬, 조합
          문제 분류 : 그래프 이론, 위상 정렬

        - 진입차수만 존재 >> 진입차수 이후에 출력
        - 진출차수만 존재 >> 맨처음 출력
        ** 진입차수가 0 = 더이상 자신 앞에 존재하는게 없다
'''
from collections import deque
from sys import stdin
input = stdin.readline



#define function
def topologySort():
    result = []
    dq = deque()
    
    # 진입차수가 0인 학생들을 root노드로 잡고, dq에 담는다
    for student in range(1,n+1):
        if indegree[student] == 0:
            dq.append(student)
    
    # dq에는 진입차수가 0이 된 학생들만 들어오므로, result에 추가해준다
    while dq:
        curStudent = dq.popleft()
        result.append(curStudent)
        
        # 현재 노드의 진입차수가 0이 되었으므로, 진출 노드로 이동해서 진입차수를 하나 빼준다
        for nextStudent in graph[curStudent]:
            indegree[nextStudent] -= 1
            
            # 진출노드의 진입차수가 0이 되었다면 dq에 삽입한다
            if indegree[nextStudent] == 0:
                dq.append(nextStudent)
    return result



def solution():
    # 각 학생들의 진입차수 설정
    for _ in range(m):
        a, b = map(int,input().split())
        graph[a].append(b)
        
        # b로 들어가는 진입차수 1 증가
        indegree[b] +=1
    
    answer = topologySort()
    return answer




#main
n,m = map(int,input().split())
graph = [[] for _ in range(n+1)]
indegree = [0 for _ in range(n+1)]

print(*solution())