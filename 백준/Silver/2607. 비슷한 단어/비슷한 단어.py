n = int(input())
fst = list(input().strip())
cnt = 0
for _ in range(n-1):
    f = fst[:]
    no = 0
    for s in input().strip():
        if s in f: f.remove(s)
        else: no +=1
    if len(f) <=1 and no <=1:
        cnt +=1
print(cnt)