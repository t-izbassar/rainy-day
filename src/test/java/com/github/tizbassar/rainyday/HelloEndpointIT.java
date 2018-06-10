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
package com.github.tizbassar.rainyday;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.cactoos.text.TextOf;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Integration Test for {@link HelloEndpoint}.
 *
 * @since 0.1
 * @checkstyle JavadocMethodCheck (500 lines)
 */
public final class HelloEndpointIT {

    @Test
    public void shouldSayHi() throws Exception {
        final CloseableHttpClient client = HttpClients.createDefault();
        final CloseableHttpResponse response = client.execute(
            new HttpGet("http://localhost:8080/rainy-day/hello")
        );
        try {
            final HttpEntity entity = response.getEntity();
            MatcherAssert.assertThat(
                new TextOf(entity.getContent()).asString(),
                Matchers.is("HELLO")
            );
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }
    }
}
