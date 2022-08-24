<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/owl.carousel.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/owl.theme.default.min.css">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/owl.carousel.min.js"></script>
<!-- fullcalendar CDN -->
<link href='${pageContext.request.contextPath}/resources/css/main.css' rel='stylesheet' />
<script src='${pageContext.request.contextPath}/resources/js/main.js'></script>
<!-- fullcalendar 언어 CDN -->
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/locales-all.min.js'></script>

<meta charset="UTF-8">
<title>Calendar</title>
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

/* body 스타일 */ 
	body {    
		margin-top: 40px;    
		font-size: 14px;    
		font-family: Arial, Helvetica Neue, Helvetica, sans-serif;}
/* 드래그 박스의 스타일 */  
#external-events {  
		left: 20px;    
		top: 20px;    
		padding: 0 10px;    
		border: 1px solid #ccc;    
		background: #eee;    
		text-align: left;  }  

#external-events h4 {    
		font-size: 16px;    margin-top: 0;    
		padding-top: 1em;  }  

#external-events .fc-event {    
		margin: 3px 0;    
		cursor: move;  }   

#external-events p {    
		margin: 1.5em 0;    
		font-size: 11px;    
		color: #666;  }   

#external-events p input {   
		margin: 0;    
		vertical-align: middle;  }   

#calendar-wrap {    

}   

#calendar1 {    
		max-width: 1100px;    
		margin: 0 auto;  }

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
		
	</header>
	
	<div class="container">
		<div class="row">
			<div class="col">
				
			</div>
			<div class="col-9">
				<div class="row">
					<div class="col-3">
						<img src="${pageContext.request.contextPath}/resources/images/sidebar.png" style="width:200px; height:500px;"/>
					</div>
					<div class="col-9">
						<h4 style="margin-top:30px; margin-bottom:30px;"><strong>배송달력</strong></h4>
						<!-- calendar 태그 -->
						<div id='calendar-wrap'>
							<div id='calendar1'></div>
						</div>
						
						<!-- 드래그 박스 -->
						<div id='external-events'>
							<h4 style="margin-top:10px; margin-bottom:10px; color:purple;"><strong>장바구니</strong></h4>
							<div id='external-events-list'>
								<div class="owl-carousel owl-theme owl-loaded">
									<div class="owl-stage-outer">
								  		<div class="owl-stage">
								  			<c:forEach var="tmp" items="${map.list}">
								  			<div class="owl-item">
												<div class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event'>
													<div class='fc-event-main'>
														<img src="${tmp.product_imgeurl}" style="width:100px; height:100px;"/>
														<p>${tmp.product_name}</p>
														<p>${tmp.product_count}개</p>
														<p>${tmp.money}원</p>
													</div>
												</div>
											</div>
										</c:forEach>
								  		</div>
								 	</div>
								</div>
							</div>
						</div>
						
						<!-- 추천상품 -->
						<h6 style="margin-top:30px; margin-bottom:30px; color:purple;"><strong>정기 배송 추천상품</strong></h6>
						<div class="row">
							<c:forEach var="tmt" items="${map.list2}">
								<div class="col">
									<a href="${pageContext.request.contextPath}/info.do?product_id=${tmt.product_id}">
										<img src="${tmt.product_imgeurl}" style="width:200px; height:200px;">
									</a>
									<p>${tmt.product_name}</p>
								</div>
			  				</c:forEach>
						</div>
					</div>
				</div>
			</div>
			<div class="col">
			
			</div>
		</div>
	</div>


	
	


<script>

$(document).ready(function($){
	    var owl = $('.owl-carousel');
	    
	    owl.owlCarousel({
	    	navigation : true,
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

<script>  
(function(){    $(function(){
	// 드래그 박스 취득      
	var containerEl = $('#external-events-list')[0];      
	// 설정하기      
	new FullCalendar.Draggable(containerEl, {
		itemSelector: '.fc-event',
		eventData: function(eventEl) {
			return {
				title: eventEl.innerText.trim()
				}}});      
	// 드래그 아이템 추가하기      
	
	// calendar element 취득      
	var calendarEl = $('#calendar1')[0];      
	// full-calendar 생성하기      
	var calendar = new FullCalendar.Calendar(calendarEl, {        
		// 해더에 표시할 툴바        
		headerToolbar: {
			left: 'prev,next today',
			center: 'title',
			right: 'dayGridMonth,listWeek'
			},   
		locale: 'ko',
		// 한국어 설정       
		editable: true,
		// 수정 가능        
		droppable: true,
		// 드래그 가능        
		drop: function(arg) { 
			// 드래그 엔 드롭 성공시          
			// 드래그 박스에서 아이템을 삭제한다.
			arg.draggedEl.parentNode.removeChild(arg.draggedEl);
		}
	});
	// 캘린더 랜더링      
	calendar.render();    
});  })();
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>