package com.github.maumay.jflow.iterator;

import com.github.maumay.jflow.utils.LongTup;
import com.github.maumay.jflow.vec.LongVec;

import java.util.OptionalLong;
import java.util.PrimitiveIterator;
import java.util.function.*;

/**
 * <p>
 * An extension of {@link PrimitiveIterator.OfInt} with a multitude of extra
 * methods for piping and transforming sequential data streams. There are many
 * static factory methods for constructing instances of this interface in the
 * {@link Iter} class.
 * </p>
 * <p>
 * In general a good rule of thumb when using iterators is to avoid consuming
 * them with the {@link #nextLong()} method unless it is in the standard while
 * loop idiom. The consumption methods provided in this interface are usually
 * far more useful in general (unless you enjoy writing while loops and using
 * mutable collections in which case this library isn't for you).
 * </p>
 *
 * @author ThomasB
 * @since 20 Apr 2018
 */
public interface LongIterator extends OptionalLongIterator
{
    /**
     * Applies a function elementwise to this {@link LongIterator} to make new
     * {@link LongIterator}.
     *
     * @param f A mapping function.
     * @return A new {@link LongIterator} instance whose elements are obtained
     * by applying the parameter mapping function to each element of this {@link
     * LongIterator} instance in turn.
     */
    LongIterator map(LongUnaryOperator f);

    /**
     * Applies a function elementwise to this {@link LongIterator} to make new
     * {@link RichIterator}.
     *
     * @param <E> The target type of the mapping function.
     * @param f   A mapping function.
     * @return A new {@link RichIterator} instance whose elements are obtained
     * by applying the parameter mapping function to each element of this {@link
     * LongIterator} instance in turn.
     */
    <E> RichIterator<E> mapToObj(LongFunction<? extends E> f);

    /**
     * Applies a function elementwise to this {@link LongIterator} to make a new
     * {@link DoubleIterator}.
     *
     * @param f A mapping function.
     * @return A new {@link DoubleIterator} instance whose elements are obtained
     * by applying the parameter mapping function to each element of this {@link
     * LongIterator} instance in turn.
     */
    DoubleIterator mapToDouble(LongToDoubleFunction f);

    /**
     * Applies a function elementwise to this {@link LongIterator} to make a new
     * {@link IntIterator}.
     *
     * @param f A mapping function.
     * @return A new {@link IntIterator} instance whose elements are obtained by
     * applying the parameter mapping function to each element of this {@link
     * LongIterator} instance in turn.
     */
    IntIterator mapToInt(LongToIntFunction f);

    /**
     * Combines this {@link LongIterator} with another primitive iterator to
     * create a new {@link RichIterator} consisting of pairs of elements with
     * the same index in their respective origins.
     *
     * @param other The primitive iterator to zip this source {@link
     *              LongIterator} with.
     * @return Denote this source {@link LongIterator} by {@code F} with the
     * parameter primitive iterator denoted by {@code I}. We return a new {@link
     * RichIterator} instance {@code G} defined by:
     * <ul>
     * <li>{@code G[j] = (F[j], I[j])}</li>
     * <li>{@code length(G) = min(length(F), length(I))}</li>
     * </ul>
     */
    RichIterator<LongTup> zip(PrimitiveIterator.OfLong other);

    /**
     * Creates a new {@link RichIterator} by mapping each element in this source
     * {@link LongIterator} to a pair consisting of the element and the index it
     * appears.
     *
     * @return Denote this source {@link LongIterator} by {@code F}. We return a
     * new {@link RichIterator} instance {@code G} defined by:
     * <ul>
     * <li>{@code G[j] = (F[j], j)}</li>
     * <li>{@code length(G) = length(F)}</li>
     * </ul>
     */
    RichIterator<LongTup> enumerate();

    /**
     * Creates a new {@link LongIterator} from this {@link LongIterator} by
     * selecting elements with indices defined by the parameter index mapping.
     *
     * @param indexMap A strictly monotonically increasing function {@code f: N
     *                 -> N}
     * @return Let {@code F} denote this source {@link LongIterator}, let {@code
     * n = length(F)} and denote the indexMap by {@code f}. Then this method
     * returns a {@link LongIterator} {@code G} given by:
     * <ul>
     * <li>{@code G[i] = F(f(i))}</li>
     * <li><code> length(G) = supremum {i | (i in N) and (f(i) &lt; length(F))} </code></li>
     * </ul>
     */
    LongIterator slice(IteratorSlicer indexMap);

    /**
     * Creates a new {@link LongIterator} from this {@link LongIterator} by
     * selecting the first n elements.
     *
     * @param n A non-negative integer.
     * @return Let {@code F} denote this source {@link LongIterator}. We return
     * a {@link LongIterator} consisting of the first {@code max(n, length(F))}
     * elements of {@code F}.
     * @throws IllegalArgumentException If parameter is negative.
     */
    LongIterator take(int n);

    /**
     * Creates a new {@link LongIterator} from this {@link LongIterator} by
     * selecting elements until an element fails the supplied test, the first
     * failure is not selected.
     *
     * @param predicate A {@link LongPredicate}.
     * @return Let {@code n} be the index of the first element that the
     * parameter predicate fails for. Then this method returns a {@link
     * LongIterator} consisting of the first {@code n} elements of this source
     * {@link LongIterator}. If no element fails the predicate test then a copy
     * of the source is returned.
     */
    LongIterator takeWhile(LongPredicate predicate);

    /**
     * Creates a new {@link LongIterator} from this {@link LongIterator} by
     * removing the first n elements.
     *
     * @param n A non-negative integer.
     * @return Let {@code F} denote this source {@link LongIterator}. We return
     * a {@link LongIterator} missing the first {@code min(n, length(F))}
     * elements of {@code F}.
     * @throws IllegalArgumentException If parameter is negative.
     */
    LongIterator skip(int n);

    /**
     * Creates a new {@link LongIterator} from this {@link LongIterator} by
     * removing elements until an element fails the supplied test, the first
     * failure is the first element of the result.
     *
     * @param predicate A {@link LongPredicate}.
     * @return Let {@code n} be the index of the first element that the
     * parameter predicate fails for. Then this method returns a {@link
     * LongIterator} missing {@code n} elements of this source {@link
     * LongIterator}. If no element fails the predicate test then a copy of the
     * source is returned.
     */
    LongIterator skipWhile(LongPredicate predicate);

    /**
     * Creates a new {@link LongIterator} from this {@link LongIterator} by
     * removing any element which fails the supplied predicate test.
     *
     * @param predicate A {@link LongPredicate}.
     * @return A {@link LongIterator} containing only those elements of this
     * source {@link LongIterator} which pass the test defined by the parameter
     * predicate. The relative ordering of elements is retained.
     */
    LongIterator filter(LongPredicate predicate);

    /**
     * Creates a new {@link LongIterator} from this {@link LongIterator} by
     * adding each element of the supplied primitive iterator to its end in
     * order.
     *
     * @param other A primitive iterator.
     * @return A {@link LongIterator} consisting of the elements of this source
     * {@link LongIterator} followed by the elements of the parameter primitive
     * iterator.
     */
    LongIterator chain(PrimitiveIterator.OfLong other);

    /**
     * Creates a new {@link LongIterator} from this {@link LongIterator} by
     * adding each element of the supplied varargs array to its end in order.
     *
     * @param other - A varargs long array
     * @return A {@link LongIterator} consisting of the elements of the source
     * {@link LongIterator} followed by the elements in the parameter array.
     */
    LongIterator append(long... other);

    /**
     * Creates a new {@link LongIterator} from this {@link RichIterator} by
     * adding each element to the end of the supplied primitive iterator in
     * order.
     *
     * @param other A primitive iterator.
     * @return a {@link LongIterator} consisting of the elements of the
     * parameter primitive iterator followed by the elements of this source
     * {@link LongIterator}.
     */
    LongIterator rchain(PrimitiveIterator.OfLong other);

    /**
     * Creates a new {@link LongIterator} from this {@link RichIterator} by
     * adding each element to the end of the supplied varargs array in order.
     *
     * @param other - A varargs long array
     * @return an {@link LongIterator} consisting of the elements in the
     * parameter array followed by the elements of the source {@link
     * LongIterator}.
     */
    LongIterator insert(long... other);

    /**
     * Calculates the minimum value in this {@link LongIterator}.
     *
     * This method is a 'consuming method', i.e. it will iterate through this
     * {@link LongIterator}.
     *
     * @return an {@link OptionalLong} wrapping the smallest element in this
     * {@link LongIterator} or nothing if the iteration is empty.
     */
    OptionalLong minOp();

    /**
     * Calculates the minimum value in this {@link LongIterator}.
     *
     * This method is a 'consuming method', i.e. it will iterate through this
     * {@link LongIterator}.
     *
     * @return the smallest element in this {@link LongIterator} or the default
     * value if the iteration is empty.
     */
    long min();

    /**
     * Calculates the maximum value in this {@link LongIterator}.
     *
     * This method is a 'consuming method', i.e. it will iterate through this
     * {@link LongIterator}.
     *
     * @return an {@link OptionalLong} wrapping the largest element in this
     * {@link LongIterator} or nothing if the iteration is empty.
     */
    OptionalLong maxOp();

    /**
     * Calculates the maximum value in this {@link LongIterator}.
     *
     * This method is a 'consuming method', i.e. it will iterate through this
     * {@link LongIterator}.
     *
     * @return The largest element in this {@link LongIterator} or the default
     * value if the iteration is empty.
     */
    long max();

    /**
     * Checks whether every element in this {@link LongIterator} passes the
     * supplied {@linkplain LongPredicate} test.
     *
     * This method is a 'consuming method', i.e. it will iterate through this
     * {@link LongIterator}.
     *
     * @param predicate The supplied test.
     * @return True if every element passes the parameter predicate test, false
     * otherwise.
     */
    boolean all(LongPredicate predicate);

    /**
     * Checks whether any element in this {@link LongIterator} passes the
     * supplied {@linkplain LongPredicate} test.
     *
     * This method is a 'consuming method', i.e. it will iterate through this
     * {@link LongIterator}.
     *
     * @param predicate The supplied test.
     * @return True if any element passes the parameter predicate test, false
     * otherwise.
     */
    boolean any(LongPredicate predicate);

    /**
     * Checks whether every element in this {@link LongIterator} fails the
     * supplied {@linkplain LongPredicate} test.
     *
     * This method is a 'consuming method', i.e. it will iterate through this
     * {@link LongIterator}.
     *
     * @param predicate The supplied test.
     * @return True if every element fails the parameter predicate test, false
     * otherwise.
     */
    boolean none(LongPredicate predicate);

    /**
     * Reduces this {@link LongIterator} to a single value via some reduction
     * function and an initial value.
     *
     * This method is a 'consuming method', i.e. it will iterate through this
     * {@link LongIterator}.
     *
     * @param id      The identity of the reduction operation
     * @param reducer The reduction function
     * @return If we denote this source {@link LongIterator} by {@code F}, the
     * length of {@code F} by {@code n} and the reduction function by {@code f}
     * then the result is equal to: <br>
     * <br>
     * {@code f(...f(f(id, F[0]), F[1])..., F[n - 1])}
     */
    long fold(long id, LongBinaryOperator reducer);

    /**
     * Reduces this {@link LongIterator} to a single value via some reduction
     * function. Throws an exception if empty iterator.
     *
     * This method is a 'consuming method', i.e. it will iterate through this
     * {@link LongIterator}.
     *
     * @param reducer The reduction function
     * @return Let us denote this source {@link LongIterator} by {@code F}, the
     * length of {@code F} by {@code n} and the reduction function by {@code f}.
     * If {@code n == 0} we return nothing, else we return: <br>
     * <br>
     * {@code f(...f(f(F[0], F[1]), F[2])..., F[n - 1])}
     */
    long fold(LongBinaryOperator reducer);

    /**
     * Reduces this {@link LongIterator} to a single value via some reduction
     * function. Return nothing if empty iterator.
     *
     * This method is a 'consuming method', i.e. it will iterate through this
     * {@link LongIterator}.
     *
     * @param reducer The reduction function
     * @return Let us denote this source {@link LongIterator} by {@code F}, the
     * length of {@code F} by {@code n} and the reduction function by {@code f}.
     * If {@code n == 0} we return nothing, else we return: <br>
     * <br>
     * {@code f(...f(f(F[0], F[1]), F[2])..., F[n - 1])}
     */
    OptionalLong foldOp(LongBinaryOperator reducer);

    /**
     * Counts the number of elements in this {@link LongIterator}.
     *
     * This method is a 'consuming method', i.e. it will iterate through this
     * {@link LongIterator}.
     *
     * @return The number of elements in this {@link LongIterator}.
     */
    long count();

    /**
     * Caches the values in this {@link LongIterator} to a {@link LongVec}.
     *
     * This method is a 'consuming method', i.e. it will iterate through this
     * {@link LongIterator}.
     *
     * @return A vector containing all elements of this {@link LongIterator}
     * with their ordering retained.
     */
    LongVec toVec();

    /**
     * Caches the values in this {@link LongIterator} to an array.
     *
     * This method is a 'consuming method', i.e. it will iterate through this
     * {@link LongIterator}.
     *
     * @return A long array containing all elements of this {@link LongIterator}
     * with their ordering retained.
     */
    long[] toArray();

    /**
     * Boxes the primitive long values in this {@link LongIterator}.
     *
     * @return a copy of this source {@link LongIterator} as a {@link
     * RichIterator} of boxed {@linkplain Long} instances.
     */
    default RichIterator<Long> boxed()
    {
        return mapToObj(x -> x);
    }

    /**
     * Adapts this iterator by casting each element to a primitive double.
     *
     * @return An iterator traversing the same elements as this one but cast to
     * primitive double values.
     */
    default DoubleIterator asDouble()
    {
        return mapToDouble(n -> n);
    }
}
