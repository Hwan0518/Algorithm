from sys import stdin
input = stdin.readline

def check(fst:list, word:str):
    fst = fst[:]
    no = 0
    for s in word:
        if s in fst: fst.remove(s)
        else: no +=1
    return True if len(fst) <= 1 and no <=1 else False

def main():
    n = int(input())
    fst = list(input().strip())
    cnt = 0
    for _ in range(n-1):
        cnt += 1 if check(fst, input().strip()) else 0
    return cnt

print(main())