(self.webpackChunk=self.webpackChunk||[]).push([[641],{3905:(e,t,a)=>{"use strict";a.r(t),a.d(t,{MDXContext:()=>o,MDXProvider:()=>m,mdx:()=>f,useMDXComponents:()=>d,withMDXComponents:()=>u});var n=a(7294);function r(e,t,a){return t in e?Object.defineProperty(e,t,{value:a,enumerable:!0,configurable:!0,writable:!0}):e[t]=a,e}function i(){return(i=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var a=arguments[t];for(var n in a)Object.prototype.hasOwnProperty.call(a,n)&&(e[n]=a[n])}return e}).apply(this,arguments)}function s(e,t){var a=Object.keys(e);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(e);t&&(n=n.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),a.push.apply(a,n)}return a}function l(e){for(var t=1;t<arguments.length;t++){var a=null!=arguments[t]?arguments[t]:{};t%2?s(Object(a),!0).forEach((function(t){r(e,t,a[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(a)):s(Object(a)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(a,t))}))}return e}function c(e,t){if(null==e)return{};var a,n,r=function(e,t){if(null==e)return{};var a,n,r={},i=Object.keys(e);for(n=0;n<i.length;n++)a=i[n],t.indexOf(a)>=0||(r[a]=e[a]);return r}(e,t);if(Object.getOwnPropertySymbols){var i=Object.getOwnPropertySymbols(e);for(n=0;n<i.length;n++)a=i[n],t.indexOf(a)>=0||Object.prototype.propertyIsEnumerable.call(e,a)&&(r[a]=e[a])}return r}var o=n.createContext({}),u=function(e){return function(t){var a=d(t.components);return n.createElement(e,i({},t,{components:a}))}},d=function(e){var t=n.useContext(o),a=t;return e&&(a="function"==typeof e?e(t):l(l({},t),e)),a},m=function(e){var t=d(e.components);return n.createElement(o.Provider,{value:t},e.children)},p={inlineCode:"code",wrapper:function(e){var t=e.children;return n.createElement(n.Fragment,{},t)}},b=n.forwardRef((function(e,t){var a=e.components,r=e.mdxType,i=e.originalType,s=e.parentName,o=c(e,["components","mdxType","originalType","parentName"]),u=d(a),m=r,b=u["".concat(s,".").concat(m)]||u[m]||p[m]||i;return a?n.createElement(b,l(l({ref:t},o),{},{components:a})):n.createElement(b,l({ref:t},o))}));function f(e,t){var a=arguments,r=t&&t.mdxType;if("string"==typeof e||r){var i=a.length,s=new Array(i);s[0]=b;var l={};for(var c in t)hasOwnProperty.call(t,c)&&(l[c]=t[c]);l.originalType=e,l.mdxType="string"==typeof e?e:r,s[1]=l;for(var o=2;o<i;o++)s[o]=a[o];return n.createElement.apply(null,s)}return n.createElement.apply(null,a)}b.displayName="MDXCreateElement"},8215:(e,t,a)=>{"use strict";var n=a(5318).default;t.Z=void 0;var r=n(a(7294));var i=function(e){var t=e.children,a=e.hidden,n=e.className;return r.default.createElement("div",{role:"tabpanel",hidden:a,className:n},t)};t.Z=i},279:(e,t,a)=>{"use strict";var n=a(5318).default,r=a(862).default;t.Z=void 0;var i=r(a(7294)),s=n(a(944)),l=n(a(6010)),c=n(a(3693));var o=37,u=39;var d=function(e){var t=e.lazy,a=e.block,n=e.defaultValue,r=e.values,d=e.groupId,m=e.className,p=(0,s.default)(),b=p.tabGroupChoices,f=p.setTabGroupChoices,v=(0,i.useState)(n),y=v[0],g=v[1],h=i.Children.toArray(e.children),x=[];if(null!=d){var j=b[d];null!=j&&j!==y&&r.some((function(e){return e.value===j}))&&g(j)}var T=function(e){var t=e.currentTarget,a=x.indexOf(t),n=r[a].value;g(n),null!=d&&(f(d,n),setTimeout((function(){var e,a,n,r,i,s,l,o;(e=t.getBoundingClientRect(),a=e.top,n=e.left,r=e.bottom,i=e.right,s=window,l=s.innerHeight,o=s.innerWidth,a>=0&&i<=o&&r<=l&&n>=0)||(t.scrollIntoView({block:"center",behavior:"smooth"}),t.classList.add(c.default.tabItemActive),setTimeout((function(){return t.classList.remove(c.default.tabItemActive)}),2e3))}),150))},w=function(e){var t,a;switch(e.keyCode){case u:var n=x.indexOf(e.target)+1;a=x[n]||x[0];break;case o:var r=x.indexOf(e.target)-1;a=x[r]||x[x.length-1]}null==(t=a)||t.focus()};return i.default.createElement("div",{className:"tabs-container"},i.default.createElement("ul",{role:"tablist","aria-orientation":"horizontal",className:(0,l.default)("tabs",{"tabs--block":a},m)},r.map((function(e){var t=e.value,a=e.label;return i.default.createElement("li",{role:"tab",tabIndex:y===t?0:-1,"aria-selected":y===t,className:(0,l.default)("tabs__item",c.default.tabItem,{"tabs__item--active":y===t}),key:t,ref:function(e){return x.push(e)},onKeyDown:w,onFocus:T,onClick:T},a)}))),t?(0,i.cloneElement)(h.filter((function(e){return e.props.value===y}))[0],{className:"margin-vert--md"}):i.default.createElement("div",{className:"margin-vert--md"},h.map((function(e,t){return(0,i.cloneElement)(e,{key:t,hidden:e.props.value!==y})}))))};t.Z=d},9443:(e,t,a)=>{"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var n=(0,a(7294).createContext)(void 0);t.default=n},944:(e,t,a)=>{"use strict";var n=a(5318).default;Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var r=a(7294),i=n(a(9443));var s=function(){var e=(0,r.useContext)(i.default);if(null==e)throw new Error("`useUserPreferencesContext` is used outside of `Layout` Component.");return e};t.default=s},6690:(e,t,a)=>{"use strict";a.r(t),a.d(t,{frontMatter:()=>o,metadata:()=>u,toc:()=>d,default:()=>p});var n=a(2122),r=a(9756),i=(a(7294),a(3905)),s=a(279),l=a(8215),c=["components"],o={id:"accessibility",title:"Accessibility",sidebar_label:"Accessibility"},u={unversionedId:"accessibility",id:"accessibility",isDocsHomePage:!1,title:"Accessibility",description:"As of version 1.2, Sauce Bindings supports the new Sauce Labs",source:"@site/docs/ACCESSIBILITY.md",sourceDirName:".",slug:"/accessibility",permalink:"/sauce_bindings/docs/accessibility",editUrl:"https://github.com/saucelabs/sauce_bindings/edit/main/website/docs/ACCESSIBILITY.md",version:"current",lastUpdatedBy:"titusfortner",lastUpdatedAt:1626393503,formattedLastUpdatedAt:"7/15/2021",sidebar_label:"Accessibility",frontMatter:{id:"accessibility",title:"Accessibility",sidebar_label:"Accessibility"},sidebar:"docs",previous:{title:"Test Runners",permalink:"/sauce_bindings/docs/test-runners"},next:{title:"Roadmap",permalink:"/sauce_bindings/docs/roadmap"}},d=[],m={toc:d};function p(e){var t=e.components,a=(0,r.default)(e,c);return(0,i.mdx)("wrapper",(0,n.default)({},m,a,{components:t,mdxType:"MDXLayout"}),(0,i.mdx)("p",null,"As of version 1.2, Sauce Bindings supports the new Sauce Labs\n",(0,i.mdx)("a",{parentName:"p",href:"https://docs.saucelabs.com/basics/integrations/deque/index.html"},"Deque axe\u2122 Integration")),(0,i.mdx)("ul",null,(0,i.mdx)("li",{parentName:"ul"},"The Session classes now have a method to obtain accessibility results, which does two basic things:",(0,i.mdx)("ul",{parentName:"li"},(0,i.mdx)("li",{parentName:"ul"},"Populates the new Accessibility tab in the Sauce Labs UI for a given job\nwith the accessibility violations found during the test run."),(0,i.mdx)("li",{parentName:"ul"},"Returns the specific violations found in the code. The format of these results varies by language, and it is left to the user\nto determine how best to make use of them in their code if so desired."))),(0,i.mdx)("li",{parentName:"ul"},"The Java code is implemented using Deque's ",(0,i.mdx)("a",{parentName:"li",href:"https://github.com/dequelabs/axe-core-maven-html"},"Axe Core Maven HTML Jar"),(0,i.mdx)("ul",{parentName:"li"},(0,i.mdx)("li",{parentName:"ul"},"This implementation provides access to all the advanced features."),(0,i.mdx)("li",{parentName:"ul"},"As a wrapper, Sauce bindings provides 3 method signatures for getting accessibility results, as outlined below."))),(0,i.mdx)("li",{parentName:"ul"},"The Ruby and Python code is implemented with the new ",(0,i.mdx)("a",{parentName:"li",href:"https://github.com/saucelabs/sa11y"},"sa11y")," project",(0,i.mdx)("ul",{parentName:"li"},(0,i.mdx)("li",{parentName:"ul"},"Sa11y is a minimalist implementation of Deque's axe\u2122 functionality."),(0,i.mdx)("li",{parentName:"ul"},"The accessibility results method allows you to specify a different js library, the ability to turn off frame support,\nand the ability to turn on cross-origin frame support. Examples are provided below.")))),(0,i.mdx)(s.Z,{defaultValue:"java",values:[{label:"Java",value:"java"},{label:"Python",value:"python"},{label:"Ruby",value:"ruby"},{label:"C#",value:"csharp"}],mdxType:"Tabs"},(0,i.mdx)(l.Z,{value:"java",mdxType:"TabItem"},(0,i.mdx)(s.Z,{defaultValue:"junit5",values:[{label:"JUnit 5",value:"junit5"},{label:"JUnit 4",value:"junit4"},{label:"TestNG",value:"testng"},{label:"Direct",value:"direct"}],mdxType:"Tabs"},(0,i.mdx)(l.Z,{value:"junit5",mdxType:"TabItem"},(0,i.mdx)("pre",null,(0,i.mdx)("code",{parentName:"pre",className:"language-java",metastring:"reference",reference:!0},"https://github.com/saucelabs/sauce_bindings/tree/website-1.2.0/java/junit5/src/test/java/com/saucelabs/saucebindings/junit5/examples/AccessibilityTest.java\n"))),(0,i.mdx)(l.Z,{value:"junit4",mdxType:"TabItem"},(0,i.mdx)("pre",null,(0,i.mdx)("code",{parentName:"pre",className:"language-java",metastring:"reference",reference:!0},"https://github.com/saucelabs/sauce_bindings/tree/website-1.2.0/java/junit4/src/test/java/com/saucelabs/saucebindings/junit4/examples/AccessibilityTest.java\n"))),(0,i.mdx)(l.Z,{value:"testng",mdxType:"TabItem"},(0,i.mdx)("pre",null,(0,i.mdx)("code",{parentName:"pre",className:"language-java",metastring:"reference",reference:!0},"https://github.com/saucelabs/sauce_bindings/tree/website-1.2.0/java/testng/src/test/java/com/saucelabs/saucebindings/testng/examples/AccessibilityTest.java\n"))),(0,i.mdx)(l.Z,{value:"direct",mdxType:"TabItem"},(0,i.mdx)("pre",null,(0,i.mdx)("code",{parentName:"pre",className:"language-java",metastring:"reference",reference:!0},"https://github.com/saucelabs/sauce_bindings/tree/website-1.2.0/java/main/src/test/java/com/saucelabs/saucebindings/examples/AccessibilityTest.java\n"))))),(0,i.mdx)(l.Z,{value:"python",mdxType:"TabItem"},(0,i.mdx)("pre",null,(0,i.mdx)("code",{parentName:"pre",className:"language-python",metastring:"reference",reference:!0},"https://github.com/saucelabs/sauce_bindings/tree/website-1.2.0/python/tests/examples/test_accessibility.py\n"))),(0,i.mdx)(l.Z,{value:"ruby",mdxType:"TabItem"},(0,i.mdx)("pre",null,(0,i.mdx)("code",{parentName:"pre",className:"language-ruby",metastring:"reference",reference:!0},"https://github.com/saucelabs/sauce_bindings/tree/website-1.2.0/ruby/spec/examples/accessibility_spec.rb\n"))),(0,i.mdx)(l.Z,{value:"csharp",mdxType:"TabItem"},(0,i.mdx)("p",null,(0,i.mdx)("strong",{parentName:"p"},"C# bindings are coming soon...")))))}p.isMDXComponent=!0},6010:(e,t,a)=>{"use strict";function n(e){var t,a,r="";if("string"==typeof e||"number"==typeof e)r+=e;else if("object"==typeof e)if(Array.isArray(e))for(t=0;t<e.length;t++)e[t]&&(a=n(e[t]))&&(r&&(r+=" "),r+=a);else for(t in e)e[t]&&(r&&(r+=" "),r+=t);return r}function r(){for(var e,t,a=0,r="";a<arguments.length;)(e=arguments[a++])&&(t=n(e))&&(r&&(r+=" "),r+=t);return r}a.r(t),a.d(t,{default:()=>r})},3693:(e,t,a)=>{"use strict";a.r(t),a.d(t,{default:()=>n});const n={tabItem:"tabItem_1uMI",tabItemActive:"tabItemActive_2DSg",blink:"blink_XB8L"}}}]);