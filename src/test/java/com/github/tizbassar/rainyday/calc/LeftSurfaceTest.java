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

import java.util.Arrays;
import java.util.Collections;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Test cases for {@link LeftSurface}.
 *
 * @since 0.1
 * @checkstyle JavadocMethodCheck (100 lines)
 * @checkstyle MagicNumberCheck (100 lines)
 */
public final class LeftSurfaceTest {

    @Test
    public void shouldCalculateCharacterOfEmptySurface() {
        MatcherAssert.assertThat(
            new LeftSurface(Collections.emptyList()).character(),
            Matchers.empty()
        );
    }

    @Test
    public void shouldRepresentLeftSurface() {
        MatcherAssert.assertThat(
            new LeftSurface(
                Arrays.asList(3, 2, 4, 1)
            ).character(),
            Matchers.is(
                Arrays.asList(3, 3, 4, 4)
            )
        );
    }

    @Test
    public void shouldCalculateCharacterOfTheSortedSurface() {
        MatcherAssert.assertThat(
            new LeftSurface(
                Arrays.asList(1, 2, 3, 4)
            ).character(),
            Matchers.is(
                Arrays.asList(1, 2, 3, 4)
            )
        );
    }

    @Test
    public void shouldCalculateCharacterOfTheReverseSortedSurface() {
        MatcherAssert.assertThat(
            new LeftSurface(
                Arrays.asList(4, 3, 2, 1)
            ).character(),
            Matchers.is(
                Arrays.asList(4, 4, 4, 4)
            )
        );
    }
}
