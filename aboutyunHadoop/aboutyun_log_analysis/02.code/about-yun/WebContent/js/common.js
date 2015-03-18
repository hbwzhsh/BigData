// JavaScript Document
$(function(){
	$(".table-sel").click(function(){
		$(this).toggleClass("sel-open");
		return false
	})
	$(".table-sel>.table-sel-bd>ul>li").hover(function(){
		$(this).toggleClass("hover")
	})
	
	$(".time-sort").click(function(){
		$(this).toggleClass("sortB")
	})
})