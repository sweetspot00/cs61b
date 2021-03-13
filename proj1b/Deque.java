public interface Deque<Item> {
    void addFirst(Item item);
    void addLast(Item item);
    void printDeque();
    Item removeFirst();
    Item removeLast();
    Item get(int index);
    int size();
    default boolean isEmpty() {
        return size() == 0;
    }

}
