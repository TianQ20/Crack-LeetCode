class Presum:
    arr = []
    def __init__(self):
        self.arr = arr

    def SubarraySumK(self, k:int) -> int:
        if not arr: return 0
        maps = { 0: 1 }
        count = sum = 0
        for num in arr:
            sum += num
            if sum - k in maps:
                count += maps[sum - k]
            maps[sum] = maps.get(sum, 0) + 1
        return count