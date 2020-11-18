class At_Most_K_or_K_distinct_characters:
    s = "asadfewhsadf"
    def __init__(self):
        self.s = s
    def atMostK(self, k:int) -> int:
        if not s:return 0
        res = left = 0
        chars = {} # using map to store char's frequency
        for right in range(len(s)):
            temp = s[right]
            chars[s[right]]+=1
            while len(chars) > k: # more than k distinct chars
                chars[s[left]]-=1 # remove from left
                if chars[s[left]] == 0:
                    chars.remove(s[left])
                left+=1
            res = max(res, right - left + 1) # update res
        return res

    # convert Kdistinct to atMostK functions

    def KDistinct(self, k:int) -> int:
        if not s:return 0
        return atMostK(self,k) - atMostK(self,k-1)
