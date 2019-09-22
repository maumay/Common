/**
 *
 */
package com.github.maumay.jflow.utils;

import java.util.Objects;

/**
 * Compact two element tuple (pair) of primitive longs inspired by Scala.
 *
 * @author t
 */
public final class LongTup
{
    /**
     * First element of the pair. Note this is the Scala naming convention for
     * tuples
     */
    public final long _1;

    /**
     * Second element of the pair. Note this is the Scala naming convention for
     * tuples
     */
    public final long _2;

    public LongTup(long first, long second)
    {
        this._1 = first;
        this._2 = second;
    }

    /**
     * Creates a new tuple.
     *
     * @param first  The first (left) element of the new tuple.
     * @param second The second (right) element of the new tuple.
     * @return The new tuple.
     */
    public static LongTup of(long first, long second)
    {
        return new LongTup(first, second);
    }

    /**
     * Retrieve the first (left) element of this tuple.
     *
     * @return The first element of this tuple.
     */
    public long _1()
    {
        return _1;
    }

    /**
     * Retrieve the second (right) element of this tuple.
     *
     * @return The second element of this tuple.
     */
    public long _2()
    {
        return _2;
    }

    @Override
    public String toString()
    {
        return new StringBuilder("(").append(_1).append(", ").append(_2)
                .append(")")
                .toString();
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(_1, _2);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LongTup other = (LongTup) obj;
        return _1 == other._1 && _2 == other._2;
    }
}
