
op="/images/triangleleft_83849.png"; // 開いた状態の矢印
cl="/images/triangledown_83849.png"; // 閉じた状態の矢印

window.addEventListener("load", execFunction);

function execFunction() {
    // セッション情報が登録されていなかったら
    if(!sessionStorage.getItem('isOpen')) {
        // trueで初期化する
        sessionStorage.setItem("isOpen", false);
    } else {
        // 現在の表示非表示状態を取得
        var isOpen = sessionStorage.getItem('isOpen')
        // セッションに非表示フラグが有効だったら折りたたみ処理を実行する
        if (isOpen == 'true') {
            dd('折りたたみ');
        } else {
            
        }
    }
}

function dd(obj){
    if(document.getElementById)
    {   
        // クリックしたときに非表示状態だったら
        if (document.getElementById(obj).style.display == 'none') {
            // true（表示状態）を設定
            sessionStorage.setItem("isOpen", true);
            document.getElementById(obj).style.display='';
        } else {
            // false（非表示状態）を設定
            sessionStorage.setItem("isOpen", false);
            document.getElementById(obj).style.display='none';
        }

        let obji = document.getElementById(obj+"i");
        if (obji.src.indexOf('triangledown_83849') > 0) {
            obji.src = op;
        } else {
            obji.src = cl;
        }
    }
}