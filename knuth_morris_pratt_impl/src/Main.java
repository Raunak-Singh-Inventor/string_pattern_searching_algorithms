import java.util.Arrays;

public class Main {
    public static void searchStringPattern(String string, String pattern) {
        char[] pattern_arr = pattern.toCharArray();
        char[] string_arr = string.toCharArray();
        int[] lps = new int[pattern_arr.length];

        for(int i = 0; i < lps.length; i++) {
            lps[i]=-1;
        }

        for(int i = 0; i < pattern_arr.length; i++) {
            for (int j = i + 1; j < pattern_arr.length; j++) {
                if (pattern_arr[i] == pattern_arr[j]) {
                    lps[j] = i;
                }
            }
        }

        int i = 0;
        int counter = 0;
        int start_index = 0;
        boolean start_flag = true;
        for(int j = -1; j < pattern_arr.length; j++) {
            while (string_arr[i] != pattern_arr[j + 1]) {
                counter = 0;
                if (j == -1) {
                    start_flag = true;
                    break;
                }
                start_index += (j-lps[j]);
                j = lps[j];
                counter = j+1;
            }

            if(start_flag) {
                start_index = i;
                start_flag = false;
            }

            if(string_arr[i] == pattern_arr[j + 1]) {
                counter++;
            }

            if(counter == pattern_arr.length) {
                System.out.println("pattern found at index " + start_index);
                counter = 0;
                j = -2;
                i--;
                start_flag = true;
            }

            i++;
            if (i >= string_arr.length) {
                break;
            }
        }

        System.out.println(Arrays.toString(lps));
    }

    public static void main(String[] args) {
        searchStringPattern("AABAACAADAABAABA","AABA");
        searchStringPattern("THIS IS A TEST TEXT","TEST");
        searchStringPattern("ababcabcabababd","ababd");
    }
}