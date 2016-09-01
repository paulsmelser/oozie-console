'use strict';

import {bootstrap} from '@angular/platform-browser-dynamic';
import {provide} from '@angular/core';
import { HTTP_PROVIDERS,  } from "@angular/http";
import {LocationStrategy, HashLocationStrategy, APP_BASE_HREF} from '@angular/common';
import "angular2-materialize";

import {AppComponent} from './app.component';

bootstrap(AppComponent, [
    provide(APP_BASE_HREF, {useValue: '/'}),
    provide(LocationStrategy, {useClass: HashLocationStrategy}),
    HTTP_PROVIDERS
]).catch(err => console.error(err));
