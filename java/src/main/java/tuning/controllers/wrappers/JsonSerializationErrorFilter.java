package tuning.controllers.wrappers;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;

public class JsonSerializationErrorFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (!(response instanceof HttpServletResponse)) {
            chain.doFilter(request, response);
            return;
        }

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        BufferedResponseWrapper wrappedResponse = new BufferedResponseWrapper(httpServletResponse);

        try {
            chain.doFilter(request, wrappedResponse);
            byte[] responseData = wrappedResponse.getCapturedResponseData();

            if (!httpServletResponse.isCommitted()) {
                response.getOutputStream().write(responseData);
            }
        } catch (Exception e) {
            if (!httpServletResponse.isCommitted()) {
                httpServletResponse.reset();
                httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                httpServletResponse.getWriter().write("JSON serialization error: " + e.getMessage());
            } else {
                throw e;
            }
        }
    }
}