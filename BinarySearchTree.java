import java.util.ArrayList;

public class BinarySearchTree {
    private BSTNode root;
    private int size = 0;

    public void insert(String word) {
        if (!contains(word)) {
            root = insertRec(root, word);
            size++;
        }
    }

    private BSTNode insertRec(BSTNode node, String word) {
        if (node == null) return new BSTNode(word);
        int cmp = word.compareTo(node.word);
        if (cmp < 0) node.left = insertRec(node.left, word);
        else if (cmp > 0) node.right = insertRec(node.right, word);
        return node;
    }

    public boolean contains(String word) {
        return containsRec(root, word);
    }

    private boolean containsRec(BSTNode node, String word) {
        if (node == null) return false;
        int cmp = word.compareTo(node.word);
        if (cmp < 0) return containsRec(node.left, word);
        else if (cmp > 0) return containsRec(node.right, word);
        return true;
    }

    public boolean remove(String word) {
        if (contains(word)) {
            root = removeRec(root, word);
            size--;
            return true;
        }
        return false;
    }

    private BSTNode removeRec(BSTNode node, String word) {
        if (node == null) return null;
        int cmp = word.compareTo(node.word);
        if (cmp < 0) node.left = removeRec(node.left, word);
        else if (cmp > 0) node.right = removeRec(node.right, word);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            BSTNode min = findMin(node.right);
            node.word = min.word;
            node.right = removeRec(node.right, min.word);
        }
        return node;
    }

    private BSTNode findMin(BSTNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    public ArrayList<String> searchBySubstring(String substring) {
        ArrayList<String> result = new ArrayList<>();
        inOrderSearch(root, substring.toLowerCase(), result);
        return result;
    }

    private void inOrderSearch(BSTNode node, String substring, ArrayList<String> result) {
        if (node != null) {
            inOrderSearch(node.left, substring, result);
            if (node.word.contains(substring)) result.add(node.word);
            inOrderSearch(node.right, substring, result);
        }
    }

    public int size() {
        return size;
    }
}
