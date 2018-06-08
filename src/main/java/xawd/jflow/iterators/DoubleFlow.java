package xawd.jflow.iterators;

import java.util.Iterator;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.PrimitiveIterator;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

import xawd.jflow.iterators.misc.DoublePair;
import xawd.jflow.iterators.misc.DoublePredicatePartition;
import xawd.jflow.iterators.misc.DoubleWith;
import xawd.jflow.iterators.misc.DoubleWithLong;
import xawd.jflow.iterators.misc.IntWithDouble;

/**
 * An {@link IntFlow} instance is an {@link PrimitiveIterator.OfInt} with lots
 * of extra functionality in the style of the {@link IntStream} interface. There
 * are methods inspired by other languages too, namely Python and Haskell.
 *
 * @author ThomasB
 * @since 20 Apr 2018
 */
public interface DoubleFlow extends PrototypeDoubleFlow
{
	/**
	 * Applies a function elementwise to this {@linkplain DoubleFlow} to make new
	 * DoubleFlow.
	 *
	 * @param f
	 *            A mapping function.
	 * @return A new DoubleFlow instance whose elements are obtained by applying the
	 *         parameter mapping function to each element of this DoubleFlow
	 *         instance in turn.
	 */
	DoubleFlow map(final DoubleUnaryOperator f);

	/**
	 * Applies a function elementwise to this {@linkplain DoubleFlow} to make new
	 * {@linkplain Flow}.
	 *
	 * @param <E>
	 *            The target type of the mapping function.
	 * @param f
	 *            A mapping function.
	 * @return A new Flow instance whose elements are obtained by applying the
	 *         parameter mapping function to each element of this DoubleFlow
	 *         instance in turn.
	 */
	<E> Flow<E> mapToObject(DoubleFunction<E> f);

	/**
	 * Applies a function elementwise to this {@linkplain DoubleFlow} to make new
	 * {@linkplain LongFlow}.
	 *
	 * @param f
	 *            A mapping function.
	 * @return A new LongFlow instance whose elements are obtained by applying the
	 *         parameter mapping function to each element of this DoubleFlow
	 *         instance in turn.
	 */
	LongFlow mapToLong(DoubleToLongFunction f);

	/**
	 * Applies a function elementwise to this {@linkplain DoubleFlow} to make a new
	 * {@linkplain IntFlow}.
	 *
	 * @param f
	 *            A mapping function.
	 * @return A new IntFlow instance whose elements are obtained by applying the
	 *         parameter mapping function to each element of this DoubleFlow
	 *         instance in turn.
	 */
	IntFlow mapToInt(DoubleToIntFunction f);

	/**
	 * Combines this {@linkplain DoubleFlow} with another {@linkplain Iterator} to
	 * create a new {@linkplain Flow} consisting of pairs of elements with the same
	 * index in their respective origins.
	 *
	 * @param <E>
	 *            The upper type bound on the parameter Iterator.
	 * @param other
	 *            The Iterator to zip this source Flow with.
	 *
	 * @return Denote this source DoubleFlow by {@code F} with the parameter
	 *         Iterator denoted by {@code I}. We return a new Flow instance
	 *         {@code G} defined by:
	 *         <ul>
	 *         <li>{@code G[j] = (F[j], I[j])}</li>
	 *         <li>{@code length(G) = min(length(F), length(I))}</li>
	 *         </ul>
	 */
	<E> Flow<DoubleWith<E>> zipWith(final Iterator<? extends E> other);

	/**
	 * Combines this {@linkplain DoubleFlow} with another
	 * {@linkplain PrimitiveIterator.OfDouble}} to create a new {@linkplain Flow}
	 * consisting of pairs of elements with the same index in their respective
	 * origins.
	 *
	 * @param other
	 *            The PrimitiveIterator.OfDouble to zip this source DoubleFlow with.
	 *
	 * @return Denote this source DoubleFlow by {@code F} with the parameter
	 *         PrimitiveIterator.OfDouble denoted by {@code I}. We return a new Flow
	 *         instance {@code G} defined by:
	 *         <ul>
	 *         <li>{@code G[j] = (F[j], I[j])}</li>
	 *         <li>{@code length(G) = min(length(F), length(I))}</li>
	 *         </ul>
	 */
	Flow<DoublePair> zipWith(final PrimitiveIterator.OfDouble other);

	/**
	 * Combines this {@linkplain DoubleFlow} with another
	 * {@linkplain PrimitiveIterator.OfLong}} to create a new {@linkplain Flow}
	 * consisting of pairs of elements with the same index in their respective
	 * origins.
	 *
	 * @param other
	 *            The PrimitiveIterator.OfLong to zip this source DoubleFlow with.
	 *
	 * @return Denote this source DoubleFlow by {@code F} with the parameter
	 *         PrimitiveIterator.OfLong denoted by {@code I}. We return a new Flow
	 *         instance {@code G} defined by:
	 *         <ul>
	 *         <li>{@code G[j] = (F[j], I[j])}</li>
	 *         <li>{@code length(G) = min(length(F), length(I))}</li>
	 *         </ul>
	 */
	Flow<DoubleWithLong> zipWith(final PrimitiveIterator.OfLong other);

	/**
	 * Combines this {@linkplain DoubleFlow} with another
	 * {@linkplain PrimitiveIterator.OfInt}} to create a new {@linkplain Flow}
	 * consisting of pairs of elements with the same index in their respective
	 * origins.
	 *
	 * @param other
	 *            The PrimitiveIterator.OfInt to zip this source DoubleFlow with.
	 *
	 * @return Denote this source DoubleFlow by {@code F} with the parameter
	 *         PrimitiveIterator.OfInt denoted by {@code I}. We return a new Flow
	 *         instance {@code G} defined by:
	 *         <ul>
	 *         <li>{@code G[j] = (F[j], I[j])}</li>
	 *         <li>{@code length(G) = min(length(F), length(I))}</li>
	 *         </ul>
	 */
	Flow<IntWithDouble> zipWith(final PrimitiveIterator.OfInt other);

	/**
	 * Combines this {@linkplain DoubleFlow} with another
	 * {@linkplain PrimitiveIterator.OfDouble} via a two argument function to create
	 * a new {@linkplain Flow} consisting of the images of pairs of elements with
	 * the same index in their origin.
	 *
	 * @param other
	 *            The PrimitiveIterator.OfDouble to combine this source DoubleFlow
	 *            with.
	 * @param combiner
	 *            The combining function.
	 *
	 * @return Denote this source DoubleFlow by {@code F} with the parameter
	 *         PrimitiveIterator.OfDouble denoted by {@code I} and the combining
	 *         function by {@code f}. We return a new Flow instance {@code G}
	 *         defined by:
	 *         <ul>
	 *         <li>{@code G[j] = f(F[j], I[j])}</li>
	 *         <li>{@code length(G) = min(length(F), length(I))}</li>
	 *         </ul>
	 */
	DoubleFlow combineWith(final PrimitiveIterator.OfDouble other, final DoubleBinaryOperator combiner);

	/**
	 * Creates a new {@linkplain Flow} by mapping each element in this source
	 * {@linkplain DoubleFlow} to a pair consisting of the element and the index it
	 * appears.
	 *
	 * @return Denote this source DoubleFlow by {@code F}. We return a new Flow
	 *         instance {@code G} defined by:
	 *         <ul>
	 *         <li>{@code G[j] = (F[j], j)}</li>
	 *         <li>{@code length(G) = length(F)}</li>
	 *         </ul>
	 */
	Flow<IntWithDouble> enumerate();

	/**
	 * Creates a new {@linkplain DoubleFlow} from this DoubleFlow by selecting
	 * elements with indices defined by the parameter index mapping.
	 *
	 * @param indexMap
	 *            A strictly monotonically increasing function {@code f: N -> N}
	 * @return Let {@code F} denote this source DoubleFlow, let
	 *         {@code n = length(F)} and denote the indexMap by {@code f}. Then this
	 *         method returns a DoubleFlow {@code G} given by:
	 *         <ul>
	 *         <li>{@code G[i] = F(f(i))}</li>
	 *         <li><code> length(G) = supremum {i | (i in N) and (f(i) &lt; length(F))} </code></li>
	 *         </ul>
	 */
	DoubleFlow slice(IntUnaryOperator indexMap);

	/**
	 * Creates a new {@linkplain DoubleFlow} from this DoubleFlow by selecting the
	 * first n elements.
	 *
	 * @param n
	 *            A non-negative integer.
	 * @throws IllegalArgumentException
	 *             If parameter is negative.
	 * @return Let {@code F} denote this source DoubleFlow. We return a DoubleFlow
	 *         consisting of the first {@code max(n, length(F))} elements of
	 *         {@code F}.
	 */
	DoubleFlow take(final int n);

	/**
	 * Creates a new {@linkplain DoubleFlow} from this DoubleFlow by selecting
	 * elements until an element fails the supplied test, the first failure is not
	 * selected.
	 *
	 * @param predicate
	 *            A {@link DoublePredicate}.
	 * @return Let {@code n} be the index of the first element that the parameter
	 *         predicate fails for. Then this method returns a DoubleFlow consisting
	 *         of the first {@code n} elements of this source DoubleFlow. If no
	 *         element fails the predicate test then a copy of the source is
	 *         returned.
	 */
	DoubleFlow takeWhile(final DoublePredicate predicate);

	/**
	 * Creates a new {@linkplain DoubleFlow} from this DoubleFlow by removing the
	 * first n elements.
	 *
	 * @param n
	 *            A non-negative integer.
	 * @throws IllegalArgumentException
	 *             If parameter is negative.
	 * @return Let {@code F} denote this source DoubleFlow. We return a DoubleFlow
	 *         missing the first {@code min(n, length(F))} elements of {@code F}.
	 */
	DoubleFlow drop(final int n);

	/**
	 * Creates a new {@linkplain DoubleFlow} from this DoubleFlow by removing
	 * elements until an element fails the supplied test, the first failure is the
	 * first element of the result.
	 *
	 * @param predicate
	 *            A {@link DoublePredicate}.
	 * @return Let {@code n} be the index of the first element that the parameter
	 *         predicate fails for. Then this method returns a DoubleFlow missing
	 *         {@code n} elements of this source DoubleFlow. If no element fails the
	 *         predicate test then a copy of the source is returned.
	 */
	DoubleFlow dropWhile(final DoublePredicate predicate);

	/**
	 * Creates a new {@linkplain DoubleFlow} from this DoubleFlow by removing any
	 * element which fails the supplied predicate test.
	 *
	 * @param predicate
	 *            A {@link DoublePredicate}.
	 * @return A DoubleFlow containing only those elements of this source DoubleFlow
	 *         which pass the test defined by the parameter predicate. The relative
	 *         ordering of elements is retained.
	 */
	DoubleFlow filter(final DoublePredicate predicate);

	/**
	 * Creates a new {@linkplain DoubleFlow} from this DoubleFlow by adding each
	 * element of the supplied {@linkplain PrimitiveIterator.OfDouble} to its end in
	 * order.
	 *
	 * @param other
	 *            A PrimitiveIterator.OfDouble.
	 * @return A DoubleFlow consisting of the elements of this source DoubleFlow
	 *         followed by the elements of the parameter PrimitiveIterator.OfDouble.
	 */
	DoubleFlow append(PrimitiveIterator.OfDouble other);

	/**
	 * Creates a new {@linkplain DoubleFlow} from this DoubleFlow by adding each
	 * element of the supplied varargs array to its end in order.
	 *
	 * @param other
	 *            - A varargs double array
	 * @return A DoubleFlow consisting of the elements of the source DoubleFlow
	 *         followed by the elements in the parameter array.
	 */
	DoubleFlow append(double... other);

	/**
	 * Creates a new {@linkplain DoubleFlow} from this Flow by adding each element
	 * to the end of the supplied {@linkplain PrimitiveIterator.OfDouble} in order.
	 *
	 * @param other
	 *            A PrimitiveIterator.OfDouble.
	 * @return a DoubleFlow consisting of the elements of the parameter
	 *         PrimitiveIterator.OfDouble followed by the elements of this source
	 *         DoubleFlow.
	 */
	DoubleFlow insert(PrimitiveIterator.OfDouble other);

	/**
	 * Creates a new {@linkplain DoubleFlow} from this Flow by adding each element
	 * to the end of the supplied varargs array in order.
	 *
	 * @param other
	 *            - A varargs double array
	 * @return an DoubleFlow consisting of the elements in the parameter array
	 *         followed by the elements of the source DoubleFlow.
	 */
	DoubleFlow insert(double... other);

	/**
	 * Applies an accumulation operation to this {@linkplain DoubleFlow} to produce
	 * a new DoubleFlow.
	 *
	 * @param accumulator
	 *            The accumulation function.
	 * @return Let {@code F} denote this source DoubleFlow and {@code g} denote the
	 *         accumulation function. Then the DoubleFlow returned is of the form:
	 *         <ul>
	 *         <li>{@code [F[0], g(F[0], F[1]), g(g(F[0], F[1]), F[2]), ... ]}</li>
	 *         </ul>
	 */
	DoubleFlow accumulate(DoubleBinaryOperator accumulator);

	/**
	 * Applies an accumulation operation to this {@linkplain DoubleFlow} to produce
	 * a new DoubleFlow.
	 *
	 * @param id
	 *            The identity element in the accumulation.
	 * @param accumulator
	 *            The accumulator function.
	 * @return Let {@code F} denote this source DoubleFlow and {@code g} denote the
	 *         accumulation function. Then the DoubleFlow returned is of the form:
	 *         <ul>
	 *         <li>{@code [id, g(id, F[0]), g(g(id, F[0]), F[1]), ... ]}</li>
	 *         </ul>
	 */
	DoubleFlow accumulate(double id, DoubleBinaryOperator accumulator);

	/**
	 * Calculates the minimum value in this {@linkplain DoubleFlow}.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * DoubleFlow.
	 *
	 * @return an {@link OptionalDouble} wrapping the smallest element in this
	 *         DoubleFlow or nothing if the iteration is empty.
	 */
	OptionalDouble min();

	/**
	 * Calculates the minimum value in this {@linkplain DoubleFlow}.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * DoubleFlow.
	 *
	 * @param defaultValue
	 *            - The value which will be returned if the source is empty.
	 *
	 * @return the smallest element in this DoubleFlow or the default value if the
	 *         iteration is empty.
	 */
	double min(double defaultValue);

	/**
	 * Calculates the minimum element in this {@linkplain DoubleFlow} by an
	 * embedding into the real numbers.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * DoubleFlow.
	 *
	 * @param defaultValue
	 *            The value returned if this DoubleFlow is empty.
	 *
	 * @param key
	 *            A function mapping the elements of this DoubleFlow into the real
	 *            numbers.
	 * @return The element of this DoubleFlow whose image under the key mapping is
	 *         the minimum among all images. A parameter default value is returned
	 *         if the source is empty. NaN images are ignored.
	 */
	double minByKey(double defaultValue, final DoubleUnaryOperator key);

	/**
	 * Calculates the minimum element in this {@linkplain DoubleFlow} by an
	 * embedding into the real numbers.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * DoubleFlow.
	 *
	 * @param key
	 *            A function mapping the elements of this DoubleFlow into the real
	 *            numbers.
	 * @return The element of this DoubleFlow whose image under the key mapping is
	 *         the minimum among all images. Nothing is returned if the source is
	 *         empty. NaN images are ignored.
	 */
	OptionalDouble minByKey(final DoubleUnaryOperator key);

	/**
	 * Calculates the maximum value in this {@linkplain DoubleFlow}.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * DoubleFlow.
	 *
	 * @return an {@link OptionalDouble} wrapping the largest element in this
	 *         DoubleFlow or nothing if the iteration is empty.
	 */
	OptionalDouble max();

	/**
	 * Calculates the maximum value in this {@linkplain DoubleFlow}.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * DoubleFlow.
	 *
	 * @param defaultValue
	 *            - The value which will be returned if the source is empty.
	 *
	 * @return The largest element in this DoubleFlow or the default value if the
	 *         iteration is empty.
	 */
	double max(double defaultValue);

	/**
	 * Calculates the maximum element in this {@linkplain DoubleFlow} by an
	 * embedding into the real numbers.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * DoubleFlow.
	 *
	 * @param defaultValue
	 *            The value returned if this DoubleFlow is empty.
	 *
	 * @param key
	 *            A function mapping the elements of this DoubleFlow into the real
	 *            numbers.
	 * @return The element of this DoubleFlow whose image under the key mapping is
	 *         the maximum among all images. A parameter default value is returned
	 *         if the source is empty. NaN images are ignored.
	 */
	double maxByKey(double defaultValue, final DoubleUnaryOperator key);

	/**
	 * Calculates the maximum element in this {@linkplain DoubleFlow} by an
	 * embedding into the real numbers.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * DoubleFlow.
	 *
	 * @param key
	 *            A function mapping the elements of this DoubleFlow into the real
	 *            numbers.
	 * @return The element of this DoubleFlow whose image under the key mapping is
	 *         the maximum among all images. Nothing is returned if the source is
	 *         empty. NaN images are ignored.
	 */
	OptionalDouble maxByKey(final DoubleUnaryOperator key);

	/**
	 * Checks whether every element in this {@linkplain DoubleFlow} is the same.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * DoubleFlow.
	 *
	 * @return True is every element of this DoubleFlow is equal, false otherwise.
	 */
	boolean areAllEqual();

	/**
	 * Checks whether every element in this {@linkplain DoubleFlow} passes the
	 * supplied {@linkplain DoublePredicate} test.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * DoubleFlow.
	 *
	 * @param predicate
	 *            The supplied test.
	 * @return True if every element passes the parameter predicate test, false
	 *         otherwise.
	 */
	boolean allMatch(final DoublePredicate predicate);

	/**
	 * Checks whether any element in this {@linkplain DoubleFlow} passes the
	 * supplied {@linkplain DoublePredicate} test.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * DoubleFlow.
	 *
	 * @param predicate
	 *            The supplied test.
	 * @return True if any element passes the parameter predicate test, false
	 *         otherwise.
	 */
	boolean anyMatch(final DoublePredicate predicate);

	/**
	 * Checks whether every element in this {@linkplain DoubleFlow} fails the
	 * supplied {@linkplain DoublePredicate} test.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * DoubleFlow.
	 *
	 * @param predicate
	 *            The supplied test.
	 * @return True if every element fails the parameter predicate test, false
	 *         otherwise.
	 */
	boolean noneMatch(final DoublePredicate predicate);

	/**
	 * Partitions the elements of this {@linkplain DoubleFlow} on whether they pass
	 * the supplied {@linkplain DoublePredicate} test.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * DoubleFlow.
	 *
	 * @param predicate
	 *            The supplied test.
	 * @return A partition of the cached elements split into two arrays on whether
	 *         they passed or failed the parameter predicate.
	 */
	DoublePredicatePartition partition(DoublePredicate predicate);

	/**
	 * Reduces this {@linkplain DoubleFlow} to a single value via some reduction
	 * function.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * DoubleFlow.
	 *
	 * @param id
	 *            The identity of the reduction operation
	 * @param reducer
	 *            The reduction function
	 * @return If we denote this source DoubleFlow by {@code F}, the length of
	 *         {@code F} by {@code n} and the reduction function by {@code f} then
	 *         the result is equal to: <br>
	 *         <br>
	 *         {@code f(...f(f(id, F[0]), F[1])..., F[n - 1])}
	 */
	double reduce(double id, DoubleBinaryOperator reducer);

	/**
	 * Reduces this {@linkplain DoubleFlow} to a single value via some reduction
	 * function.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * DoubleFlow.
	 *
	 * @param reducer
	 *            The reduction function
	 * @return Let us denote this source DoubleFlow by {@code F}, the length of
	 *         {@code F} by {@code n} and the reduction function by {@code f}. If
	 *         {@code n == 0} we return nothing, else we return: <br>
	 *         <br>
	 *         {@code f(...f(f(F[0], F[1]), F[2])..., F[n - 1])}
	 */
	OptionalDouble reduce(DoubleBinaryOperator reducer);

	/**
	 * Counts the number of elements in this {@linkplain DoubleFlow}.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * DoubleFlow.
	 *
	 * @return The number of elements in this DoubleFlow.
	 */
	long count();

	/**
	 * Caches the values in this {@linkplain DoubleFlow} to an array.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link DoubleFlow}.
	 *
	 * @return A double array containing all elements of this DoubleFlow with their
	 *         ordering retained.
	 */
	double[] toArray();

	/**
	 * Builds a {@linkplain Map} using the elements in this {@linkplain DoubleFlow}
	 * via two supplied functions.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * DoubleFlow.
	 *
	 * @param <K>
	 *            The type of the keys in the created mapping.
	 * @param <V>
	 *            The type of the values in the created mapping.
	 * @param keyMapper
	 *            A function mapping doubles to elements of the key type.
	 * @param valueMapper
	 *            A function mapping doubles to elements of the value type.
	 *
	 * @throws IllegalStateException
	 *             If two elements of this DoubleFlow map to the same key.
	 *
	 * @return A Map instance whose key-value pairs have a 1-to-1 correspondence
	 *         with the elements in this source DoubleFlow. More specifically if:
	 *         <ul>
	 *         <li>{@code k} denotes the key mapping function</li>
	 *         <li>{@code v} denotes the value mapping function</li>
	 *         </ul>
	 *         an element of this source DoubleFlow, say {@code e}, is associated to
	 *         the key value pair {@code (k(e), v(e))}.
	 */
	<K, V> Map<K, V> toMap(final DoubleFunction<K> keyMapper, final DoubleFunction<V> valueMapper);

	/**
	 * Groups elements in this {@linkplain DoubleFlow} via their image under some
	 * supplied classification function.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * DoubleFlow.
	 *
	 * @param <K>
	 *            The type of the keys in the final grouping map.
	 *
	 * @param classifier
	 *            A function defining the different groups of elements.
	 * @return A Map instance whose keys partition the elements of this source
	 *         DoubleFlow via the classification function. Elements in this source
	 *         DoubleFlow who have equal (under .equals() contract) images under the
	 *         classification function are grouped together in a double array
	 *         accessed by their shared classification key.
	 */
	<K> Map<K, double[]> groupBy(final DoubleFunction<K> classifier);

	/**
	 * A convenience method for applying a global function onto this
	 * {@linkplain DoubleFlow}.
	 *
	 * This method is potentially (depending on the supplied function) a 'consuming
	 * method', i.e. it will iterate through this DoubleFlow.
	 *
	 * A convenience method for applying a global function onto this DoubleFlow.
	 *
	 * @param <C>
	 *            The target type of the build function.
	 * @param builder
	 *            - a function whose input encompasses DoubleFlow instances of this
	 *            element type.
	 * @return the output of the supplied function applied to this DoubleFlow.
	 */
	default <C> C build(final Function<? super DoubleFlow, C> builder)
	{
		return builder.apply(this);
	}

	/**
	 * Boxes the primitive double values in this {@linkplain DoubleFlow}.
	 *
	 * @return a copy of this source DoubleFlow as a {@linkplain Flow} of boxed
	 *         {@linkplain Double} instances.
	 */
	default Flow<Double> boxed()
	{
		return mapToObject(x -> x);
	}
}
