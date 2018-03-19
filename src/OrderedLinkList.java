/**
 * 问题：已知两个链表head1和head2各自有序，
 * 请把它们合并成一个链表依然有序。结果链表要包含head1和head2的所有节点，即使节点值相同。
 *
 * @author Cyrus
 * @version v1.0, 2018/3/19 下午8:41
 */
public class OrderedLinkList {

    private static class Node {
        public Node next;
        public int value;
    }


    /**
     * 递归实现，分解，每次都找出头结点，当前节点的下一个节点就是下一轮的头节点
     */
    public static Node mergeOrderedLinkList(Node one, Node two) {
        Node head;
        if (one == null) {
            head = two;
        } else if (two == null) {
            head = one;
        } else {
            if (one.value <= two.value) {
                head = one;
                head.next = mergeOrderedLinkList(one.next, two);
            } else {
                head = two;
                head.next = mergeOrderedLinkList(one, two.next);
            }
        }
        return head;
    }

    public static Node loopMerge(Node one, Node two) {
        Node head;
        if (one == null) {
            return two;
        } else if (two == null) {
            return one;
        } else {
            if (one.value <= two.value) {
                head = one;
                one = head.next;
            } else {
                head = two;
                two = head.next;
            }

            Node curr = head;
            while (two != null && one != null) {
                if (one.value <= two.value) {
                    curr.next = one;
                    one = one.next;
                } else {
                    curr.next = two;
                    two = two.next;
                }
            }
            if (one == null) {
                curr.next = two;
            } else {
                curr.next = one;
            }
        }
        return head;
    }


    public static void main(String[] args) {
        Node one = new Node();
        one.value = 6;
        Node one2 = new Node();
        one2.value = 4;
        one2.next = one;
        Node one3 = new Node();
        one3.value = 2;
        one3.next = one2;
        Node oneTmp = one3;
        System.out.println("One");
        while (oneTmp != null) {
            System.out.println(oneTmp.value);
            oneTmp = oneTmp.next;
        }

        Node two = new Node();
        two.value = 5;
        Node two2 = new Node();
        two2.value = 3;
        two2.next = two;
        Node two3 = new Node();
        two3.value = 1;
        two3.next = two2;
        Node twoTmp = two3;
        System.out.println("Two");
        while (twoTmp != null) {
            System.out.println(twoTmp.value);
            twoTmp = twoTmp.next;
        }

       /* System.out.println("Merge");
        Node merge = mergeOrderedLinkList(one3, two3);
        while (merge != null) {
            System.out.println(merge.value);
            merge = merge.next;
        }*/

        System.out.println("Loop");
        Node loop = mergeOrderedLinkList(one3, two3);
        while (loop != null) {
            System.out.println(loop.value);
            loop = loop.next;
        }
    }

}
