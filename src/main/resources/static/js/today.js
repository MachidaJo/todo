    function getCurrentTime() {
      const checkbox = document.getElementById('timeCheckbox');
      const timeDisplay = document.getElementById('timeDisplay');

      if (checkbox.checked) {
        const now = new Date();
        const formattedTime = now.toLocaleTimeString('ja-JP', { hour: '2-digit', minute: '2-digit', second: '2-digit' });
        timeDisplay.textContent = `現在時刻: ${formattedTime}`;
      } else {
        timeDisplay.textContent = 'チェックを外しました';
      }
    }