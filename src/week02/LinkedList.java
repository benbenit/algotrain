package week02;

public class LinkedList {
    public class Node {
        public int data;
        public Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node head = null;

    /**
     * 遍历
     */
    public void travel() {
        Node p = head;
        while (p != null) {
            System.out.println(p.data);
            p = p.next;
        }
    }
    /**
     * 查找
     */
    public Node find(int value) {
        Node p = head;
        while (p != null) {
            if (p.data == value) {
                return p;
            } else {
                p = p.next;
            }
        }
        return null;
    }

    /**
     * 链头插入
     */
    public void insertAtHead(int value) {
        Node newNode = new Node(value, null);
        newNode.next = head;
        head = newNode;
    }

    /**
     * 链尾插入
     */
    public void insertAtTail(int value) {
        Node newNode = new Node(value, null);

        if (head == null) {
            head = newNode;
        } else {
            Node p = head;
            // 前面用p判断的时候,用q记录前一个节点的位置,这里直接用p.next进行判断
            while (p.next != null) {
                p = p.next;
            }
            p.next = newNode;
        }
    }


}
