'''
Condition
    - TL : 1s
    - 보석 색 M가지(30만)
    - 보석 못받는 학생 있어도 됨, 하지만 학생은 항상 같은 색을 받아야함
    - 질투심 = 가장 많은 보석을 가져간 학생이 가지고 있는 보석의 개수

Goals
    - 질투심의 최솟값을 출력

Approach
    - 이분탐색
    - 질투심을 mid로 잡고, 이때 필요한 사람 수가 n이 될 때까지 mid를 조절한다
'''

def input_data():
    n,m = map(int,input().split())
    jewels = []
    for _ in range(m):
        j = int(input())
        jewels.append(j)
    return n, jewels

def receiver_cnt(avg:int, jewls:list):
    cnt = 0
    for j in jewls:
        cnt += j//avg if j%avg==0 else j//avg+1
    return cnt

def binary_search(n:int, jewels:list):
    answer = -1
    stt = 1 # 질투심 최소는 1이다
    end = max(jewels)
    while stt <= end:
        mid = (stt+end)//2
        result = receiver_cnt(mid, jewels)
        if result <= n:
            end = mid-1
            answer = mid
        elif result >n:
            stt = mid+1
    return answer

def main(n:int, jewels:list):
    return binary_search(n, jewels)

print(main(*input_data()))