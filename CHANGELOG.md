# Changelog

## [0.1.2](https://github.com/Fr33styler/SpringBootCalculator/compare/v0.1.1...v0.1.2) (2026-04-22)


### Bug Fixes

* Applied @Transactional so the lazy loaded collection wouldn't throw an exception because of the detached entity ([b746b2f](https://github.com/Fr33styler/SpringBootCalculator/commit/b746b2f26855483d008cb6da79c288eaccedcc2c))

## [0.1.1](https://github.com/Fr33styler/SpringBootCalculator/compare/v0.1.0...v0.1.1) (2026-04-11)


### Bug Fixes

* Fixed the N + 1 problem inside appendHistory method ([ff764ff](https://github.com/Fr33styler/SpringBootCalculator/commit/ff764ff316f439a73ed6e7e1d98459b67bd7707f))
* Made a HistoryRepository that handles the userhistory instead of entity manager ([b7fc5af](https://github.com/Fr33styler/SpringBootCalculator/commit/b7fc5af2f5d55454201b0d32a4505dde5275fe01))
* Now the passwords are sent in the body of the request ([b7fc5af](https://github.com/Fr33styler/SpringBootCalculator/commit/b7fc5af2f5d55454201b0d32a4505dde5275fe01))

## [0.1.0](https://github.com/Fr33styler/SpringBootCalculator/compare/v0.0.1...v0.1.0) (2026-04-09)


### Features

* Added changeAccountPassword and changeAccountRole ([6814ffb](https://github.com/Fr33styler/SpringBootCalculator/commit/6814ffb176e9caea509073b2c4cd1b502b2e1f27))
* Added deleteAccount rest api. ([8b15afd](https://github.com/Fr33styler/SpringBootCalculator/commit/8b15afd9cb489f69b610380e4e771f6a6d663919))
* Added transactional to make sure all queries happen in one go ([52f2854](https://github.com/Fr33styler/SpringBootCalculator/commit/52f2854f7c67bef3dbd01cc01b6eea5ddc81c34b))
* Now userHistory and History are mapped together to remove the creation of a third table that had to map them together instead. ([52f2854](https://github.com/Fr33styler/SpringBootCalculator/commit/52f2854f7c67bef3dbd01cc01b6eea5ddc81c34b))

## 0.0.1 (2026-04-08)


### Miscellaneous Chores

* Added github action workflow ([6e00371](https://github.com/Fr33styler/SpringBootCalculator/commit/6e00371b3d36912228a975f3cbcbe44fd37b7c76))
