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
    protected T minStep;
    protected T maxStep;

    public GenericNumericRange(boolean startIncluded, boolean endIncluded, T start, T end, T minStep, T maxStep) {
        if (start == null || end == null || minStep == null || maxStep == null) {
            throw new NullPointerException();
        }
        if (start.doubleValue() > end.doubleValue() || minStep.doubleValue() > maxStep.doubleValue() || minStep.doubleValue() <= 0 || minStep.doubleValue() > end.doubleValue() - start.doubleValue()) {
            throw new IllegalArgumentException();
        }
        this.startIncluded = startIncluded;
        this.endIncluded = endIncluded;
        this.start = start;
        this.end = end;
        this.minStep = minStep;
        this.maxStep = maxStep;
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
        res.append(" : ").append(minStep).append(" / ").append(maxStep);
        return res.toString();
    }
}
