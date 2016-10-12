package InfiniteSequence;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by Skaiol-PC on 08.10.2016.
 */
public class InfiniteSequence {
    public void run() {
        System.out.println("Вводите подпоследовательности построчно. По окончании ввода введите Enter");
        System.out.println(getPosition3("055"));
        System.out.println(getPosition("055"));

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

    private static BigInteger getPosition3(String value) {
        int valueLength = value.length();
        if (valueLength == 1) {
            return new BigInteger(value);
        }

        for (int i = 1; i <= valueLength; i++) {
            ArrayList<MayBeNumber> candidates = new ArrayList<MayBeNumber>();

            for (int j = 0; j < i; j++) {
                ArrayList<MayBeNumber> splited = MayBeNumber.split(value, i, j);
                boolean canJoin = true;
                for (int k = 0; k < splited.size() - 1; k++) {
                    if (!splited.get(k).tryJoin(splited.get(k + 1))) {
                        canJoin = false;
                        break;
                    }
                }

                if (canJoin)
                    candidates.add(splited.get(0));
            }

            if (!candidates.isEmpty()) {
                MayBeNumber min = candidates.get(0);
                for (int j = 1; j < candidates.size(); j++)
                    if (candidates.get(j).getValue().compareTo(min.getValue()) < 0)
                        min = candidates.get(j);

                return calcPositionInSequence(min.getValue()).add(min.getStartOffset());
            }
        }
        return calcPositionInSequence(new BigInteger(value));
    }

    private static BigInteger calcPositionInSequence(BigInteger number) {
        int length = number.toString().length();
        if (length == 1)
        {
            return number;
        }

        BigInteger result = BigInteger.TEN;
        int i;
        for (i = 2; i < length; i++)
        {
            result = result.add(BigInteger.TEN
                                   .pow(i - 1)
                                   .multiply(BigInteger.valueOf(i*9)));
        }
        result = result.add(BigInteger.valueOf(i)
                       .multiply(number.subtract(BigInteger.TEN.pow(i - 1))));
        return result;
    }
}
