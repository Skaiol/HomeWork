/**
 * Created by Skaiol on 08.10.2016.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Вводите подпоследовательности построчно. По окончании ввода введите Enter");
        System.out.println(getPosition("6789"));
        System.out.println(getPosition("111"));
        System.out.println(getPosition("124"));
        System.out.println(getPosition("8192"));
        System.out.println(getPosition2("8192"));
        System.out.println(getPosition("930"));
        System.out.println(getPosition2("930"));
        System.out.println("Девяточки");
        System.out.println(getPosition("9"));
        System.out.println(getPosition("99"));
        System.out.println(getPosition("999"));
        System.out.println(getPosition("9999"));
        System.out.println(getPosition("99999"));
        System.out.println(getPosition("999999"));
        System.out.println(getPosition("9999999"));
        String str = "12345678910111213141516171819202122232425262728293031323334353637383940";
    }

    private static long getPosition(String value) {
        char valueFirstChar = value.charAt(0);
        long count = 1;
        long position = 1;

        while (true) {
            String positionString = String.valueOf(count);
            int positionFirstChar = positionString.indexOf(valueFirstChar);

            if (positionFirstChar != -1) {
                StringBuilder builder = new StringBuilder(positionString.substring(positionFirstChar));
                long countNew = count;

                while (builder.length() < value.length()) {
                    countNew++;
                    builder.append(countNew);
                }

                if (builder.indexOf(value) != -1) {
                    return position + positionFirstChar;
                }
            }

            position += positionString.length();
            count++;
        }
    }

    private static long getPosition2(String value) {
        long count = 1;
        StringBuilder builder = new StringBuilder();
        while (true) {
            builder.append(count);
            int position = builder.indexOf(value);
            if (position != -1) {
                return position + 1;
            }
            count++;
        }
    }

    private static char getFirstChar(long value) {
        while (value > 9)
            value /= 10;
        return String.valueOf(value).charAt(0);
    }

    private static long getLongLength(long value) {
        return (long)(Math.log10(value) + 1);
    }
}
