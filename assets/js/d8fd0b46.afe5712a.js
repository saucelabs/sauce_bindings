"use strict";(self.webpackChunkwebsite=self.webpackChunkwebsite||[]).push([[97],{9833:(e,t,a)=>{a.r(t),a.d(t,{assets:()=>i,contentTitle:()=>l,default:()=>b,frontMatter:()=>u,metadata:()=>c,toc:()=>d});var s=a(4848),n=a(8453),r=a(1470),o=a(9365);const u={id:"sauce-options",title:"Setting Sauce Labs Options",sidebar_label:"Sauce Options"},l=void 0,c={id:"core-concepts/sauce-options",title:"Setting Sauce Labs Options",description:"Sauce Labs provides dozens of ways to configure your tests on our platform.",source:"@site/docs/core-concepts/sauce-options.md",sourceDirName:"core-concepts",slug:"/core-concepts/sauce-options",permalink:"/docs/core-concepts/sauce-options",draft:!1,unlisted:!1,editUrl:"https://github.com/saucelabs/sauce_bindings/edit/main/website/docs/core-concepts/sauce-options.md",tags:[],version:"current",frontMatter:{id:"sauce-options",title:"Setting Sauce Labs Options",sidebar_label:"Sauce Options"},sidebar:"tutorialSidebar",previous:{title:"Desktop Browsers",permalink:"/docs/core-concepts/desktop-browsers"},next:{title:"Test Runners",permalink:"/docs/core-concepts/test-runners"}},i={},d=[];function p(e){const t={a:"a",code:"code",p:"p",pre:"pre",strong:"strong",...(0,n.R)(),...e.components};return(0,s.jsxs)(s.Fragment,{children:[(0,s.jsxs)(t.p,{children:["Sauce Labs provides dozens of ways to configure your tests on our platform.\nHere is a ",(0,s.jsx)(t.a,{href:"https://docs.saucelabs.com/dev/test-configuration-options/index.html",children:"full list of everything with exhaustive descriptions"}),".\nAll of these configurations can now be easily set with the ",(0,s.jsx)(t.code,{children:"SauceOptions"})," class"]}),"\n",(0,s.jsx)(t.p,{children:"Here's an example of running a test on Firefox that sets extended debugging, changes the idle timeout to 45 seconds,\nand sets the time zone to Alaska:"}),"\n","\n",(0,s.jsxs)(r.A,{defaultValue:"java",values:[{label:"Java",value:"java"},{label:"Python",value:"python"},{label:"Ruby",value:"ruby"},{label:"C#",value:"csharp"}],children:[(0,s.jsx)(o.A,{value:"java",children:(0,s.jsxs)(r.A,{defaultValue:"junit5",values:[{label:"JUnit 5",value:"junit5"},{label:"JUnit 4",value:"junit4"},{label:"TestNG",value:"testng"},{label:"Direct",value:"direct"}],children:[(0,s.jsx)(o.A,{value:"junit5",children:(0,s.jsx)(t.pre,{children:(0,s.jsx)(t.code,{className:"language-java",metastring:"reference",children:"https://github.com/saucelabs/sauce_bindings/tree/website-1.2.0/java/junit5/src/test/java/com/saucelabs/saucebindings/junit5/examples/SauceLabsOptionsTest.java\n"})})}),(0,s.jsx)(o.A,{value:"junit4",children:(0,s.jsx)(t.pre,{children:(0,s.jsx)(t.code,{className:"language-java",metastring:"reference",children:"https://github.com/saucelabs/sauce_bindings/tree/website-1.2.0/java/junit4/src/test/java/com/saucelabs/saucebindings/junit4/examples/SauceLabsOptionsTest.java\n"})})}),(0,s.jsx)(o.A,{value:"testng",children:(0,s.jsx)(t.pre,{children:(0,s.jsx)(t.code,{className:"language-java",metastring:"reference",children:"https://github.com/saucelabs/sauce_bindings/tree/website-1.2.0/java/testng/src/test/java/com/saucelabs/saucebindings/testng/examples/SauceLabsOptionsTest.java\n"})})}),(0,s.jsx)(o.A,{value:"direct",children:(0,s.jsx)(t.pre,{children:(0,s.jsx)(t.code,{className:"language-java",metastring:"reference",children:"https://github.com/saucelabs/sauce_bindings/tree/website-1.2.0/java/main/src/test/java/com/saucelabs/saucebindings/examples/SauceLabsOptionsTest.java\n"})})})]})}),(0,s.jsx)(o.A,{value:"python",children:(0,s.jsx)(t.pre,{children:(0,s.jsx)(t.code,{className:"language-python",metastring:"reference",children:"https://github.com/saucelabs/sauce_bindings/tree/website-1.2.0/python/tests/examples/test_sauce_options.py\n"})})}),(0,s.jsx)(o.A,{value:"ruby",children:(0,s.jsx)(t.pre,{children:(0,s.jsx)(t.code,{className:"language-ruby",metastring:"reference",children:"https://github.com/saucelabs/sauce_bindings/tree/website-1.2.0/ruby/spec/examples/sauce_options_spec.rb\n"})})}),(0,s.jsx)(o.A,{value:"csharp",children:(0,s.jsx)(t.p,{children:(0,s.jsx)(t.strong,{children:"C# bindings are coming soon..."})})})]})]})}function b(e={}){const{wrapper:t}={...(0,n.R)(),...e.components};return t?(0,s.jsx)(t,{...e,children:(0,s.jsx)(p,{...e})}):p(e)}},9365:(e,t,a)=>{a.d(t,{A:()=>o});a(6540);var s=a(4164);const n={tabItem:"tabItem_Ymn6"};var r=a(4848);function o(e){let{children:t,hidden:a,className:o}=e;return(0,r.jsx)("div",{role:"tabpanel",className:(0,s.A)(n.tabItem,o),hidden:a,children:t})}},1470:(e,t,a)=>{a.d(t,{A:()=>w});var s=a(6540),n=a(4164),r=a(3104),o=a(6347),u=a(205),l=a(7485),c=a(1682),i=a(679);function d(e){return s.Children.toArray(e).filter((e=>"\n"!==e)).map((e=>{if(!e||(0,s.isValidElement)(e)&&function(e){const{props:t}=e;return!!t&&"object"==typeof t&&"value"in t}(e))return e;throw new Error(`Docusaurus error: Bad <Tabs> child <${"string"==typeof e.type?e.type:e.type.name}>: all children of the <Tabs> component should be <TabItem>, and every <TabItem> should have a unique "value" prop.`)}))?.filter(Boolean)??[]}function p(e){const{values:t,children:a}=e;return(0,s.useMemo)((()=>{const e=t??function(e){return d(e).map((e=>{let{props:{value:t,label:a,attributes:s,default:n}}=e;return{value:t,label:a,attributes:s,default:n}}))}(a);return function(e){const t=(0,c.X)(e,((e,t)=>e.value===t.value));if(t.length>0)throw new Error(`Docusaurus error: Duplicate values "${t.map((e=>e.value)).join(", ")}" found in <Tabs>. Every value needs to be unique.`)}(e),e}),[t,a])}function b(e){let{value:t,tabValues:a}=e;return a.some((e=>e.value===t))}function h(e){let{queryString:t=!1,groupId:a}=e;const n=(0,o.W6)(),r=function(e){let{queryString:t=!1,groupId:a}=e;if("string"==typeof t)return t;if(!1===t)return null;if(!0===t&&!a)throw new Error('Docusaurus error: The <Tabs> component groupId prop is required if queryString=true, because this value is used as the search param name. You can also provide an explicit value such as queryString="my-search-param".');return a??null}({queryString:t,groupId:a});return[(0,l.aZ)(r),(0,s.useCallback)((e=>{if(!r)return;const t=new URLSearchParams(n.location.search);t.set(r,e),n.replace({...n.location,search:t.toString()})}),[r,n])]}function m(e){const{defaultValue:t,queryString:a=!1,groupId:n}=e,r=p(e),[o,l]=(0,s.useState)((()=>function(e){let{defaultValue:t,tabValues:a}=e;if(0===a.length)throw new Error("Docusaurus error: the <Tabs> component requires at least one <TabItem> children component");if(t){if(!b({value:t,tabValues:a}))throw new Error(`Docusaurus error: The <Tabs> has a defaultValue "${t}" but none of its children has the corresponding value. Available values are: ${a.map((e=>e.value)).join(", ")}. If you intend to show no default tab, use defaultValue={null} instead.`);return t}const s=a.find((e=>e.default))??a[0];if(!s)throw new Error("Unexpected error: 0 tabValues");return s.value}({defaultValue:t,tabValues:r}))),[c,d]=h({queryString:a,groupId:n}),[m,f]=function(e){let{groupId:t}=e;const a=function(e){return e?`docusaurus.tab.${e}`:null}(t),[n,r]=(0,i.Dv)(a);return[n,(0,s.useCallback)((e=>{a&&r.set(e)}),[a,r])]}({groupId:n}),v=(()=>{const e=c??m;return b({value:e,tabValues:r})?e:null})();(0,u.A)((()=>{v&&l(v)}),[v]);return{selectedValue:o,selectValue:(0,s.useCallback)((e=>{if(!b({value:e,tabValues:r}))throw new Error(`Can't select invalid tab value=${e}`);l(e),d(e),f(e)}),[d,f,r]),tabValues:r}}var f=a(2303);const v={tabList:"tabList__CuJ",tabItem:"tabItem_LNqP"};var g=a(4848);function j(e){let{className:t,block:a,selectedValue:s,selectValue:o,tabValues:u}=e;const l=[],{blockElementScrollPositionUntilNextRender:c}=(0,r.a_)(),i=e=>{const t=e.currentTarget,a=l.indexOf(t),n=u[a].value;n!==s&&(c(t),o(n))},d=e=>{let t=null;switch(e.key){case"Enter":i(e);break;case"ArrowRight":{const a=l.indexOf(e.currentTarget)+1;t=l[a]??l[0];break}case"ArrowLeft":{const a=l.indexOf(e.currentTarget)-1;t=l[a]??l[l.length-1];break}}t?.focus()};return(0,g.jsx)("ul",{role:"tablist","aria-orientation":"horizontal",className:(0,n.A)("tabs",{"tabs--block":a},t),children:u.map((e=>{let{value:t,label:a,attributes:r}=e;return(0,g.jsx)("li",{role:"tab",tabIndex:s===t?0:-1,"aria-selected":s===t,ref:e=>l.push(e),onKeyDown:d,onClick:i,...r,className:(0,n.A)("tabs__item",v.tabItem,r?.className,{"tabs__item--active":s===t}),children:a??t},t)}))})}function x(e){let{lazy:t,children:a,selectedValue:n}=e;const r=(Array.isArray(a)?a:[a]).filter(Boolean);if(t){const e=r.find((e=>e.props.value===n));return e?(0,s.cloneElement)(e,{className:"margin-top--md"}):null}return(0,g.jsx)("div",{className:"margin-top--md",children:r.map(((e,t)=>(0,s.cloneElement)(e,{key:t,hidden:e.props.value!==n})))})}function y(e){const t=m(e);return(0,g.jsxs)("div",{className:(0,n.A)("tabs-container",v.tabList),children:[(0,g.jsx)(j,{...t,...e}),(0,g.jsx)(x,{...t,...e})]})}function w(e){const t=(0,f.A)();return(0,g.jsx)(y,{...e,children:d(e.children)},String(t))}},8453:(e,t,a)=>{a.d(t,{R:()=>o,x:()=>u});var s=a(6540);const n={},r=s.createContext(n);function o(e){const t=s.useContext(r);return s.useMemo((function(){return"function"==typeof e?e(t):{...t,...e}}),[t,e])}function u(e){let t;return t=e.disableParentContext?"function"==typeof e.components?e.components(n):e.components||n:o(e.components),s.createElement(r.Provider,{value:t},e.children)}}}]);