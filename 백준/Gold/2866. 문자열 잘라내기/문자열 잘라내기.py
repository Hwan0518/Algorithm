'''
Condition
    - TL : 1s(2000만)
      ML : 256(64*100만)
    - 2<=r,c<-=1000

Question
    - count를 출력하라

Access
    - 예상 분류 : 문자열, 구현
      실제 분류 : 문자열, 정렬, 이분탐색, 해시를 사용한 집합과 맵

    try_1)
        - 그대로 구현해본다
        >>> O(n^3), TLE
        
    try_2)
        - 맨 위에부터 행 하나하나의 중복을 확인한다
        >>> fail
        
    try_3)
        - 맨 아래부터 중복을 확인한다
        - 이후 중복이 발견된 행부터 중복이 안나오는 행까지 cnt를 확인한다
        >>> fail, 중복되는 알파벳이 동일한 위치에 존재해야지 정답이 출력됨
    
    try_4) 클론코딩 : https://velog.io/@dasd412/%EB%B0%B1%EC%A4%80-2866-%EB%AC%B8%EC%9E%90%EC%97%B4-%EC%9E%98%EB%9D%BC%EB%82%B4%EA%B8%B0-%ED%8C%8C%EC%9D%B4%EC%8D%AC
        - 이분탐색을 활용한다

'''
from sys import stdin
input = stdin.readline


#define function
def check(mid):
    noDupl = set()
    for col in range(c):
        string = ''
        # mid부터 table끝까지의 문자열을 탐색
        for row in range(mid, r):
            string += table[row][col]
        
        # 중복이 없다면 set에 대입
        if string not in noDupl:
            noDupl.add(string)
        # 중복이 생긴다면 True를 반환
        else:
            return True
    return False
    

def solution():
    stt = 0
    end = r-1
    answer = 0  # 중복이 시작되기 직전 line이라 생각하면 됨
    
    # 이분탐색 시작
    while stt <= end:
        mid = (stt + end)//2

        # mid~table끝까지 중복이 있는지 확인
        if check(mid):
            end = mid-1     # 중복이 있다면 mid를 stt에 가깝게 위치시켜서, 이전에 중복이 있었는지 확인
        else:
            answer = mid    # 중복이 없다면, 중복이 시작되기 직전을 갱신
            stt = mid+1
    return answer


#main
r,c = map(int,input().split())
table = [list(input().strip()) for _ in range(r)]
print(solution())


