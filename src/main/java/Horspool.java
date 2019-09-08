import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by pingao on 2019/9/8.
 */
public class Horspool implements Search {
    @Override
    public List<Integer> search(String p, String text) {
        int m = p.length(), n = text.length();
        if (m == 0) {
            return Collections.emptyList();
        }
        Map<Character, Integer> d = new HashMap<>();
        for (int j = 0; j < m - 1; j++) {
            char c = p.charAt(j);
            d.put(c, m - j - 1);
        }

        int pos = 0;
        List<Integer> idx = new ArrayList<>();
        while (pos <= n - m) {
            int j = m;
            while (j > 0 && text.charAt(pos + j - 1) == p.charAt(j - 1)) {
                j--;
            }
            if (j == 0) {
                idx.add(pos);
            }
            pos += d.getOrDefault(text.charAt(pos + m - 1), m);
        }
        return idx;
    }

    public static void main(String[] args) {
        Utils.pprint("", "", new Horspool());
        Utils.pprint("a", "a", new Horspool());
        Utils.pprint("a", "", new Horspool());
        Utils.pprint("", "a", new Horspool());
        Utils.pprint("a", "ba", new Horspool());
        Utils.pprint("announce", "annual_announce", new Horspool());
        Utils.pprint("ATATA", "AGATACGATATATAC", new Horspool());
    }
}
