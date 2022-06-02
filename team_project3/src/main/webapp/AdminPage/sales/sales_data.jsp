<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.8.0/chart.min.js"></script>
<script src="AdminPage/js/jquery-3.6.0.js"></script>
<div class="chart-container" style="position: relative; height: 200px; width: 60vw">
	<canvas id="myChart"></canvas>
</div>
<script>
	var ctx = document.getElementById('myChart');

	$(function() {
		$.ajax({
			type : 'POST',
			url : "SalesChart.co",
			dataType : 'json',
			success : function(data) {
				drawChart(data);
			}
		})
	});

	function drawChart(result) {

		//myChart.destroy(); // 기존에 생성한 차트 오브젝트를 없앤다. 

		var sell_total_money = [];
		var sell_fee = [];
		var buy_total_money = [];
		var buy_fee = [];
		var net_income = [];

		for (var i = 0; i < result.length; i++) {
			sell_total_money.push(result[i].sell_total_money);
			sell_fee.push(result[i].sell_fee);
			buy_total_money.push(result[i].buy_total_money);
			buy_fee.push(result[i].buy_fee);
			net_income.push(result[i].net_income);
		}

		var config = {
			type : 'line',
			data : {
				labels : [ // Date Objects
				'01', '02', '03', '04', '05', '06', '07', '08', '09', '10',
						'11', '12' ],
				datasets : [ {
					label : '총 판매한 금액',
					backgroundColor : 'rgba(35, 154, 41, 1)',
					borderColor : 'rgba(35, 154, 41, 1)',
					fill : false,
					data : sell_total_money,
				}, {
					label : '판매 수수료',
					backgroundColor : 'rgba(29, 245, 0, 1)',
					borderColor : 'rgba(29, 245, 0, 1)',
					fill : false,
					data : sell_fee,
				}, {
					label : '총 구매한 금액',
					backgroundColor : 'rgba(10, 42, 184, 1)',
					borderColor : 'rgba(10, 42, 184, 1)',
					fill : false,
					data : buy_total_money,
				}, {
					label : '구매 수수료',
					backgroundColor : 'rgba(46, 126, 255, 1)',
					borderColor : 'rgba(46, 126, 255, 1)',
					fill : false,
					data : buy_fee,
				}, {
					label : '순수익',
					backgroundColor : 'rgba(255, 214, 51, 1)',
					borderColor : 'rgba(255, 214, 51, 1)',
					fill : false,
					data : net_income,
				} ]
			}
		};

		//차트 그리기
		var myChart = new Chart(ctx, config);
	}
</script>