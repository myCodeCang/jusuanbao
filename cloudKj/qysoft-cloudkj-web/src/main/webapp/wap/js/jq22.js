// JavaScript Document
function b(){
	t = parseInt(x.css('top'));
	y.css('top','40px');
	x.animate({top: t - 40 + 'px'},'slow');	//19为每个li的高度
	if(Math.abs(t) == h-40){ //40为每个li的高度
		y.animate({top:'0px'},'slow');
		z=x;
		x=y;
		y=z;
	}
	setTimeout(b,3000);//滚动间隔时间 现在是3秒
}
jQuery(document).ready(function(){
    jQuery('.swap').html(jQuery('.news_li').html());
	x = jQuery('.news_li');
	y = jQuery('.swap');
	h = jQuery('.news_li li').length * 40; //40为每个li的高度
	setTimeout(b,4000);//滚动间隔时间 现在是3秒

})