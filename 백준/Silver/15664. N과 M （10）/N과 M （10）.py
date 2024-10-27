import sys
sys.setrecursionlimit(10**6)
def bt(size, arr, ar_visit:set):
    global visited
    if size == m:
        visited.add(tuple(arr))
        return
    for i in range(n):
        num = a[i]
        if (not arr or num >= arr[-1]) and i not in ar_visit and tuple(arr+[num]) not in visited:
            ar_visit.add(i)
            bt(size+1, arr+[num], ar_visit)
            ar_visit.remove(i)

n,m = map(int,input().split())
a = list(map(int,input().split()))
visited = set()
bt(0, [], set())
answer = sorted(list(visited))
for ans in answer:
    print(*ans)