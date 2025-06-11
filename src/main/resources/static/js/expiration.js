document.addEventListener("DOMContentLoaded", () => {
  const today = new Date();
  today.setHours(0, 0, 0, 0); // 時間部分を0にする

  const expired = new Date(today);
  const plan = new Date(today);
  expired.setDate(today.getDate() + 1);
  plan.setDate(today.getDate() + 3);

  const dateCells = document.querySelectorAll(".date");

  dateCells.forEach(cell => {
    const cellDate = new Date(cell.textContent.trim());
    cellDate.setHours(0, 0, 0, 0); // 時間部分を0にする

    if (cellDate.getTime() === today.getTime()) {
      cell.classList.add("today");
    } else if (cellDate.getTime() < expired.getTime()) {
      cell.classList.add("expired");
    } else if (cellDate.getTime() < plan.getTime()) {
      cell.classList.add("plan");
    }
  });
});