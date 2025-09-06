package io.awijaya.algo;

public class MyAddBinary {

    public static String addBinary(String a, String b) {
        if (a == null || b == null) {
            return "0";
        }

        StringBuilder result = new StringBuilder();
        int carry = 0;
        int i = a.length() - 1;
        int j = b.length() - 1;

        // Process from right to left
        while (i >= 0 || j >= 0 || carry > 0) {
            int sum = carry;

            if (i >= 0) {
                sum += a.charAt(i) - '0';
                i--;
            }
            if (j >= 0) {
                sum += b.charAt(j) - '0';
                j--;
            }

            result.append(sum % 2);
            carry = sum / 2;
        }

        return result.reverse().toString();
    }

    public static void main(String[] args) {
        String input1 = "11";
        String input2 = "1";
        System.out.println(addBinary(input1, input2));

        String input3 = "1010";
        String input4 = "1011";
        System.out.println(addBinary(input3, input4));
    }
}
