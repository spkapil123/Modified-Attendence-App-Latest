$(function() {

         $("#fname_error_message").hide();
         $("#sname_error_message").hide();
         $("#userName_error_message").hide();
         $("#email_error_message").hide();
         $("#password_error_message").hide();
         $("#retype_password_error_message").hide();

         var error_fname = false;
         var error_sname = false;
         var error_userName = false;
         var error_email = false;
         var error_password = false;
         var error_retype_password = false;

         $("#form_fname").focusout(function(){
            check_fname();
         });
         $("#form_sname").focusout(function() {
            check_sname();
         });
         $("#form_userName").focusout(function() {
             check_userName();
          });
         $("#form_email").focusout(function() {
            check_email();
         });
         $("#form_password").focusout(function() {
            check_password();
         });
         $("#form_retype_password").focusout(function() {
            check_retype_password();
         });

         function check_fname() {
            var pattern = /^[a-zA-Z]*$/;
            var fname = $("#form_fname").val();
            if (pattern.test(fname) && fname !== '') {
               $("#fname_error_message").hide();
               $("#form_fname").css("border-bottom","2px solid #34F458");
            } else {
               $("#fname_error_message").html("Should contain only Characters");
               $("#fname_error_message").show();
               $("#form_fname").css("border-bottom","2px solid #F90A0A");
               error_fname = true;
            }
         }

         function check_sname() {
            var pattern = /^[a-zA-Z]*$/;
            var sname = $("#form_sname").val()
            if (pattern.test(sname) && sname !== '') {
               $("#sname_error_message").hide();
               $("#form_sname").css("border-bottom","2px solid #34F458");
            } else {
               $("#sname_error_message").html("Should contain only Characters");
               $("#sname_error_message").show();
               $("#form_sname").css("border-bottom","2px solid #F90A0A");
               error_fname = true;
            }
         }
         
         function check_userName() {
             var pattern = /^[a-zA-Z0-9]*$/;
             var username = $("#form_userName").val()
             if (pattern.test(username) && username !== '') {
                $("#userName_error_message").hide();
                $("#form_userName").css("border-bottom","2px solid #34F458");
             } else {
                $("#userName_error_message").html("Enter only Characters,numbers");
                $("#userName_error_message").show();
                $("#form_userName").css("border-bottom","2px solid #F90A0A");
                error_userName = true;
             }
          }

         function check_password() {
            var password_length = $("#form_password").val().length;
            if (password_length < 8) {
               $("#password_error_message").html("Atleast 8 Characters");
               $("#password_error_message").show();
               $("#form_password").css("border-bottom","2px solid #F90A0A");
               error_password = true;
            } else {
               $("#password_error_message").hide();
               $("#form_password").css("border-bottom","2px solid #34F458");
            }
         }

         function check_retype_password() {
            var password = $("#form_password").val();
            var retype_password = $("#form_retype_password").val();
            if (password !== retype_password) {
               $("#retype_password_error_message").html("Passwords Did not Matched");
               $("#retype_password_error_message").show();
               $("#form_retype_password").css("border-bottom","2px solid #F90A0A");
               error_retype_password = true;
            } else {
               $("#retype_password_error_message").hide();
               $("#form_retype_password").css("border-bottom","2px solid #34F458");
            }
         }

         function check_email() {
            var pattern = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
            var email = $("#form_email").val();
            if (pattern.test(email) && email !== '') {
               $("#email_error_message").hide();
               $("#form_email").css("border-bottom","2px solid #34F458");
            } else {
               $("#email_error_message").html("Invalid Email");
               $("#email_error_message").show();
               $("#form_email").css("border-bottom","2px solid #F90A0A");
               error_email = true;
            }
         }

         $("#registration_form").submit(function() {
            error_fname = false;
            error_sname = false;
            error_email = false;
            error_userName = false;
            error_password = false;
            error_retype_password = false;

            check_fname();
            check_sname();
            check_userName();
            check_email();
            check_password();
            check_retype_password();

            if (error_fname === false && error_sname === false && error_userName === false && error_email === false && error_password === false && error_retype_password === false) {
               alert("Registration Successfull");
               return true;
            } else {
               alert("Please Fill the form Correctly");
               return false;
            }


         });
      });

$(document).ready(function(){
	var error = false;
    $('#form_userName').on('input', function()
    {   
       var user=$('#form_userName').val();
       $.ajax({
            type: "POST",
            url:"register",
            data:{"userName":user},
            success: function (data) {
               if(data=='True'){
                 $("#userName_error_message").html("userName available");
                 $("#userName_error_message").show();
                 $("#form_userName").css("border-bottom","2px solid #008000");
                 error=false;
               }else{
            	   $("#userName_error_message").html("UserName not available!");
                   $("#userName_error_message").show();
                   $("#form_userName").css("border-bottom","2px solid #F90A0A");
                   error=true;
               }
            }
          });                                
        });
    
    $('#form_userName').focusout('input', function()
    	    {   
    	        if(error){
    	        	document.getElementById("form_userName").value = "";
    	        	$("#form_userName").css("border-bottom","2px solid #F90A0A");
    	        }
    	    });
      });

