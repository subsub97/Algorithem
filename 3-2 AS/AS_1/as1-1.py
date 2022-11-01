
n = int(input())
# n개의 수를 입력받는 배열
num_arr = tuple(map(int, input().split()))

k = int(input())

# k와 가장 인접한 수 찾기

# 가장 가까운 수를 찾기위한 변수
min_gap = 1000000001
# 가까운 수가 여러개일 경우를 대비한 리스트 생성
min_gap_list = []
start = 0
end = len(num_arr) - 1

while start <= end:
    mid = (start + end) // 2

    if k == num_arr[mid]:
        min_gap_list = []
        min_gap_list.append(k)
        break


    else:
        if k > num_arr[mid]:
            start = mid + 1
        else:
            end = mid -1
if len(min_gap_list) == 0:
    #이진탐색을 이용하여 k와 동일한 값을 찾지 못한경우
    if mid >= 1 and (n - 1) != mid:
        a = abs(k - num_arr[mid -1])
        b = abs(k - num_arr[mid])
        c = abs(k - num_arr[mid + 1])
        min_num = min(a,b,c)
        if a < c and a == b:
            min_gap_list.append(num_arr[mid-1])
            min_gap_list.append(num_arr[mid])
        elif b < a and b == c:
            min_gap_list.append(num_arr[mid])
            min_gap_list.append(num_arr[mid+1])
        elif a < b and a == c:
            min_gap_list.append(num_arr[mid - 1])
            min_gap_list.append(num_arr[mid + 1])
        elif min_num == a:
            min_gap_list.append(num_arr[mid - 1])
        elif min_num == b:
            min_gap_list.append(num_arr[mid])
        elif min_num == c:
            min_gap_list.append(num_arr[mid+1])

    elif mid == 0:
        a = abs(k - num_arr[mid])
        b = abs(k - num_arr[mid + 1])
        if a == b:
            min_gap_list.append(num_arr[mid])
            min_gap_list.append(num_arr[mid + 1])
        elif a > b:
            min_gap_list.append(num_arr[mid + 1])
        else:
            min_gap_list.append(num_arr[mid])

    else:
        a = abs(k - num_arr[mid - 1])
        b = abs(k - num_arr[mid])
        if a == b:
            min_gap_list.append(num_arr[mid-1])
            min_gap_list.append(num_arr[mid])
        elif a > b:
            min_gap_list.append(num_arr[mid])
        else:
            min_gap_list.append(num_arr[mid-1])






#len 메소드는 O(1)시간
min_gap_list.sort()
for i in range(len(min_gap_list)):
    print(min_gap_list[i],end =' ')




