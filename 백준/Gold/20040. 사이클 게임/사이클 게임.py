'''
Condition
    - TL : 1s (2000만)
      ML : 512 (128*100만)
    - 3<=n<=50만, 3<=m<=100만
    
    
Question
    - 입력된 선분을 차례대로 나열해보고, 사이클이 존재한다면 몇번째에 존재하는지를,
      사이클이 존재하지 않는다면 0을 출력하여라


Access
    try_1)
        - 예상 분류 : union-find
          실제 분류 : 분리집합(== 서로소집합 == union-find)



'''
from sys import stdin
input = stdin.readline



#define function
def findParent(i):
    if parent[i] != i:
        parent[i] = findParent(parent[i]) # root node를 찾을때까지 갱신
    return parent[i]    


def unionParent(a,b):
    a = findParent(a)
    b = findParent(b)
    parent[max(a,b)] = min(a,b) # 더 작은쪽을 부모로 설정한다


def solution():
    for i in range(1,m+1):
        a,b = map(int,input().split())
        
        #사이클 판별 : root node가 같다면 사이클이므로 i를 반환
        if findParent(a) == findParent(b):
            return i
        
        #root node가 같지 않다면, 합집합으로 두 노드를 연결
        unionParent(a,b)
        
    #m번째 차례까지 진행해도 사이클이 없다면 0을 리턴
    return 0




#main
n,m = map(int,input().split())
parent = [i for i in range(n)]
print(solution())
