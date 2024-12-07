'''
Condition
- 뉴클레오티드 : A,T,G,C 4종류
- DNA는 뉴클레오티드 종류로 표현
- Hamming Distance: 길이가 같은 DNA에서, 각 위치에서 다른 문자의 수

Answer
- 다른 s들과 HD를 비교했을 때, 그 합이 가장 작게되는 s를 출력하여라
- 사전순으로 가장 앞서는 것을 출력
- 입력으로 주어진 값에서 찾는 것이 아닌 커스텀해도 상관없음

>>>> 완탐: n!이므로 불가능
>>>> i번째 인덱스 값만 완탐
- 모든 s들에 대해 i번째 문자열중 가장 많은쪽을 선택하면 될듯
'''
from collections import Counter
from sys import stdin
input = stdin.readline
n,m = map(int,input().split())
lst = [input() for _ in range(n)]

hd = 0
answer = ""
for j in range(m):
    # j열의 문자들
    colJ = sorted([lst[i][j] for i in range(n)])
    # hd 계산
    maxS,cnt = Counter(colJ).most_common()[0]
    hd += (n - cnt)
    answer += maxS

print(answer)
print(hd)