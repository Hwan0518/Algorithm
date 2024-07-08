from collections import defaultdict

n = int(input())
thr = list(map(int,input().split()))
t_types = defaultdict(int)
max_cnt = 0

r = 0
cnt = 0
for l in range(n):
    while r < n:
        if thr[r] not in t_types and len(t_types) > 1:
            break
        t_types[thr[r]] +=1
        cnt +=1
        r +=1
    max_cnt = max(max_cnt, cnt)
    cnt -=1
    t_stt = thr[l]
    t_types[t_stt] -=1
    if t_types[t_stt] == 0:
        t_types.pop(t_stt)

print(max_cnt)