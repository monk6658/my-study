package com.leetcode.linkedlist.deleterepeat;

import com.common.ListNode;

/**
 * 
 * @author zxl
 * @date 2021/3/26 9:16
 */
public class 删除排序链表中的重复元素 {

    public static ListNode deleteDuplicates(ListNode head) {

        if (head == null) {
            return null;
        }

        ListNode cur = head;
        while(cur.next != null){
            if(cur.val == cur.next.val){
                cur.next = cur.next.next;
            }else {
                cur = cur.next;
            }
        }

        return head;
    }


    public static void main(String[] args) {
        // [1,1,2,2,3]

        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(1,new ListNode(2,new ListNode(2,new ListNode(3))));
        deleteDuplicates(listNode);

    }

}
