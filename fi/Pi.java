package chapter.pi;

import java.sql.Timestamp;

public class Pi {
	//public static final String[] inputValues = {"12341234", "11111222", "12122222", "22222222", "12673939"};
	public static final String[] inputValues = {"12341234111112221212222222222222126739391267393912673939"};
	private static int[] cache;
	private static int cnt = 0;
	
	
	
	public static int totalDifficulty(String val, boolean cacheable) {
		cnt ++;
		if (cacheable && cache[val.length()] != 0) return cache[val.length()];
		int tot = Integer.MAX_VALUE;
		if (val.length() <= 5) return difficulty(val.toCharArray());
		for(int i = 3 ; i <= 5 ; i++) {
			tot = Math.min(tot, difficulty(val.substring(0, i).toCharArray()) + totalDifficulty(val.substring(i), cacheable));
		}
		cache[val.length()] = tot;
		return tot;
	}
	
	/**
	 * val은 3~5자리의 숫자
	 * @param val
	 * @return
	 */
	private static int difficulty(char[] val) {
		if (isSame(val)) return 1;
		if (isSequence(val)) return 2;
		if (isPingPong(val)) return 4;
		if (isProgression(val)) return 5;
		return 10;
	}
	
	private static boolean isSame(char[] val) {
		for (char c : val) {
			if (val[0] != c) return false;
		}
		return true;
	}
	
	private static boolean isSequence(char[] val) {
		for (int i = 0 ; i < val.length -1; i++){
			if(val[0] - val[1] != val[i] - val[i +1]) return false;
		}
		return true;
	}
	
	private static boolean isPingPong(char[] val) {
		if (val.length %2 == 0) {
			return false;
		}
		for (int i = 3 ; i < val.length ; i++){
			if (i%2 == 1 && val[0] != val[i]) return false;
		}
		return true;
	}
	
	private static boolean isProgression(char[] val) {
		int gap = val[0] - val[1];
		for (int i = 1 ; i < val.length - 1 ; i++) {
			if (val[i] - val[i+1] != gap) return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		
		System.out.println("==========================================");
		System.out.println("== use cache =============================");
		System.out.println("==========================================");
		for (String str : inputValues) {
			System.out.println(str.length() + " : char length");
			System.out.println(new Timestamp(System.currentTimeMillis()) + " : start");
			cnt = 0;
			cache = new int[str.length() +1];
			System.out.println(Pi.totalDifficulty(str, true) + ", recursive cnt : " + cnt );
			System.out.println(new Timestamp(System.currentTimeMillis()) + " : end");
		}

		System.out.println("==========================================");
		System.out.println("== not use cache  ========================");
		System.out.println("==========================================");
		for (String str : inputValues) {
			System.out.println(new Timestamp(System.currentTimeMillis()));
			System.out.println(Pi.totalDifficulty(str, false) + ", recursive cnt : " + cnt );
			System.out.println(new Timestamp(System.currentTimeMillis()));
		}
	}
}
