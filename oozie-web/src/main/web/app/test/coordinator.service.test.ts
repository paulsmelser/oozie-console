import {
    describe,
    expect,
    beforeEach,
    it,
    inject,
    injectAsync,
    beforeEachProviders
} from '@angular/core/testing';

import { CoordinatorService } from "../coordinator/coordinator.service";

export class CoordinatorServiceTest {
    constructor(private coordinatorService:CoordinatorService) {
    }

    test() {
        it('should get blogs',
            inject([CoordinatorService], (mockBackend, blogService) => {

                // // first we register a mock response - when a connection
                // // comes in, we will respond by giving it an array of (one)
                // // blog entries
                // mockBackend.connections.subscribe(
                //     (connection: MockConnection) => {
                //         connection.mockRespond(new Response(
                //             new ResponseOptions({
                //                     body: [
                //                         {
                //                             id: 26,
                //                             contentRendered: "<p><b>Hi there</b></p>",
                //                             contentMarkdown: "*Hi there*"
                //                         }]
                //                 }
                //             )));
                //     });
                // // with our mock response configured, we now can
                // // ask the blog service to get our blog entries
                // // and then test them
                // blogService.getBlogs().subscribe((blogs: BlogEntry[]) => {
                //     expect(blogs.length).toBe(1);
                //     expect(blogs[0].id).toBe(26);
                // });

            }));
    }



}