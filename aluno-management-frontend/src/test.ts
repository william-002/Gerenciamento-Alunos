// This file is required by karma.conf.js and loads recursively all the .spec and framework files

import 'zone.js/dist/zone-testing';
import { getTestBed } from '@angular/core/testing';
import {
  BrowserDynamicTestingModule,
  platformBrowserDynamicTesting
} from '@angular/platform-browser-dynamic/testing';

declare const require: any;

// First, initialize the Angular testing environment.
getTestBed().initTestEnvironment(
  BrowserDynamicTestingModule,
  platformBrowserDynamicTesting()
);
// Then we find all the tests.
//const context = require.context('./', true, /\.spec\.ts$/);

const context = (require as any).context(
  './',
  true,
  /(cpf\.pipe|login\.component|input-field\.component|view-field\.component)\.spec\.ts$/
);
context.keys().map(context);