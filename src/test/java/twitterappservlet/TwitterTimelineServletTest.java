package twitterappservlet;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Assert;
import org.junit.Test;

import twitterappservlet.TwitterTimelineServlet;

public class TwitterTimelineServletTest {

	@Test
	public void test() throws IOException, ServletException {
		MockHttpServletResponse response = new MockHttpServletResponse();
		new TwitterTimelineServlet().doGet(null, response);
		Assert.assertEquals("text/plain", response.getContentType());
		Assert.assertEquals("UTF-8", response.getCharacterEncoding());
	}
}
