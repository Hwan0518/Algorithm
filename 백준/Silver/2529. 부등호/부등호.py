'''
- 부등호를 만족하는 최대, 최소를 출력
- 사용되는 숫자는 유니크해야함
- 백트래킹으로하자
'''
def compare(before, cur, sign):
    return before<cur if sign=="<" else before>cur

def dfs(depth, s):
    global max_v, min_v, visited
    # 제일 처음 depth가 k+1이 되었을 때 -> 0부터 오름차순이므로 min이 됨
    # 제일 마지막으로 depth가 k+1이 되었을 때 -> max가 됨
    if depth == k+1:
        if len(min_v) == 0:
            min_v = s
        else:
            max_v = s
        return
    # 0부터 오름차순으로 탐색
    for i in range(10):
        # 방문 여부
        if visited[i]:
            continue
        # s = 내가 선택했던 숫자 집합
        # idx = 0,1인 숫자를 비교 -> idx=0 인 부등호를 사용
        # idx = depth-1, depth 인 숫자를 비교 -> idx=depth 인 부등호를 사용
        # 따라서 -> before = s[-1], cur = i, 현재 부등호 = sign_list[depth-1]
        if depth == 0 or compare(s[-1], str(i), sign_list[depth-1]):
            visited[i] = True
            dfs(depth+1, s+str(i))
            visited[i] = False

def solution():
    global k, sign_list, max_v, min_v, visited
    k = int(input())
    sign_list = input().strip().split()
    max_v = ''
    min_v = ''
    visited = [False]*10    
    dfs(0,"")
    print(max_v)
    print(min_v)

solution()