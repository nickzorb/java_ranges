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
public class GenericRealRange extends GenericNumericRange<Double> {

    private class GenericRealRangeIterator implements RangeIterator<Double> {
        
        private Double cur = null;
        private Double step;

        public GenericRealRangeIterator() {
            step = GenericRealRange.this.minStep;
        }

        @Override
        public Double previous() {
            if (hasPrevious()) {
                cur -=  step;
                return cur;
            }
            throw new NoSuchElementException();
        }

        @Override
        public Double current() {
            if (cur != null) {
                return cur;
            }
            throw new NoSuchElementException();
        }

        @Override
        public Double next() {
            if (hasNext()) {
                cur = cur == null ? GenericRealRange.this.startIncluded ? GenericRealRange.this.start :  GenericRealRange.this.start +  step : cur +  step;
                return cur;
            }
            throw new NoSuchElementException();
        }

        @Override
        public void setCurrent(Double item) {
            if (item == null || GenericRealRange.this.contains(item)) {
                cur = item;
            }
            throw new NoSuchElementException();
        }

        @Override
        public boolean hasPrevious() {
            return cur != null && cur - step >= (GenericRealRange.this.startIncluded ? GenericRealRange.this.start : GenericRealRange.this.start + step);
        }

        @Override
        public boolean hasNext() {
            return (cur == null && !GenericRealRange.this.empty()) || cur + step <= (GenericRealRange.this.endIncluded ? GenericRealRange.this.end : GenericRealRange.this.end - step);
        }

        @Override
        public void setSpeed(Double step) {
            this.step = step;
        }

        @Override
        public Double getSpeed() {
            return step;
        }

        @Override
        public Double minSpeed() {
            return GenericRealRange.this.minStep;
        }

        @Override
        public Double maxSpeed() {
            return GenericRealRange.this.maxStep;
        }

        @Override
        public RangeIterator clone() {
            GenericRealRangeIterator res = new GenericRealRangeIterator();
            res.setCurrent(cur);
            res.setSpeed(step);
            return res;
        }
    }

    public GenericRealRange(boolean startIncluded, boolean endIncluded, Double start, Double end, Double minStep, Double maxStep) {
        super(startIncluded, endIncluded, start, end, minStep, maxStep);
        while (this.maxStep > end - start) {
            this.maxStep--;
        }
    }

    @Override
    public GenericNumericRange<Double> clone() {
        return new GenericRealRange(startIncluded, endIncluded, start, end, minStep, maxStep);
    }
    
    @Override
    public NumericRange<Double> times(Double mul, boolean alterStep) {
        Double newStart = start * mul;
        Double newEnd = end * mul;
        if (newStart > newEnd) {
            newStart += newEnd;
            newEnd = newStart - newEnd;
            newStart -= newEnd;
        }
        Double newMinStep = minStep;
        Double newMaxStep = maxStep;
        if (alterStep) {
            Math.abs(newMinStep *= mul);
            Math.abs(newMaxStep *= mul);
        }
        return new GenericRealRange(startIncluded, endIncluded, newStart, newEnd, newMinStep, newMaxStep);
    }
    
    @Override
    public NumericRange<Double> divide(Double div, boolean alterStep) {
        Double newStart = start / div;
        Double newEnd = end / div;
        if (newStart > newEnd) {
            newStart += newEnd;
            newEnd = newStart - newEnd;
            newStart -= newEnd;
        }
        Double newMinStep = minStep;
        Double newMaxStep = maxStep;
        if (alterStep) {
            Math.abs(newMinStep /= div);
            Math.abs(newMaxStep /= div);
        }
        return new GenericRealRange(startIncluded, endIncluded, newStart, newEnd, newMinStep, newMaxStep);
    }

    @Override
    public NumericRange<Double> setStart(Double start, boolean included) {
        if (start > end) {
            throw new IllegalArgumentException();
        }
        return new GenericRealRange(included, endIncluded, start, end, minStep, maxStep);
    }

    @Override
    public NumericRange<Double> setEnd(Double end, boolean included) {
        if (start > end) {
            throw new IllegalArgumentException();
        }
        return new GenericRealRange(startIncluded, included, start, end, minStep, maxStep);
    }

    @Override
    public NumericRange<Double> transfer(Double dgr) {
        return new GenericRealRange(startIncluded, endIncluded, start + dgr, end + dgr, minStep, maxStep);
    }

    @Override
    public boolean empty() {
        return (startIncluded ? start : start + minStep) > (endIncluded ? end : end - minStep);
    }

    @Override
    public boolean contains(Double item) {
        return (startIncluded ? start : start + minStep) <= item && (endIncluded ? end : end - minStep) >= item && (item - start) % minStep == 0;
    }

    @Override
    public RangeIterator<Double> iterate() {
        return new GenericRealRangeIterator();
    }

    @Override
    public int estimateSize() {
        return (int) (((endIncluded ? end : end - minStep) - (startIncluded ? start : start + minStep)) / minStep + 1);
    }

    @Override
    public Double minSpeed() {
        return minStep;
    }

    @Override
    public Double maxSpeed() {
        return maxStep;
    }
}
