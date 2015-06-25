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
import java.util.Objects;

/**
 *
 * @author Nikolaos Zormpas
 * @param <T>
 */
public abstract class GenericNumericRange<T extends Number> implements NumericRange<T> {

    protected boolean startIncluded;
    protected boolean endIncluded;
    protected T start;
    protected T end;
    protected T step;
    protected Long maxSpeed;

    protected abstract class GenericNumericIterator implements RangeIterator<T> {

        protected T cur = null;
        protected Long speed;

        public GenericNumericIterator() {
            speed = 1L;
        }

        @Override
        public T current() {
            if (cur != null) {
                return cur;
            }
            throw new NoSuchElementException();
        }

        @Override
        public void current(T item) {
            if (item == null || contains(item)) {
                cur = item;
            }
            throw new NoSuchElementException();
        }

        @Override
        public Long speed() {
            return speed;
        }

        @Override
        public void speed(Long speed) {
            if (speed <= maxSpeed) {
                this.speed = speed;
            } else {
                throw new IllegalArgumentException();
            }
        }

        @Override
        public Long maxSpeed() {
            return maxSpeed;
        }

        @Override
        public abstract RangeIterator clone();
    }

    public GenericNumericRange(boolean startIncluded, boolean endIncluded, T start, T end, T step, Long maxSpeed) {
        if (start == null || end == null || step == null || maxSpeed == null) {
            throw new NullPointerException();
        }
        if (start.doubleValue() > end.doubleValue() || step.doubleValue() <= 0 || step.doubleValue() > end.doubleValue() - start.doubleValue()) {
            throw new IllegalArgumentException();
        }
        this.startIncluded = startIncluded;
        this.endIncluded = endIncluded;
        this.start = start;
        this.end = end;
        this.step = step;
        this.maxSpeed = maxSpeed;
    }

    @Override
    public abstract GenericNumericRange<T> clone();

    @Override
    public Long maxSpeed() {
        return maxSpeed;
    }

    @Override
    public T min() {
        return start;
    }

    @Override
    public T max() {
        return end;
    }

    @Override
    public T step() {
        return step;
    }

    @Override
    public boolean equals(Object other) {
        if (other.getClass().equals(this.getClass())) {
            GenericNumericRange o = (GenericNumericRange) other;
            if (o.start.getClass().equals(start.getClass())) {
                return o.start.equals(start) && o.end.equals(end) && o.step.equals(step) && o.maxSpeed.equals(maxSpeed) && o.startIncluded == startIncluded && o.endIncluded == endIncluded;
            }
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (this.startIncluded ? 1 : 0);
        hash = 29 * hash + (this.endIncluded ? 1 : 0);
        hash = 29 * hash + Objects.hashCode(this.start);
        hash = 29 * hash + Objects.hashCode(this.end);
        hash = 29 * hash + Objects.hashCode(this.step);
        hash = 29 * hash + Objects.hashCode(this.maxSpeed);
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        if (startIncluded) {
            res.append('[');
        } else {
            res.append('(');
        }
        res.append(start.toString()).append("/").append(end);
        if (endIncluded) {
            res.append(']');
        } else {
            res.append(')');
        }
        res.append("/").append(step).append("/").append(maxSpeed);
        return res.toString();
    }
}
