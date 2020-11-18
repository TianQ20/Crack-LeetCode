class LongestStringWithoutDuplicate:
    string = "asbasdf"
    def __init__(self):
        self.string = string

# 一般写法：
# for (int i = 0, j = 0; i < n; i ++ )
# {
#     while (j < i && check(i, j)){
#           j ++ ;
#           // 具体问题的逻辑
#     }
# }

# 常见问题分类：
#     (1) 对于一个序列，用两个指针维护一段区间
#     (2) 对于两个序列，维护某种次序，比如归并排序中合并两个有序序列的操作

# 在区间[star, end]里，end右移时，如果找到了重复字母，start右移的，直到字母不重复

    def longestStringWithNoDuplicate(self, s:str) -> int:
        # corner case
        if not s: return 0
        res = 0
        start = end = 0
        used = set()
        while start < len(s) and end < len(s):
            if s[end] not in used:
                used.add(s[end])
                end+=1
                res = max(res, end - start)
            else:
                used.remove(s[start])
                start+=1
        return res

    # 另外一种写法可以用128的数组存每个char的出现次数，如果>1则从start开始--直到=0，然后更新max即可。

