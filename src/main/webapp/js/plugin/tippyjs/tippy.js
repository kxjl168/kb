/**
 * 
 */

!function(t,e){"object"==typeof exports&&"undefined"!=typeof module?module.exports=e(require("popper.js")):"function"==typeof define&&define.amd?define(["popper.js"],e):(t=t||self).tippy=e(t.Popper)}(this,function(t){"use strict";t=t&&t.hasOwnProperty("default")?t.default:t;function e(){return(e=Object.assign||function(t){for(var e=1;e<arguments.length;e++){var a=arguments[e];for(var r in a)Object.prototype.hasOwnProperty.call(a,r)&&(t[r]=a[r])}return t}).apply(this,arguments)}var a="undefined"!=typeof window&&"undefined"!=typeof document,r=a?navigator.userAgent:"",n=/MSIE |Trident\//.test(r),i=/UCBrowser\//.test(r),o=a&&/iPhone|iPad|iPod/.test(navigator.platform)&&!window.MSStream,p={a11y:!0,allowHTML:!0,animateFill:!0,animation:"shift-away",appendTo:function(){return document.body},aria:"describedby",arrow:!1,arrowType:"sharp",boundary:"scrollParent",content:"",delay:0,distance:10,duration:[325,275],flip:!0,flipBehavior:"flip",flipOnUpdate:!1,followCursor:!1,hideOnClick:!0,ignoreAttributes:!1,inertia:!1,interactive:!1,interactiveBorder:2,interactiveDebounce:0,lazy:!0,maxWidth:350,multiple:!1,offset:0,onHidden:function(){},onHide:function(){},onMount:function(){},onShow:function(){},onShown:function(){},onTrigger:function(){},placement:"top",popperOptions:{},role:"tooltip",showOnInit:!1,size:"regular",sticky:!1,target:"",theme:"dark",touch:!0,touchHold:!1,trigger:"mouseenter focus",triggerTarget:null,updateDuration:0,wait:null,zIndex:9999},s=["arrow","arrowType","boundary","distance","flip","flipBehavior","flipOnUpdate","offset","placement","popperOptions"],l=a?Element.prototype:{},c=l.matches||l.matchesSelector||l.webkitMatchesSelector||l.mozMatchesSelector||l.msMatchesSelector;function d(t){return[].slice.call(t)}function f(t,e){return m(t,function(t){return c.call(t,e)})}function m(t,e){for(;t;){if(e(t))return t;t=t.parentElement}return null}var u={passive:!0},b=4,y="x-placement",v="x-out-of-boundaries",h="tippy-iOS",x="tippy-active",g="tippy-popper",w="tippy-tooltip",k="tippy-content",A="tippy-backdrop",E="tippy-arrow",X="tippy-roundarrow",Y=".".concat(g),C=".".concat(w),L=".".concat(k),T=".".concat(A),I=".".concat(E),S=".".concat(X),O=!1;function M(){O||(O=!0,o&&document.body.classList.add(h),window.performance&&document.addEventListener("mousemove",H))}var z=0;function H(){var t=performance.now();t-z<20&&(O=!1,document.removeEventListener("mousemove",H),o||document.body.classList.remove(h)),z=t}function V(){var t=document.activeElement;t&&t.blur&&t._tippy&&t.blur()}var _=Object.keys(p);function D(t,e){return{}.hasOwnProperty.call(t,e)}function P(t,e,a){if(Array.isArray(t)){var r=t[e];return null==r?a:r}return t}function q(t,e){var a;return function(){var r=this,n=arguments;clearTimeout(a),a=setTimeout(function(){return t.apply(r,n)},e)}}function N(t,e){return t&&t.modifiers&&t.modifiers[e]}function B(t,e){return t.indexOf(e)>-1}function F(t){return t instanceof Element}function j(t){return!(!t||!D(t,"isVirtual"))||F(t)}function U(t,e){return"function"==typeof t?t.apply(null,e):t}function W(t,e){t.filter(function(t){return"flip"===t.name})[0].enabled=e}function R(){return document.createElement("div")}function J(t,e){t.forEach(function(t){t&&(t.style.transitionDuration="".concat(e,"ms"))})}function G(t,e){t.forEach(function(t){t&&t.setAttribute("data-state",e)})}function K(t,a){var r=e({},a,{content:U(a.content,[t])},a.ignoreAttributes?{}:function(t){return _.reduce(function(e,a){var r=(t.getAttribute("data-tippy-".concat(a))||"").trim();if(!r)return e;if("content"===a)e[a]=r;else try{e[a]=JSON.parse(r)}catch(t){e[a]=r}return e},{})}(t));return(r.arrow||i)&&(r.animateFill=!1),r}function Q(t,e){Object.keys(t).forEach(function(t){if(!D(e,t))throw new Error("[tippy]: `".concat(t,"` is not a valid option"))})}function Z(t,e){t.innerHTML=F(e)?e.innerHTML:e}function $(t,e){if(F(e.content))Z(t,""),t.appendChild(e.content);else if("function"!=typeof e.content){t[e.allowHTML?"innerHTML":"textContent"]=e.content}}function tt(t){return{tooltip:t.querySelector(C),backdrop:t.querySelector(T),content:t.querySelector(L),arrow:t.querySelector(I)||t.querySelector(S)}}function et(t){t.setAttribute("data-inertia","")}function at(t){var e=R();return"round"===t?(e.className=X,Z(e,'<svg viewBox="0 0 18 7" xmlns="http://www.w3.org/2000/svg"><path d="M0 7s2.021-.015 5.253-4.218C6.584 1.051 7.797.007 9 0c1.203-.007 2.416 1.035 3.761 2.782C16.012 7.005 18 7 18 7H0z"/></svg>')):e.className=E,e}function rt(){var t=R();return t.className=A,t.setAttribute("data-state","hidden"),t}function nt(t,e){t.setAttribute("tabindex","-1"),e.setAttribute("data-interactive","")}function it(t,e,a){var r=i&&void 0!==document.body.style.webkitTransition?"webkitTransitionEnd":"transitionend";t[e+"EventListener"](r,a)}function ot(t){var e=t.getAttribute(y);return e?e.split("-")[0]:""}function pt(t,e,a){a.split(" ").forEach(function(a){t.classList[e](a+"-theme")})}function st(t,e){var a=R();a.className=g,a.id="tippy-".concat(t),a.style.zIndex=""+e.zIndex,e.role&&a.setAttribute("role",e.role);var r=R();r.className=w,r.style.maxWidth=e.maxWidth+("number"==typeof e.maxWidth?"px":""),r.setAttribute("data-size",e.size),r.setAttribute("data-animation",e.animation),r.setAttribute("data-state","hidden"),pt(r,"add",e.theme);var n=R();return n.className=k,n.setAttribute("data-state","hidden"),e.interactive&&nt(a,r),e.arrow&&r.appendChild(at(e.arrowType)),e.animateFill&&(r.appendChild(rt()),r.setAttribute("data-animatefill","")),e.inertia&&et(r),$(n,e),r.appendChild(n),a.appendChild(r),a}function lt(t,e,a){var r=tt(t),n=r.tooltip,i=r.content,o=r.backdrop,p=r.arrow;t.style.zIndex=""+a.zIndex,n.setAttribute("data-size",a.size),n.setAttribute("data-animation",a.animation),n.style.maxWidth=a.maxWidth+("number"==typeof a.maxWidth?"px":""),a.role?t.setAttribute("role",a.role):t.removeAttribute("role"),e.content!==a.content&&$(i,a),!e.animateFill&&a.animateFill?(n.appendChild(rt()),n.setAttribute("data-animatefill","")):e.animateFill&&!a.animateFill&&(n.removeChild(o),n.removeAttribute("data-animatefill")),!e.arrow&&a.arrow?n.appendChild(at(a.arrowType)):e.arrow&&!a.arrow&&n.removeChild(p),e.arrow&&a.arrow&&e.arrowType!==a.arrowType&&n.replaceChild(at(a.arrowType),p),!e.interactive&&a.interactive?nt(t,n):e.interactive&&!a.interactive&&function(t,e){t.removeAttribute("tabindex"),e.removeAttribute("data-interactive")}(t,n),!e.inertia&&a.inertia?et(n):e.inertia&&!a.inertia&&function(t){t.removeAttribute("data-inertia")}(n),e.theme!==a.theme&&(pt(n,"remove",e.theme),pt(n,"add",a.theme))}var ct=1;function dt(a,r){var i,o,l,h,g,w=K(a,r);if(!w.multiple&&a._tippy)return null;var k,A,E,X,C,L,T=!1,I=!1,S=!1,M=[],z=w.interactiveDebounce>0?q(vt,w.interactiveDebounce):vt,H=ct++,V=st(H,w),_=tt(V),j={id:H,reference:a,popper:V,popperChildren:_,popperInstance:null,props:w,state:{isEnabled:!0,isVisible:!1,isDestroyed:!1,isMounted:!1,isShown:!1},clearDelayTimeouts:Lt,set:Tt,setContent:function(t){Tt({content:t})},show:It,hide:St,enable:function(){j.state.isEnabled=!0},disable:function(){j.state.isEnabled=!1},destroy:function(t){if(j.state.isDestroyed)return;j.state.isMounted&&St(0);mt(),delete a._tippy;var e=j.props.target;e&&t&&F(a)&&d(a.querySelectorAll(e)).forEach(function(t){t._tippy&&t._tippy.destroy()});j.popperInstance&&j.popperInstance.destroy();j.state.isDestroyed=!0}};return a._tippy=j,V._tippy=j,ft(),w.lazy||Et(),w.showOnInit&&Xt(),!w.a11y||w.target||(!F(L=$())||c.call(L,"a[href],area[href],button,details,input,textarea,select,iframe,[tabindex]")&&!L.hasAttribute("disabled"))||$().setAttribute("tabindex","0"),V.addEventListener("mouseenter",function(t){j.props.interactive&&j.state.isVisible&&"mouseenter"===i&&Xt(t,!0)}),V.addEventListener("mouseleave",function(){j.props.interactive&&"mouseenter"===i&&document.addEventListener("mousemove",z)}),j;function R(){document.removeEventListener("mousemove",bt)}function Z(){document.body.removeEventListener("mouseleave",Yt),document.removeEventListener("mousemove",z)}function $(){return j.props.triggerTarget||a}function et(){document.addEventListener("click",Ct,!0)}function at(){return[j.popperChildren.tooltip,j.popperChildren.backdrop,j.popperChildren.content]}function rt(){return j.props.followCursor&&!O&&"focus"!==i}function nt(t,e){var a=j.popperChildren.tooltip;function r(t){t.target===a&&(it(a,"remove",r),e())}if(0===t)return e();it(a,"remove",X),it(a,"add",r),X=r}function pt(t,e){var a=arguments.length>2&&void 0!==arguments[2]&&arguments[2];$().addEventListener(t,e,a),M.push({eventType:t,handler:e,options:a})}function ft(){j.props.touchHold&&!j.props.target&&(pt("touchstart",yt,u),pt("touchend",ht,u)),j.props.trigger.trim().split(" ").forEach(function(t){if("manual"!==t)if(j.props.target)switch(t){case"mouseenter":pt("mouseover",gt),pt("mouseout",wt);break;case"focus":pt("focusin",gt),pt("focusout",wt);break;case"click":pt(t,gt)}else switch(pt(t,yt),t){case"mouseenter":pt("mouseleave",ht);break;case"focus":pt(n?"focusout":"blur",xt)}})}function mt(){M.forEach(function(t){var e=t.eventType,a=t.handler,r=t.options;$().removeEventListener(e,a,r)}),M=[]}function ut(t){return j.props.arrow?C[t]+("round"===j.props.arrowType?18:16):C[t]}function bt(t){var r=o=t,n=r.clientX,i=r.clientY;if(C){var p=a.getBoundingClientRect(),s=j.props.followCursor,l="horizontal"===s,c="vertical"===s,d=ot(V),f=B(["top","bottom"],d),u=B(["left","right"],d),b=e({},C);f&&(b.left=ut("left"),b.right=ut("right")),u&&(b.top=ut("top"),b.bottom=ut("bottom"));var y=f?Math.max(b.left,n):n,v=u?Math.max(b.top,i):i;f&&y>b.right&&(y=Math.min(n,window.innerWidth-b.right)),u&&v>b.bottom&&(v=Math.min(i,window.innerHeight-b.bottom)),!m(t.target,function(t){return t===a})&&j.props.interactive||(j.popperInstance.reference=e({},j.popperInstance.reference,{getBoundingClientRect:function(){return{width:0,height:0,top:l?p.top:v,bottom:l?p.bottom:v,left:c?p.left:y,right:c?p.right:y}},clientWidth:0,clientHeight:0}),j.popperInstance.scheduleUpdate()),"initial"===s&&j.state.isVisible&&R()}}function yt(t){j.state.isEnabled&&!kt(t)&&(j.state.isVisible||(i=t.type,t instanceof MouseEvent&&(o=t)),"click"===t.type&&!1!==j.props.hideOnClick&&j.state.isVisible?Yt():Xt(t))}function vt(t){var e=f(t.target,Y)===V,r=m(t.target,function(t){return t===a});e||r||function(t,e,a,r){if(!t)return!0;var n=a.clientX,i=a.clientY,o=r.interactiveBorder,p=r.distance,s=e.top-i>("top"===t?o+p:o),l=i-e.bottom>("bottom"===t?o+p:o),c=e.left-n>("left"===t?o+p:o),d=n-e.right>("right"===t?o+p:o);return s||l||c||d}(ot(V),V.getBoundingClientRect(),t,j.props)&&(Z(),Yt())}function ht(t){if(!kt(t))return j.props.interactive?(document.body.addEventListener("mouseleave",Yt),void document.addEventListener("mousemove",z)):void Yt()}function xt(t){t.target===$()&&(j.props.interactive&&t.relatedTarget&&V.contains(t.relatedTarget)||Yt())}function gt(t){f(t.target,j.props.target)&&Xt(t)}function wt(t){f(t.target,j.props.target)&&Yt()}function kt(t){var e="ontouchstart"in window,a=B(t.type,"touch"),r=j.props.touchHold;return e&&O&&r&&!a||O&&!r&&a}function At(){!S&&E&&(S=!0,function(t){t.offsetHeight}(V),E())}function Et(){var r=j.props.popperOptions,n=j.popperChildren,i=n.tooltip,o=n.arrow,p=N(r,"preventOverflow");function s(t){j.props.flip&&!j.props.flipOnUpdate&&(t.flipped&&(j.popperInstance.options.placement=t.placement),W(j.popperInstance.modifiers,!1)),i.setAttribute(y,t.placement),!1!==t.attributes[v]?i.setAttribute(v,""):i.removeAttribute(v),A&&A!==t.placement&&I&&(i.style.transition="none",requestAnimationFrame(function(){i.style.transition=""})),A=t.placement,I=j.state.isVisible;var a=ot(V),r=i.style;r.top=r.bottom=r.left=r.right="",r[a]=-(j.props.distance-10)+"px";var n=p&&void 0!==p.padding?p.padding:b,o="number"==typeof n,s=e({top:o?n:n.top,bottom:o?n:n.bottom,left:o?n:n.left,right:o?n:n.right},!o&&n);s[a]=o?n+j.props.distance:(n[a]||0)+j.props.distance,j.popperInstance.modifiers.filter(function(t){return"preventOverflow"===t.name})[0].padding=s,C=s}var l=e({eventsEnabled:!1,placement:j.props.placement},r,{modifiers:e({},r?r.modifiers:{},{preventOverflow:e({boundariesElement:j.props.boundary,padding:b},p),arrow:e({element:o,enabled:!!o},N(r,"arrow")),flip:e({enabled:j.props.flip,padding:j.props.distance+b,behavior:j.props.flipBehavior},N(r,"flip")),offset:e({offset:j.props.offset},N(r,"offset"))}),onCreate:function(t){At(),s(t),r&&r.onCreate&&r.onCreate(t)},onUpdate:function(t){At(),s(t),r&&r.onUpdate&&r.onUpdate(t)}});j.popperInstance=new t(a,V,l)}function Xt(t,a){if(Lt(),!j.state.isVisible){if(j.props.target)return function(t){if(t){var a=f(t.target,j.props.target);a&&!a._tippy&&dt(a,e({},j.props,{content:U(r.content,[a]),appendTo:r.appendTo,target:"",showOnInit:!0}))}}(t);if(T=!0,t&&!a&&j.props.onTrigger(j,t),j.props.wait)return j.props.wait(j,t);rt()&&!j.state.isMounted&&(j.popperInstance||Et(),document.addEventListener("mousemove",bt)),et();var n=P(j.props.delay,0,p.delay);n?l=setTimeout(function(){It()},n):It()}}function Yt(){if(Lt(),!j.state.isVisible)return R();T=!1;var t=P(j.props.delay,1,p.delay);t?h=setTimeout(function(){j.state.isVisible&&St()},t):g=requestAnimationFrame(function(){St()})}function Ct(t){if(!j.props.interactive||!V.contains(t.target)){if($().contains(t.target)){if(O)return;if(j.state.isVisible&&B(j.props.trigger,"click"))return}!0===j.props.hideOnClick&&(Lt(),St())}}function Lt(){clearTimeout(l),clearTimeout(h),cancelAnimationFrame(g)}function Tt(t){Q(t=t||{},p),mt();var r=j.props,n=K(a,e({},j.props,t,{ignoreAttributes:!0}));n.ignoreAttributes=D(t,"ignoreAttributes")?t.ignoreAttributes||!1:r.ignoreAttributes,j.props=n,ft(),Z(),z=q(vt,t.interactiveDebounce||0),lt(V,r,n),j.popperChildren=tt(V),j.popperInstance&&(s.some(function(e){return D(t,e)&&t[e]!==r[e]})?(j.popperInstance.destroy(),Et(),j.state.isVisible&&j.popperInstance.enableEventListeners(),j.props.followCursor&&o&&bt(o)):j.popperInstance.update())}function It(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:P(j.props.duration,0,p.duration[1]);if(!j.state.isDestroyed&&j.state.isEnabled&&(!O||j.props.touch)&&!$().hasAttribute("disabled")&&!1!==j.props.onShow(j)){et(),V.style.visibility="visible",j.state.isVisible=!0,j.props.interactive&&$().classList.add(x);var e=at();J(e.concat(V),0),E=function(){j.state.isVisible&&(rt()||j.popperInstance.update(),j.popperChildren.backdrop&&(j.popperChildren.content.style.transitionDelay=Math.round(t/12)+"ms"),j.props.sticky&&(J([V],n?0:j.props.updateDuration),function t(){j.popperInstance.scheduleUpdate(),j.state.isMounted?requestAnimationFrame(t):J([V],0)}()),J([V],j.props.updateDuration),J(e,t),G(e,"visible"),function(t,e){nt(t,e)}(t,function(){j.props.aria&&$().setAttribute("aria-".concat(j.props.aria),V.id),j.props.onShown(j),j.state.isShown=!0}))},function(){S=!1;var t=!(rt()||"initial"===j.props.followCursor&&O);j.popperInstance?(rt()||(j.popperInstance.scheduleUpdate(),t&&j.popperInstance.enableEventListeners()),W(j.popperInstance.modifiers,j.props.flip)):(Et(),t&&j.popperInstance.enableEventListeners()),j.popperInstance.reference=a;var e=j.popperChildren.arrow;rt()?(e&&(e.style.margin="0"),o&&bt(o)):e&&(e.style.margin=""),O&&o&&"initial"===j.props.followCursor&&(bt(o),e&&(e.style.margin="0"));var r=j.props.appendTo;(k="parent"===r?a.parentNode:U(r,[a])).contains(V)||(k.appendChild(V),j.props.onMount(j),j.state.isMounted=!0)}()}}function St(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:P(j.props.duration,1,p.duration[1]);if(!j.state.isDestroyed&&j.state.isEnabled&&!1!==j.props.onHide(j)){document.removeEventListener("click",Ct,!0),V.style.visibility="hidden",j.state.isVisible=!1,j.state.isShown=!1,I=!1,j.props.interactive&&$().classList.remove(x);var e=at();J(e,t),G(e,"hidden"),function(t,e){nt(t,function(){!j.state.isVisible&&k&&k.contains(V)&&e()})}(t,function(){T||R(),j.props.aria&&$().removeAttribute("aria-".concat(j.props.aria)),j.popperInstance.disableEventListeners(),j.popperInstance.options.placement=j.props.placement,k.removeChild(V),j.props.onHidden(j),j.state.isMounted=!1})}}}var ft=!1;function mt(t,a){Q(a||{},p),ft||(document.addEventListener("touchstart",M,u),window.addEventListener("blur",V),ft=!0);var r,n=e({},p,a);r=t,"[object Object]"!=={}.toString.call(r)||r.addEventListener||function(t){var e={isVirtual:!0,attributes:t.attributes||{},contains:function(){},setAttribute:function(e,a){t.attributes[e]=a},getAttribute:function(e){return t.attributes[e]},removeAttribute:function(e){delete t.attributes[e]},hasAttribute:function(e){return e in t.attributes},addEventListener:function(){},removeEventListener:function(){},classList:{classNames:{},add:function(e){t.classList.classNames[e]=!0},remove:function(e){delete t.classList.classNames[e]},contains:function(e){return e in t.classList.classNames}}};for(var a in e)t[a]=e[a]}(t);var i=function(t){if(j(t))return[t];if(t instanceof NodeList)return d(t);if(Array.isArray(t))return t;try{return d(document.querySelectorAll(t))}catch(t){return[]}}(t).reduce(function(t,e){var a=e&&dt(e,n);return a&&t.push(a),t},[]);return j(t)?i[0]:i}return mt.version="4.3.1",mt.defaults=p,mt.setDefaults=function(t){Object.keys(t).forEach(function(e){p[e]=t[e]})},mt.hideAll=function(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{},e=t.exclude,a=t.duration;d(document.querySelectorAll(Y)).forEach(function(t){var r,n=t._tippy;if(n){var i=!1;e&&(i=(r=e)._tippy&&!c.call(r,Y)?n.reference===e:t===e.popper),i||n.hide(a)}})},mt.group=function(t){var a=arguments.length>1&&void 0!==arguments[1]?arguments[1]:{},r=a.delay,n=void 0===r?t[0].props.delay:r,i=a.duration,o=void 0===i?0:i,p=!1;function s(t){p=t,f()}function l(e){e._originalProps.onShow(e),t.forEach(function(t){t.set({duration:o}),t.state.isVisible&&t.hide()}),s(!0)}function c(t){t._originalProps.onHide(t),s(!1)}function d(t){t._originalProps.onShown(t),t.set({duration:t._originalProps.duration})}function f(){t.forEach(function(t){t.set({onShow:l,onShown:d,onHide:c,delay:p?[0,Array.isArray(n)?n[1]:n]:n,duration:p?o:t._originalProps.duration})})}t.forEach(function(t){t._originalProps?t.set(t._originalProps):t._originalProps=e({},t.props)}),f()},a&&setTimeout(function(){d(document.querySelectorAll("[data-tippy]")).forEach(function(t){var e=t.getAttribute("data-tippy");e&&mt(t,{content:e})})}),function(t){if(a){var e=document.createElement("style");e.type="text/css",e.textContent=t,e.setAttribute("data-tippy-stylesheet","");var r=document.head,n=r.firstChild;n?r.insertBefore(e,n):r.appendChild(e)}}('.tippy-iOS{cursor:pointer!important;-webkit-tap-highlight-color:transparent}.tippy-popper{transition-timing-function:cubic-bezier(.165,.84,.44,1);max-width:calc(100% - 8px);pointer-events:none;outline:0}.tippy-popper[x-placement^=top] .tippy-backdrop{border-radius:40% 40% 0 0}.tippy-popper[x-placement^=top] .tippy-roundarrow{bottom:-7px;bottom:-6.5px;-webkit-transform-origin:50% 0;transform-origin:50% 0;margin:0 3px}.tippy-popper[x-placement^=top] .tippy-roundarrow svg{position:absolute;left:0;-webkit-transform:rotate(180deg);transform:rotate(180deg)}.tippy-popper[x-placement^=top] .tippy-arrow{border-top:8px solid #333;border-right:8px solid transparent;border-left:8px solid transparent;bottom:-7px;margin:0 3px;-webkit-transform-origin:50% 0;transform-origin:50% 0}.tippy-popper[x-placement^=top] .tippy-backdrop{-webkit-transform-origin:0 25%;transform-origin:0 25%}.tippy-popper[x-placement^=top] .tippy-backdrop[data-state=visible]{-webkit-transform:scale(1) translate(-50%,-55%);transform:scale(1) translate(-50%,-55%)}.tippy-popper[x-placement^=top] .tippy-backdrop[data-state=hidden]{-webkit-transform:scale(.2) translate(-50%,-45%);transform:scale(.2) translate(-50%,-45%);opacity:0}.tippy-popper[x-placement^=top] [data-animation=shift-toward][data-state=visible]{-webkit-transform:translateY(-10px);transform:translateY(-10px)}.tippy-popper[x-placement^=top] [data-animation=shift-toward][data-state=hidden]{opacity:0;-webkit-transform:translateY(-20px);transform:translateY(-20px)}.tippy-popper[x-placement^=top] [data-animation=perspective]{-webkit-transform-origin:bottom;transform-origin:bottom}.tippy-popper[x-placement^=top] [data-animation=perspective][data-state=visible]{-webkit-transform:perspective(700px) translateY(-10px) rotateX(0);transform:perspective(700px) translateY(-10px) rotateX(0)}.tippy-popper[x-placement^=top] [data-animation=perspective][data-state=hidden]{opacity:0;-webkit-transform:perspective(700px) translateY(0) rotateX(60deg);transform:perspective(700px) translateY(0) rotateX(60deg)}.tippy-popper[x-placement^=top] [data-animation=fade][data-state=visible]{-webkit-transform:translateY(-10px);transform:translateY(-10px)}.tippy-popper[x-placement^=top] [data-animation=fade][data-state=hidden]{opacity:0;-webkit-transform:translateY(-10px);transform:translateY(-10px)}.tippy-popper[x-placement^=top] [data-animation=shift-away][data-state=visible]{-webkit-transform:translateY(-10px);transform:translateY(-10px)}.tippy-popper[x-placement^=top] [data-animation=shift-away][data-state=hidden]{opacity:0;-webkit-transform:translateY(0);transform:translateY(0)}.tippy-popper[x-placement^=top] [data-animation=scale]{-webkit-transform-origin:bottom;transform-origin:bottom}.tippy-popper[x-placement^=top] [data-animation=scale][data-state=visible]{-webkit-transform:translateY(-10px) scale(1);transform:translateY(-10px) scale(1)}.tippy-popper[x-placement^=top] [data-animation=scale][data-state=hidden]{opacity:0;-webkit-transform:translateY(-10px) scale(.5);transform:translateY(-10px) scale(.5)}.tippy-popper[x-placement^=bottom] .tippy-backdrop{border-radius:0 0 30% 30%}.tippy-popper[x-placement^=bottom] .tippy-roundarrow{top:-7px;-webkit-transform-origin:50% 100%;transform-origin:50% 100%;margin:0 3px}.tippy-popper[x-placement^=bottom] .tippy-roundarrow svg{position:absolute;left:0;-webkit-transform:rotate(0);transform:rotate(0)}.tippy-popper[x-placement^=bottom] .tippy-arrow{border-bottom:8px solid #333;border-right:8px solid transparent;border-left:8px solid transparent;top:-7px;margin:0 3px;-webkit-transform-origin:50% 100%;transform-origin:50% 100%}.tippy-popper[x-placement^=bottom] .tippy-backdrop{-webkit-transform-origin:0 -50%;transform-origin:0 -50%}.tippy-popper[x-placement^=bottom] .tippy-backdrop[data-state=visible]{-webkit-transform:scale(1) translate(-50%,-45%);transform:scale(1) translate(-50%,-45%)}.tippy-popper[x-placement^=bottom] .tippy-backdrop[data-state=hidden]{-webkit-transform:scale(.2) translate(-50%);transform:scale(.2) translate(-50%);opacity:0}.tippy-popper[x-placement^=bottom] [data-animation=shift-toward][data-state=visible]{-webkit-transform:translateY(10px);transform:translateY(10px)}.tippy-popper[x-placement^=bottom] [data-animation=shift-toward][data-state=hidden]{opacity:0;-webkit-transform:translateY(20px);transform:translateY(20px)}.tippy-popper[x-placement^=bottom] [data-animation=perspective]{-webkit-transform-origin:top;transform-origin:top}.tippy-popper[x-placement^=bottom] [data-animation=perspective][data-state=visible]{-webkit-transform:perspective(700px) translateY(10px) rotateX(0);transform:perspective(700px) translateY(10px) rotateX(0)}.tippy-popper[x-placement^=bottom] [data-animation=perspective][data-state=hidden]{opacity:0;-webkit-transform:perspective(700px) translateY(0) rotateX(-60deg);transform:perspective(700px) translateY(0) rotateX(-60deg)}.tippy-popper[x-placement^=bottom] [data-animation=fade][data-state=visible]{-webkit-transform:translateY(10px);transform:translateY(10px)}.tippy-popper[x-placement^=bottom] [data-animation=fade][data-state=hidden]{opacity:0;-webkit-transform:translateY(10px);transform:translateY(10px)}.tippy-popper[x-placement^=bottom] [data-animation=shift-away][data-state=visible]{-webkit-transform:translateY(10px);transform:translateY(10px)}.tippy-popper[x-placement^=bottom] [data-animation=shift-away][data-state=hidden]{opacity:0;-webkit-transform:translateY(0);transform:translateY(0)}.tippy-popper[x-placement^=bottom] [data-animation=scale]{-webkit-transform-origin:top;transform-origin:top}.tippy-popper[x-placement^=bottom] [data-animation=scale][data-state=visible]{-webkit-transform:translateY(10px) scale(1);transform:translateY(10px) scale(1)}.tippy-popper[x-placement^=bottom] [data-animation=scale][data-state=hidden]{opacity:0;-webkit-transform:translateY(10px) scale(.5);transform:translateY(10px) scale(.5)}.tippy-popper[x-placement^=left] .tippy-backdrop{border-radius:50% 0 0 50%}.tippy-popper[x-placement^=left] .tippy-roundarrow{right:-12px;-webkit-transform-origin:33.33333333% 50%;transform-origin:33.33333333% 50%;margin:3px 0}.tippy-popper[x-placement^=left] .tippy-roundarrow svg{position:absolute;left:0;-webkit-transform:rotate(90deg);transform:rotate(90deg)}.tippy-popper[x-placement^=left] .tippy-arrow{border-left:8px solid #333;border-top:8px solid transparent;border-bottom:8px solid transparent;right:-7px;margin:3px 0;-webkit-transform-origin:0 50%;transform-origin:0 50%}.tippy-popper[x-placement^=left] .tippy-backdrop{-webkit-transform-origin:50% 0;transform-origin:50% 0}.tippy-popper[x-placement^=left] .tippy-backdrop[data-state=visible]{-webkit-transform:scale(1) translate(-50%,-50%);transform:scale(1) translate(-50%,-50%)}.tippy-popper[x-placement^=left] .tippy-backdrop[data-state=hidden]{-webkit-transform:scale(.2) translate(-75%,-50%);transform:scale(.2) translate(-75%,-50%);opacity:0}.tippy-popper[x-placement^=left] [data-animation=shift-toward][data-state=visible]{-webkit-transform:translateX(-10px);transform:translateX(-10px)}.tippy-popper[x-placement^=left] [data-animation=shift-toward][data-state=hidden]{opacity:0;-webkit-transform:translateX(-20px);transform:translateX(-20px)}.tippy-popper[x-placement^=left] [data-animation=perspective]{-webkit-transform-origin:right;transform-origin:right}.tippy-popper[x-placement^=left] [data-animation=perspective][data-state=visible]{-webkit-transform:perspective(700px) translateX(-10px) rotateY(0);transform:perspective(700px) translateX(-10px) rotateY(0)}.tippy-popper[x-placement^=left] [data-animation=perspective][data-state=hidden]{opacity:0;-webkit-transform:perspective(700px) translateX(0) rotateY(-60deg);transform:perspective(700px) translateX(0) rotateY(-60deg)}.tippy-popper[x-placement^=left] [data-animation=fade][data-state=visible]{-webkit-transform:translateX(-10px);transform:translateX(-10px)}.tippy-popper[x-placement^=left] [data-animation=fade][data-state=hidden]{opacity:0;-webkit-transform:translateX(-10px);transform:translateX(-10px)}.tippy-popper[x-placement^=left] [data-animation=shift-away][data-state=visible]{-webkit-transform:translateX(-10px);transform:translateX(-10px)}.tippy-popper[x-placement^=left] [data-animation=shift-away][data-state=hidden]{opacity:0;-webkit-transform:translateX(0);transform:translateX(0)}.tippy-popper[x-placement^=left] [data-animation=scale]{-webkit-transform-origin:right;transform-origin:right}.tippy-popper[x-placement^=left] [data-animation=scale][data-state=visible]{-webkit-transform:translateX(-10px) scale(1);transform:translateX(-10px) scale(1)}.tippy-popper[x-placement^=left] [data-animation=scale][data-state=hidden]{opacity:0;-webkit-transform:translateX(-10px) scale(.5);transform:translateX(-10px) scale(.5)}.tippy-popper[x-placement^=right] .tippy-backdrop{border-radius:0 50% 50% 0}.tippy-popper[x-placement^=right] .tippy-roundarrow{left:-12px;-webkit-transform-origin:66.66666666% 50%;transform-origin:66.66666666% 50%;margin:3px 0}.tippy-popper[x-placement^=right] .tippy-roundarrow svg{position:absolute;left:0;-webkit-transform:rotate(-90deg);transform:rotate(-90deg)}.tippy-popper[x-placement^=right] .tippy-arrow{border-right:8px solid #333;border-top:8px solid transparent;border-bottom:8px solid transparent;left:-7px;margin:3px 0;-webkit-transform-origin:100% 50%;transform-origin:100% 50%}.tippy-popper[x-placement^=right] .tippy-backdrop{-webkit-transform-origin:-50% 0;transform-origin:-50% 0}.tippy-popper[x-placement^=right] .tippy-backdrop[data-state=visible]{-webkit-transform:scale(1) translate(-50%,-50%);transform:scale(1) translate(-50%,-50%)}.tippy-popper[x-placement^=right] .tippy-backdrop[data-state=hidden]{-webkit-transform:scale(.2) translate(-25%,-50%);transform:scale(.2) translate(-25%,-50%);opacity:0}.tippy-popper[x-placement^=right] [data-animation=shift-toward][data-state=visible]{-webkit-transform:translateX(10px);transform:translateX(10px)}.tippy-popper[x-placement^=right] [data-animation=shift-toward][data-state=hidden]{opacity:0;-webkit-transform:translateX(20px);transform:translateX(20px)}.tippy-popper[x-placement^=right] [data-animation=perspective]{-webkit-transform-origin:left;transform-origin:left}.tippy-popper[x-placement^=right] [data-animation=perspective][data-state=visible]{-webkit-transform:perspective(700px) translateX(10px) rotateY(0);transform:perspective(700px) translateX(10px) rotateY(0)}.tippy-popper[x-placement^=right] [data-animation=perspective][data-state=hidden]{opacity:0;-webkit-transform:perspective(700px) translateX(0) rotateY(60deg);transform:perspective(700px) translateX(0) rotateY(60deg)}.tippy-popper[x-placement^=right] [data-animation=fade][data-state=visible]{-webkit-transform:translateX(10px);transform:translateX(10px)}.tippy-popper[x-placement^=right] [data-animation=fade][data-state=hidden]{opacity:0;-webkit-transform:translateX(10px);transform:translateX(10px)}.tippy-popper[x-placement^=right] [data-animation=shift-away][data-state=visible]{-webkit-transform:translateX(10px);transform:translateX(10px)}.tippy-popper[x-placement^=right] [data-animation=shift-away][data-state=hidden]{opacity:0;-webkit-transform:translateX(0);transform:translateX(0)}.tippy-popper[x-placement^=right] [data-animation=scale]{-webkit-transform-origin:left;transform-origin:left}.tippy-popper[x-placement^=right] [data-animation=scale][data-state=visible]{-webkit-transform:translateX(10px) scale(1);transform:translateX(10px) scale(1)}.tippy-popper[x-placement^=right] [data-animation=scale][data-state=hidden]{opacity:0;-webkit-transform:translateX(10px) scale(.5);transform:translateX(10px) scale(.5)}.tippy-tooltip{position:relative;color:#fff;border-radius:.25rem;font-size:.875rem;padding:.3125rem .5625rem;line-height:1.4;text-align:center;background-color:#333}.tippy-tooltip[data-size=small]{padding:.1875rem .375rem;font-size:.75rem}.tippy-tooltip[data-size=large]{padding:.375rem .75rem;font-size:1rem}.tippy-tooltip[data-animatefill]{overflow:hidden;background-color:transparent}.tippy-tooltip[data-interactive],.tippy-tooltip[data-interactive] path{pointer-events:auto}.tippy-tooltip[data-inertia][data-state=visible]{transition-timing-function:cubic-bezier(.54,1.5,.38,1.11)}.tippy-tooltip[data-inertia][data-state=hidden]{transition-timing-function:ease}.tippy-arrow,.tippy-roundarrow{position:absolute;width:0;height:0}.tippy-roundarrow{width:18px;height:7px;fill:#333;pointer-events:none}.tippy-backdrop{position:absolute;background-color:#333;border-radius:50%;width:calc(110% + 2rem);left:50%;top:50%;z-index:-1;transition:all cubic-bezier(.46,.1,.52,.98);-webkit-backface-visibility:hidden;backface-visibility:hidden}.tippy-backdrop:after{content:"";float:left;padding-top:100%}.tippy-backdrop+.tippy-content{transition-property:opacity;will-change:opacity}.tippy-backdrop+.tippy-content[data-state=visible]{opacity:1}.tippy-backdrop+.tippy-content[data-state=hidden]{opacity:0}'),mt});
//# sourceMappingURL=index.all.min.js.map