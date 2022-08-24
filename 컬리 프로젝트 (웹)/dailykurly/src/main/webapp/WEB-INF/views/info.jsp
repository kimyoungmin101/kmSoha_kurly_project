<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Info</title>
</head>
<body>

	<header>
		<div class="container">
			<div class="row justify-content-md-center">
				<div class="col"></div>
				<div class="col-8">
					<div class="row">
						<c:choose>
	                    	<c:when test="${ empty sessionScope.user_id}">
	                    		<div class="row" style="float:right;">
	                    			<div class="col">
	                    				<p style="float:right; margin-left:5px;">회원가입</p>
	                    				<a href="${pageContext.request.contextPath }/user/loginform.do"><p style="float:right; margin-left:5px;">로그인</p></a>
	                    				<p style="float:right; margin-left:5px;">고객센터</p>
	                    			</div>
	                    		</div>
	                    	</c:when>
	                    	<c:otherwise>
	                    		<div class="row" style="float:right;">
	                    			<div class="col">
	                    				<p style="float:right; margin-left:5px;">고객센터</p>
										<a href="${pageContext.request.contextPath }/user/logout.do"><p style="float:right; margin-left:5px;">로그아웃</p></a>
		                    			<p style="float:right; margin-left:5px;">${user_id}님</p>
	                    			</div>
	                    		</div>
	                    	</c:otherwise>
                    	</c:choose>
					</div>
					<div class="row">
						<table>
							<tr>
								<td>
									<img src="${pageContext.request.contextPath}/resources/images/banner2-1.png"/>
								</td>
								<td>
									<img src="${pageContext.request.contextPath}/resources/images/icon1.png" style="width:30px; height:30px; margin-right:10px;"/>
								</td>
								<td>
									<a href="${pageContext.request.contextPath}/calendar/calendar.do">
										<img src="${pageContext.request.contextPath}/resources/images/icon2.png" style="width:30px; height:30px; margin-right:10px;"/>
									</a>
								</td>
								<td>
									<img src="${pageContext.request.contextPath}/resources/images/icon3.png" style="width:30px; height:30px; margin-right:10px;"/>
								</td>
							</tr>
						</table>
					</div>
					<div class="row">
						<img src="${pageContext.request.contextPath}/resources/images/banner3.png"/>
					</div>
				</div>
				<div class="col"></div>
			</div>
		</div>
		
	</header>
	<div class="container">
		<div class="row justify-content-md-center">
			<div class="col"></div>
			<div class="col-8">
				<table>
					<tr>
						<td><img src="${dto.product_imgeurl}"></td>
						<td>
							<h3><strong>${dto.product_name}</strong></h3>
							<h5>${dto.product_price}<strong>원</strong></h5>
							<img src="${pageContext.request.contextPath}/resources/images/info.png"/>
							
							<div class="row">
								<table>
									<tr>
										<td>
											<img src="${pageContext.request.contextPath}/resources/images/damgi2.png" style="width:120px; height:60px;  margin-left:10px; margin-right:10px;"/>
										</td>
										<td>
											<form name="form1" method="post" action="${pageContext.request.contextPath}/calendar/insert.do">
						        				<input type="hidden" name="product_id" value="${dto.product_id}"/>
						        				<select name="product_count">
						        					<c:forEach begin="1" end="10" var="i">
						        						<option value="${i}">${i}</option>
						        					</c:forEach>
						        				</select>&nbsp;개
						        				<button type="submit"><img src="${pageContext.request.contextPath}/resources/images/damgi1.png" style="width:400px;"/></button>
		        							</form>
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
				</table>
			</div>
			<div class="col"></div>
		</div>
	</div>
	
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>