import java.util.ArrayList;
import java.util.List;

public class CategoryTreeNode {
		String category;
		List<String> queries;
		List<CategoryTreeNode> children;
		
		CategoryTreeNode() {
			category = null;
			queries = new ArrayList<String>();
			children = new ArrayList<CategoryTreeNode>();
		}
		
		CategoryTreeNode(String _category) {
			category = _category;
			queries = new ArrayList<String>();
			children = new ArrayList<CategoryTreeNode>();
		}
		
		CategoryTreeNode(String _category, List<String> _queries, List<CategoryTreeNode> _children){
			category = _category;
			queries = _queries;
			children = _children;
		}
		
		public String toString() {
			String res = null;
			res = "Node: " + category + "\n";
			
			res += "Queries is: \n";
			for (String q: queries)
				res += q + "\n";
			
			res += "Children is: \n";
			for (CategoryTreeNode child: children)
				res += child.category + "\n";
			
			return res;
		}
	}