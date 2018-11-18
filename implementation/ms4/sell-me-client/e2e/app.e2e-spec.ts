import { SellMeClientPage } from './app.po';

describe('sell-me-client App', () => {
  let page: SellMeClientPage;

  beforeEach(() => {
    page = new SellMeClientPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
