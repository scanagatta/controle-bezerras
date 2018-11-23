var online = navigator.onLine;
  
  if(online==false) {
	$("#primeiralinha").append("<div class='alert alert-warning alert-dismissible text-center'>" +
			" <span class='fa-stack fa-sm'><i class='fa fa-wifi fa-stack-1x'></i>" +
			"<i class='fa fa-ban fa-stack-2x'></i></span>" +
			"<strong> Você está offline! Algumas ações não estarão disponíveis. " +
			"<span class='fa-stack fa-sm'><i class='fa fa-wifi fa-stack-1x'></i>" +
			"<i class='fa fa-ban fa-stack-2x'></i></span></strong> </div>");
  } 
  
  