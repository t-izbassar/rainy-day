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
package com.github.tizbassar.rainyday.it;

import com.jcabi.http.Request;
import com.jcabi.http.request.JdkRequest;
import com.jcabi.http.response.RestResponse;
import java.net.HttpURLConnection;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Integration tests for
 * {@link com.github.tizbassar.rainyday.rest.HillsEndpoint}.
 *
 * @since 0.1
 * @checkstyle JavadocMethodCheck (150 lines)
 * @checkstyle JavadocVariableCheck (150 lines)
 * @checkstyle MagicNumberCheck (150 lines)
 */
public final class HillsEndpointIT {

    private final JsonBuilderFactory factory = Json.createBuilderFactory(null);

    private final String port = System.getProperty("docker.local.port");

    @Test
    public void shouldReturnBadMethodIfMethodIsNotPost() throws Exception {
        this.requestToTestServer().fetch().as(RestResponse.class)
            .assertStatus(HttpURLConnection.HTTP_BAD_METHOD);
    }

    @Test
    public void shouldReturnBadRequestIfInvalidJsonPassed() throws Exception {
        this.requestToTestServer().method(Request.POST)
            .body().set("{\"some\": \"INVALID\"}").back()
            .fetch().as(RestResponse.class)
            .assertStatus(HttpURLConnection.HTTP_BAD_REQUEST);
    }

    @Test
    public void shouldReturnBadRequestForHillsWithNegativeValues()
        throws Exception {
        this.givenRequestedHills(1, 2, -5, 6)
            .assertStatus(HttpURLConnection.HTTP_BAD_REQUEST);
    }

    @Test
    public void shouldCalculateZeroForEmptyHills() throws Exception {
        this.givenRequestedHills()
            .assertBody(
                Matchers.is("0")
            );
    }

    @Test
    public void shouldCalculateWaterVolumeForFirstExample() throws Exception {
        this.givenRequestedHills(3, 2, 4, 1, 2)
            .assertBody(
                Matchers.is("2")
            );
    }

    @Test
    public void shouldCalculateWaterVolumeForSecondExample() throws Exception {
        this.givenRequestedHills(4, 1, 1, 0, 2, 3)
            .assertBody(
                Matchers.is("8")
            );
    }

    private RestResponse givenRequestedHills(final int... hills)
        throws Exception {
        return this.requestToTestServer()
            .method(Request.POST)
            .body().set(
                this.factory.createObjectBuilder().add(
                    "hills", this.array(hills)
                ).build()
            ).back().fetch().as(RestResponse.class);
    }

    private Request requestToTestServer() {
        final String address = String.format(
            "http://localhost:%s/rainy-day/hills", this.port
        );
        return new JdkRequest(address)
            .header("Content-Type", "application/json");
    }

    private JsonArray array(final int... hills) {
        final JsonArrayBuilder builder = this.factory.createArrayBuilder();
        for (final int hill : hills) {
            builder.add(hill);
        }
        return builder.build();
    }
}
