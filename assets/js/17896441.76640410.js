(self.webpackChunk=self.webpackChunk||[]).push([[918],{1814:(e,t,a)=>{"use strict";var l=a(5318).default;Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var n=l(a(7294)),d=l(a(1101)),r=l(a(5661)),i=l(a(1217)),s=l(a(4176)),u=l(a(5176)),o=l(a(4992)),c=l(a(6010)),f=l(a(716)),m=a(907);var v=function(e){var t,a=e.content,l=a.metadata,v=a.frontMatter,p=v.image,E=v.keywords,g=v.hide_title,h=v.hide_table_of_contents,b=l.description,_=l.title,N=l.editUrl,L=l.lastUpdatedAt,y=l.formattedLastUpdatedAt,U=l.lastUpdatedBy,T=(0,m.useActivePlugin)({failfast:!0}).pluginId,C=(0,m.useVersions)(T),k=(0,m.useActiveVersion)(T),w=C.length>1,A=v.title||_;return n.default.createElement(n.default.Fragment,null,n.default.createElement(i.default,{title:A,description:b,keywords:E,image:p}),n.default.createElement("div",{className:"row"},n.default.createElement("div",{className:(0,c.default)("col",(t={},t[f.default.docItemCol]=!h,t))},n.default.createElement(r.default,null),n.default.createElement("div",{className:f.default.docItemContainer},n.default.createElement("article",null,w&&n.default.createElement("div",null,n.default.createElement("span",{className:"badge badge--secondary"},"Version: ",k.label)),!g&&n.default.createElement("header",null,n.default.createElement("h1",{className:f.default.docTitle},_)),n.default.createElement("div",{className:"markdown"},n.default.createElement(a,null))),(N||L||U)&&n.default.createElement("div",{className:"margin-vert--xl"},n.default.createElement("div",{className:"row"},n.default.createElement("div",{className:"col"},N&&n.default.createElement(o.default,{editUrl:N})),(L||U)&&n.default.createElement(s.default,{lastUpdatedAt:L,formattedLastUpdatedAt:y,lastUpdatedBy:U}))),n.default.createElement("div",{className:"margin-vert--lg"},n.default.createElement(d.default,{metadata:l})))),!h&&a.toc&&n.default.createElement("div",{className:"col col--3"},n.default.createElement(u.default,{toc:a.toc}))))};t.default=v},1101:(e,t,a)=>{"use strict";var l=a(862).default,n=a(5318).default;Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var d=n(a(7294)),r=n(a(3692)),i=l(a(9052));var s=function(e){var t=e.metadata;return d.default.createElement("nav",{className:"pagination-nav","aria-label":(0,i.translate)({id:"theme.docs.paginator.navAriaLabel",message:"Docs pages navigation",description:"The ARIA label for the docs pagination"})},d.default.createElement("div",{className:"pagination-nav__item"},t.previous&&d.default.createElement(r.default,{className:"pagination-nav__link",to:t.previous.permalink},d.default.createElement("div",{className:"pagination-nav__sublabel"},d.default.createElement(i.default,{id:"theme.docs.paginator.previous",description:"The label used to navigate to the previous doc"},"Previous")),d.default.createElement("div",{className:"pagination-nav__label"},"\xab ",t.previous.title))),d.default.createElement("div",{className:"pagination-nav__item pagination-nav__item--next"},t.next&&d.default.createElement(r.default,{className:"pagination-nav__link",to:t.next.permalink},d.default.createElement("div",{className:"pagination-nav__sublabel"},d.default.createElement(i.default,{id:"theme.docs.paginator.next",description:"The label used to navigate to the next doc"},"Next")),d.default.createElement("div",{className:"pagination-nav__label"},t.next.title," \xbb"))))};t.default=s},5661:(e,t,a)=>{"use strict";var l=a(5318).default;Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var n=l(a(7294)),d=l(a(2263)),r=l(a(3692)),i=l(a(9052)),s=a(907),u=a(6700);function o(e){var t=e.siteTitle,a=e.versionLabel;return n.default.createElement(i.default,{id:"theme.docs.versions.unreleasedVersionLabel",description:"The label used to tell the user that he's browsing an unreleased doc version",values:{siteTitle:t,versionLabel:n.default.createElement("strong",null,a)}},"This is unreleased documentation for {siteTitle} {versionLabel} version.")}function c(e){var t=e.siteTitle,a=e.versionLabel;return n.default.createElement(i.default,{id:"theme.docs.versions.unmaintainedVersionLabel",description:"The label used to tell the user that he's browsing an unmaintained doc version",values:{siteTitle:t,versionLabel:n.default.createElement("strong",null,a)}},"This is documentation for {siteTitle} {versionLabel}, which is no longer actively maintained.")}function f(e){var t=e.versionLabel,a=e.to,l=e.onClick;return n.default.createElement(i.default,{id:"theme.docs.versions.latestVersionSuggestionLabel",description:"The label userd to tell the user that he's browsing an unmaintained doc version",values:{versionLabel:t,latestVersionLink:n.default.createElement("strong",null,n.default.createElement(r.default,{to:a,onClick:l},n.default.createElement(i.default,{id:"theme.docs.versions.latestVersionLinkLabel",description:"The label used for the latest version suggestion link label"},"latest version")))}},"For up-to-date documentation, see the {latestVersionLink} ({versionLabel}).")}var m=function(){var e=(0,d.default)().siteConfig.title,t=(0,s.useActivePlugin)({failfast:!0}).pluginId,a=(0,u.useDocsPreferredVersion)(t).savePreferredVersionName,l=(0,s.useActiveVersion)(t),r=(0,s.useDocVersionSuggestions)(t),i=r.latestDocSuggestion,m=r.latestVersionSuggestion;if(!m)return n.default.createElement(n.default.Fragment,null);var v,p=null!=i?i:(v=m).docs.find((function(e){return e.id===v.mainDocId}));return n.default.createElement("div",{className:"alert alert--warning margin-bottom--md",role:"alert"},n.default.createElement("div",null,"current"===l.name?n.default.createElement(o,{siteTitle:e,versionLabel:l.label}):n.default.createElement(c,{siteTitle:e,versionLabel:l.label})),n.default.createElement("div",{className:"margin-top--md"},n.default.createElement(f,{versionLabel:m.label,to:p.path,onClick:function(){return a(m.name)}})))};t.default=m},4992:(e,t,a)=>{"use strict";var l=a(5318).default;Object.defineProperty(t,"__esModule",{value:!0}),t.default=function(e){var t=e.editUrl;return n.default.createElement("a",{href:t,target:"_blank",rel:"noreferrer noopener"},n.default.createElement(r.default,null),n.default.createElement(d.default,{id:"theme.common.editThisPage",description:"The link label to edit the current page"},"Edit this page"))};var n=l(a(7294)),d=l(a(9052)),r=l(a(8558))},8558:(e,t,a)=>{"use strict";var l=a(5318).default;Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var n=l(a(2122)),d=l(a(9756)),r=l(a(7294)),i=l(a(6010)),s=l(a(1321)),u=["className"],o=function(e){var t=e.className,a=(0,d.default)(e,u);return r.default.createElement("svg",(0,n.default)({fill:"currentColor",height:"1.2em",width:"1.2em",preserveAspectRatio:"xMidYMid meet",role:"img",viewBox:"0 0 40 40",className:(0,i.default)(s.default.iconEdit,t),"aria-label":"Edit page"},a),r.default.createElement("g",null,r.default.createElement("path",{d:"m34.5 11.7l-3 3.1-6.3-6.3 3.1-3q0.5-0.5 1.2-0.5t1.1 0.5l3.9 3.9q0.5 0.4 0.5 1.1t-0.5 1.2z m-29.5 17.1l18.4-18.5 6.3 6.3-18.4 18.4h-6.3v-6.2z"})))};t.default=o},4176:(e,t,a)=>{"use strict";var l=a(5318).default;Object.defineProperty(t,"__esModule",{value:!0}),t.default=function(e){var t=e.lastUpdatedAt,a=e.formattedLastUpdatedAt,l=e.lastUpdatedBy;return n.default.createElement("div",{className:"col text--right"},n.default.createElement("em",null,n.default.createElement("small",null,n.default.createElement(r.default,{id:"theme.lastUpdated.lastUpdatedAtBy",description:"The sentence used to display when a page has been last updated, and by who",values:{atDate:t&&a?n.default.createElement(i,{lastUpdatedAt:t,formattedLastUpdatedAt:a}):"",byUser:l?n.default.createElement(s,{lastUpdatedBy:l}):""}},"Last updated{atDate}{byUser}"),!1)))};var n=l(a(7294)),d=l(a(1910)),r=l(a(9052));function i(e){var t=e.lastUpdatedAt,a=e.formattedLastUpdatedAt;return n.default.createElement(r.default,{id:"theme.lastUpdated.atDate",description:"The words used to describe on which date a page has been last updated",values:{date:n.default.createElement("time",{dateTime:new Date(1e3*t).toISOString(),className:d.default.lastUpdatedDate},a)}}," on {date}")}function s(e){var t=e.lastUpdatedBy;return n.default.createElement(r.default,{id:"theme.lastUpdated.byUser",description:"The words used to describe by who the page has been last updated",values:{user:n.default.createElement("strong",null,t)}}," by {user}")}},5176:(e,t,a)=>{"use strict";var l=a(5318).default;Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var n=l(a(7294)),d=l(a(6010)),r=l(a(5035)),i=l(a(8018)),s="table-of-contents__link";function u(e){var t=e.toc,a=e.isChild;return t.length?n.default.createElement("ul",{className:a?"":"table-of-contents table-of-contents__left-border"},t.map((function(e){return n.default.createElement("li",{key:e.id},n.default.createElement("a",{href:"#"+e.id,className:s,dangerouslySetInnerHTML:{__html:e.value}}),n.default.createElement(u,{isChild:!0,toc:e.children}))}))):null}var o=function(e){var t=e.toc;return(0,r.default)(s,"table-of-contents__link--active",100),n.default.createElement("div",{className:(0,d.default)(i.default.tableOfContents,"thin-scrollbar")},n.default.createElement(u,{toc:t}))};t.default=o},5035:(e,t,a)=>{"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var l=a(7294);var n=function(e,t,a){var n=(0,l.useState)(void 0),d=n[0],r=n[1];(0,l.useEffect)((function(){function l(){var l=function(){var e=Array.from(document.getElementsByClassName("anchor")),t=e.find((function(e){return e.getBoundingClientRect().top>=a}));if(t){if(t.getBoundingClientRect().top>=a){var l=e[e.indexOf(t)-1];return null!=l?l:t}return t}return e[e.length-1]}();if(l)for(var n=0,i=!1,s=document.getElementsByClassName(e);n<s.length&&!i;){var u=s[n],o=u.href,c=decodeURIComponent(o.substring(o.indexOf("#")+1));l.id===c&&(d&&d.classList.remove(t),u.classList.add(t),r(u),i=!0),n+=1}}return document.addEventListener("scroll",l),document.addEventListener("resize",l),l(),function(){document.removeEventListener("scroll",l),document.removeEventListener("resize",l)}}))};t.default=n},716:(e,t,a)=>{"use strict";a.r(t),a.d(t,{default:()=>l});const l={docTitle:"docTitle_3a4h",docItemContainer:"docItemContainer_33ec",docItemCol:"docItemCol_3FnS"}},1321:(e,t,a)=>{"use strict";a.r(t),a.d(t,{default:()=>l});const l={iconEdit:"iconEdit_2_ui"}},1910:(e,t,a)=>{"use strict";a.r(t),a.d(t,{default:()=>l});const l={lastUpdatedDate:"lastUpdatedDate_1WI_"}},8018:(e,t,a)=>{"use strict";a.r(t),a.d(t,{default:()=>l});const l={tableOfContents:"tableOfContents_35-E",docItemContainer:"docItemContainer_gpai"}}}]);