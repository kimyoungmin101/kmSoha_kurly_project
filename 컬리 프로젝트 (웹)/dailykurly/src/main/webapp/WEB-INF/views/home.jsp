<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/owl.carousel.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/owl.theme.default.min.css">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/owl.carousel.min.js"></script>
<meta charset="UTF-8">
<title>Home</title>
<style>
.owl-dots{display:none;}
.owl-item img{
    height: 40px;
}
@media(max-width:768px){
    .pbPrevBtn, .pbNextBtn{
        display: none;
    }
}
</style>
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
		
		<img src="${pageContext.request.contextPath}/resources/images/home1.png"/>
		
	</header>

		<div class="row justify-content-center">
		
			<h4 style="text-align: center; margin-top:50px; margin-bottom:50px;"><strong>이 상품 어때요?</strong></h4>
		
			<div class="col"></div>
			<div class="col-8">
				<div class="owl-carousel owl-theme owl-loaded">
					<div class="owl-stage-outer">
				  		<div class="owl-stage">
				  			<c:forEach var="tmp" items="${list}">
				  				<div class="owl-item">
									<a href="${pageContext.request.contextPath}/info.do?product_id=${tmp.product_id}">
										<img src="${tmp.product_imgeurl}" style="width:200px; height:200px;">
									</a>
									<p>${tmp.product_name}</p>
									<p>${tmp.product_price}원</p>
				   				</div>
				  			</c:forEach>
				  		</div>
				 	</div>
				</div>
			</div>
			<div class="col"></div>
		</div>
	
<script type="text/javascript">
$(document).ready(function(){
    var owl = $('.owl-carousel');
    
    owl.owlCarousel({
        items:4,                 // 한번에 보여줄 아이템 수
        loop:true,               // 반복여부
        margin:35,               // 오른쪽 간격
        autoplay:true,           // 자동재생 여부
        autoplayTimeout:3600,    // 재생간격
        autoplayHoverPause:true  //마우스오버시 멈출지 여부
    });    
    
    $('.customNextBtn').click(function() {
        owl.trigger('next.owl.carousel');
    })
    
    $('.customPrevBtn').click(function() {
        owl.trigger('prev.owl.carousel', [300]);
    })
});
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</body>
</html>