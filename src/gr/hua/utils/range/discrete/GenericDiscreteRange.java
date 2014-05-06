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
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 *
 * @author NickZorb
 * @param <T>
 */
public class GenericDiscreteRange<T> implements DiscreteRange<T> {

    private final ArrayList<T> items;
    private int modCount = 0;

    @Override
    public int estimateSize() {
        return items.size();
    }

    private class GenericDiscreteRangeIterator implements RangeIterator<T> {

        private final int expectedModCount;
        private int cur = -1;

        public GenericDiscreteRangeIterator() {
            expectedModCount = GenericDiscreteRange.this.modCount;
        }

        @Override
        public T next() {
            checkValidity();
            if (hasNext()) {
                return (T) GenericDiscreteRange.this.items.get(++cur);
            }
            throw new NoSuchElementException();
        }

        @Override
        public T previous() {
            checkValidity();
            if (hasPrevious()) {
                return (T) GenericDiscreteRange.this.items.get(--cur);
            }
            throw new NoSuchElementException();
        }

        @Override
        public T current() {
            checkValidity();
            if (cur > -1) {
                return (T) GenericDiscreteRange.this.items.get(cur);
            }
            throw new NoSuchElementException();
        }

        @Override
        public void setCurrent(T item) {
            checkValidity();
            if (item == null) {
                cur = -1;
            } else if (GenericDiscreteRange.this.items.contains(item)) {
                cur = GenericDiscreteRange.this.items.indexOf(item);
            }
            throw new NoSuchElementException();
        }

        @Override
        public boolean hasNext() {
            return checkValidity() && cur < GenericDiscreteRange.this.items.size() - 1;
        }

        @Override
        public boolean hasPrevious() {
            return checkValidity() && cur > 0;
        }

        @Override
        public RangeIterator clone() {
            checkValidity();
            GenericDiscreteRangeIterator res = new GenericDiscreteRangeIterator();
            res.setCurrent(current());
            return res;
        }

        private boolean checkValidity() {
            if (expectedModCount != GenericDiscreteRange.this.modCount) {
                throw new ConcurrentModificationException();
            }
            return true;
        }

    }

    public GenericDiscreteRange() {
        items = new ArrayList();
    }

    @Override
    public void remove(T item) {
        modCount++;
        while (items.contains(item)) {
            items.remove(item);
        }
    }

    @Override
    public void append(T item) {
        modCount++;
        items.add(item);
    }

    @Override
    public Set<T> itemSet() {
        return new HashSet(items);
    }

    @Override
    public boolean empty() {
        return items.isEmpty();
    }

    @Override
    public boolean contains(T item) {
        return items.contains(item);
    }

    @Override
    public RangeIterator<T> iterate() {
        return new GenericDiscreteRangeIterator();
    }

    @Override
    public Range<T> clone() {
        GenericDiscreteRange<T> res = new GenericDiscreteRange();
        res.append(this);
        return res;
    }

    @Override
    public List<DiscreteRange<T>> split(T sub) {
        modCount++;
        ArrayList<DiscreteRange<T>> result = new ArrayList();
        if (items.contains(sub)) {
            ArrayList<ArrayList<T>> itemLists = new ArrayList();
            itemLists.add(new ArrayList());
            int list = 0;
            for (T item : items) {
                if (item == sub) {
                    list++;
                    itemLists.add(new ArrayList());
                    continue;
                }
                itemLists.get(list).add(item);
            }
            if (itemLists.get(itemLists.size() - 1).isEmpty()) {
                itemLists.remove(itemLists.size() - 1);
            }
            for (int i = 0; i < itemLists.size(); i++) {
                result.add(new GenericDiscreteRange());
                result.get(i).append(itemLists.get(i));
            }
        } else {
            result.add((DiscreteRange<T>) this.clone());
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{");
        for (int i = 0; i < items.size(); i++) {
            result.append("\"").append(items.get(i)).append("\"");
            if (i < items.size() - 1) {
                result.append(", ");
            }
        }
        result.append("}");
        return result.toString();
    }

    public boolean equals(GenericDiscreteRange target) {
        for (int i = 0; i < items.size(); i++) {
            if (target.items.get(i) != items.get(i)) {
                return false;
            }
        }
        return true;
    }

}
