'''
- TL : 1s
- 1<=n<=10만
- -1000<=num<=1000
- 수열에서 연속된 몇 개의 값을 선택해서 가장 큰 합을 만들어라

>>> 투포인터
>>> 양수에서 시작해서 양수로 끝내는 투포인터를 구현해본다
>>> 시간복잡도 O(N)이라서 가능

1. 탐색
    - for문으로 탐색
    - sum 시작값 : i=0부터 배열 탐색, 양수면 sum 시작값으로 설정
    - sum 끝값 : 시작값부터 계속해서 양수가 나오면 계속 더해준다, 양수가 끝나면 그 이전값까지를 sum한다
    - sum을 마친 이후, 다시 sum 시작값을 sum 끝값 이후의 양수로 시작해준다
    - 끝값이 배열의 끝에 도착할때까지 이어간다
    
2. 탐색
    - 음수가 나오더라도 그 뒤에 계속 양수가 나오면 최댓값은 증가한다. 따라서 이 부분을 고려해야한다
    - 음수가 나오면 일단 result에 sum값을 더해준다(최댓값 갱신)
    - 이후 계속 음수를 더해가다가, sum값이 0보다 작아진다면, 다음 수를 시작점으로 sum을 시작한다

3. 탐색
    - summation <=0일때, 현재 값부터 다시 시작한다
'''
def input_data():
    n = int(input())
    array = list(map(int,input().split()))
    return n, array

def solution(n, array):
    result = -1e9
    summation = 0
    idx = 0
    while idx <n:
        v = array[idx]
        # v가 양수면 곧바로 최댓값을 갱신한다
        if v >= 0:
            # result가 음수라면 v를 summation으로
            if result <0:
                summation = v
            # result가 양수라면, v를 더한 값을 summation으로
            else:
                summation += v
            if summation > result:
                result = summation
        # v가 음수일 때
        else:
            # v + summation이 0이하라면 summation을 0으로 초기화한다
            if v + summation <= 0:
                # summation이 0이하라면 result,v중 큰값으로 최댓값을 갱신한다
                if summation <= 0:
                    result = max(result, v)
                summation = 0
            # 그렇지 않다면, 계속해서 더해본다
            else:
                summation += v
        # idx를 증가시킨다
        idx +=1
    return result

print(solution(*input_data()))

