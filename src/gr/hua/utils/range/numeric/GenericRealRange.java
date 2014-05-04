/*
 * Copyright 2014 NickZorb.
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
public class GenericRealRange extends GenericNumericRange<Double> {
    
    private class GenericRealRangeIterator implements RangeIterator<Double> {

        private final int expectedModCount;
        private Double cur = null;

        public GenericRealRangeIterator() {
            expectedModCount = GenericRealRange.this.modCount;
        }

        @Override
        public Double previous() {
            checkValidity();
            if (hasPrevious()) {
                cur -=  GenericRealRange.this.step;
                return cur;
            }
            throw new NoSuchElementException();
        }

        @Override
        public Double current() {
            checkValidity();
            if (cur != null) {
                return cur;
            }
            throw new NoSuchElementException();
        }

        @Override
        public void setCurrent(Double item) {
            checkValidity();
            if (item == null || GenericRealRange.this.contains(item)) {
                cur = item;
            }
            throw new NoSuchElementException();
        }

        @Override
        public boolean hasPrevious() {
            return checkValidity() && cur != null && cur - step >= (GenericRealRange.this.startIncluded ? GenericRealRange.this.start : GenericRealRange.this.start + GenericRealRange.this.step);
        }

        @Override
        public RangeIterator clone() {
            checkValidity();
            GenericRealRangeIterator res = new GenericRealRangeIterator();
            res.setCurrent(current());
            return res;
        }

        @Override
        public boolean hasNext() {
            if (cur == null) {
                return checkValidity() && !GenericRealRange.this.empty();
            }
            return checkValidity() && cur + GenericRealRange.this.step <= (GenericRealRange.this.endIncluded ? GenericRealRange.this.end : GenericRealRange.this.end - GenericRealRange.this.step);
        }

        @Override
        public Double next() {
            checkValidity();
            if (hasNext()) {
                cur = cur == null ? GenericRealRange.this.startIncluded ? GenericRealRange.this.start :  GenericRealRange.this.start +  GenericRealRange.this.step : cur +  GenericRealRange.this.step;
                return cur;
            }
            throw new NoSuchElementException();
        }

        private boolean checkValidity() {
            if (expectedModCount != GenericRealRange.this.modCount) {
                throw new ConcurrentModificationException();
            }
            return true;
        }
    }

    public GenericRealRange(boolean startIncluded, boolean endIncluded, Double start, Double end, Double step) {
        super(startIncluded, endIncluded, start, end, step);
        if (start > end) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public GenericNumericRange<Double> clone() {
        return new GenericRealRange(startIncluded, endIncluded, start, end, step);
    }

    @Override
    public void times(Double mul, boolean increaseStep) {
        modCount++;
        start *= mul;
        end *= mul;
        if (increaseStep) {
            step = Math.abs(step * mul);
        }
        checkEndsAndSwap();
    }

    @Override
    public void divide(Double div, boolean decreaseStep) {
        modCount++;
        start /= div;
        end /= div;
        if (decreaseStep) {
            step = Math.abs(step / div);
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
    public void setStart(Double start, boolean included) {
        modCount++;
        if (start > end) {
            throw new IllegalArgumentException();
        }
        this.start = start;
        startIncluded = included;
    }

    @Override
    public void setEnd(Double end, boolean included) {
        modCount++;
        if (start > end) {
            throw new IllegalArgumentException();
        }
        this.end = end;
        endIncluded = included;
    }

    @Override
    public void transfer(Double dgr) {
        modCount++;
        start += dgr;
        end += dgr;
    }

    @Override
    public boolean empty() {
        return (startIncluded ? start : start + step) > (endIncluded ? end : end - step);
    }

    @Override
    public boolean contains(Double item) {
        return (startIncluded ? start : start + step) <= item && (endIncluded ? end : end - step) >= item && (item - start) % step == 0;
    }

    @Override
    public RangeIterator<Double> iterate() {
        return new GenericRealRangeIterator();
    }

    @Override
    public int estimateSize() {
        return (int) (((endIncluded ? end : end - step) - (startIncluded ? start : start + step)) / step + 1);
    }
}
