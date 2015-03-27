$(document).ready(function(){
	
	var fullData="";
	var eachArray=[];
	var i;
	
	$.ajax({
		method:'get',
        url:'/tpl3/GetMatchDetails',
        success:function(data)
        {
        	fullData=data;
        	fullData = fullData.substring(0,fullData.length-1);
           	eachArray = fullData.split(" ");
        	for (i = 0; i < eachArray.length; ++i)
        	{
        		$("#sel_id").append("<option value='"+eachArray[i]+"'>"+eachArray[i]+"</option>");
        	}
        }
	});
	
	$("#sel_id").change(function()
	{
		$("#toss_team").empty();
		$.ajax({
			method:'get',
	        url:'/tpl3/GetTeams?match_id='+$("#sel_id").val(),
	        success:function(data)
	        {
	        	fullData = data;	        	
	           	eachArray = fullData.split(",");
	           	$("#team_a_name").attr("value",eachArray[0]);
	           	$("#team_b_name").attr("value",eachArray[1]);  	
	           	for (i = 0; i < eachArray.length; i++)
	        	{
	        		$("#toss_team").append("<option value='"+eachArray[i]+"'>"+eachArray[i]+"</option>");
	        	}
	           	$("#radio_team_a").attr("value",eachArray[0]);
	           	$("#radio_team_b").attr("value",eachArray[1]); 
	        }
		});
	});
	
	$( ".start_btn" ).click(function( event ) {
		if($("#sel_id").val=="" ||$("#toss_team").val==""||$("input[name=innings]:checked").val=="")
			{
			alert("select field");
			return false;
			}
			var params = $( "#form" ).serialize();			
		    $.ajax({
		    	method:'get',
		    	url:'/tpl3/StartMatch?'+params,
		    	success:function(data){
		    		
		    	}
		    });		    
		    
		});
	
	$( "#form1	" ).submit(function( event ) {
		var params = $( "#form1" ).serialize();		
		var match_id = $("#sel_id").val();
		 var innings = $("input[name=innings]:checked").val();
		 alert(match_id+" "+innings);
	    $.ajax({
	    	method:'get',
	    	url:'/tpl3/LiveScore?'+params+'&match_id='+match_id+'&innings='+innings,
	    	success:function(data){
	    		
	    	}
	    });		    
	    
	});
	
    	
	/* wickets count */
	
	$("#wicket_cnt").click(function(){
		var $w = $("#wickt_detl");
		if($w.val() < 10){
			$w.val(Number($w.val())+1);
		}
	});
	
	/* team extras */
	
	$("#team_ext_cnt").click(function(){
		var $tmx = $("#team_ext");
		var $tmscr = $("#team_scr_cnt");
		$tmscr.val(Number($tmscr.val())+1); // Team Score Change
		$tmx.val(Number($tmx.val())+1);
	});
	
	/* bowler extras */
	
	$("#bwl_ext_cnt").click(function(){
		var $bx = $("#bwl_ext");
		var $tmscr = $("#team_scr_cnt");
		$tmscr.val(Number($tmscr.val())+1); // Team Score Change
		$bx.val(Number($bx.val())+1);
	});
	
	/* balls count */
	
	$("#ball_cnt").click(function(){
		var $bc = $("#balls_delt");
		var $oc = $("#over_detl");
		if($bc.val() < 5){
			$bc.val(Number($bc.val())+1);
			
			
		}
		else{
			$bc.val(Number(0));
			$oc.val(Number($oc.val())+1); // over count 
		}
	});
	
	/* circle score count*/
	
	$("#scr_1").click(function(){
		var $tmscr = $("#team_scr_cnt");
		$tmscr.val(Number($tmscr.val())+1); // Team Score Change by 1
	});
	$("#scr_2").click(function(){
		var $tmscr = $("#team_scr_cnt");
		$tmscr.val(Number($tmscr.val())+2); // Team Score Change by 2
	});
	$("#scr_3").click(function(){
		var $tmscr = $("#team_scr_cnt");
		$tmscr.val(Number($tmscr.val())+3); // Team Score Change by 2
	});
	$("#scr_4").click(function(){
		var $tmscr = $("#team_scr_cnt");
		$tmscr.val(Number($tmscr.val())+4); // Team Score Change by 4
	});
	$("#scr_6").click(function(){
		var $tmscr = $("#team_scr_cnt");
		$tmscr.val(Number($tmscr.val())+6); // Team Score Change by 6
	});
	
	//innings
	 $(".end_inng_btn").click(function(){
         var innings_status = $("input[name=innings_status]:checked").val();
         if(innings_status=="First Batting")
                 {
                 alert("Confirm To End First innings ?");
                 $(".score_card").hide();
                 }
         else if(innings_status=="Second Batting")
                 {
                 alert("Confirm To End Second innings And End The Game");
                 $(".score_card").hide();
                 }
 });
	
	
	
	
	
	
	/* Json */
	
			$.getJSON( "input.json", function( data ) {
				$(".logo").append("<img src="+data.tpl[0].logo+">");
				$(".header_title").append(data.tpl[0].headTitle);
				$('.logo img:not([alt])').attr('alt', 'tpl_logo');
					/*$.each(data.team, function(i, message){
						var selettm = '<option value="' + this.ID + '">' + this.NAME + '</option>'; // Select TeamA
						var seletid = '<option value="' + this.ID + '">' + "match" + this.ID + '</option>'; // Select Id
						$(selettm).appendTo("#team_a_name");
						$(selettm).appendTo("#team_b_name");
						$(seletid).appendTo("#sel_id");
					});*/
					$.each(data.menu, function(a, val){
						var getmenuitm = '<li>'+'<a href="'+ this.URL +'">' + this.MENU + '</a>'+'</li>';
						$(getmenuitm).appendTo(".menu ul");
					});
					/*var arr = [], len;
					for(key in data.team) {
					arr.push(key);
					}									// array length find 
					len = arr.length;
					alert(len);
					console.log(len); //4 */
					
			});				
});