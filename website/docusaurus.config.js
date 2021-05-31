module.exports={
  "title": "Sauce Bindings",
  "tagline": "Provide Convenient Way to Use Sauce Labs",
  "url": "https://saucelabs.github.io",
  "baseUrl": "/sauce_bindings/",
  "organizationName": "saucelabs",
  "projectName": "sauce_bindings",
  "scripts": [
    "https://buttons.github.io/buttons.js"
  ],
  "stylesheets": [
    "https://use.typekit.net/zmt8tam.css"
  ],
  "favicon": "img/favicon.png",
  "customFields": {
    "disableHeaderTitle": true,
    "users": [
      {
        "caption": "Sauce Labs",
        "image": "/img/sauce-badge.png",
        "infoLink": "https://saucelabs.com",
        "pinned": true
      }
    ],
    "fonts": {
      "saucelabsFont": [
        "museo-sans",
        "HelveticaNeue",
        "Helvetica Neue",
        "Serif"
      ]
    },
    "repoUrl": "https://github.com/saucelabs/sauce_bindings"
  },
  "onBrokenLinks": "log",
  "onBrokenMarkdownLinks": "log",
  "presets": [
    [
      "@docusaurus/preset-classic",
      {
        "docs": {
          "showLastUpdateAuthor": true,
          "showLastUpdateTime": true,
          "path": "../docs",
          "sidebarPath": "../website/sidebars.json"
        },
        "blog": {},
        "theme": {
          "customCss": "../src/css/customTheme.css"
        }
      }
    ]
  ],
  "plugins": [],
  "themeConfig": {
    "navbar": {
      "title": "Sauce Bindings",
      "logo": {
        "src": "img/logo-saucelabs.png"
      },
      "items": []
    },
    "image": "img/undraw_online.svg",
    "footer": {
      "links": [],
      "copyright": "Copyright © 2020-2021 Sauce Labs",
      "logo": {
        "src": "img/favicon.ico"
      }
    },
    "gtag": {
      "trackingID": "UA-6735579-21"
    }
  }
}