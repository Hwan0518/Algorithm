'''
Condition
    - TL: 1s
    - 그룹 조건
        - 키 순서로 정렬되어있음
        - 적어도 한 명이 존재해야 함
        - 같은 조의 인원들끼리는 인접해야함
    - 티셔츠
        - 비용 = 그룹에서 max(키) - min(키)

Goals
    - k개의 조로 나누었을 때, 티셔츠 만드는 비용의 최소는?

Approach
    - 그리디
    - 차이: 2,2,1,4
        - 이웃된 것은 선택할 수 없다
        - k-1개를 선택했을 때, 선택할 수 있는 차이가 없어야 한다
        - 가장 작은 것 부터 선택하면 될듯
    
    - hint: https://cochin-man.tistory.com/16
    - 최소 차이 = 각 학생이 혼자 있는 경우
            = 1|3|5|6|10
    - k개의 조가 되는 경우 = 최소 차이 경우에서 n-k개의 막대를 제거한 경우
                        = 1|3|(5,6,10) or (1,3)|(5,6)|10 등등
                        = n개에서 n-k개를 고름
    - 값이 최소가 되는 경우 = n개에서 차이가 작은 순서대로 n-k개를 고르는 경우
                        = 정렬된 차이에서 n-k개를 골라서 더한 값
                        = sum(diff[:n-k])
'''
n,k = map(int,input().split())
p = list(map(int,input().split()))
diffs = [p[i+1]-p[i] for i in range(n-1)]
diffs.sort()
answer = sum(diffs[:n-k])
print(answer)