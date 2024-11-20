'''
이건 백트래킹으로 풀면 될 듯!
    -> n,m 범위가 너무 커서 안됨

그리디
    -> 2칸위로+2칸아래로 or 1칸위로+1칸아래로 조합중 무조건 하나 택1
    -> 이동 횟수가 4번보다 적은 경우 이동 방법에 제약이 없다 == 이동 횟수가 4번이상이라면, 모든 방법을 한번씩 다 사용한 후에야 중복해서 사용할 수 있다
'''
def main():
    r,c = map(int,input().split())
    # 2칸위로+2칸아래로 조합으로 갈 수 있는 경우 -> 1칸위로+1칸아래로 한번 하고, 이후에 2칸위로+2칸아래로 반복, c<=5라면 그대로 c-1 return
    if r>=3:
        if c<=4: return c
        elif c<=6: return 4
        else: return 3+(c-5)
    # 이 경우 2칸위로+2칸아래로 쓸 수가 없음 -> 한칸위로+한칸아래로 경우만 생각해주자
    elif r==2:
        if c<3: return 1
        elif c<7: return c//2 +1 if c%2 else c//2
        else: return 4
    # 위,아래로 이동 불가
    else:
        return 1

print(main())