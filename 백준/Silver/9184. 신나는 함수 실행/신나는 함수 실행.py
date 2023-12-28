'''
요구사항
    - TL : 1s
    - -50 <= a,b,c <=50
    - w(a,b,c) 함수
        - a,b,c중 하나라도 0 이하라면 1을 return
        - a,b,c중 하나라도 20보다 크다면, w(20,20,20)을 return
        - a<b<c라면 w(a, b, c-1) + w(a, b-1, c-1) - w(a, b-1, c)를 return
        - 이외의 경우는 w(a-1, b, c) + w(a-1, b-1, c) + w(a-1, b, c-1) - w(a-1, b-1, c-1)을 return
        
    - 주어진 값에 대해 w(a,b,c)를 출력하여라

접근
    - 그대로 구현하면 시간이 오래걸리므로 시간을 단축시켜야함
    - 메모이제이션을 사용하자

1. dfs
    - 메모이제이션을 사용하기 위해 w(a,b,c) 값을 저장해둘 배열을 만들자(3차원)
'''
import sys
input = sys.stdin.readline

def input_data():
    a,b,c = map(int,input().split())
    if a==-1 and b==-1 and c==-1:
        a = 1e9
    return a,b,c

def dfs(a,b,c):
    # a,b,c중 하나라도 0 이하라면 1을 return
    if a<=0 or b<=0 or c<=0:
        return 1
    # 저장되지 않은 경우 직접 구현
    if not dp[a][b][c]:
        # a,b,c중 하나라도 20보다 크다면, w(20,20,20)을 return
        if a>20 or b>20 or c>20:
            dp[a][b][c] = dfs(20,20,20)
        # a<b<c라면 w(a, b, c-1) + w(a, b-1, c-1) - w(a, b-1, c)를 return
        elif a<b and b<c:
            dp[a][b][c] = dfs(a,b,c-1) + dfs(a,b-1,c-1) - dfs(a,b-1,c)
        # 이외의 경우는 w(a-1, b, c) + w(a-1, b-1, c) + w(a-1, b, c-1) - w(a-1, b-1, c-1)을 return
        else:
            dp[a][b][c] = dfs(a-1,b,c) + dfs(a-1,b-1,c) + dfs(a-1,b,c-1) - dfs(a-1,b-1,c-1)
    # dp값을 return
    return dp[a][b][c]

def solution():
    global dp
    dp =[[[0]*101 for _ in range(101)] for _ in range(101)]
    # input값이 -1,-1,-1일때를 제외하고 계속 탐색
    a,b,c = input_data()
    while a < 1e9:
        result = dfs(a, b, c)
        print(f"w({a}, {b}, {c}) = {result}")
        a,b,c = input_data()
    return

solution()