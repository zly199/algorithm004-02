package com.algorithm.qinchao.homework.week03;

import java.util.*;

/**
 * @author zhuruihong
 * @version 1.0
 * @date 2019/11/1 12:10
 * @description 一条基因序列由一个带有8个字符的字符串表示，其中每个字符都属于 "A", "C", "G", "T"中的任意一个。
 * 假设我们要调查一个基因序列的变化。一次基因变化意味着这个基因序列中的一个字符发生了变化。
 * 例如，基因序列由"AACCGGTT" 变化至 "AACCGGTA" 即发生了一次基因变化。
 * 与此同时，每一次基因变化的结果，都需要是一个合法的基因串，即该结果属于一个基因库。
 * 现在给定3个参数 — start, end, bank，分别代表起始基因序列，目标基因序列及基因库，请找出能够使起始基因序列变化为目标基因序列所需的最少变化次数。如果无法实现目标变化，请返回 -1。
 * 注意:
 * 起始基因序列默认是合法的，但是它并不一定会出现在基因库中。
 * 所有的目标基因序列必须是合法的。
 * 假定起始基因序列与目标基因序列是不一样的。
 * 示例 1:
 * start: "AACCGGTT"
 * end:   "AACCGGTA"
 * bank: ["AACCGGTA"]
 * 返回值: 1
 * 示例 2:
 * start: "AACCGGTT"
 * end:   "AAACGGTA"
 * bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]
 * 返回值: 2
 * 示例 3:
 * start: "AAAAACCC"
 * end:   "AACCCCCC"
 * bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]
 * 返回值: 3
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-genetic-mutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_433_202 {
    /**
     * @param start
     * @param end
     * @param bank
     * @return
     * BFS
     */
    public static int minMutationByBFS(String start, String end, String[] bank) {
        if (bank.length == 0) {
            return -1;
        }
        List<String> bankList = Arrays.asList(bank);
        if (!bankList.contains(end)) {
            return -1;
        }
        LinkedList<String> queue = new LinkedList<>();
        char[] genes = {'A', 'C', 'G', 'T'};
        int count = 0;
        queue.add(start);
        bankList.remove(start);
        while (!queue.isEmpty()) {
            for (int i = queue.size(); i > 0; i--) {
                String current = queue.poll();
                if (end.equals(current)) {
                    return count;
                }
                getNext(queue, current, bankList, genes);
            }
            count++;
        }
        return -1;
    }

    private static void getNext(LinkedList<String> queue, String current, List<String> bankList, char[] genes) {
        char[] chars = current.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char oldCurr = chars[i];
            for (char gene : genes) {
                if (chars[i] != gene) {
                    chars[i] = gene;
                    String strCurr = String.valueOf(chars);
                    // 存在则为符合要求(每一次基因变化的结果都需要是一个合法的基因串)
                    if (bankList.contains(strCurr)) {
                        queue.add(strCurr);
                        bankList.remove(strCurr);
                    }
                }
            }
            chars[i] = oldCurr;
        }
    }

    public static void main(String[] args) {
        String start = "AAAAACCC";
        String end = "AACCCCCC";
        String[] bank = {"AAAACCCC", "AAACCCCC", "AACCCCCC"};
        System.out.println(LeetCode_433_202.minMutationByBFS(start, end, bank));
    }
}
