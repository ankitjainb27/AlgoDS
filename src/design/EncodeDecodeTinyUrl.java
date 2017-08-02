package design;

import java.util.*;

/**
 * Date 02/08/17
 *
 * @author Ankit Jain
 * https://leetcode.com/problems/encode-and-decode-tinyurl/description/
 */
public class EncodeDecodeTinyUrl {
    public class Codec {
        List<String> urls = new ArrayList<String>();

        // Encodes a URL to a shortened URL.
        public String encode(String longUrl) {
            urls.add(longUrl);
            return String.valueOf(urls.size() - 1);
        }

        // Decodes a shortened URL to its original URL.
        public String decode(String shortUrl) {
            int index = Integer.valueOf(shortUrl);
            return (index < urls.size()) ? urls.get(index) : "";
        }
    }

    //Approach 1- Using simple counter
    public class Codec2 {
        Map<Integer, String> map = new HashMap<>();
        int i = 0;

        public String encode(String longUrl) {
            map.put(i, longUrl);
            return "http://tinyurl.com/" + i++;
        }

        public String decode(String shortUrl) {
            return map.get(Integer.parseInt(shortUrl.replace("http://tinyurl.com/", "")));
        }
    }

    //    Approach 2- using hashcode
    public class Codec3 {
        Map<Integer, String> map = new HashMap<>();

        public String encode(String longUrl) {
            map.put(longUrl.hashCode(), longUrl);
            return "http://tinyurl.com/" + longUrl.hashCode();
        }

        public String decode(String shortUrl) {
            return map.get(Integer.parseInt(shortUrl.replace("http://tinyurl.com/", "")));
        }
    }

    //    Approach 3- using random function
    public class Codec4 {
        Map<Integer, String> map = new HashMap<>();
        Random r = new Random();
        int key = r.nextInt(10000);

        public String encode(String longUrl) {
            while (map.containsKey(key))
                key = r.nextInt(10000);
            map.put(key, longUrl);
            return "http://tinyurl.com/" + key;
        }

        public String decode(String shortUrl) {
            return map.get(Integer.parseInt(shortUrl.replace("http://tinyurl.com/", "")));
        }
    }

    public class Codec1 {

        int[] url = {0, 0, 0, 0, 0, 0};
        HashMap<String, String> encodeMap;
        HashMap<String, String> decodeMap;
        StringBuilder sb;

        public Codec1() {
            encodeMap = new HashMap<>();
            decodeMap = new HashMap<>();
            sb = new StringBuilder();
        }

        // Encodes a URL to a shortened URL.
        public String encode(String longUrl) {
            for (int val : url) {
                char ch;
                if (val < 10) ch = (char) (val + '0');
                else if (val < 36) ch = (char) ((val - 10) + 'A');
                else ch = (char) ((val - 36) + 'a');

                sb.append(ch);
            }
            encodeMap.put(longUrl, sb.toString());
            decodeMap.put(sb.toString(), longUrl);
            int carry = 1;
            for (int i = 5; i >= 0; i--) {
                int val = url[i] + carry;
                url[i] = val % 62;
                carry = val / 62;
            }
            return sb.toString();
        }

        // Decodes a shortened URL to its original URL.
        public String decode(String shortUrl) {
            return decodeMap.get(shortUrl);
        }
    }
}
