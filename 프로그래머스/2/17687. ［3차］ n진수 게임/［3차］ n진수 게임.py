'''
- n진법 계산
- 이후 돌아가면서 한 자릿수 씩 말한다
- 말할 숫자t개, 인원m명, 튜브의 순서p

1. 숫자 0부터 시작해서 n진법으로 변환
2. m과 p에 따라 result에 추가
3. result가 t개가 될 때까지 반복
'''
def trans(n, target):
    if target == 0:
        return ["0"]
    # 2진법이면 1보다 작아지면 끝, 3진법이면 2보다 작아지면 끝, ...
    tNum = []
    while target > n-1:
        tNum.append(need[target%n])
        target = target//n
    if target != 0:
        tNum.append(need[target])
    # 끝에서부터 pop 시켜서 말할 예정이라 reverse 하지 않는다
    return tNum

def getResult(n, t, m, p):
    result = []
    num = 0
    turn = 1
    # result가 t개가 될 때까지 진행
    while len(result) < t:
        tNum = trans(n, num)
        # tNum을 모두 말할때까지 계속 진행
        while tNum and len(result) < t:
            nn = tNum.pop()
            if turn == p:
                result.append(nn)
            # 다음 사람이 말할 차례
            if turn == m:
                turn = 1
            else:
                turn +=1
        # 변환한 수를 다 말했다면, 10진법 수 +=1
        num +=1
    # 결과
    return result

def solution(n, t, m, p):
    global need
    numList = [str(i) for i in range(10)] + ["A","B","C","D","E","F"]
    need = numList[:n]
    answer = ''.join(getResult(n, t, m, p))
    return answer