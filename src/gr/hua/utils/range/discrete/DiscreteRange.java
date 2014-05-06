/*
 * Copyright 2014 Nick Zorbas.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gr.hua.utils.range.discrete;

import gr.hua.utils.range.Range;
import gr.hua.utils.range.RangeIterator;
import gr.hua.utils.range.numeric.NumericRange;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 *
 * @author NickZorb
 * @param <T>
 */
public interface DiscreteRange<T> extends Range<T> {

    static <E extends Number, K extends DiscreteRange<E>> void discretize(NumericRange<E> num, K target) {
        RangeIterator<E> iter = num.iterate();
        while (iter.hasNext()) {
            target.append(iter.next());
        }
    }

    static <E> void combine(DiscreteRange<E> target, DiscreteRange<E>... ranges) {
        for (DiscreteRange<E> range : ranges) {
            target.append(range);
        }
    }
    
    static DiscreteRange<String> parseRange(String s) {
        if (!s.startsWith("{")) {
            throw new IllegalArgumentException();
        }
        GenericDiscreteRange<String> res = new GenericDiscreteRange();
        Arrays.stream(s.trim().replaceAll("[{}]", "").split("(\\\", )?\\\"")).forEach(item -> {if (!item.equals("")) res.append(item);});
        return res;
    }

    void remove(T item);
    
    void append(T item);
    
    default void append(T item, T... items) {
        append(item);
        for (T cur : items) {
            append(cur);
        }
    }

    default void remove(Collection<T> sub) {
        for (T item : sub) {
            remove(item);
        }
    }

    default void remove(DiscreteRange<T> sub) {
        RangeIterator<T> iter = sub.iterate();
        while (iter.hasNext()) {
            remove(iter.next());
        }
    }

    default void append(Collection<T> add) {
        for (T item : add) {
            append(item);
        }
    }

    default void append(DiscreteRange<T> add) {
        RangeIterator<T> iter = add.iterate();
        while (iter.hasNext()) {
            append(iter.next());
        }
    }

    Set<T> itemSet();
    
    List<DiscreteRange<T>> split(T sub);
    
    @Override
    String toString();
}
