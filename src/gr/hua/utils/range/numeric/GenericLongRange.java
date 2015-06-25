/*
 * Copyright 2015 Nikolaos Zormpas.
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
public class GenericLongRange extends GenericNumericRange<Long> {

    private class GenericLongRangeIterator extends GenericNumericIterator {

        @Override
        public Long previous() {
            if (hasPrevious()) {
                cur -= step * speed;
                return cur;
            }
            throw new NoSuchElementException();
        }

        @Override
        public Long next() {
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
            GenericLongRangeIterator res = new GenericLongRangeIterator();
            res.current(cur);
            res.speed(speed);
            return res;
        }
    }

    public GenericLongRange(boolean startIncluded, boolean endIncluded, Long start, Long end, Long step, Long maxSpeed) {
        super(startIncluded, endIncluded, start, end, step, maxSpeed);
    }

    @Override
    public GenericNumericRange<Long> clone() {
        return new GenericLongRange(startIncluded, endIncluded, start, end, step, maxSpeed);
    }

    @Override
    public NumericRange<Long> times(Double mul, boolean alterStep) {
        long newStart = (long) (start * mul);
        long newEnd = (long) (end * mul);
        if (newStart > newEnd) {
            newStart += newEnd;
            newEnd = newStart - newEnd;
            newStart -= newEnd;
            startIncluded ^= endIncluded;
            endIncluded ^= startIncluded;
            startIncluded ^= endIncluded;
        }
        long newStep = step;
        if (alterStep) {
            newStep = (long) Math.abs(step * mul);
        }
        return new GenericLongRange(startIncluded, endIncluded, newStart, newEnd, newStep, maxSpeed);
    }

    @Override
    public NumericRange<Long> setStart(Long start, boolean included) {
        if (start > end) {
            throw new IllegalArgumentException();
        }
        return new GenericLongRange(included, endIncluded, start, end, step, maxSpeed);
    }

    @Override
    public NumericRange<Long> setEnd(Long end, boolean included) {
        if (start > end) {
            throw new IllegalArgumentException();
        }
        return new GenericLongRange(startIncluded, included, start, end, step, maxSpeed);
    }

    @Override
    public boolean empty() {
        return (startIncluded ? start : start + step) > (endIncluded ? end : end - step);
    }

    @Override
    public boolean contains(Long item) {
        return (startIncluded ? start : start + step) <= item && (endIncluded ? end : end - step) >= item && (item - start) % step == 0;
    }

    @Override
    public RangeIterator<Long> iterate() {
        return new GenericLongRangeIterator();
    }

    @Override
    public long size() {
        return (((endIncluded ? end : end - step) - (startIncluded ? start : start + step)) / step) + 1;
    }
}
