import { SpringBootAngular2StaticSamplePage } from './app.po';

describe('spring-boot-angular2-static-sample App', function() {
  let page: SpringBootAngular2StaticSamplePage;

  beforeEach(() => {
    page = new SpringBootAngular2StaticSamplePage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
