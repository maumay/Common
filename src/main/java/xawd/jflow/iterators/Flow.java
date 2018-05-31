package xawd.jflow.iterators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PrimitiveIterator;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import xawd.jflow.iterators.iterables.DoubleFlowIterable;
import xawd.jflow.iterators.iterables.IntFlowIterable;
import xawd.jflow.iterators.iterables.LongFlowIterable;
import xawd.jflow.iterators.misc.DoubleWith;
import xawd.jflow.iterators.misc.IntWith;
import xawd.jflow.iterators.misc.LongWith;
import xawd.jflow.iterators.misc.Pair;
import xawd.jflow.iterators.misc.PredicatePartition;

/**
 * @author ThomasB
 * @since 20 Apr 2018
 */
public interface Flow<E> extends SkippableIterator<E>
{
	/**
	 * @param f - A mapping function.
	 * @return a new {@linkplain Flow} instance whose
	 * elements are obtained by applying the parameter mapping
	 * function to each element of this {@linkplain Flow} instance
	 * in turn.
	 */
	<R> Flow<R> map(final Function<? super E, R> f);

	/**
	 * @param f - A mapping function.
	 * @return a new {@linkplain IntFlow} instance whose
	 * elements are obtained by applying the parameter mapping
	 * function to each element of this {@linkplain Flow} instance
	 * in turn.
	 */
	IntFlow mapToInt(ToIntFunction<? super E> f);

	/**
	 * @param f - A mapping function.
	 * @return a new {@linkplain DoubleFlow} instance whose
	 * elements are obtained by applying the parameter mapping
	 * function to each element of this {@linkplain Flow} instance
	 * in turn.
	 */
	DoubleFlow mapToDouble(ToDoubleFunction<? super E> f);

	/**
	 * @param f - A mapping function.
	 * @return a new {@linkplain LongFlow} instance whose
	 * elements are obtained by applying the parameter mapping
	 * function to each element of this {@linkplain Flow} instance
	 * in turn.
	 */
	LongFlow mapToLong(ToLongFunction<? super E> f);

	/**
	 * @param mapping - A function taking elements to instances of {@linkplain Flow}
	 * @return a {@linkplain Flow} obtained by applying the mapping function to
	 * each element in turn and sequentially concatenating the results.
	 */
	<R> Flow<R> flatten(Function<? super E, ? extends Flow<R>> mapping);

	/**
	 * @param mapping - A function taking elements to instances of {@linkplain IntFlow}
	 * @return a {@linkplain IntFlow} obtained by applying the mapping function to
	 * each element in turn and sequentially concatenating the results.
	 */
	IntFlow flattenToInts(Function<? super E, ? extends IntFlow> mapping);

	/**
	 * @param mapping - A function taking elements to instances of {@linkplain LongFlow}
	 * @return a {@linkplain LongFlow} obtained by applying the mapping function to
	 * each element in turn and sequentially concatenating the results.
	 */
	LongFlow flattenToLongs(Function<? super E, ? extends LongFlow> mapping);

	/**
	 * @param mapping - A function taking elements to instances of {@linkplain DoubleFlow}
	 * @return a {@linkplain DoubleFlow} obtained by applying the mapping function to
	 * each element in turn and sequentially concatenating the results.
	 */
	DoubleFlow flattenToDoubles(Function<? super E, ? extends DoubleFlow> mapping);

	/**
	 * Zips this {@linkplain Flow} {@code F} with the parameter {@linkplain Iterator} {@code I}
	 * producing a new {@linkplain Flow} instance {@code G} given by:<br><br> {@code G[j] = (F[j], I[j])}
	 * <br>{@code length(G) = min(length(F), length(I))}
	 *
	 * @param other - The {@linkplain Iterator} to zip with.
	 * @return the result described above.
	 */
	<R> Flow<Pair<E, R>> zipWith(final Iterator<? extends R> other);

	Flow<IntWith<E>> zipWith(final PrimitiveIterator.OfInt other);

	Flow<DoubleWith<E>> zipWith(final PrimitiveIterator.OfDouble other);

	Flow<LongWith<E>> zipWith(final PrimitiveIterator.OfLong other);

	<E2, R> Flow<R> combineWith(final Iterator<? extends E2> other, final BiFunction<? super E, ? super E2, R> f);

	Flow<IntWith<E>> enumerate();

	Flow<E> slice(final IntUnaryOperator f);

	Flow<E> take(final int n);

	Flow<E> takeWhile(final Predicate<? super E> p);

	Flow<E> skip(final int n);

	Flow<E> skipWhile(final Predicate<? super E> p);

	Flow<E> filter(final Predicate<? super E> p);

	Flow<E> append(Iterator<? extends E> other);

	Flow<E> insert(Iterator<? extends E> other);

	Flow<E> accumulate(BinaryOperator<E> accumulator);

	<R> Flow<R> accumulate(R id, BiFunction<R, E, R> accumulator);


	Optional<E> minByKey(final ToDoubleFunction<? super E> key);

	<C extends Comparable<C>> Optional<E> minByObjectKey(final Function<? super E, C> key);

	Optional<E> maxByKey(final ToDoubleFunction<? super E> key);

	<C extends Comparable<C>> Optional<E> maxByObjectKey(final Function<? super E, C> key);


	boolean areAllEqual();

	boolean allMatch(final Predicate<? super E> predicate);

	boolean anyMatch(final Predicate<? super E> predicate);

	boolean noneMatch(final Predicate<? super E> predicate);

	PredicatePartition<E> partition(Predicate<? super E> predicate);


	<R> R reduce(R id, BiFunction<R, E, R> reducer);

	Optional<E> reduce(BinaryOperator<E> reducer);

	int count();


	<C extends Collection<E>> C toCollection(final Supplier<C> collectionFactory);

	<K, V> Map<K, V> toMap(final Function<? super E, K> keyMapper, final Function<? super E, V> valueMapper);

	<K> Map<K, List<E>> groupBy(final Function<? super E, K> classifier);


	//********** DEFAULT METHODS ***********//

	default <R> Flow<R> filterAndCastTo(final Class<R> klass)
	{
		return filter(klass::isInstance).map(klass::cast);
	}

	default <R> R build(final Function<? super Flow<E>, R> builder)
	{
		return builder.apply(this);
	}

	default Flow<E> append(final E e)
	{
		return append(Arrays.asList(e));
	}

	default Flow<E> insert(final E ts)
	{
		return append(Arrays.asList(ts));
	}

	default List<E> toList()
	{
		return toCollection(ArrayList::new);
	}

	default List<E> toImmutableList()
	{
		return Collections.unmodifiableList(toList());
	}

	default Set<E> toImmutableSet()
	{
		return Collections.unmodifiableSet(toSet());
	}

	default Set<E> toSet()
	{
		return toCollection(HashSet::new);
	}

	default <R> Flow<Pair<E, R>> zipWith(final Iterable<? extends R> other)
	{
		return zipWith(other.iterator());
	}

	default Flow<IntWith<E>> zipWith(final IntFlowIterable other)
	{
		return zipWith(other.iterator());
	}

	default Flow<DoubleWith<E>> zipWith(final DoubleFlowIterable other)
	{
		return zipWith(other.iterator());
	}

	default Flow<LongWith<E>> zipWith(final LongFlowIterable other)
	{
		return zipWith(other.iterator());
	}

	default <U, R> Flow<R> combineWith(final Iterable<U> other, final BiFunction<E, U, R> f)
	{
		return combineWith(other.iterator(), f);
	}

	default Flow<E> append(final Iterable<? extends E> other)
	{
		return append(other.iterator());
	}

	default Flow<E> insert(final Iterable<? extends E> other)
	{
		return insert(other.iterator());
	}
}
