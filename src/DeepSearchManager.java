import java.util.HashMap;
import java.util.Map;


public class DeepSearchManager {
	
	static String classifySite(String site, CategoryTreeNode t, double t_es, int t_ec) {
		String res = t.category;
		
		if (t.children.size() == 0)
			return res;
		
		System.out.println("now in category: " + t.category);
		
		int total_matches = 0;
		int matches = 0;
		Map<CategoryTreeNode, Integer> category2matches = new HashMap<CategoryTreeNode, Integer>();
		for (CategoryTreeNode n: t.children) {
			matches = 0;
			for (String q: n.queries)
				matches += BingManager.Query4RelevantWebNum(site, q);
			
			System.out.println("at category: " + n.category + ", matches is: " + String.valueOf(matches));
			
			category2matches.put(n, matches);
			total_matches += matches;
		}
		
		System.out.println("Total matches is: " + String.valueOf(total_matches));
		
		for (CategoryTreeNode n: t.children) {
			int ECoverage = category2matches.get(n);
			double ESpecificity = 1.0 * category2matches.get(n) / total_matches;
			
			if (ECoverage >= t_ec && ESpecificity >= t_es) {
				res += "/" + DeepSearchManager.classifySite(site, n, t_es, t_ec);
				// Assume there will be only one result satisfying the condition...
				break;
			}
		}
		
		return res;
	}
	
	
}
