  function getCurrentDate() {
  const checkbox = document.getElementById('timeCheckbox');
  const dateDisplay = document.getElementById('dateDisplay'); // timeDisplay から名前を変更

  if (checkbox.checked) {
    const now = new Date();
    const formattedDate = now.toLocaleDateString('ja-JP', {
      year: 'numeric',
      month: '2-digit',
      day: '2-digit'
    });
    dateDisplay.textContent = `現在の日付: ${formattedDate}`;
  } else {
    dateDisplay.textContent = 'チェックを外しました';
  }
}