# 삽입정렬
'''
선택정렬 , 버블정렬들과 비교하였을때는 더 효과적인
정렬방법이다. 앞에서 부터 수를 잡아서 자신의 앞에
숫자들은 이미 정렬이 되어있고 그 상태에서 자신을
끼워 넣는형태의 정렬이다.
"거의 정렬"된 상태라면 가장 좋은 정렬이 될 수 있다.
'''

def insertion_sort(list):
    list_size = len(list)
    for i in range(list_size -1):
        j = i
        while list[j] > list[j+1]:
            temp = list[j]
            list[j] = list[j+1]
            list[j+1] = temp
            if j !=  0:
                j -= 1
    return list



A = [5,4,3,2,1]
list = insertion_sort(A)

for i in range(5):
    print(list[i])