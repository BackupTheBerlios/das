function submitForm(url, msg){
   var doSubmit = true;
   if (msg)
      doSubmit = confirm(msg);

   if (doSubmit){
      document.forms[0].action = url;
      document.forms[0].submit();
   }
}
