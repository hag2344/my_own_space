
//최근 7일간 개통 건수//
$(function () {
var areaChartData = {
	labels  : ['7/11', '7/12', '7/13', '7/14', '7/15', '7/16', '7/17'],
	datasets: [
	{
		label               : '기가지니',
		backgroundColor     : 'rgba(64,101,224,0.1)',
		borderColor         : 'rgba(64,101,224,1)',
		borderWidth         : 1,
		pointRadius         : 0,
		pointBackgroundColor : 'rgba(64,101,224,1)',
		data                : [144, 102, 122, 107, 100, 88, 68],
		lineTension         : 0.4
	},
	{
		label               : 'Olleh TV',
		backgroundColor     : 'rgba(46,141,255, 0.1)',
		borderColor         : 'rgba(46,141,255, 1)',
		borderWidth         : 1,
		pointRadius         : 0,
		pointBackgroundColor : 'rgba(46,141,255, 1)',
		data                : [250, 221, 260, 214, 222, 198, 180],
		lineTension         : 0.4
	},
	{
		label               : '인터넷전화(가정)',
		backgroundColor     : 'rgba(37,214,251, 0.1)',
		borderColor         : 'rgba(37,214,251, 1)',
		borderWidth         : 1,
		pointRadius         : 0,
		pointBackgroundColor : 'rgba(37,214,251, 1)',
		data                : [78, 130, 166, 122, 98, 88, 78],
		lineTension         : 0.4
	},
	{
		label               : '인터넷전화(기업)',
		backgroundColor     : 'rgba(99,255,218, 0.1)',
		borderColor         : 'rgba(99,255,218, 1)',
		borderWidth         : 1,
		pointRadius         : 0,
		pointBackgroundColor : 'rgba(99,255,218, 1)',
		data                : [150, 200, 134, 165, 122, 88, 110],
		lineTension         : 0.4
	},
	{
		label               : '홈AP',
		backgroundColor     : 'rgba(5,230,153, 0.1)',
		borderColor         : 'rgba(5,230,153, 1)',
		borderWidth         : 1,
		pointRadius         : 0,
		pointBackgroundColor : 'rgba(5,230,153, 1)',
		data                : [270, 151, 282, 255, 226, 243, 22],
		lineTension         : 0.4
	},
	{
		label               : '홈IoT',
		backgroundColor     : 'rgba(7,179,207, 0.1)',
		borderColor         : 'rgba(7,179,207, 1)',
		borderWidth         : 1,
		pointRadius         : 0,
		pointBackgroundColor : 'rgba(7,179,207, 1)',
		data                : [230, 30, 90, 180, 95, 107, 60],
		lineTension         : 0.4
	},
	]
};
var areaChartOptions = {

	maintainAspectRatio : false,
	responsive : true,
	legend: {
	display: false
	},
	scales: {
	xAxes: [{
		gridLines : {
		display : true,
		}
	}],
	yAxes: [{
		gridLines : {
		display : true,
		}
	}]
	}
};
//var areaChart = new Chart(
//		$('#areaChart').get(0).getContext('2d'),
//		{
//			type: 'line',
//			data: areaChartData,
//			options: areaChartOptions
//});

});




	//금일 단말유형별 개통 건수 - 개통상태별(Stacked Bar Chart)

$(function () {
var barChart1Data = {
		labels  : ['기가지니','Olleh TV','인터넷전화(가정)','인터넷전화(기업)','홈AP','홈IoT'],
		datasets: [
		{
			label               : '개통완료',
			backgroundColor     : 'rgba(39, 209, 236, 1)',
			borderColor         : 'rgba(39, 209, 236, 1)',
			borderWidth         : 1,
			pointRadius         : false,
			pointColor          : '#3b8bba',
			pointStrokeColor    : 'rgba(60,141,188,1)',
			pointHighlightFill  : '#fff',
			pointHighlightStroke: 'rgba(60,141,188,1)',
			maxBarThickness     : 35,
			data                : [150, 101, 220, 184, 222, 78, 130]
		},
		{
			label               : '개통대기',
			backgroundColor     : 'rgba(227, 233, 242, 1)',
			borderColor         : 'rgba(227, 233, 242, 1)',
			borderWidth         : 1,
			pointRadius          : false,
			pointColor          : '#3b8bba',
			pointStrokeColor    : 'rgba(210, 214, 222,1)',
			pointHighlightFill  : '#fff',
			pointHighlightStroke: 'rgba(210, 214, 222,1)',
			maxBarThickness     : 35,
			data                : [50, 32, 82, 50, 100, 28, 48]
		}
		]
};
		/* Chart 옵션 */
var stackedBarChartOptions = {
	responsive              : true,
	maintainAspectRatio     : false,
	legend: {
	display: false
	},
	scales: {
		xAxes: [{
			stacked: true,
		}],
		yAxes: [{
			stacked: true
		}]
	}
}
//var stackedBarChart1 = new Chart($('#stackedBarChart1').get(0).getContext('2d'), {
//		type: 'bar',
//		data: barChart1Data,
//		options: stackedBarChartOptions
//});

});





	//금일 단말유형별 개통 건수 - 개통유형별(Stacked Bar Chart)
$(function () {
var barChart1Data = {
		labels  : ['기가지니','Olleh TV','인터넷전화(가정)','인터넷전화(기업)','홈AP','홈IoT'],
		datasets: [
		{
			label               : '개통완료',
			backgroundColor     : 'rgba(39, 209, 236, 1)',
			borderColor         : 'rgba(39, 209, 236, 1)',
			borderWidth         : 1,
			pointRadius         : false,
			pointColor          : '#3b8bba',
			pointStrokeColor    : 'rgba(60,141,188,1)',
			pointHighlightFill  : '#fff',
			pointHighlightStroke: 'rgba(60,141,188,1)',
			maxBarThickness     : 35,
			data                : [50, 31, 150, 224, 122, 178, 230]
		},
		{
			label               : '개통대기',
			backgroundColor     : 'rgba(227, 233, 242, 1)',
			borderColor         : 'rgba(227, 233, 242, 1)',
			borderWidth         : 1,
			pointRadius          : false,
			pointColor          : '#3b8bba',
			pointStrokeColor    : 'rgba(210, 214, 222,1)',
			pointHighlightFill  : '#fff',
			pointHighlightStroke: 'rgba(210, 214, 222,1)',
			maxBarThickness     : 35,
			data                : [20, 12, 32, 50, 70, 58, 48]
		}
		]
};
		/* Chart 옵션 */
var stackedBarChartOptions = {
	responsive              : true,
	maintainAspectRatio     : false,
	legend: {
	display: false
	},
	scales: {
		xAxes: [{
			stacked: true,
		}],
		yAxes: [{
			stacked: true
		}]
	}
}
//var stackedBarChart2 = new Chart($('#stackedBarChart2').get(0).getContext('2d'), {
//		type: 'bar',
//		data: barChart1Data,
//		options: stackedBarChartOptions
//});

});





//단말 유형별 현황 도넛 그래프

//$(function () {
//	var donutChart = new Chart(
//					 $('#donutChart').get(0).getContext('2d'),
//					 {
//							type: 'doughnut',
//							/* Chart 데이터 */
//		data: {
//								 labels: [
//											 '기가지니',
//											 'Olleh TV',
//											 '인터넷전화(가정)',
//											 '인터넷전화(기업)',
//											 '홈AP',
//											 '홈IoT'
//								 ],
//								 datasets: [
//										{
//											 data: [12700,45500,9400,55600,52000,7800],
//											 backgroundColor: [
//													 'rgba(64,101,224,1)',
//													 'rgba(46,141,255,1)',
//													 'rgba(37,214,251, 1)',
//													 'rgba(99,255,218, 1)',
//													 'rgba(5,230,153, 1)',
//													 'rgba(7,179,207, 1)'
//											 ],
//											 borderColor: [
//													 'rgba(64,101,224,1)',
//													 'rgba(46,141,255,1)',
//													 'rgba(37,214,251, 1)',
//													 'rgba(99,255,218, 1)',
//													 'rgba(5,230,153, 1)',
//													 'rgba(7,179,207, 1)'
//											 ],
//											 borderWidth: 1
//										}
//								 ]
//							},
//							/* Chart 옵션 */
//							options: {
//								 maintainAspectRatio : false,
//								 responsive : true,
//								 position : 'right',
//								 legend: {
//							 	display: false
//							 	},
//								 elements: {
//										 arc: [{
//												 borderWidth:20,
//										 }],
//									 }
//							}
//			 });
//
//	});





//장기미사용 단말 - 단말유형별

$(function () {
	var barChart2Data = {
		labels  : ['전체','1개월이상','2개월이상','3개월이상','4개월이상','5개월이상','6개월이상'],
		datasets: [
		{
			label               : '기가지니',
			backgroundColor     : 'rgba(64,101,224,1)',
			borderColor         : 'rgba(64,101,224,1)',
			pointRadius          : false,
			pointColor          : '#3b8bba',
			pointStrokeColor    : 'rgba(60,141,188,1)',
			pointHighlightFill  : '#fff',
			pointHighlightStroke: 'rgba(60,141,188,1)',
			maxBarThickness     : 8,
			categoryPercentage  : 0.5,
			barPercentage       : 1.0,
			data                : [632, 75, 130, 150, 68, 155, 54]
		},
		{
			label               : 'Olleh TV',
			backgroundColor     : 'rgba(46,141,255, 1)',
			borderColor         : 'rgba(46,141,255, 1)',
			pointRadius          : false,
			pointColor          : '#3b8bba',
			pointStrokeColor    : 'rgba(210, 214, 222,1)',
			pointHighlightFill  : '#fff',
			pointHighlightStroke: 'rgba(210, 214, 222,1)',
			maxBarThickness     : 8,
			categoryPercentage  : 0.5,
			barPercentage       : 1.0,
			data                : [1201, 103, 143, 129, 285, 247, 295]
		},
		{
			label               : '인터넷전화(가정)',
			backgroundColor     : 'rgba(37,214,251, 1)',
			borderColor         : 'rgba(37,214,251, 1))',
			pointRadius          : false,
			pointColor          : '#3b8bba',
			pointStrokeColor    : 'rgba(0,166,90,1)',
			pointHighlightFill  : '#fff',
			pointHighlightStroke: 'rgba(0,166,90,1)',
			maxBarThickness     : 8,
			categoryPercentage  : 0.5,
			barPercentage       : 1.0,
			data                : [1074, 255, 128, 221, 116, 264, 92]
		},
		{
			label               : '인터넷전화(기업)',
			backgroundColor     : 'rgba(99,255,218, 1)',
			borderColor         : 'rgba(99,255,218, 1)',
			pointRadius          : false,
			pointColor          : '#3b8bba',
			pointStrokeColor    : 'rgba(243,156,18,1)',
			pointHighlightFill  : '#fff',
			pointHighlightStroke: 'rgba(243,156,18,1)',
			maxBarThickness     : 8,
			categoryPercentage  : 0.5,
			barPercentage       : 1.0,
			data                : [1390, 165, 150, 330, 286, 341, 119]
		},
		{
			label               : '홈AP',
			backgroundColor     : 'rgba(5,230,153, 1)',
			borderColor         : 'rgba(5,230,153, 1)',
			pointRadius          : false,
			pointColor          : '#3b8bba',
			pointStrokeColor    : 'rgba(0,192,239,1)',
			pointHighlightFill  : '#fff',
			pointHighlightStroke: 'rgba(0,192,239,1)',
			maxBarThickness     : 8,
			categoryPercentage  : 0.5,
			barPercentage       : 1.0,
			data                : [1770, 210, 420, 190, 364, 434, 151]
		},
		{
			label               : '홈IoT',
			backgroundColor     : 'rgba(7,179,207, 1)',
			borderColor         : 'rgba(7,179,207, 1)',
			pointRadius          : false,
			pointColor          : '#3b8bba',
			pointStrokeColor    : 'rgba(123, 104, 238,1)',
			pointHighlightFill  : '#fff',
			pointHighlightStroke: 'rgba(123, 104, 238,1)',
			maxBarThickness     : 8,
			categoryPercentage  : 0.5,
			barPercentage       : 1.0,
			data                : [253, 30, 27, 62, 60, 22, 52]
		}
		]
	};

	var barChartOptions = {
		responsive              : true,
		maintainAspectRatio     : false,
		datasetFill             : false,
		legend: {
	 	display: false
	 	},
	}

//	var barChart2 = new Chart($('#barChart2').get(0).getContext('2d'), {
//			type: 'bar',
//			data: barChart2Data,
//			options: barChartOptions
//	});
});










//장기미사용 단말 - 기간별

$(function () {
	var barChart2Data = {
		labels  : ['전체','1개월이상','2개월이상','3개월이상','4개월이상','5개월이상','6개월이상'],
		datasets: [
		{
			label               : '기가지니',
			backgroundColor     : 'rgba(64,101,224,1)',
			borderColor         : 'rgba(64,101,224,1)',
			pointRadius          : false,
			pointColor          : '#3b8bba',
			pointStrokeColor    : 'rgba(60,141,188,1)',
			pointHighlightFill  : '#fff',
			pointHighlightStroke: 'rgba(60,141,188,1)',
			maxBarThickness     : 8,
			categoryPercentage  : 0.5,
			barPercentage       : 1.0,
			data                : [632, 75, 130, 150, 68, 155, 54]
		},
		{
			label               : 'Olleh TV',
			backgroundColor     : 'rgba(46,141,255, 1)',
			borderColor         : 'rgba(46,141,255, 1)',
			pointRadius          : false,
			pointColor          : '#3b8bba',
			pointStrokeColor    : 'rgba(210, 214, 222,1)',
			pointHighlightFill  : '#fff',
			pointHighlightStroke: 'rgba(210, 214, 222,1)',
			maxBarThickness     : 8,
			categoryPercentage  : 0.5,
			barPercentage       : 1.0,
			data                : [1000, 53, 203, 300, 85, 147, 95]
		},
		{
			label               : '인터넷전화(가정)',
			backgroundColor     : 'rgba(37,214,251, 1)',
			borderColor         : 'rgba(37,214,251, 1))',
			pointRadius          : false,
			pointColor          : '#3b8bba',
			pointStrokeColor    : 'rgba(0,166,90,1)',
			pointHighlightFill  : '#fff',
			pointHighlightStroke: 'rgba(0,166,90,1)',
			maxBarThickness     : 8,
			categoryPercentage  : 0.5,
			barPercentage       : 1.0,
			data                : [1274, 155, 28, 121, 56, 364, 92]
		},
		{
			label               : '인터넷전화(기업)',
			backgroundColor     : 'rgba(99,255,218, 1)',
			borderColor         : 'rgba(99,255,218, 1)',
			pointRadius          : false,
			pointColor          : '#3b8bba',
			pointStrokeColor    : 'rgba(243,156,18,1)',
			pointHighlightFill  : '#fff',
			pointHighlightStroke: 'rgba(243,156,18,1)',
			maxBarThickness     : 8,
			categoryPercentage  : 0.5,
			barPercentage       : 1.0,
			data                : [1390, 365, 150, 130, 186, 241, 119]
		},
		{
			label               : '홈AP',
			backgroundColor     : 'rgba(5,230,153, 1)',
			borderColor         : 'rgba(5,230,153, 1)',
			pointRadius          : false,
			pointColor          : '#3b8bba',
			pointStrokeColor    : 'rgba(0,192,239,1)',
			pointHighlightFill  : '#fff',
			pointHighlightStroke: 'rgba(0,192,239,1)',
			maxBarThickness     : 8,
			categoryPercentage  : 0.5,
			barPercentage       : 1.0,
			data                : [1770, 210, 420, 190, 364, 434, 151]
		},
		{
			label               : '홈IoT',
			backgroundColor     : 'rgba(7,179,207, 1)',
			borderColor         : 'rgba(7,179,207, 1)',
			pointRadius          : false,
			pointColor          : '#3b8bba',
			pointStrokeColor    : 'rgba(123, 104, 238,1)',
			pointHighlightFill  : '#fff',
			pointHighlightStroke: 'rgba(123, 104, 238,1)',
			maxBarThickness     : 8,
			categoryPercentage  : 0.5,
			barPercentage       : 1.0,
			data                : [253, 130, 27, 162, 60, 222, 52]
		}
		]
	};

	var barChartOptions = {
		responsive              : true,
		maintainAspectRatio     : false,
		datasetFill             : false,
		legend: {
	 	display: false
	 	},
	}

//	var barChart3 = new Chart($('#barChart3').get(0).getContext('2d'), {
//			type: 'bar',
//			data: barChart2Data,
//			options: barChartOptions
//	});
});




//장기미사용 단말 - 지역별

$(function () {
	var barChart2Data = {
		labels  : ['전체','1개월이상','2개월이상','3개월이상','4개월이상','5개월이상','6개월이상'],
		datasets: [
		{
			label               : '기가지니',
			backgroundColor     : 'rgba(64,101,224,1)',
			borderColor         : 'rgba(64,101,224,1)',
			pointRadius          : false,
			pointColor          : '#3b8bba',
			pointStrokeColor    : 'rgba(60,141,188,1)',
			pointHighlightFill  : '#fff',
			pointHighlightStroke: 'rgba(60,141,188,1)',
			maxBarThickness     : 8,
			categoryPercentage  : 0.5,
			barPercentage       : 1.0,
			data                : [632, 175, 230, 350, 68, 155, 54]
		},
		{
			label               : 'Olleh TV',
			backgroundColor     : 'rgba(46,141,255, 1)',
			borderColor         : 'rgba(46,141,255, 1)',
			pointRadius          : false,
			pointColor          : '#3b8bba',
			pointStrokeColor    : 'rgba(210, 214, 222,1)',
			pointHighlightFill  : '#fff',
			pointHighlightStroke: 'rgba(210, 214, 222,1)',
			maxBarThickness     : 8,
			categoryPercentage  : 0.5,
			barPercentage       : 1.0,
			data                : [1201, 153, 143, 329, 185, 147, 295]
		},
		{
			label               : '인터넷전화(가정)',
			backgroundColor     : 'rgba(37,214,251, 1)',
			borderColor         : 'rgba(37,214,251, 1))',
			pointRadius          : false,
			pointColor          : '#3b8bba',
			pointStrokeColor    : 'rgba(0,166,90,1)',
			pointHighlightFill  : '#fff',
			pointHighlightStroke: 'rgba(0,166,90,1)',
			maxBarThickness     : 8,
			categoryPercentage  : 0.5,
			barPercentage       : 1.0,
			data                : [1074, 355, 328, 221, 116, 64, 292]
		},
		{
			label               : '인터넷전화(기업)',
			backgroundColor     : 'rgba(99,255,218, 1)',
			borderColor         : 'rgba(99,255,218, 1)',
			pointRadius          : false,
			pointColor          : '#3b8bba',
			pointStrokeColor    : 'rgba(243,156,18,1)',
			pointHighlightFill  : '#fff',
			pointHighlightStroke: 'rgba(243,156,18,1)',
			maxBarThickness     : 8,
			categoryPercentage  : 0.5,
			barPercentage       : 1.0,
			data                : [1390, 165, 350, 130, 86, 341, 119]
		},
		{
			label               : '홈AP',
			backgroundColor     : 'rgba(5,230,153, 1)',
			borderColor         : 'rgba(5,230,153, 1)',
			pointRadius          : false,
			pointColor          : '#3b8bba',
			pointStrokeColor    : 'rgba(0,192,239,1)',
			pointHighlightFill  : '#fff',
			pointHighlightStroke: 'rgba(0,192,239,1)',
			maxBarThickness     : 8,
			categoryPercentage  : 0.5,
			barPercentage       : 1.0,
			data                : [1770, 210, 220, 190, 164, 134, 151]
		},
		{
			label               : '홈IoT',
			backgroundColor     : 'rgba(7,179,207, 1)',
			borderColor         : 'rgba(7,179,207, 1)',
			pointRadius          : false,
			pointColor          : '#3b8bba',
			pointStrokeColor    : 'rgba(123, 104, 238,1)',
			pointHighlightFill  : '#fff',
			pointHighlightStroke: 'rgba(123, 104, 238,1)',
			maxBarThickness     : 8,
			categoryPercentage  : 0.5,
			barPercentage       : 1.0,
			data                : [253, 330, 27, 362, 60, 322, 52]
		}
		]
	};

	var barChartOptions = {
		responsive              : true,
		maintainAspectRatio     : false,
		datasetFill             : false,
		legend: {
	 	display: false
	 	},
	}

//	var barChart4 = new Chart($('#barChart4').get(0).getContext('2d'), {
//			type: 'bar',
//			data: barChart2Data,
//			options: barChartOptions
//	});
});
