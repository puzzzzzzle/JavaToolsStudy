package group.zhangtao.alog.me;

import java.util.ArrayList;

public class Solution {



    static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }


    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
//        Stack<Integer> stack = new Stack<>();
//        ArrayList<Integer> integers = new ArrayList<>();
//        ListNode temp = listNode;
//        while (temp!=null){
//            stack.add(temp.val);
//            temp = temp.next;
//        }
//        while (!stack.isEmpty()){
//            integers.add(stack.pop());
//        }
//        return integers;
        ArrayList<Integer> integers = new ArrayList<>();
        ListNode temp = listNode;
        while (temp != null) {
            integers.add(0,temp.val);
            temp = temp.next;
        }
        return integers;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(10);
        listNode.next = new ListNode(15);
        listNode.next.next = new ListNode(55);
        System.out.println(printListFromTailToHead(listNode));
    }
}