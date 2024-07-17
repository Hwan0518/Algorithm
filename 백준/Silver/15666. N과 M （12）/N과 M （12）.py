n,m = map(int,input().split())
nums = list(map(int,input().split()))
visited = set()
result = []
def dfs(seq:list):
    if len(seq) == m:
        sorted_seq = sorted(seq)
        if tuple(sorted_seq) not in visited:
            result.append(sorted_seq[:])
            visited.add(tuple(sorted_seq))
        return
    for i in nums:
        seq.append(i)
        dfs(seq)
        seq.pop()
dfs([])
for r in sorted(result):
    print(*r)