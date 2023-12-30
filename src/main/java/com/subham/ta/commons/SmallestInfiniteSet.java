package com.subham.ta.commons;

import static java.util.Collections.swap;

import java.util.*;

class MinHeap {
    private ArrayList<Integer> heap;

    public MinHeap() {
        this.heap = new ArrayList<>();
    }

    public void add(int num) {
        heap.add(num);
        heapify();
    }

    public Integer poll() {
        if (heap.isEmpty()) {
            return null;
        }

        int rootValue = heap.get(0);

        // evacuate root
        if (heap.size() == 1) {
            heap.clear();
        } else {
            swap(heap, 0, heap.size() - 1);
            heap.remove(heap.size() - 1);
            heapify();
        }

        return rootValue;
    }

    private void heapify() {
        if (heap.size() > 1) {
            for (int position = heap.size() - 1; position > 0; --position) {
                int currentVal = heap.get(position);
                int parentPosition = (position - 1) >> 1;
                int parentValue = heap.get(parentPosition);

                if (parentValue > currentVal) {
                    swap(heap, position, parentPosition);
                }
            }
        }
    }
}

public class SmallestInfiniteSet {

    private MinHeap minHeap;
    private Set<Integer> alreadyIncludedInHeap;

    public SmallestInfiniteSet() {
        minHeap = new MinHeap();
        alreadyIncludedInHeap = new HashSet<>();
    }

    public static void main(String[] args) {
        SmallestInfiniteSet smallestInfiniteSet = new SmallestInfiniteSet();
        smallestInfiniteSet.addBack(2);
        System.out.println(smallestInfiniteSet.popSmallest());
    }

    public int popSmallest() {
        Integer minimumVal = minHeap.poll();
        alreadyIncludedInHeap.remove(minimumVal);
        return minimumVal;
    }

    public void addBack(int num) {
        if (!alreadyIncludedInHeap.contains(num)) {
            alreadyIncludedInHeap.add(num);
            minHeap.add(num);
        }
    }
}