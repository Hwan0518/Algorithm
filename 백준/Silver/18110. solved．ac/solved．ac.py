from sys import stdin
input = stdin.readline
n = int(input())
def realRound(num:int):
    if (num - int(num)) >= 0.5:
        return int(num) +1
    return int(num)
    
if n == 0:
    print(0)
else:
    difficulty = sorted([int(input()) for _ in range(n)])
    exceptSize = realRound(n * 0.15)
    targetNum = difficulty[exceptSize:(n-exceptSize)]
    print(realRound(sum(targetNum)/(n-2*exceptSize)))