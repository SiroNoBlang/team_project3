<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.8.0/chart.min.js"></script>
<script src="AdminPage/js/jquery-3.6.0.js"></script>
<div class="chart-container" style="position: relative; height: 200px; width: 60vw">
	<canvas id="myChart"></canvas>
</div>
 <c:if test="${not empty salesChartList and not empty month}">
	<c:forEach var="salesChartList" items="${salesChartList }" varStatus="status">
		<tr>
			<td>${salesChartList.month }</td>
			<td>${salesChartList.sell_total_money }</td>
			<td>${salesChartList.sell_fee }</td>
			<td>${salesChartList.buy_total_money }</td>
			<td>${salesChartList.buy_fee }</td>
		</tr>
	</c:forEach>
</c:if>
<script>
	var ctx = document.getElementById('myChart');
    
	$(function(){
		$.ajax({
	        url: "SalesChart.co", 
	        type: 'post',
	        dataType: 'json',
	        success: function (data) {
	        	drawChart(data);
	        }
	    })
	}); 
	
	function drawChart(data){
		
		chart.destroy(); // 기존에 생성한 차트 오브젝트를 없앤다. 
        
		var config = {
				type : 'line',
				data : {
					labels : [ // Date Objects
					'data1', 'data2', 'data3', 'data4', 'data5', 'data6', 'data7' ],
				 //  01 02 03 04  05  06 ... 12 month
					datasets : [
							{
								label : 'My First dataset',
								// sell_total_money
								backgroundColor : 'rgba(75, 192, 192, 0.1)',
								borderColor : 'rgba(75, 192, 192, 0.1)',
								fill : false,
								data : [ Math.floor(Math.random() * 50),
										Math.floor(Math.random() * 50),
										Math.floor(Math.random() * 50),
										Math.floor(Math.random() * 50),
										Math.floor(Math.random() * 50),
										Math.floor(Math.random() * 50),
										Math.floor(Math.random() * 50) ],
							},
							{
								label : 'My Second dataset',
								// sell_fee
								backgroundColor : 'rgba(75, 192, 192, 1)',
								borderColor : 'rgba(75, 192, 192, 1)',
								fill : false,
								data : [ Math.floor(Math.random() * 50),
										Math.floor(Math.random() * 50),
										Math.floor(Math.random() * 50),
										Math.floor(Math.random() * 50),
										Math.floor(Math.random() * 50),
										Math.floor(Math.random() * 50),
										Math.floor(Math.random() * 50) ],
							},
							{
								label : 'My Second dataset',
								// buy_total_money
								backgroundColor : 'rgba(255, 99, 132, 0.1)',
								borderColor : 'rgba(255, 99, 132, 0.1)',
								fill : false,
								data : [ Math.floor(Math.random() * 50),
										Math.floor(Math.random() * 50),
										Math.floor(Math.random() * 50),
										Math.floor(Math.random() * 50),
										Math.floor(Math.random() * 50),
										Math.floor(Math.random() * 50),
										Math.floor(Math.random() * 50) ],
							},
							{
								label : 'My Second dataset',
								// buy_fee
								backgroundColor : 'rgba(255, 99, 132, 1)',
								borderColor : 'rgba(255, 99, 132, 1)',
								fill : false,
								data : [ Math.floor(Math.random() * 50),
										Math.floor(Math.random() * 50),
										Math.floor(Math.random() * 50),
										Math.floor(Math.random() * 50),
										Math.floor(Math.random() * 50),
										Math.floor(Math.random() * 50),
										Math.floor(Math.random() * 50) ],
							} ]
				},
				options : {
					maintainAspectRatio : false,
					title : {
						text : 'Chart.js Time Scale'
					},
					scales : {
						yAxes : [ {
							scaleLabel : {
								display : true,
								labelString : '천원'
							}
						} ]
					}
				}
			};

			//차트 그리기
			var myChart = new Chart(ctx, config);
	}
</script>