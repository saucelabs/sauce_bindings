"use strict";(self.webpackChunk=self.webpackChunk||[]).push([[608],{1875:(e,t)=>{Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;t.default=function(){return null}},5066:(e,t,a)=>{var n=a(4836).default;Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var l=n(a(7294)),r=n(a(6010)),u=a(6700),o=n(a(944)),d=a(9052),i=n(a(5806));var c=function(){var e,t=(0,o.default)(),a=t.isAnnouncementBarClosed,n=t.closeAnnouncementBar,c=(0,u.useThemeConfig)().announcementBar;if(!c)return null;var f=c.content,s=c.backgroundColor,m=c.textColor,v=c.isCloseable;return!f||v&&a?null:l.default.createElement("div",{className:i.default.announcementBar,style:{backgroundColor:s,color:m},role:"banner"},l.default.createElement("div",{className:(0,r.default)(i.default.announcementBarContent,(e={},e[i.default.announcementBarCloseable]=v,e)),dangerouslySetInnerHTML:{__html:f}}),v?l.default.createElement("button",{type:"button",className:i.default.announcementBarClose,onClick:n,"aria-label":(0,d.translate)({id:"theme.AnnouncementBar.closeButtonAriaLabel",message:"Close",description:"The ARIA label for close button of announcement bar"})},l.default.createElement("span",{"aria-hidden":"true"},"\xd7")):null)};t.default=c},3264:(e,t,a)=>{var n=a(4836).default;Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var l=n(a(7462)),r=n(a(3366)),u=n(a(7294)),o=["width","height"],d=function(e){var t=e.width,a=void 0===t?20:t,n=e.height,d=void 0===n?20:n,i=(0,r.default)(e,o);return u.default.createElement("svg",(0,l.default)({xmlns:"http://www.w3.org/2000/svg",viewBox:"0 0 20 20",width:a,height:d},i),u.default.createElement("path",{fill:"currentColor",d:"M19.753 10.909c-.624-1.707-2.366-2.726-4.661-2.726-.09 0-.176.002-.262.006l-.016-2.063 3.525-.607c.115-.019.133-.119.109-.231-.023-.111-.167-.883-.188-.976-.027-.131-.102-.127-.207-.109-.104.018-3.25.461-3.25.461l-.013-2.078c-.001-.125-.069-.158-.194-.156l-1.025.016c-.105.002-.164.049-.162.148l.033 2.307s-3.061.527-3.144.543c-.084.014-.17.053-.151.143.019.09.19 1.094.208 1.172.018.08.072.129.188.107l2.924-.504.035 2.018c-1.077.281-1.801.824-2.256 1.303-.768.807-1.207 1.887-1.207 2.963 0 1.586.971 2.529 2.328 2.695 3.162.387 5.119-3.06 5.769-4.715 1.097 1.506.256 4.354-2.094 5.98-.043.029-.098.129-.033.207l.619.756c.08.096.206.059.256.023 2.51-1.73 3.661-4.515 2.869-6.683zm-7.386 3.188c-.966-.121-.944-.914-.944-1.453 0-.773.327-1.58.876-2.156a3.21 3.21 0 011.229-.799l.082 4.277a2.773 2.773 0 01-1.243.131zm2.427-.553l.046-4.109c.084-.004.166-.01.252-.01.773 0 1.494.145 1.885.361.391.217-1.023 2.713-2.183 3.758zm-8.95-7.668a.196.196 0 00-.196-.145h-1.95a.194.194 0 00-.194.144L.008 16.916c-.017.051-.011.076.062.076h1.733c.075 0 .099-.023.114-.072l1.008-3.318h3.496l1.008 3.318c.016.049.039.072.113.072h1.734c.072 0 .078-.025.062-.076-.014-.05-3.083-9.741-3.494-11.04zm-2.618 6.318l1.447-5.25 1.447 5.25H3.226z"}))};t.default=d},4478:(e,t,a)=>{var n=a(4836).default;Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var l=n(a(7462)),r=n(a(3366)),u=n(a(7294)),o=["width","height","className"],d=function(e){var t=e.width,a=void 0===t?30:t,n=e.height,d=void 0===n?30:n,i=e.className,c=(0,r.default)(e,o);return u.default.createElement("svg",(0,l.default)({"aria-label":"Menu",className:i,width:a,height:d,viewBox:"0 0 30 30",role:"img",focusable:"false"},c),u.default.createElement("title",null,"Menu"),u.default.createElement("path",{stroke:"currentColor",strokeLinecap:"round",strokeMiterlimit:"10",strokeWidth:"2",d:"M4 7h22M4 15h22M4 23h22"}))};t.default=d},8532:(e,t,a)=>{var n=a(4836).default;Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var l=n(a(7294)),r=n(a(6010)),u=n(a(2604)),o=n(a(5066)),d=n(a(3008)),i=n(a(6463)),c=n(a(2274)),f=n(a(411)),s=n(a(8245)),m=a(6700);a(9873);var v=function(e){var t=e.children,a=e.noFooter,n=e.wrapperClassName,v=e.pageClassName;return(0,s.default)(),l.default.createElement(c.default,null,l.default.createElement(f.default,e),l.default.createElement(u.default,null),l.default.createElement(o.default,null),l.default.createElement(d.default,null),l.default.createElement("div",{className:(0,r.default)(m.ThemeClassNames.wrapper.main,n,v)},t),!a&&l.default.createElement(i.default,null))};t.default=v},411:(e,t,a)=>{var n=a(4836).default;Object.defineProperty(t,"__esModule",{value:!0}),t.default=function(e){var t=(0,o.default)(),a=t.siteConfig,n=a.favicon,s=a.themeConfig.metadatas,h=t.i18n,b=h.currentLocale,p=h.localeConfigs,g=e.title,E=e.description,_=e.image,y=e.keywords,k=e.searchMetadatas,w=(0,d.default)(n),C=(0,f.useTitleFormatter)(g),N=b,P=p[b].direction;return r.default.createElement(r.default.Fragment,null,r.default.createElement(u.default,null,r.default.createElement("html",{lang:N,dir:P}),n&&r.default.createElement("link",{rel:"shortcut icon",href:w}),r.default.createElement("title",null,C),r.default.createElement("meta",{property:"og:title",content:C})),r.default.createElement(c.default,{description:E,keywords:y,image:_}),r.default.createElement(v,null),r.default.createElement(m,null),r.default.createElement(i.default,(0,l.default)({tag:f.DEFAULT_SEARCH_TAG,locale:b},k)),r.default.createElement(u.default,null,s.map((function(e,t){return r.default.createElement("meta",(0,l.default)({key:"metadata_"+t},e))}))))};var l=n(a(7462)),r=n(a(7294)),u=n(a(5742)),o=n(a(2263)),d=n(a(4996)),i=n(a(4246)),c=n(a(1217)),f=a(6700),s=a(8143);function m(){var e=(0,o.default)().i18n,t=e.defaultLocale,a=e.locales,n=(0,f.useAlternatePageUtils)();return r.default.createElement(u.default,null,a.map((function(e){return r.default.createElement("link",{key:e,rel:"alternate",href:n.createUrl({locale:e,fullyQualified:!0}),hrefLang:e})})),r.default.createElement("link",{rel:"alternate",href:n.createUrl({locale:t,fullyQualified:!0}),hrefLang:"x-default"}))}function v(e){var t=e.permalink,a=(0,o.default)().siteConfig.url,n=function(){var e=(0,o.default)().siteConfig.url,t=(0,s.useLocation)().pathname;return e+(0,d.default)(t)}(),l=t?""+a+t:n;return r.default.createElement(u.default,null,r.default.createElement("meta",{property:"og:url",content:l}),r.default.createElement("link",{rel:"canonical",href:l}))}},2274:(e,t,a)=>{var n=a(4836).default;Object.defineProperty(t,"__esModule",{value:!0}),t.default=function(e){var t=e.children;return l.default.createElement(r.default,null,l.default.createElement(u.default,null,l.default.createElement(o.DocsPreferredVersionContextProvider,null,t)))};var l=n(a(7294)),r=n(a(6417)),u=n(a(1073)),o=a(6700)},5537:(e,t,a)=>{var n=a(4836).default;Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var l=n(a(7462)),r=n(a(3366)),u=n(a(7294)),o=n(a(3692)),d=n(a(2388)),i=n(a(4996)),c=n(a(2263)),f=a(6700),s=["imageClassName","titleClassName"],m=function(e){var t=(0,c.default)().isClient,a=(0,f.useThemeConfig)().navbar,n=a.title,m=a.logo,v=void 0===m?{src:""}:m,h=e.imageClassName,b=e.titleClassName,p=(0,r.default)(e,s),g=(0,i.default)(v.href||"/"),E={light:(0,i.default)(v.src),dark:(0,i.default)(v.srcDark||v.src)};return u.default.createElement(o.default,(0,l.default)({to:g},p,v.target&&{target:v.target}),v.src&&u.default.createElement(d.default,{key:t,className:h,sources:E,alt:v.alt||n||"Logo"}),null!=n&&u.default.createElement("strong",{className:b},n))};t.default=m},3008:(e,t,a)=>{var n=a(5263).default,l=a(4836).default;Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var r=l(a(7462)),u=n(a(7294)),o=l(a(6010)),d=l(a(1081)),i=l(a(8037)),c=l(a(5350)),f=a(6700),s=l(a(5662)),m=l(a(1839)),v=n(a(3783)),h=l(a(1442)),b=l(a(5537)),p=l(a(4478)),g=l(a(1805)),E="right";var _=function(){var e,t=(0,f.useThemeConfig)(),a=t.navbar,n=a.items,l=a.hideOnScroll,_=a.style,y=t.colorMode.disableSwitch,k=(0,u.useState)(!1),w=k[0],C=k[1],N=(0,c.default)(),P=N.isDarkTheme,M=N.setLightTheme,O=N.setDarkTheme,S=(0,s.default)(l),L=S.navbarRef,I=S.isNavbarVisible;(0,m.default)(w);var A=(0,u.useCallback)((function(){C(!0)}),[C]),T=(0,u.useCallback)((function(){C(!1)}),[C]),B=(0,u.useCallback)((function(e){return e.target.checked?O():M()}),[M,O]),j=(0,v.default)();(0,u.useEffect)((function(){j===v.windowSizes.desktop&&C(!1)}),[j]);var D=n.some((function(e){return"search"===e.type})),x=function(e){return{leftItems:e.filter((function(e){var t;return"left"===(null!=(t=e.position)?t:E)})),rightItems:e.filter((function(e){var t;return"right"===(null!=(t=e.position)?t:E)}))}}(n),V=x.leftItems,R=x.rightItems;return u.default.createElement("nav",{ref:L,className:(0,o.default)("navbar","navbar--fixed-top",(e={"navbar--dark":"dark"===_,"navbar--primary":"primary"===_,"navbar-sidebar--show":w},e[g.default.navbarHideable]=l,e[g.default.navbarHidden]=l&&!I,e))},u.default.createElement("div",{className:"navbar__inner"},u.default.createElement("div",{className:"navbar__items"},null!=n&&0!==n.length&&u.default.createElement("button",{"aria-label":"Navigation bar toggle",className:"navbar__toggle",type:"button",tabIndex:0,onClick:A,onKeyDown:A},u.default.createElement(p.default,null)),u.default.createElement(b.default,{className:"navbar__brand",imageClassName:"navbar__logo",titleClassName:(0,o.default)("navbar__title")}),V.map((function(e,t){return u.default.createElement(h.default,(0,r.default)({},e,{key:t}))}))),u.default.createElement("div",{className:"navbar__items navbar__items--right"},R.map((function(e,t){return u.default.createElement(h.default,(0,r.default)({},e,{key:t}))})),!y&&u.default.createElement(i.default,{className:g.default.displayOnlyInLargeViewport,checked:P,onChange:B}),!D&&u.default.createElement(d.default,null))),u.default.createElement("div",{role:"presentation",className:"navbar-sidebar__backdrop",onClick:T}),u.default.createElement("div",{className:"navbar-sidebar"},u.default.createElement("div",{className:"navbar-sidebar__brand"},u.default.createElement(b.default,{className:"navbar__brand",imageClassName:"navbar__logo",titleClassName:"navbar__title",onClick:T}),!y&&w&&u.default.createElement(i.default,{checked:P,onChange:B})),u.default.createElement("div",{className:"navbar-sidebar__items"},u.default.createElement("div",{className:"menu"},u.default.createElement("ul",{className:"menu__list"},n.map((function(e,t){return u.default.createElement(h.default,(0,r.default)({mobile:!0},e,{onClick:T,key:t}))})))))))};t.default=_},5525:(e,t,a)=>{var n=a(4836).default,l=a(5263).default;Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var r=n(a(7462)),u=n(a(3366)),o=l(a(7294)),d=n(a(6010)),i=n(a(3692)),c=n(a(4996)),f=a(8143),s=a(6700),m=["activeBasePath","activeBaseRegex","to","href","label","activeClassName","prependBaseUrlToHref"],v=["items","position","className"],h=["className"],b=["items","className","position"],p=["className"],g=["mobile"];function E(e){var t=e.activeBasePath,a=e.activeBaseRegex,n=e.to,l=e.href,d=e.label,f=e.activeClassName,s=void 0===f?"navbar__link--active":f,v=e.prependBaseUrlToHref,h=(0,u.default)(e,m),b=(0,c.default)(n),p=(0,c.default)(t),g=(0,c.default)(l,{forcePrependBaseUrl:!0});return o.default.createElement(i.default,(0,r.default)({},l?{href:v?g:l}:Object.assign({isNavLink:!0,activeClassName:s,to:b},t||a?{isActive:function(e,t){return a?new RegExp(a).test(t.pathname):t.pathname.startsWith(p)}}:null),h),d)}function _(e){var t,a=e.items,n=e.position,l=e.className,i=(0,u.default)(e,v),c=(0,o.useRef)(null),f=(0,o.useRef)(null),s=(0,o.useState)(!1),m=s[0],b=s[1];(0,o.useEffect)((function(){var e=function(e){c.current&&!c.current.contains(e.target)&&b(!1)};return document.addEventListener("mousedown",e),document.addEventListener("touchstart",e),function(){document.removeEventListener("mousedown",e),document.removeEventListener("touchstart",e)}}),[c]);var p=function(e,t){return void 0===t&&(t=!1),(0,d.default)({"navbar__item navbar__link":!t,dropdown__link:t},e)};return a?o.default.createElement("div",{ref:c,className:(0,d.default)("navbar__item","dropdown","dropdown--hoverable",{"dropdown--left":"left"===n,"dropdown--right":"right"===n,"dropdown--show":m})},o.default.createElement(E,(0,r.default)({className:p(l)},i,{onClick:i.to?void 0:function(e){return e.preventDefault()},onKeyDown:function(e){"Enter"===e.key&&(e.preventDefault(),b(!m))}}),null!=(t=i.children)?t:i.label),o.default.createElement("ul",{ref:f,className:"dropdown__menu"},a.map((function(e,t){var n=e.className,l=(0,u.default)(e,h);return o.default.createElement("li",{key:t},o.default.createElement(E,(0,r.default)({onKeyDown:function(e){if(t===a.length-1&&"Tab"===e.key){e.preventDefault(),b(!1);var n=c.current.nextElementSibling;n&&n.focus()}},activeClassName:"dropdown__link--active",className:p(n,!0)},l)))})))):o.default.createElement(E,(0,r.default)({className:p(l)},i))}function y(e){var t,a,n,l=e.items,i=e.className,c=(e.position,(0,u.default)(e,b)),m=(0,o.useRef)(null),v=(0,f.useLocation)().pathname,h=(0,o.useState)((function(){var e;return null==(e=!(null!=l&&l.some((function(e){return(0,s.isSamePath)(e.to,v)}))))||e})),g=h[0],_=h[1],y=function(e,t){return void 0===t&&(t=!1),(0,d.default)("menu__link",{"menu__link--sublist":t},e)};if(!l)return o.default.createElement("li",{className:"menu__list-item"},o.default.createElement(E,(0,r.default)({className:y(i)},c)));var k=null!=(t=m.current)&&t.scrollHeight?(null==(a=m.current)?void 0:a.scrollHeight)+"px":void 0;return o.default.createElement("li",{className:(0,d.default)("menu__list-item",{"menu__list-item--collapsed":g})},o.default.createElement(E,(0,r.default)({role:"button",className:y(i,!0)},c,{onClick:function(e){e.preventDefault(),_((function(e){return!e}))}}),null!=(n=c.children)?n:c.label),o.default.createElement("ul",{className:"menu__list",ref:m,style:{height:g?void 0:k}},l.map((function(e,t){var a=e.className,n=(0,u.default)(e,p);return o.default.createElement("li",{className:"menu__list-item",key:t},o.default.createElement(E,(0,r.default)({activeClassName:"menu__link--active",className:y(a)},n,{onClick:c.onClick})))}))))}var k=function(e){var t=e.mobile,a=void 0!==t&&t,n=(0,u.default)(e,g),l=a?y:_;return o.default.createElement(l,n)};t.default=k},6400:(e,t,a)=>{var n=a(4836).default;t.Z=function(e){var t,a,n=e.docId,s=e.activeSidebarClassName,m=e.label,v=e.docsPluginId,h=(0,r.default)(e,f),b=(0,d.useActiveDocContext)(v),p=b.activeVersion,g=b.activeDoc,E=(0,c.useDocsPreferredVersion)(v).preferredVersion,_=(0,d.useLatestVersion)(v),y=null!=(t=null!=p?p:E)?t:_,k=y.docs.find((function(e){return e.id===n}));if(!k){var w=y.docs.map((function(e){return e.id})).join("\n- ");throw new Error("DocNavbarItem: couldn't find any doc with id="+n+" in version "+y.name+".\nAvailable docIds=\n- "+w)}return u.default.createElement(o.default,(0,l.default)({exact:!0},h,{className:(0,i.default)(h.className,(a={},a[s]=g&&g.sidebar===k.sidebar,a)),label:null!=m?m:k.id,to:k.path}))};var l=n(a(7462)),r=n(a(3366)),u=n(a(7294)),o=n(a(5525)),d=a(907),i=n(a(6010)),c=a(6700),f=["docId","activeSidebarClassName","label","docsPluginId"]},9308:(e,t,a)=>{var n=a(4836).default;t.Z=function(e){var t,a,n=e.mobile,s=e.docsPluginId,m=e.dropdownActiveClassDisabled,v=e.dropdownItemsBefore,h=e.dropdownItemsAfter,b=(0,r.default)(e,c),p=(0,d.useActiveDocContext)(s),g=(0,d.useVersions)(s),E=(0,d.useLatestVersion)(s),_=(0,i.useDocsPreferredVersion)(s),y=_.preferredVersion,k=_.savePreferredVersionName;var w=null!=(t=null!=(a=p.activeVersion)?a:y)?t:E,C=n?"Versions":w.label,N=n?void 0:f(w).path;return u.default.createElement(o.default,(0,l.default)({},b,{mobile:n,label:C,to:N,items:function(){var e=g.map((function(e){var t=(null==p?void 0:p.alternateDocVersions[e.name])||f(e);return{isNavLink:!0,label:e.label,to:t.path,isActive:function(){return e===(null==p?void 0:p.activeVersion)},onClick:function(){k(e.name)}}})),t=[].concat(v,e,h);if(!(t.length<=1))return t}(),isActive:m?function(){return!1}:void 0}))};var l=n(a(7462)),r=n(a(3366)),u=n(a(7294)),o=n(a(5525)),d=a(907),i=a(6700),c=["mobile","docsPluginId","dropdownActiveClassDisabled","dropdownItemsBefore","dropdownItemsAfter"],f=function(e){return e.docs.find((function(t){return t.id===e.mainDocId}))}},7250:(e,t,a)=>{var n=a(4836).default;t.Z=function(e){var t,a=e.label,n=e.to,s=e.docsPluginId,m=(0,r.default)(e,c),v=(0,d.useActiveVersion)(s),h=(0,i.useDocsPreferredVersion)(s).preferredVersion,b=(0,d.useLatestVersion)(s),p=null!=(t=null!=v?v:h)?t:b,g=null!=a?a:p.label,E=null!=n?n:f(p).path;return u.default.createElement(o.default,(0,l.default)({},m,{label:g,to:E}))};var l=n(a(7462)),r=n(a(3366)),u=n(a(7294)),o=n(a(5525)),d=a(907),i=a(6700),c=["label","to","docsPluginId"],f=function(e){return e.docs.find((function(t){return t.id===e.mainDocId}))}},5958:(e,t,a)=>{var n=a(4836).default;Object.defineProperty(t,"__esModule",{value:!0}),t.default=function(e){var t=e.mobile,a=e.dropdownItemsBefore,n=e.dropdownItemsAfter,s=(0,r.default)(e,f),m=(0,i.default)().i18n,v=m.currentLocale,h=m.locales,b=m.localeConfigs,p=(0,c.useAlternatePageUtils)();function g(e){return b[e].label}var E=h.map((function(e){var t="pathname://"+p.createUrl({locale:e,fullyQualified:!1});return{isNavLink:!0,label:g(e),to:t,target:"_self",autoAddBaseUrl:!1,className:e===v?"dropdown__link--active":"",style:{textTransform:"capitalize"}}})),_=[].concat(a,E,n),y=t?"Languages":g(v);return u.default.createElement(o.default,(0,l.default)({},s,{href:"#",mobile:t,label:u.default.createElement("span",null,u.default.createElement(d.default,{style:{verticalAlign:"text-bottom",marginRight:5}}),u.default.createElement("span",null,y)),items:_}))};var l=n(a(7462)),r=n(a(3366)),u=n(a(7294)),o=n(a(5525)),d=n(a(3264)),i=n(a(2263)),c=a(6700),f=["mobile","dropdownItemsBefore","dropdownItemsAfter"]},1093:(e,t,a)=>{var n=a(4836).default;Object.defineProperty(t,"__esModule",{value:!0}),t.default=function(e){if(e.mobile)return null;return l.default.createElement("div",{className:u.default.searchWrapper},l.default.createElement(r.default,null))};var l=n(a(7294)),r=n(a(1081)),u=n(a(1594))},1442:(e,t,a)=>{var n=a(4836).default;Object.defineProperty(t,"__esModule",{value:!0}),t.default=function(e){var t=e.type,a=(0,l.default)(e,i),n=f(t);return r.default.createElement(n,a)};var l=n(a(3366)),r=n(a(7294)),u=n(a(5525)),o=n(a(5958)),d=n(a(1093)),i=["type"],c={default:function(){return u.default},localeDropdown:function(){return o.default},search:function(){return d.default},docsVersion:function(){return a(7250).Z},docsVersionDropdown:function(){return a(9308).Z},doc:function(){return a(6400).Z}},f=function(e){void 0===e&&(e="default");var t=c[e];if(!t)throw new Error("No NavbarItem component found for type="+e+".");return t()}},4608:(e,t,a)=>{var n=a(4836).default;Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var l=n(a(7294)),r=n(a(8532)),u=n(a(9052));var o=function(){return l.default.createElement(r.default,{title:"Page Not Found"},l.default.createElement("main",{className:"container margin-vert--xl"},l.default.createElement("div",{className:"row"},l.default.createElement("div",{className:"col col--6 col--offset-3"},l.default.createElement("h1",{className:"hero__title"},l.default.createElement(u.default,{id:"theme.NotFound.title",description:"The title of the 404 page"},"Page Not Found")),l.default.createElement("p",null,l.default.createElement(u.default,{id:"theme.NotFound.p1",description:"The first paragraph of the 404 page"},"We could not find what you were looking for.")),l.default.createElement("p",null,l.default.createElement(u.default,{id:"theme.NotFound.p2",description:"The 2nd paragraph of the 404 page"},"Please contact the owner of the site that linked you to the original URL and let them know their link is broken."))))))};t.default=o},1081:(e,t,a)=>{var n=a(4836).default;Object.defineProperty(t,"__esModule",{value:!0}),Object.defineProperty(t,"default",{enumerable:!0,get:function(){return l.default}});var l=n(a(1875))},4246:(e,t,a)=>{var n=a(4836).default;Object.defineProperty(t,"__esModule",{value:!0}),t.default=function(e){var t=e.locale,a=e.version,n=e.tag;return l.default.createElement(r.default,null,t&&l.default.createElement("meta",{name:"docusaurus_locale",content:""+t}),a&&l.default.createElement("meta",{name:"docusaurus_version",content:a}),n&&l.default.createElement("meta",{name:"docusaurus_tag",content:n}))};var l=n(a(7294)),r=n(a(5742))},2604:(e,t,a)=>{var n=a(4836).default,l=a(5263).default;Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var r=l(a(7294)),u=n(a(9052)),o=a(8143),d=n(a(126));function i(e){e.setAttribute("tabindex","-1"),e.focus(),e.removeAttribute("tabindex")}var c=function(){var e=(0,r.useRef)(null),t=(0,o.useLocation)();return(0,r.useEffect)((function(){!t.hash&&e.current&&i(e.current)}),[t.pathname]),r.default.createElement("div",{ref:e},r.default.createElement("a",{href:"#main",className:d.default.skipToContent,onClick:function(e){e.preventDefault();var t=document.querySelector("main:first-of-type")||document.querySelector(".main-wrapper");t&&i(t)}},r.default.createElement(u.default,{id:"theme.common.skipToMainContent",description:"The skip to content label used for accessibility, allowing to rapidly navigate to main content with keyboard tab/enter navigation"},"Skip to main content")))};t.default=c},2924:(e,t,a)=>{var n=a(4836).default;Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var l=n(a(7294)).default.createContext(void 0);t.default=l},6417:(e,t,a)=>{var n=a(4836).default;Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var l=n(a(7294)),r=n(a(2026)),u=n(a(2924));var o=function(e){var t=(0,r.default)(),a=t.isDarkTheme,n=t.setLightTheme,o=t.setDarkTheme;return l.default.createElement(u.default.Provider,{value:{isDarkTheme:a,setLightTheme:n,setDarkTheme:o}},e.children)};t.default=o},2388:(e,t,a)=>{var n=a(4836).default;Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var l=n(a(7462)),r=n(a(3366)),u=n(a(7294)),o=n(a(6010)),d=n(a(2263)),i=n(a(5350)),c=n(a(2068)),f=["sources","className","alt"],s=function(e){var t=(0,d.default)().isClient,a=(0,i.default)().isDarkTheme,n=e.sources,s=e.className,m=e.alt,v=void 0===m?"":m,h=(0,r.default)(e,f),b=t?a?["dark"]:["light"]:["light","dark"];return u.default.createElement(u.default.Fragment,null,b.map((function(e){return u.default.createElement("img",(0,l.default)({key:e,src:n[e],alt:v,className:(0,o.default)(c.default.themedImage,c.default["themedImage--"+e],s)},h))})))};t.default=s},8037:(e,t,a)=>{var n=a(5263).default,l=a(4836).default;Object.defineProperty(t,"__esModule",{value:!0}),t.default=function(e){var t=(0,o.useThemeConfig)().colorMode.switchConfig,a=t.darkIcon,n=t.darkIconStyle,l=t.lightIcon,i=t.lightIconStyle,c=(0,d.default)().isClient;return u.default.createElement(m,(0,r.default)({disabled:!c,icons:{checked:u.default.createElement(f,{icon:a,style:n}),unchecked:u.default.createElement(s,{icon:l,style:i})}},e))};var r=l(a(7462)),u=n(a(7294)),o=a(6700),d=l(a(2263)),i=l(a(6010)),c=l(a(4475)),f=function(e){var t=e.icon,a=e.style;return u.default.createElement("span",{className:(0,i.default)(c.default.toggle,c.default.dark),style:a},t)},s=function(e){var t=e.icon,a=e.style;return u.default.createElement("span",{className:(0,i.default)(c.default.toggle,c.default.light),style:a},t)},m=(0,u.memo)((function(e){var t=e.className,a=e.icons,n=e.checked,l=e.disabled,r=e.onChange,o=(0,u.useState)(n),d=o[0],c=o[1],f=(0,u.useState)(!1),s=f[0],m=f[1],v=(0,u.useRef)(null);return u.default.createElement("div",{className:(0,i.default)("react-toggle",t,{"react-toggle--checked":d,"react-toggle--focus":s,"react-toggle--disabled":l}),role:"button",tabIndex:-1,onClick:function(e){var t=v.current;if(t)return e.target!==t?(e.preventDefault(),t.focus(),void t.click()):void c(null==t?void 0:t.checked)}},u.default.createElement("div",{className:"react-toggle-track"},u.default.createElement("div",{className:"react-toggle-track-check"},a.checked),u.default.createElement("div",{className:"react-toggle-track-x"},a.unchecked)),u.default.createElement("div",{className:"react-toggle-thumb"}),u.default.createElement("input",{ref:v,checked:d,type:"checkbox",className:"react-toggle-screenreader-only","aria-label":"Switch between dark and light mode",onChange:r,onFocus:function(){return m(!0)},onBlur:function(){return m(!1)}}))}))},9443:(e,t,a)=>{Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var n=(0,a(7294).createContext)(void 0);t.default=n},1073:(e,t,a)=>{var n=a(4836).default;Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var l=n(a(7294)),r=n(a(6693)),u=n(a(3912)),o=n(a(9443));var d=function(e){var t=(0,r.default)(),a=t.tabGroupChoices,n=t.setTabGroupChoices,d=(0,u.default)(),i=d.isAnnouncementBarClosed,c=d.closeAnnouncementBar;return l.default.createElement(o.default.Provider,{value:{tabGroupChoices:a,setTabGroupChoices:n,isAnnouncementBarClosed:i,closeAnnouncementBar:c}},e.children)};t.default=d},3912:(e,t,a)=>{Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var n=a(7294),l=a(6700),r=(0,l.createStorageSlot)("docusaurus.announcement.dismiss"),u=(0,l.createStorageSlot)("docusaurus.announcement.id"),o=function(){var e=(0,l.useThemeConfig)().announcementBar,t=(0,n.useState)(!0),a=t[0],o=t[1],d=(0,n.useCallback)((function(){r.set("true"),o(!0)}),[]);return(0,n.useEffect)((function(){if(e){var t=e.id,a=u.get();"annoucement-bar"===a&&(a="announcement-bar");var n=t!==a;u.set(t),n&&r.set("false"),(n||"false"===r.get())&&o(!1)}}),[]),{isAnnouncementBarClosed:a,closeAnnouncementBar:d}};t.default=o},5662:(e,t,a)=>{var n=a(4836).default;Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var l=a(7294),r=a(8143),u=n(a(7898)),o=function(e){var t=(0,r.useLocation)(),a=(0,l.useState)(e),n=a[0],o=a[1],d=(0,l.useRef)(!1),i=(0,l.useState)(0),c=i[0],f=i[1],s=(0,l.useCallback)((function(e){null!==e&&f(e.getBoundingClientRect().height)}),[]);return(0,u.default)((function(t,a){var n=t.scrollY,l=a.scrollY;if(e)if(n<c)o(!0);else{if(d.current)return d.current=!1,void o(!1);l&&0===n&&o(!0);var r=document.documentElement.scrollHeight-c,u=window.innerHeight;l&&n>=l?o(!1):n+u<r&&o(!0)}}),[c,d]),(0,l.useEffect)((function(){e&&o(!0)}),[t.pathname]),(0,l.useEffect)((function(){e&&t.hash&&(d.current=!0)}),[t.hash]),{navbarRef:s,isNavbarVisible:n}};t.default=o},8245:(e,t,a)=>{Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var n=a(7294);a(2397);var l=function(){(0,n.useEffect)((function(){var e="navigation-with-keyboard";function t(t){"keydown"===t.type&&"Tab"===t.key&&document.body.classList.add(e),"mousedown"===t.type&&document.body.classList.remove(e)}return document.addEventListener("keydown",t),document.addEventListener("mousedown",t),function(){document.body.classList.remove(e),document.removeEventListener("keydown",t),document.removeEventListener("mousedown",t)}}),[])};t.default=l},1839:(e,t,a)=>{Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var n=a(7294);var l=function(e){void 0===e&&(e=!0),(0,n.useEffect)((function(){return document.body.style.overflow=e?"hidden":"visible",function(){document.body.style.overflow="visible"}}),[e])};t.default=l},7898:(e,t,a)=>{var n=a(4836).default;Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var l=a(7294),r=n(a(412)),u=function(){return{scrollX:r.default.canUseDOM?window.pageXOffset:0,scrollY:r.default.canUseDOM?window.pageYOffset:0}},o=function(e,t){void 0===t&&(t=[]);var a=(0,l.useRef)(u()),n=function(){var t=u();e&&e(t,a.current),a.current=t};(0,l.useEffect)((function(){var e={passive:!0};return n(),window.addEventListener("scroll",n,e),function(){return window.removeEventListener("scroll",n,e)}}),t)};t.default=o},6693:(e,t,a)=>{var n=a(4836).default;Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var l=n(a(5196)),r=a(7294),u=a(6700),o="docusaurus.tab.",d=function(){var e=(0,r.useState)({}),t=e[0],a=e[1],n=(0,r.useCallback)((function(e,t){(0,u.createStorageSlot)(""+o+e).set(t)}),[]);return(0,r.useEffect)((function(){try{for(var e,t={},n=(0,l.default)((0,u.listStorageKeys)());!(e=n()).done;){var r=e.value;if(r.startsWith(o))t[r.substring(o.length)]=(0,u.createStorageSlot)(r).get()}a(t)}catch(d){console.error(d)}}),[]),{tabGroupChoices:t,setTabGroupChoices:function(e,t){a((function(a){var n;return Object.assign({},a,((n={})[e]=t,n))})),n(e,t)}}};t.default=d},2026:(e,t,a)=>{var n=a(4836).default;Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var l=a(7294),r=n(a(412)),u=a(6700),o=(0,u.createStorageSlot)("theme"),d="light",i="dark",c=function(e){return e===i?i:d},f=function(e){(0,u.createStorageSlot)("theme").set(c(e))},s=function(){var e=(0,u.useThemeConfig)().colorMode,t=e.defaultMode,a=e.disableSwitch,n=e.respectPrefersColorScheme,s=(0,l.useState)(function(e){return r.default.canUseDOM?c(document.documentElement.getAttribute("data-theme")):c(e)}(t)),m=s[0],v=s[1],h=(0,l.useCallback)((function(){v(d),f(d)}),[]),b=(0,l.useCallback)((function(){v(i),f(i)}),[]);return(0,l.useEffect)((function(){document.documentElement.setAttribute("data-theme",c(m))}),[m]),(0,l.useEffect)((function(){if(!a)try{var e=o.get();null!==e&&v(c(e))}catch(t){console.error(t)}}),[v]),(0,l.useEffect)((function(){a&&!n||window.matchMedia("(prefers-color-scheme: dark)").addListener((function(e){var t=e.matches;v(t?i:d)}))}),[]),{isDarkTheme:m===i,setLightTheme:h,setDarkTheme:b}};t.default=s},5350:(e,t,a)=>{var n=a(4836).default;Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var l=a(7294),r=n(a(2924));var u=function(){var e=(0,l.useContext)(r.default);if(null==e)throw new Error("`useThemeContext` is used outside of `Layout` Component. See https://docusaurus.io/docs/api/themes/configuration#usethemecontext.");return e};t.default=u},944:(e,t,a)=>{var n=a(4836).default;Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var l=a(7294),r=n(a(9443));var u=function(){var e=(0,l.useContext)(r.default);if(null==e)throw new Error("`useUserPreferencesContext` is used outside of `Layout` Component.");return e};t.default=u},3783:(e,t,a)=>{var n=a(4836).default;Object.defineProperty(t,"__esModule",{value:!0}),t.windowSizes=t.default=void 0;var l=a(7294),r=n(a(412)),u=996,o={desktop:"desktop",mobile:"mobile"};t.windowSizes=o;var d=function(){var e=r.default.canUseDOM;function t(){if(e)return window.innerWidth>u?o.desktop:o.mobile}var a=(0,l.useState)(t),n=a[0],d=a[1];return(0,l.useEffect)((function(){if(e)return window.addEventListener("resize",a),function(){return window.removeEventListener("resize",a)};function a(){d(t())}}),[]),n};t.default=d},9873:(e,t,a)=>{a.r(t)},2397:(e,t,a)=>{a.r(t)},5806:(e,t,a)=>{a.r(t),a.d(t,{default:()=>n});const n={announcementBar:"announcementBar_axC9",announcementBarClose:"announcementBarClose_A3A1",announcementBarContent:"announcementBarContent_6uhP",announcementBarCloseable:"announcementBarCloseable_y4cp"}},1805:(e,t,a)=>{a.r(t),a.d(t,{default:()=>n});const n={displayOnlyInLargeViewport:"displayOnlyInLargeViewport_cxYs",navbarHideable:"navbarHideable_RReh",navbarHidden:"navbarHidden_FBwS"}},1594:(e,t,a)=>{a.r(t),a.d(t,{default:()=>n});const n={searchWrapper:"searchWrapper_f8aU"}},126:(e,t,a)=>{a.r(t),a.d(t,{default:()=>n});const n={skipToContent:"skipToContent_OuoZ"}},2068:(e,t,a)=>{a.r(t),a.d(t,{default:()=>n});const n={themedImage:"themedImage_TMUO","themedImage--light":"themedImage--light_4Vu1","themedImage--dark":"themedImage--dark_uzRr"}},4475:(e,t,a)=>{a.r(t),a.d(t,{default:()=>n});const n={toggle:"toggle_iYfV"}},5196:(e,t,a)=>{function n(e,t){(null==t||t>e.length)&&(t=e.length);for(var a=0,n=new Array(t);a<t;a++)n[a]=e[a];return n}function l(e,t){var a="undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(a)return(a=a.call(e)).next.bind(a);if(Array.isArray(e)||(a=function(e,t){if(e){if("string"==typeof e)return n(e,t);var a=Object.prototype.toString.call(e).slice(8,-1);return"Object"===a&&e.constructor&&(a=e.constructor.name),"Map"===a||"Set"===a?Array.from(e):"Arguments"===a||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(a)?n(e,t):void 0}}(e))||t&&e&&"number"==typeof e.length){a&&(e=a);var l=0;return function(){return l>=e.length?{done:!0}:{done:!1,value:e[l++]}}}throw new TypeError("Invalid attempt to iterate non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}a.r(t),a.d(t,{default:()=>l})}}]);