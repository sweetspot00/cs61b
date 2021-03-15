public class LinkedListDeque<T>  {
    private class IntNode {
        T item;
        IntNode next;
        IntNode prev;

        IntNode() {

        }
        IntNode(T i, IntNode ne, IntNode pr) {
            item = i;
            next = ne;
            prev = pr;
        }
    }
    private int size;
    private IntNode sentinel;

    public LinkedListDeque() {
        sentinel = new IntNode();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }
//    public LinkedListDeque(LinkedListDeque other){
//        sentinel = new IntNode();
//        sentinel.next =sentinel;
//        sentinel.prev = sentinel;
//        size = 0;
//        for(int i=0;i<other.size();i+=1){
//            this.addLast((T) other.get(i));
//        }
//
//    }

    public void addLast(T item) {
        if (size == 0) {
            sentinel = new IntNode(item, null, null);
            IntNode newLast = new IntNode(item, sentinel, sentinel);
            sentinel.next = newLast;
            sentinel.prev = newLast;
        } else {
            IntNode oldLast = sentinel.prev;
            IntNode newLast = new IntNode(item, sentinel, oldLast);
            oldLast.next = newLast;
            sentinel.prev = newLast;
        }
        size += 1;
    }
    public void addFirst(T item) {
        if (size == 0) {
            sentinel = new IntNode(item, null, null);
            IntNode newLast = new IntNode(item, sentinel, sentinel);
            sentinel.next = newLast;
            sentinel.prev = newLast;
        } else {
            IntNode oldFirst = sentinel.next;
            IntNode newFirst = new IntNode(item, oldFirst, sentinel);
            oldFirst.prev = newFirst;
            sentinel.next = newFirst;
        }

        size += 1;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }
    public void printDeque() {
        for (int i = 0; i < size; i += 1) {
            System.out.print(this.get(i));
            System.out.print(" ");
        }
        System.out.println();
    }
    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            T res = sentinel.next.item;
            IntNode newFirst = sentinel.next.next;
            sentinel.next = newFirst;
            newFirst.prev = sentinel;
            size -= 1;
            return res;
        }
    }
    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            T res = sentinel.prev.item;
            IntNode newLast = sentinel.prev.prev;
            newLast.next = sentinel;
            sentinel.prev = newLast;
            size -= 1;
            return res;
        }

    }
    public T get(int index) {
        if (index > size - 1) {
            return null;
        } else {
            IntNode tmp = sentinel.next;
            while (index > 0) {
                tmp = tmp.next;
                index -= 1;
            }
            return tmp.item;
        }

    }
    private T helpergetRecursive(IntNode tmp, int index) {
        if (index <= 0) {
            return tmp.item;
        } else {
            return helpergetRecursive(tmp.next, index - 1);
        }
    }
    public T getRecursive(int index) {
        if (index > size - 1) {
            return null;
        }
        return helpergetRecursive(sentinel.next, index);
    }

}
