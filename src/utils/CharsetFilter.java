package utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class CharsetFilter implements Filter {
	private String encoding;

    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("requestEncoding");
        if (encoding == null) encoding = "UTF-8";
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next)
            throws IOException, ServletException {
        if (null == request.getCharacterEncoding()) {
            request.setCharacterEncoding(encoding);
        }
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        /*HttpServletRequest req = (HttpServletRequest) request;
        if(req.getRequestURI().startsWith(request.getServletContext().getContextPath()+"/api")) {
        	//HttpServletResponse res = (HttpServletResponse) response;
        	//res.setHeader("Access-Control-Allow-Origin", "http://www.canal7video.com, http://www.canal7video.local");
        	//res.setHeader("Access-Control-Allow-Methods", "GET, POST");
        	//String origin = req.getHeader("Origin");
        	//System.out.println(origin);
        	if(Functions.inArray(origin, new Object[] {"http://www.canal7video.com","http://www.canal7video.local"}) == -1)
        		return;
        }
        else*/
        	((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        next.doFilter(request, response);
    }

    public void destroy() {
    }
}