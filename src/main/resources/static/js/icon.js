document.addEventListener('DOMContentLoaded', function() {
    var radios = document.querySelectorAll('input[name="priority"]');
    var icons = document.querySelectorAll('.icon');

    updateImages();
    // 初期状態の画像を更新
    function updateImages() {
        icons.forEach(function(icon) {
            var value = icon.getAttribute('data-value');
            var radio = document.querySelector('input[name="priority"][value="' + value + '"]');
            if (radio && radio.checked) {
                icon.src = "images/" + getPriorityImage(value, true);
            } else {
                icon.src = "images/" + getPriorityImage(value, false);
            }
        });
    }
    // ラジオボタンの状態が変更されたときに画像を更新
    radios.forEach(function(radio) {
        radio.addEventListener('change', function() {
            icons.forEach(function(icon) {
                var value = icon.getAttribute('data-value');
                if (radio.checked && radio.value === value) {
                    icon.src = "images/" + getPriorityImage(value, true);
                } else {
                    icon.src = "images/" + getPriorityImage(value, false);
                }
            });
        });
    });

    // 画像をクリックしたときの動作
    icons.forEach(function(icon) {
        icon.addEventListener('click', function() {
            var value = icon.getAttribute('data-value');
            document.querySelector('input[name="priority"][value="' + value + '"]').click();
        });
    });

    // 画像の名前を返す関数
    function getPriorityImage(value, isSelected) {
        switch (value) {
            case "1": return isSelected ? "nullMAX.png" : "null.png";
            case "2": return isSelected ? "lowMAX.png" : "low.png";
            case "3": return isSelected ? "mediumMAX.png" : "medium.png";
            case "4": return isSelected ? "highMAX.png" : "high.png";
            default: return "";
        }
    }
});
