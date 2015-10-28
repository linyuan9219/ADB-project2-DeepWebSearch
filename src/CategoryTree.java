
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class CategoryTree {
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
	
	CategoryTreeNode root;
	
	CategoryTree() {
		root = new CategoryTreeNode("Root");
	}
	
	// Construct Tree from file 
	CategoryTree(List<String> category_data) {
		root = new CategoryTreeNode("Root");
		Map<String, CategoryTreeNode> name2category_node = new HashMap<String, CategoryTreeNode>();
		
		CategoryTreeNode cur_node = root;
		String[] tmp_tokens = null;
		for (String line: category_data) {
			
			if (line.length() == 0)
				continue;

			if (line.charAt(line.length() - 1) != ':') {
				tmp_tokens = line.split(" ");
				String query = line.substring(tmp_tokens[0].length() + 1);
				
				if (name2category_node.containsKey(tmp_tokens[0])) {
					name2category_node.get(tmp_tokens[0]).queries.add(query);
				}
				else {
					CategoryTreeNode n = new CategoryTreeNode(tmp_tokens[0]);
					name2category_node.put(tmp_tokens[0], n);
					n.queries.add(query);
					cur_node.children.add(n);
				}
			}
			else {
				String c = line.substring(0, line.length() - 1);
				if (name2category_node.containsKey(c))
					cur_node = name2category_node.get(c);
			}
		}
	}
	
	public String toString() {
		String res = root.toString();
		for (CategoryTreeNode child: root.children)
			res += child.toString();
		
		return res;
	}
}
