(self.webpackChunk=self.webpackChunk||[]).push([[499],{3905:(e,t,a)=>{"use strict";a.r(t),a.d(t,{MDXContext:()=>u,MDXProvider:()=>m,mdx:()=>v,useMDXComponents:()=>c,withMDXComponents:()=>d});var n=a(7294);function r(e,t,a){return t in e?Object.defineProperty(e,t,{value:a,enumerable:!0,configurable:!0,writable:!0}):e[t]=a,e}function i(){return(i=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var a=arguments[t];for(var n in a)Object.prototype.hasOwnProperty.call(a,n)&&(e[n]=a[n])}return e}).apply(this,arguments)}function l(e,t){var a=Object.keys(e);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(e);t&&(n=n.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),a.push.apply(a,n)}return a}function o(e){for(var t=1;t<arguments.length;t++){var a=null!=arguments[t]?arguments[t]:{};t%2?l(Object(a),!0).forEach((function(t){r(e,t,a[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(a)):l(Object(a)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(a,t))}))}return e}function s(e,t){if(null==e)return{};var a,n,r=function(e,t){if(null==e)return{};var a,n,r={},i=Object.keys(e);for(n=0;n<i.length;n++)a=i[n],t.indexOf(a)>=0||(r[a]=e[a]);return r}(e,t);if(Object.getOwnPropertySymbols){var i=Object.getOwnPropertySymbols(e);for(n=0;n<i.length;n++)a=i[n],t.indexOf(a)>=0||Object.prototype.propertyIsEnumerable.call(e,a)&&(r[a]=e[a])}return r}var u=n.createContext({}),d=function(e){return function(t){var a=c(t.components);return n.createElement(e,i({},t,{components:a}))}},c=function(e){var t=n.useContext(u),a=t;return e&&(a="function"==typeof e?e(t):o(o({},t),e)),a},m=function(e){var t=c(e.components);return n.createElement(u.Provider,{value:t},e.children)},p={inlineCode:"code",wrapper:function(e){var t=e.children;return n.createElement(n.Fragment,{},t)}},f=n.forwardRef((function(e,t){var a=e.components,r=e.mdxType,i=e.originalType,l=e.parentName,u=s(e,["components","mdxType","originalType","parentName"]),d=c(a),m=r,f=d["".concat(l,".").concat(m)]||d[m]||p[m]||i;return a?n.createElement(f,o(o({ref:t},u),{},{components:a})):n.createElement(f,o({ref:t},u))}));function v(e,t){var a=arguments,r=t&&t.mdxType;if("string"==typeof e||r){var i=a.length,l=new Array(i);l[0]=f;var o={};for(var s in t)hasOwnProperty.call(t,s)&&(o[s]=t[s]);o.originalType=e,o.mdxType="string"==typeof e?e:r,l[1]=o;for(var u=2;u<i;u++)l[u]=a[u];return n.createElement.apply(null,l)}return n.createElement.apply(null,a)}f.displayName="MDXCreateElement"},8215:(e,t,a)=>{"use strict";var n=a(5318).default;t.Z=void 0;var r=n(a(7294));var i=function(e){var t=e.children,a=e.hidden,n=e.className;return r.default.createElement("div",{role:"tabpanel",hidden:a,className:n},t)};t.Z=i},279:(e,t,a)=>{"use strict";var n=a(5318).default,r=a(862).default;t.Z=void 0;var i=r(a(7294)),l=n(a(944)),o=n(a(6010)),s=n(a(3693));var u=37,d=39;var c=function(e){var t=e.lazy,a=e.block,n=e.defaultValue,r=e.values,c=e.groupId,m=e.className,p=(0,l.default)(),f=p.tabGroupChoices,v=p.setTabGroupChoices,g=(0,i.useState)(n),b=g[0],x=g[1],y=i.Children.toArray(e.children),h=[];if(null!=c){var N=f[c];null!=N&&N!==b&&r.some((function(e){return e.value===N}))&&x(N)}var w=function(e){var t=e.currentTarget,a=h.indexOf(t),n=r[a].value;x(n),null!=c&&(v(c,n),setTimeout((function(){var e,a,n,r,i,l,o,u;(e=t.getBoundingClientRect(),a=e.top,n=e.left,r=e.bottom,i=e.right,l=window,o=l.innerHeight,u=l.innerWidth,a>=0&&i<=u&&r<=o&&n>=0)||(t.scrollIntoView({block:"center",behavior:"smooth"}),t.classList.add(s.default.tabItemActive),setTimeout((function(){return t.classList.remove(s.default.tabItemActive)}),2e3))}),150))},I=function(e){var t,a;switch(e.keyCode){case d:var n=h.indexOf(e.target)+1;a=h[n]||h[0];break;case u:var r=h.indexOf(e.target)-1;a=h[r]||h[h.length-1]}null==(t=a)||t.focus()};return i.default.createElement("div",{className:"tabs-container"},i.default.createElement("ul",{role:"tablist","aria-orientation":"horizontal",className:(0,o.default)("tabs",{"tabs--block":a},m)},r.map((function(e){var t=e.value,a=e.label;return i.default.createElement("li",{role:"tab",tabIndex:b===t?0:-1,"aria-selected":b===t,className:(0,o.default)("tabs__item",s.default.tabItem,{"tabs__item--active":b===t}),key:t,ref:function(e){return h.push(e)},onKeyDown:I,onFocus:w,onClick:w},a)}))),t?(0,i.cloneElement)(y.filter((function(e){return e.props.value===b}))[0],{className:"margin-vert--md"}):i.default.createElement("div",{className:"margin-vert--md"},y.map((function(e,t){return(0,i.cloneElement)(e,{key:t,hidden:e.props.value!==b})}))))};t.Z=c},9443:(e,t,a)=>{"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var n=(0,a(7294).createContext)(void 0);t.default=n},944:(e,t,a)=>{"use strict";var n=a(5318).default;Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var r=a(7294),i=n(a(9443));var l=function(){var e=(0,r.useContext)(i.default);if(null==e)throw new Error("`useUserPreferencesContext` is used outside of `Layout` Component.");return e};t.default=l},920:(e,t,a)=>{"use strict";a.r(t),a.d(t,{frontMatter:()=>u,metadata:()=>d,toc:()=>c,default:()=>p});var n=a(2122),r=a(9756),i=(a(7294),a(3905)),l=a(279),o=a(8215),s=["components"],u={id:"getting-started",title:"Getting Started",sidebar_label:"Getting Started"},d={unversionedId:"getting-started",id:"getting-started",isDocsHomePage:!1,title:"Getting Started",description:"Universal Prerequisites",source:"@site/docs/GETTING_STARTED.md",sourceDirName:".",slug:"/getting-started",permalink:"/sauce_bindings/docs/getting-started",editUrl:"https://github.com/saucelabs/sauce_bindings/edit/main/website/docs/GETTING_STARTED.md",version:"current",lastUpdatedBy:"titusfortner",lastUpdatedAt:1626393187,formattedLastUpdatedAt:"7/15/2021",sidebar_label:"Getting Started",frontMatter:{id:"getting-started",title:"Getting Started",sidebar_label:"Getting Started"},sidebar:"docs",previous:{title:"Overview",permalink:"/sauce_bindings/docs/overview"},next:{title:"Creating a Session",permalink:"/sauce_bindings/docs/create-session"}},c=[{value:"Universal Prerequisites",id:"universal-prerequisites",children:[]},{value:"Language Specific  Prerequisites",id:"language-specific--prerequisites",children:[]}],m={toc:c};function p(e){var t=e.components,a=(0,r.default)(e,s);return(0,i.mdx)("wrapper",(0,n.default)({},m,a,{components:t,mdxType:"MDXLayout"}),(0,i.mdx)("h2",{id:"universal-prerequisites"},"Universal Prerequisites"),(0,i.mdx)("p",null,"To start with make sure you have a valid ",(0,i.mdx)("a",{parentName:"p",href:"https://app.saucelabs.com/"},"Sauce Labs")," account "),(0,i.mdx)("p",null,"Since credentials should never be stored in code that might get added to a version control system,\nwe have decided to require users of Sauce Bindings to store these values in environment variables:"),(0,i.mdx)("pre",null,(0,i.mdx)("code",{parentName:"pre",className:"language-bash"},"SAUCE_USERNAME='valid.username'\nSAUCE_ACCESS_KEY='valid.key'\n")),(0,i.mdx)("p",null,"Here are instructions for setting environment variables on each Operating System: "),(0,i.mdx)("ul",null,(0,i.mdx)("li",{parentName:"ul"},(0,i.mdx)("a",{parentName:"li",href:"https://www.architectryan.com/2018/08/31/how-to-change-environment-variables-on-windows-10/"},"Windows 10")," "),(0,i.mdx)("li",{parentName:"ul"},(0,i.mdx)("a",{parentName:"li",href:"https://apple.stackexchange.com/questions/106778/how-do-i-set-environment-variables-on-os-x"},"MacOS")),(0,i.mdx)("li",{parentName:"ul"},(0,i.mdx)("a",{parentName:"li",href:"https://askubuntu.com/questions/58814/how-do-i-add-environment-variables"},"Linux"))),(0,i.mdx)("h2",{id:"language-specific--prerequisites"},"Language Specific  Prerequisites"),(0,i.mdx)(l.Z,{defaultValue:"java",values:[{label:"Java",value:"java"},{label:"Python",value:"python"},{label:"Ruby",value:"ruby"},{label:"C#",value:"csharp"}],mdxType:"Tabs"},(0,i.mdx)(o.Z,{value:"java",mdxType:"TabItem"},(0,i.mdx)("ol",null,(0,i.mdx)("li",{parentName:"ol"},"Install ",(0,i.mdx)("strong",{parentName:"li"},(0,i.mdx)("em",{parentName:"strong"},"Java version 8"))," or greater"),(0,i.mdx)("li",{parentName:"ol"},"Install your favorite Java IDE (we really like IntelliJ and the Community Edition is free)."),(0,i.mdx)("li",{parentName:"ol"},"Choose your ",(0,i.mdx)("a",{parentName:"li",href:"test-runners/"},"test runner")," below, or use the Sauce Bindings directly without test runner support:")),(0,i.mdx)(l.Z,{defaultValue:"junit5",values:[{label:"JUnit 5",value:"junit5"},{label:"JUnit 4",value:"junit4"},{label:"TestNG",value:"testng"},{label:"Direct",value:"direct"}],mdxType:"Tabs"},(0,i.mdx)(o.Z,{value:"junit5",mdxType:"TabItem"},(0,i.mdx)("ol",{start:3},(0,i.mdx)("li",{parentName:"ol"},"Add the following to your ",(0,i.mdx)("inlineCode",{parentName:"li"},".pom")," file:")),(0,i.mdx)("pre",null,(0,i.mdx)("code",{parentName:"pre",className:"language-xml"},"\x3c!-- https://mvnrepository.com/artifact/com.saucelabs/saucebindings-junit4/latest --\x3e\n<dependency>\n    <groupId>com.saucelabs</groupId>\n    <artifactId>saucebindings-junit5</artifactId>\n    <version>1.0.0</version>\n</dependency>\n"))),(0,i.mdx)(o.Z,{value:"junit4",mdxType:"TabItem"},(0,i.mdx)("ol",{start:3},(0,i.mdx)("li",{parentName:"ol"},"Add the following to your ",(0,i.mdx)("inlineCode",{parentName:"li"},".pom")," file:")),(0,i.mdx)("pre",null,(0,i.mdx)("code",{parentName:"pre",className:"language-xml"},"\x3c!-- https://mvnrepository.com/artifact/com.saucelabs/saucebindings-junit4/latest --\x3e\n<dependency>\n    <groupId>com.saucelabs</groupId>\n    <artifactId>saucebindings-junit4</artifactId>\n    <version>1.0.0</version>\n</dependency>\n"))),(0,i.mdx)(o.Z,{value:"testng",mdxType:"TabItem"},(0,i.mdx)("ol",{start:3},(0,i.mdx)("li",{parentName:"ol"},"Add the following to your ",(0,i.mdx)("inlineCode",{parentName:"li"},".pom")," file:")),(0,i.mdx)("pre",null,(0,i.mdx)("code",{parentName:"pre",className:"language-xml"},"\x3c!-- https://mvnrepository.com/artifact/com.saucelabs/saucebindings-testng/latest --\x3e\n<dependency>\n    <groupId>com.saucelabs</groupId>\n    <artifactId>saucebindings-testng</artifactId>\n    <version>1.0.0</version>\n</dependency>\n"))),(0,i.mdx)(o.Z,{value:"direct",mdxType:"TabItem"},(0,i.mdx)("ol",{start:3},(0,i.mdx)("li",{parentName:"ol"},"Add the following to your ",(0,i.mdx)("inlineCode",{parentName:"li"},".pom")," file:")),(0,i.mdx)("pre",null,(0,i.mdx)("code",{parentName:"pre",className:"language-xml"},"\x3c!-- https://mvnrepository.com/artifact/com.saucelabs/sauce_bindings/latest --\x3e\n<dependency>\n    <groupId>com.saucelabs</groupId>\n    <artifactId>sauce_bindings</artifactId>\n    <version>1.2.0</version>\n</dependency>\n"))))),(0,i.mdx)(o.Z,{value:"python",mdxType:"TabItem"},(0,i.mdx)("ol",null,(0,i.mdx)("li",{parentName:"ol"},"Install the package:")),(0,i.mdx)("pre",null,(0,i.mdx)("code",{parentName:"pre",className:"language-bash"},"pip install saucebindings\n")),(0,i.mdx)("ol",{start:2},(0,i.mdx)("li",{parentName:"ol"},"Import it into your project")),(0,i.mdx)("pre",null,(0,i.mdx)("code",{parentName:"pre",className:"language-python"},"from saucebindings.options import SauceOptions\nfrom saucebindings.session import SauceSession\n"))),(0,i.mdx)(o.Z,{value:"ruby",mdxType:"TabItem"},(0,i.mdx)("ol",null,(0,i.mdx)("li",{parentName:"ol"},"Add it to your Gemfile:")),(0,i.mdx)("pre",null,(0,i.mdx)("code",{parentName:"pre",className:"language-ruby"},"gem 'sauce_bindings'\n")),(0,i.mdx)("ol",{start:2},(0,i.mdx)("li",{parentName:"ol"},"Require it in your project:")),(0,i.mdx)("pre",null,(0,i.mdx)("code",{parentName:"pre",className:"language-ruby"},"require 'sauce_bindings'\n")),(0,i.mdx)("p",null,"If you are using Capybara, you must also require this file:"),(0,i.mdx)("pre",null,(0,i.mdx)("code",{parentName:"pre",className:"language-ruby"},"require 'sauce_bindings/capybara_session'\n")),(0,i.mdx)("p",null,"Examples on this site are written to use RSpec, so to execute them you must also add this to your Gemfile:"),(0,i.mdx)("pre",null,(0,i.mdx)("code",{parentName:"pre",className:"language-ruby"},"gem 'rspec'\n"))),(0,i.mdx)(o.Z,{value:"csharp",mdxType:"TabItem"},(0,i.mdx)("p",null,(0,i.mdx)("strong",{parentName:"p"},"C# bindings are coming soon...")))))}p.isMDXComponent=!0},6010:(e,t,a)=>{"use strict";function n(e){var t,a,r="";if("string"==typeof e||"number"==typeof e)r+=e;else if("object"==typeof e)if(Array.isArray(e))for(t=0;t<e.length;t++)e[t]&&(a=n(e[t]))&&(r&&(r+=" "),r+=a);else for(t in e)e[t]&&(r&&(r+=" "),r+=t);return r}function r(){for(var e,t,a=0,r="";a<arguments.length;)(e=arguments[a++])&&(t=n(e))&&(r&&(r+=" "),r+=t);return r}a.r(t),a.d(t,{default:()=>r})},3693:(e,t,a)=>{"use strict";a.r(t),a.d(t,{default:()=>n});const n={tabItem:"tabItem_1uMI",tabItemActive:"tabItemActive_2DSg",blink:"blink_XB8L"}}}]);