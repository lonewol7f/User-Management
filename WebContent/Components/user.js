$(document).ready(function()
{
	 $("#alertSuccess").hide();
 	 $("#alertError").hide();
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{ 
	// Clear alerts---------------------
	 $("#alertSuccess").text(""); 
	 $("#alertSuccess").hide(); 
	 $("#alertError").text(""); 
	 $("#alertError").hide(); 

	// Form validation-------------------
	var status = validateUserForm();
	if (status != true) 
	 { 
		 $("#alertError").text(status); 
		 $("#alertError").show(); 
		 return; 
	 } 
	// If valid------------------------
	var type = ($("#hididSave").val() == "") ? "POST" : "PUT"; 
	 $.ajax( 
 	{ 
		 url : "UserAPI", 
		 type : type, 
		 data : $("#formUser").serialize(), 
		 dataType : "text", 
		 complete : function(response, status) 
 		{ 
 			onUserSaveComplete(response.responseText, status); 
 		} 
 	}); 
});

function onUserSaveComplete(response, status)
{ 
	if (status == "success") 
 	{ 
		 var resultSet = JSON.parse(response); 
		 if (resultSet.status.trim() == "success") 
		 { 
			 $("#alertSuccess").text("Successfully saved."); 
			 $("#alertSuccess").show(); 
			 $("#divUserGrid").html(resultSet.data); 
		 } 
		 else if (resultSet.status.trim() == "error") 
		 { 
			 $("#alertError").text(resultSet.data); 
			 $("#alertError").show(); 
 		 } 
 	} 
    else if (status == "error") 
 	{ 
		 $("#alertError").text("Error while saving."); 
		 $("#alertError").show(); 
	 } 
	 else
  	 { 
		 $("#alertError").text("Unknown error while saving.."); 
		 $("#alertError").show(); 
 	 }
	 $("#hididSave").val(""); 
	 $("#formUser")[0].reset(); 
}

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
	{ 
		 $("#hididSave").val($(this).data("id")); 
		 $("#firstName").val($(this).closest("tr").find('td:eq(0)').text()); 
		 $("#lastName").val($(this).closest("tr").find('td:eq(1)').text()); 
		 $("#email").val($(this).closest("tr").find('td:eq(2)').text());  
		 $("#role").val($(this).closest("tr").find('td:eq(3)').text());
         $("#password").val($(this).closest("tr").find('td:eq(4)').text());
});

$(document).on("click", ".btnRemove", function(event)
	{ 
		 $.ajax( 
		 { 
		 	url : "UserAPI", 
			type : "DELETE", 
		 	data : "id=" + $(this).data("id"),
		 	dataType : "text", 
		 	complete : function(response, status) 
		 	{ 
		 		onUserDeleteComplete(response.responseText, status); 
		 	} 
	 }); 
});
		
function onUserDeleteComplete(response, status)
{ 
	if (status == "success") 
	 { 
		 var resultSet = JSON.parse(response); 
		 if (resultSet.status.trim() == "success") 
		 { 
			 $("#alertSuccess").text("Successfully deleted."); 
			 $("#alertSuccess").show(); 
			 $("#divUserGrid").html(resultSet.data); 
		 } else if (resultSet.status.trim() == "error") 
		 { 
			 $("#alertError").text(resultSet.data); 
			 $("#alertError").show(); 
		 } 
		 } else if (status == "error") 
		 { 
			 $("#alertError").text("Error while deleting."); 
			 $("#alertError").show(); 
		 } else
		 { 
			 $("#alertError").text("Unknown error while deleting.."); 
			 $("#alertError").show(); 
	 } 
}


// CLIENT-MODEL================================================================
function validateUserForm()
{
	//first name
	if ($("#firstName").val().trim() == ""){
		return "Insert first name.";
	}
	//last name
	if ($("#lastName").val().trim() == ""){
		return "Insert last name.";
	}
	// email
	if ($("#email").val().trim() == ""){
		return "Insert email.";
	}
		
	//role
	if ($("#role").val().trim() == ""){
		return "Insert role.";
	}
	//password
	if ($("#password").val().trim() == ""){
		return "Insert password.";
	}
	return true;
}