'''
Condition
    - TL : 3s(6000만)
      ML : 256(64*100만)
    - 프로젝트 팀원 수 제한 x
    - 자기자신만 선택 가능
    - 2<=n<=10만

Question
    - 팀에 속하지 못하는 학생 수는?

Access
    - 예상 분류 : union-find
      실제 분류 : dfs, 그래프

    try_1)
        - 방문하지 않은 노드들을 출발지로 설정해서 전체탐색해본다
        - 이때 출발지와 도착지가 동일하다면 팀이 결성된다
    
    try_2)
        참고 : https://www.acmicpc.net/board/view/70227
        - stt가 포함되지 않는 싸이클이 발생하는것도 방문처리한다
        
    try_3)
        클론코딩 : https://claude-u.tistory.com/435
'''
import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline


#define function
def dfs(cur):
    global result,cycle
    visited.add(cur)
    cycle.append(cur)
    neighbor = choice[cur]
    
    if neighbor in visited:
        if neighbor in cycle:
            idx = cycle.index(neighbor)
            result += cycle[idx:]
        return
    dfs(neighbor)


def solution():        
    global cycle
    for stt in range(1,n+1):
        if stt not in visited:
            cycle = []
            dfs(stt)
    return n-len(result)



#main
t = int(input())
for _ in range(t):
    n=int(input())
    choice = [0] + list(map(int,input().split()))
    visited = set()
    result = []
    print(solution())