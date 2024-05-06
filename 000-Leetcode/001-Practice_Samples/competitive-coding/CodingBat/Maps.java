package CodingBat;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Maps {

	public static void main(String[] args) {
		for(int i=0;i<args.length;i++){
			System.out.println(args[i]);
		}
	}

	public Map<String, String> mapBully(Map<String, String> map) {
		Map<String, String> retval = new HashMap<>();
		map = (HashMap<String, String>) map;
		if (map.containsKey("a")) {
			retval.put("b", map.get("a"));
			retval.put("a", "");
		}
		Set<Map.Entry<String, String>> mapEntry = map.entrySet();
		for (Map.Entry<String, String> entry : mapEntry) {
			if (!retval.containsKey(entry.getKey())) {
				retval.put(entry.getKey(), entry.getValue());
			}
		}
		return retval;
	}

	public Map<String, String> mapShare(Map<String, String> map) {
		Map<String, String> retval = new HashMap<>();
		if (map.containsKey("a")) {
			map.put("b", map.get("a"));
		}
		if (map.containsKey("c")) {
			map.remove("c");
		}
		Set<Map.Entry<String, String>> mapEntry = map.entrySet();
		for (Map.Entry<String, String> entry : mapEntry) {
			retval.put(entry.getKey(), entry.getValue());
		}
		return retval;
	}

	public Map<String, String> mapAB(Map<String, String> map) {
		Map<String, String> retval = new HashMap<>();
		if (map.containsKey("a") && map.containsKey("b")) {
			map.put("ab", map.get("a") + map.get("b"));
		}
		Set<Map.Entry<String, String>> mapEntry = map.entrySet();
		for (Map.Entry<String, String> entry : mapEntry) {
			retval.put(entry.getKey(), entry.getValue());
		}
		return retval;
	}

	public Map<String, String> topping1(Map<String, String> map) {
		Map<String, String> retval = new HashMap<>();
		if (map.containsKey("ice cream")) {
			map.put("ice cream", "cherry");
		}
		map.put("bread", "butter");
		Set<Map.Entry<String, String>> mapEntry = map.entrySet();
		for (Map.Entry<String, String> entry : mapEntry) {
			retval.put(entry.getKey(), entry.getValue());
		}
		return retval;
	}

	public Map<String, Integer> word0(String[] strings) {
		Map<String, Integer> retval = new HashMap<>();
		for (int i = 0; i < strings.length; i++) {
			if (!retval.containsKey(strings[i])) {
				retval.put(strings[i], 0);
			}
		}
		return retval;
	}

	public Map<String, Integer> wordLen(String[] strings) {
		Map<String, Integer> retval = new HashMap<String, Integer>();
		for (int i = 0; i < strings.length; i++) {
			if (!retval.containsKey(strings[i])) {
				retval.put(strings[i], strings[i].length());
			}
		}
		return retval;
	}

	public Map<String, String> pairs(String[] strings) {
		Map<String, String> retval = new HashMap<String, String>();
		for (int i = 0; i < strings.length; i++) {
			if (!retval.containsKey(strings[i])) {
				retval.put(strings[i].charAt(0) + "", strings[i].charAt(strings[i].length() - 1) + "");
			}
		}
		return retval;
	}

	public Map<String, Integer> wordCount(String[] strings) {
		Map<String, Integer> retval = new HashMap<String, Integer>();
		for (int i = 0; i < strings.length; i++) {
			if (!retval.containsKey(strings[i])) {
				retval.put(strings[i], retval.get(strings[i]) + 1);
			} else {
				retval.put(strings[i], 1);
			}
		}
		return retval;
	}

	public Map<String, String> firstChar(String[] strings) {
		Map<String, String> retval = new HashMap<String, String>();
		for (int i = 0; i < strings.length; i++) {
			if (retval.containsKey(strings[i].charAt(0) + "")) {
				retval.put(strings[i].charAt(0) + "", retval.get(strings[i].charAt(0) + "") + strings[i]);
			} else {
				retval.put(strings[i].charAt(0) + "", strings[i]);
			}
		}
		return retval;
	}

	public String wordAppend(String[] strings) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		String retval = "";
		for (int i = 0; i < strings.length; i++) {
			if (map.containsKey(strings[i])) {
				retval += strings[i];
				map.remove(strings[i]);
			} else {
				map.put(strings[i], 1);
			}
		}
		return retval;
	}

	public Map<String, Boolean> wordMultiple(String[] strings) {
		Map<String, Boolean> retval = new HashMap<String, Boolean>();
		for (int i = 0; i < strings.length; i++) {
			if (retval.containsKey(strings[i])) {
				retval.put(strings[i], true);
			} else {
				retval.put(strings[i], false);
			}
		}
		return retval;
	}

	public String[] allSwap(String[] strings) {
		String[] retval = new String[strings.length];
		Map<Character, Integer> check = new HashMap<Character, Integer>();
		for (int i = 0; i < strings.length; i++) {
			if (!check.containsKey(strings[i].charAt(0))) {
				check.put(strings[i].charAt(0), i);
			} else {
				int x = check.get(strings[i].charAt(0));
				String temp = strings[x];
				strings[x] = strings[i];
				strings[i] = temp;
				check.remove(strings[i].charAt(0));
			}
		}
		retval = strings;
		return retval;
	}

	public String[] firstSwap(String[] strings) {
		String[] retval = new String[strings.length];
		Map<Character, Boolean> map = new HashMap<Character, Boolean>();
		Map<Character, Integer> check = new HashMap<Character, Integer>();
		for (int i = 0; i < strings.length; i++) {
			if (!check.containsKey(strings[i].charAt(0))) {
				check.put(strings[i].charAt(0), i);
				if (!map.containsKey(strings[i].charAt(0))) {
					map.put(strings[i].charAt(0), true);
				}
			} else if (map.get(strings[i].charAt(0))) {
				int x = check.get(strings[i].charAt(0));
				String temp = strings[x];
				strings[x] = strings[i];
				strings[i] = temp;
				check.remove(strings[i].charAt(0));
				map.put(strings[i].charAt(0), false);
			}
		}
		retval = strings;
		return retval;
	}
}