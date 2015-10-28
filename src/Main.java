import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Main {
	
	public static List<String> LoadCategoryData(String file_name) {
		// read the file
		String tmpStr = null;
		List<String> res = new ArrayList<String>();
		
		File f = new File(file_name);
		if (f.exists()) {  
            try {  
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));  
                while ((tmpStr = reader.readLine()) != null) {  
                    res.add(tmpStr);  
                }  
            } catch (Exception e) {  
                e.printStackTrace();  
                return null;
            }
        } else {  
            System.out.println("No file!");
            return null;
        }
		
		return res;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> data = LoadCategoryData("querydata.txt");

		CategoryTree t = new CategoryTree(data);
		
		//System.out.println(t);
		
		String account_key = "oJ3gTr9BZ/XtsMgrJvtrvUUVbbg4cidHkkfDu8JccFE";
		BingQuery.Query("fifa.com", "soccer", account_key);
	}

}
