'''
A,B의 길이가 같으면서 차이가 최소가 되도록 만들었을 때, 차이는 얼마인가?

완탐
- 앞뒤에 아무런 알파벳이나 추가할 수 있다
- 즉, A가 B와 같은 길이가 될때까지 아무런 알파벳을 추가할 수 있으므로 신경쓰지 않아도 된다
- 중요한건 현재 A와B를 비교했을 때 차이가 가장 적게만들어야한다
'''
a,b = input().split()
stt = 0
minDiff=1e9
while stt <= len(b)-len(a):
    diff = 0
    a_stt = 0
    for i in range(stt, stt+len(a)):
        if a[a_stt] != b[i]:
            diff +=1
        a_stt+=1
    minDiff = min(minDiff, diff)
    stt +=1
print(minDiff)