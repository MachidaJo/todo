new Image().src = "/images/triangleleft_83849.png";
op="/images/triangleleft_83849.png";
cl="/images/triangledown_83849.png";

window.addEventListener("load", execFunction);

function execFunction() {
    console.log("hello")
    if(!sessionStorage.getItem('isHide')) {
        sessionStorage.setItem("isHide", false);
    } else {
        var isHide = sessionStorage.getItem('isHide')
        if (isHide == 'true') {
            dd('折りたたみ指示B');
        } else {

        }
        console.log(isHide)
    }
}

function dd(obj){
    if(document.getElementById)
    {
        if (document.getElementById(obj).style.display == 'none') {
            sessionStorage.setItem("isHide", false);
        } else {
            sessionStorage.setItem("isHide", true);
        }

        document.getElementById(obj)
            .style.display=='none'?document.getElementById(obj)
            .style.display='':document.getElementById(obj)
            .style.display='none';
        document.getElementById(obj+"i").src.indexOf('triangledown_83849')>0?
        document.getElementById(obj+"i").src=op:
        document.getElementById(obj+"i").src=cl

    }
}