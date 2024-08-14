import Heading from "@theme/Heading";
import clsx from "clsx";
import styles from "./styles.module.css";

const FeatureList = [
  {
    title: "Easy to Use",
    Svg: require("@site/static/img/icon-easytouse.svg").default,
    description: <>Sauce Bindings API is simple.</>,
  },
  {
    title: "Focus on What Matters",
    Svg: require("@site/static/img/icon-focusonwhatmatters.svg").default,
    description: (
      <>
        Let the IDE guide you to structure your Sauce tests. So you can focus on
        the automation.
      </>
    ),
  },
  {
    title: "Cleaner Code",
    Svg: require("@site/static/img/icon-createcleanercode.svg").default,
    description: <>Less code, less maintenance, more automation.</>,
  },
];

function Feature({ Svg, title, description }) {
  return (
    <div className={clsx("col col--4")}>
      <div className="text--center">
        <Svg className={styles.featureSvg} role="img" />
      </div>
      <div className="text--center padding-horiz--md">
        <Heading as="h3">{title}</Heading>
        <p>{description}</p>
      </div>
    </div>
  );
}

export default function HomepageFeatures() {
  return (
    <section className={styles.features}>
      <div className="container">
        <div className="row">
          {FeatureList.map((props, idx) => (
            <Feature key={idx} {...props} />
          ))}
        </div>
      </div>
    </section>
  );
}
