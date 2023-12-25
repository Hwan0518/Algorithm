'''
요구사항
    - TL : 1s
    - 3<=N<=8
    - 주어진 배열에서 값들의 순서를 바꿔서, |A[0]-A[1]| + |A[1]-A[2]| + ... + |A[N-2]-A[N-1]|의 최댓값을 구하여라

접근
    - 가능한 배열의 순서 = 8! = O(40320) 가능
    - 따라서 전체 탐색으로 할 수 있다

1-1. 브루트포스
    - 8중 for문..? 이건 좀..
1-2. 백트래킹
    
'''
def input_data():
    n = int(input())
    arr = list(map(int,input().split()))
    return n, arr

def dfs(n:int, arr:list, cur:list, visited:list):
    global result
    # cur 개수가 n개가 된다면 식을 계산
    if len(cur) == n:
        formula = 0
        for i in range(n-1):
            formula += abs(cur[i] - cur[i+1])
        result = max(result, formula)
        return
    # 그렇지 않다면 cur을 더함
    for i in range(n):
        # 이미 사용한 인덱스라면 continue
        if visited[i]:
            continue
        # 그렇지 않다면 사용
        visited[i] = True
        cur.append(arr[i])
        dfs(n, arr, cur, visited)
        # 이전값으로 리셋
        cur.pop()
        visited[i] = False
    
def solution(n, arr):
    global result
    result = 0
    dfs(n,arr,[],[False]*n)
    return result
    
print(solution(*input_data()))