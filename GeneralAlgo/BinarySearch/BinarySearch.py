
class BinarySearch:

    nums = []
    def __init__(self, nums):
        self.nums = nums
    #
    #  模版1：find the smallest numebr that arr[i] >= target ｜｜ arr[i] > target
    #  这种情况下，区间[l,r] 可以被分成[l, mid] 和 [mid+1, r]
    #  此时更新的时候，我们要么l = mid + 1 或者 r = mid;

    def search_larger(self, target: int) -> int:
        left,right = 0, len(nums)-1
        while left < right:
            mid = left + ( right - left )//2
            if nums[mid] >= target:
                right = mid
            else:
                left = mid + 1
        return left if nums[left] >= target else -1

    #  模版2： find the largest number that arr[i] <= target  || arr[i] < target
    #  这种情况下，区间[l,r] 可以被分成[l, mid-1] 和 [mid, r]
    #  此时更新的时候，我们要么l = mid  或者 r = mid + 1;
    #
    #  PS: 为了防止死循环，此时mid 要变成 left + ( right - left + 1 ) / 2.

    def search_smaller(self, target :int) -> int:
        left,right = 0, len(nums)-1
        while left < right:
            mid = left + ( right - left + 1)//2
            if nums[mid] <= target:
                left = mid
            else:
                right = mid - 1
        return right if nums[left] <= target else -1


    #  模版3 find the target value in the array that arr[i] == target
    #  这种情况下，将区间[l,r] 分成 [l, mid-1] , mid, [mid+1, r]
    #  此时while里的条件是l<=r， 而不是单纯的 l<r了
    #  如果arr[mid] != target, 按照arr[i] 和 target的关系移动l = mid + 1 或者 r = mid - 1

    def search_equal(self, target:int) -> int :
        left, right = 0, len(nums)-1
        while left < right:
            mid = left + ( right - left )//2
            if nums[mid] == target:
                return mid
            elif nums[mid] < target:
                left+=1
            else:
                right-=1
        return -1
