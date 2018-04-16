

function main() {

	//首页Slide
	jQuery(".slideBox1").slide({
		mainCell: ".bnr-lst",
		effect: "fold",
		mouseOverStop: true,
		autoPlay: true,
		scroll: "1",
		prevCell: ".bnr-btnfl",
		nextCell: ".bnr-btnfr",
		titCell: ".bnr-nbr i",
		interTime: "4000",
		delayTime: 600,
		trigger: "click"
	});

	//算力首页
	jQuery(".slideBox2").slide({
		mainCell: ".n-mills-slide",
		effect: "fold",
		mouseOverStop: true,
		autoPlay: true,
		scroll: "1",
		delayTime: 600,
		trigger: "click"
	});
	// $('.pro-lst-contract-sdc-go').click(function(){
	// 	$('.pop-pro-by,.pop-pro-main').show();
	// })
	$('.pop-pro-data-off,.pop-pro-close,.pop-pro-by,.pop-pro-errors-close,.pop-pro-data-btn').click(function() {
		$('.pop-pro-by,.pop-pro-main,.pop-pro-errors').hide();
		window.location.reload();
	})
	$('.pop-pro-data-btn2').click(function() {
			$('.pop-pro-by,.pop-pro-main,.pop-pro-errors').hide();
		})
		//公告
	jQuery(".slnotice").slide({
		mainCell: ".notice-lst",
		effect: "topLoop",
		mouseOverStop: true,
		autoPlay: true,
		scroll: "1",
		interTime: 3000
	});
	var noticeLst = $('.slnotice li');
	var noticePop = $('.notice-pop');
	var noticeClose = $('.slnotice-close');
	var zhezhao = $('.zhezhao');
	noticeLst.each(function(i) {
		noticeLst.eq(i).click(function() {
			noticePop.eq(i - 1).add(zhezhao).show();
		})
	})
	// noticeClose.add(zhezhao).click(function() {
	// 		noticePop.add(zhezhao).hide();
	// 	})
		//算力首页视频弹框
	var videoDom = $('.btc-mp4,.btc-mp4by');
	$('.banner-btn').click(function() {
		videoDom.show();
	});
	$('.close-mp4').click(function() {
		videoDom.hide();
	});

	//Tab切换
	$(function() {
			function setTab(obj, hoverObj, setObj, current, active, event) {
				var _this = 0;
				if(!active) {
					obj.find(setObj).hide();
					obj.find(setObj).eq(0).show();
				}
				obj.find(hoverObj).on(event, function() {
					_this = $(this).index();
					if(current) {
						$(this).addClass('current').siblings().removeClass('current');
					}
					if(active) {
						obj.find(setObj).eq(_this).removeClass('disnone').siblings().addClass('disnone');
					} else {
						obj.find(setObj).hide();
						obj.find(setObj).eq(_this).show() //.siblings('ul').hide();
					}
				})
			}
			setTab($('.purchaseBox'), '.switchPay li', '.payBox_btc', 'current', null, 'click');
			setTab($('.slprobox'), '.slpro-tab span', '.slpro-tabmn', 'current', null, 'mouseover');
			setTab($('.cal-4box'), '.cal-4 span', '.cal-4mn', 'current', null, 'click');

		})
		//fixed定位

	$(window).resize(function() {
		$('.dd-open').css({
			position: 'fixed',
			left: ($(window).width() - $('.dd-open').outerWidth()) / 2,
			top: ($(window).height() - $('.dd-open').outerHeight()) / 2
		});
	});
	var h_doc = $(document).height();
	var h_foo = h_doc - 60;
	$('.foot').offset({
		top: h_foo,
		left: 0
	});

	$(window).resize();

	//跳转
	$('.down-to').click(function() {
		$('html,body').animate({
			scrollTop: $('.goal').offset().top
		}, 500);
	});
}