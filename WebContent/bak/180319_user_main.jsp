<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<title>Story Blog - User</title>
</head>
<body>
<!-- ��ü Ʋ div �� -->  
<div>&nbsp;

<!-- ���� (�������������� ���� O) -->
<div style="margin-top:54px; margin-left: 10%;"><br>
	<!-- <div class="w3-col m8 w3-row" style="margin-left: 22%; margin-top: 5%; margin-bottom: 3%;"> -->

<!-- ���� margin -->
<div style="margin-left: 10%; margin-right: 10%;">

		<!-- �Ϸ��� �� - form ���� -->
		<form action="<%=request.getContextPath() %>/story/user_write" method="post">
				
			<!-- �Ϸ��� �� -->
			<div class="w3-row-padding">
				<div class="w3-col m12">
					<div class="w3-card w3-round w3-white">
						<div class="w3-container w3-padding">
							<h6 class="w3-text-gray">���� �Ϸ縦 ����غ�����.</h6>
							<input class="w3-input w3-border w3-text-grey" name="subject" type="text" style="font-size: 9pt;" value="${subject}"><br>
							<button type="submit" class="w3-button w3-theme w3-pink w3-right" 
								onclick="location.href='/Story_Blog_MyBatis/story/user_write'">�۾���</button>
								<input type="hidden" name="diaryid" value="${diaryid}">
								<input type="hidden" name="subject" value="${subejct}">
						</div>
					</div>
				</div>
			</div>
			<!-- end. �Ϸ��� �� -->
				
		</form>
		<!-- end. �Ϸ��� �� - form ���� -->
			
		
		<!-- �ϱⰡ ���� �� -->	
        <c:if test="${count==0}">
        <div class="w3-container w3-card w3-white w3-round w3-margin">
       		<h3 class="w3-center">�ϱ� ��:${count}</h3>

	        <div class="w3-center w3-container">
	       		<p class="w3-pink">�ϱ⸦ ���ּ���!!</p>
	       	</div>	
        </div>
        </c:if>
       
	    
	    <!-- �ϱⰡ ���� �� -->
	    <c:if test="${count!=0}">
	    <div class="w3-container w3-card w3-white w3-round w3-margin">
	    	<h3 class="w3-center">�ϱ� ��:${count}</h3>
	    </div>
	    
        <!-- �ϱ� (����) -->
        <c:forEach var="diary" items="${diaryList}">
		<div class="w3-container w3-card w3-white w3-round w3-margin">
			<br> 
			<img src="<%=request.getContextPath() %>/userSave/${filename}" alt="${filename}" class="w3-left w3-circle w3-margin-right"
				style="width: 60px; height: 60px"> 
			<!-- ��¥ ��� -->
			<span class="w3-right w3-text-gray">${diary.cdate}</span>
			<!-- ���� ��� -->
			<b>����: </b><a href="<%=request.getContextPath()%>/story/user_content?num=${diary.num}&pageNum=${currentPage}"><b>${diary.subject}</b></a>
			<!-- ����, ���� -->
			<div style="margin-top: 6px;">
				<form method="post" style="display: inline-block; margin-left: 10px;" class="w3-right" action="<%=request.getContextPath() %>/story/user_deleteDPro">
					<input type="submit" class="w3-button w3-red w3-small" value="����">
					
					<!-- hidden����  deleteDPro�� �ѱ��!!!  -->
               		<%-- <input type="hidden" name="email" value="<%=diary.getEmail() %>"> --%>
               		<input type="hidden" name="diaryid" value="${diary.diaryid}">
               		<input type="hidden" name="num" value="${diary.num}">
               		<!--  -->
				</form>
					
				<form method="post" style="display: inline-block;" class="w3-right" action="<%=request.getContextPath() %>/story/user_updateDForm">
					 <input type="submit" class="w3-button w3-blue w3-small" value="����">
					 
					 <!-- hidden����  updateDForm�� �ѱ��!!!  -->
               		<%-- <input type="hidden" name="email" value="<%=diary.getEmail() %>"> --%>
               		<input type="hidden" name="diaryid" value="${diary.diaryid}">
               		<input type="hidden" name="num" value="${diary.num}">
               		<!--  -->
				</form>
			</div>
			<!-- end. ����, ���� -->
			
			<!-- �̸��� ��� -->		
			<p class="w3-text-gray" style="font-size: 9pt;">${diary.email}</p>
			
			<hr class="w3-clear">
			<!-- ���� ��� -->
			<p>${diary.content}</p>

			<!-- �̹��� ��� -->
			<c:if test="${diary.filename0!=null}">
				<div class="w3-third w3-container w3-margin-bottom">
					<div class="w3-container w3-white w3-padding w3-center">
						<img src="<%=request.getContextPath() %>/fileSave/${diary.filename0}" alt="${diary.filename0}" style="width: auto; height:200px;">
					</div>
				</div>		
			</c:if>
			
			<c:if test="${diary.filename1!=null}">
				<div class="w3-third w3-container w3-margin-bottom">
					<div class="w3-container w3-white w3-padding w3-center">
						<img src="<%=request.getContextPath() %>/fileSave/${diary.filename1}" alt="${diary.filename1}" style="width: auto; height:200px;">
					</div>
				</div>		
			</c:if>
			
			<c:if test="${diary.filename2!=null}">
				<div class="w3-third w3-container w3-margin-bottom">
					<div class="w3-container w3-white w3-padding w3-center">
						<img src="<%=request.getContextPath() %>/fileSave/${diary.filename2}" alt="${diary.filename2}" style="width: auto; height:200px;">
					</div>
				</div>		
			</c:if>
			
			<c:if test="${diary.filename3!=null}">
				<div class="w3-third w3-container w3-margin-bottom">
					<div class="w3-container w3-white w3-padding w3-center">
						<img src="<%=request.getContextPath() %>/fileSave/${diary.filename3}" alt="${diary.filename3}" style="width: auto; height:200px;">
					</div>
				</div>		
			</c:if>
			
			<c:if test="${diary.filename4!=null}">
				<div class="w3-third w3-container w3-margin-bottom">
					<div class="w3-container w3-white w3-padding w3-center">
						<img src="<%=request.getContextPath() %>/fileSave/${diary.filename4}" alt="${diary.filename4}" style="width: auto; height:200px;">
					</div>
				</div>		
			</c:if>
		</div>

		</c:forEach>
		</c:if>
			
		<!-- end. �ϱ� (Ÿ�Ӷ���) -->	
		
		<!-- �ϱ� ������ -->
		<div class = "w3-center w3-container w3-card w3-white w3-round w3-margin">
			<c:if test="${count>0}"> 
				<c:if test="${startPage > bottomLine}">
					<a href="user_main?pageNum=${startPage - bottomLine}">[����]</a>
				</c:if>
				
				<c:forEach var="i" begin="${startPage}" end="${endPage}">
					<a href="user_main?pageNum=${i}"> <c:if test="${i != currentPage}">[${i}]</c:if>
						<c:if test="${i == currentPage}">
							<font color='red'>[${i}]</font>
						</c:if>
					</a>
				</c:forEach>
				
				<c:if test="${endPage < pageCount}">
						<a href="user_main?pageNum=${startPage + bottomLine}">[����]</a>
		
					</c:if>
				</c:if>
		</div>
<!-- end. �ϱ� ������ -->
		
      	
</div>
<!-- end. ���� margin -->
</div>
<!-- end. ���� -->

</div>
<!-- end. ��ü Ʋ div �� -->



</body>
</html>