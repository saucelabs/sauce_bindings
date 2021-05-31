/*! For license information please see 889.18ac77c8.js.LICENSE.txt */
(self.webpackChunk=self.webpackChunk||[]).push([[889],{3905:(e,t,n)=>{"use strict";n.r(t),n.d(t,{MDXContext:()=>c,MDXProvider:()=>h,mdx:()=>m,useMDXComponents:()=>d,withMDXComponents:()=>u});var a=n(7294);function r(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function o(){return(o=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var n=arguments[t];for(var a in n)Object.prototype.hasOwnProperty.call(n,a)&&(e[a]=n[a])}return e}).apply(this,arguments)}function l(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);t&&(a=a.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,a)}return n}function s(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?l(Object(n),!0).forEach((function(t){r(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):l(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function i(e,t){if(null==e)return{};var n,a,r=function(e,t){if(null==e)return{};var n,a,r={},o=Object.keys(e);for(a=0;a<o.length;a++)n=o[a],t.indexOf(n)>=0||(r[n]=e[n]);return r}(e,t);if(Object.getOwnPropertySymbols){var o=Object.getOwnPropertySymbols(e);for(a=0;a<o.length;a++)n=o[a],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(r[n]=e[n])}return r}var c=a.createContext({}),u=function(e){return function(t){var n=d(t.components);return a.createElement(e,o({},t,{components:n}))}},d=function(e){var t=a.useContext(c),n=t;return e&&(n="function"==typeof e?e(t):s(s({},t),e)),n},h=function(e){var t=d(e.components);return a.createElement(c.Provider,{value:t},e.children)},f={inlineCode:"code",wrapper:function(e){var t=e.children;return a.createElement(a.Fragment,{},t)}},p=a.forwardRef((function(e,t){var n=e.components,r=e.mdxType,o=e.originalType,l=e.parentName,c=i(e,["components","mdxType","originalType","parentName"]),u=d(n),h=r,p=u["".concat(l,".").concat(h)]||u[h]||f[h]||o;return n?a.createElement(p,s(s({ref:t},c),{},{components:n})):a.createElement(p,s({ref:t},c))}));function m(e,t){var n=arguments,r=t&&t.mdxType;if("string"==typeof e||r){var o=n.length,l=new Array(o);l[0]=p;var s={};for(var i in t)hasOwnProperty.call(t,i)&&(s[i]=t[i]);s.originalType=e,s.mdxType="string"==typeof e?e:r,l[1]=s;for(var c=2;c<o;c++)l[c]=n[c];return a.createElement.apply(null,l)}return a.createElement.apply(null,n)}p.displayName="MDXCreateElement"},1841:function(e,t,n){"use strict";var a=this&&this.__importDefault||function(e){return e&&e.__esModule?e:{default:e}};Object.defineProperty(t,"__esModule",{value:!0});const r=a(n(7294)),o=a(n(5665)),l=a(n(5913));e.exports=(l.default,e=>e.reference?r.default.createElement(o.default,Object.assign({},e)):r.default.createElement(l.default,Object.assign({},e)))},5665:function(e,t,n){"use strict";var a=this&&this.__createBinding||(Object.create?function(e,t,n,a){void 0===a&&(a=n),Object.defineProperty(e,a,{enumerable:!0,get:function(){return t[n]}})}:function(e,t,n,a){void 0===a&&(a=n),e[a]=t[n]}),r=this&&this.__setModuleDefault||(Object.create?function(e,t){Object.defineProperty(e,"default",{enumerable:!0,value:t})}:function(e,t){e.default=t}),o=this&&this.__importStar||function(e){if(e&&e.__esModule)return e;var t={};if(null!=e)for(var n in e)"default"!==n&&Object.prototype.hasOwnProperty.call(e,n)&&a(t,e,n);return r(t,e),t},l=this&&this.__importDefault||function(e){return e&&e.__esModule?e:{default:e}};Object.defineProperty(t,"__esModule",{value:!0}),t.parseReference=void 0;const s=n(8575),i=o(n(7294)),c=l(n(5913)),u={code:"loading...",error:null,loading:null},d={textAlign:"right",fontSize:".8em"};function h(e){const t=e.slice(e.indexOf("https"),-1),[n,a]=t.split("#"),r=globalThis||{};r.URL||(r.URL=s.URL);const[o,l,i,c,...u]=new r.URL(n).pathname.split("/").slice(1),[d,h]=a?a.split("-").map((e=>parseInt(e.slice(1),10)-1)):[0,1/0];return{url:`https://raw.githubusercontent.com/${o}/${l}/${c}/${u.join("/")}`,fromLine:d,toLine:h,title:u.join("/")}}function f(e,{type:t,value:n}){switch(t){case"reset":return u;case"loading":return{...e,loading:!0};case"loaded":return{...e,code:n,loading:!1};case"error":return{...e,error:n,loading:!1};default:return e}}t.parseReference=h,t.default=function(e){const[t,n]=i.useReducer(f,u),a=h(e.children);!1!==t.loading&&async function({url:e,fromLine:t,toLine:n},a){let r;try{r=await fetch(e)}catch(s){return a({type:"error",value:s})}if(200!==r.status)return a({type:"error",value:await r.text()});const o=(await r.text()).split("\n").slice(t,(n||t)+1),l=o.reduce(((e,t)=>{if(0===t.length)return e;const n=t.match(/^\s+/);return n?Math.min(e,n[0].length):0}),1/0);a({type:"loaded",value:o.map((e=>e.slice(l))).join("\n")})}(a,n);const r={...e,metastring:` title="${a.title}"`,children:u.code};return i.default.createElement("div",null,i.default.createElement(c.default,Object.assign({},r),t.code),i.default.createElement("div",{style:d},"See full example on ",i.default.createElement("a",{href:e.children,target:"_blank"},"GitHub")))}},5913:(e,t,n)=>{"use strict";var a=n(862).default,r=n(5318).default;Object.defineProperty(t,"__esModule",{value:!0}),t.default=function(e){var t=e.children,n=e.className,a=e.metastring,r=e.title,b=(0,p.useThemeConfig)().prism,y=(0,l.useState)(!1),g=y[0],k=y[1],E=(0,l.useState)(!1),C=E[0],j=E[1];(0,l.useEffect)((function(){j(!0)}),[]);var O=(0,p.parseCodeBlockTitle)(a)||r,_=(0,l.useRef)(null),x=[],N=(0,d.default)(),w=Array.isArray(t)?t.join(""):t;if(a&&m.test(a)){var S=a.match(m)[1];x=(0,u.default)(S).filter((function(e){return e>0}))}var P=n&&n.replace(/language-/,"");!P&&b.defaultLanguage&&(P=b.defaultLanguage);var T=w.replace(/\n$/,"");if(0===x.length&&void 0!==P){for(var I,A="",B=function(e){switch(e){case"js":case"javascript":case"ts":case"typescript":return v(["js","jsBlock"]);case"jsx":case"tsx":return v(["js","jsBlock","jsx"]);case"html":return v(["js","jsBlock","html"]);case"python":case"py":return v(["python"]);default:return v()}}(P),L=w.replace(/\n$/,"").split("\n"),M=0;M<L.length;){var R=L[M],D=M+1,U=R.match(B);if(null!==U){switch(U.slice(1).reduce((function(e,t){return e||t}),void 0)){case"highlight-next-line":A+=D+",";break;case"highlight-start":I=D;break;case"highlight-end":A+=I+"-"+(D-1)+","}L.splice(M,1)}else M+=1}x=(0,u.default)(A),T=L.join("\n")}var q=function(){(0,c.default)(T),k(!0),setTimeout((function(){return k(!1)}),2e3)};return l.default.createElement(i.default,(0,o.default)({},i.defaultProps,{key:String(C),theme:N,code:T,language:P}),(function(e){var t,n=e.className,a=e.style,r=e.tokens,i=e.getLineProps,c=e.getTokenProps;return l.default.createElement("div",{className:f.default.codeBlockContainer},O&&l.default.createElement("div",{style:a,className:f.default.codeBlockTitle},O),l.default.createElement("div",{className:(0,s.default)(f.default.codeBlockContent,P)},l.default.createElement("div",{tabIndex:0,className:(0,s.default)(n,f.default.codeBlock,"thin-scrollbar",(t={},t[f.default.codeBlockWithTitle]=O,t))},l.default.createElement("div",{className:f.default.codeBlockLines,style:a},r.map((function(e,t){1===e.length&&""===e[0].content&&(e[0].content="\n");var n=i({line:e,key:t});return x.includes(t+1)&&(n.className=n.className+" docusaurus-highlight-code-line"),l.default.createElement("div",(0,o.default)({key:t},n),e.map((function(e,t){return l.default.createElement("span",(0,o.default)({key:t},c({token:e,key:t})))})))})))),l.default.createElement("button",{ref:_,type:"button","aria-label":(0,h.translate)({id:"theme.CodeBlock.copyButtonAriaLabel",message:"Copy code to clipboard",description:"The ARIA label for copy code blocks button"}),className:(0,s.default)(f.default.copyButton),onClick:q},g?l.default.createElement(h.default,{id:"theme.CodeBlock.copied",description:"The copied button label on code blocks"},"Copied"):l.default.createElement(h.default,{id:"theme.CodeBlock.copy",description:"The copy button label on code blocks"},"Copy"))))}))};var o=r(n(2122)),l=a(n(7294)),s=r(n(6010)),i=a(n(4544)),c=r(n(735)),u=r(n(7594)),d=r(n(6617)),h=a(n(9052)),f=r(n(7165)),p=n(6700),m=/{([\d,-]+)}/,v=function(e){void 0===e&&(e=["js","jsBlock","jsx","python","html"]);var t={js:{start:"\\/\\/",end:""},jsBlock:{start:"\\/\\*",end:"\\*\\/"},jsx:{start:"\\{\\s*\\/\\*",end:"\\*\\/\\s*\\}"},python:{start:"#",end:""},html:{start:"\x3c!--",end:"--\x3e"}},n=["highlight-next-line","highlight-start","highlight-end"].join("|"),a=e.map((function(e){return"(?:"+t[e].start+"\\s*("+n+")\\s*"+t[e].end+")"})).join("|");return new RegExp("^\\s*(?:"+a+")\\s*$")}},9889:(e,t,n)=>{"use strict";var a=n(5318).default,r=n(862).default;Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var o=r(n(7294)),l=n(3905),s=a(n(2263)),i=a(n(6291)),c=a(n(8532)),u=a(n(4396)),d=a(n(3491)),h=a(n(4608)),f=a(n(4096)),p=n(8143),m=n(9052),v=a(n(6010)),b=a(n(7959)),y=n(6700);function g(e){var t,n,a,r,i,h=e.currentDocRoute,p=e.versionMetadata,g=e.children,k=(0,s.default)(),E=k.siteConfig,C=k.isClient,j=p.pluginId,O=p.permalinkToSidebar,_=p.docsSidebars,x=p.version,N=O[h.path],w=_[N],S=(0,o.useState)(!1),P=S[0],T=S[1],I=(0,o.useState)(!1),A=I[0],B=I[1],L=(0,o.useCallback)((function(){A&&B(!1),T(!P)}),[A]);return o.default.createElement(c.default,{key:C,wrapperClassName:y.ThemeClassNames.wrapper.docPages,pageClassName:y.ThemeClassNames.page.docPage,searchMetadatas:{version:x,tag:(0,y.docVersionSearchTag)(j,x)}},o.default.createElement("div",{className:b.default.docPage},w&&o.default.createElement("div",{className:(0,v.default)(b.default.docSidebarContainer,(t={},t[b.default.docSidebarContainerHidden]=P,t)),onTransitionEnd:function(e){e.currentTarget.classList.contains(b.default.docSidebarContainer)&&P&&B(!0)},role:"complementary"},o.default.createElement(u.default,{key:N,sidebar:w,path:h.path,sidebarCollapsible:null==(n=null==(a=E.themeConfig)?void 0:a.sidebarCollapsible)||n,onCollapse:L,isHidden:A}),A&&o.default.createElement("div",{className:b.default.collapsedDocSidebar,title:(0,m.translate)({id:"theme.docs.sidebar.expandButtonTitle",message:"Expand sidebar",description:"The ARIA label and title attribute for expand button of doc sidebar"}),"aria-label":(0,m.translate)({id:"theme.docs.sidebar.expandButtonAriaLabel",message:"Expand sidebar",description:"The ARIA label and title attribute for expand button of doc sidebar"}),tabIndex:0,role:"button",onKeyDown:L,onClick:L},o.default.createElement(f.default,{className:b.default.expandSidebarButtonIcon}))),o.default.createElement("main",{className:(0,v.default)(b.default.docMainContainer,(r={},r[b.default.docMainContainerEnhanced]=P||!w,r))},o.default.createElement("div",{className:(0,v.default)("container padding-vert--lg",b.default.docItemWrapper,(i={},i[b.default.docItemWrapperEnhanced]=P,i))},o.default.createElement(l.MDXProvider,{components:d.default},g)))))}var k=function(e){var t=e.route.routes,n=e.versionMetadata,a=e.location,r=t.find((function(e){return(0,p.matchPath)(a.pathname,e)}));return r?o.default.createElement(g,{currentDocRoute:r,versionMetadata:n},(0,i.default)(t)):o.default.createElement(h.default,e)};t.default=k},4396:(e,t,n)=>{"use strict";var a=n(5318).default,r=n(862).default;Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var o=a(n(2122)),l=a(n(9756)),s=r(n(7294)),i=a(n(6010)),c=n(6700),u=a(n(944)),d=a(n(1839)),h=r(n(3783)),f=a(n(7898)),p=a(n(3692)),m=a(n(3919)),v=a(n(5537)),b=a(n(4096)),y=a(n(4478)),g=n(9052),k=a(n(3698)),E=["items"],C=["item","onItemClick","collapsible","activePath"],j=["item","onItemClick","activePath","collapsible"];var O=function e(t,n){return"link"===t.type?(0,c.isSamePath)(t.href,n):"category"===t.type&&t.items.some((function(t){return e(t,n)}))},_=(0,s.memo)((function(e){var t=e.items,n=(0,l.default)(e,E);return t.map((function(e,t){return s.default.createElement(x,(0,o.default)({key:t,item:e},n))}))}));function x(e){switch(e.item.type){case"category":return s.default.createElement(N,e);case"link":default:return s.default.createElement(w,e)}}function N(e){var t,n,a,r=e.item,c=e.onItemClick,u=e.collapsible,d=e.activePath,h=(0,l.default)(e,C),f=r.items,p=r.label,m=O(r,d),v=(n=m,a=(0,s.useRef)(n),(0,s.useEffect)((function(){a.current=n}),[n]),a.current),b=(0,s.useState)((function(){return!!u&&(!m&&r.collapsed)})),y=b[0],g=b[1],E=(0,s.useRef)(null),j=(0,s.useState)(void 0),x=j[0],N=j[1],w=function(e){var t;void 0===e&&(e=!0),N(e?(null==(t=E.current)?void 0:t.scrollHeight)+"px":void 0)};(0,s.useEffect)((function(){m&&!v&&y&&g(!1)}),[m,v,y]);var S=(0,s.useCallback)((function(e){e.preventDefault(),x||w(),setTimeout((function(){return g((function(e){return!e}))}),100)}),[x]);return 0===f.length?null:s.default.createElement("li",{className:(0,i.default)("menu__list-item",{"menu__list-item--collapsed":y})},s.default.createElement("a",(0,o.default)({className:(0,i.default)("menu__link",(t={"menu__link--sublist":u,"menu__link--active":u&&m},t[k.default.menuLinkText]=!u,t)),onClick:u?S:void 0,href:u?"#!":void 0},h),p),s.default.createElement("ul",{className:"menu__list",ref:E,style:{height:x},onTransitionEnd:function(){y||w(!1)}},s.default.createElement(_,{items:f,tabIndex:y?"-1":"0",onItemClick:c,collapsible:u,activePath:d})))}function w(e){var t,n=e.item,a=e.onItemClick,r=e.activePath,c=(e.collapsible,(0,l.default)(e,j)),u=n.href,d=n.label,h=O(n,r);return s.default.createElement("li",{className:"menu__list-item",key:d},s.default.createElement(p.default,(0,o.default)({className:(0,i.default)("menu__link",(t={"menu__link--active":h},t[k.default.menuLinkExternal]=!(0,m.default)(u),t)),to:u},(0,m.default)(u)&&{isNavLink:!0,exact:!0,onClick:a},c),d))}function S(e){var t=e.onClick;return s.default.createElement("button",{type:"button",title:(0,g.translate)({id:"theme.docs.sidebar.collapseButtonTitle",message:"Collapse sidebar",description:"The title attribute for collapse button of doc sidebar"}),"aria-label":(0,g.translate)({id:"theme.docs.sidebar.collapseButtonAriaLabel",message:"Collapse sidebar",description:"The title attribute for collapse button of doc sidebar"}),className:(0,i.default)("button button--secondary button--outline",k.default.collapseSidebarButton),onClick:t},s.default.createElement(b.default,{className:k.default.collapseSidebarButtonIcon}))}function P(e){var t=e.responsiveSidebarOpened,n=e.onClick;return s.default.createElement("button",{"aria-label":t?(0,g.translate)({id:"theme.docs.sidebar.responsiveCloseButtonLabel",message:"Close menu",description:"The ARIA label for close button of mobile doc sidebar"}):(0,g.translate)({id:"theme.docs.sidebar.responsiveOpenButtonLabel",message:"Open menu",description:"The ARIA label for open button of mobile doc sidebar"}),"aria-haspopup":"true",className:"button button--secondary button--sm menu__button",type:"button",onClick:n},t?s.default.createElement("span",{className:(0,i.default)(k.default.sidebarMenuIcon,k.default.sidebarMenuCloseIcon)},"\xd7"):s.default.createElement(y.default,{className:k.default.sidebarMenuIcon,height:24,width:24}))}var T=function(e){var t,n,a=e.path,r=e.sidebar,o=e.sidebarCollapsible,l=void 0===o||o,p=e.onCollapse,m=e.isHidden,b=function(){var e=(0,u.default)().isAnnouncementBarClosed,t=(0,s.useState)(!e),n=t[0],a=t[1];return(0,f.default)((function(t){var n=t.scrollY;e||a(0===n)})),n}(),y=(0,c.useThemeConfig)(),g=y.navbar.hideOnScroll,E=y.hideableSidebar,C=(0,u.default)().isAnnouncementBarClosed,j=function(){var e=(0,s.useState)(!1),t=e[0],n=e[1];(0,d.default)(t);var a=(0,h.default)();return(0,s.useEffect)((function(){a===h.windowSizes.desktop&&n(!1)}),[a]),{showResponsiveSidebar:t,closeResponsiveSidebar:(0,s.useCallback)((function(e){e.target.blur(),n(!1)}),[n]),toggleResponsiveSidebar:(0,s.useCallback)((function(){n((function(e){return!e}))}),[n])}}(),O=j.showResponsiveSidebar,x=j.closeResponsiveSidebar,N=j.toggleResponsiveSidebar;return s.default.createElement("div",{className:(0,i.default)(k.default.sidebar,(t={},t[k.default.sidebarWithHideableNavbar]=g,t[k.default.sidebarHidden]=m,t))},g&&s.default.createElement(v.default,{tabIndex:-1,className:k.default.sidebarLogo}),s.default.createElement("div",{className:(0,i.default)("menu","menu--responsive","thin-scrollbar",k.default.menu,(n={"menu--show":O},n[k.default.menuWithAnnouncementBar]=!C&&b,n))},s.default.createElement(P,{responsiveSidebarOpened:O,onClick:N}),s.default.createElement("ul",{className:"menu__list"},s.default.createElement(_,{items:r,onItemClick:x,collapsible:l,activePath:a}))),E&&s.default.createElement(S,{onClick:p}))};t.default=T},6638:(e,t,n)=>{"use strict";var a=n(5318).default;Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var r=a(n(9756)),o=a(n(7294)),l=a(n(6010)),s=n(9052),i=n(6700);n(8133);var c=a(n(8867)),u=["id"],d=function(e){return function(t){var n,a=t.id,d=(0,r.default)(t,u),h=(0,i.useThemeConfig)().navbar.hideOnScroll;return a?o.default.createElement(e,d,o.default.createElement("a",{"aria-hidden":"true",tabIndex:-1,className:(0,l.default)("anchor",(n={},n[c.default.enhancedAnchor]=!h,n)),id:a}),d.children,o.default.createElement("a",{className:"hash-link",href:"#"+a,title:(0,s.translate)({id:"theme.common.headingLinkTitle",message:"Direct link to heading",description:"Title for link to heading"})},"#")):o.default.createElement(e,d)}};t.default=d},4096:(e,t,n)=>{"use strict";var a=n(5318).default;Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var r=a(n(2122)),o=a(n(7294)),l=function(e){return o.default.createElement("svg",(0,r.default)({width:"20",height:"20",role:"img"},e),o.default.createElement("g",{fill:"#7a7a7a"},o.default.createElement("path",{d:"M9.992 10.023c0 .2-.062.399-.172.547l-4.996 7.492a.982.982 0 01-.828.454H1c-.55 0-1-.453-1-1 0-.2.059-.403.168-.551l4.629-6.942L.168 3.078A.939.939 0 010 2.528c0-.548.45-.997 1-.997h2.996c.352 0 .649.18.828.45L9.82 9.472c.11.148.172.347.172.55zm0 0"}),o.default.createElement("path",{d:"M19.98 10.023c0 .2-.058.399-.168.547l-4.996 7.492a.987.987 0 01-.828.454h-3c-.547 0-.996-.453-.996-1 0-.2.059-.403.168-.551l4.625-6.942-4.625-6.945a.939.939 0 01-.168-.55 1 1 0 01.996-.997h3c.348 0 .649.18.828.45l4.996 7.492c.11.148.168.347.168.55zm0 0"})))};t.default=l},3491:(e,t,n)=>{"use strict";var a=n(5318).default,r=n(862).default;Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var o=r(n(7294)),l=a(n(3692)),s=a(n(1841)),i=a(n(6638)),c={code:function(e){var t=e.children;return(0,o.isValidElement)(t)?t:t.includes("\n")?o.default.createElement(s.default,e):o.default.createElement("code",e)},a:function(e){return o.default.createElement(l.default,e)},pre:function(e){var t,n=e.children;return(0,o.isValidElement)(null==n||null==(t=n.props)?void 0:t.children)?null==n?void 0:n.props.children:o.default.createElement(s.default,(0,o.isValidElement)(n)?null==n?void 0:n.props:{children:n})},h1:(0,i.default)("h1"),h2:(0,i.default)("h2"),h3:(0,i.default)("h3"),h4:(0,i.default)("h4"),h5:(0,i.default)("h5"),h6:(0,i.default)("h6")};t.default=c},4608:(e,t,n)=>{"use strict";var a=n(5318).default;Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var r=a(n(7294)),o=a(n(8532)),l=a(n(9052));var s=function(){return r.default.createElement(o.default,{title:"Page Not Found"},r.default.createElement("main",{className:"container margin-vert--xl"},r.default.createElement("div",{className:"row"},r.default.createElement("div",{className:"col col--6 col--offset-3"},r.default.createElement("h1",{className:"hero__title"},r.default.createElement(l.default,{id:"theme.NotFound.title",description:"The title of the 404 page"},"Page Not Found")),r.default.createElement("p",null,r.default.createElement(l.default,{id:"theme.NotFound.p1",description:"The first paragraph of the 404 page"},"We could not find what you were looking for.")),r.default.createElement("p",null,r.default.createElement(l.default,{id:"theme.NotFound.p2",description:"The 2nd paragraph of the 404 page"},"Please contact the owner of the site that linked you to the original URL and let them know their link is broken."))))))};t.default=s},6617:(e,t,n)=>{"use strict";var a=n(5318).default;Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var r=a(n(7552)),o=a(n(5350)),l=n(6700),s=function(){var e=(0,l.useThemeConfig)().prism,t=(0,o.default)().isDarkTheme,n=e.theme||r.default,a=e.darkTheme||n;return t?a:n};t.default=s},735:(e,t,n)=>{"use strict";function a(e,{target:t=document.body}={}){const n=document.createElement("textarea"),a=document.activeElement;n.value=e,n.setAttribute("readonly",""),n.style.contain="strict",n.style.position="absolute",n.style.left="-9999px",n.style.fontSize="12pt";const r=document.getSelection();let o=!1;r.rangeCount>0&&(o=r.getRangeAt(0)),t.append(n),n.select(),n.selectionStart=0,n.selectionEnd=e.length;let l=!1;try{l=document.execCommand("copy")}catch{}return n.remove(),o&&(r.removeAllRanges(),r.addRange(o)),a&&a.focus(),l}n.r(t),n.d(t,{default:()=>a})},8133:(e,t,n)=>{"use strict";n.r(t)},7165:(e,t,n)=>{"use strict";n.r(t),n.d(t,{default:()=>a});const a={codeBlockContainer:"codeBlockContainer_K1bP",codeBlockContent:"codeBlockContent_hGly",codeBlockTitle:"codeBlockTitle_eoMF",codeBlock:"codeBlock_23N8",codeBlockWithTitle:"codeBlockWithTitle_2JqI",copyButton:"copyButton_Ue-o",codeBlockLines:"codeBlockLines_39YC"}},7959:(e,t,n)=>{"use strict";n.r(t),n.d(t,{default:()=>a});const a={docPage:"docPage_31aa",docMainContainer:"docMainContainer_3ufF",docMainContainerEnhanced:"docMainContainerEnhanced_3NYZ",docSidebarContainer:"docSidebarContainer_3Kbt",docSidebarContainerHidden:"docSidebarContainerHidden_3pA8",collapsedDocSidebar:"collapsedDocSidebar_2JMH",expandSidebarButtonIcon:"expandSidebarButtonIcon_1naQ",docItemWrapperEnhanced:"docItemWrapperEnhanced_2vyJ",docItemWrapper:"docItemWrapper_3FMP"}},3698:(e,t,n)=>{"use strict";n.r(t),n.d(t,{default:()=>a});const a={sidebar:"sidebar_15mo",sidebarWithHideableNavbar:"sidebarWithHideableNavbar_267A",sidebarHidden:"sidebarHidden_2kNb",sidebarLogo:"sidebarLogo_3h0W",menu:"menu_Bmed",menuLinkText:"menuLinkText_2aKo",menuWithAnnouncementBar:"menuWithAnnouncementBar_2WvA",collapseSidebarButton:"collapseSidebarButton_1CGd",collapseSidebarButtonIcon:"collapseSidebarButtonIcon_3E-R",sidebarMenuIcon:"sidebarMenuIcon_fgN0",sidebarMenuCloseIcon:"sidebarMenuCloseIcon_1lpH",menuLinkExternal:"menuLinkExternal_1OhN"}},8867:(e,t,n)=>{"use strict";n.r(t),n.d(t,{default:()=>a});const a={enhancedAnchor:"enhancedAnchor_2LWZ"}},7594:(e,t)=>{function n(e){let t,n=[];for(let a of e.split(",").map((e=>e.trim())))if(/^-?\d+$/.test(a))n.push(parseInt(a,10));else if(t=a.match(/^(-?\d+)(-|\.\.\.?|\u2025|\u2026|\u22EF)(-?\d+)$/)){let[e,a,r,o]=t;if(a&&o){a=parseInt(a),o=parseInt(o);const e=a<o?1:-1;"-"!==r&&".."!==r&&"\u2025"!==r||(o+=e);for(let t=a;t!==o;t+=e)n.push(t)}}return n}t.default=n,e.exports=n},4544:(e,t,n)=>{"use strict";n.r(t),n.d(t,{Prism:()=>a.default,default:()=>p,defaultProps:()=>l});var a=n(7410);const r={plain:{backgroundColor:"#2a2734",color:"#9a86fd"},styles:[{types:["comment","prolog","doctype","cdata","punctuation"],style:{color:"#6c6783"}},{types:["namespace"],style:{opacity:.7}},{types:["tag","operator","number"],style:{color:"#e09142"}},{types:["property","function"],style:{color:"#9a86fd"}},{types:["tag-id","selector","atrule-id"],style:{color:"#eeebff"}},{types:["attr-name"],style:{color:"#c4b9fe"}},{types:["boolean","string","entity","url","attr-value","keyword","control","directive","unit","statement","regex","at-rule","placeholder","variable"],style:{color:"#ffcc99"}},{types:["deleted"],style:{textDecorationLine:"line-through"}},{types:["inserted"],style:{textDecorationLine:"underline"}},{types:["italic"],style:{fontStyle:"italic"}},{types:["important","bold"],style:{fontWeight:"bold"}},{types:["important"],style:{color:"#c4b9fe"}}]};var o=n(7294),l={Prism:a.default,theme:r};function s(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function i(){return(i=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var n=arguments[t];for(var a in n)Object.prototype.hasOwnProperty.call(n,a)&&(e[a]=n[a])}return e}).apply(this,arguments)}var c=/\r\n|\r|\n/,u=function(e){0===e.length?e.push({types:["plain"],content:"\n",empty:!0}):1===e.length&&""===e[0].content&&(e[0].content="\n",e[0].empty=!0)},d=function(e,t){var n=e.length;return n>0&&e[n-1]===t?e:e.concat(t)},h=function(e,t){var n=e.plain,a=Object.create(null),r=e.styles.reduce((function(e,n){var a=n.languages,r=n.style;return a&&!a.includes(t)||n.types.forEach((function(t){var n=i({},e[t],r);e[t]=n})),e}),a);return r.root=n,r.plain=i({},n,{backgroundColor:null}),r};function f(e,t){var n={};for(var a in e)Object.prototype.hasOwnProperty.call(e,a)&&-1===t.indexOf(a)&&(n[a]=e[a]);return n}const p=function(e){function t(){for(var t=this,n=[],a=arguments.length;a--;)n[a]=arguments[a];e.apply(this,n),s(this,"getThemeDict",(function(e){if(void 0!==t.themeDict&&e.theme===t.prevTheme&&e.language===t.prevLanguage)return t.themeDict;t.prevTheme=e.theme,t.prevLanguage=e.language;var n=e.theme?h(e.theme,e.language):void 0;return t.themeDict=n})),s(this,"getLineProps",(function(e){var n=e.key,a=e.className,r=e.style,o=i({},f(e,["key","className","style","line"]),{className:"token-line",style:void 0,key:void 0}),l=t.getThemeDict(t.props);return void 0!==l&&(o.style=l.plain),void 0!==r&&(o.style=void 0!==o.style?i({},o.style,r):r),void 0!==n&&(o.key=n),a&&(o.className+=" "+a),o})),s(this,"getStyleForToken",(function(e){var n=e.types,a=e.empty,r=n.length,o=t.getThemeDict(t.props);if(void 0!==o){if(1===r&&"plain"===n[0])return a?{display:"inline-block"}:void 0;if(1===r&&!a)return o[n[0]];var l=a?{display:"inline-block"}:{},s=n.map((function(e){return o[e]}));return Object.assign.apply(Object,[l].concat(s))}})),s(this,"getTokenProps",(function(e){var n=e.key,a=e.className,r=e.style,o=e.token,l=i({},f(e,["key","className","style","token"]),{className:"token "+o.types.join(" "),children:o.content,style:t.getStyleForToken(o),key:void 0});return void 0!==r&&(l.style=void 0!==l.style?i({},l.style,r):r),void 0!==n&&(l.key=n),a&&(l.className+=" "+a),l})),s(this,"tokenize",(function(e,t,n,a){var r={code:t,grammar:n,language:a,tokens:[]};e.hooks.run("before-tokenize",r);var o=r.tokens=e.tokenize(r.code,r.grammar,r.language);return e.hooks.run("after-tokenize",r),o}))}return e&&(t.__proto__=e),t.prototype=Object.create(e&&e.prototype),t.prototype.constructor=t,t.prototype.render=function(){var e=this.props,t=e.Prism,n=e.language,a=e.code,r=e.children,o=this.getThemeDict(this.props),l=t.languages[n];return r({tokens:function(e){for(var t=[[]],n=[e],a=[0],r=[e.length],o=0,l=0,s=[],i=[s];l>-1;){for(;(o=a[l]++)<r[l];){var h=void 0,f=t[l],p=n[l][o];if("string"==typeof p?(f=l>0?f:["plain"],h=p):(f=d(f,p.type),p.alias&&(f=d(f,p.alias)),h=p.content),"string"==typeof h){var m=h.split(c),v=m.length;s.push({types:f,content:m[0]});for(var b=1;b<v;b++)u(s),i.push(s=[]),s.push({types:f,content:m[b]})}else l++,t.push(f),n.push(h),a.push(0),r.push(h.length)}l--,t.pop(),n.pop(),a.pop(),r.pop()}return u(s),i}(void 0!==l?this.tokenize(t,a,l,n):[a]),className:"prism-code language-"+n,style:void 0!==o?o.root:{},getLineProps:this.getLineProps,getTokenProps:this.getTokenProps})},t}(o.Component)},7552:(e,t,n)=>{"use strict";n.r(t),n.d(t,{default:()=>a});const a={plain:{color:"#bfc7d5",backgroundColor:"#292d3e"},styles:[{types:["comment"],style:{color:"rgb(105, 112, 152)",fontStyle:"italic"}},{types:["string","inserted"],style:{color:"rgb(195, 232, 141)"}},{types:["number"],style:{color:"rgb(247, 140, 108)"}},{types:["builtin","char","constant","function"],style:{color:"rgb(130, 170, 255)"}},{types:["punctuation","selector"],style:{color:"rgb(199, 146, 234)"}},{types:["variable"],style:{color:"rgb(191, 199, 213)"}},{types:["class-name","attr-name"],style:{color:"rgb(255, 203, 107)"}},{types:["tag","deleted"],style:{color:"rgb(255, 85, 114)"}},{types:["operator"],style:{color:"rgb(137, 221, 255)"}},{types:["boolean"],style:{color:"rgb(255, 88, 116)"}},{types:["keyword"],style:{fontStyle:"italic"}},{types:["doctype"],style:{color:"rgb(199, 146, 234)",fontStyle:"italic"}},{types:["namespace"],style:{color:"rgb(178, 204, 214)"}},{types:["url"],style:{color:"rgb(221, 221, 221)"}}]}},4971:function(e,t,n){var a;e=n.nmd(e),function(r){t&&t.nodeType,e&&e.nodeType;var o="object"==typeof n.g&&n.g;o.global!==o&&o.window!==o&&o.self;var l,s=2147483647,i=36,c=/^xn--/,u=/[^\x20-\x7E]/,d=/[\x2E\u3002\uFF0E\uFF61]/g,h={overflow:"Overflow: input needs wider integers to process","not-basic":"Illegal input >= 0x80 (not a basic code point)","invalid-input":"Invalid input"},f=Math.floor,p=String.fromCharCode;function m(e){throw RangeError(h[e])}function v(e,t){for(var n=e.length,a=[];n--;)a[n]=t(e[n]);return a}function b(e,t){var n=e.split("@"),a="";return n.length>1&&(a=n[0]+"@",e=n[1]),a+v((e=e.replace(d,".")).split("."),t).join(".")}function y(e){for(var t,n,a=[],r=0,o=e.length;r<o;)(t=e.charCodeAt(r++))>=55296&&t<=56319&&r<o?56320==(64512&(n=e.charCodeAt(r++)))?a.push(((1023&t)<<10)+(1023&n)+65536):(a.push(t),r--):a.push(t);return a}function g(e){return v(e,(function(e){var t="";return e>65535&&(t+=p((e-=65536)>>>10&1023|55296),e=56320|1023&e),t+=p(e)})).join("")}function k(e,t){return e+22+75*(e<26)-((0!=t)<<5)}function E(e,t,n){var a=0;for(e=n?f(e/700):e>>1,e+=f(e/t);e>455;a+=i)e=f(e/35);return f(a+36*e/(e+38))}function C(e){var t,n,a,r,o,l,c,u,d,h,p,v=[],b=e.length,y=0,k=128,C=72;for((n=e.lastIndexOf("-"))<0&&(n=0),a=0;a<n;++a)e.charCodeAt(a)>=128&&m("not-basic"),v.push(e.charCodeAt(a));for(r=n>0?n+1:0;r<b;){for(o=y,l=1,c=i;r>=b&&m("invalid-input"),((u=(p=e.charCodeAt(r++))-48<10?p-22:p-65<26?p-65:p-97<26?p-97:i)>=i||u>f((s-y)/l))&&m("overflow"),y+=u*l,!(u<(d=c<=C?1:c>=C+26?26:c-C));c+=i)l>f(s/(h=i-d))&&m("overflow"),l*=h;C=E(y-o,t=v.length+1,0==o),f(y/t)>s-k&&m("overflow"),k+=f(y/t),y%=t,v.splice(y++,0,k)}return g(v)}function j(e){var t,n,a,r,o,l,c,u,d,h,v,b,g,C,j,O=[];for(b=(e=y(e)).length,t=128,n=0,o=72,l=0;l<b;++l)(v=e[l])<128&&O.push(p(v));for(a=r=O.length,r&&O.push("-");a<b;){for(c=s,l=0;l<b;++l)(v=e[l])>=t&&v<c&&(c=v);for(c-t>f((s-n)/(g=a+1))&&m("overflow"),n+=(c-t)*g,t=c,l=0;l<b;++l)if((v=e[l])<t&&++n>s&&m("overflow"),v==t){for(u=n,d=i;!(u<(h=d<=o?1:d>=o+26?26:d-o));d+=i)j=u-h,C=i-h,O.push(p(k(h+j%C,0))),u=f(j/C);O.push(p(k(u,0))),o=E(n,g,a==r),n=0,++a}++n,++t}return O.join("")}l={version:"1.3.2",ucs2:{decode:y,encode:g},decode:C,encode:j,toASCII:function(e){return b(e,(function(e){return u.test(e)?"xn--"+j(e):e}))},toUnicode:function(e){return b(e,(function(e){return c.test(e)?C(e.slice(4).toLowerCase()):e}))}},void 0===(a=function(){return l}.call(t,n,t,e))||(e.exports=a)}()},2587:e=>{"use strict";function t(e,t){return Object.prototype.hasOwnProperty.call(e,t)}e.exports=function(e,n,a,r){n=n||"&",a=a||"=";var o={};if("string"!=typeof e||0===e.length)return o;var l=/\+/g;e=e.split(n);var s=1e3;r&&"number"==typeof r.maxKeys&&(s=r.maxKeys);var i=e.length;s>0&&i>s&&(i=s);for(var c=0;c<i;++c){var u,d,h,f,p=e[c].replace(l,"%20"),m=p.indexOf(a);m>=0?(u=p.substr(0,m),d=p.substr(m+1)):(u=p,d=""),h=decodeURIComponent(u),f=decodeURIComponent(d),t(o,h)?Array.isArray(o[h])?o[h].push(f):o[h]=[o[h],f]:o[h]=f}return o}},2361:e=>{"use strict";var t=function(e){switch(typeof e){case"string":return e;case"boolean":return e?"true":"false";case"number":return isFinite(e)?e:"";default:return""}};e.exports=function(e,n,a,r){return n=n||"&",a=a||"=",null===e&&(e=void 0),"object"==typeof e?Object.keys(e).map((function(r){var o=encodeURIComponent(t(r))+a;return Array.isArray(e[r])?e[r].map((function(e){return o+encodeURIComponent(t(e))})).join(n):o+encodeURIComponent(t(e[r]))})).join(n):r?encodeURIComponent(t(r))+a+encodeURIComponent(t(e)):""}},7673:(e,t,n)=>{"use strict";t.decode=t.parse=n(2587),t.encode=t.stringify=n(2361)},8575:(e,t,n)=>{"use strict";var a=n(4971),r=n(2502);function o(){this.protocol=null,this.slashes=null,this.auth=null,this.host=null,this.port=null,this.hostname=null,this.hash=null,this.search=null,this.query=null,this.pathname=null,this.path=null,this.href=null}t.parse=g,t.resolve=function(e,t){return g(e,!1,!0).resolve(t)},t.resolveObject=function(e,t){return e?g(e,!1,!0).resolveObject(t):t},t.format=function(e){r.isString(e)&&(e=g(e));return e instanceof o?e.format():o.prototype.format.call(e)},t.Url=o;var l=/^([a-z0-9.+-]+:)/i,s=/:[0-9]*$/,i=/^(\/\/?(?!\/)[^\?\s]*)(\?[^\s]*)?$/,c=["{","}","|","\\","^","`"].concat(["<",">",'"',"`"," ","\r","\n","\t"]),u=["'"].concat(c),d=["%","/","?",";","#"].concat(u),h=["/","?","#"],f=/^[+a-z0-9A-Z_-]{0,63}$/,p=/^([+a-z0-9A-Z_-]{0,63})(.*)$/,m={javascript:!0,"javascript:":!0},v={javascript:!0,"javascript:":!0},b={http:!0,https:!0,ftp:!0,gopher:!0,file:!0,"http:":!0,"https:":!0,"ftp:":!0,"gopher:":!0,"file:":!0},y=n(7673);function g(e,t,n){if(e&&r.isObject(e)&&e instanceof o)return e;var a=new o;return a.parse(e,t,n),a}o.prototype.parse=function(e,t,n){if(!r.isString(e))throw new TypeError("Parameter 'url' must be a string, not "+typeof e);var o=e.indexOf("?"),s=-1!==o&&o<e.indexOf("#")?"?":"#",c=e.split(s);c[0]=c[0].replace(/\\/g,"/");var g=e=c.join(s);if(g=g.trim(),!n&&1===e.split("#").length){var k=i.exec(g);if(k)return this.path=g,this.href=g,this.pathname=k[1],k[2]?(this.search=k[2],this.query=t?y.parse(this.search.substr(1)):this.search.substr(1)):t&&(this.search="",this.query={}),this}var E=l.exec(g);if(E){var C=(E=E[0]).toLowerCase();this.protocol=C,g=g.substr(E.length)}if(n||E||g.match(/^\/\/[^@\/]+@[^@\/]+/)){var j="//"===g.substr(0,2);!j||E&&v[E]||(g=g.substr(2),this.slashes=!0)}if(!v[E]&&(j||E&&!b[E])){for(var O,_,x=-1,N=0;N<h.length;N++){-1!==(w=g.indexOf(h[N]))&&(-1===x||w<x)&&(x=w)}-1!==(_=-1===x?g.lastIndexOf("@"):g.lastIndexOf("@",x))&&(O=g.slice(0,_),g=g.slice(_+1),this.auth=decodeURIComponent(O)),x=-1;for(N=0;N<d.length;N++){var w;-1!==(w=g.indexOf(d[N]))&&(-1===x||w<x)&&(x=w)}-1===x&&(x=g.length),this.host=g.slice(0,x),g=g.slice(x),this.parseHost(),this.hostname=this.hostname||"";var S="["===this.hostname[0]&&"]"===this.hostname[this.hostname.length-1];if(!S)for(var P=this.hostname.split(/\./),T=(N=0,P.length);N<T;N++){var I=P[N];if(I&&!I.match(f)){for(var A="",B=0,L=I.length;B<L;B++)I.charCodeAt(B)>127?A+="x":A+=I[B];if(!A.match(f)){var M=P.slice(0,N),R=P.slice(N+1),D=I.match(p);D&&(M.push(D[1]),R.unshift(D[2])),R.length&&(g="/"+R.join(".")+g),this.hostname=M.join(".");break}}}this.hostname.length>255?this.hostname="":this.hostname=this.hostname.toLowerCase(),S||(this.hostname=a.toASCII(this.hostname));var U=this.port?":"+this.port:"",q=this.hostname||"";this.host=q+U,this.href+=this.host,S&&(this.hostname=this.hostname.substr(1,this.hostname.length-2),"/"!==g[0]&&(g="/"+g))}if(!m[C])for(N=0,T=u.length;N<T;N++){var W=u[N];if(-1!==g.indexOf(W)){var H=encodeURIComponent(W);H===W&&(H=escape(W)),g=g.split(W).join(H)}}var F=g.indexOf("#");-1!==F&&(this.hash=g.substr(F),g=g.slice(0,F));var $=g.indexOf("?");if(-1!==$?(this.search=g.substr($),this.query=g.substr($+1),t&&(this.query=y.parse(this.query)),g=g.slice(0,$)):t&&(this.search="",this.query={}),g&&(this.pathname=g),b[C]&&this.hostname&&!this.pathname&&(this.pathname="/"),this.pathname||this.search){U=this.pathname||"";var z=this.search||"";this.path=U+z}return this.href=this.format(),this},o.prototype.format=function(){var e=this.auth||"";e&&(e=(e=encodeURIComponent(e)).replace(/%3A/i,":"),e+="@");var t=this.protocol||"",n=this.pathname||"",a=this.hash||"",o=!1,l="";this.host?o=e+this.host:this.hostname&&(o=e+(-1===this.hostname.indexOf(":")?this.hostname:"["+this.hostname+"]"),this.port&&(o+=":"+this.port)),this.query&&r.isObject(this.query)&&Object.keys(this.query).length&&(l=y.stringify(this.query));var s=this.search||l&&"?"+l||"";return t&&":"!==t.substr(-1)&&(t+=":"),this.slashes||(!t||b[t])&&!1!==o?(o="//"+(o||""),n&&"/"!==n.charAt(0)&&(n="/"+n)):o||(o=""),a&&"#"!==a.charAt(0)&&(a="#"+a),s&&"?"!==s.charAt(0)&&(s="?"+s),t+o+(n=n.replace(/[?#]/g,(function(e){return encodeURIComponent(e)})))+(s=s.replace("#","%23"))+a},o.prototype.resolve=function(e){return this.resolveObject(g(e,!1,!0)).format()},o.prototype.resolveObject=function(e){if(r.isString(e)){var t=new o;t.parse(e,!1,!0),e=t}for(var n=new o,a=Object.keys(this),l=0;l<a.length;l++){var s=a[l];n[s]=this[s]}if(n.hash=e.hash,""===e.href)return n.href=n.format(),n;if(e.slashes&&!e.protocol){for(var i=Object.keys(e),c=0;c<i.length;c++){var u=i[c];"protocol"!==u&&(n[u]=e[u])}return b[n.protocol]&&n.hostname&&!n.pathname&&(n.path=n.pathname="/"),n.href=n.format(),n}if(e.protocol&&e.protocol!==n.protocol){if(!b[e.protocol]){for(var d=Object.keys(e),h=0;h<d.length;h++){var f=d[h];n[f]=e[f]}return n.href=n.format(),n}if(n.protocol=e.protocol,e.host||v[e.protocol])n.pathname=e.pathname;else{for(var p=(e.pathname||"").split("/");p.length&&!(e.host=p.shift()););e.host||(e.host=""),e.hostname||(e.hostname=""),""!==p[0]&&p.unshift(""),p.length<2&&p.unshift(""),n.pathname=p.join("/")}if(n.search=e.search,n.query=e.query,n.host=e.host||"",n.auth=e.auth,n.hostname=e.hostname||e.host,n.port=e.port,n.pathname||n.search){var m=n.pathname||"",y=n.search||"";n.path=m+y}return n.slashes=n.slashes||e.slashes,n.href=n.format(),n}var g=n.pathname&&"/"===n.pathname.charAt(0),k=e.host||e.pathname&&"/"===e.pathname.charAt(0),E=k||g||n.host&&e.pathname,C=E,j=n.pathname&&n.pathname.split("/")||[],O=(p=e.pathname&&e.pathname.split("/")||[],n.protocol&&!b[n.protocol]);if(O&&(n.hostname="",n.port=null,n.host&&(""===j[0]?j[0]=n.host:j.unshift(n.host)),n.host="",e.protocol&&(e.hostname=null,e.port=null,e.host&&(""===p[0]?p[0]=e.host:p.unshift(e.host)),e.host=null),E=E&&(""===p[0]||""===j[0])),k)n.host=e.host||""===e.host?e.host:n.host,n.hostname=e.hostname||""===e.hostname?e.hostname:n.hostname,n.search=e.search,n.query=e.query,j=p;else if(p.length)j||(j=[]),j.pop(),j=j.concat(p),n.search=e.search,n.query=e.query;else if(!r.isNullOrUndefined(e.search)){if(O)n.hostname=n.host=j.shift(),(S=!!(n.host&&n.host.indexOf("@")>0)&&n.host.split("@"))&&(n.auth=S.shift(),n.host=n.hostname=S.shift());return n.search=e.search,n.query=e.query,r.isNull(n.pathname)&&r.isNull(n.search)||(n.path=(n.pathname?n.pathname:"")+(n.search?n.search:"")),n.href=n.format(),n}if(!j.length)return n.pathname=null,n.search?n.path="/"+n.search:n.path=null,n.href=n.format(),n;for(var _=j.slice(-1)[0],x=(n.host||e.host||j.length>1)&&("."===_||".."===_)||""===_,N=0,w=j.length;w>=0;w--)"."===(_=j[w])?j.splice(w,1):".."===_?(j.splice(w,1),N++):N&&(j.splice(w,1),N--);if(!E&&!C)for(;N--;N)j.unshift("..");!E||""===j[0]||j[0]&&"/"===j[0].charAt(0)||j.unshift(""),x&&"/"!==j.join("/").substr(-1)&&j.push("");var S,P=""===j[0]||j[0]&&"/"===j[0].charAt(0);O&&(n.hostname=n.host=P?"":j.length?j.shift():"",(S=!!(n.host&&n.host.indexOf("@")>0)&&n.host.split("@"))&&(n.auth=S.shift(),n.host=n.hostname=S.shift()));return(E=E||n.host&&j.length)&&!P&&j.unshift(""),j.length?n.pathname=j.join("/"):(n.pathname=null,n.path=null),r.isNull(n.pathname)&&r.isNull(n.search)||(n.path=(n.pathname?n.pathname:"")+(n.search?n.search:"")),n.auth=e.auth||n.auth,n.slashes=n.slashes||e.slashes,n.href=n.format(),n},o.prototype.parseHost=function(){var e=this.host,t=s.exec(e);t&&(":"!==(t=t[0])&&(this.port=t.substr(1)),e=e.substr(0,e.length-t.length)),e&&(this.hostname=e)}},2502:e=>{"use strict";e.exports={isString:function(e){return"string"==typeof e},isObject:function(e){return"object"==typeof e&&null!==e},isNull:function(e){return null===e},isNullOrUndefined:function(e){return null==e}}}}]);