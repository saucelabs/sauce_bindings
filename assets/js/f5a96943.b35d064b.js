"use strict";(self.webpackChunk=self.webpackChunk||[]).push([[744],{3905:(e,t,a)=>{a.r(t),a.d(t,{MDXContext:()=>u,MDXProvider:()=>d,mdx:()=>v,useMDXComponents:()=>m,withMDXComponents:()=>c});var n=a(7294);function r(e,t,a){return t in e?Object.defineProperty(e,t,{value:a,enumerable:!0,configurable:!0,writable:!0}):e[t]=a,e}function i(){return i=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var a=arguments[t];for(var n in a)Object.prototype.hasOwnProperty.call(a,n)&&(e[n]=a[n])}return e},i.apply(this,arguments)}function o(e,t){var a=Object.keys(e);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(e);t&&(n=n.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),a.push.apply(a,n)}return a}function s(e){for(var t=1;t<arguments.length;t++){var a=null!=arguments[t]?arguments[t]:{};t%2?o(Object(a),!0).forEach((function(t){r(e,t,a[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(a)):o(Object(a)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(a,t))}))}return e}function l(e,t){if(null==e)return{};var a,n,r=function(e,t){if(null==e)return{};var a,n,r={},i=Object.keys(e);for(n=0;n<i.length;n++)a=i[n],t.indexOf(a)>=0||(r[a]=e[a]);return r}(e,t);if(Object.getOwnPropertySymbols){var i=Object.getOwnPropertySymbols(e);for(n=0;n<i.length;n++)a=i[n],t.indexOf(a)>=0||Object.prototype.propertyIsEnumerable.call(e,a)&&(r[a]=e[a])}return r}var u=n.createContext({}),c=function(e){return function(t){var a=m(t.components);return n.createElement(e,i({},t,{components:a}))}},m=function(e){var t=n.useContext(u),a=t;return e&&(a="function"==typeof e?e(t):s(s({},t),e)),a},d=function(e){var t=m(e.components);return n.createElement(u.Provider,{value:t},e.children)},p="mdxType",b={inlineCode:"code",wrapper:function(e){var t=e.children;return n.createElement(n.Fragment,{},t)}},f=n.forwardRef((function(e,t){var a=e.components,r=e.mdxType,i=e.originalType,o=e.parentName,u=l(e,["components","mdxType","originalType","parentName"]),c=m(a),d=r,p=c["".concat(o,".").concat(d)]||c[d]||b[d]||i;return a?n.createElement(p,s(s({ref:t},u),{},{components:a})):n.createElement(p,s({ref:t},u))}));function v(e,t){var a=arguments,r=t&&t.mdxType;if("string"==typeof e||r){var i=a.length,o=new Array(i);o[0]=f;var s={};for(var l in t)hasOwnProperty.call(t,l)&&(s[l]=t[l]);s.originalType=e,s[p]="string"==typeof e?e:r,o[1]=s;for(var u=2;u<i;u++)o[u]=a[u];return n.createElement.apply(null,o)}return n.createElement.apply(null,a)}f.displayName="MDXCreateElement"},8215:(e,t,a)=>{var n=a(4836).default;t.Z=void 0;var r=n(a(7294));var i=function(e){var t=e.children,a=e.hidden,n=e.className;return r.default.createElement("div",{role:"tabpanel",hidden:a,className:n},t)};t.Z=i},279:(e,t,a)=>{var n=a(4836).default,r=a(5263).default;t.Z=void 0;var i=r(a(7294)),o=n(a(944)),s=n(a(6010)),l=n(a(3693));var u={left:37,right:39};var c=function(e){var t=e.lazy,a=e.block,n=e.defaultValue,r=e.values,c=e.groupId,m=e.className,d=(0,o.default)(),p=d.tabGroupChoices,b=d.setTabGroupChoices,f=(0,i.useState)(n),v=f[0],g=f[1],h=i.Children.toArray(e.children),x=[];if(null!=c){var y=p[c];null!=y&&y!==v&&r.some((function(e){return e.value===y}))&&g(y)}var O=function(e){var t=e.currentTarget,a=x.indexOf(t),n=r[a].value;g(n),null!=c&&(b(c,n),setTimeout((function(){var e,a,n,r,i,o,s,u;(e=t.getBoundingClientRect(),a=e.top,n=e.left,r=e.bottom,i=e.right,o=window,s=o.innerHeight,u=o.innerWidth,a>=0&&i<=u&&r<=s&&n>=0)||(t.scrollIntoView({block:"center",behavior:"smooth"}),t.classList.add(l.default.tabItemActive),setTimeout((function(){return t.classList.remove(l.default.tabItemActive)}),2e3))}),150))},j=function(e){var t,a;switch(e.keyCode){case u.right:var n=x.indexOf(e.target)+1;a=x[n]||x[0];break;case u.left:var r=x.indexOf(e.target)-1;a=x[r]||x[x.length-1]}null==(t=a)||t.focus()};return i.default.createElement("div",{className:"tabs-container"},i.default.createElement("ul",{role:"tablist","aria-orientation":"horizontal",className:(0,s.default)("tabs",{"tabs--block":a},m)},r.map((function(e){var t=e.value,a=e.label;return i.default.createElement("li",{role:"tab",tabIndex:v===t?0:-1,"aria-selected":v===t,className:(0,s.default)("tabs__item",l.default.tabItem,{"tabs__item--active":v===t}),key:t,ref:function(e){return x.push(e)},onKeyDown:j,onFocus:O,onClick:O},a)}))),t?(0,i.cloneElement)(h.filter((function(e){return e.props.value===v}))[0],{className:"margin-vert--md"}):i.default.createElement("div",{className:"margin-vert--md"},h.map((function(e,t){return(0,i.cloneElement)(e,{key:t,hidden:e.props.value!==v})}))))};t.Z=c},9443:(e,t,a)=>{Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var n=(0,a(7294).createContext)(void 0);t.default=n},944:(e,t,a)=>{var n=a(4836).default;Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var r=a(7294),i=n(a(9443));var o=function(){var e=(0,r.useContext)(i.default);if(null==e)throw new Error("`useUserPreferencesContext` is used outside of `Layout` Component.");return e};t.default=o},4227:(e,t,a)=>{a.r(t),a.d(t,{default:()=>b,frontMatter:()=>u,metadata:()=>c,toc:()=>m});var n=a(7462),r=a(3366),i=(a(7294),a(3905)),o=a(279),s=a(8215),l=["components"],u={id:"basic-options",title:"Setting Common Options",sidebar_label:"Common Options"},c={unversionedId:"basic-options",id:"basic-options",isDocsHomePage:!1,title:"Setting Common Options",description:"The three capabilities that matter most to Sauce Labs users are:",source:"@site/docs/BASIC_OPTIONS.md",sourceDirName:".",slug:"/basic-options",permalink:"/sauce_bindings/basic-options",editUrl:"https://github.com/saucelabs/sauce_bindings/edit/main/website/docs/BASIC_OPTIONS.md",version:"current",lastUpdatedBy:"titusfortner",lastUpdatedAt:1626393503,formattedLastUpdatedAt:"7/16/2021",sidebar_label:"Common Options",frontMatter:{id:"basic-options",title:"Setting Common Options",sidebar_label:"Common Options"},sidebar:"docs",previous:{title:"Configuration Overview",permalink:"/sauce_bindings/configurations"},next:{title:"Setting Sauce Labs Options",permalink:"/sauce_bindings/sauce-options"}},m=[],d={toc:m},p="wrapper";function b(e){var t=e.components,a=(0,r.default)(e,l);return(0,i.mdx)(p,(0,n.default)({},d,a,{components:t,mdxType:"MDXLayout"}),(0,i.mdx)("p",null,"The ",(0,i.mdx)("a",{parentName:"p",href:"https://docs.saucelabs.com/dev/test-configuration-options/index.html#webdriver-w3c-capabilities--required"},"three capabilities")," that matter most to Sauce Labs users are: "),(0,i.mdx)("ul",null,(0,i.mdx)("li",{parentName:"ul"},(0,i.mdx)("inlineCode",{parentName:"li"},"browserName")),(0,i.mdx)("li",{parentName:"ul"},(0,i.mdx)("inlineCode",{parentName:"li"},"browserVersion")),(0,i.mdx)("li",{parentName:"ul"},(0,i.mdx)("inlineCode",{parentName:"li"},"platformName"))),(0,i.mdx)("p",null,"By default, Sauce Bindings provides the latest version of Google Chrome on Windows 10."),(0,i.mdx)("p",null,"To see what values are supported by Sauce Labs for these 3 capabilities, take a look at our\n",(0,i.mdx)("a",{parentName:"p",href:"https://saucelabs.com/platform/platform-configurator#/"},"Platform Configurator")),(0,i.mdx)("p",null,"Additionally, there are ",(0,i.mdx)("a",{parentName:"p",href:"https://docs.saucelabs.com/dev/test-configuration-options/index.html#browser-w3c-capabilities--optional"},"settings that apply to all browser sessions"),"\nthat can be configured in ",(0,i.mdx)("inlineCode",{parentName:"p"},"SauceOptions")),(0,i.mdx)("p",null,"Here's an example of running a test on Firefox and Windows 8.0, that accepts insecure certificates and changes\nthe unhandled prompt behavior to ",(0,i.mdx)("inlineCode",{parentName:"p"},'"ignore"'),":"),(0,i.mdx)(o.Z,{defaultValue:"java",values:[{label:"Java",value:"java"},{label:"Python",value:"python"},{label:"Ruby",value:"ruby"},{label:"C#",value:"csharp"}],mdxType:"Tabs"},(0,i.mdx)(s.Z,{value:"java",mdxType:"TabItem"},(0,i.mdx)("p",null,"Select the ",(0,i.mdx)("a",{parentName:"p",href:"test-runners/"},"test runner")," tab based on which library you ",(0,i.mdx)("a",{parentName:"p",href:"getting-started#language-specific--prerequisites"},"added to your ",(0,i.mdx)("inlineCode",{parentName:"a"},".pom")," file")),(0,i.mdx)(o.Z,{defaultValue:"junit5",values:[{label:"JUnit 5",value:"junit5"},{label:"JUnit 4",value:"junit4"},{label:"TestNG",value:"testng"},{label:"Direct",value:"direct"}],mdxType:"Tabs"},(0,i.mdx)(s.Z,{value:"junit5",mdxType:"TabItem"},(0,i.mdx)("pre",null,(0,i.mdx)("code",{parentName:"pre",className:"language-java",metastring:"reference",reference:!0},"https://github.com/saucelabs/sauce_bindings/tree/website-1.2.0/java/junit5/src/test/java/com/saucelabs/saucebindings/junit5/examples/CommonOptionsTest.java\n"))),(0,i.mdx)(s.Z,{value:"junit4",mdxType:"TabItem"},(0,i.mdx)("pre",null,(0,i.mdx)("code",{parentName:"pre",className:"language-java",metastring:"reference",reference:!0},"https://github.com/saucelabs/sauce_bindings/tree/website-1.2.0/java/junit4/src/test/java/com/saucelabs/saucebindings/junit4/examples/CommonOptionsTest.java\n"))),(0,i.mdx)(s.Z,{value:"testng",mdxType:"TabItem"},(0,i.mdx)("pre",null,(0,i.mdx)("code",{parentName:"pre",className:"language-java",metastring:"reference",reference:!0},"https://github.com/saucelabs/sauce_bindings/tree/website-1.2.0/java/testng/src/test/java/com/saucelabs/saucebindings/testng/examples/CommonOptionsTest.java\n"))),(0,i.mdx)(s.Z,{value:"direct",mdxType:"TabItem"},(0,i.mdx)("pre",null,(0,i.mdx)("code",{parentName:"pre",className:"language-java",metastring:"reference",reference:!0},"https://github.com/saucelabs/sauce_bindings/tree/website-1.2.0/java/main/src/test/java/com/saucelabs/saucebindings/examples/CommonOptionsTest.java\n"))))),(0,i.mdx)(s.Z,{value:"python",mdxType:"TabItem"},(0,i.mdx)("pre",null,(0,i.mdx)("code",{parentName:"pre",className:"language-python",metastring:"reference",reference:!0},"https://github.com/saucelabs/sauce_bindings/tree/website-1.2.0/python/tests/examples/test_basic_options.py\n"))),(0,i.mdx)(s.Z,{value:"ruby",mdxType:"TabItem"},(0,i.mdx)("pre",null,(0,i.mdx)("code",{parentName:"pre",className:"language-ruby",metastring:"reference",reference:!0},"https://github.com/saucelabs/sauce_bindings/tree/website-1.2.0/ruby/spec/examples/basic_options_spec.rb\n"))),(0,i.mdx)(s.Z,{value:"csharp",mdxType:"TabItem"},(0,i.mdx)("p",null,(0,i.mdx)("strong",{parentName:"p"},"C# bindings are coming soon...")))))}b.isMDXComponent=!0},6010:(e,t,a)=>{function n(e){var t,a,r="";if("string"==typeof e||"number"==typeof e)r+=e;else if("object"==typeof e)if(Array.isArray(e))for(t=0;t<e.length;t++)e[t]&&(a=n(e[t]))&&(r&&(r+=" "),r+=a);else for(t in e)e[t]&&(r&&(r+=" "),r+=t);return r}function r(){for(var e,t,a=0,r="";a<arguments.length;)(e=arguments[a++])&&(t=n(e))&&(r&&(r+=" "),r+=t);return r}a.r(t),a.d(t,{clsx:()=>r,default:()=>i});const i=r},3693:(e,t,a)=>{a.r(t),a.d(t,{default:()=>n});const n={tabItem:"tabItem_vU9c",tabItemActive:"tabItemActive_cw6a",blink:"blink_4cVI"}}}]);