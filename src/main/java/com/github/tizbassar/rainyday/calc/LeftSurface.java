/*
 * MIT License
 *
 * Copyright (c) 2018 Tolegen Izbassar
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.github.tizbassar.rainyday.calc;

import java.util.ArrayList;
import java.util.List;

/**
 * Surface from left-end perspective.
 *
 * @since 0.1
 */
final class LeftSurface implements Surface {

    /**
     * Hills.
     */
    private final List<Integer> hills;

    /**
     * Ctor.
     * @param hills Given hills
     */
    LeftSurface(final List<Integer> hills) {
        this.hills = hills;
    }

    @Override
    public List<Integer> character() {
        int current = 0;
        final List<Integer> result = new ArrayList<>(this.hills.size());
        for (final int hill : this.hills) {
            if (hill > current) {
                current = hill;
            }
            result.add(current);
        }
        return result;
    }
}
