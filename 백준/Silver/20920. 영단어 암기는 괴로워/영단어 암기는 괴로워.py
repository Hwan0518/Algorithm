'''
Approach
    - 우선순위대로 정렬
        1. 자주 나오는 단어일수록 앞에
        2. 길이가 길수록 앞에
        3. 사전 순으로 앞에
    - 길이가 M 이상인것만
    - 1<=N<=10만

>>> sort(reverse=True)를 사용
    : 각 단어당 (cnt, length, word)를 list에 넣고 sort(reverse=True)를 해준다
'''
from collections import defaultdict
from sys import stdin
input = stdin.readline

def inputData():
    n,m = map(int,input().split())
    words = [input().strip() for _ in range(n)]
    return n,m,words

def solution(n,m,words:list):
    sorted_words = []
    visited = set()
    dct = defaultdict(int)
    
    # 단어 갯수 파악
    for w in words:
        length = len(w)
        if w in visited or length <m :
            continue
        dct[w] +=1

    # 해쉬에 등록된 단어만 정렬
    for w in dct.keys():
        sorted_words.append((dct[w], len(w), w))
    sorted_words.sort(key=lambda x: [-x[0],-x[1],x[2]])
    
    for sw in sorted_words:
        print(sw[-1])
        
solution(*inputData())
    
    