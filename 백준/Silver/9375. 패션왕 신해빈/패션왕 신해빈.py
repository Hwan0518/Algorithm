#정답: 모든 경우의수(안입은 상태를 포함하기 때문에 +1한 값을 곱해줌) -1

from collections import defaultdict
for _ in range(int(input())):
    n = int(input())
    dt = defaultdict(list)
    for _ in range(n):
        inputs = input().split()
        dt[inputs[1]].append(inputs[0])
    answer = 1
    for k in dt.keys():
        answer *= len(dt[k])+1
    print(answer-1)