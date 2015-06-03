/*
 * Copyright 2014 Nikolaos Zormpas.
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
import java.util.NoSuchElementException;

/**
 *
 * @author Nikolaos Zormpas
 */
public class GenericIntegerRange extends GenericNumericRange<Integer> {

    private class GenericIntegerRangeIterator implements RangeIterator<Integer> {
        
        private Integer cur = null;
        private Integer step;

        public GenericIntegerRangeIterator() {
            step = GenericIntegerRange.this.minStep;
        }

        @Override
        public Integer previous() {
            if (hasPrevious()) {
                cur -=  step;
                return cur;
            }
            throw new NoSuchElementException();
        }

        @Override
        public Integer current() {
            if (cur != null) {
                return cur;
            }
            throw new NoSuchElementException();
        }

        @Override
        public Integer next() {
            if (hasNext()) {
                cur = cur == null ? GenericIntegerRange.this.startIncluded ? GenericIntegerRange.this.start :  GenericIntegerRange.this.start +  step : cur +  step;
                return cur;
            }
            throw new NoSuchElementException();
        }

        @Override
        public void setCurrent(Integer item) {
            if (item == null || GenericIntegerRange.this.contains(item)) {
                cur = item;
            }
            throw new NoSuchElementException();
        }

        @Override
        public boolean hasPrevious() {
            return cur != null && cur - step >= (GenericIntegerRange.this.startIncluded ? GenericIntegerRange.this.start : GenericIntegerRange.this.start + step);
        }

        @Override
        public boolean hasNext() {
            return (cur == null && !GenericIntegerRange.this.empty()) || cur + step <= (GenericIntegerRange.this.endIncluded ? GenericIntegerRange.this.end : GenericIntegerRange.this.end - step);
        }

        @Override
        public void setSpeed(Integer step) {
            this.step = step;
        }

        @Override
        public Integer getSpeed() {
            return step;
        }

        @Override
        public Integer minSpeed() {
            return GenericIntegerRange.this.minStep;
        }

        @Override
        public Integer maxSpeed() {
            return GenericIntegerRange.this.maxStep;
        }

        @Override
        public RangeIterator clone() {
            GenericIntegerRangeIterator res = new GenericIntegerRangeIterator();
            res.setCurrent(cur);
            res.setSpeed(step);
            return res;
        }
    }

    public GenericIntegerRange(boolean startIncluded, boolean endIncluded, Integer start, Integer end, Integer minStep, Integer maxStep) {
        super(startIncluded, endIncluded, start, end, minStep, maxStep);
        while (this.maxStep > end - start) {
            this.maxStep--;
        }
    }

    @Override
    public GenericNumericRange<Integer> clone() {
        return new GenericIntegerRange(startIncluded, endIncluded, start, end, minStep, maxStep);
    }
    
    @Override
    public NumericRange<Integer> times(Integer mul, boolean alterStep) {
        int newStart = start * mul;
        int newEnd = end * mul;
        if (newStart > newEnd) {
            newStart += newEnd;
            newEnd = newStart - newEnd;
            newStart -= newEnd;
        }
        int newMinStep = minStep;
        int newMaxStep = maxStep;
        if (alterStep) {
            Math.abs(newMinStep *= mul);
            Math.abs(newMaxStep *= mul);
        }
        return new GenericIntegerRange(startIncluded, endIncluded, newStart, newEnd, newMinStep, newMaxStep);
    }
    
    @Override
    public NumericRange<Integer> divide(Integer div, boolean alterStep) {
        int newStart = start / div;
        int newEnd = end / div;
        if (newStart > newEnd) {
            newStart += newEnd;
            newEnd = newStart - newEnd;
            newStart -= newEnd;
        }
        int newMinStep = minStep;
        int newMaxStep = maxStep;
        if (alterStep) {
            Math.abs(newMinStep /= div);
            Math.abs(newMaxStep /= div);
        }
        return new GenericIntegerRange(startIncluded, endIncluded, newStart, newEnd, newMinStep, newMaxStep);
    }

    @Override
    public NumericRange<Integer> setStart(Integer start, boolean included) {
        if (start > end) {
            throw new IllegalArgumentException();
        }
        return new GenericIntegerRange(included, endIncluded, start, end, minStep, maxStep);
    }

    @Override
    public NumericRange<Integer> setEnd(Integer end, boolean included) {
        if (start > end) {
            throw new IllegalArgumentException();
        }
        return new GenericIntegerRange(startIncluded, included, start, end, minStep, maxStep);
    }

    @Override
    public NumericRange<Integer> transfer(Integer dgr) {
        return new GenericIntegerRange(startIncluded, endIncluded, start + dgr, end + dgr, minStep, maxStep);
    }

    @Override
    public boolean empty() {
        return (startIncluded ? start : start + minStep) > (endIncluded ? end : end - minStep);
    }

    @Override
    public boolean contains(Integer item) {
        return (startIncluded ? start : start + minStep) <= item && (endIncluded ? end : end - minStep) >= item && (item - start) % minStep == 0;
    }

    @Override
    public RangeIterator<Integer> iterate() {
        return new GenericIntegerRangeIterator();
    }

    @Override
    public int estimateSize() {
        return ((endIncluded ? end : end - minStep) - (startIncluded ? start : start + minStep)) / minStep + 1;
    }

    @Override
    public Integer minSpeed() {
        return minStep;
    }

    @Override
    public Integer maxSpeed() {
        return maxStep;
    }
}
