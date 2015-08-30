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
package gr.utils.range.discrete;

import gr.hua.utils.range.Range;
import gr.hua.utils.range.RangeIterator;
import gr.hua.utils.range.numeric.GenericLongRange;
import gr.hua.utils.range.numeric.NumericRange;

/**
 *
 * @author Nikolaos Zormpas <nickzorb@gmail.com>
 */
public class GenericStringRange implements StringRange {

    private class GenericStringRangeIterator implements RangeIterator<String> {

        RangeIterator<Long> iter = range.iterate();

        @Override
        public boolean hasPrevious() {
            return iter.hasPrevious();
        }

        @Override
        public String previous() {
            return items[iter.previous().intValue()];
        }

        @Override
        public String current() {
            return items[iter.current().intValue()];
        }

        @Override
        public void current(String item) {
            if (item == null) {
                iter.current(null);
                return;
            }
            for (int i = 0; i < items.length; i++) {
                if (items[i].equals(item)) {
                    iter.current((long) i);
                    return;
                }
            }
            iter.current(-1L);
        }

        @Override
        public Long speed() {
            return iter.speed();
        }

        @Override
        public void speed(Long speed) {
            iter.speed(speed);
        }

        @Override
        public Long maxSpeed() {
            return iter.maxSpeed();
        }

        @Override
        public RangeIterator clone() {
            RangeIterator res = new GenericStringRangeIterator();
            res.current(current());
            return res;
        }

        @Override
        public boolean hasNext() {
            return iter.hasNext();
        }

        @Override
        public String next() {
            return items[iter.next().intValue()];
        }
    }

    protected String[] items;
    protected GenericLongRange range;

    public GenericStringRange(String[] items) {
        this.items = items;
        range = (GenericLongRange) NumericRange.basicRange(items.length);
    }

    @Override
    public String min() {
        return items[0];
    }

    @Override
    public String max() {
        return items[items.length - 1];
    }

    @Override
    public String step() {
        return null;
    }

    @Override
    public Long maxSpeed() {
        return range.maxSpeed();
    }

    @Override
    public long size() {
        return range.size();
    }

    @Override
    public boolean empty() {
        return range.empty();
    }

    @Override
    public boolean contains(String item) {
        for (int i = 0; i < items.length; i++) {
            if (items[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public RangeIterator<String> iterate() {
        return new GenericStringRangeIterator();
    }

    @Override
    public Range<String> clone() {
        return new GenericStringRange(items.clone());
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("{");
        for (String item : items) {
            res.append("\"").append(item).append("\",");
        }
        res.setLength(res.length() - 1);
        res.append("}");
        return res.toString();
    }
}
