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
package gr.hua.utils.range.numeric;

import gr.hua.utils.range.RangeIterator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

/**
 *
 * @author NickZorb
 */
public class GenericIntegerRange extends GenericNumericRange<Integer> {

    private class GenericIntegerRangeIterator implements RangeIterator<Integer> {

        private final int expectedModCount;
        private Integer cur = null;

        public GenericIntegerRangeIterator() {
            expectedModCount = GenericIntegerRange.this.modCount;
        }

        @Override
        public Integer previous() {
            checkValidity();
            if (hasPrevious()) {
                cur -=  GenericIntegerRange.this.step;
                return cur;
            }
            throw new NoSuchElementException();
        }

        @Override
        public Integer current() {
            checkValidity();
            if (cur != null) {
                return cur;
            }
            throw new NoSuchElementException();
        }

        @Override
        public void setCurrent(Integer item) {
            checkValidity();
            if (item == null || GenericIntegerRange.this.contains(item)) {
                cur = item;
            }
            throw new NoSuchElementException();
        }

        @Override
        public boolean hasPrevious() {
            return checkValidity() && cur != null && cur - step >= (GenericIntegerRange.this.startIncluded ? GenericIntegerRange.this.start : GenericIntegerRange.this.start + GenericIntegerRange.this.step);
        }

        @Override
        public RangeIterator clone() {
            checkValidity();
            GenericIntegerRangeIterator res = new GenericIntegerRangeIterator();
            res.setCurrent(current());
            return res;
        }

        @Override
        public boolean hasNext() {
            if (cur == null) {
                return checkValidity() && !GenericIntegerRange.this.empty();
            }
            return checkValidity() && cur + GenericIntegerRange.this.step <= (GenericIntegerRange.this.endIncluded ? GenericIntegerRange.this.end : GenericIntegerRange.this.end - GenericIntegerRange.this.step);
        }

        @Override
        public Integer next() {
            checkValidity();
            if (hasNext()) {
                cur = cur == null ? GenericIntegerRange.this.startIncluded ? GenericIntegerRange.this.start :  GenericIntegerRange.this.start +  GenericIntegerRange.this.step : cur +  GenericIntegerRange.this.step;
                return cur;
            }
            throw new NoSuchElementException();
        }

        private boolean checkValidity() {
            if (expectedModCount != GenericIntegerRange.this.modCount) {
                throw new ConcurrentModificationException();
            }
            return true;
        }

    }

    public GenericIntegerRange(boolean startIncluded, boolean endIncluded, Integer start, Integer end, Integer step) {
        super(startIncluded, endIncluded, start, end, step);
        if (start > end) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public GenericNumericRange<Integer> clone() {
        return new GenericIntegerRange(startIncluded, endIncluded, start, end, step);
    }

    @Override
    public void times(Integer mul, boolean increaseStep) {
        modCount++;
        start *= mul;
        end *= mul;
        if (increaseStep) {
            step = Math.abs(step * mul);
        }
        checkEndsAndSwap();
    }

    @Override
    public void divide(Integer div, boolean decreaseStep) {
        modCount++;
        start /= div;
        end /= div;
        if (decreaseStep) {
            step = Math.abs(step / div);
            if (step == 0) {
                step = 1;
            }
        }
        checkEndsAndSwap();
    }
    
    private void checkEndsAndSwap() {
        if (start > end) {
            start += end;
            end = start - end;
            start -= end;
        }
    }

    @Override
    public void setStart(Integer start, boolean included) {
        modCount++;
        if (start > end) {
            throw new IllegalArgumentException();
        }
        this.start = start;
        startIncluded = included;
    }

    @Override
    public void setEnd(Integer end, boolean included) {
        modCount++;
        if (start > end) {
            throw new IllegalArgumentException();
        }
        this.end = end;
        endIncluded = included;
    }

    @Override
    public void transfer(Integer dgr) {
        modCount++;
        start += dgr;
        end += dgr;
    }

    @Override
    public boolean empty() {
        return (startIncluded ? start : start + step) > (endIncluded ? end : end - step);
    }

    @Override
    public boolean contains(Integer item) {
        return (startIncluded ? start : start + step) <= item && (endIncluded ? end : end - step) >= item && (item - start) % step == 0;
    }

    @Override
    public RangeIterator<Integer> iterate() {
        return new GenericIntegerRangeIterator();
    }

    @Override
    public int estimateSize() {
        return ((endIncluded ? end : end - step) - (startIncluded ? start : start + step)) / step + 1;
    }
}
