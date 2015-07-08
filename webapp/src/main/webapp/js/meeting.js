$(document).ready(function(){
	  $('.uiTabInPage .nav  a').click(function (e) {
      e.preventDefault();
      $(this).tab('show');
    })
})