from distutils.core import setup
setup(
  name = 'simplesauce',
  packages = ['simplesauce'],
  version = '0.0.3',
  license='MIT',
  description = 'Python bindings of the simple_sauce library',
  author = 'josh.grant',
  author_email = 'josh.grant@saucelabs.com',
  url = 'https://github.com/saucelabs/simple_sauce/',
  download_url = 'https://github.com/saucelabs/simple_sauce/archive/v_003.tar.gz', # based on GitHub releases
  keywords = ['Sauce Labs', 'testing', 'selenium'],
  install_requires=[
          'selenium',
          'pytest'
      ],
  classifiers=[
    'Development Status :: 3 - Alpha',
    'Intended Audience :: Developers',
    'Topic :: Software Development :: Testing',
    'License :: OSI Approved :: MIT License',
    'Programming Language :: Python :: 3',
    'Programming Language :: Python :: 3.5',
    'Programming Language :: Python :: 3.6',
  ],
)
