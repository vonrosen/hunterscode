#in place sorting algorithm

def quick_sort(array, left, right):
    if (left >= right): 
        return
    
    pivot_index = choose_pivot_index(left, right)
    pivot_value = array[pivot_index]
    
    new_pivot_index = arrange(array, pivot_index, pivot_value, left, right)
    
    quick_sort(array, left, new_pivot_index - 1)
    quick_sort(array, new_pivot_index + 1, right)
    
def swap(array, swap_from_index, swap_to_index):
    tmp = array[swap_to_index]
    array[swap_to_index] = array[swap_from_index]
    array[swap_from_index] = tmp
    
def choose_pivot_index(start, end):
    return int(round(start + end)/2)

def arrange(array, pivot_index, pivot_value, left, right):
    swap(array, pivot_index, right)
    next_swap_index = left
    for index in range(left, right):
        if (array[index] <= pivot_value):
            swap(array, next_swap_index, index)
            next_swap_index += 1
    
    swap(array, right, next_swap_index)
    return next_swap_index
        
arr = [565,4,3,45,234,2,1,23]
arr2 = [1,2]
arr3 = [2,1]
arr4 = [6,5,5,3,1,2]

quick_sort(arr, 0, len(arr) - 1)
quick_sort(arr2, 0, len(arr2) - 1)
quick_sort(arr3, 0, len(arr3) - 1)
quick_sort(arr4, 0, len(arr4) - 1)

print(arr)
print(arr2)
print(arr3)
print(arr4)