'''
conditions
    - TL: 1s
    - 1<=N<=10**5, 1<=M<=10**5

goals
    - 캐릭터의 전투력 범위에 맞는 칭호를 출력

approach
    - 이분탐색
        - 이중 for문 사용시 O(10만*10만)=TLE
        - 캐릭터 전투력 범위 탐색을 이분탐색으로 처리하자
'''
from sys import stdin
input = stdin.readline

def input_data():
    n,m = map(int,input().split())
    titles = []
    for _ in range(n):
        inputs = input().split()
        titles.append([inputs[0], int(inputs[1])])
    return n, m, titles

def binary_search(n:int, power:int, titles:list[list]):
    l=0
    r=n-1
    while l<=r:
        mid = (l+r)//2
        limit = titles[mid][1]
        # mid 감소
        if power <= limit:
            r = mid-1
        # mid 증가
        else:
            l = mid+1
    return titles[l][0]

def main(n:int, m:int, titles:list[list]):
    for _ in range(m):
        print(binary_search(n, int(input()), titles))
    return

main(*input_data())