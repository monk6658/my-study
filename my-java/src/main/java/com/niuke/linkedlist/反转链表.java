package com.niuke.linkedlist;

/**
 * 
 * @author zxl
 * @date 2021/5/17 17:25
 */
public class 反转链表 {

    /*
    输入一个链表，反转链表后，输出新链表的表头。

    输入
        {1,2,3}
    返回值
        {3,2,1}
     */
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }

        ListNode pre = null;
        ListNode next;

        while (head != null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }

        return pre;
    }

    public static void main(String[] args) {



    }

}
