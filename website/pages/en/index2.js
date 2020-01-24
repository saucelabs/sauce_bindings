/**
 * Copyright (c) 2017-present, Facebook, Inc.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

const React = require("react");

const CompLibrary = require("../../core/CompLibrary.js");

const MarkdownBlock = CompLibrary.MarkdownBlock; /* Used to read markdown */
const Container = CompLibrary.Container;
const GridBlock = CompLibrary.GridBlock;

class HomeSplash extends React.Component {
  render() {
    const {siteConfig, language = ""} = this.props;
    const {baseUrl, docsUrl} = siteConfig;
    const docsPart = `${docsUrl ? `${docsUrl}/` : ""}`;
    const langPart = `${language ? `${language}/` : ""}`;
    const docUrl = doc => `${baseUrl}${docsPart}${langPart}${doc}`;

    const SplashContainer = props => (
      <div className="homeContainer">
        <div className="homeSplashFade">
          <div className="wrapper homeWrapper">{props.children}</div>
        </div>
      </div>
    );

    const Logo = props => (
      <div className="projectLogo">
        <img src={props.img_src} alt="Project Logo" />
      </div>
    );

    const ProjectTitle = () => (
      <h2 className="projectTitle">
        {siteConfig.title}
        <small>{siteConfig.tagline}</small>
      </h2>
    );

    const PromoSection = props => (
      <div className="section promoSection">
        <div className="promoRow">
          <div className="pluginRowBlock">{props.children}</div>
        </div>
      </div>
    );

    const Button = props => (
      <div className="pluginWrapper buttonWrapper">
        <a className="button" href={props.href} target={props.target}>
          {props.children}
        </a>
      </div>
    );

    return (
      <SplashContainer>
        <Logo img_src={`${baseUrl}img/undraw_monitor.svg`} />
        <div className="inner">
          <ProjectTitle siteConfig={siteConfig} />
          <PromoSection>
            <Button href={docUrl("gettingstarted.html")}>Get Started</Button>
          </PromoSection>
        </div>
      </SplashContainer>
    );
  }
}

class Index2 extends React.Component {
  render() {
    const {config: siteConfig, language = ""} = this.props;
    const {baseUrl} = siteConfig;

    const Block = props => (
      <Container
        padding={["bottom", "top"]}
        id={props.id}
        background={props.background}>
        <GridBlock
          align="center"
          contents={props.children}
          layout={props.layout}
        />
      </Container>
    );

      const LeftBlock = props => (
          <Container
              padding={["bottom", "top"]}
              id={props.id}
              background={props.background}>
              <GridBlock
                  align="left"
                  contents={props.children}
                  layout={props.layout}
              />
          </Container>
      );
    const FeatureCallout = () => (
      <div
        className="productShowcaseSection paddingBottom"
        style={{textAlign: "center"}}>
        <h2>Feature Callout</h2>
        <MarkdownBlock>These are features of this project</MarkdownBlock>
      </div>
    );

    const TryOut = () => (
      <LeftBlock id="try">
        {[
          {
            content:
              "This project is a cross-language effort, with bindings available in the following languages:\n" +
                "\n" +
                "* [Java](https://github.com/saucelabs/simple_sauce/tree/master/java)\n" +
                "* [C#](https://github.com/saucelabs/simple_sauce/tree/master/dotnet)\n" +
                "* [Python](https://github.com/saucelabs/simple_sauce/tree/master/python)\n" +
                "* [Ruby](https://github.com/saucelabs/simple_sauce/tree/master/ruby)\n" +
                "\n" +
                "Each binding balances having a shared API and feature set while being idiomatic to the language and ecosystem the binding is a part of. Each feature should be available in all language bindings.\n" +
                "\n",
            image: `${baseUrl}img/undraw_code_review.svg`,
            imageAlign: "left",
            title: "Supported Language Bindings",
          },
        ]}
      </LeftBlock>
    );

    const Description = () => (
      <Block background="dark">
        {[
          {
            content:
              "This is another description of how this project is useful",
            image: `${baseUrl}img/undraw_note_list.svg`,
            imageAlign: "right",
            title: "Description",
          },
        ]}
      </Block>
    );

    const LearnHow = () => (
      <LeftBlock background="light">
        {[
          {
            content:
              "Writing and maintaining automated good test frameworks is hard work. Sauce Labs wants to help make this work simpler." +
                " We\'ve created Simple Sauce, a collection of tools to make connecting to and working with Sauce Labs simple.\n" +
                "\n" +
                "Simple Sauce has three main goals:\n" +
                "* Providing test developers with a wrapper or binding in their preferred programming language\n" +
                "* Providing a concise API for connecting to Sauce Labs and using Sauce features in test frameworks\n" +
                "* Providing an excellent developer user experience for using Sauce Labs in a straightforward, intuitive way.\n" +
                "\n" +
                "The focus of this project is to make using Sauce Labs simple so that test developers can focus on developing the best automated test frameworks possible for their teams.\n" +
                "\n",
            image: `${baseUrl}img/undraw_youtube_tutorial.svg`,
            imageAlign: "right",
            title: "Making Sauce Labs Simple To Use",
          },
        ]}
      </LeftBlock>
    );

    const Features = () => (
      <Block layout="fourColumn">
        {[
          {
            content: "An easy way to get the latest required syntax for Sauce Labs",
            image: `${baseUrl}img/undraw_react.svg`,
            imageAlign: "top",
            title: "Selenium 4 Compatibility",
          },
          {
            content: "This will make it easier for Sauce Labs to provide useful analytics",
            image: `${baseUrl}img/undraw_operating_system.svg`,
            imageAlign: "top",
            title: "Automatic Build Information",
          },
        ]}
      </Block>
    );

    const Showcase = () => {
      if ((siteConfig.users || []).length === 0) {
        return null;
      }

      const showcase = siteConfig.users
        .filter(user => user.pinned)
        .map(user => (
          <a href={user.infoLink} key={user.infoLink}>
            <img src={user.image} alt={user.caption} title={user.caption} />
          </a>
        ));

      const pageUrl = page => baseUrl + (language ? `${language}/` : "") + page;

      return (
        <div className="productShowcaseSection paddingBottom">
          <h2>Who is Using This?</h2>
          <p>This project is used by all these people</p>
          <div className="logos">{showcase}</div>
          <div className="more-users">
            <a className="button" href={pageUrl("users.html")}>
              More {siteConfig.title} Users
            </a>
          </div>
        </div>
      );
    };

    return (
      <div>
        <HomeSplash siteConfig={siteConfig} language={language} />
        <div className="mainContainer">
          <Features />
          {/*<FeatureCallout />*/}
          <LearnHow />
          <TryOut />
          {/*<Description />*/}
          {/*<Showcase />*/}
        </div>
      </div>
    );
  }
}

module.exports = Index2;
