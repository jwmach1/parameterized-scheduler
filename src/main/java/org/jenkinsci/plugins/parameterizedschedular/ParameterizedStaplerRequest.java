package org.jenkinsci.plugins.parameterizedschedular;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.kohsuke.stapler.Ancestor;
import org.kohsuke.stapler.BindInterceptor;
import org.kohsuke.stapler.Stapler;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;
import org.kohsuke.stapler.WebApp;
import org.kohsuke.stapler.bind.BoundObjectTable;
import org.kohsuke.stapler.lang.Klass;


public class ParameterizedStaplerRequest implements StaplerRequest{

	private String value;

	public ParameterizedStaplerRequest(String value) {
		this.value = value;
	}
	
	@Override
	public String getAuthType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cookie[] getCookies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getDateHeader(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getHeader(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enumeration getHeaders(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enumeration getHeaderNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getIntHeader(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getMethod() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPathInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPathTranslated() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getContextPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRemoteUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUserInRole(String role) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Principal getUserPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRequestedSessionId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRequestURI() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StringBuffer getRequestURL() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServletPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HttpSession getSession(boolean create) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HttpSession getSession() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRequestedSessionIdValid() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRequestedSessionIdFromCookie() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRequestedSessionIdFromURL() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRequestedSessionIdFromUrl() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object getAttribute(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enumeration getAttributeNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCharacterEncoding() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCharacterEncoding(String env) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getContentLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getParameter(String name) {
		return value;
	}

	@Override
	public Enumeration getParameterNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getParameterValues(String name) {
		return new String[] { value};
	}

	@Override
	public Map getParameterMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProtocol() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getScheme() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServerName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getServerPort() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BufferedReader getReader() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRemoteAddr() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRemoteHost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAttribute(String name, Object o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeAttribute(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Locale getLocale() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enumeration getLocales() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSecure() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public RequestDispatcher getRequestDispatcher(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRealPath(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getRemotePort() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getLocalName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLocalAddr() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLocalPort() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Stapler getStapler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebApp getWebApp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRestOfPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getOriginalRestOfPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServletContext getServletContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRequestURIWithQueryString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StringBuffer getRequestURLWithQueryString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RequestDispatcher getView(Object it, String viewName) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RequestDispatcher getView(Class clazz, String viewName) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RequestDispatcher getView(Klass<?> clazz, String viewName) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRootPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getReferer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ancestor> getAncestors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ancestor findAncestor(Class type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T findAncestorObject(Class<T> type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ancestor findAncestor(Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasParameter(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getOriginalRequestURI() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkIfModified(long timestampOfResource, StaplerResponse rsp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkIfModified(Date timestampOfResource, StaplerResponse rsp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkIfModified(Calendar timestampOfResource, StaplerResponse rsp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkIfModified(long timestampOfResource, StaplerResponse rsp, long expiration) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void bindParameters(Object bean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bindParameters(Object bean, String prefix) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> List<T> bindParametersToList(Class<T> type, String prefix) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T bindParameters(Class<T> type, String prefix) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T bindParameters(Class<T> type, String prefix, int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T bindJSON(Class<T> type, JSONObject src) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T bindJSON(Type genericType, Class<T> erasure, Object json) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void bindJSON(Object bean, JSONObject src) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> List<T> bindJSONToList(Class<T> type, Object src) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BindInterceptor getBindInterceptor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BindInterceptor setBindListener(BindInterceptor bindListener) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject getSubmittedForm() throws ServletException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileItem getFileItem(String name) throws ServletException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isJavaScriptProxyCall() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BoundObjectTable getBoundObjectTable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createJavaScriptProxy(Object toBeExported) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
