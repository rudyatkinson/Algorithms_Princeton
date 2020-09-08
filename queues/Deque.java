import java.util.Iterator;

public class Deque implements Iterable<Item> {

    Node first, last;
    int size;

    class Node {
        Item item;
        Node next;
        Node previous;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new NullPointerException();
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        first.previous = null;
        if (isEmpty())
            last = first;
        else
            oldFirst.previous = first;
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null)
            throw new java.lang.NullPointerException();
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.previous = oldLast;
        if (isEmpty())
            first = last;
        else
            oldLast.next = last;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        Item copy = first.item;
        first = first.next;
        size--;
        if (isEmpty())
            last = first;
        else
            first.previous = null;
        return copy;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        Item copy = last.item;
        last = last.previous;
        size--;
        if (isEmpty())
            first = last;
        else
            last.next = null;
        return copy;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DeqIterator();
    }

    class DeqIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
