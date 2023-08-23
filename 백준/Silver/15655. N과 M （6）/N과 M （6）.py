'''
Approach
    - N개중 M개를 고른 수열을 오름차순으로 출력
    - 1<=M<=N<=8

>>> Back-tracking
    : for문으로 작은숫자부터 더해나가며, 수열 길이가 m이 되었다면 return 하는 형식으로 풀어나간다
'''
import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

n,m = map(int,input().split())
arr = sorted(list(map(int,input().split())))
visited = set()
temp = []

def back(size:int, temp:list):
    if size == m:
        result = tuple(sorted(temp))
        if result in visited:
            return
        print(*result,sep=" ")
        visited.add(result)
    
    for i in arr:
        if i in temp:
            continue
        temp.append(i)
        back(size +1, temp)
        temp.pop()
back(0,temp)