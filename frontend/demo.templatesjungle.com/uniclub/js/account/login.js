$(document).ready(function () {
  $(".btn-login").click(function () {
    var email = $("#login-email").val().toLowerCase();
    var password = $("#login-password").val();

    $.ajax({
      method: "POST",
      url: "http://localhost:8080/login/signin",
      data: { username: email, password: password },
    }).done(function (result) {
      if (result && result.statusCode == 200) {
        localStorage.setItem("token", result.data);
        alert("Login successed!");
        window.location.href = "index-2.html";
      }
    });
  });
});
