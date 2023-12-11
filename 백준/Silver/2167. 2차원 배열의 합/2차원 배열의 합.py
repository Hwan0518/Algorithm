'''
- TL : 2s
- 1<= N,M <= 300
- 1<= K <= 1만
- (i,j)부터 (x,y)까지 저장된 수들의 합을 구하여라

>>> N*M을 매번 탐색해서 더한다하더라도 최대 O(900)이므로, K번곱해도 O(900만)으로 통과가능
>>> (i,j)와 (x,y)가 이루는 사각형 내부에 위치하는 수들을 모두 더한다

1. 브루트포스
    - 행 : i~x까지
    - 열 : j~y까지
    >>> fail : 300 * 300 = 900이 아니라 90000이다.. k번 곱하면 O(90000 * 10000) = O(9억) = TLE
    
2. 메모이제이션 사용 = 구간합
    - (i,j)~(x,y)의 모든 구간합을 저장해놓음
    - 행 : i~x까지
    - 열 : y까지 구간합 - j까지 구간합
    - ex)
        1~3의 합 : prefixsum(3) - prifixsum(1-1)
        4~6의 합 : prefixsum(6) - prifixsum(4-1)
    
'''

from sys import stdin
input = stdin.readline

def input_data():
    n,m = map(int,input().split())
    arr = []
    for i in range(n):
        arr.extend(list(map(int,input().split())))
    k = int(input())
    from_to = []
    for _ in range(k):
        i,j,x,y = map(int,input().split())
        from_to.append((i,j,x,y))
    return n,m,arr,from_to


def prefix_sum(n, arr):
    prefixsum = [0]
    for i in range(n):
        prefixsum.append(prefixsum[i]+arr[i])
    return prefixsum


def solution(n,m,arr,from_to):
    # 구간합을 구함
    prefixsum = prefix_sum(n*m, arr)
    # from_to에서 r:i~x, c:j~y까지의 모든 수의 합을 더함
    for data in from_to:
        i,j,x,y = data
        summation = 0
        # 구간합을 사용해서 결과를 구함
        for r in range(i,x+1):
            prefix_end = prefixsum[(r-1)*m+y]
            prefix_stt = prefixsum[(r-1)*m+(j-1)]
            summation += prefix_end - prefix_stt
        print(summation)
    return


solution(*input_data())


''' Test Case
2 3
1 2 4
8 16 32
3
1 1 2 3
1 2 1 2
1 3 2 3
answer : 63 2 36

4 4
1 2 3 4
5 6 7 8
9 10 11 12
13 14 15 16
3
2 2 3 3
2 4 4 4
3 1 4 4
answer : 34 36 100
'''