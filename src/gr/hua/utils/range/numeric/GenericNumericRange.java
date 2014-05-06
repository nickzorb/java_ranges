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

/**
 *
 * @author NickZorb
 * @param <T>
 */
public abstract class GenericNumericRange<T extends Number> implements NumericRange<T> {

    protected boolean startIncluded;
    protected boolean endIncluded;
    protected T start;
    protected T end;
    protected T step;
    protected int modCount = 0;

    public GenericNumericRange(boolean startIncluded, boolean endIncluded, T start, T end, T step) {
        if (start == null || end == null || step == null) {
            throw new NullPointerException();
        }
        this.startIncluded = startIncluded;
        this.endIncluded = endIncluded;
        this.start = start;
        this.end = end;
        this.step = step;
    }
    
    @Override
    public abstract GenericNumericRange<T> clone();
    
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        if (startIncluded) {
            res.append('[');
        } else {
            res.append('(');
        }
        res.append(start.toString()).append(" - ").append(end);
        if (endIncluded) {
            res.append(']');
        } else {
            res.append(')');
        }
        res.append(" : ").append(step);
        return res.toString();
    }
}
