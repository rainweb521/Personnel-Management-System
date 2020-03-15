/* rem 适应*/
/*让文字和标签的大小随着屏幕的尺寸做变话 等比缩放*/
var html = document.getElementsByTagName('html')[0];
/*取到屏幕的宽度*/
var width = window.innerWidth;
/* 640 100  320 50 */
if (width > 1080) {
    width = 1080;
}
else if (width < 320 ) {
    width = 320 ;
}
var fontSize = 100/1080*width;
/*设置fontsize*/

html.style.fontSize = fontSize +'px';
window.onresize = function(){
    var html = document.getElementsByTagName('html')[0];
	/*取到屏幕的宽度*/
    var width = window.innerWidth;
    if (width > 1080) {
        width = 1080;
    }
    else if (width < 320 ) {
        width = 320 ;
    }
	/* 640 100  320 50 */
    var fontSize = 100/1080 * width;
	/*设置fontsize*/
    html.style.fontSize = fontSize +'px';
}