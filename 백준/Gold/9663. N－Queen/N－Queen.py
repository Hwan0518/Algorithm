n = int(input())

def visitCheck(r1:int):
    for r2 in range(r1):
        if visited[r1] == visited[r2] or abs(r1-r2) == abs(visited[r1]-visited[r2]):
            return False
    return True

def dfs(r:int):
    global answer
    if r == n:
        answer +=1
        return
    # 탐색
    for c in range(n):
        visited[r] = c # r,c에 퀸을 놓음
        if visitCheck(r):
            dfs(r+1)
            
answer = 0
visited = [0] * n
dfs(0)
print(answer)