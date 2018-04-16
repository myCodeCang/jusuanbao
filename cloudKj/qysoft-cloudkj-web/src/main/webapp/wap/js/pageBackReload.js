    var isPageHide = false;
    window.addEventListener('pageshow', function () {
        if (isPageHide) {
            //执行刷新操作
            alert(606);
            pageBackReload();
        }
    });
    window.addEventListener('pagehide', function () {
        isPageHide = true;
    });