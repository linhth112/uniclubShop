$(document).ready(function() {
    
    $("#btn-signup").click(function() {
        let email = $("#exampleInputEmail1").val().trim().toLowerCase();
        let passWord1 = $("#inputPassword1").val();
        let passWord2 = $("#inputPassword2").val();
        console.log(email + passWord1 + passWord2);
        const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

        if (email === "") {
            alert("Blank Email");
        } else if(!regex.test(email)) {
            alert("Invalid Email!")
        } else if (passWord1 === "") {
            alert("Blank PassWord")
        } else if (passWord2 === "") {
            alert("Blank Confirm Password")
        } else if(passWord1 !== passWord2) {
            alert("Passwords do not match")
        } else {
            $.ajax({
                method: "POST",
                url: "http://localhost:8080/login/signup",
                contentType: "application/json",
                data: JSON.stringify({
                    email: email,
                    password: passWord1
                }),
              }).done(function (result) {
                console.log(result);
                
                if (result && result.data) {
                  alert("SignUp successed!");
                } else {
                    alert("SignUp fail!")
                }
              });    
        }
    })
})