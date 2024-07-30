'''
Conditions
    - TL: 1s

Goals
    - 조건을 만족하는, 짝수로 이루어진 연속한 부분 수열중 가장 긴 길이

Approach
    - 완탐
        - O(N**N) -> TLE
    - 투포인터
        - 남은 k 개수를 관리 
'''
n,k = map(int,input().split())
s = list(map(int,input().split()))

end = 0
odd = 0
length = 0
max_length = 0
for stt in range(n):
    while odd <= k and end < n:
        num = s[end]
        # num이 홀수인 경우
        if num%2:
            odd +=1
        # num이 짝수인 경우
        else:
            length +=1
        end +=1
    max_length = max(max_length, length)
    # 시작값이 홀수인 경우
    if s[stt]%2:
        odd -=1
    # 시작값이 짝수인 경우
    else:
        length -=1
print(max_length)