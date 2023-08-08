'''
Approach
    - n-1회차 : '뻔-데기-뻔-데기- 뻔*n - 데기*n'
    - '-'를 지날때마다 다음사람으로
    - T번째 '뻔' or '데기'를 외치는 사람은 몇번째 사람인가?
    - 1<=t<=1만
    - 1<=A<=2000
    - 0이면 '뻔'을, 1이면 '데기'를 구한다
    

>>> BruteForce
    : 구하고자 하는 구호와 인원을 따로따로 cnt한다
'''
a=int(input())
t=int(input())
word = input().strip()
    
a_cnt = 0
t_cnt = 0
repeat = 2
while t_cnt<t:
    say = ['0','1','0','1']
    say += ['0']*repeat
    say += ['1']*repeat
    for s in say:
        # n번째 사람이 말하는것
        if s == word:
            t_cnt +=1
        if t_cnt==t:
            break
        # n+1번째 사람
        a_cnt +=1
        if a_cnt == a:
            a_cnt = 0
    repeat +=1
print(a_cnt)