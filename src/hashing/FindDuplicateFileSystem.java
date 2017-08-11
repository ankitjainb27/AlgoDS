package hashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Date 09/08/17
 *
 * @author Ankit Jain
 */
public class FindDuplicateFileSystem {
    public List<List<String>> findDuplicate(String[] paths) {
        HashMap<String, List<String>> map = new HashMap<>();
        //Set<String> set = new HashSet<>();
        for (String path : paths) {
            String[] vals = path.split(" ");
            String base = vals[0];
            for (int i = 1; i < vals.length; i++) {
                int keyIndex = vals[i].indexOf("(");
                String key = vals[i].substring(keyIndex + 1, vals[i].length() - 1);
                if (!map.containsKey(key))
                    map.put(key, new ArrayList<String>());
                map.get(key).add(base + "/" + vals[i].substring(0, keyIndex));
            }
        }
        List list = new ArrayList();
        for (Map.Entry<String, List<String>> me : map.entrySet()) {
            if (me.getValue().size() > 1)
                list.add(me.getValue());
        }
        return list;
    }
}
