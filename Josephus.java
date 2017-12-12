import java.util.ArrayDeque;
import java.util.LinkedList;

/**
 * 08-722 Data Structures for Application Programmers.
 * Homework Assignment 2
 * Solve Josephus problem with different data structures
 * and different algorithms and compare running times
 * Out of all three methods, I would use LinkedList as List to implement functionalities of Queue.
 * First, it just has one loop, so time complexity will be O(n) and removing element is done using indexes.
 *  which will be quicker and efficient.
 *
 * Andrew ID: rushabhs
 * @author Rushabh Shah
 */
public class Josephus {

    /**
     * Uses ArrayDeque class as Queue/Deque to find the survivor's position.
     * ArraDequeue is faster than LinkedList. This method is sloer than the 3rd method and faster than 2nd method.
     * @param size Number of people in the circle that is bigger than 0
     * @param rotation Elimination order in the circle. The value has to be greater than 0
     * @return The position value of the survivor
     */
    public int playWithAD(int size, int rotation) {
        // TODO your implementation here
        if (size <= 0 || rotation <= 0) {
            throw new RuntimeException("RuntimeException");
        }
        ArrayDeque<Integer> l = new ArrayDeque<>();
        // adding elements to the LinkedList
        for (int i = 1; i <= size; i++) {
            l.offerLast(i);
        }
        while (l.size() != 1) {
            int times = 1;
            int current = rotation % l.size();
            if (current == 0) {
                while (times < l.size()) {
                    l.offerLast(l.removeFirst());
                    times++;
                }
            } else {
                while (times < current) {
                    l.offerLast(l.removeFirst());
                    times++;
                }
            }
            l.removeFirst();
        }
        return l.removeFirst();
    }

    /**
     * Uses LinkedList class as Queue/Deque to find the survivor's position.
     * This is the slowest method.
     * @param size Number of people in the circle that is bigger than 0
     * @param rotation Elimination order in the circle. The value has to be greater than 0
     * @return The position value of the survivor
     */
    public int playWithLL(int size, int rotation) {
        // TODO your implementation here
        if (size <= 0 || rotation <= 0) {
            throw new RuntimeException("RuntimeException");
        }
        LinkedList<Integer> l = new LinkedList<>();
        // adding elements to the LinkedList
        for (int i = 1; i <= size; i++) {
            l.addLast(i);
        }
        while (l.size() != 1) {
            int times = 1;
            int current = rotation % l.size();
            if (current == 0) {
                while (times < l.size()) {
                    l.addLast(l.removeFirst());
                    times++;
                }
            } else {
                while (times < current) {
                    l.addLast(l.removeFirst());
                    times++;
                }
            }
            l.removeFirst();
        }
        return l.removeFirst();
    }

    /**
     * Uses LinkedList class to find the survivor's position.
     * However, do NOT use the LinkedList as Queue/Deque
     * Instead, use the LinkedList as "List"
     * That means, it uses index value to find and remove a person to be executed in the circle
     *
     * Note: Think carefully about this method!!
     * When in doubt, please visit one of the office hours!!
     * This is the fastest out of all three methods
     * @param size Number of people in the circle that is bigger than 0
     * @param rotation Elimination order in the circle. The value has to be greater than 0
     * @return The position value of the survivor
     */
    public int playWithLLAt(int size, int rotation) {
        // TODO your implementation here
        if (size <= 0 || rotation <= 0) {
            return -1;
        }
        LinkedList<Integer> l = new LinkedList<>();
        for (int i = 1; i <= size; i++) {
            l.add(i);
        }
        int pos = rotation - 1;
        int n = size;
        while (l.size() > 1) {
            pos = pos % n;
            l.remove(pos);
            n = l.size();
            pos += rotation - 1;
        }
        return l.get(0);
    }
}
