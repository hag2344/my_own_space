<%@page import="org.slf4j.Logger"%>
<%@page import="org.slf4j.LoggerFactory"%>
<%@page import="java.net.URL"%>
<%@page import="java.util.Properties"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
Logger LOGGER = LoggerFactory.getLogger("com.nhs.firstProject");
ClassLoader currentThreadClassLoader = Thread.currentThread().getContextClassLoader();

String astrU = request.getRequestURL().toString();
%>
<jsp:forward page="/home.do"/>