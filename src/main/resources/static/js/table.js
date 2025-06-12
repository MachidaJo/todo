window.onload = function() {
    // 全てのタイトル列を取得
    let todoTitles = document.querySelectorAll('.todo-table .auto-width');
    let doneTitles = document.querySelectorAll('.done-table .auto-width');
    let allTitles = [...todoTitles, ...doneTitles];

    // 各タイトルの幅を確認し、最大の幅を取得
    let maxWidth = 110;
    allTitles.forEach(cell => {
        let cellWidth = cell.scrollWidth; // 使用する幅をscrollWidthで取得
        if (cellWidth > maxWidth) {
            maxWidth = cellWidth;
        }
    });

    // 最大の幅を全てのタイトル列に適用
    allTitles.forEach(cell => {
        cell.style.width = maxWidth + 'px';
    });
};