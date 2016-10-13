package InfiniteSequence;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Skaiol-PC on 12.10.2016.
 */
public class MayBeNumber {
    private String _value;
    private int _startOffset;
    private int _endOffset;

    public MayBeNumber(String value, int startOffset, int endOffset) {
        _value = value;
        _startOffset = startOffset;
        _endOffset = endOffset;
    }

    public boolean isNumber() {
        return _startOffset == 0 && _endOffset == 0;
    }

    public BigInteger getValue() {
        return new BigInteger(_value);
    }

    public String getStringValue() {
        return _value;
    }

    public BigInteger getStartOffset() {
        return BigInteger.valueOf(_startOffset);
    }

    public void tryFillEnd(MayBeNumber prev) {
        BigInteger increment = prev.getValue().add(BigInteger.ONE);
        String incrementString = increment.toString();

        StringBuilder builder = new StringBuilder(incrementString);
        while (builder.length() < prev.getStringValue().length()) {
            builder.insert(0, "0");
        }
        incrementString = builder.toString();

        String end = incrementString.substring(incrementString.length() - _endOffset);
        _value = _value + end;
    }

    public void tryFillStart(MayBeNumber next) {
        BigInteger decrement = next.getValue().subtract(BigInteger.ONE);
        if (decrement.equals(getValue())) {
            _startOffset = 0;
            return;
        }
        String decrementString = decrement.toString();
        String start = decrementString.substring(0, _startOffset);
        _value = start + _value;
    }

    public boolean tryJoin(MayBeNumber next) {
        if (getStringValue().equals("0") && _startOffset == 0) {
            return false;
        }

        if (next.getStringValue().startsWith("0"))
            return false;

        if (!next.isNumber()) {
            next.tryFillEnd(this);
        }

        if (!isNumber()) {
            tryFillStart(next);
        }

        BigInteger increment = getValue().add(BigInteger.ONE);
        return Objects.equals(increment, next.getValue());
    }

    public static ArrayList<MayBeNumber> split(String value, int chunkSize, int offset) {
        ArrayList<MayBeNumber> result = new ArrayList<MayBeNumber>();

        String regularExpr = String.format("(?<=\\G.{%1$d})", chunkSize);
        String[] splitValues;
        if (offset == 0) {
            splitValues = value.split(regularExpr);
        }
        else {
            splitValues = value.substring(offset).split(regularExpr);
            String firstValue = value.substring(0, offset);
            result.add(new MayBeNumber(firstValue, chunkSize - firstValue.length(), 0));
        }

        for (String splitItem: splitValues) {
            result.add(new MayBeNumber(splitItem, 0, chunkSize - splitItem.length()));
        }

        return result;
    }
}
