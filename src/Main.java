public class Main {

    static class ListNode {
        int value;
        ListNode next;

        public ListNode(int value) {
            this.value = value;
        }

        public ListNode(int value, ListNode next) {
            this.value = value;
            this.next = next;
        }
    }

    public static void main(String[] args) {
//        intro();
//        ListNode first = new ListNode(1, new ListNode(2, new ListNode(3)));
//        ListNode second = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(4))));
//        ListNode merge = merge(first, second);
//        iterate(merge); // 1 -> 1 -> 2 -> 2 -> 2 -> 3 -> 4

        ListNode node = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(4,
                new ListNode(7, new ListNode(4)))))); // 1 -> 2 -> 2 -> 4 -> 7 -> 4
        System.out.println(size(node)); // 6
        System.out.println(asString(node)); // [1 -> 2 -> 2 -> 4]
        System.out.println(getByIndex(node, 3)); //4

        //System.out.println(getByIndex(node, 0)); // 1
        //System.out.println(getByIndex(node, 3)); // 4
        //System.out.println(getByIndex(node, 4)); // new IndexOutOfBoundsException
    }

    /**
     * Посчитать размер списка.
     */
    static int size(ListNode head) {
        int count = 0;
        if(head != null){
            count++;
            while(head.next != null){
                count++;
                head = head.next;
            }
        }
        else{
            return 0;
        }
        return count;
    }

    /**
     * Написать строковое представление списка в формате
     * [first_value -> second_value -> ... -> last_value]
     */
    static String asString(ListNode head) {
       ListNode asStr = head; // [null]
        String res = new String("[ ");
        while (asStr != null) {
            //System.out.print(asStr.value + " -> ");
            res = res + asStr.value + " -> ";
            asStr = asStr.next;
        }
            //System.out.println("null");
            res = res + "null ]";
        return res;
    }

    /**
     * Найти значение по индексу
     */
    static int getByIndex(ListNode head, int index) {
        ListNode n = head;
        int count = 0;
        while (count < index){
            if (head != null){
                count++;
            }
            n = n.next;
            }
        return n.value;
    }



        //        public Node get(int i) {
//            if (i >= size()) {
//                throw new IndexOutOfRangeException();
//            }
//            Node n = first;
//            int j = 0;
//            while (i != j) {
//                n = n.getNext();
//            }
//            return n;
//
//
//    }

    /**
     * Дано 2 отсортированных связных списка.
     * Нужно их смержить и получить новый отсортированный связный список.
     *
     * (1, 2, 3), (1, 2, 2, 4) -> (1, 1, 2, 2, 2, 3, 4)
     * (1, 2), (3, 4, 5, 6) -> 1, 2, 3, 4, 5, 6
     */

    // (a && b) ~ !a || !b
    // a || b ~ !a && !b
    static ListNode merge(ListNode first, ListNode second) {
        ListNode head = null; // 1 -> 1 -> 2
        ListNode iterator = null; // 2
        while (first != null || second != null) {
            int nextValue = -1; // 2

            if (first == null) { // second != null
                nextValue = second.value;
                second = second.next;
            } else if (second == null) {
                nextValue = first.value;
                first = first.next;
            } else if (first.value > second.value) {
                nextValue = second.value;
                second = second.next;
            } else { // if (first.value <= second.value) {
                nextValue = first.value;
                first = first.next;
            }

            if (head == null) {
                head = new ListNode(nextValue);
                iterator = head;
            } else {
                iterator.next = new ListNode(nextValue);
                iterator = iterator.next;
            }
        }
        return head;
    }

    static void intro() {
        // head -> second -> third -> ... -> last -> null
        // 1 -> 2 -> 3
        ListNode third = new ListNode(3); // 3
        ListNode second = insertFirst(third, 2); // 2 -> 3
        ListNode first = insertFirst(second, 1); // 1 -> 2 -> 3
        iterate(first); // 1 -> 2 -> 3 -> null

        ListNode head = insertFirst(first, 0); // 0 -> 1 -> 2 -> 3
        iterate(head); // 0 -> 1 -> 2 -> 3 -> null

        insertLast(head, 4); // 0 -> 1 -> 2 -> 3 -> 4
        iterate(head); // 0 -> 1 -> 2 -> 3 -> 4 -> null

        remove(head, 2);
        iterate(head); // 0 -> 1 -> 3 -> 4 -> null

        head = reverse(head);
        iterate(head); // 4 -> 3 -> 1 -> 0 -> null
    }

    /**
     * Распечатать все элементы связного списка
     */
    static void iterate(ListNode node) {
        ListNode iter = node; // [null]
        while (iter != null) {
            System.out.print(iter.value + " -> ");
            iter = iter.next;
        }
        System.out.println("null");
    }

    /**
     * Создать список, получанный из head прибавлением value в начало
     */
    static ListNode insertFirst(ListNode head, int value) {
        return new ListNode(value, head);
    }

    /**
     * Добавить к существующему списку в конце значение value
     */
    static void insertLast(ListNode head, int value) {
        ListNode last = new ListNode(value);
        ListNode iter = head;
        while (iter.next != null) {
            iter = iter.next;
        }
        iter.next = last;
    }

    /**
     * Удаляет из списка первое вхождение value.
     */
    static void remove(ListNode head, int value) {
        // iter -> 1
        // iter.next -> 2
        // iter.next.next -> 3

        // -> .. -> 1 -> 1 -> null

        ListNode iter = head; // [null]
        while (iter.next != null) {
            if (iter.next.value == value) {
                iter.next = iter.next.next;
                break;
            }
            iter = iter.next;
        }
    }

    static ListNode reverse(ListNode head) {
        // 0 -> 1 -> 3 -> 4 -> null
        ListNode node = null;
        ListNode iterator = head;
        while (iterator != null) {
            if (node == null) {
                node = new ListNode(iterator.value);
            } else {
                node = insertFirst(node, iterator.value);
            }
            iterator = iterator.next;
        }
        return node;
    }

}