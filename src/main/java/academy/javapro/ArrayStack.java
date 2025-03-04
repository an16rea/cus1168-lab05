package academy.javapro;

import java.util.Arrays;
import java.util.EmptyStackException;

public class ArrayStack<T extends Number> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;
    
    private T[] stack;
    private int size;
    private int operations;
    private final int stackId;
    
    private static int totalStacks = 0;
    private static int totalElements = 0;
    
    @SuppressWarnings("unchecked")
    public ArrayStack() {
        stack = (T[]) new Number[DEFAULT_CAPACITY];
        size = 0;
        operations = 0;
        stackId = ++totalStacks;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public int size() {
        return size;
    }
    
    public void push(T value) {
        operations++;
        if (size == stack.length) {
            resize();
        }
        stack[size++] = value;
        totalElements++;
    }
    
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        operations++;
        T value = stack[--size];
        stack[size] = null;
        totalElements--;
        return value;
    }
    
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        operations++;
        return stack[size - 1];
    }
    
    public void addTopTwo() {
        if (size < 2) {
            throw new IllegalStateException("Not enough elements to add");
        }
        T first = pop();
        T second = pop();
        Number sum = first.doubleValue() + second.doubleValue();
        if (first instanceof Integer && second instanceof Integer) {
            push((T) Integer.valueOf(sum.intValue()));
        } else {
            push((T) Double.valueOf(sum.doubleValue()));
        }
    }
    
    private void resize() {
        int newCapacity = (int) (stack.length * GROWTH_FACTOR);
        stack = Arrays.copyOf(stack, newCapacity);
    }
    
    public String getStats() {
        return "Stack #" + stackId + ": Size=" + size + ", Operations=" + operations;
    }
    
    public static String getGlobalStats() {
        return "Total stacks: " + totalStacks + ", Total elements: " + totalElements;
    }
}
