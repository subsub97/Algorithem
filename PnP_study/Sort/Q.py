def quick_sort(list,start,end):
    if start >= end:
        return
    pivot = start
    left = start + 1
    right = end

    while(left <= right):
        while left <= end and list[left] <= list[pivot]:
            left += 1

        while right > start and list[right] >= list[pivot]:
            right -= 1
        if left > right:
            temp = list[right]
            list[right] = list[pivot]
            list[pivot] = temp
        else:
            temp = list[right]
            list[right] = list[left]
            list[left] = temp

    quick_sort(list,start,right - 1)
    quick_sort(list,right + 1,end)

A = [87,1,2,5,7,3,2,3,6,8,9,4,3,1,2,23,4,6,69]
quick_sort(A,0,len(A)-1)
print(A)