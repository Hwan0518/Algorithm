from collections import defaultdict
sList = list(input().strip())
sList.sort()
sSet = set(tuple(sList))
sDict = defaultdict(int)
for s in sSet:
    sDict[s] = sList.count(s)
sDictKeys = sorted(sDict.keys())
answer = []
single = []
for s in sDictKeys:
    cnt = sDict[s]
    if cnt%2:
        answer.append(s*((cnt-1)//2))
        single.append(s)
    else:
        answer.append(s*(cnt//2))

if len(single)>1:
    print("I'm Sorry Hansoo")
else:
    print(''.join(answer),end="")
    print(single.pop(),end="") if single else print(end="")
    print(''.join(answer[::-1]))
