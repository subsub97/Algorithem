import sys

n = int(sys.stdin.readline().strip())

card_list = [
    i for i in range(1,n+1)
]

def Qpop(list):
    for i in range(len(list)):
        if i == 0:
            pop_num = list[i]
        if i+1 < len(list):
            list[i] = list[i+1]
    if len(list) >= 2:
        list.pop()
    return pop_num

cnt = 0
while len(card_list) != 1:
    cnt += 1
    #짝수번째 인경우 pop한 수를 맨뒤로 붙여야하기때문
    if cnt % 2 == 0:
        num = Qpop(card_list)
        card_list.append(num)
    else:
        num = Qpop(card_list)

print(card_list[0])


