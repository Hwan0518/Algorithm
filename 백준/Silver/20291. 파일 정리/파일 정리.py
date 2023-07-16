'''
Approach
    - 파일을 확장자별로 정리후, 개수를 파악
    - 확장자들을 사전순으로 정렬
    
>>> 해쉬 완탐돌리면 될듯싶음

'''
from collections import defaultdict
from sys import stdin
input = stdin.readline

def inputData():
    n = int(input())
    files = [input().strip() for _ in range(n)]
    return n, files

def solution(n, files):
    dt = defaultdict(int)
    for f in files:
        idx = f.index('.')
        extension = f[idx+1:]
        dt[extension] +=1
    
    exts = sorted(dt.keys())
    for ext in exts:
        print(ext, dt[ext])
    
solution(*inputData())
    
    