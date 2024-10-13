package cp213;

/**
 * Implements a Popularity Tree. Extends BST.
 *
 * @author your name here
 * @author David Brown
 * @version 2023-09-06
 */
public class PopularityTree<T extends Comparable<T>> extends BST<T> {

	/**
	 * Auxiliary method for valid. May force node rotation if the retrieval count of
	 * the located node item is incremented.
	 *
	 * @param node The node to examine for key.
	 * @param key  The item to search for. Count is updated to count in matching
	 *             node item if key is found.
	 * @return The updated node.
	 */
	private TreeNode<T> retrieveAux(TreeNode<T> node, final CountedItem<T> key) {

		// your code here
		if (node == null) {
			return null;
		}

		int compare = node.getdata().compareTo(key);
		this.comparisons++;

		if (compare == 0) {
			node.getdata().incrementCount();
			node.updateHeight(); // Update height after incrementing count
			return node;
		} else if (compare > 0) {
			TreeNode<T> temp = this.retrieveAux(node.getLeft(), key);
			if (temp != null) {
				if (node.getLeft().getdata().getCount() < temp.getdata().getCount()) {
					node.setLeft(temp);
				}
				if (node.getdata().getCount() < temp.getdata().getCount()) {
					TreeNode<T> r = this.rotateRight(node);
					if (node.getdata().compareTo(this.root.getdata()) == 0) {
						this.root = r;
					}
				}
			}

			return temp;

		} else {
			TreeNode<T> temp = this.retrieveAux(node.getRight(), key);

			if (temp != null) {
				if (node.getRight().getdata().getCount() < temp.getdata().getCount()) {
					node.setRight(temp);
				}
				if (node.getdata().getCount() < temp.getdata().getCount()) {
					TreeNode<T> newRoot = this.rotateLeft(node);
					if (node.getdata().compareTo(this.root.getdata()) == 0) {
						this.root = newRoot;
					}
				}
			}

			return temp;
		}
	}

	/**
	 * Performs a left rotation around node.
	 *
	 * @param parent The subtree to rotate.
	 * @return The new root of the subtree.
	 */
	private TreeNode<T> rotateLeft(final TreeNode<T> parent) {

		// your code here
		TreeNode<T> temp = parent.getRight();
		parent.setRight(temp.getLeft());
		temp.setLeft(parent);

		parent.updateHeight();
		temp.updateHeight();

		return temp;
	}

	/**
	 * Performs a right rotation around {@code node}.
	 *
	 * @param parent The subtree to rotate.
	 * @return The new root of the subtree.
	 */
	private TreeNode<T> rotateRight(final TreeNode<T> parent) {

		// your code here

		TreeNode<T> temp = parent.getLeft();
		parent.setLeft(temp.getRight());
		temp.setRight(parent);

		parent.updateHeight();
		temp.updateHeight();

		return temp;
	}

	/**
	 * Replaces BST insertAux - does not increment count on repeated insertion.
	 * Counts are incremented only on retrieve.
	 */
	@Override
	protected TreeNode<T> insertAux(TreeNode<T> node, final CountedItem<T> data) {

		// your code here

		if (node == null) {
			TreeNode<T> node1 = new TreeNode<T>(data);
			this.size++;
			return node1;
		} else if (data.compareTo(node.getdata()) > 0) {
			node.setRight(insertAux(node.getRight(), data));
		} else if (data.compareTo(node.getdata()) < 0) {
			node.setLeft(insertAux(node.getLeft(), data));
		}
		node.updateHeight();
		return node;
	}

	/**
	 * Auxiliary method for valid. Determines if a subtree based on node is a valid
	 * subtree. An Popularity Tree must meet the BST validation conditions, and
	 * additionally the counts of any node data must be greater than or equal to the
	 * counts of its children.
	 *
	 * @param node The root of the subtree to test for validity.
	 * @return true if the subtree base on node is valid, false otherwise.
	 */
	@Override
	protected boolean isValidAux(final TreeNode<T> node, TreeNode<T> minNode, TreeNode<T> maxNode) {

		// your code here
		if (node == null) {
			return true;
		} else if (minNode != null) {
			if (minNode.getdata().compareTo(node.getdata()) > 0
					|| minNode.getdata().getCount() != node.getdata().getCount()) {
				return false;
			}
		} else if (maxNode != null) {
			if (maxNode.getdata().compareTo(node.getdata()) < 0
					|| maxNode.getdata().getCount() != node.getdata().getCount()) {
				return false;
			}
		}
		return isValidAux(node.getLeft(), minNode, node) && isValidAux(node.getRight(), node, maxNode);
	}

	/**
	 * Determines whether two PopularityTrees are identical.
	 *
	 * @param target The PopularityTree to compare this PopularityTree against.
	 * @return true if this PopularityTree and target contain nodes that match in
	 *         position, item, count, and height, false otherwise.
	 */
	public boolean equals(final PopularityTree<T> target) {
		return super.equals(target);
	}

	/**
	 * Very similar to the BST retrieve, but increments the data count here instead
	 * of in the insertion.
	 *
	 * @param key The key to search for.
	 */
	@Override
	public CountedItem<T> retrieve(CountedItem<T> key) {

		// your code here
		CountedItem<T> val = null;
		TreeNode<T> node = retrieveAux(this.root, key);
		if (node != null) {
			val = node.getdata();
		}
		return val;
	}

}
