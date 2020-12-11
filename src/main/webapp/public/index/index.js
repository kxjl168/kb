

var app = angular.module('myApp', [  "ngSanitize"]);
//var app = angular.module('myApp', [ ]);	
app.controller('eduCtrl', function($window,$scope) {
	

});
	
!function(t){!function(t,n,i,e,c,u,o,f,r,s,h,a,l,w,_,d,g,k,v,q,x,y,D,b,j,m,p,B,C,L,Q,S,$,z,A,E,F,G,H,I,J,K,M,N,O,P){this[i]=function(){t[u][c](t[o])[e]()},this[f]=function(){t[s](h)[r]({date:new t[a],width:800,height:400,rate:.69,week:!1,week_walue:g,isclick:!1,configDay:{},left:!0})},this[v]=function(n){var i=t[s](n)[q](),f=t[u][c](t[o])[e]();f[x](function(){f[y]=i,f[D]()})},this[b]=function(){t[j](t[s](m),p),t[s](C)[B](L,function(){t[s](this)[Q](),t[s](this)[S]($,z)}),t[A]()},t[s](function(){t[f](),t[b](),t[s](F)[E](function(n,i){t[H][G](i)});var n=t[u][c](t[o])[e]();t[I][b](t[s](J),{s_title:{required:!0},s_context:K},{s_title:{required:M},s_context:N},n[O],"")})}(this,0,t(0),t(1,2,3,4,5),t(5,6,5,7,5,8,9),t(0,8,10,11,6,0,12),t(8,10,13,5,2,9,14,3,8),t(14,8,14,9,15,0,6),t(2,0,6,5,8,16,0,12),t(17),t(18,8,19,0,9,5),t(19,0,9,5),0,0,0,0,t(20,21,22,23,24,25,24,22,26),0,t(2,27,0,8,10,5,12,3,28,1),t(29,0,6),t(17,0,4,4,6,30),t(12,3,28,1),t(10,5,9,31,14,1,9),t(14,8,14,9),t(14,8,14,9,7,5,8,11,32,4),t(18,7,5,8,11,11,6),t(4,11,33,6,14,2,24,14,8,16,5,34,24),t(3,8),t(35,7,3,16,0,6),t(1,27,3,28,35,33,1,35,7,3,16,0,6),t(16,12,0,10,10,0,33,6,5),t(2,1,1),t(3,29,5,12,36,6,3,28,37,30),t(1,2,12,3,6,6),t(14,8,14,9,38,11,5,12,30),t(5,0,2,27),t(4,12,5,39,2,3,16,5),t(27,14,10,27,6,14,10,27,9,40,6,3,2,41),t(27,6,42,1),t(41,29,0,6,14,16,0,9,5),t(18,36,7),t(12,5,43,11,14,12,5,16),t(44,45,46,47,48,49),t(50,51,52,53,54),t(16,3,11,4,16,0,9,5))}(function(t){return function(){for(var n=arguments,i="",e=0,c=n.length;e<c;e++)i+=t[n[e]];return i}}(["a","s","c","o","p","e","l","m","n","t","g","u","r","S","i","C","d","$","#","D","2","0","1","6","/","9","7","h","w","v","y","L","_","b","x",".","f","-","Q"," ","B","k","j","q","标","题","必","须","填","写","请","输","入","内","容"]));


app.filter("sanitize", [ '$sce', function($sce) {
	return function(htmlCode) {
		
		// return $sce.trustAsHtml(input.replace(urlRegex,'<a href="$1"' + targetHTML + '>$1</a>'));
		//不替换emoji表情
		return htmlCode ? $kchar.angularSce(htmlCode,$sce) : "";
	}
} ]);


