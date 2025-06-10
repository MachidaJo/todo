function updateCounts() {
  updateCompletedCount();
  updateIncompleteCount();
}

function countIncompleteRows() {
  // テーブルを取得
  var table = document.querySelector('table');

  // テーブルのtbodyを取得
  var tbody = table.getElementsByTagName('tbody')[0];

  // 条件に合う行数をカウント
  var incompleteRows = 0;
  var rows = tbody.getElementsByTagName('tr');

  for (var i = 0; i < rows.length; i++) {
    var checkBox = rows[i].querySelector('input[type="checkbox"]');
    if (checkBox && !checkBox.checked) {
      incompleteRows++;
    }
  }

  return incompleteRows;
}

function countCompletedRows() {
  // テーブルを取得
  var table = document.getElementById('折りたたみ指示B');

  // テーブルのtbodyを取得
  var tbody = table.getElementsByTagName('tbody')[0];

  // 条件に合う行数をカウント
  var completedRows = 0;
  var rows = tbody.getElementsByTagName('tr');

  for (var i = 0; i < rows.length; i++) {
    var checkBox = rows[i].querySelector('input[type="checkbox"]');
    if (checkBox && checkBox.checked) {
      completedRows++;
    }
  }

  return completedRows;
}

 function updateCompletedCount() {
    var completedCount = countCompletedRows();
    document.getElementById('completedCount').innerText = '(' + completedCount + '件)';
  }

  function updateIncompleteCount() {
    var incompleteCount = countIncompleteRows();
    document.getElementById('incompleteCount').innerText = '(' + incompleteCount + '件)';
  }

  // ページロード時に件数を更新
  window.onload = updateCounts;