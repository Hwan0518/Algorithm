from collections import defaultdict

def printAnswer(answer, single):
    if len(single)>1:
        print("I'm Sorry Hansoo")
    else:
        print(''.join(answer),end="")
        print(single.pop(),end="") if single else print(end="")
        print(''.join(answer[::-1]))

def calc():
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
    return answer, single

def solution():
    global sList, sSet, sDict
    sList = sorted(list(input().strip()))
    sSet = set(tuple(sList))
    sDict = defaultdict(int)
    # 계산
    answer, single = calc()
    # 정답 출력
    printAnswer(answer, single)

solution()