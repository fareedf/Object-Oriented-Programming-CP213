package cp213;

/**
 * A single linked list structure of <code>Node T</code> objects. These data
 * objects must be Comparable - i.e. they must provide the compareTo method.
 * Only the <code>T</code> value contained in the priority queue is visible
 * through the standard priority queue methods. Extends the
 * <code>SingleLink</code> class.
 *
 * @author your name, id, email here
 * @version 2023-09-06
 * @param <T> this SingleList data type.
 */
public class SingleList<T extends Comparable<T>> extends SingleLink<T> {

	/**
	 * Searches for the first occurrence of key in this SingleList. Private helper
	 * methods - used only by other ADT methods.
	 *
	 * @param key The value to look for.
	 * @return A pointer to the node previous to the node containing key.
	 */
	private SingleNode<T> linearSearch(final T key) {

		// your code here

		SingleNode<T> prev = null;
		SingleNode<T> curr = this.front;
		while (curr != null && curr.getItem() != key) {
			prev = curr;
			curr = curr.getNext();

		}
		if (curr == null) {
		}
		return curr;
	}

	/**
	 * Appends data to the end of this SingleList.
	 *
	 * @param data The value to append.
	 */
	public void append(final T data) {

		SingleNode<T> node = new SingleNode<T>(data, null);
		if (this.length == 0) {
			this.rear = node;
			this.front = node;

		} else {
			this.rear.setNext(node);
			this.rear = node;
		}
		this.length += 1;
		return;
	}

	/**
	 * Removes duplicates from this SingleList. The list contains one and only one
	 * of each value formerly present in this SingleList. The first occurrence of
	 * each value is preserved.
	 */
	public void clean() {

		// your code here
		SingleNode<T> curr = this.front;
		SingleNode<T> temp = null;

		while (curr != null) {
			temp = curr;

			while (temp.getNext() != null) {
				if (curr.getItem().compareTo(temp.getNext().getItem()) == 0) {
					temp.setNext(temp.getNext().getNext());
				} else {
					temp = temp.getNext();
				}
			}
			curr = curr.getNext();
		}
		return;
	}

	/**
	 * Combines contents of two lists into a third. Values are alternated from the
	 * origin lists into this SingleList. The origin lists are empty when finished.
	 * NOTE: data must not be moved, only nodes.
	 *
	 * @param left  The first list to combine with this SingleList.
	 * @param right The second list to combine with this SingleList.
	 */
	public void combine(final SingleList<T> left, final SingleList<T> right) {

		// your code here
		boolean b = true;
		int l = left.length + right.length;
		int key = 0;
		while (key < l) {
			if (b && left.length > 0) {
				this.moveFrontToRear(left);
			} else if (right.length > 0) {
				this.moveFrontToRear(right);
			}
			b = !b;
			key += 1;
		}
		return;
	}

	/**
	 * Determines if this SingleList contains key.
	 *
	 * @param key The key value to look for.
	 * @return true if key is in this SingleList, false otherwise.
	 */
	public boolean contains(final T key) {

		// your code here
		boolean c = false;
		if (this.linearSearch(key) != null) {
			c = true;
		}
		return c;
	}

	/**
	 * Finds the number of times key appears in list.
	 *
	 * @param key The value to look for.
	 * @return The number of times key appears in this SingleList.
	 */
	public int count(final T key) {

		// your code here
		int count = 0;
		SingleNode<T> curr = this.front;
		while (curr != null) {
			if (curr.getItem().compareTo(key) == 0) {
				count += 1;
			}
			curr = curr.getNext();
		}
		return count;
	}

	/**
	 * Finds and returns the value in list that matches key.
	 *
	 * @param key The value to search for.
	 * @return The value that matches key, null otherwise.
	 */
	public T find(final T key) {

		// your code here
		T data;
		SingleNode<T> curr = this.linearSearch(key);
		if (curr == null) {
			data = null;
		} else {
			data = curr.getItem();
		}
		return data;
	}

	/**
	 * Get the nth item in this SingleList.
	 *
	 * @param n The index of the item to return.
	 * @return The nth item in this SingleList.
	 * @throws ArrayIndexOutOfBoundsException if n is not a valid index.
	 */
	public T get(final int n) throws ArrayIndexOutOfBoundsException {

		// your code here
		SingleNode<T> curr = this.front;
		int c = 0;
		if (n >= 0) {
			c = 0;
		} else {
			c = -1 * this.length;
		}
		while (c < n && curr != null) {
			curr = curr.getNext();
			c += 1;
		}
		T data = curr.getItem();
		return data;
	}

	/**
	 * Determines whether two lists are identical.
	 *
	 * @param source The list to compare against this SingleList.
	 * @return true if this SingleList contains the same values in the same order as
	 *         source, false otherwise.
	 */
	public boolean identical(final SingleList<T> source) {

		// your code here
		boolean same = true;
		if (this.length != source.length) {
			same = false;
		} else {
			SingleNode<T> node = this.front;
			SingleNode<T> node1 = source.front;

			while (node != null && same) {
				if (node1.getItem().compareTo(node.getItem()) != 0) {
					same = false;
				}
				node = node.getNext();
				node1 = node1.getNext();
			}
		}
		return same;
	}

	/**
	 * Finds the first location of a value by key in this SingleList.
	 *
	 * @param key The value to search for.
	 * @return The index of key in this SingleList, -1 otherwise.
	 */
	public int index(final T key) {

		// your code here
		SingleNode<T> prev = null;
		SingleNode<T> curr = this.front;
		int i = -1;
		if (curr != null) {
			i = 0;
		}
		while (curr != null && curr.getItem() != key) {
			prev = curr;
			curr = curr.getNext();
			i += 1;

		}
		if (curr == null) {
			i = -1;
		}
		return i;
	}

	/**
	 * Inserts value into this SingleList at index i. If i greater than the length
	 * of this SingleList, append data to the end of this SingleList.
	 *
	 * @param i    The index to insert the new data at.
	 * @param data The new value to insert into this SingleList.
	 */
	public void insert(int i, final T data) {

		// your code here
		if (this.length == 0) {
			SingleNode<T> node = new SingleNode<T>(data, null);
			this.rear = node;
			this.front = node;
		} else if (i < -this.length || i == 0) {
			SingleNode<T> node = new SingleNode<T>(data, this.front);
			this.front = node;
		} else if (i > this.length - 1) {
			SingleNode<T> node = new SingleNode<T>(data, null);
			this.rear.setNext(node);
			this.rear = node;
		} else {
			if (i >= -this.length && i < 0) {
				i = this.length + i;
			}

			SingleNode<T> prev = this.front;
			int count = 0;

			while (prev.getNext() != null || count <= i) {
				if (count == (i - 1)) {
					prev.setNext(new SingleNode<T>(data, prev.getNext()));
				}
				count += 1;
				prev = prev.getNext();
			}
		}
		this.length += 1;
		return;
	}

	/**
	 * Creates an intersection of two other SingleLists into this SingleList. Copies
	 * data to this SingleList. left and right SingleLists are unchanged. Values
	 * from left are copied in order first, then values from right are copied in
	 * order.
	 *
	 * @param left  The first SingleList to create an intersection from.
	 * @param right The second SingleList to create an intersection from.
	 */
	public void intersection(final SingleList<T> left, final SingleList<T> right) {

		// your code here

		SingleNode<T> curr = left.front;
		while (curr != null) {
			T data = curr.getItem();
			SingleNode<T> node = right.linearSearch(data);

			if (node != null) {
				node = this.linearSearch(data);

				if (node == null) {
					this.append(data);
				}
			}
			curr = curr.getNext();
		}
		return;
	}

	/**
	 * Finds the maximum value in this SingleList.
	 *
	 * @return The maximum value.
	 */
	public T max() {

		// your code here

		SingleNode<T> prev = null;
		SingleNode<T> curr = this.front;
		T max = this.front.getItem();

		while (curr != null) {
			T data = curr.getItem();
			if (max.compareTo(data) < 0) {
				max = data;
			}
			prev = curr;
			curr = curr.getNext();
		}
		return max;
	}

	/**
	 * Finds the minimum value in this SingleList.
	 *
	 * @return The minimum value.
	 */
	public T min() {

		// your code here
		SingleNode<T> prev = null;
		SingleNode<T> curr = this.front;
		T min = this.front.getItem();
		while (curr != null) {
			T data = curr.getItem();
			if (min.compareTo(data) > 0) {
				min = data;
			}
			prev = curr;
			curr = curr.getNext();
		}
		return min;
	}

	/**
	 * Inserts value into the front of this SingleList.
	 *
	 * @param data The value to insert into the front of this SingleList.
	 */
	public void prepend(final T data) {

		// your code here
		SingleNode<T> node = new SingleNode<T>(data, this.front);
		this.front = node;
		if (this.length == 0) {
			this.rear = node;
		}
		this.length += 1;
		return;
	}

	/**
	 * Finds, removes, and returns the value in this SingleList that matches key.
	 *
	 * @param key The value to search for.
	 * @return The value matching key, null otherwise.
	 */
	public T remove(final T key) {

		// your code here
		SingleNode<T> curr = this.front;
		SingleNode<T> prev = null;
		boolean value = false;
		T data = null;
		while (curr != null && value == false) {

			if (curr.getItem().compareTo(key) == 0) {
				data = curr.getItem();
				if (this.front == curr && curr.getNext() != null) {
					this.front = curr.getNext();
					this.length -= 1;
				} else if (this.front == curr && curr.getNext() == null) {
					this.front = null;
					this.rear = null;
					this.length = 0;

				} else if (this.rear == curr) {
					this.rear = prev;
					prev.setNext(null);
					this.length -= 1;
				} else {
					prev.setNext(curr.getNext());
					this.length -= 1;
				}
				value = true;
			}
			prev = curr;
			curr = curr.getNext();
		}
		return data;
	}

	/**
	 * Removes the value at the front of this SingleList.
	 *
	 * @return The value at the front of this SingleList.
	 */
	public T removeFront() {

		// your code here
		T data = null;
		if (this.length == 1) {
			data = this.front.getItem();
			this.rear = null;
			this.front = null;
			this.length = 0;

		} else if (this.length > 1) {
			data = this.front.getItem();
			this.front = this.front.getNext();
			this.length -= 1;
		}
		return data;
	}

	/**
	 * Finds and removes all values in this SingleList that match key.
	 *
	 * @param key The value to search for.
	 */
	public void removeMany(final T key) {

		// your code here
		SingleNode<T> prev = null;
		SingleNode<T> curr = this.front;

		while (curr != null) {

			if (curr.getItem().compareTo(key) == 0) {
				if (this.front == curr && curr.getNext() != null) {
					this.front = curr.getNext();
					this.length -= 1;
				} else if (this.front == curr && curr.getNext() == null) {
					this.front = null;
					this.rear = null;
					this.length = 0;

				} else if (this.rear == curr) {
					prev.setNext(null);
					this.rear = prev;
					this.length -= 1;
				} else {
					prev.setNext(curr.getNext());
					this.length -= 1;
				}
			}
			prev = curr;
			curr = curr.getNext();
		}
		return;
	}

	/**
	 * Reverses the order of the values in this SingleList.
	 */
	public void reverse() {

		// your code here
		this.rear = this.front;
		SingleNode<T> prev = null;
		SingleNode<T> curr = this.front;
		SingleNode<T> node;

		while (curr != null) {
			node = curr.getNext();
			curr.setNext(prev);
			prev = curr;
			curr = node;
		}
		this.front = prev;

		return;
	}

	/**
	 * Splits the contents of this SingleList into the left and right SingleLists.
	 * Moves nodes only - does not move value or call the high-level methods insert
	 * or remove. this SingleList is empty when done. The first half of this
	 * SingleList is moved to left, and the last half of this SingleList is moved to
	 * right. If the resulting lengths are not the same, left should have one more
	 * item than right. Order is preserved.
	 *
	 * @param left  The first SingleList to move nodes to.
	 * @param right The second SingleList to move nodes to.
	 */
	public void split(final SingleList<T> left, final SingleList<T> right) {

		// your code here
		int len = this.length;
		int num = len / 2;
		if (len % 2 != 0) {
			num += 1;
		}
		int counter = 0;
		while (counter < len) {
			if (counter < len) {
				if (counter < num) {
					left.moveFrontToRear(this);
				} else {
					right.moveFrontToRear(this);
				}
			}
			counter += 1;
		}

		return;
	}

	/**
	 * Splits the contents of this SingleList into the left and right SingleLists.
	 * Moves nodes only - does not move value or call the high-level methods insert
	 * or remove. this SingleList is empty when done. Nodes are moved alternately
	 * from this SingleList to left and right. Order is preserved.
	 *
	 * @param left  The first SingleList to move nodes to.
	 * @param right The second SingleList to move nodes to.
	 */
	public void splitAlternate(final SingleList<T> left, final SingleList<T> right) {

		// your code here
		boolean b = true;
		while (this.front != null) {
			if (b == true) {
				left.moveFrontToRear(this);
			} else {
				right.moveFrontToRear(this);
			}
			b = !b;
		}

		return;
	}

	/**
	 * Creates a union of two other SingleLists into this SingleList. Copies value
	 * to this list. left and right SingleLists are unchanged. Values from left are
	 * copied in order first, then values from right are copied in order.
	 *
	 * @param left  The first SingleList to create a union from.
	 * @param right The second SingleList to create a union from.
	 */
	public void union(final SingleList<T> left, final SingleList<T> right) {

		// your code here
		SingleNode<T> curr = left.front;

		while (curr != null) {
			T data = curr.getItem();
			SingleNode<T> node = this.linearSearch(data);
			if (node == null) {
				this.append(data);
			}
			curr = curr.getNext();
		}
		curr = right.front;
		while (curr != null) {
			T data = curr.getItem();
			SingleNode<T> node = this.linearSearch(data);
			if (node == null) {
				this.append(data);
			}
			curr = curr.getNext();
		}

		return;
	}
}
