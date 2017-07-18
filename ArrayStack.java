/**
 * 
 * @author Jazzy Campbell 250 886 086
 *
 * @param <T>
 */
public class ArrayStack<T> implements StackADT<T> {
	private T[] stack;
	private int top = -1;
	private int cap = 0;

	/**
	 * ArrayStack constructor - with an initial capacity of 5
	 */
	public ArrayStack() {
		top = 0;
		stack = (T[]) (new Object[5]);
		cap = 5;
	}

	/**
	 * ArrayStack constructor - with the parameter being an integer for the
	 * initial capacity
	 * 
	 * @param initialCapacity
	 */
	public ArrayStack(int initialCapacity) {
		top = 0;
		stack = (T[]) (new Object[initialCapacity]);
		cap = initialCapacity;
	}

	/**
	 * A method that adds the parameter dataItem to the top of the stack
	 * 
	 * @param dataItem
	 */
	public void push(T dataItem) {
		if (size() == cap) {
			expandCapacity();
		}
		stack[top] = dataItem;
		top++;
	}

	/**
	 * A private method that increases the capacity of the stack - has
	 * conditions for if the capacity hits 20, greater than 20 (but less than
	 * 1000), and greater than 1000
	 */
	private void expandCapacity() {
		if (cap < 20) {
			T[] largerStack = (T[]) new Object[cap * 2];
			for (int x = 0; x < cap; x++) {
				largerStack[x] = stack[x];
			}
			cap = cap * 2;
			stack = largerStack;
		}
		if (cap >= 20 && cap < 1000) {
			T[] largerStack = (T[]) new Object[cap + 10];
			for (int x = 0; x < cap; x++) {
				largerStack[x] = stack[x];
			}
			cap = cap + 10;
			stack = largerStack;
		}
		if (cap > 1000) {
			throw new FullStackException("Stack overflow");
		}
	}

	/**
	 * A method that removes and returns the data item at the beginning of the
	 * stack- throws an exception if the stack is empty
	 */
	public T pop() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException("Empty Stack");
		}
		top--;
		T end = stack[top];
		stack[top] = null;
		return end;
	}

	/**
	 * Returns the data item at the top of the stack without removing it.-
	 * throws an exception if empty
	 */
	public T peek() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException("Empty Stack");
		}
		return stack[top - 1];
	}

	/**
	 * Returns a boolean for whether the stack is empty
	 */
	public boolean isEmpty() {
		return (top == 0);
	}

	/**
	 * Returns the number of items of the stack
	 */
	public int size() {
		return top;
	}

	/**
	 * Returns a string representation of the stack
	 */
	public String toString() {
		if (isEmpty()) {
			String s = "This stack is empty";
			return s;
		}
		String form = "";
		for (int i = 0; i < top; i++) {
			form = form + " " + stack[i].toString();
		}
		return form;
	}
}
