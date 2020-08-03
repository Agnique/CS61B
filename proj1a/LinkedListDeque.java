public class LinkedListDeque<T> {
    public class Node {
        public T item;
        public Node next;
        public Node prev;

        public Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }

        public T get(int index) {
            if (index > 0) {
                return get(index-1);
            }
            return item;
        }
    }

        private Node sentinel;
        private int size;

        public LinkedListDeque() {
            sentinel = new Node(null, null, null);
            sentinel.prev = sentinel;
            sentinel.next = sentinel;
            size = 0;
        }

        public LinkedListDeque(LinkedListDeque other) {
            sentinel = new Node(null, null, null);
            sentinel.prev = sentinel;
            sentinel.next = sentinel;
            size = other.size;

            for (int i=0; i< other.size(); i++) {
                addLast((T) other.get(i));
            }
        }

        public void addFirst(T item) {
            Node curr = new Node(item, sentinel, sentinel.next);
            sentinel.next = curr;
            size += 1;
        }

        public void addLast(T item) {
            Node curr = new Node(item, sentinel.prev, sentinel);
            sentinel.prev = curr;
            size += 1;
        }

        public boolean isEmpty() {
            return size > 0 ? false : true;
        }

        public int size() {
            return size;
        }

        public void printDeque() {
            Node p = sentinel.next;
            while(p != sentinel) {
                System.out.print(p.item);
                System.out.print(' ');
                p = p.next;
            }
            System.out.println(' ');
        }

        public T removeFirst() {
            if (size > 0) {
                T val = sentinel.next.item;
                Node p = sentinel.next.next;
                p.prev = sentinel;
                sentinel.next = p;
                size -= 1;
                return val;
            }
            return null;
        }

        public T removeLast() {
            if (size > 0) {
                T val = sentinel.prev.item;
                Node p = sentinel.prev.prev;
                p.next = sentinel;
                sentinel.prev = p;
                size -= 1;
                return val;
            }
            return null;
        }

        public T get(int index) {
            if (index < 0 || index >= size) {
                return null;
            }
            Node p = sentinel;
            while (index >= 0) {
                p = p.next;
                index -= 1;
            }
            return p.item;
        }

        public T getRecursive(int index) {
            return sentinel.next.get(index);
        }
}