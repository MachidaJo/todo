// ページがロードされたときに実行
window.onload = function() {
    // 全てのタイトル列を取得
    let titleCells = document.querySelectorAll('.title-width');

    // 各タイトルの幅を確認し、最大の幅を取得
    let maxWidth = 0;
    titleCells.forEach(cell => {
        let cellWidth = cell.offsetWidth;
        if (cellWidth > maxWidth) {
            maxWidth = cellWidth;
        }
    });

    // 最大の幅を全てのタイトル列に適用
    titleCells.forEach(cell => {
        cell.style.width = maxWidth + 'px';
    });
};