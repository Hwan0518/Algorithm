from sys import stdin

N, K = map(int,input().split()) # N : 동전종류, K : 가치의 합
lst=[]
for _ in range(N):
    value = int(stdin.readline().strip())
    if value <= K:
        lst.append(value)

count=0
i = len(lst)-1
while K != 0:
    coin = lst[i]
    if coin <= K:
        K = K-coin
        count +=1
    else:
        i -= 1
print(count)