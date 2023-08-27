import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LC212 {
  TreeNode root;

  public LC212() {
    root = new TreeNode();
  }

  public void addWord(String word) {
    TreeNode curr = root;
    for (Character c : word.toCharArray()) {
      int index = c - 'a';
      if (curr.nodes[index] == null) {
        curr.nodes[index] = new TreeNode();
      }
      curr = curr.nodes[index];
    }
    curr.value = word;
  }

  public List<String> findWords(char[][] board, String[] words) {
    for (String word : words) {
      addWord(word);
    }
    Set<String> out = new HashSet<String>();
    int m = board.length;
    int n = board[0].length;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (root.nodes[board[i][j] - 'a'] != null) {
          lookup(root, board, i, j, out, m, n, new boolean[m][n]);
        }
      }
    }
    return new ArrayList<String>(out);
  }

  private void lookup(TreeNode nodeUnderCder, char board[][], int i, int j, Set<String> out, int m,
      int n, boolean visitedBefore[][]) {
    TreeNode curr = nodeUnderCder.nodes[board[i][j] - 'a'];
    if (i < 0 || j < 0 || i >= m || j >= n || visitedBefore[i][j])
      return;
    if (curr == null)
      return;
    if (curr.value != null) {
      out.add(curr.value);
    }
    visitedBefore[i][j] = true;
    if (i + 1 < m)
      lookup(curr, board, i + 1, j, out, m, n, visitedBefore);
    if (i - 1 >= 0)
      lookup(curr, board, i - 1, j, out, m, n, visitedBefore);
    if (j + 1 < n)
      lookup(curr, board, i, j + 1, out, m, n, visitedBefore);
    if (j - 1 >= 0)
      lookup(curr, board, i, j - 1, out, m, n, visitedBefore);
    visitedBefore[i][j] = false;
  }

  class TreeNode {
    TreeNode[] nodes = new TreeNode[26];
    String value = null;
  }
}

