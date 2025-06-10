document.addEventListener("DOMContentLoaded", () => {
  const today = new Date(); // 現在の日付
  const dateCells = document.querySelectorAll(".date"); // 日付セルを取得

  dateCells.forEach(cell => {
    const cellDate = new Date(cell.textContent.trim()); // セルの日付を取得
    if (cellDate < today) {
      cell.classList.add("expired"); // 期限切れの場合にクラスを追加
    }
  });
});
