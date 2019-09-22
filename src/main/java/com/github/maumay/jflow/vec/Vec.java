/**
 *
 */
package com.github.maumay.jflow.vec;

import com.github.maumay.jflow.impl.ArraySource;
import com.github.maumay.jflow.impl.CollectionSource;
import com.github.maumay.jflow.impl.EmptyIterator;
import com.github.maumay.jflow.impl.VecCollector;
import com.github.maumay.jflow.iterable.RichIterable;
import com.github.maumay.jflow.iterator.RichIterator;
import com.github.maumay.jflow.utils.Tup;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Stream;

/**
 * <p>
 * A Vec (vector) is an immutable wrapper around an object array which provides
 * a myriad of higher order methods for operating on the elements using enhanced
 * sequential iterators (see {@link RichIterator}). The immutability guarantee
 * means we can also construct streams which are well suited for parallelising
 * operations. Null values are not permitted to be stored inside this data
 * structure.
 * </p>
 *
 * @param <E> The element type of this Vec.
 *
 * @author ThomasB
 */
public interface Vec<E> extends RichIterable<E>, Indexable<E>
{
    /**
     * Creates an iterator traversing the elements in this vector in reverse order.
     *
     * @return An iterator traversing the elements in this vector in reverse order.
     */
    RichIterator<E> iterRev();

    /**
     * Creates a sequential stream over the elements in this vector.
     *
     * @return A sequential stream over the elements of this vector in order.
     */
    Stream<E> stream();

    /**
     * Creates a new vector by applying a function element-wise to this vector.
     *
     * @param         <R> The element type of the new vector.
     * @param mapFunc The function to apply to each element of this vector.
     * @return A new vector obtains by mapping this vector element-wise by the
     *         parameter function.
     */
    <R> Vec<R> map(Function<? super E, ? extends R> mapFunc);

    /**
     * Creates a new vector by applying a function element-wise to this vector
     * before flattening the images.
     *
     * @param         <R> The element type of the new vector.
     * @param mapFunc The function to apply to each element of this vector.
     * @return A new vector obtained by concatenating the images of the parameter
     *         function applied element-wise to this vector.
     */
    <R> Vec<R> flatMap(
            Function<? super E, ? extends Iterator<? extends R>> mapFunc);

    /**
     * Creates a new vector by applying a filter to the elements in this vector.
     *
     * @param predicate The predicate test to filter elements with.
     * @return A new vector contained the elements of this vector which pass the
     *         supplied predicate with their relative order preserved.
     */
    Vec<E> filter(Predicate<? super E> predicate);

    /**
     * Unsafely manipulates the element type of this vector by forcibly casting each
     * element in turn to the target type.
     *
     * @param <R> The target type
     * @return A new {@link RichIterator} with element type given by the supplied
     *         target type.
     */
    <R> Vec<R> cast();

    /**
     * Creates a new vector by appending the contents of an Iterable to this vector.
     *
     * @param other The source of the elements to append.
     * @return A new vector containing the elements of this vector with order
     *         retained followed by the elements of the parameter iterable whose
     *         order is defined by the iterator it produces.
     */
    Vec<E> append(Iterable<? extends E> other);

    /**
     * Creates a new vector by appending the contents of a Collection to this
     * vector.
     *
     * @param other The source of the elements to append.
     * @return A new vector containing the elements of this vector with order
     *         retained followed by the elements of the parameter collection whose
     *         order is defined by the iterator it produces.
     */
    Vec<E> append(Collection<? extends E> other);

    /**
     * Creates a new vector by appending a single element to this vector.
     *
     * @param other The element to append.
     * @return A new vector containing the elements of this vector with order
     *         preserved followed by the parameter element.
     */
    Vec<E> append(E other);

    /**
     * Creates a new vector by inserting the contents of an Iterable at the start of
     * this vector.
     *
     * @param other The source of the elements to insert.
     * @return A new vector containing the elements of the parameter iterable whose
     *         order is determined by the iterator it produces followed by the
     *         elements of this vector whose relative order is preserved.
     */
    Vec<E> insert(Iterable<? extends E> other);

    /**
     * Creates a new vector by inserting the contents of a collection at the start
     * of this vector.
     *
     * @param other The source of the elements to insert.
     * @return A new vector containing the elements of the parameter collection
     *         whose order is determined by the iterator it produces followed by the
     *         elements of this vector whose relative order is preserved.
     */
    Vec<E> insert(Collection<? extends E> other);

    /**
     * Creates a new vector by inserting a single element at the start of this
     * vector.
     *
     * @param other the element to insert.
     * @return A vector containing the parameter element followed by the elements of
     *         this vector whose relative order is preserved.
     */
    Vec<E> insert(E other);

    /**
     * Creates a new vector by taking n elements from the start of this vector.
     *
     * @param n The number of elements to take, must be non-negative.
     * @return A vector consisting of the first n elements of this vector with their
     *         relative order retained. If the requested number of elements is
     *         larger than the size of this vector then we will just return this
     *         vector, if it is less than zero an exception will be thrown.
     */
    Vec<E> take(int n);

    /**
     * Creates a new vector by taking elements from the start of this vector until
     * the given predicate fails.
     *
     * @param predicate The predicate test.
     * @return A vector consisting of the elements taken from the head of this
     *         vector until an element fails the given predicate. The first failure
     *         <b>is not</b> included.
     */
    Vec<E> takeWhile(Predicate<? super E> predicate);

    /**
     * Creates a new vector by skipping n elements from the start of this vector and
     * keeping the remainder.
     *
     * @param n The number of elements to skip.
     * @return A vector consisting of all but the first n elements of this vector
     *         with their relative order retained. If the requested number of
     *         elements is 0 then we just return this vector, if it is less than
     *         zero an exception will be thrown.
     */
    Vec<E> skip(int n);

    /**
     * Creates a new vector by skipping elements from the start of this vector until
     * the given predicate fails and keeping the rest.
     *
     * @param predicate The predicate test.
     * @return A vector consisting of the elements in this vector which occur after
     *         the first element which fails the predicate. This first failure
     *         <b>is</b> included.
     */
    Vec<E> skipWhile(Predicate<? super E> predicate);

    /**
     * Partitions this vector into two new vectors by applying a predicate test
     * element-wise starting from the head until it fails.
     *
     * @param predicate The predicate test.
     * @return A pair of vectors whose first element is the result of
     *         {@link Vec#takeWhile(Predicate)} and whose second element is a vector
     *         of all elements who were not included in the first vector.
     */
    Tup<Vec<E>, Vec<E>> span(Predicate<? super E> predicate);

    /**
     * Partitions this vector into two new vectors according to whether elements
     * pass or fail a given predicate test.
     *
     * @param predicate The predicate test.
     * @return A pair of vectors whose first element is all the elements of this
     *         vector which pass the given predicate, the second is all the
     *         failures. Relative ordering in these subvectors is preserved.
     */
    Tup<Vec<E>, Vec<E>> partition(Predicate<? super E> predicate);

    /**
     * Creates a new vector by sorting the elements of this vector according to the
     * given ordering.
     *
     * @param ordering The total ordering on the element type.
     * @return A copy of this vector where the elements are ordered according to the
     *         supplied comparator.
     */
    Vec<E> sorted(Comparator<? super E> ordering);

    /**
     * Creates a new primitive vector by applying the given function element-wise to
     * this vector.
     *
     * @param mappingFunc The mapping function.
     * @return A new primitive vector of the same size as this vector whose ith
     *         element is the image of the ith element of this vector under the
     *         given function.
     */
    DoubleVec mapToDouble(ToDoubleFunction<? super E> mappingFunc);

    /**
     * Creates a new primitive vector by applying the given function element-wise to
     * this vector.
     *
     * @param mappingFunc The mapping function.
     * @return A new primitive vector of the same size as this vector whose ith
     *         element is the image of the ith element of this vector under the
     *         given function.
     */
    IntVec mapToInt(ToIntFunction<? super E> mappingFunc);

    /**
     * Creates a new primitive vector by applying the given function element-wise to
     * this vector.
     *
     * @param mappingFunc The mapping function.
     * @return A new primitive vector of the same size as this vector whose ith
     *         element is the image of the ith element of this vector under the
     *         given function.
     */
    LongVec mapToLong(ToLongFunction<? super E> mappingFunc);

    // Default methods

    /**
     * Performs a linear search to check if this vector contains the given element.
     *
     * @param element The instance to check for membership.
     * @return true if the given element is in this vector, false otherwise.
     */
    default boolean contains(E element)
    {
        return any(x -> x.equals(element));
    }

    /**
     * Creates a parallel stream over the elements of this vector.
     *
     * @return A parallel stream over the elements of this vector.
     */
    default Stream<E> parstream()
    {
        return stream().parallel();
    }

    @Override
    default Vec<E> toVec()
    {
        return this;
    }

    // Static factories

    /**
     * Creates an empty vector.
     *
     * @param <E> The inferred type of the new empty vector.
     * @return An empty vector
     */
    static <E> Vec<E> empty()
    {
        return new EmptyIterator.OfObject<E>().toVec();
    }

    /**
     * Creates a new vector wrapping the given elements, <b>no</b> defensive copying
     * takes place if an array is passed. If a null reference is passed as an
     * argument then an exception will be thrown.
     *
     * @param          <E> The element type of the new vector.
     * @param elements the elements which will populate the resulting vector.
     * @return a vector wrapping the arguments.
     */
    @SafeVarargs
    static <E> Vec<E> of(E... elements)
    {
        return new ArraySource.OfObject<>(elements).toVec();
    }

    /**
     * Creates a new vector wrapping the given elements, <b>no</b> defensive copying
     * takes place if an array is passed. If a null reference is passed as an
     * argument then an exception will be thrown.
     *
     * @param          <E> The element type of the new vector.
     * @param elements the elements which will populate the resulting vector.
     * @return a vector wrapping the arguments.
     */
    @SafeVarargs
    static <E> Vec<E> vec(E... elements)
    {
        return new ArraySource.OfObject<>(elements).toVec();
    }

    /**
     * Copies the contents of the given collection into a vector, the ordering is
     * determined by the particular implementation of {@link Collection#iterator()}.
     * If the passed collection contains a null reference then an exception will be
     * thrown.
     *
     * @param            <E> The element type of the new vector.
     * @param collection the collection to copy the contents of.
     * @return a vector containing all the elements contained in the argument.
     */
    static <E> Vec<E> copy(Collection<? extends E> collection)
    {
        return new CollectionSource<E>(collection).toVec();
    }

    /**
     * Create a new {@link Collector} instance which can be used to convert a
     * {@link Stream} of elements to a vector.
     *
     * @param <E> The type of elements this collector can collect. It is inferred at
     *        the point of calling this method.
     *
     * @return A Collector used to convert a Stream to a vector.
     */
    static <E> VecCollector<E> collector()
    {
        return new VecCollector<>();
    }
}
