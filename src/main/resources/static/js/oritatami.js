new Image().src = "/images/triangleleft_83849.png";
op="/images/triangleleft_83849.png";
cl="/images/triangledown_83849.png";
function dd(obj){

if(document.getElementById)
{document.getElementById(obj)
.style.display=='none'?document.getElementById(obj)
.style.display='':document.getElementById(obj)
.style.display='none';
document.getElementById(obj+"i").src.indexOf('triangledown_83849')>0?
document.getElementById(obj+"i").src=op:
document.getElementById(obj+"i").src=cl

} else if(document.all){
document.all(obj).style.display=='none'?
document.all(obj).style.display='':document.all(obj).style.display='none';
document.all(obj+"i").src.indexOf('triangledown_83849')>0?
document.all(obj+"i").src=op:document.all(obj+"i").src=cl

} else if(document.layers){
document.layers[obj].display=='none'?
document.layers[obj].display='':document.layers[obj].display='none';
document.layers[obj+"i"].src.indexOf('triangledown_83849')>0?
document.layers[obj+"i"].src=op:document.layers[obj+"i"].src=cl
}
}