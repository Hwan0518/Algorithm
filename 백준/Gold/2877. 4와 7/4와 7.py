'''
Condition
    - TL : 1s
      ML :

Question
    - 

Access
    - 예상 분류 : 수학
      실제 분류 : 수학

    try_1)
        - 규칙을 찾아보자
            4, 7 (2개, 1자리수)
            44, 47, 74, 77 (4개, 2자리수)
            444, 447, 474, 477, 744, 747, 774, 777 (8개, 3자리수)
            4444, 4447, 4474, 4777, ... (16개, 4자리수)
        >>> n자리 수의 개수 = 2**n

        - 구현
            1. 자리수를 먼저 찾는다(n번째 자리수)
            2. k%(2**1)가 1 = 끝이 4
                             0 = 끝이 7
            3. (k+1)%(2**2)가 0,1 = 그다음이 4      (4//2 미만)
                          2,3 = 그다음이 7
            4. (k+1)%(2**3)이 0,1,2,3 = 그다음이 4  (8//2 미만)
                              4,5,6,7 = 그다음이 7
                .
                .
                .
               (k+1)%(2**n)이 (2**n)//2미만 = 그다음이4
                              else = 그다음이7
               
'''
k=int(input())
# k가 몇번째 자리수인지 확인하기 위해 누적합을 구한 후 확인함
prefixCount = [0]
for i in range(40):
    prefixCount.append(prefixCount[-1] + 2**(i+1))

# 자리수 계산
digit = 1
for i in range(40):
    if prefixCount[i]<k<=prefixCount[i+1]:
        digit = i+1
        break

# 숫자 찾기
result = ''
for i in range(digit,1,-1):
    if (k+1)%(2**i) < (2**i)//2:
        result += '4'
    else:
        result += '7'
if k%2:
    result +='4'
else:
    result +='7'
print(result)