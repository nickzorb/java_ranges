/*
 * Copyright 2015 Nikolaos Zormpas <nickzorb@gmail.com>.
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
 * @author Nikolaos Zormpas <nickzorb@gmail.com>
 */
public class GenericRealRange extends GenericNumericRange<Double> {

    private class GenericRealRangeIterator extends GenericNumericIterator {

        @Override
        public Double previous() {
            if (hasPrevious()) {
                cur -= step * speed;
                return cur;
            }
            throw new NoSuchElementException();
        }

        @Override
        public Double next() {
            if (hasNext()) {
                cur = cur == null ? startIncluded ? start : start + step * speed : cur + step * speed;
                return cur;
            }
            throw new NoSuchElementException();
        }

        @Override
        public boolean hasPrevious() {
            return cur != null && cur - step * speed >= (startIncluded ? start : start + step * speed);
        }

        @Override
        public boolean hasNext() {
            return (cur == null && !empty()) || cur + step * speed <= (endIncluded ? end : end - step * speed);
        }

        @Override
        public RangeIterator clone() {
            GenericRealRangeIterator res = new GenericRealRangeIterator();
            res.current(cur);
            res.speed(speed);
            return res;
        }
    }

    public GenericRealRange(boolean startIncluded, boolean endIncluded, Double start, Double end, Double step, Long maxSpeed) {
        super(startIncluded, endIncluded, start, end, step, maxSpeed);
    }

    @Override
    public GenericNumericRange<Double> clone() {
        return new GenericRealRange(startIncluded, endIncluded, start, end, step, maxSpeed);
    }

    @Override
    public NumericRange<Double> times(Double mul, boolean alterStep) {
        double newStart = start * mul;
        double newEnd = end * mul;
        if (newStart > newEnd) {
            newStart += newEnd;
            newEnd = newStart - newEnd;
            newStart -= newEnd;
            startIncluded ^= endIncluded;
            endIncluded ^= startIncluded;
            startIncluded ^= endIncluded;
        }
        double newStep = step;
        if (alterStep) {
            newStep = Math.abs(step * mul);
        }
        return new GenericRealRange(startIncluded, endIncluded, newStart, newEnd, newStep, maxSpeed);
    }

    @Override
    public NumericRange<Double> setStart(Double start, boolean included) {
        if (start > end) {
            throw new IllegalArgumentException();
        }
        return new GenericRealRange(included, endIncluded, start, end, step, maxSpeed);
    }

    @Override
    public NumericRange<Double> setEnd(Double end, boolean included) {
        if (start > end) {
            throw new IllegalArgumentException();
        }
        return new GenericRealRange(startIncluded, included, start, end, step, maxSpeed);
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
    public long size() {
        return (long) ((((endIncluded ? end : end - step) - (startIncluded ? start : start + step)) / step) + 1);
    }
}
