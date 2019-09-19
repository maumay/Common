package com.github.maumay.jflow.iterable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import com.github.maumay.jflow.iterator.RichIterator;
import com.github.maumay.jflow.iterator.collector.IteratorCollector;
import com.github.maumay.jflow.vec.Vec;

/**
 * Abstraction of iterable object which can construct enhanced iterators
 * ({@link RichIterator}).
 *
 * @param <E> The type of element this object can iterate over.
 *
 * @author ThomasB
 */
@FunctionalInterface
public interface RichIterable<E> extends Iterable<E>
{
	/**
	 * Constructs an iterator traversing the elements encapsulated by this object.
	 * 
	 * @return See above.
	 */
	RichIterator<E> iter();

	@Override
	default RichIterator<E> iterator()
	{
		return iter();
	}

	/**
	 * Finds the first element (if any) which satisfies a given predicate.
	 * 
	 * @param predicate The predicate which will be used to test elements.
	 * @return The first element to pass the predicate test if there is one, nothing
	 *         otherwise.
	 */
	default Optional<E> findOp(Predicate<? super E> predicate)
	{
		return iter().filter(predicate).nextOp();
	}

	/**
	 * Finds the minimum element in the iterator created by {@link #iter()}
	 * according to the given comparator.
	 * 
	 * @param orderingFunction the complete ordering of this element type.
	 * @return see {@link RichIterator#minOp(Comparator)}.
	 */
	default Optional<E> minOp(Comparator<? super E> orderingFunction)
	{
		return iter().minOp(orderingFunction);
	}

	/**
	 * Finds the minimum element in the iterator created by {@link #iter()}
	 * according to the given comparator throwing an exception if the iterator is
	 * empty.
	 * 
	 * @param orderingFunction the complete ordering of this element type.
	 * @return see {@link RichIterator#minOp(Comparator)}.
	 */
	default E min(Comparator<? super E> orderingFunction)
	{
		return iter().min(orderingFunction);
	}

	/**
	 * Finds the maximum element in the iterator created from this iterable by
	 * {@link #iter()} according to the given comparator.
	 * 
	 * @param orderingFunction the complete ordering of this element type.
	 * @return see {@link RichIterator#maxOp(Comparator)}.
	 */
	default Optional<E> maxOp(Comparator<? super E> orderingFunction)
	{
		return iter().maxOp(orderingFunction);
	}

	/**
	 * Finds the maximum element in the iterator created by {@link #iter()}
	 * according to the given comparator throwing an exception if the iterator is
	 * empty.
	 * 
	 * @param orderingFunction the complete ordering of this element type.
	 * @return see {@link RichIterator#maxOp(Comparator)}.
	 */
	default E max(Comparator<? super E> orderingFunction)
	{
		return iter().max(orderingFunction);
	}

	/**
	 * Calculates whether every element in the iterator created by {@link #iter()}
	 * passes the supplied predicate.
	 * 
	 * @param condition The predicate test.
	 * @return true if all elements pass the test or the iterator is empty, false
	 *         otherwise.
	 */
	default boolean all(Predicate<? super E> condition)
	{
		return iter().all(condition);
	}

	/**
	 * Spawns an enhanced iterator and delegates to its
	 * {@link EnhancedIterator#anyMatch(Predicate)} method.
	 */
	/**
	 * Calculates at least one element in the iterator created by {@link #iter()}
	 * passes the supplied predicate.
	 * 
	 * @param condition The predicate test.
	 * @return true if at least one element passes the test, false otherwise.
	 */
	default boolean any(Predicate<? super E> condition)
	{
		return iter().any(condition);
	}

	/**
	 * Calculates whether all elements in the iterator created by {@link #iter()}
	 * fail the supplied predicate.
	 * 
	 * @param condition The predicate test.
	 * @return true if all elements fail the test or the iterator is empty, false
	 *         otherwise.
	 */
	default boolean none(Predicate<? super E> condition)
	{
		return iter().none(condition);
	}

	/**
	 * Groups the elements of the iterator created by {@link #iter()} according to
	 * some classification function.
	 * 
	 * @param            <K> The type of the classification values.
	 * @param classifier The classification function.
	 * @return see {@link RichIterator#groupBy(Function)}
	 */
	default <K> Map<K, List<E>> groupBy(Function<? super E, K> classifier)
	{
		return iter().groupBy(classifier);
	}

	/**
	 * Folds the elements of the iterator created by {@link #iter()} into one value
	 * using a provided reduction operator and a starting value.
	 * 
	 * @param         <R> The type of the fold result.
	 * @param id      The initial value of the fold operation.
	 * @param reducer The reduction operator governing how two values are combined
	 *                into one.
	 * 
	 * @return see {@link RichIterator#fold(Object, BiFunction)}
	 */
	default <R> R fold(R id, BiFunction<R, E, R> reducer)
	{
		return iter().fold(id, reducer);
	}

	/**
	 * Folds the elements of the iterator created by {@link #iter()} into one value
	 * using a given reduction function making an allowance for a potentially empty
	 * source.
	 * 
	 * @param reducer The reduction operator governing how two values are combined
	 *                into one.
	 * @return see {@link RichIterator#foldOp(BinaryOperator)}
	 */
	default Optional<E> foldOp(BinaryOperator<E> reducer)
	{
		return iter().foldOp(reducer);
	}

	/**
	 * Folds the elements of the iterator created by {@link #iter()} into one value
	 * using a given reduction function throwing an exception if the source is
	 * empty.
	 * 
	 * @param reducer The reduction operator governing how two values are combined
	 *                into one.
	 * @return see {@link RichIterator#fold(BinaryOperator)}
	 */
	default E fold(BinaryOperator<E> reducer)
	{
		return iter().fold(reducer);
	}

	/**
	 * Builds a Map from the elements in the iterator created by {@link #iter()} by
	 * applying two functions to each value.
	 * 
	 * @param          <K> The type of the keys in the constructed map.
	 * @param          <V> The type of the values in the constructed map
	 * @param keyMap   The function determining the map keys.
	 * @param valueMap The function determining the map values.
	 * @return see {@link RichIterator#toMap(Function, Function)}
	 */
	default <K, V> Map<K, V> toMap(Function<? super E, ? extends K> keyMap,
			Function<? super E, ? extends V> valueMap)
	{
		return iter().toMap(keyMap, valueMap);
	}

	/**
	 * Builds a Map from the elements in the iterator created by {@link #iter()} by
	 * associating each with it's image under the provided function.
	 * 
	 * @param          <V> the type of the values in the constructed map.
	 * @param valueMap The function determining the map values.
	 * @return see {@link RichIterator#associate(Function)}
	 */
	default <V> Map<E, V> associate(Function<? super E, ? extends V> valueMap)
	{
		return iter().associate(valueMap);
	}

	/**
	 * Builds an ArrayList from the elements in the iterator created by
	 * {@link #iter()}.
	 * 
	 * @return see {@link RichIterator#toList()}
	 */
	default List<E> toList()
	{
		return to(ArrayList::new);
	}

	/**
	 * Builds an HashSet from the elements in the iterator created by
	 * {@link #iter()}.
	 * 
	 * @return see {@link RichIterator#toSet()}
	 */
	default Set<E> toSet()
	{
		return to(HashSet::new);
	}

	/**
	 * Builds a collection from the elements in the iterator created by
	 * {@link #iter()}.
	 * 
	 * @param                   <C> The type of collection to build
	 * @param collectionFactory A supplier of empty, mutable instances of the
	 *                          required collection type.
	 * @return see {@link RichIterator#to(Supplier)}
	 */
	default <C extends Collection<E>> C to(Supplier<C> collectionFactory)
	{
		return iter().to(collectionFactory);
	}

	/**
	 * Builds a vector from the elements traversed by the iterator created by
	 * {@link #iter()}
	 * 
	 * @return see {@link RichIterator#toVec()}
	 */
	default Vec<E> toVec()
	{
		return iter().toVec();
	}

	/**
	 * Creates a new instance of the given type by applying the given collector to
	 * the iterator returned by {@link #iter()}.
	 *
	 * @param                   <R> The target type of the transformation.
	 * @param iteratorCollector The collector which determines how this vector will
	 *                          be transformed.
	 * @return The image of the transformation under the supplied collector.
	 */
	default <R> R transform(IteratorCollector<? super E, R> iteratorCollector)
	{
		return iter().collect(iteratorCollector);
	}

//	/**
//	 * Creates a new instance of the given type by applying the given collector to
//	 * the iterator returned by {@link #iter()}.
//	 *
//	 * @param                   <R> The target type of the transformation.
//	 * @param iteratorCollector The collector which determines how this vector will
//	 *                          be transformed.
//	 * @return The image of the transformation under the supplied collector.
//	 */
//	default <R> R transform(IteratorCollector2<? super E, ? extends R> iteratorCollector)
//	{
//		return iter().collect(iteratorCollector);
//	}
}
