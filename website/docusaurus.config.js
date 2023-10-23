const lightCodeTheme = require('prism-react-renderer/themes/github');
const darkCodeTheme = require('prism-react-renderer/themes/dracula');

// With JSDoc @type annotations, IDEs can provide config autocompletion
/** @type {import('@docusaurus/types').DocusaurusConfig} */
(module.exports = {
  title: 'Sauce Bindings',
  tagline: 'Making test automation with Sauce Labs simple',
  url: 'https://saucelabs.github.io',
  baseUrl: '/',
  organizationName: 'saucelabs',
  projectName: 'sauce_bindings',
  onBrokenLinks: 'throw',
  onBrokenMarkdownLinks: 'warn',
  favicon: 'img/favicon.ico',

  presets: [
    [
      '@docusaurus/preset-classic',
      /** @type {import('@docusaurus/preset-classic').Options} */
      ({
        docs: {
          sidebarPath: require.resolve('./sidebars.js'),
          // Please change this to your repo.
          editUrl: 'https://github.com/saucelabs/sauce_bindings/edit/main/website/',
        },
        blog: {
          showReadingTime: true,
          // Please change this to your repo.
          editUrl:
            'https://github.com/saucelabs/sauce_bindings/edit/main/website/blog/',
        },
        theme: {
          customCss: require.resolve('./src/css/custom.css'),
        },
      }),
    ],
  ],

  themeConfig:
    /** @type {import('@docusaurus/preset-classic').ThemeConfig} */
    ({
      navbar: {
        title: 'Sauce Bindings',
        logo: {
          alt: 'Sauce Bindings Logo',
          src: 'img/logo.svg',
        },
        items: [
          {to: '/docs', label: 'Docs', position: 'left'},
          {
            //TODO duplication of this URL, need to move to a constants file
            href: 'https://github.com/saucelabs/sauce_bindings',
            label: 'GitHub',
            position: 'right',
          },
        ],
      },
      footer: {
        style: 'dark',
        links: [
          {
            title: 'Docs',
            items: [
              {
                label: 'Tutorial',
                to: '/docs/intro',
              },
            ],
          },
          {
            title: 'Community',
            items: [
              // {
              //   label: 'Stack Overflow',
              //   href: 'https://stackoverflow.com/questions/tagged/docusaurus',
              // },
              //TODO fix this URL to Sauce Community
              {
                label: 'Chat with us',
                href: 'https://saucelabs.com',
              },
            ],
          },
          {
            title: 'More',
            items: [
              {
                label: 'GitHub',
                href: 'https://github.com/saucelabs/sauce_bindings',
              },
            ],
          },
        ],
        copyright: `Copyright © ${new Date().getFullYear()} Sauce Labs.`,
      },
      prism: {
        theme: lightCodeTheme,
        darkTheme: darkCodeTheme,
      },
    }),
});
