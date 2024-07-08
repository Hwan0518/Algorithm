n = int(input())
tanghuru = list(map(int,input().split()))
stt = 0
end = 0
cnt = 0
typeCnt = 0
types = {}
max_cnt = 0
tanghuru_type = set()
while stt < n:
    while end < n:
        t = tanghuru[end]
        if t not in tanghuru_type:
            if typeCnt >= 2:
                break
            types[t] = 1
            tanghuru_type.add(tanghuru[end])
            typeCnt +=1
        else:
            types[t] += 1
        cnt +=1
        end +=1
    max_cnt = max(max_cnt, cnt)
    stt_t = tanghuru[stt]
    stt +=1
    cnt -=1
    types[stt_t] -=1
    if types[stt_t] == 0:
        tanghuru_type.remove(stt_t)
        typeCnt -=1
    
print(max_cnt)