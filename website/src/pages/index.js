import React, { useEffect } from 'react';
import { useHistory } from '@docusaurus/router';

export default function Home() {
  useEffect(() => {
    window.location.replace("https://github.com/saucelabs/sauce_bindings/");
  }, []);

  return (
      <p>If you are not redirected, <a href="https://github.com/saucelabs/sauce_bindings/">click here</a>.</p>
  );
}
