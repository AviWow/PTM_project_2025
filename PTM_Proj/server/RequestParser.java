package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RequestParser {

    public static RequestInfo parseRequest(BufferedReader reader) throws IOException {
        String requestLine = reader.readLine();
        if (requestLine == null || requestLine.isEmpty()) {
            throw new IOException("Empty request line");
        }

        String[] requestLineParts = requestLine.split(" ");
        String httpCommand = requestLineParts[0];
        String fullUri = requestLineParts[1];

        String uri = fullUri;
        Map<String, String> parameters = new HashMap<>();
        if (fullUri.contains("?")) {
            String[] uriParts = fullUri.split("\\?", 2);
            uri = uriParts[0];
            String queryString = uriParts[1];
            for (String param : queryString.split("&")) {
                String[] keyValue = param.split("=", 2);
                parameters.put(keyValue[0].trim(), keyValue.length > 1 ? keyValue[1].trim() : "");
            }
        }
        String[] uriSegments = Arrays.stream(uri.split("/"))
                .filter(s -> !s.isEmpty())
                .toArray(String[]::new);

        String line;
        while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
        }

        while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
            if (line.contains("=")) {
                String[] keyValue = line.split("=", 2);
                parameters.put(keyValue[0].trim(), keyValue.length > 1 ? keyValue[1].trim() : "");
            }
        }

        StringBuilder contentBuilder = new StringBuilder();
        while (reader.ready() && (line = reader.readLine()) != null && !line.trim().isEmpty()) {
            contentBuilder.append(line).append("\n");
        }

        byte[] content = contentBuilder.toString().getBytes(StandardCharsets.UTF_8);

        return new RequestInfo(httpCommand, fullUri, uriSegments, parameters, content);

    }
	
	// RequestInfo given internal class
    public static class RequestInfo {
        private final String httpCommand;
        private final String uri;
        private final String[] uriSegments;
        private final Map<String, String> parameters;
        private final byte[] content;

        public RequestInfo(String httpCommand, String uri, String[] uriSegments, Map<String, String> parameters, byte[] content) {
            this.httpCommand = httpCommand;
            this.uri = uri;
            this.uriSegments = uriSegments;
            this.parameters = parameters;
            this.content = content;
        }

        public String getHttpCommand() {
            return httpCommand;
        }

        public String getUri() {
            return uri;
        }

        public String[] getUriSegments() {
            return uriSegments;
        }

        public Map<String, String> getParameters() {
            return parameters;
        }

        public byte[] getContent() {
            return content;
        }
    }
}
