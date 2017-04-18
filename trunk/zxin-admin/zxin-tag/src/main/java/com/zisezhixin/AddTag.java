package com.zisezhixin;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

public class AddTag extends TagSupport {
	private int x;
	private int y;

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private PageContext pageContext;

	@Override
	public void setPageContext(PageContext pageContext) {
		this.pageContext = pageContext;
	}

	@Override
	public int doStartTag() throws JspException {
		int z = x + y;
		try {
			pageContext.getResponse().getWriter()
					.write("tld:<input type='text' value='" + x + "'></input>+<input type='text' value='" + y + "'></input>=<input type='text' value='" + z + "'></input>");
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return super.doStartTag();
	}
}
