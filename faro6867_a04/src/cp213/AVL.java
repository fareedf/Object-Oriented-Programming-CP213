package cp213;

/**
 * Implements an AVL (Adelson-Velsky Landis) tree. Extends BST.
 *
 * @author your name here
 * @author David Brown
 * @version 2023-09-06
 */
public class AVL<T extends Comparable<T>> extends BST<T> {

	/**
	 * Returns the balance item of node. If greater than 1, then left heavy, if less
	 * than -1, then right heavy. If in the range -1 to 1 inclusive, the node is
	 * balanced. Used to determine whether to rotate a node upon insertion.
	 *
	 * @param node The TreeNode to analyze for balance.
	 * @return A balance number.
	 */
	private int balance(final TreeNode<T> node) {

		// your code here

		return this.nodeHeight(node.getLeft()) - this.nodeHeight(node.getRight());
	}

	/**
	 * Rebalances the current node if its children are not balanced.
	 *
	 * @param node the node to rebalance
	 * @return replacement for the rebalanced node
	 */
	private TreeNode<T> rebalance(TreeNode<T> node) {

		// your code here

		int balance = this.balance(node);
		if (balance > 1) {
			if (this.balance(node.getLeft()) < 0) {
				node.setLeft(rotateLeft(node.getLeft()));
			}
			node = rotateRight(node);
		} else if (balance < -1) {
			if (this.balance(node.getRight()) > 0) {
				node.setRight(rotateRight(node.getRight()));
			}
			node = rotateLeft(node);
		}

		return node;
	}

	/**
	 * Performs a left rotation around node.
	 *
	 * @param node The subtree to rotate.
	 * @return The new root of the subtree.
	 */
	private TreeNode<T> rotateLeft(final TreeNode<T> node) {

		// your code here
		if (node == null || node.getRight() == null) {
			return node;
		}

		TreeNode<T> root = node.getRight();
		node.setRight(root.getLeft());
		root.setLeft(node);

		node.updateHeight();
		root.updateHeight();

		return root;
	}

	/**
	 * Performs a right rotation around node.
	 *
	 * @param node The subtree to rotate.
	 * @return The new root of the subtree.
	 */
	private TreeNode<T> rotateRight(final TreeNode<T> node) {

		// your code here

		if (node == null || node.getLeft() == null) {
			return node;
		}

		TreeNode<T> root = node.getLeft();
		node.setLeft(root.getRight());
		root.setRight(node);
		node.updateHeight();

		return root;
	}

	/**
	 * Auxiliary method for insert. Inserts data into this AVL.
	 *
	 * @param node The current node (TreeNode).
	 * @param data Data to be inserted into the node.
	 * @return The inserted node.
	 */
	@Override
	protected TreeNode<T> insertAux(TreeNode<T> node, final CountedItem<T> data) {

		// your code here

		// Perform standard BST insert
		if (node == null) {
			node = new TreeNode(data);
			this.size++;
			data.incrementCount();
		} else {
			int result = node.getdata().compareTo(data);

			if (result > 0) {
				node.setLeft(insertUpdate(node.getLeft(), data));
			} else if (result < 0) {
				node.setRight(insertUpdate(node.getRight(), data));
			} else {
				this.size++;
			}
		}

		return updateBalance(node);
	}

	private TreeNode<T> insertUpdate(TreeNode<T> node, CountedItem<T> data) {
		node = insertAux(node, data);
		node.updateHeight();
		return node;
	}

	private TreeNode<T> updateBalance(TreeNode<T> node) {
		if (node != null) {
			node.updateHeight();
			int balance = this.balance(node);

			if (balance > 1) {
				if (this.balance(node.getLeft()) < 0) {
					node.setLeft(rotateLeft(node.getLeft()));
				}
				node = rotateRight(node);
			} else if (balance < -1) {
				if (this.balance(node.getRight()) > 0) {
					node.setRight(rotateRight(node.getRight()));
				}
				node = rotateLeft(node);
			}
		}

		return node;
	}

	/**
	 * Auxiliary method for valid. Determines if a subtree based on node is a valid
	 * subtree. An AVL must meet the BST validation conditions, and additionally be
	 * balanced in all its subtrees - i.e. the difference in height between any two
	 * children must be no greater than 1.
	 *
	 * @param node The root of the subtree to test for validity.
	 * @return true if the subtree base on node is valid, false otherwise.
	 */
	@Override
	protected boolean isValidAux(final TreeNode<T> node, TreeNode<T> minNode, TreeNode<T> maxNode) {

		// your code here
		// Base case
		if (node == null) {
			return true;
		}

		if ((minNode != null && minNode.getdata().compareTo(node.getdata()) > 0)
				|| (maxNode != null && maxNode.getdata().compareTo(node.getdata()) < 0)) {
			return false;
		}

		return isValidAux(node.getLeft(), minNode, node) && isValidAux(node.getRight(), node, maxNode);

	}

	/**
	 * Determines whether two AVLs are identical.
	 *
	 * @param target The AVL to compare this AVL against.
	 * @return true if this AVL and target contain nodes that match in position,
	 *         item, count, and height, false otherwise.
	 */
	public boolean equals(final AVL<T> target) {
		return super.equals(target);
	}

}
