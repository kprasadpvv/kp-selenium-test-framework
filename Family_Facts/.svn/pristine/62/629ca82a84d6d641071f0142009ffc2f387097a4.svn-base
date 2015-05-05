$(document).ready( function()
{
$(".modules").on('click',function()
{
	var $tablediv = $('.tctable').get();
	
// hiding other elements except the one selected
    $($tablediv).each(function(index, element) 
		{
			$(element).hide();
		});
	$("#mpiechart").hide();
		
// Open the element clicked 	
	for(var index=0; index<$tablediv.length;index++)
	{
	 if($tablediv[index].id==($(this).text()))
		{
		
		 $("#"+$tablediv[index].id).slideDown(300, function()
			{
				$(this).addClass("active");
			
			}); 
		$("."+$tablediv[index].id).slideDown(300,function()
		{
			$(this).addClass("active");
		}); 
		}
	 }
	 return false;
 });
 
 $( ".tc" ).on('click',function() 
{
	$("#fade").show();
	var $mname = $(this).attr("name");
	var $senarios = $('.testsenarios').get();
		
	for(var index=0; index<$senarios.length;index++)
	{
		var $tcName= ($mname+$(this).text());
		
		if($senarios[index].id == $tcName)
		{
			var $tableid = $senarios[index].id;
		 $("#"+$senarios[index].id).slideDown(0, function()
			{
				$(this).addClass("movements");
				$(this).addClass("white_content").css("display","block");
				$(".senario").css("display","block");
				$(".close").css("display","block");
				
			});
			
			$(".close").on('click', function() 
			{
				$(".close").css("display","none");
				$("#"+$tableid).css("display","none");
				$("#fade").hide();
			
			});
			
			$("#fade").on('click', function() 
			{
				
				$("#"+$tableid).css("display","none");
				$("#fade").hide();
				$(".close").css("display","none");
			
			});
			
		}
		
	}
	return false;
	
	
	
});
 
 
}); 
 
 
 
 function mdrawChart( p,  f, s ) 
	{
	   var pass= parseInt(p);
	   var fail= parseInt(f);
	    var skip= parseInt(s);
        var data = google.visualization.arrayToDataTable([
          ['TS', 'Results'],
          ['Pass',  pass],
          ['Fail',  fail],
          ['Skip',  skip],
          ]);

        var options = {
          title: 'Module level Results',
		  colors: ['green', 'red', 'orange'],
		  backgroundColor: '#f8f8f3',
		  width:400,
		  height:400,
		  is3D: true,
        };
		
		var tchart = new google.visualization.PieChart(document.getElementById('mpiechart'));
		var $tablediv = $('.tctable').get();
	
		// hiding other elements except the one selected
			$($tablediv).each(function(index, element) 
			{
				$(element).hide();
			});
		$('#tpiechart').css("display","none");
		$('#mpiechart').css("display","block");
	
        tchart.draw(data,options);
		
      }

function drawChart( p, f, s ) 
	{
	   var pass= parseInt(p);
	   var fail= parseInt(f);
	    var skip= parseInt(s);
        var data = google.visualization.arrayToDataTable([
          ['TC', 'Results'],
          ['Pass',  pass],
          ['Fail',  fail],
          ['Skip',  skip],
          ]);

        var options = {
          title: 'Test Case Results',
		  colors: ['green', 'red', 'orange'],
		  backgroundColor: '#f8f8f3',
		  width:380,
		  height:350,
		  is3D: true,

        };

		var chart = new google.visualization.PieChart(document.getElementById('tpiechart'));
		$('#mpiechart').css("display","none");
		$('#tpiechart').css("display","block");
		
// Adding Action Listeners to the graph sectors
		/* function selectHandler() {
          var $selectedItem = chart.getSelection()[0];
          if ($selectedItem) {
            var topping = data.getValue($selectedItem.row, 0);
			if(topping=="fail")
			{
			$('#fail').addClass("hidden");
			}
           // alert('The user selected ' + topping);
          }
        }

        google.visualization.events.addListener(chart, 'select', selectHandler); */
		
        chart.draw(data, options);
}
	 