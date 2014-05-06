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
package gr.hua.utils.range;

/**
 *
 * @author NickZorb
 */
public class InvalidatedRangeException extends RuntimeException {

    /**
     * Constructs an <code>InvalidatedRangeException</code> with no detail
     * message.
     */
    public InvalidatedRangeException() {
        super();
    }

    /**
     * Constructs a new <code>InvalidatedRangeException</code> class with an
     * argument indicating the invalidated range.
     *
     * @param range the invalidated range.
     */
    public InvalidatedRangeException(Range range) {
        super("Concurrent use of range: " + range.toString());
    }

    /**
     * Constructs an <code>InvalidatedRangeException</code> class with the
     * specified detail message.
     *
     * @param s the detail message.
     */
    public InvalidatedRangeException(String s) {
        super(s);
    }
//
}
