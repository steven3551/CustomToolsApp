package com.wuwg.component.net;

import com.wuwg.component.log.MyLog;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * Created by wuwengao on 2017/6/15.
 */
public class JHttpLoggingInterceptor implements Interceptor {

    private static final Charset UTF8 = Charset.forName("UTF-8");

    public enum Level {
        /**
         * No logs.
         */
        NONE,
        /**
         * Logs request and response lines.
         * <p>
         * <p>Example:
         * <pre>{@code
         * --> POST /greeting HTTP/1.1 (3-byte body)
         *
         * <-- HTTP/1.1 200 OK (22ms, 6-byte body)
         * }</pre>
         */
        BASIC,
        /**
         * Logs request and response lines and their respective headers.
         * <p>
         * <p>Example:
         * <pre>{@code
         * --> POST /greeting HTTP/1.1
         * Host: example.com
         * Content-Type: plain/text
         * Content-Length: 3
         * --> END POST
         *
         * <-- HTTP/1.1 200 OK (22ms)
         * Content-Type: plain/text
         * Content-Length: 6
         * <-- END HTTP
         * }</pre>
         */
        HEADERS,
        /**
         * Logs request and response lines and their respective headers and bodies (if present).
         * <p>
         * <p>Example:
         * <pre>{@code
         * --> POST /greeting HTTP/1.1
         * Host: example.com
         * Content-Type: plain/text
         * Content-Length: 3
         *
         * Hi?
         * --> END GET
         *
         * <-- HTTP/1.1 200 OK (22ms)
         * Content-Type: plain/text
         * Content-Length: 6
         *
         * Hello!
         * <-- END HTTP
         * }</pre>
         */
        BODY
    }

    public interface Logger {
        void log(String... message);

        /**
         * A {@link JHttpLoggingInterceptor.Logger} defaults output appropriate for the current platform.
         */
        JHttpLoggingInterceptor.Logger DEFAULT = new JHttpLoggingInterceptor.Logger() {
            @Override
            public void log(String... message) {
                // Platform.get().log(message);
                /**
                 * 使用自定义Log 输出
                 */
                MyLog.i(message);
            }
        };
    }

    public JHttpLoggingInterceptor() {
        this(JHttpLoggingInterceptor.Logger.DEFAULT);
    }

    public JHttpLoggingInterceptor(JHttpLoggingInterceptor.Logger logger) {
        this.logger = logger;
    }

    private final JHttpLoggingInterceptor.Logger logger;

    private volatile JHttpLoggingInterceptor.Level level = JHttpLoggingInterceptor.Level.NONE;

    /**
     * Change the level at which this interceptor logs.
     */
    public JHttpLoggingInterceptor setLevel(JHttpLoggingInterceptor.Level level) {
        if (level == null) throw new NullPointerException("level == null. Use Level.NONE instead.");
        this.level = level;
        return this;
    }

    public JHttpLoggingInterceptor.Level getLevel() {
        return level;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long startNs = System.nanoTime();
        Response response = chain.proceed(request);
        long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);
        /**
         * response body
         */
        ResponseBody responseBody = response.body();
        long contentLength = responseBody.contentLength();
        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE);
        Buffer buffer = source.buffer();

        Charset charset = UTF8;
        MediaType contentType = responseBody.contentType();
        if (contentType != null) {
            charset = contentType.charset(UTF8);
        }
        /**
         * request body
         */
        Buffer bufferRequest = new Buffer();
        request.body().writeTo(bufferRequest);
        String requestBody = bufferRequest.clone().readString(charset);

        logger.log("<-- " + response.code() + ' ' + response.message() + " (" + tookMs + "ms" + (buffer != null ? "," + buffer.size() + "byte" : "") + ')'
                , "" + response.request().url()
                , requestBody
                , buffer.clone().readString(charset));
        return response;
    }

    private boolean bodyEncoded(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
    }

    private static String protocol(Protocol protocol) {
        return protocol == Protocol.HTTP_1_0 ? "HTTP/1.0" : "HTTP/1.1";
    }

}
