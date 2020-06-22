import { Injectable } from "@angular/core";
import { Router } from "@angular/router";

@Injectable({
  providedIn: "root",
})
export class PageAccessCheckService {
  private found = false;

  constructor(private router: Router) {}

  checkPageAccess(accessMenu) {
    this.found = false;
    return new Promise((resolve) => {
      this.getPageAccess(accessMenu, this.router.url, this.found).then(() => {
        return resolve(this.found);
      });
    });
  }

  getPageAccess(pages, url, _found) {
    return new Promise((resolve) => {
      let _promises = [];
      pages.forEach((page) => {
        _promises.push(
          new Promise((_resolve) => {
            if (page.path.split("/").length == url.split("/").length) {
              let promises = [];
              let found = true;
              page.path.split("/").forEach((p, key) => {
                if (p != "?" && p != url.split("/")[key]) {
                  found = false;
                }
                promises.push({});
              });
              Promise.all(promises).then(() => {
                if (found) {
                  _found = true;
                  this.found = true;
                  return resolve({});
                }
                if (page.subMenu.length > 0) {
                  this.getPageAccess(page.subMenu, url, _found).then(() => {
                    return _resolve({});
                  });
                } else {
                  return _resolve({});
                }
              });
            } else {
              if (page.subMenu.length > 0) {
                this.getPageAccess(page.subMenu, url, _found).then(() => {
                  return _resolve({});
                });
              } else {
                return _resolve({});
              }
            }
          })
        );
      });
      Promise.all(_promises).then(() => {
        return resolve({});
      });
    });
  }
}
