from math import sqrt

n = int(input())
sq = sqrt(n)

def main():
    # 1. n이 제곱근
    if int(sq) == sq:
        return 1
    # 2. n이 제곱근 두 개의 합
    for i in range(1, int(sq)+1):
        # n에서 제곱수를 뺀 결과가 제곱수라면 = 제곱수 두 개의 합임
        result = n-i**2
        if result < 0:
            continue
        if int(sqrt(result)) == sqrt(result):
            return 2
    # 3. n이 제곱근 세 개의 합
    for i in range(1, int(sq)+1):
        for j in range(1, int(sq)+1):
            result = n-i**2-j**2
            if result < 0:
                continue
            if int(sqrt(result)) == sqrt(result):
                return 3
    # 나머지
    return 4

print(main())