import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size;
    private Item[] s;

    // construct an empty randomized queue
    public RandomizedQueue() {
        size = 0;
        s = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++)
            copy[i] = s[i];
        s = copy;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new NullPointerException();
        s[size++] = item;
        if (size == s.length) resize(2 * s.length);
    }

    // remove and return a random item
    public Item dequeue() {
        if (size == 0) throw new java.util.NoSuchElementException();
        int r = StdRandom.uniform(size);
        Item item = s[r];
        s[r] = s[size - 1];
        s[--size] = null;
        if (size > 0 && size == s.length / 4) resize(s.length / 2);
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0) throw new java.util.NoSuchElementException();
        int r = StdRandom.uniform(size);
        return s[r];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private int i = size;
        private int[] order;

        public RandomizedQueueIterator() {
            order = new int[i];
            for (int j = 0; j < i; ++j) {
                order[j] = j;
            }
            StdRandom.shuffle(order);
        }

        public boolean hasNext() {
            return i > 0;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new java.util.NoSuchElementException();
            return s[order[--i]];
        }
    }
}
