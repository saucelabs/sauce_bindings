module.exports={
  title: 'Sauce Bindings',
  tagline: 'Provide Convenient Way to Use Sauce Labs',
  url: 'https://saucelabs.github.io',
  baseUrl: '/sauce_bindings/',
  organizationName: 'saucelabs',
  projectName: 'sauce_bindings',
  scripts: [
    'https://buttons.github.io/buttons.js'
  ],
  stylesheets: [
    'https://use.typekit.net/zmt8tam.css'
  ],
  favicon: 'img/favicon.png',
  customFields: {
    disableHeaderTitle: true,
    users: [
      {
        caption: 'Sauce Labs',
        image: '/img/sauce-badge.png',
        infoLink: 'https://saucelabs.com',
        pinned: true
      }
    ],
    fonts: {
      saucelabsFont: [
        'museo-sans',
        'HelveticaNeue',
        'Helvetica Neue',
        'Serif'
      ]
    },
    repoUrl: 'https://github.com/saucelabs/sauce_bindings'
  },
  onBrokenLinks: 'log',
  onBrokenMarkdownLinks: 'log',
  presets: [
    [
      '@docusaurus/preset-classic',
      {
        docs: {
          sidebarPath: 'sidebars.json',
          routeBasePath: '/',
          editUrl:
              'https://github.com/saucelabs/sauce_bindings/edit/main/website/',
          showLastUpdateAuthor: true,
          showLastUpdateTime: true,
        },
        theme: {
          customCss: require.resolve('./src/css/custom.css')
        },
      }
    ]
  ],
  themes: [
    '@saucelabs/theme-github-codeblock'
  ],
  plugins: [],
  themeConfig: {
    docs: {
      sidebar: {
        hideable: true,
      },
    },
    prism: {
      additionalLanguages: ['java', 'ruby', 'csharp', 'bash', 'powershell', 'python'],
    },
    presets: [
      [
        '@docusaurus/preset-classic',
        {
          googleAnalytics: {
            trackingID: 'UA-6735579-21',
          },
          // other preset options
        },
      ],
    ],
    navbar: {
      title: null,
      hideOnScroll: false,
      logo: {
        alt: 'Sauce Labs logo',
        src: 'img/logo-saucelabs.png',
        srcDark: 'img/logo-saucelabs-inverted.png'
      },
      items: [
        {
          label: 'Try it Free',
          position: 'right',
          href: 'https://saucelabs.com/sign-up',
        },
        {
          label: 'Sign In',
          position: 'right',
          href: 'https://accounts.saucelabs.com/',
        },
      ]
    },
    footer: {
      logo: {
        alt: 'Sauce Logo',
        src: '/img/logo-saucelabs-inverted.png',
        href: 'https://saucelabs.com',
      },
      style: 'light',
      copyright: `Copyright © ${new Date().getFullYear()} Sauce Labs, Inc. Built with Docusaurus.`,
    },
  }
}
