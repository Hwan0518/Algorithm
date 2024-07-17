n, m = map(int, input().split())
nums = sorted(set(map(int, input().split())))

def dfs(seq:list, stt:int):
    if len(seq) == m:
        print(*seq)
        return
    for i in range(stt, len(nums)):
        dfs(seq + [nums[i]], i)

dfs([], 0)
