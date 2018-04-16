// JavaScript Document
var pie = {
    run:function(opts){
        if(!opts.id) throw new Error("must be canvas id.");
        var canvas = document.getElementById(opts.id), ctx;
        if(canvas && (ctx = canvas.getContext("2d"))){
            canvas.width = canvas.height = "65";
            var noop = function(){};
            var before = opts.onBefore || noop;
            var after = opts.onAfter || noop;
            before(ctx);
            ctx.fillStyle = opts.color || '#ddd';
            var step = opts.step || 1;
            var delay = opts.delay || 8;
            var i = 0, rage = 360 * (opts.percent || 0);
            var sRage = -Math.PI * 0.5;
            var djs = function(){
                i = i + step;
                if(i <= rage){
                    ctx.beginPath();
                    ctx.moveTo(32.5, 32.5);   
                    ctx.arc(32.5, 32.5, 32.5, sRage, Math.PI * 2 * (i/360)+sRage);
                    ctx.fill();
                    setTimeout(djs, delay);
                } else {
                    after(ctx);
                }
            };
            djs();
        }
    }
};
//var p=0;
//var len=$(".jqm-round-wrap").length;
//for(p=0;p<len;p++){
//	var k=$("#jqm-round-sector"+p).next(".jqm-round-circle").find("p").html();
//		k=percentTofloat(k);
//	pie.run({
//		id:"jqm-round-sector"+p,
//		percent: k,
//		onBefore:function(ctx){
//			ctx.fillStyle = '#ee7e4d';
//			ctx.beginPath();
//			ctx.moveTo(32.5, 32.5);   
//			ctx.arc(32.5, 32.5, 32.5, 0, Math.PI * 2);
//			ctx.fill();
//		}
//	});
//}
function percentTofloat(km){
		if(km.substring(km.length-1,km.length)=="%"){
			km = km.substring(0,km.length-1);
			km = km/100;
		}
		return km;
	}