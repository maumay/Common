/**
 *
 */
package com.github.maumay.jflow.impl;

import java.util.OptionalInt;
import java.util.function.IntFunction;

/**
 * @author ThomasB
 */
public class IntMinMaxConsumption
{
    private IntMinMaxConsumption()
    {
    }

    public static OptionalInt findMinOption(AbstractIntIterator source)
    {
        source.relinquishOwnership();
        boolean found = false;
        int min = Integer.MAX_VALUE;
        while (source.hasNext()) {
            int next = source.nextIntImpl();
            if (!found) {
                found = true;
                min = next;
            } else if (next < min) {
                min = next;
            }
        }
        return found ? OptionalInt.of(min) : OptionalInt.empty();
    }

    public static <C extends Comparable<C>> OptionalInt findMinOption(
            AbstractIntIterator source,
            IntFunction<C> key)
    {
        source.relinquishOwnership();
        int minKey = -1;
        C minVal = null;
        while (source.hasNext()) {
            final int nextKey = source.nextIntImpl();
            final C nextVal = key.apply(nextKey);

            if (minVal == null) {
                minKey = nextKey;
                minVal = nextVal;
            } else if (nextVal.compareTo(minVal) < 0) {
                minVal = nextVal;
                minKey = nextKey;
            }
        }
        return minVal == null ? OptionalInt.empty() : OptionalInt.of(minKey);
    }

    public static int findMin(AbstractIntIterator source)
    {
        source.relinquishOwnership();
        boolean found = false;
        int min = Integer.MAX_VALUE;
        while (source.hasNext()) {
            int next = source.nextIntImpl();
            if (!found) {
                found = true;
                min = next;
            } else if (next < min) {
                min = next;
            }
        }
        if (found) {
            return min;
        } else {
            throw new IllegalStateException();
        }
    }

    public static OptionalInt findMaxOption(AbstractIntIterator source)
    {
        source.relinquishOwnership();
        boolean found = false;
        int max = Integer.MIN_VALUE;
        while (source.hasNext()) {
            int next = source.nextIntImpl();
            if (!found) {
                found = true;
                max = next;
            } else if (next > max) {
                max = next;
            }
        }
        return found ? OptionalInt.of(max) : OptionalInt.empty();
    }

    public static int findMax(AbstractIntIterator source)
    {
        source.relinquishOwnership();
        boolean found = false;
        int max = Integer.MIN_VALUE;
        while (source.hasNext()) {
            int next = source.nextIntImpl();
            if (!found) {
                found = true;
                max = next;
            } else if (next > max) {
                max = next;
            }
        }
        if (found) {
            return max;
        } else {
            throw new IllegalStateException();
        }
    }

    public static <C extends Comparable<C>> OptionalInt findMaxOption(
            AbstractIntIterator source,
            IntFunction<C> key)
    {
        source.relinquishOwnership();
        int maxKey = -1;
        C maxVal = null;
        while (source.hasNext()) {
            final int nextKey = source.nextIntImpl();
            final C nextVal = key.apply(nextKey);

            if (maxVal == null) {
                maxKey = nextKey;
                maxVal = nextVal;
            } else if (nextVal.compareTo(maxVal) > 0) {
                maxVal = nextVal;
                maxKey = nextKey;
            }
        }
        return maxVal == null ? OptionalInt.empty() : OptionalInt.of(maxKey);
    }
}
