"""
Assign 02 - <Sean Kruse>

Directions:
    * Complete the sorting algorithm functions that are given below. Note that
      it okay (and probably helpful) to define auxiliary/helper functions that
      are called from the functions below.  Refer to the README.md file for
      additional info.

    * NOTE: Remember to add a docstring for each function, and that a
      reasonable
      coding style is followed (e.g. blank lines between functions).
      Your program will not pass the tests if this is not done!

    * Be sure that you implement your own sorting functions since.
      No credit will be given if Python's built-in sort function is used.
"""
import time
import random
from math import ceil, log10
import sys
sys.setrecursionlimit(100000)


def bubbleSort(a_list):
    """
    Sort a list using the bubblesort algorithm.
    Start at the beginning of the list. Compare the first
    two elements. If the first element is greater, swap them. Continue
    this process until the largest element is at the end of the list.
    Repeat these steps until each element is sorted from the lowest to
    the largest elements in the list.

    Args:
    -----
    a_list: A list of comparable elements to be sorted.

    Returns:
    --------
    A tuple containing a sorted list and the elapsed time taken to sort it.
    """
    start_time = time.time()

    # Adapted from ChatGPT
    n = len(a_list)
    for i in range(n):
        # Last i elements are already sorted
        for j in range(0, n - i - 1):
            # Swap if the element found is greater than the next element
            if a_list[j] > a_list[j + 1]:
                a_list[j], a_list[j + 1] = a_list[j + 1], a_list[j]

    elapsed_time = time.time() - start_time
    return (a_list, elapsed_time)


def mergeSortbyThree(a_list, split_by_k=3):
    """
    Sort a list using the mergesort algorithm by first dividing it into three
    parts. Then it merges the three sorted thirds to produce a single sorted
    list. This is done by comparing the first elements of each third, and
    adding the smallest one to the merged list. The same comparison is
    repeated with the next elements of the same thirds until one of the thirds
    is empty. The remaining elements of the non-empty thirds are then added to
    the merged list.

    Args:
    -----
    a_list: A list of comparable elements to be sorted.
    split_by_k (optional): An integer representing how
    to split the list into thirds. The default value is 3.

    Returns:
    --------
    A sorted list from the 3-way merge.
    """
    # base case: Make sure that the 3 sub-lists that have
    # 2 elements are ordered from least to greatest by flipping
    # the sub lists. Devin Schulist and Connor Meek both helped me with this
    # condition to swap these elements based on the issue with
    # the way I was sorting
    if len(a_list) == 2 and a_list[0] > a_list[1]:
        a_list[0], a_list[1] = a_list[1], a_list[0]
        return a_list
    # base case: if the list has more than 2 elements
    if len(a_list) > 2:
        # left pointer left_th
        i = 0
        # mid pointer mid_third
        j = 0
        # right pointer right_th
        k = 0
        # track the elements in the list
        z = 0
        # initilialize the list split and define each sublist into three parts
        mid = len(a_list) // split_by_k
        left_th = a_list[:mid]
        right_th = a_list[2 * mid:]
        mid_third = a_list[mid:2 * mid]
        # Recursively call the left third, and right third, and middle third
        mergeSortbyThree(left_th)
        mergeSortbyThree(right_th)
        mergeSortbyThree(mid_third)
        # Merge the sorted thirds:
        # Get the min value by comparing the elements from
        # each sublist and setting the elements of the
        # sublists and adding the smallest to a_list
        while i < len(left_th) and j < len(mid_third) and k < len(right_th):
            if left_th[i] <= mid_third[j] and left_th[i] <= right_th[k]:
                a_list[z] = left_th[i]
                i += 1
            elif mid_third[j] <= left_th[i] and mid_third[j] <= right_th[k]:
                a_list[z] = mid_third[j]
                j += 1
            else:
                a_list[z] = right_th[k]
                k += 1
            z += 1
        # Merge any remaining elements in left_th and mid_th
        while i < len(left_th) and j < len(mid_third):
            if left_th[i] <= mid_third[j]:
                a_list[z] = left_th[i]
                i += 1
            else:
                a_list[z] = mid_third[j]
                j += 1
            z += 1
        # Merge any remaining elements in mid_th and right_th
        while j < len(mid_third) and k < len(right_th):
            if mid_third[j] <= right_th[k]:
                a_list[z] = mid_third[j]
                j += 1
            else:
                a_list[z] = right_th[k]
                k += 1
            z += 1
        # Merge any remaining elements in left_th and right_th
        while i < len(left_th) and k < len(right_th):
            if left_th[i] < right_th[k]:
                a_list[z] = left_th[i]
                i += 1
            else:
                a_list[z] = right_th[k]
                k += 1
            z += 1
        # Add any remaining elements in left_th to a_list
        while i < len(left_th):
            a_list[z] = left_th[i]
            i += 1
            z += 1
        # Add any remaining elements in mid_th to a_list
        while j < len(mid_third):
            a_list[z] = mid_third[j]
            j += 1
            z += 1
        # Add any remaining elements in right_th to a_list
        while k < len(right_th):
            a_list[z] = right_th[k]
            k += 1
            z += 1
        # Return the sorted list
        return a_list


def mergeSort(a_list, split_by_k=2):
    """
    Sort the list `a_list` using the mergesort algorithm.

    The algorithm works by recursively dividing the list into halves until each
    half has a length of 0 or 1. Then it merges the two sorted halves to
    produce a single sorted list. This is done by comparing the first elements
    of each half, and adding the smaller one to the merged list. The same
    comparison is repeated with the next elements of the two halves until one
    of the halves is empty. The remaining elements of the non-empty half are
    then added to the merged list.

    Args:
    -----
    a_list : list
        The list of comparable elements to be sorted.
    split_by_k : int, optional
        An integer representing how to split the list into halves. The default
        value is 2, meaning that the list will be split in half.

    Returns:
    --------
    tuple
        A tuple containing a sorted list and the elapsed time taken to sort it.

    Notes:
    ------
    This function returns a new sorted list and does not modify the input list.
    The elapsed time returned is the time taken to sort the list in seconds.
    """
    start_time = time.time()

    # Adapted from example from class slides
    # Added base case and if else statement to handle both the
    # split k by 2 and split k by 2
    # base case: if the list has 0 or 1 elements, it is already sorted
    if len(a_list) <= 1:
        elapsed_time = time.time() - start_time
        return (a_list, elapsed_time)
    # If the split_by_k
    if split_by_k == 2 and len(a_list) > 1:
        mid = len(a_list) // 2
        left_half = a_list[:mid]
        right_half = a_list[mid:]
        mergeSort(left_half)
        mergeSort(right_half)

        i = 0
        j = 0
        k = 0
        # merge the sorted left and right halves
        while i < len(left_half) and j < len(right_half):
            # split the list into two halves
            if left_half[i] < right_half[j]:
                a_list[k] = left_half[i]
                i += 1
            else:
                a_list[k] = right_half[j]
                j += 1
            # Increment k after it breaks out of the loop
            k += 1
        while i < len(left_half):
            # copy remaining items in left half to alist
            a_list[k] = left_half[i]
            i += 1
            k += 1
        while j < len(right_half):
            # copy remaining items in right half to alist
            a_list[k] = right_half[j]
            j += 1
            k += 1
    # Split by k is equal to 3
    elif split_by_k == 3 and len(a_list) > 1:
        mergeSortbyThree(a_list, 3)

    elapsed_time = time.time() - start_time
    return (a_list, elapsed_time)


def partition(a_list, low, high, pivot):
    """
    Partition the list `a_list` in the range [low, high] around the element at
    index `pivot`, such that all elements to the left of the pivot are smaller
    than or equal to the pivot, and all elements to the right are larger than
    the pivot. Returns the index of the pivot after partitioning.

    Args:
    -----
    a_list : list
        The list to be partitioned.
    low : int
        The lowest index of the range to be partitioned.
    high : int
        The highest index of the range to be partitioned.
    pivot : int
        The index of the element to use as pivot.

    Returns:
    --------
    int
        The index of the pivot after partitioning.

    Notes
    -----
    This function modifies the input list in place.
    """
    # Swap the pivot element to the end of the list
    a_list[pivot], a_list[high] = a_list[high], a_list[pivot]

    # Initialize the left and right pointers
    i = low - 1

    # Loop through the list from low to high-1
    for j in range(low, high):
        # If the current element is less than or equal to the pivot,
        # swap it with the element to the right of the left pointer
        if a_list[j] <= a_list[high]:
            i += 1
            a_list[i], a_list[j] = a_list[j], a_list[i]

    # Swap the pivot with the element to the right of the left pointer
    a_list[i + 1], a_list[high] = a_list[high], a_list[i + 1]

    # Return the index of the pivot
    return i + 1


def quickSort(a_list, pivot='middle', low=0, high=None):
    """
    Sort a list using the quicksort algorithm.

    Args:
    -----
    a_list: A list of comparable elements to be sorted.
    pivot (optional): A string representing the method for choosing the pivot
    element. 'first', 'last', or 'middle'. Default is 'middle'.
    low (optional): An integer representing the starting index of the list to
    be sorted. Default is 0.
    high (optional): An integer representing the ending index of the list to be
    sorted. Default is None.

    Returns:
    --------
    A tuple containing a sorted list and the elapsed time taken to sort it.
    """
    start_time = time.time()
    # Adapted from ChatGPT
    # Set the high as the last element if there is no high
    if high is None:
        high = len(a_list) - 1
    if low < high:
        # Choose pivot index based on first or msiddle
        if pivot == 'first':
            pivot_index = low
        elif pivot == 'middle':
            pivot_index = (low + high) // 2
        else:
            pivot_index = high

        # Partition the list and get the pivot index
        partition_index = partition(a_list, low, high, pivot_index)

        # Recursively sort the left and right halves
        quickSort(a_list, pivot, low, partition_index - 1)
        quickSort(a_list, pivot, partition_index + 1, high)

    elapsed_time = time.time() - start_time
    return (a_list, elapsed_time)


def radixSort(a_list):
    """
    Sort a list using the radixsort algorithm.
    Find the maximum number of digits. For each digit position create
    a corresponding bucket. Iterate throught the list and distribute each
    number into the appropriate bucket based on the number of digits.
    Then concatanate the buckets to create an ordered list.

    Args:
    -----
    a_list: A list of comparable elements to be sorted.

    Returns:
    --------
    A tuple containing a sorted list and the elapsed time taken to sort it.
    """
    start_time = time.time()
    # Adapted from ChatGPT
    max_num_digits = ceil(log10(max(a_list) + 1))

    for i in range(max_num_digits):
        # Create 10 buckets, one for each digit (0-9)
        buckets = [[] for _ in range(10)]

        # Distribute elements into the buckets based on the i-th digit
        for num in a_list:
            digit = (num // 10**i) % 10
            buckets[digit].append(num)

        # Concatenate the buckets in order to obtain a sorted list
        a_list = [num for bucket in buckets for num in bucket]

    elapsed_time = time.time() - start_time
    return (a_list, elapsed_time)


def assign02_main():
    """ A 'main' function to be run when our program is run standalone """
    list1 = list(range(5000))
    random.seed(1)
    random.shuffle(list1)

    # list1 = [54, 26, 93, 17, 77, 31, 44, 55, 20] # helpful for early testing

    # run sorting functions
    bubbleRes = bubbleSort(list(list1))
    mergeRes2 = mergeSort(list(list1), split_by_k=2)
    mergeRes3 = mergeSort(list(list1), split_by_k=3)
    quickResA = quickSort(list(list1), pivot='first')
    quickResB = quickSort(list(list1), pivot='middle')
    radixRes = radixSort(list(list1))

    # Print results
    print(f"\nlist1 results (randomly shuffled w/ size = {len(list1)})")
    print(list1[:10])
    print(f"  bubbleSort time: {bubbleRes[1]:.4f} sec")
    print(bubbleRes[0][:10])
    print(f"  mergeSort2 time: {mergeRes2[1]:.4f} sec")
    print(mergeRes2[0][:10])
    print(f"  mergeSort3 time: {mergeRes3[1]:.4f} sec")
    print(mergeRes3[0][:10])
    print(f"  quickSortA time: {quickResA[1]:.4f} sec")
    print(quickResA[0][:10])
    print(f"  quickSortB time: {quickResB[1]:.4f} sec")
    print(quickResB[0][:10])
    print(f"  radixSort time: {radixRes[1]:.4f} sec")
    print(radixRes[0][:10])

    # Try with a list sorted in reverse order (worst case for quicksort)
    list2 = list(range(6000, 1000, -1))

    # run sorting functions
    bubbleRes = bubbleSort(list(list2))
    mergeRes2 = mergeSort(list(list2), split_by_k=2)
    mergeRes3 = mergeSort(list(list2), split_by_k=3)
    quickResA = quickSort(list(list2), pivot='first')
    quickResB = quickSort(list(list2), pivot='middle')
    radixRes = radixSort(list(list2))

    # Print results
    print(f"\nlist2 results (sorted in reverse w/ size = {len(list2)})")
    print(list2[:10])
    print(f"  bubbleSort time: {bubbleRes[1]:.4f} sec")
    print(bubbleRes[0][:10])
    print(f"  mergeSort2 time: {mergeRes2[1]:.4f} sec")
    print(mergeRes2[0][:10])
    print(f"  mergeSort3 time: {mergeRes3[1]:.4f} sec")
    print(mergeRes3[0][:10])
    print(f"  quickSortA time: {quickResA[1]:.4f} sec")
    print(quickResA[0][:10])
    print(f"  quickSortB time: {quickResB[1]:.4f} sec")
    print(quickResB[0][:10])
    print(f"  radixSort time: {radixRes[1]:.4f} sec")
    print(radixRes[0][:10])


# Check if the program is being run directly (i.e. not being imported)
if __name__ == '__main__':
    assign02_main()
