$(document).ready(function () {
  $("#btn-logout").click(function () {
    var currentToken = localStorage.getItem("token");
    if (currentToken === null) {
      alert("User has been logged out");
    } else {
      const isConfirmed = confirm("Are you sure you want to logout?");

      if (isConfirmed) {
        const tokenKey = "token";

        localStorage.removeItem(tokenKey);

        if (localStorage.getItem(tokenKey) === null) {
          alert("Logout successful");
          location.reload();
        } else {
          alert("Logout fail");
        }
      }
    }
  });
});
