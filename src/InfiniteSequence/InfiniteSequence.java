package InfiniteSequence;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by Skaiol-PC on 08.10.2016.
 */
public class InfiniteSequence {
    public BigInteger getPositionOfSubSequence(String subSequence) {
        int valueLength = subSequence.length();
        if (valueLength == 1) {
            return new BigInteger(subSequence);
        }

        for (int i = 1; i <= valueLength; i++) {
            ArrayList<MayBeNumber> candidates = new ArrayList<MayBeNumber>();

            for (int j = 0; j < i; j++) {
                ArrayList<MayBeNumber> splited = MayBeNumber.split(subSequence, i, j);
                boolean canJoin = true;

                if (splited.size() == 1 && splited.get(0).getStringValue().startsWith("0"))
                    canJoin = false;

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
        return calcPositionInSequence(new BigInteger(subSequence));
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
