package com.sbaars.adventofcode.common;

import java.util.HashMap;
import java.util.Map;

import static java.util.stream.IntStream.range;

/**
 * A circular linked list of fixed length where all operations are O(1)
 */
public class CircularLinkedList {
    public class Node{
        private int value;
        private Node prev;
        private Node next;
    }

    private Node current;
    private final Map<Integer, Node> valueMap = new HashMap<>();

    public CircularLinkedList(int[] elements){
        Node prev = null;
        Node first = null;
        for (int element : elements) {
            Node n = new Node();
            n.value = element;
            n.prev = prev;
            valueMap.put(element, n);
            if (prev != null) prev.next = n;
            else first = n;
            prev = n;
        }
        prev.next = first;
        first.prev = prev;
        this.current = first;
    }

    public int current(){
        return current.value;
    }

    public void next(){
        current = current.next;
    }

    public int[] next(int n){
        int[] arr = new int[n];
        Node node = current;
        for(int i = 0; i<n; i++){
            node = node.next;
            arr[i] = node.value;
        }
        return arr;
    }

    public int[] nextRev(int n){
        int[] arr = new int[n];
        Node node = current;
        for(int i = n-1; i>=0; i--){
            node = node.next;
            arr[i] = node.value;
        }
        return arr;
    }

    public void insertAfter(int src, int dest){
        Node s = valueMap.get(src);
        Node d = valueMap.get(dest);
        Node oldNext = d.next;
        s.prev.next = s.next;
        s.next.prev = s.prev;
        d.next = s;
        s.next = oldNext;
        oldNext.prev = s;
        s.prev = d;
    }

    public int size(){
        return valueMap.size();
    }

    public int[] toArray(){
        return next(size());
    }

    public void setCurrent(int value){
        current = valueMap.get(value);
    }
}
